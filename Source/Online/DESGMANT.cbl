      *Copyright © 2019-2020, 2021 ATOS SYNTEL, INC. and affiliates.
      *All Rights Reserved. This software (the "Software") is a 
      *copyrighted work owned by Atos Syntel, Inc. and its affiliates.
      *Nothing contained in this notice may be interpreted as an 
      *assignment of the ownership of the copyright pertaining to the
      *Software.
      *
      *If you (the "Customer") have received the Software as part of a
      *Deliverable under the applicable contract, or Master Service
      *Agreement, or equivalent (the "Contract"), then the Software is
      *being licensed to the Customer under the relevant Intellectual
      *Property provisions governing Atos Syntel Background 
      *Intellectual Property in the Contract (the "IP Clause"), and the 
      *Customer's use of the Software is governed by the IP Clause. The 
      *Software is made available to the Customer solely for the 
      *purpose contained in the IP CLAUSE. Any reproduction or 
      *redistribution of the Software not in accordance with the IP 
      *Clause is expressly prohibited.
      * 
      *WITHOUT LIMITING THE FOREGOING, COPYING OR REPRODUCTION OF THE
      *SOFTWARE TO ANY OTHER SERVER OR LOCATION FOR FURTHER 
      *REPRODUCTION OR REDISTRIBUTION IS EXPRESSLY PROHIBITED, UNLESS 
      *SUCH REPRODUCTION OR REDISTRIBUTION IS EXPRESSLY PERMITTED BY 
      *THE IP CLAUSE.
      * 
      *THE SOFTWARE IS WARRANTED, IF AT ALL, ONLY ACCORDING TO THE 
      *TERMS OF THE CONTRACT. EXCEPT AS WARRANTED IN THE CONTRACT, 
      *ATOS SYNTEL, INC. AND ITS AFFILIATES HEREBY DISCLAIM ALL 
      *WARRANTIES AND CONDITIONS WITH REGARD TO THE SOFTWARE, INCLUDING 
      *ALL WARRANTIES AND CONDITIONS OF MERCHANTABILITY, WHETHER 
      *EXPRESS, IMPLIED OR STATUTORY, FITNESS FOR A PARTICULAR PURPOSE, 
      *TITLE AND NON-INFRINGEMENT.
      *
       IDENTIFICATION DIVISION.
       PROGRAM-ID. DESGMANT.
       AUTHOR. ATOS SYNTEL.
       ENVIRONMENT DIVISION.
       DATA DIVISION.
      ***************************************************
       WORKING-STORAGE SECTION.

       01  PROGRAM-DESC                PIC X(20) VALUE 'DESG MAINT'.
 
       01  WS-TEMP.
           05 WS-MSG                   PIC X(50).
           05 WS-MCOMM                 PIC X(10) VALUE SPACES.
           05 WS-DESC                  PIC X(50) VALUE SPACES.

       01  WS-SWITCHES.
           05  WS-ACT                      PIC X(1).
               88  VALID-ACTION                     VALUE 'Y'.

           05  WS-INPUT                    PIC X(1). 
               88  VALID-INPUT                      VALUE 'Y'.
               88  INVALID-INPUT                    VALUE 'N'.

           05  WS-DESGVAL                  PIC X(1).
               88  DESG-EXIST                       VALUE 'F'.
               88  DESG-NOTFND                      VALUE 'N'.
               88  DESG-DBERR                       VALUE 'E'.

       01  WS-COMMAREA. 
           05 WS-DESGCD                PIC X(05) VALUE SPACES.
           05 WS-BASICPAY              PIC 9(05) VALUE ZEROES.
           05 WS-HRAPAY                PIC 9(05) VALUE ZEROES.
           05 WS-GROSSPAY              PIC 9(07) VALUE ZEROES.
           05 WS-ACTION                PIC X(01) VALUE SPACES.
           05 WS-CRUD-FLAG             PIC X(1).
              88 ADD-REC               VALUE 'C'.
              88 CHANGE-REC            VALUE 'U'.
              88 DELETE-REC            VALUE 'D'.
              88 DISPLAY-REC           VALUE 'R'.
           05 WS-ENTRY                 PIC 9(02) VALUE ZEROES.
           05 WS-PROCESS               PIC X(10) VALUE SPACES.

       01  RESPONSE-CODE               PIC S9(08) COMP.
       01  ALIAS-NAME                  PIC X(32).
       01  COMMAND                     PIC X(1).
           COPY DESGSET.
       01  CONNECT-STATUS              PIC 9(6).

           COPY SQLCA.      

           COPY SQLAN.

           COPY DFHAID.

           COPY ATTR.
      ***************************************************
       LINKAGE SECTION.

       01  DFHCOMMAREA.
           05 DESGCD                   PIC X(05).
           05 BASICPAY                 PIC 9(05).
           05 HRAPAY                   PIC 9(05).
           05 GROSSPAY                 PIC 9(07).
           05 ACTION                   PIC X(01).
           05 CRUD-FLAG                PIC X(1).
           05 ENTRYT                   PIC 9(2).
           05 PROCESS                  PIC X(10).
      ***************************************************
       PROCEDURE DIVISION.
       MAIN-PARA.

           MOVE "Murach" TO ALIAS-NAME.
           MOVE "C" TO COMMAND.
           CALL "LIBERCONNECTDB" USING ALIAS-NAME, COMMAND,
                                       CONNECT-STATUS.

           IF EIBAID = DFHPF3
              MOVE 'EXIT FROM DESIGNATION MASTER MAINTENANCE' TO WS-MSG
               EXEC CICS
                    SEND TEXT FROM(WS-MSG)
                    ERASE
                    FREEKB
               END-EXEC
               
               EXEC CICS RETURN
                    TRANSID('EMPM')
               END-EXEC

           END-IF.   
      *
           IF EIBCALEN = 0 
              MOVE 'START' TO WS-PROCESS
              MOVE SPACES TO WS-MSG
              EXEC CICS
                    SEND TEXT FROM(WS-MSG)
                    ERASE
                    FREEKB
              END-EXEC
              MOVE -1 TO ACTIONL
           ELSE
              MOVE DFHCOMMAREA TO WS-COMMAREA

              PERFORM RECEIVE-MAP-PARA
              PERFORM VALIDATE-ACTION-PARA

              IF VALID-ACTION 
                 PERFORM PROCESS-PARA
              END-IF
           END-IF. 

           PERFORM SEND-MAP-PARA.

      ***************************************************
       SEND-MAP-PARA.
           EXEC CICS SEND MAP('DESGMNT')
                MAPSET('DESGSET')
                FROM(DESGMNTO)
                FREEKB
           END-EXEC.

           EXEC CICS RETURN TRANSID('DESG')
               COMMAREA(WS-COMMAREA)
               LENGTH(LENGTH OF WS-COMMAREA)
           END-EXEC.   

      ***************************************************        
       RECEIVE-MAP-PARA.
           EXEC CICS RECEIVE MAP('DESGMNT')
                MAPSET('DESGSET')
                INTO(DESGMNTI)
           END-EXEC.    

      ***************************************************
       PROCESS-PARA.
           IF DESGCDI NOT = WS-DESGCD
              PERFORM VALIDATE-DESGCD-PARA 
              IF NOT DESG-DBERR 
                 PERFORM ACTION-PROCESS-VAL-PARA
              END-IF
           END-IF.

           IF WS-ENTRY > 0 
              IF ACTIONI NOT = 'D' 
                 IF DESGDSI = SPACES
                    PERFORM VALIDATE-DESGDS-PARA
                 ELSE
                    MOVE DESGDSI TO WS-DESC
                    MOVE 'Y'    TO WS-INPUT
                 END-IF
                 IF BASPAYI = ZEROES OR BASPAYI NOT = WS-BASICPAY
                    PERFORM VALIDATE-BASICPAY-PARA
                 END-IF
                 IF HRAPAYI = ZEROES OR HRAPAYI NOT = WS-HRAPAY
                    PERFORM VALIDATE-HRAPAY-PARA  
                 END-IF
                 COMPUTE WS-GROSSPAY = WS-BASICPAY + WS-HRAPAY 
              END-IF

              EVALUATE ACTIONI
                  WHEN 'A'
                     PERFORM INSERT-PARA
                  WHEN 'C'   
                     PERFORM UPDATE-PARA
                  WHEN 'D'  
                     PERFORM DELETE-PARA
              END-EVALUATE
           END-IF.


      ***************************************************
       ACTION-PROCESS-VAL-PARA.
           EVALUATE ACTIONI 
           WHEN 'A'
              IF DESG-EXIST
                 MOVE 'DESG CODE ALREADY EXISTS, ENTER NEW CODE' 
                          TO MSGLINO
                 MOVE 'N' TO WS-INPUT
                 MOVE -1  TO DESGCDL
              ELSE
                 MOVE 'Y' TO WS-INPUT
		         ADD 1    TO WS-ENTRY
                 MOVE -1  TO DESGDSL
                 PERFORM DB-DATAMOVE-PARA
                 PERFORM SEND-MAP-PARA
              END-IF
           WHEN 'C'
              IF DESG-NOTFND
                 MOVE 'DESG CODE DOES NOT EXISTS, ENTER NEW CODE' 
                          TO MSGLINO
                 MOVE -1  TO DESGCDL
                 MOVE 'N' TO WS-INPUT
              ELSE
                 MOVE 'Y' TO WS-INPUT
                 ADD 1    TO WS-ENTRY
                 MOVE -1  TO DESGDSL
                 PERFORM DB-DATAMOVE-PARA
                 PERFORM SEND-MAP-PARA
              END-IF
           WHEN 'D'
              IF DESG-NOTFND
                 MOVE 'DESG CODE DOES NOT EXISTS, ENTER NEW CODE' 
                          TO MSGLINO
                 MOVE -1  TO DESGCDL
                 MOVE 'N' TO WS-INPUT
              ELSE
                 MOVE 'Y' TO WS-INPUT
                 ADD 1    TO WS-ENTRY
                 PERFORM DB-DATAMOVE-PARA
                 PERFORM SEND-MAP-PARA
              END-IF
           END-EVALUATE.

      ***************************************************
       DB-DATAMOVE-PARA.

           MOVE WS-DESC     TO DESGDSO
           MOVE WS-BASICPAY TO BASPAYO
           MOVE WS-HRAPAY   TO HRAPAYO
           MOVE WS-GROSSPAY TO GRSPAYO.

      ***************************************************
       VALIDATE-ACTION-PARA.

           IF ACTIONI NOT = 'A' AND ACTIONI NOT = 'D' AND 
               ACTIONI NOT = 'C'
               MOVE 'VALID ACTION VALUES - A/D/C'  
                 TO MSGLINO 
               MOVE -1       TO ACTIONL
               MOVE 'ACTION' TO WS-PROCESS
               MOVE 'N' TO WS-ACT
            ELSE 
               IF WS-ACTION NOT = ACTIONI
                  MOVE 'ENTER DESIGNATION CODE'  
                      TO MSGLINO 
                  MOVE ACTIONI TO WS-ACTION
                  MOVE 'Y' TO WS-ACT
               END-IF
           END-IF.
  
      ***************************************************
       VALIDATE-DESGCD-PARA.

           IF DESGCDI = SPACES 
              MOVE 'DESIGNATION CODE CAN NOT BE EMPTY'  
                 TO MSGLINO 
              MOVE 'DESGCD' TO WS-PROCESS
              MOVE -1       TO DESGCDL
              MOVE 'N'      TO WS-DESGVAL
              MOVE 'N'      TO WS-INPUT
           ELSE 
              IF WS-DESGCD NOT = DESGCDI
                 MOVE DESGCDI TO WS-DESGCD
                 PERFORM DESGCODE-VALIDATE
              END-IF
           END-IF.             
  
      ***************************************************
       VALIDATE-DESGDS-PARA.
           IF DESGDSI = SPACES AND ACTION NOT = 'D'  
              MOVE 'ENTER DESIGNATION DESCRIPTION' TO MSGLINO
              MOVE 'DESC' TO WS-PROCESS
              MOVE -1       TO DESGDSL
              MOVE 'N'    TO WS-INPUT
           END-IF.

      ***************************************************
       VALIDATE-BASICPAY-PARA.

           IF BASPAYI = ZEROES OR BASPAYI = LOW-VALUES
              MOVE 'BASIC PAY CAN NOT BE EMPTY'  
                 TO MSGLINO 
              MOVE 'BASPAY' TO WS-PROCESS
              MOVE -1       TO BASPAYL
              MOVE 'N'      TO WS-DESGVAL
              MOVE 'N'      TO WS-INPUT
           ELSE 
              IF BASPAYI NOT = WS-BASICPAY
                 MOVE BASPAYI TO WS-BASICPAY
              END-IF
           END-IF. 

      ***************************************************
       VALIDATE-HRAPAY-PARA.

           IF HRAPAYI = ZEROES OR HRAPAYI = LOW-VALUES
              MOVE 'HRA PAY CAN NOT BE EMPTY'  
                 TO MSGLINO 
              MOVE 'HRAPAY' TO WS-PROCESS
              MOVE -1       TO HRAPAYL
              MOVE 'N'      TO WS-DESGVAL
              MOVE 'N'      TO WS-INPUT
           ELSE 
              IF HRAPAYI NOT = WS-HRAPAY
                 MOVE HRAPAYI  TO WS-HRAPAY
                 COMPUTE WS-GROSSPAY = WS-BASICPAY + WS-HRAPAY
              END-IF
           END-IF.  

      ***************************************************
       INITIALIZE-FIELD-PARA.

           MOVE SPACES TO ACTIONO WS-ACTION
           MOVE SPACES TO DESGCDO WS-DESGCD
           MOVE SPACES TO DESGDSO WS-DESC
	   MOVE ZEROES TO BASPAYO WS-BASICPAY
           MOVE ZEROES TO HRAPAYO WS-HRAPAY
           MOVE ZEROES TO GRSPAYO WS-GROSSPAY
           MOVE ZEROES TO WS-ENTRY
           MOVE -1 TO ACTIONL
           MOVE 'N' TO WS-INPUT    
           MOVE 'START' TO WS-PROCESS.

      ***************************************************
        DESGCODE-VALIDATE.

           EXEC SQL
               SELECT DESGDESC, BASICPAY, HRA, GROSSPAY  
                   INTO :WS-DESC, :WS-BASICPAY, 
                        :WS-HRAPAY, :WS-GROSSPAY  
                   FROM DESIGNATION
                   WHERE DESGCODE = :WS-DESGCD
           END-EXEC.  

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL  
                 MOVE 'DESIGNATION CODE FOUND' 
                      TO MSGLINO   
                 MOVE 'F' TO WS-DESGVAL  
              WHEN WS-SQL-RETURN-NO-ENTRY   
                 MOVE 'DESIGNATION CODE DOES NOT EXIST' 
                      TO MSGLINO                                       
                 MOVE 'N' TO WS-DESGVAL
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN SELECT' TO MSGLINO                   
                 MOVE 'ERROR' TO WS-PROCESS
                 MOVE 'E' TO WS-DESGVAL
                 PERFORM INITIALIZE-FIELD-PARA
           END-EVALUATE.  

      ***************************************************
       DELETE-PARA.

           EXEC SQL
               DELETE FROM DESIGNATION 
                   WHERE DESGCODE = :DESGCDI
           END-EXEC.           

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'DESIGNATION CODE DELETED' 
                      TO MSGLINO   
                 PERFORM INITIALIZE-FIELD-PARA
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'DESIGNATION CODE DOES NOT EXIST, TRY AGAIN' 
                      TO MSGLINO                    
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN DELETE' TO MSGLINO  
                 PERFORM INITIALIZE-FIELD-PARA 
           END-EVALUATE.
           PERFORM INITIALIZE-FIELD-PARA.

      ***************************************************
       INSERT-PARA.

           COMPUTE WS-GROSSPAY = BASPAYI + HRAPAYI.
 
           EXEC SQL
               INSERT INTO DESIGNATION 
               (DESGCODE, DESGDESC, BASICPAY, HRA, GROSSPAY) 
                      VALUES 
               (:DESGCDI, :DESGDSI, :BASPAYI, :HRAPAYI, 
                :WS-GROSSPAY)
           END-EXEC.           

           MOVE SQLCODE TO WS-SQLCODE.    
             
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'DESIGNATION CODE INSERTED' 
                      TO MSGLINO   
              WHEN WS-SQL-RETURN-DUPKEY                                      
                 MOVE 'DESIGNATION CODE ALREADY EXIST, TRY AGAIN' 
                      TO MSGLINO              
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN INSERT' TO MSGLINO                  
           END-EVALUATE.

           PERFORM INITIALIZE-FIELD-PARA.

      ***************************************************
       UPDATE-PARA.

           COMPUTE WS-GROSSPAY = BASPAYI + HRAPAYI.

           EXEC SQL
               UPDATE DESIGNATION 
                   SET DESGCODE = :DESGCDI,
                       DESGDESC = :DESGDSI,
                       BASICPAY = :BASPAYI, 
                       HRA      = :HRAPAYI, 
                       GROSSPAY = :WS-GROSSPAY
                   WHERE DESGCODE = :DESGCDI
           END-EXEC.       
    
           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'DESIGNATION CODE UPDATED' 
                      TO MSGLINO   
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'DESIGNATION CODE DOES NOT EXIST, TRY AGAIN' 
                      TO MSGLINO                    
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN UPDATE' TO MSGLINO                   
           END-EVALUATE.

           PERFORM INITIALIZE-FIELD-PARA.

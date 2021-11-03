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
       PROGRAM-ID. MEMPMANT.
       AUTHOR. ATOS SYNTEL.
       ENVIRONMENT DIVISION.
       DATA DIVISION.
      ***************************************************

      ***************************************************

       WORKING-STORAGE SECTION.

       01  PROGRAM-DESC                PIC X(20) VALUE 'EMP MAINT'.
 
       01  WS-TEMP.
           05 WS-EMPNM                 PIC X(50).
           05 WS-DEPTDS                PIC X(50).
           05 WS-DESGDS                PIC X(50).
           05 WS-BASICPAY              PIC 9(05).
           05 WS-HRAPAY                PIC 9(05).
           05 WS-GROSSPAY              PIC 9(07).
           05 WS-LOC                   PIC X(50).
           05 WS-MSG                   PIC X(50).
           05 WS-MCOMM                 PIC X(10) VALUE SPACES.

       01  WS-SWITCHES.
           05  WS-ACT                      PIC X(1).
               88  VALID-ACTION                     VALUE 'Y'.

           05  WS-INPUT                    PIC X(1). 
               88  VALID-INPUT                      VALUE 'Y'.
               88  INVALID-INPUT                    VALUE 'N'.

           05  WS-EMPVAL                  PIC X(1).
               88  EMP-EXIST                       VALUE 'F'.
               88  EMP-NOTFND                      VALUE 'N'.
               88  EMP-DBERR                       VALUE 'E'.

           05  WS-DESGVAL                  PIC X(1).
               88  DESG-EXIST                       VALUE 'F'.
               88  DESG-NOTFND                      VALUE 'N'.
               88  DESG-DBERR                       VALUE 'E'.

           05  WS-DEPTVAL                  PIC X(1).
               88  DEPT-EXIST                       VALUE 'F'.
               88  DEPT-NOTFND                      VALUE 'N'.
               88  DEPT-DBERR                       VALUE 'E'.

       01  WS-COMMAREA. 
           05 WS-EMPCD                 PIC X(08) VALUE SPACES.
           05 WS-DEPTCD                PIC X(05).
           05 WS-DESGCD                PIC X(05). 
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
       01  CONNECT-STATUS              PIC 9(6).

           COPY SQLCA.      

           COPY SQLAN.
           COPY DFHAID.

           COPY EMPSETC.
      ***************************************************
       LINKAGE SECTION.

       01  DFHCOMMAREA.
           05 EMPCD                    PIC X(08).
           05 DEPTCD                   PIC X(5).
           05 DESGCD                   PIC X(5).
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
              MOVE 'EXIT FROM EMPLOYEE MASTER MAINTENANCE' TO WS-MSG
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
      *        PERFORM SEND-MAP-PARA
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
           EXEC CICS SEND MAP('EMPMAPC')
                MAPSET('EMPSETC')
                FROM(EMPMAPCO)
                FREEKB
           END-EXEC.

           EXEC CICS RETURN TRANSID('MEMP')
               COMMAREA(WS-COMMAREA)
               LENGTH(LENGTH OF WS-COMMAREA)
           END-EXEC.   

      ***************************************************        
       RECEIVE-MAP-PARA.
           EXEC CICS RECEIVE MAP('EMPMAPC')
                MAPSET('EMPSETC')
                INTO(EMPMAPCI)
           END-EXEC.    

      ***************************************************
       PROCESS-PARA.
           IF EMPCDI NOT = WS-EMPCD
              PERFORM VALIDATE-EMPCD-PARA 
              IF NOT EMP-DBERR 
                 PERFORM ACTION-PROCESS-VAL-PARA
              END-IF
           END-IF.

           IF WS-ENTRY > 0 
              IF ACTIONI NOT = 'D' 
                 IF EMPNMI = SPACES
                    PERFORM VALIDATE-EMPNM-PARA
                 ELSE
                    MOVE EMPNMI TO WS-EMPNM
                    MOVE 'Y'    TO WS-INPUT
                 END-IF
                 IF DEPTCDI = SPACES OR DEPTCDI NOT = WS-DEPTCD OR
                    DESGCDI = SPACES OR DESGCDI NOT = WS-DESGCD
                    IF DEPTCDI = SPACES OR DEPTCDI NOT = WS-DEPTCD
                       MOVE DEPTCDI TO WS-DEPTCD  
                       PERFORM DEPTCODE-DETAILS  
                    END-IF
                    IF DESGCDI = SPACES OR DESGCDI NOT = WS-DESGCD
                       MOVE DESGCDI TO WS-DESGCD
                       PERFORM DESGCODE-DETAILS  
                    END-IF     
                    PERFORM SEND-MAP-PARA
                 END-IF   
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
              IF EMP-EXIST
                 STRING 'EMP CODE ALREADY EXISTS, ENTER NEW CODE'
                     WS-EMPCD DELIMITED BY SIZE 
                    INTO MSGLINO

                 MOVE 'N' TO WS-INPUT
                 MOVE -1  TO EMPCDL
              ELSE
                 MOVE 'Y' TO WS-INPUT
		         ADD 1    TO WS-ENTRY
                 MOVE -1  TO EMPNML
                 PERFORM DB-DATAMOVE-PARA
                 PERFORM SEND-MAP-PARA
              END-IF
           WHEN 'C'
              IF EMP-NOTFND
                 MOVE 'EMP CODE DOES NOT EXISTS, ENTER NEW CODE' 
                          TO MSGLINO
                 MOVE -1  TO EMPCDL
                 MOVE 'N' TO WS-INPUT
              ELSE
                 MOVE 'Y' TO WS-INPUT
                 ADD 1    TO WS-ENTRY
                 MOVE -1  TO EMPNML
                 PERFORM DB-DATAMOVE-PARA
                 PERFORM SEND-MAP-PARA
              END-IF
           WHEN 'D'
              IF EMP-NOTFND
                 MOVE 'EMP CODE DOES NOT EXISTS, ENTER NEW CODE' 
                          TO MSGLINO
                 MOVE -1  TO EMPCDL
                 MOVE 'N' TO WS-INPUT
              ELSE
                 MOVE 'Y' TO WS-INPUT
                 ADD 1    TO WS-ENTRY
                 MOVE -1  TO EMPNML
                 PERFORM DB-DATAMOVE-PARA
                 PERFORM SEND-MAP-PARA
              END-IF
           END-EVALUATE.

      ***************************************************
       DB-DATAMOVE-PARA.

           MOVE WS-EMPNM    TO EMPNMO
           MOVE WS-DEPTCD   TO DEPTCDO
           PERFORM DEPTCODE-DETAILS
           MOVE WS-DESGCD   TO DESGCDO  
           PERFORM DESGCODE-DETAILS
           MOVE WS-LOC      TO LOCO.

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
       VALIDATE-EMPCD-PARA.

           IF EMPCDI = SPACES 
              MOVE 'EMPLOYEE CODE CAN NOT BE EMPTY'  
                 TO MSGLINO 
              MOVE 'EMPCD' TO WS-PROCESS
              MOVE -1       TO EMPCDL
              MOVE 'N'      TO WS-EMPVAL
              MOVE 'N'      TO WS-INPUT
           ELSE 
              IF WS-EMPCD NOT = EMPCDI
                 MOVE EMPCDI TO WS-EMPCD
                 PERFORM EMPCODE-VALIDATE
              END-IF
           END-IF. 
  
      ***************************************************
       VALIDATE-EMPNM-PARA.
           IF EMPNMI = SPACES AND ACTION NOT = 'D'  
              MOVE 'ENTER EMPLOYEE NAME ' TO MSGLINO
              MOVE 'NAME' TO WS-PROCESS
              MOVE -1       TO EMPNML
              MOVE 'N'    TO WS-INPUT
           END-IF.

      ***************************************************
       INITIALIZE-FIELD-PARA.

           MOVE SPACES TO ACTIONO WS-ACTION
           MOVE SPACES TO EMPCDO  WS-EMPCD
           MOVE SPACES TO EMPNMO  WS-EMPNM
           MOVE SPACES TO DEPTCDO WS-DEPTCD
           MOVE SPACES TO DEPTDSO WS-DEPTDS
           MOVE SPACES TO DESGCDO WS-DESGCD
           MOVE SPACES TO DESGDSO WS-DESGDS
	   MOVE ZEROES TO BASPAYO WS-BASICPAY
           MOVE ZEROES TO HRAPAYO WS-HRAPAY
           MOVE ZEROES TO GRSPAYO WS-GROSSPAY
           MOVE SPACES TO LOCO    WS-LOC 
           MOVE ZEROES TO WS-ENTRY
           MOVE -1 TO ACTIONL
           MOVE 'N' TO WS-INPUT    
           MOVE 'START' TO WS-PROCESS.

      ***************************************************
        DESGCODE-DETAILS.

           EXEC SQL
               SELECT DESGDESC, BASICPAY, HRA, GROSSPAY  
                   INTO :WS-DESGDS, :WS-BASICPAY, 
                        :WS-HRAPAY, :WS-GROSSPAY  
                   FROM DESIGNATION
                   WHERE DESGCODE = :WS-DESGCD
           END-EXEC.  

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL  
                 MOVE 'DESIGNATION CODE FOUND' 
                      TO MSGLINO   
                 MOVE 'F'         TO WS-DESGVAL 
                 MOVE WS-DESGDS   TO DESGDSO 
	         MOVE WS-BASICPAY TO BASPAYO 
                 MOVE WS-HRAPAY   TO HRAPAYO 
                 MOVE WS-GROSSPAY TO GRSPAYO  
              WHEN WS-SQL-RETURN-NO-ENTRY   
                 STRING 'DESIGNATION CODE DOES NOT EXIST' 
                      WS-DESGCD DELIMITED BY SIZE
                      ' - ' DELIMITED BY SIZE
                     DESGCDI DELIMITED BY SIZE
                     INTO MSGLINO                                       
                 MOVE 'N'    TO WS-DESGVAL
                 MOVE SPACES TO DESGDSO WS-DESGDS
	         MOVE ZEROES TO BASPAYO WS-BASICPAY
                 MOVE ZEROES TO HRAPAYO WS-HRAPAY
                 MOVE ZEROES TO GRSPAYO WS-GROSSPAY 
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN SELECT' TO MSGLINO                   
                 MOVE 'ERROR' TO WS-PROCESS
                 MOVE 'E' TO WS-DESGVAL
                 PERFORM INITIALIZE-FIELD-PARA
           END-EVALUATE.  

      ***************************************************
        EMPCODE-VALIDATE.

           EXEC SQL
               SELECT EMPNAME, DEPTCODE, DESGCODE, LOCATION  
                   INTO :WS-EMPNM, :WS-DEPTCD, 
                        :WS-DESGCD, :WS-LOC   
                   FROM EMPLOYEE
                   WHERE EMPID = :WS-EMPCD
           END-EXEC.  

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL  
                 MOVE 'EMPLOYEE CODE FOUND' 
                      TO MSGLINO   
                 MOVE 'F' TO WS-EMPVAL  
              WHEN WS-SQL-RETURN-NO-ENTRY   
                 MOVE 'EMPLOYEE CODE DOES NOT EXIST' 
                      TO MSGLINO                                       
                 MOVE 'N' TO WS-EMPVAL
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN SELECT' TO MSGLINO                   
                 MOVE 'ERROR' TO WS-PROCESS
                 MOVE 'E' TO WS-EMPVAL
                 PERFORM INITIALIZE-FIELD-PARA
           END-EVALUATE.  

      ***************************************************
        DEPTCODE-DETAILS.

           EXEC SQL
               SELECT DEPTDESC   
                   INTO :WS-DEPTDS
                   FROM DEPARTMENT
                   WHERE DEPTCODE = :WS-DEPTCD
           END-EXEC.  

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL  
                 MOVE 'DEPARTMENT CODE FOUND' 
                      TO MSGLINO   
                 MOVE 'F' TO WS-DEPTVAL  
                 MOVE WS-DEPTDS  TO DEPTDSO
              WHEN WS-SQL-RETURN-NO-ENTRY   
                 MOVE 'DEPARTMENT CODE DOES NOT EXIST' 
                      TO MSGLINO                                       
                 MOVE 'N' TO WS-DEPTVAL
                 MOVE SPACES  TO DEPTDSO
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN SELECT' TO MSGLINO                   
                 MOVE 'ERROR' TO WS-PROCESS
                 MOVE 'E' TO WS-DEPTVAL
                 PERFORM INITIALIZE-FIELD-PARA
           END-EVALUATE.  

      ***************************************************
       DELETE-PARA.

           EXEC SQL
               DELETE FROM EMPLOYEE
                   WHERE EMPID = :EMPCDI
           END-EXEC.           

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'EMPLOYEE CODE DELETED' 
                      TO MSGLINO   
                 PERFORM INITIALIZE-FIELD-PARA
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'EMPLOYEE CODE DOES NOT EXIST, TRY AGAIN' 
                      TO MSGLINO                    
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN DELETE' TO MSGLINO  
                 PERFORM INITIALIZE-FIELD-PARA 
           END-EVALUATE.
           PERFORM INITIALIZE-FIELD-PARA.

      ***************************************************
       INSERT-PARA.

           EXEC SQL
               INSERT INTO EMPLOYEE 
               (EMPID, EMPNAME, DEPTCODE, DESGCODE, LOCATION) 
                      VALUES 
               (:EMPCDI, :EMPNMI, :DEPTCDI, :DESGCDI, 
                :LOCI)
           END-EXEC.           

           MOVE SQLCODE TO WS-SQLCODE.    
             
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'EMPLOYEE CODE INSERTED' 
                      TO MSGLINO   
              WHEN WS-SQL-RETURN-DUPKEY                                      
                 MOVE 'EMPLOYEE CODE ALREADY EXIST, TRY AGAIN' 
                      TO MSGLINO              
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN INSERT' TO MSGLINO                  
           END-EVALUATE.

           PERFORM INITIALIZE-FIELD-PARA.

      ***************************************************
       UPDATE-PARA.

           EXEC SQL
               UPDATE EMPLOYEE 
                   SET 
                       EMPNAME  = :EMPNMI,
                       DEPTCODE = :DEPTCDI, 
                       DESGCODE = :DESGCDI, 
                       LOCATION = :LOCI
                   WHERE EMPID = :EMPCDI
           END-EXEC.       
    
           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'EMPLOYEE CODE UPDATED' 
                      TO MSGLINO   
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'EMPLOYEE CODE DOES NOT EXIST, TRY AGAIN' 
                      TO MSGLINO                    
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED IN UPDATE' TO MSGLINO                   
           END-EVALUATE.

           PERFORM INITIALIZE-FIELD-PARA.

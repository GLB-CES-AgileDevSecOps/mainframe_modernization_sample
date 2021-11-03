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
       PROGRAM-ID. DEPTMANT.
       AUTHOR. ATOS SYNTEL.
       ENVIRONMENT DIVISION.
       DATA DIVISION.
      ***************************************************
       WORKING-STORAGE SECTION.

       01  PROGRAM-DESC                PIC X(20) VALUE 'DEPT MAINT'.
 
       01  WS-TEMP.
           05 WS-DEPTCODE              PIC X(05). 
           05 WS-DESC                  PIC X(50).
           05 WS-MSG                   PIC X(50).
           05 WS-MCOMM                 PIC X(10) VALUE SPACES.

       01  WS-SWITCHES.
           05  WS-ACT                      PIC X(1).
               88  VALID-ACTION                     VALUE 'Y'.

           05  WS-INPUT                    PIC X(1). 
               88  VALID-INPUT                      VALUE 'Y'.
               88  INVALID-INPUT                    VALUE 'N'.

           05  WS-DEPTVAL                  PIC X(1).
               88  DEPT-EXIST                       VALUE 'F'.
               88  DEPT-NOTFND                      VALUE 'N'.
               88  DEPT-DBERR                       VALUE 'E'.

       01  WS-COMMAREA. 
           05 WS-DEPTCD                PIC X(05) VALUE SPACES.
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

           COPY DEPTSET.
      ***************************************************
       LINKAGE SECTION.

       01  DFHCOMMAREA.
           05 DEPT-CODE                PIC X(05).
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
      *
           IF EIBAID = DFHPF3
              MOVE 'EXIT FROM DEPARTMENT MAINTENANCE' TO WS-MSG
               EXEC CICS
                    SEND TEXT FROM(WS-MSG)
                    ERASE
                    FREEKB
               END-EXEC
               INITIALIZE DFHCOMMAREA
               INITIALIZE DEPTMNTI
               EXEC CICS RETURN
                    TRANSID('EMPM')
               END-EXEC

           END-IF. 

           IF EIBCALEN = 0 
              MOVE 'START' TO WS-PROCESS
              MOVE SPACES TO WS-MSG
           ELSE
              MOVE DFHCOMMAREA TO WS-COMMAREA
              MOVE 'CONTINUE' TO WS-PROCESS
              PERFORM RECEIVE-MAP-PARA
              PERFORM VALIDATE-ACTION-PARA

              IF VALID-ACTION 
                 PERFORM PROCESS-PARA
              END-IF
           END-IF. 

           PERFORM SEND-MAP-PARA.

      ***************************************************
       SEND-MAP-PARA.
           IF WS-PROCESS = 'START'
              EXEC CICS SEND MAP('DEPTMNT')
                MAPSET('DEPTSET')
                FROM(DEPTMNTO)
                ERASE
                CURSOR
              END-EXEC
           ELSE
              EXEC CICS SEND MAP('DEPTMNT')
                MAPSET('DEPTSET')
                FROM(DEPTMNTO)
                DATAONLY
                CURSOR
              END-EXEC
           END-IF.
           EXEC CICS RETURN TRANSID('DEPT')
               COMMAREA(WS-COMMAREA)
               LENGTH(LENGTH OF WS-COMMAREA)
           END-EXEC.

      ***************************************************        
       RECEIVE-MAP-PARA.
           EXEC CICS RECEIVE MAP('DEPTMNT')
                MAPSET('DEPTSET')
                INTO(DEPTMNTI)
           END-EXEC.    

      ***************************************************
       PROCESS-PARA.

           IF DEPTCDI NOT = WS-DEPTCD
              PERFORM VALIDATE-DEPTCD-PARA
              IF NOT DEPT-DBERR
                 PERFORM ACTION-PROCESS-VAL-PARA
              END-IF 
           END-IF.
           
           IF WS-ENTRY > 0
              IF DEPTDSI = SPACES AND ACTION NOT = 'D'  
                 MOVE 'ENTER DEPARTMENT DESCRIPTION' TO MSGLINO
                 MOVE 'DESC'   TO WS-PROCESS
                 MOVE -1       TO DEPTDSL
                 MOVE 'N'      TO WS-INPUT
                 MOVE ZEROES   TO WS-ENTRY
              ELSE
                 MOVE 'Y'      TO WS-INPUT
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
              IF DEPT-EXIST
                 MOVE 'DEPT CODE ALREADY EXISTS, ENTER NEW CODE' 
                          TO MSGLINO
                 MOVE 'N' TO WS-INPUT
                 MOVE ZEROES TO WS-ENTRY
                 MOVE -1  TO DEPTCDL
              ELSE
                 MOVE 'Y' TO WS-INPUT
                 ADD 1    TO WS-ENTRY
                 PERFORM SEND-MAP-PARA
              END-IF
           WHEN 'C'
              IF DEPT-NOTFND
                 MOVE 'DEPT CODE DOES NOT EXISTS, ENTER NEW CODE' 
                          TO MSGLINO
                 MOVE -1  TO DEPTCDL
                 MOVE 'N' TO WS-INPUT
                 MOVE ZEROES TO WS-ENTRY
              ELSE
                 MOVE 'Y' TO WS-INPUT
                 ADD 1    TO WS-ENTRY
                 PERFORM SEND-MAP-PARA
              END-IF
           WHEN 'D'
              IF DEPT-NOTFND
                 MOVE 'DEPT CODE DOES NOT EXISTS, ENTER NEW CODE' 
                          TO MSGLINO
                 MOVE -1  TO DEPTCDL
                 MOVE 'N' TO WS-INPUT
                 MOVE ZEROES TO WS-ENTRY
              ELSE
                 MOVE 'Y' TO WS-INPUT
                 ADD 1    TO WS-ENTRY
                 PERFORM SEND-MAP-PARA
              END-IF
           END-EVALUATE.

      ***************************************************
       VALIDATE-ACTION-PARA.

           IF ACTIONI NOT = 'A' AND ACTIONI NOT = 'D' AND 
               ACTIONI NOT = 'C'
               MOVE 'VALID ACTION VALUES - A/D/C'  
                 TO MSGLINO 
               MOVE 'ACTION' TO WS-PROCESS
               MOVE 'N' TO WS-ACT
            ELSE 
               IF WS-ACTION NOT = ACTIONI
                  MOVE 'ENTER DEPARTMENT CODE'  
                      TO MSGLINO 
                  MOVE ACTIONI TO WS-ACTION
               END-IF
               MOVE 'Y' TO WS-ACT
           END-IF.

      ***************************************************
       VALIDATE-DEPTCD-PARA.

           IF DEPTCDI = SPACES 
              MOVE 'DEPARTMENT CODE CAN NOT BE EMPTY'  
                 TO MSGLINO 
              MOVE 'DEPTCD' TO WS-PROCESS
              MOVE -1       TO DEPTCDL
              MOVE ZEROES TO WS-ENTRY
              MOVE 'N'      TO WS-DEPTVAL
              MOVE 'N'      TO WS-INPUT
           ELSE 
              IF WS-DEPTCD NOT = DEPTCDI 
                 MOVE DEPTCDI TO WS-DEPTCD
                 PERFORM DEPTCODE-VALIDATE
              END-IF
           END-IF.             

      ***************************************************
        DEPTCODE-VALIDATE.

           EXEC SQL
               SELECT DEPTDESC 
                   INTO :WS-DESC
                   FROM DEPARTMENT
                   WHERE DEPTCODE = :DEPTCDI
           END-EXEC.   
        
           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'DEPARTMENT CODE FOUND, CONTINUE PROCESS' 
                      TO MSGLINO   
                 MOVE 'F' TO WS-DEPTVAL  
                 MOVE WS-DESC TO DEPTDSO
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'DEPARTMENT CODE DOES NOT EXIST, CONTINUE TO ADD' 
                      TO MSGLINO                    
                 MOVE 'N' TO WS-DEPTVAL
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED, TRY AGAIN' TO MSGLINO                   
                 MOVE 'ERROR' TO WS-PROCESS
                 MOVE 'E' TO WS-DEPTVAL
                 MOVE SPACES TO ACTIONO
                 MOVE SPACES TO DEPTCDO
                 MOVE SPACES TO DEPTDSO
                 MOVE 'N' TO WS-INPUT
           END-EVALUATE.  

      ***************************************************
       DELETE-PARA.

           EXEC SQL
               DELETE FROM DEPARTMENT 
                   WHERE DEPTCODE = :DEPTCDI
           END-EXEC.           

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE SPACES TO ACTIONO
                 MOVE SPACES TO DEPTCDO
                 MOVE SPACES TO DEPTDSO
                 MOVE 'DEPARTMENT CODE DELETED' 
                      TO MSGLINO   
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'DEPARTMENT CODE DOES NOT EXIST, TRY AGAIN' 
                      TO MSGLINO                    
           
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED, TRY AGAIN' TO MSGLINO   
                 MOVE SPACES TO ACTIONO
                 MOVE SPACES TO DEPTCDO
                 MOVE SPACES TO DEPTDSO             
           END-EVALUATE.
           MOVE 'N' TO WS-INPUT  
           MOVE ZEROES TO WS-ENTRY
           MOVE -1 TO ACTIONL
           MOVE 'START' TO WS-PROCESS.

      ***************************************************
       INSERT-PARA.

           EXEC SQL
               INSERT INTO DEPARTMENT (DEPTCODE, DEPTDESC) 
                      VALUES (:DEPTCDI, :DEPTDSI)
           END-EXEC.           

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE SPACES TO ACTIONO
                 MOVE SPACES TO DEPTCDO
                 MOVE SPACES TO DEPTDSO
                 MOVE 'DEPARTMENT CODE INSERTED' 
                      TO MSGLINO   
              WHEN WS-SQL-RETURN-DUPKEY                                      
                 MOVE 'DEPARTMENT CODE ALREADY EXIST, TRY AGAIN' 
                      TO MSGLINO                                
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED, TRY AGAIN' TO MSGLINO                  
                 MOVE SPACES TO ACTIONO
                 MOVE SPACES TO DEPTCDO
                 MOVE SPACES TO DEPTDSO            
           END-EVALUATE.
           MOVE 'N' TO WS-INPUT    
           MOVE ZEROES TO WS-ENTRY
           MOVE 'START' TO WS-PROCESS.

      ***************************************************
       UPDATE-PARA.

           EXEC SQL
               UPDATE DEPARTMENT 
                   SET DEPTCODE = :DEPTCDI,
                       DEPTDESC = :DEPTDSI
                   WHERE DEPTCODE = :DEPTCDI
           END-EXEC.       
    
           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE SPACES TO ACTIONO
                 MOVE SPACES TO DEPTCDO
                 MOVE SPACES TO DEPTDSO
                 MOVE 'DEPARTMENT CODE UPDATED' 
                      TO MSGLINO   
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'DEPARTMENT CODE DOES NOT EXIST, TRY AGAIN' 
                      TO MSGLINO                    
             
              WHEN OTHER                                                        
                 MOVE 'DB2 ERROR OCCURED, TRY AGAIN' TO MSGLINO                   
                 MOVE SPACES TO ACTIONO
                 MOVE SPACES TO DEPTCDO
                 MOVE SPACES TO DEPTDSO             
           END-EVALUATE.
           MOVE 'N' TO WS-INPUT 
           MOVE ZEROES TO WS-ENTRY
           MOVE 'START' TO WS-PROCESS.

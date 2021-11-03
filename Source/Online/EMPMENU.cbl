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
       PROGRAM-ID. EMPMENU. 
       AUTHOR. ATOS SYNTEL. 
       ENVIRONMENT DIVISION. 
       DATA DIVISION. 
       WORKING-STORAGE SECTION. 
       01  PROGRAM-DESC      PIC X(20) VALUE 'MENU PROGRAM'. 
       01  WS-TEMP. 
           05 WS-CHOICE      PIC X(10). 
       01  WS-MSG            PIC X(50). 
       01  WS-COMMAREA       PIC X(1034). 
       01  WS-ACT            PIC X(1). 
           88  VALID-INPUT   VALUE 'Y'. 
       01  WS-TEXT           PIC X(40). 
 
       01 ALIAS-NAME                    PIC X(32). 
       01 COMMAND                       PIC X(1). 
       01 CONNECT-STATUS                PIC 9(6). 
       01  RESPONSE-CODE               PIC S9(08) COMP. 
       COPY EMPSETM. 
       COPY DFHAID. 
 
       LINKAGE SECTION. 
       01  DFHCOMMAREA              PIC X(10). 
 
       PROCEDURE DIVISION. 
 
       MAIN-PARA. 
 
           EVALUATE EIBAID        
             WHEN DFHPF3 
               MOVE 'EXIT FROM EMPLOYEE MENU' TO WS-MSG 
               EXEC CICS 
                    SEND TEXT FROM(WS-MSG) 
                    ERASE 
                    FREEKB 
               END-EXEC 
               EXEC CICS 
                    RETURN 
               END-EXEC 
           END-EVALUATE.   
 
           IF EIBCALEN = 0 
              MOVE SPACES TO WS-MSG 
              EXEC CICS 
                   SEND TEXT FROM(WS-MSG) 
                   ERASE 
                   FREEKB 
              END-EXEC 

              MOVE 'START' TO WS-CHOICE 
              PERFORM SEND-MAP-PARA 
           ELSE 
              PERFORM RECEIVE-MAP-PARA 
              PERFORM VALIDATE-PARA 
              IF VALID-INPUT 
                 PERFORM PROCESS-PARA 
              ELSE 
                 PERFORM SEND-MAP-PARA 
              END-IF 
           END-IF. 
 
           EXEC CICS RETURN TRANSID('EMPM') 
                COMMAREA(WS-TEMP) 
                LENGTH(LENGTH OF WS-TEMP) 
           END-EXEC. 
 
       VALIDATE-PARA.
 
           IF ACTIONI > 3
              MOVE 'INVALID CHOICE ENTERED, PLEASE ENTER 0-3 '  
                   TO MSGLINO 
              MOVE 'INVALID' TO WS-CHOICE 
              MOVE 'N' TO WS-ACT 
           ELSE 
              MOVE 'VALID CHOICE ' TO MSGLINO 
              MOVE 'VALID' TO WS-CHOICE 
              MOVE 'Y' TO WS-ACT 
           END-IF.
 
       PROCESS-PARA.
           
           EVALUATE TRUE 
           WHEN ACTIONI = 0 
              MOVE 'EXIT' TO WS-CHOICE 
               MOVE 'EXIT FROM EMPLOYEE MENU' TO WS-MSG 
               EXEC CICS 
                    SEND TEXT FROM(WS-MSG) 
                    ERASE 
                    FREEKB 
               END-EXEC 
               
               EXEC CICS 
                   RETURN 
               END-EXEC 
            
           WHEN ACTIONI = 1 
              MOVE 'DEPT' TO WS-CHOICE 
              MOVE 'DEPARTMENT CRUD OPERATION BEGINS' TO MSGLINO 
 
              EXEC CICS XCTL 
                   PROGRAM('DEPTMANT') 
                      RESP(RESPONSE-CODE) 
              END-EXEC 
              
           WHEN ACTIONI = 2 
              MOVE 'EMPL' TO WS-CHOICE 
              MOVE 'DESIGNATION CRUD OPERATION BEGINS' TO MSGLINO 
 
              EXEC CICS XCTL 
                   PROGRAM('DESGMANT') 
                   RESP(RESPONSE-CODE) 
              END-EXEC 
              
           WHEN ACTIONI = 3 
              MOVE 'EMPL' TO WS-CHOICE 
              MOVE 'EMPLOYEE CRUD OPERATION BEGINS' TO MSGLINO 
 
              EXEC CICS XCTL 
                   PROGRAM('MEMPMANT') 
                   RESP(RESPONSE-CODE) 
              END-EXEC 
          END-EVALUATE. 
 
       SEND-MAP-PARA.
          MOVE -1 TO ACTIONL
          EXEC CICS SEND MAP('EMPMENU') 
               MAPSET('EMPSETM') 
               FROM(EMPMENUO) 
          END-EXEC. 
          
       RECEIVE-MAP-PARA. 
          EXEC CICS RECEIVE MAP('EMPMENU') 
               MAPSET('EMPSETM') 
               INTO(EMPMENUI) 
          END-EXEC.    

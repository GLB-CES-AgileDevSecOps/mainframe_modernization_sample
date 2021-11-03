00005  IDENTIFICATION DIVISION.                                             
00006  PROGRAM-ID.    emplist.                                             
00067  ENVIRONMENT DIVISION.                                                
010200 INPUT-OUTPUT SECTION.                                            
00021  FILE-CONTROL.                                                     
00023      SELECT OUTPUT-FILE  ASSIGN TO OUTFL.               
00025  DATA DIVISION.                                                    
00026  FILE SECTION.                                                   
00027 *                                                                  
00035  FD  OUTPUT-FILE                                                
00036      RECORDING MODE IS F                                           
00037      LABEL RECORDS ARE STANDARD                                    
00038      BLOCK CONTAINS 0 RECORDS                                      
00039      DATA RECORD IS OUTPUT-RECORD.                            
00040  01  OUTPUT-RECORD PIC X(250).                                
00084                                                                       
00085  WORKING-STORAGE SECTION.  
          EXEC SQL BEGIN DECLARE SECTION END-EXEC                        
       01 DATABASE-NAME PIC X(80) VALUE "ltpz_syntel:5432".
       01 USER-NAME     PIC X(80) VALUE "syntel".
       01 PASSWORD      PIC X(80) VALUE "syntel".   
       01 WS-DESGCD     PIC X(05) VALUE SPACES.	   
       01 WS-DEPTCODE   PIC X(05). 
       01 WS-REPO-DEPT  PIC X(05).		   
       01 WS-EMP-ID               PIC X(05).
       01 WS-EMP-NAME             PIC X(30).
       01 WS-EMP-LOC              PIC X(30).
       01 WS-EMP-DEPT             PIC X(05).
       01 WS-EMP-DEPTDESC         PIC X(50).
       01 WS-EMP-DESG             PIC X(05). 
       01 WS-EMP-DESGDESC         PIC X(50).
       01 WS-EMP-BASIC            PIC 9(05).
       01 WS-EMP-HRA              PIC 9(05).
       01 WS-EMP-GROSSPAY         PIC 9(07).	   
          EXEC SQL END DECLARE SECTION END-EXEC	   
       01  END-OF-INPUT-IND               PIC X(1).
            88 END-OF-FILE                VALUE 'Y'.	   
       01  WS-SQLCODE-ANALYSIS.                                                
           05 WS-SQLCODE               PIC S9(9) COMP.                
              88 WS-SQL-RETURN-NORMAL          VALUE +0.                    
              88 WS-SQL-RETURN-NO-ENTRY        VALUE +100.                  
              88 WS-SQL-RETURN-INVALD-DATETIME VALUE -181.                  
              88 WS-SQL-RETURN-DUPKEY          VALUE -803.                  
              88 WS-SQL-RETURN-MULT-RESULT     VALUE -811.                  
              88 WS-SQL-RETURN-TIMEOUT         VALUE -911 -913.             
              88 WS-SQL-TIMEOUT-NO-ROLLBACK    VALUE -913.                  
              88 WS-SQL-RETURN-ERROR           VALUE -910 THRU -1           
                                                     -912                    
                                                     -9999 THRU -914.   			
           COPY SQLCA.
            
       01  WS-TEMP.
      *     05 WS-DEPTCODE                  PIC X(05). 
      *     05 WS-REPO-DEPT                 PIC X(05).		   
      *     05 WS-MSG                       PIC X(50).
      *     05 WS-DESGCD                    PIC X(05) VALUE SPACES.
           05  WS-EMPCURVAL                  PIC X(1).
               88  EMPC-EXIST                       VALUE 'F'.
               88  EMPC-NOTFND                      VALUE 'N'.
               88  EMPC-DBERR                       VALUE 'E'.			  
           05  WS-DESGVAL                  PIC X(1).
               88  DESG-EXIST                       VALUE 'F'.
               88  DESG-NOTFND                      VALUE 'N'.
               88  DESG-DBERR                       VALUE 'E'.	
           05  WS-DEPTVAL                  PIC X(1).
               88  DEPT-EXIST                       VALUE 'F'.
               88  DEPT-NOTFND                      VALUE 'N'.
               88  DEPT-DBERR                       VALUE 'E'.			   
00134                                                                       
00136  01  OUT-FILE-RECORD.                             
00221      10  WS-OUT-EMP-ID               PIC X(05).            
00221      10  FILLER                      PIC X(01) VALUE ','.            
00221      10  WS-OUT-EMP-NAME             PIC X(30).            
00221      10  FILLER                      PIC X(01) VALUE ','.  
00221      10  WS-OUT-EMP-LOC              PIC X(30). 
00221      10  FILLER                      PIC X(01) VALUE ','.  
00248      10  WS-OUT-EMP-DEPT             PIC X(05).                
00221      10  FILLER                      PIC X(01) VALUE ','.       
00248      10  WS-OUT-EMP-DEPTDESC         PIC X(50).               
00221      10  FILLER                      PIC X(01) VALUE ','.        
00248      10  WS-OUT-EMP-DESG             PIC X(05).                
00221      10  FILLER                      PIC X(01) VALUE ','.        
00248      10  WS-OUT-EMP-DESGDESC         PIC X(50).               
00221      10  FILLER                      PIC X(01) VALUE SPACES.         
00250      10  WS-OUT-EMP-BASIC            PIC 9(05).                
00221      10  FILLER                      PIC X(01) VALUE SPACES.       
00250      10  WS-OUT-EMP-HRA              PIC 9(05).                
00221      10  FILLER                      PIC X(01) VALUE SPACES.       
00248      10  WS-OUT-EMP-GROSSPAY         PIC 9(07).   							
00136  01  WS-COUNTERS.
00219      05  EMP-COUNT                   PIC 9(10) VALUE ZEROES.               
00219      05  DESG-COUNT                  PIC 9(10) VALUE ZEROES.                                     
00219      05  DEPT-COUNT                  PIC 9(10) VALUE ZEROES.                                     
00167                                                                       
00224  PROCEDURE DIVISION.                                                  
00260                                                                       
00261  MAINLINE.                                                       
00267      PERFORM INIT-PARA.      

           MOVE 'BNFSC' TO WS-REPO-DEPT        
00112      PERFORM PROCESS-DATA THRU PROCESS-EXIT                
00113          UNTIL EMPC-NOTFND.     
                                     
00112      PERFORM PROGRAM-EXIT.
00262      DISPLAY "END OF EMPLOYEE REPORT".                
00499      
      ***************************************************                     
00500  INIT-PARA.                                                     
00262      DISPLAY "1000-INIT - start".                                                         
00501      PERFORM CONNECT-TO-DATABASE.   
           PERFORM EMPLOYEE-CURSOR-DECLARE.
           PERFORM EMPLOYEE-CURSOR-OPEN.
           OPEN OUTPUT OUTPUT-FILE.
00262      DISPLAY "1000-INIT - END".    
                                                                     
00502  INIT-EXIT.                                                      
00504      EXIT.                                           
00501                
      ***************************************************      
00131  PROCESS-DATA.                                                
00262      DISPLAY "PROCESS-DATA - start".          
           PERFORM EMPLOYEE-CURSOR-FETCH
         
      *     MOVE 'HR010' TO WS-DEPTCODE                    
00268      PERFORM DEPTCODE-RETRIEVAL.
      *     MOVE 'GCMA7' TO WS-DESGCD    
           PERFORM DESGCODE-RETRIEVAL.                            
00148      PERFORM WRITE-OUTFILE.     
                                                         
00149  PROCESS-EXIT.  EXIT.
00148    
      ***************************************************                
       CONNECT-TO-DATABASE.
00262      DISPLAY "CONNECT-TO-DATABASE - START". 	   

           EXEC SQL
              CONNECT    TO :DATABASE-NAME
                       USER :USER-NAME
              IDENTIFIED BY :PASSWORD
           END-EXEC.
00262      DISPLAY "CONNECT-TO-DATABASE - END". 	  
 
      ***************************************************
        EMPLOYEE-CURSOR-DECLARE.
00262      DISPLAY "EMPLOYEE-CURSOR-DECLARE - START". 
           EXEC SQL
              declare cur_employee cursor for 
              select empid, empname, deptcode, desgcode, location 
                  from employee
           END-EXEC.  
      *            where deptcode = :WS-REPO-DEPT;  
           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'F' TO WS-EMPCURVAL  
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'N' TO WS-EMPCURVAL
                 DISPLAY 'NOT FOUND - EMPLOYEE CURSOR DECLARE - '          
                       WS-REPO-DEPT 
              WHEN OTHER                                                         
                 DISPLAY 'DB2 ERROR - EMPLOYEE CURSOR DECLARE - ' 
                       WS-REPO-DEPT                  
                 MOVE 'E' TO WS-EMPCURVAL
           END-EVALUATE.  		   
00262      DISPLAY "EMPLOYEE-CURSOR-DECLARE - END".   

      ***************************************************
        EMPLOYEE-CURSOR-OPEN.
00262      DISPLAY "EMPLOYEE-CURSOR-OPEN - START". 
           EXEC SQL
              open cur_employee;
           END-EXEC.  
        
           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'F' TO WS-EMPCURVAL  
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'N' TO WS-EMPCURVAL
                 DISPLAY 'NOT FOUND - EMPLOYEE CURSOR OPEN - '  
                       WS-REPO-DEPT 
              WHEN OTHER                            
                 DISPLAY 'DB2 ERROR - EMPLOYEE CURSOR OPEN - ' 
                           WS-REPO-DEPT 				 
                 MOVE 'E' TO WS-EMPCURVAL
           END-EVALUATE.     
00262      DISPLAY "EMPLOYEE-CURSOR-OPEN - END".   

      ***************************************************
        EMPLOYEE-CURSOR-FETCH.
00262      DISPLAY "EMPLOYEE-CURSOR-FETCH - START". 
           EXEC SQL
              fetch next cur_employee into
                :WS-EMP-ID ,
                :WS-EMP-NAME,
                :WS-EMP-DEPT,
                :WS-EMP-DESG,
                :WS-EMP-LOC;
           END-EXEC.  
        
           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                            
              WHEN WS-SQL-RETURN-NORMAL     
                 ADD 1 TO EMP-COUNT
                 MOVE 'F' TO WS-EMPCURVAL  
              WHEN WS-SQL-RETURN-NO-ENTRY                      
                 MOVE 'N' TO WS-EMPCURVAL
                 DISPLAY 'NOT FOUND - EMPLOYEE CURSOR FETCH - '  
                     WS-REPO-DEPT 
              WHEN OTHER                                      
                 DISPLAY 'DB2 ERROR - EMPLOYEE CURSOR FETCH - ' 
                           WS-REPO-DEPT  
                 MOVE 'E' TO WS-EMPCURVAL
           END-EVALUATE.     
00262      DISPLAY "EMPLOYEE-CURSOR-FETCH - END".   
  
      ***************************************************
        DEPTCODE-RETRIEVAL.
00262      DISPLAY "DEPTCODE-RETRIEVAL - START". 
           EXEC SQL
               SELECT DEPTDESC 
                   INTO :WS-EMP-DEPTDESC
                   FROM DEPARTMENT
                   WHERE DEPTCODE = :WS-EMP-DEPT
           END-EXEC.   
        
           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL     
                 MOVE 'F' TO WS-DEPTVAL  
                 ADD 1 TO DEPT-COUNT
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'N' TO WS-DEPTVAL
                 DISPLAY 'NOT FOUND - DEPARTMENT SELECT - ' WS-DEPTCODE 
              WHEN OTHER                                                        
                 DISPLAY 'DB2 ERROR - DEPARTMENT SELECT - ' WS-DEPTCODE   
                 MOVE 'E' TO WS-DEPTVAL
           END-EVALUATE.     
00262      DISPLAY "DEPTCODE-RETRIEVAL - END".   

      ***************************************************
        DESGCODE-RETRIEVAL.
00262      DISPLAY "DESGCODE-RETRIEVAL - START".   
           EXEC SQL
               SELECT DESGDESC, BASICPAY, HRA, GROSSPAY  
                   INTO :WS-EMP-DESGDESC, :WS-EMP-BASIC, 
                        :WS-EMP-HRA, :WS-EMP-GROSSPAY  
                   FROM DESIGNATION
                   WHERE DESGCODE = :WS-EMP-DESG
           END-EXEC.  

           MOVE SQLCODE TO WS-SQLCODE.                 
           EVALUATE TRUE                                                        
              WHEN WS-SQL-RETURN-NORMAL  
                 MOVE 'F' TO WS-DESGVAL  
                 ADD 1 TO DESG-COUNT
              WHEN WS-SQL-RETURN-NO-ENTRY                                      
                 MOVE 'N' TO WS-DESGVAL
                 DISPLAY 'NOT FOUND - DESIGNATION SELECT - ' WS-DESGCD    
              WHEN OTHER                                                        
                 DISPLAY 'DB2 ERROR - DESIGNATION SELECT - ' WS-DESGCD        
                 MOVE 'E' TO WS-DESGVAL
           END-EVALUATE.     
00262      DISPLAY "DESGCODE-RETRIEVAL - end".    
00148                                                                    
      ***************************************************
        WRITE-OUTFILE.
00262      DISPLAY "WRITE-OUTFILE - START".   
           MOVE WS-EMP-ID          TO WS-OUT-EMP-ID      
           MOVE WS-EMP-NAME        TO WS-OUT-EMP-NAME    
           MOVE WS-EMP-LOC         TO WS-OUT-EMP-LOC     
           MOVE WS-EMP-DEPT        TO WS-OUT-EMP-DEPT    
           MOVE WS-EMP-DEPTDESC    TO WS-OUT-EMP-DEPTDESC
           MOVE WS-EMP-DESG        TO WS-OUT-EMP-DESG    
           MOVE WS-EMP-DESGDESC    TO WS-OUT-EMP-DESGDESC
           MOVE WS-EMP-BASIC       TO WS-OUT-EMP-BASIC   
           MOVE WS-EMP-HRA         TO WS-OUT-EMP-HRA     
           MOVE WS-EMP-GROSSPAY    TO WS-OUT-EMP-GROSSPAY.
		   
           DISPLAY 'WS-EMP-ID       ' WS-OUT-EMP-ID      
           DISPLAY 'WS-EMP-NAME     ' WS-OUT-EMP-NAME    
           DISPLAY 'WS-EMP-LOC      ' WS-OUT-EMP-LOC     
           DISPLAY 'WS-EMP-DEPT     ' WS-OUT-EMP-DEPT    
           DISPLAY 'WS-EMP-DEPTDESC ' WS-OUT-EMP-DEPTDESC
           DISPLAY 'WS-EMP-DESG     ' WS-OUT-EMP-DESG    
           DISPLAY 'WS-EMP-DESGDESC ' WS-OUT-EMP-DESGDESC
           DISPLAY 'WS-EMP-BASIC    ' WS-OUT-EMP-BASIC   
           DISPLAY 'WS-EMP-HRA      ' WS-OUT-EMP-HRA     
           DISPLAY 'WS-EMP-GROSSPAY ' WS-OUT-EMP-GROSSPAY.		
		   
           WRITE OUTPUT-RECORD FROM OUT-FILE-RECORD.
               
00262      DISPLAY "WRITE-OUTFILE - END".    

      ***************************************************                
00472  PROGRAM-EXIT.                                                   
00262      DISPLAY "9999-PROGRAM-EXIT - start".                      
00268                                                              
00115      CLOSE OUTPUT-FILE.                                          
           EXEC SQL
                disconnect all;
           END-EXEC
00116                                                 
00146      DISPLAY 'DESIGNATION COUNT '  DESG-COUNT.              
00146      DISPLAY 'DEPARTMENT  COUNT '  DEPT-COUNT.              
00146      DISPLAY 'EMPLOYEE    COUNT '  EMP-COUNT.              
00262      DISPLAY "9999-PROGRAM-EXIT - end".                              
00473                                                        
00475      STOP RUN.                                      
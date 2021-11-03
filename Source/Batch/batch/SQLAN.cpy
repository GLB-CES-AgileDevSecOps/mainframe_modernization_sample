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

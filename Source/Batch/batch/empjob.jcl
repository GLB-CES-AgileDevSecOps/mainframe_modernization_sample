//TESTPG JOB (TESTPG),'TEST PROG EXEC',CLASS=A,MSGCLASS=A,
//           MSGLEVEL=(2,2),NOTIFY=USERID
//*
//STEPID01 EXEC PGM=emplist
//OUTFL    DD DSN=OUTPUT.DATA,
//            DISP=(NEW,CATLG,DELETE),
//            DCB=(RECFM=FB,RECL=300,BLKSIZE=0)
//SYSPRINT DD SYSOUT=*
//SYSOUT   DD SYSOUT=*
//SYSUDUMP DD SYSOUT=* 
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
        01 DEPTMNTI.
          02 TITLEL              PIC S9(4) COMP.
          02 TITLEF              PIC X.
          02 FILLER REDEFINES TITLEF.
            05 TITLEA              PIC X.
          02 FILLER           PIC X(2).
          02 TITLEI              PIC X(30).
          02 ACTIONL              PIC S9(4) COMP.
          02 ACTIONF              PIC X.
          02 FILLER REDEFINES ACTIONF.
            05 ACTIONA              PIC X.
          02 FILLER           PIC X(2).
          02 ACTIONI              PIC X(1).
          02 DEPTCDL              PIC S9(4) COMP.
          02 DEPTCDF              PIC X.
          02 FILLER REDEFINES DEPTCDF.
            05 DEPTCDA              PIC X.
          02 FILLER           PIC X(2).
          02 DEPTCDI              PIC X(5).
          02 DEPTDSL              PIC S9(4) COMP.
          02 DEPTDSF              PIC X.
          02 FILLER REDEFINES DEPTDSF.
            05 DEPTDSA              PIC X.
          02 FILLER           PIC X(2).
          02 DEPTDSI              PIC X(50).
          02 MSGLINL              PIC S9(4) COMP.
          02 MSGLINF              PIC X.
          02 FILLER REDEFINES MSGLINF.
            05 MSGLINA              PIC X.
          02 FILLER           PIC X(2).
          02 MSGLINI              PIC X(50).

        01 DEPTMNTO REDEFINES DEPTMNTI.
          02 FILLER                PIC X(3).
          02 TITLEC              PIC X.
          02 TITLEH              PIC X.
          02 TITLEO              PIC X(30).
          02 FILLER                PIC X(3).
          02 ACTIONC              PIC X.
          02 ACTIONH              PIC X.
          02 ACTIONO              PIC X(1).
          02 FILLER                PIC X(3).
          02 DEPTCDC              PIC X.
          02 DEPTCDH              PIC X.
          02 DEPTCDO              PIC X(5).
          02 FILLER                PIC X(3).
          02 DEPTDSC              PIC X.
          02 DEPTDSH              PIC X.
          02 DEPTDSO              PIC X(50).
          02 FILLER                PIC X(3).
          02 MSGLINC              PIC X.
          02 MSGLINH              PIC X.
          02 MSGLINO              PIC X(50).

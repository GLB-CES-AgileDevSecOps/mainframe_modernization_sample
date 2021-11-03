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
        01 DESGMNTI.
          02 TITLEL              PIC S9(4) COMP.
          02 TITLEF              PIC X.
          02 FILLER REDEFINES TITLEF.
            05 TITLEA              PIC X.
          02 FILLER           PIC X(2).
          02 TITLEI              PIC X(32).
          02 ACTIONL              PIC S9(4) COMP.
          02 ACTIONF              PIC X.
          02 FILLER REDEFINES ACTIONF.
            05 ACTIONA              PIC X.
          02 FILLER           PIC X(2).
          02 ACTIONI              PIC X(1).
          02 DESGCDL              PIC S9(4) COMP.
          02 DESGCDF              PIC X.
          02 FILLER REDEFINES DESGCDF.
            05 DESGCDA              PIC X.
          02 FILLER           PIC X(2).
          02 DESGCDI              PIC X(5).
          02 DESGDSL              PIC S9(4) COMP.
          02 DESGDSF              PIC X.
          02 FILLER REDEFINES DESGDSF.
            05 DESGDSA              PIC X.
          02 FILLER           PIC X(2).
          02 DESGDSI              PIC X(50).
          02 BASPAYL              PIC S9(4) COMP.
          02 BASPAYF              PIC X.
          02 FILLER REDEFINES BASPAYF.
            05 BASPAYA              PIC X.
          02 FILLER           PIC X(2).
          02 BASPAYI              PIC 9(5).
          02 HRAPAYL              PIC S9(4) COMP.
          02 HRAPAYF              PIC X.
          02 FILLER REDEFINES HRAPAYF.
            05 HRAPAYA              PIC X.
          02 FILLER           PIC X(2).
          02 HRAPAYI              PIC 9(5).
          02 GRSPAYL              PIC S9(4) COMP.
          02 GRSPAYF              PIC X.
          02 FILLER REDEFINES GRSPAYF.
            05 GRSPAYA              PIC X.
          02 FILLER           PIC X(2).
          02 GRSPAYI              PIC 9(7).
          02 SYSOUTL              PIC S9(4) COMP.
          02 SYSOUTF              PIC X.
          02 FILLER REDEFINES SYSOUTF.
            05 SYSOUTA              PIC X.
          02 FILLER           PIC X(2).
          02 SYSOUTI              PIC X(60).
          02 MSGLINL              PIC S9(4) COMP.
          02 MSGLINF              PIC X.
          02 FILLER REDEFINES MSGLINF.
            05 MSGLINA              PIC X.
          02 FILLER           PIC X(2).
          02 MSGLINI              PIC X(50).

        01 DESGMNTO REDEFINES DESGMNTI.
          02 FILLER                PIC X(3).
          02 TITLEC              PIC X.
          02 TITLEH              PIC X.
          02 TITLEO              PIC X(32).
          02 FILLER                PIC X(3).
          02 ACTIONC              PIC X.
          02 ACTIONH              PIC X.
          02 ACTIONO              PIC X(1).
          02 FILLER                PIC X(3).
          02 DESGCDC              PIC X.
          02 DESGCDH              PIC X.
          02 DESGCDO              PIC X(5).
          02 FILLER                PIC X(3).
          02 DESGDSC              PIC X.
          02 DESGDSH              PIC X.
          02 DESGDSO              PIC X(50).
          02 FILLER                PIC X(3).
          02 BASPAYC              PIC X.
          02 BASPAYH              PIC X.
          02 BASPAYO              PIC 9(5).
          02 FILLER                PIC X(3).
          02 HRAPAYC              PIC X.
          02 HRAPAYH              PIC X.
          02 HRAPAYO              PIC 9(5).
          02 FILLER                PIC X(3).
          02 GRSPAYC              PIC X.
          02 GRSPAYH              PIC X.
          02 GRSPAYO              PIC 9(7).
          02 FILLER                PIC X(3).
          02 SYSOUTC              PIC X.
          02 SYSOUTH              PIC X.
          02 SYSOUTO              PIC X(60).
          02 FILLER                PIC X(3).
          02 MSGLINC              PIC X.
          02 MSGLINH              PIC X.
          02 MSGLINO              PIC X(50).

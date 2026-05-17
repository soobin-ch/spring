package com.soobin.board_back.common;

public interface ResponseCode {
 
    
    String SUCCESS = "SU";

    String VALIDATION_FAILED = "VF";

    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_NICKNAME  = "DN";
    String DUPLICATE_TEL_NUMBER  = "DT";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";

    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";


    String NO_PERMISSION = "NB";
    String DATABASE_ERROR = "DBE";
    
}

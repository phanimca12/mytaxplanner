package com.trs.constant;

public class MyTaxReturnConstants
{
  // Configuration Constants

  public static final String HIBERNATE_CONFIG_FILE         = "hibernate.cfg.xml";

  // SQL QUERIES
  public static final String ATTACHMENT_SQL                = "select * from AttachmentTable where userID= :userID ";

  public static final String GETUSERID_SQL                 = "select * from UserTable  where emailID= :email ";
  public static final String PARAMETER_USEREMAILID         = "email";

  public static final String FILINGREQUEST_SQLDELETE_REQID = "delete from ReturnFilingRequestTable where requestID= :requestID ";
  public static final String PARAMETER_DELETEREQUESTID     = "requestID";

  public static final String FILINGREQUEST_SQL             = "select * from ReturnFilingRequestTable where userID= :userID ";
  public static final String PARAMETER_REQUESTID           = "userID";
  public static final String PARAMETER_AGENTCODE           = "agentCode";

  public static final String AUTHORIZEDUSER_SQL            = "select * from UserTable where emailID= :email and password= :password";
  public static final String PARAMETER_EMAILID             = "email";
  public static final String PARAMETER_PASSWORD            = "password";

  public static final String USEREXISTS_SQL                = "select * from UserTable where emailID= :email";
  public static final String PARAMETER_EMAIL               = "email";
  public static final String PARAMETER_MOBILE              = "mobile";

  // Application Constants
  public static final String AUTHORIZATION_HEADER          = "Authorization";
  public static final String ACCEPT_HEADER                 = "accept";
  public static final String JWT_HEADER                    = "jwt_token";
  public static final String APPLICATION_PDF               = "application/pdf";
  public static final String APPLICATION_DOCX              = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

  // Error status line constants
  public static final String PERMISSION_DENIED             = "Unauthorized";
  public static final String NOT_FOUND                     = "Not Found";
  public static final String INTERNAL_SERVER_ERROR         = "Internal Server Error";
  public static final String BAD_REQUEST                   = "Bad Request";

  // Error Code Constants
  public static final String VAR_MISSING                   = "VAR_MISSING";
  public static final String CONTENT_MISSING               = "CONTENT_MISSING";
  public static final String ENCLOSURE_MISSING             = "ENCLOSURE_MISSING";
  public static final String IMAGE_MISSING                 = "IMAGE_MISSING";
  public static final String TABLE_MISSING                 = "TABLE_MISSING";
  public static final String INCLUDE_DOCUMENT_MISSING      = "INCLUDE_DOCUMENT_MISSING";
  public static final String INTERNAL_ERROR_MSG            = "INTERNAL_ERROR_MSG";
  public static final String NOT_FOUND_MSG                 = "NOT_FOUND_MSG";
  public static final String BAD_REQUEST_MSG               = "BAD_REQUEST_MSG";
  public static final String UNAUTHORIZED_MSG              = "UNAUTHORIZED_MSG";
  public static final String RENDERING_ERROR_MSG           = "RENDERING_ERROR_MSG";
  public static final String RESPONSE_GENERATION_ERROR_MSG = "RES_GENERATION_ERROR_MSG";
  public static final String PDF_DOWNLOAD_ERROR_MSG        = "PDF_DOWNLOAD_ERROR_MSG";
  public static final String HTML_DOWNLOAD_ERROR_MSG       = "HTML_DOWNLOAD_ERROR_MSG";
  public static final String DOCX_DOWNLOAD_ERROR_MSG       = "DOCX_DOWNLOAD_ERROR_MSG";
}

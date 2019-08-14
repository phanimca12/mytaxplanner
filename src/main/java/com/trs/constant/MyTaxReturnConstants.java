package com.trs.constant;

public class MyTaxReturnConstants
{
  // Configuration Constants

  public static final String HIBERNATE_CONFIG_FILE         = "hibernate.cfg.xml";

  // SQL QUERIES

  public static final String PARAMETER_STATUS              = "status";
  public static final String PARAMETER_COMMENT             = "agentComments";
  public static final String PARAMETER_REQUESTID           = "requestID";
  public static final String PARAMETER_UPDATEYEAR          = "year";
  public static final String PARAMETER_YEAR                = "year";
  public static final String PARAMETER_UID                 = "uid";
  public static final String PARAMETER_USEREMAILID         = "email";
  public static final String PARAMETER_iID                 = "id";
  public static final String PARAMETER_USERID              = "userID";
  public static final String PARAMETER_AGENTCODE           = "agentCode";
  public static final String PARAMETER_AGENTEMAILID        = "email";
  public static final String PARAMETER_AGENTPASSWORD       = "password";
  public static final String PARAMETER_EMAILID             = "email";
  public static final String PARAMETER_PASSWORD            = "password";
  public static final String PARAMETER_EMAIL               = "email";
  public static final String PARAMETER_MOBILE              = "mobile";
  public static final String PARAMETER_AEMAIL              = "email";

  public static final String FILING_USERID                 = "select * from ReturnFilingRequestTable where requestID= :requestID ";

  public static final String MODIFYITRAGENT_SQL            = "update ReturnFilingRequestTable set status=:status , agentComments= :agentComments where requestID= :requestID ";

  public static final String MODIFYITR_SQL                 = "update ReturnFilingRequestTable set filingYear=:year where requestID= :requestID ";

  public static final String ASSESMENTYEAR_SQL             = "select * from ReturnFilingRequestTable where filingYear= :year and userID=:uid";

  public static final String ATTACHMENT_SQL                = "select * from AttachmentTable where userID= :userID ";
  public static final String ATTACHMENT_REQUESTID_SQL      = "select * from AttachmentTable where requestID= :requestID";

  public static final String GETAGENTID_SQL                = "select * from AgentDetailsTable  where emailID= :email ";

  public static final String GETAGENT_EMAILID              = "select * from AgentDetailsTable  where agentCode= :agentCode ";

  public static final String GETUSERID_SQL                 = "select * from UserTable  where emailID= :email ";

  public static final String GETEMAILID_SQL                = "select * from UserTable  where id= :id ";

  public static final String ATTACHMENT_SQLDELETE_REQID    = "delete from AttachmentTable where requestID= :requestID ";
  public static final String FILINGREQUEST_SQLDELETE_REQID = "delete from ReturnFilingRequestTable where requestID= :requestID ";

  public static final String FILINGREQUEST_AGENTSTATUSSQL  = "select * from ReturnFilingRequestTable where agentCode= :agentCode  and status=:status";
  public static final String FILINGREQUEST_AGENTSQL        = "select * from ReturnFilingRequestTable where agentCode= :agentCode ";
  public static final String FILINGREQUEST_SQL             = "select * from ReturnFilingRequestTable where userID= :userID ";

  public static final String AUTHORIZEDAGENT_SQL           = "select * from AgentDetailsTable where emailID= :email and password= :password";

  public static final String AUTHORIZEDUSER_SQL            = "select * from UserTable where emailID= :email and password= :password";

  public static final String USEREXISTS_SQL                = "select * from UserTable where emailID= :email";

  public static final String AGENTEXISTS_SQL               = "select * from AgentDetailsTable where emailID= :email";

  public static final String USERDETAILS_SQL               = "select * from UserTable where id = :userID";

  public static final String AGENTDETAILS_SQL              = "select  * from AgentDetailsTable  where agentCode= :agentCode";

  public static final String AGENTREQUEST_SQL              = "select a.agentCode,a.name,a.mobile,a.emailID,a.city from AgentDetailsTable a ,ReturnFilingRequestTable b where a.agentCode=b.agentCode and b.requestID=:requestID";

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

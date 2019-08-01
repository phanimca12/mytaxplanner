package com.trs.dao;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.query.Query;

import com.trs.constant.MyTaxReturnConstants;
import com.trs.logger.FileLogger;
import com.trs.model.AttachmentDetails;
import com.trs.model.ResponseModel;
import com.trs.util.HibernateSessionCnf;

public class AttachmentDetailsImpl implements IAttachmentDetails
{
  static String classname = AttachmentDetailsImpl.class.getName();
  Logger        m_logger  = FileLogger.getInstance();

  public ResponseModel createNewAttachment( final AttachmentDetails attachmentDetails ) throws Exception
  {

    final ResponseModel md = new ResponseModel();

    return md;
  }

  public List<AttachmentDetails> getAllRequest( final int userID )
  {
    Query query = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.ATTACHMENT_SQL )
                                 .addEntity( AttachmentDetails.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_USERID, userID );
    }
    catch ( final Exception e )
    {

      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new IOException( "Internal server error" ) );
    }
    return query.list();
  }

  public List<AttachmentDetails> getAllAttachmentByReqID( final long REQID )
  {

    Query query = null;
    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.ATTACHMENT_REQUESTID_SQL )
                                 .addEntity( AttachmentDetails.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, REQID );
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    return query.list();
  }
}

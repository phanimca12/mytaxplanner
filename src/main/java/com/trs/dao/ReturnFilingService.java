package com.trs.dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.trs.config.DBConfig;
import com.trs.constant.MyTaxReturnConstants;
import com.trs.logger.FileLogger;
import com.trs.model.ResponseModel;
import com.trs.model.ReturnFiling;
import com.trs.util.HibernateSessionCnf;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class ReturnFilingService implements IReturnFilingRequest
{
  DBConfig      dbConfig  = new DBConfig();
  Logger        m_logger  = FileLogger.getInstance();
  static String classname = ReturnFilingService.class.getName();

  public ResponseModel createNewReturnRequest( final ReturnFiling returnFiling )
  {
    ResponseModel model = null;
    try
    {
      final IUtility util = new Utility();
      final ReturnFiling returnFilingRequest = new ReturnFiling();
      returnFilingRequest.setRequestID( returnFiling.getUserID() + util.getRandomNumber() );
      returnFilingRequest.setUserID( returnFiling.getUserID() );
      returnFilingRequest.setAgentCode( returnFiling.getAgentCode() );
      returnFilingRequest.setReq_Date( util.getCurrentDateTime() );
      returnFilingRequest.setFilingYear( returnFiling.getFilingYear() );
      returnFilingRequest.setStatus( returnFiling.getStatus() );
      returnFilingRequest.setAgentComments( returnFiling.getAgentComments() );

      final Transaction t = HibernateSessionCnf.getSession().beginTransaction();
      HibernateSessionCnf.getSession().save( returnFilingRequest );
      t.commit();

      model = new ResponseModel();
      // model.setReturnFilingRequest( returnFilingRequest );

      model.setSuccessMessage( "Request Created Successfully" );
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, ReturnFilingService.class.getName() + "\t" + e.getMessage(),
                    new Exception( "Internal server error" ) );

    }
    return model;
  }

  public List<ReturnFiling> getAllRequest( final int userID )
  {
    Query query = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_SQL )
                                 .addEntity( ReturnFiling.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_USERID, userID );
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, ReturnFilingService.class.getName() + "\t" + e.getMessage(),
                    new Exception( "Internal server error" ) );

    }
    return query.list();
  }

  @Transactional
  public boolean deleteRequest( final long reqID )
  {

    final Transaction tx = HibernateSessionCnf.getSession().beginTransaction();
    final Query query = HibernateSessionCnf.getSession()
                                           .createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_SQLDELETE_REQID )
                                           .addEntity( ReturnFiling.class )
                                           .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, reqID );

    final Query query2 = HibernateSessionCnf.getSession()
                                            .createSQLQuery( MyTaxReturnConstants.ATTACHMENT_SQLDELETE_REQID )
                                            .addEntity( ReturnFiling.class )
                                            .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, reqID );
    query2.executeUpdate();
    final int result = query.executeUpdate();
    tx.commit();
    if ( result > 0 )
    {
      return true;
    }
    else
    {
      return false;

    }

  }

  public boolean isAssementYearExist( final String year, final int UserID )
  {
    Query query = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.ASSESMENTYEAR_SQL )
                                 .addEntity( ReturnFiling.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_YEAR, year )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_UID, UserID );
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, ReturnFilingService.class.getName() + "\t" + e.getMessage(),
                    new Exception( "Internal server error" ) );

    }
    return query.getResultList().size() > 0 ? true : false;
  }

  public boolean modifyITR( final String year, final long reqID )
  {

    final Transaction tx = HibernateSessionCnf.getSession().beginTransaction();
    final Query query = HibernateSessionCnf.getSession()
                                           .createSQLQuery( MyTaxReturnConstants.MODIFYITR_SQL )
                                           .addEntity( ReturnFiling.class )
                                           .setParameter( MyTaxReturnConstants.PARAMETER_UPDATEYEAR, year )
                                           .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, reqID );

    final int result = query.executeUpdate();
    tx.commit();
    if ( result > 0 )
    {
      return true;
    }
    else
    {
      return false;

    }
  }

  public boolean modifyAgentITR( final String status, final String comments, final long ReqID )
  {

    final Transaction tx = HibernateSessionCnf.getSession().beginTransaction();
    final Query query = HibernateSessionCnf.getSession()
                                           .createSQLQuery( MyTaxReturnConstants.MODIFYITRAGENT_SQL )
                                           .addEntity( ReturnFiling.class )
                                           .setParameter( MyTaxReturnConstants.PARAMETER_STATUS, status )
                                           .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, ReqID )
                                           .setParameter( MyTaxReturnConstants.PARAMETER_COMMENT, comments );

    final int result = query.executeUpdate();
    tx.commit();
    if ( result > 0 )
    {
      return true;
    }
    else
    {
      return false;

    }
  }

  public long getReqUserID( final long RequestID )
  {
    Query query = null;
    long UID = 0;
    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.FILING_USERID )
                                 .addEntity( ReturnFiling.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, RequestID );
      final List list = query.list();

      final Iterator it = list.iterator();

      while ( it.hasNext() )
      {
        final Object object = it.next();
        final ReturnFiling user = (ReturnFiling)object;
        UID = user.getUserID();
      }
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, ReturnFilingService.class.getName() + "\t" + e.getMessage(),
                    new Exception( "Internal server error" ) );

    }
    return UID;
  }

  public List<ReturnFiling> getAgentProcessingRequest( final String agentCode, final String status )
  {

    Query query = null;

    if ( status.equalsIgnoreCase( "All" ) )
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_AGENTSQL )
                                 .addEntity( ReturnFiling.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_AGENTCODE, agentCode );

      return query.list();
    }
    else
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_AGENTSTATUSSQL )
                                 .addEntity( ReturnFiling.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_AGENTCODE, agentCode )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_STATUS, status );

      return query.list();

    }
  }

}

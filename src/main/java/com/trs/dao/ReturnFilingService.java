package com.trs.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.trs.config.DBConfig;
import com.trs.constant.MyTaxReturnConstants;
import com.trs.model.ResponseModel;
import com.trs.model.ReturnFiling;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class ReturnFilingService implements IReturnFilingRequest
{
  DBConfig dbConfig = new DBConfig();

  public ResponseModel createNewReturnRequest( final ReturnFiling returnFiling ) throws Exception
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

    final Session session = util.getHibernateSessionObj();
    final Transaction t = session.beginTransaction();
    session.save( returnFilingRequest );
    t.commit();

    final ResponseModel model = new ResponseModel();
    // model.setReturnFilingRequest( returnFilingRequest );

    model.setSuccessMessage( "Request Created Successfully" );
    return model;
  }

  public List<ReturnFiling> getAllRequest( final int userID )
  {
    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Query query = session.createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_SQL )
                               .addEntity( ReturnFiling.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, userID );

    return query.list();
  }

  @Transactional
  public boolean deleteRequest( final long reqID )
  {
    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Transaction tx = session.beginTransaction();
    final Query query = session.createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_SQLDELETE_REQID )
                               .addEntity( ReturnFiling.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_DELETEREQUESTID, reqID );

    final Query query2 = session.createSQLQuery( MyTaxReturnConstants.ATTACHMENT_SQLDELETE_REQID )
                                .addEntity( ReturnFiling.class )
                                .setParameter( MyTaxReturnConstants.PARAMETER_DELETEREQUESTID, reqID );
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
    final Session session = dbConfig.getSessionFactory().openSession();

    final Query query = session.createSQLQuery( MyTaxReturnConstants.ASSESMENTYEAR_SQL )
                               .addEntity( ReturnFiling.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_YEAR, year )
                               .setParameter( MyTaxReturnConstants.PARAMETER_UID, UserID );

    return query.getResultList().size() > 0 ? true : false;
  }

  public boolean modifyITR( final String year, final long reqID )
  {
    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Transaction tx = session.beginTransaction();
    final Query query = session.createSQLQuery( MyTaxReturnConstants.MODIFYITR_SQL )
                               .addEntity( ReturnFiling.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_UPDATEYEAR, year )
                               .setParameter( MyTaxReturnConstants.PARAMETER_FORREQID, reqID );

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
    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Transaction tx = session.beginTransaction();
    final Query query = session.createSQLQuery( MyTaxReturnConstants.MODIFYITRAGENT_SQL )
                               .addEntity( ReturnFiling.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_STATUS, status )
                               .setParameter( MyTaxReturnConstants.PARAMETER_FORREQID, ReqID )
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
    final Session session = dbConfig.getSessionFactory().openSession();

    final Query query = session.createSQLQuery( MyTaxReturnConstants.FILING_USERID )
                               .addEntity( ReturnFiling.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_FILINGREQUESTID, RequestID );
    final List list = query.list();
    long UID = 0;
    final Iterator it = list.iterator();

    while ( it.hasNext() )
    {
      final Object object = it.next();
      final ReturnFiling user = (ReturnFiling)object;
      UID = user.getUserID();
    }
    session.close();
    return UID;
  }

  public List<ReturnFiling> getAgentProcessingRequest( final String agentCode, final String status )
  {
    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    Query query = null;

    if ( status.equalsIgnoreCase( "all" ) )
    {
      query = session.createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_AGENTSQL )
                     .addEntity( ReturnFiling.class )
                     .setParameter( MyTaxReturnConstants.PARAMETER_AGENTCODE, agentCode );

      return query.list();
    }
    else
    {
      query = session.createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_AGENTSTATUSSQL )
                     .addEntity( ReturnFiling.class )
                     .setParameter( MyTaxReturnConstants.PARAMETER_AGENTCODE, agentCode )
                     .setParameter( MyTaxReturnConstants.PARAMETER_REQSTATUS, status );

      return query.list();

    }
  }

}

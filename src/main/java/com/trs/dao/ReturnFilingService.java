package com.trs.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.trs.constant.MyTaxReturnConstants;
import com.trs.model.ResponseModel;
import com.trs.model.ReturnFiling;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class ReturnFilingService implements IReturnFilingRequest
{

  @Override
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

  @Override
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
  public boolean deleteRequest( final String reqID )
  {
    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Transaction tx = session.beginTransaction();
    final Query query = session.createSQLQuery( MyTaxReturnConstants.FILINGREQUEST_SQLDELETE_REQID )
                               .addEntity( ReturnFiling.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_DELETEREQUESTID, reqID );

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

}

package com.trs.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trs.model.ResponseModel;
import com.trs.model.ReturnFilingRequest;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class ReturnFilingService implements IReturnFilingRequest
{

  public ResponseModel createNewReturnRequest( final ReturnFilingRequest returnFiling ) throws Exception
  {
    final IUtility util = new Utility();
    final ReturnFilingRequest returnFilingRequest = new ReturnFilingRequest();
    returnFilingRequest.setUser_ID( returnFiling.getUser_ID() );
    returnFilingRequest.setAgent_Code( returnFiling.getAgent_Code() );
    returnFilingRequest.setReq_Date( util.getCurrentDateTime() );
    returnFilingRequest.setFiling_Year( returnFiling.getFiling_Year() );
    returnFilingRequest.setStatus( returnFiling.getStatus() );
    returnFilingRequest.setAgent_Comments( returnFiling.getAgent_Comments() );

    final Session session = util.getHibernateSessionObj();
    final Transaction t = session.beginTransaction();
    session.save( returnFilingRequest );
    t.commit();

    final ResponseModel model = new ResponseModel();
    model.setReturnFilingRequest( returnFilingRequest );
    model.setSuccessMessage( "Request Created Successfully" );
    return model;
  }

}

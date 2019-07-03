package com.trs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.trs.constant.MyTaxReturnConstants;
import com.trs.model.AttachmentDetails;
import com.trs.model.ResponseModel;
import com.trs.util.Utility;

public class AttachmentDetailsImpl implements IAttachmentDetails
{

  @Override
  public ResponseModel createNewAttachment( final AttachmentDetails attachmentDetails ) throws Exception
  {

    final ResponseModel md = new ResponseModel();

    return md;
  }

  @Override
  public List<AttachmentDetails> getAllRequest( final int userID )
  {
    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Query query = session.createSQLQuery( MyTaxReturnConstants.ATTACHMENT_SQL )
                               .addEntity( AttachmentDetails.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, userID );

    return query.list();
  }

}

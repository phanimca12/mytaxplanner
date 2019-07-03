package com.trs.dao;

import java.util.List;

import com.trs.model.AttachmentDetails;
import com.trs.model.ResponseModel;

public interface IAttachmentDetails
{
  public ResponseModel createNewAttachment( final AttachmentDetails attachmentDetails ) throws Exception;

  public List<AttachmentDetails> getAllRequest( final int userID );
}

package com.trs.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "AttachmentDetailList" )
@XmlAccessorType( XmlAccessType.NONE )
public class AttachmentDetailList
{

  @XmlElement( name = "AttachmentDetail" )
  private List<AttachmentDetails> AttachmentDetailsList;

  public List<AttachmentDetails> getAttachmentDetailsList()
  {
    return AttachmentDetailsList;
  }

  public void setAttachmentDetailsList( final List<AttachmentDetails> attachmentDetailsList )
  {
    AttachmentDetailsList = attachmentDetailsList;
  }
}

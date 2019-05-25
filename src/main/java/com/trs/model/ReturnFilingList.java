package com.trs.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "RetunFilingList" )
@XmlAccessorType( XmlAccessType.NONE )
public class ReturnFilingList
{

  @XmlElement( name = "ReturnFiling" )
  private List<ReturnFilingRequest> returnFilingList;

  public List<ReturnFilingRequest> getReturnFilingList()
  {
    return returnFilingList;
  }

  public void setReturnFilingList( final List<ReturnFilingRequest> returnFilingList )
  {
    this.returnFilingList = returnFilingList;
  }

}

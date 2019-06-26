package com.trs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType( XmlAccessType.NONE )
public class ResponseModel
{
  @XmlElement
  private String       successMessage;
  @XmlElement
  private User         user;
  private Agent        agent;
  private ReturnFiling returnFilingRequest;

  public ReturnFiling getReturnFilingRequest()
  {
    return returnFilingRequest;
  }

  public void setReturnFilingRequest( final ReturnFiling returnFilingRequest )
  {
    this.returnFilingRequest = returnFilingRequest;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser( final User user )
  {
    this.user = user;
  }

  public String getSuccessMessage()
  {
    return successMessage;
  }

  public void setSuccessMessage( final String successMessage )
  {
    this.successMessage = successMessage;
  }

  public Agent getAgent()
  {
    return agent;
  }

  public void setAgent( final Agent agent )
  {
    this.agent = agent;
  }

}

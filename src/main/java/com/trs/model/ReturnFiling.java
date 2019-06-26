package com.trs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement( name = "ReturnFiling" )
@XmlAccessorType( XmlAccessType.NONE )
@Entity
@Table( name = "ReturnFilingRequestTable" )
public class ReturnFiling
{
  @Id
  @XmlAttribute
  @JsonProperty
  private long   requestID;

  @XmlAttribute
  @JsonProperty
  private String req_Date;

  @XmlAttribute
  @JsonProperty
  private int    userID;

  @XmlAttribute
  @JsonProperty
  private String agentCode;

  public String getAgentCode()
  {
    return agentCode;
  }

  public void setAgentCode( final String agentCode )
  {
    this.agentCode = agentCode;
  }

  @XmlAttribute
  @JsonProperty
  private String filingYear;

  @XmlAttribute
  @JsonProperty
  private String status;

  @XmlAttribute
  @JsonProperty
  private String agentComments;

  public String getReq_Date()
  {
    return req_Date;
  }

  public long getRequestID()
  {
    return requestID;
  }

  public void setRequestID( final long rEQID )
  {
    this.requestID = rEQID;
  }

  public void setReq_Date( final String req_Date )
  {
    this.req_Date = req_Date;
  }

  public int getUserID()
  {
    return userID;
  }

  public void setUserID( final int userID )
  {
    this.userID = userID;
  }

  public String getFilingYear()
  {
    return filingYear;
  }

  public void setFilingYear( final String filingYear )
  {
    this.filingYear = filingYear;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus( final String status )
  {
    this.status = status;
  }

  public String getAgentComments()
  {
    return agentComments;
  }

  public void setAgentComments( final String agentComments )
  {
    this.agentComments = agentComments;
  }

}

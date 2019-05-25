package com.trs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class ReturnFilingRequest
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private int    request_ID;

  @XmlAttribute
  @JsonProperty
  private String req_Date;

  @XmlAttribute
  @JsonProperty
  private String user_ID;

  @XmlAttribute
  @JsonProperty
  private String agent_Code;

  @XmlAttribute
  @JsonProperty
  private String filing_Year;

  @XmlAttribute
  @JsonProperty
  private String status;

  @XmlAttribute
  @JsonProperty
  private String agent_Comments;

  public String getReq_Date()
  {
    return req_Date;
  }

  public void setReq_Date( final String req_Date )
  {
    this.req_Date = req_Date;
  }

  public String getUser_ID()
  {
    return user_ID;
  }

  public void setUser_ID( final String user_ID )
  {
    this.user_ID = user_ID;
  }

  public String getAgent_Code()
  {
    return agent_Code;
  }

  public void setAgent_Code( final String agent_Code )
  {
    this.agent_Code = agent_Code;
  }

  public String getFiling_Year()
  {
    return filing_Year;
  }

  public void setFiling_Year( final String filing_Year )
  {
    this.filing_Year = filing_Year;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus( final String status )
  {
    this.status = status;
  }

  public String getAgent_Comments()
  {
    return agent_Comments;
  }

  public void setAgent_Comments( final String agent_Comments )
  {
    this.agent_Comments = agent_Comments;
  }

}

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

@XmlRootElement( name = "Agent" )
@XmlAccessorType( XmlAccessType.NONE )
@Entity
@Table( name = "AgentDetailsTable" )
public class Agent
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private int    id;
  @XmlAttribute
  @JsonProperty
  private String name;

  @XmlAttribute
  @JsonProperty
  private String emailID;
  @XmlAttribute
  @JsonProperty
  private String mobile;
  @XmlAttribute
  @JsonProperty
  private String password;

  @XmlAttribute
  @JsonProperty
  private String creationDate;

  @XmlAttribute
  @JsonProperty
  private String country;

  @XmlAttribute
  @JsonProperty
  private String city;

  @XmlAttribute
  @JsonProperty
  private String agentDescription;

  @XmlAttribute
  @JsonProperty
  private String agentCode;

  public String getCountry()
  {
    return country;
  }

  public void setCountry( final String country )
  {
    this.country = country;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity( final String city )
  {
    this.city = city;
  }

  public String getAgentDescription()
  {
    return agentDescription;
  }

  public void setAgentDescription( final String agentDescription )
  {
    this.agentDescription = agentDescription;
  }

  public String getAgentCode()
  {
    return agentCode;
  }

  public void setAgentCode( final String agentCode )
  {
    this.agentCode = agentCode;
  }

  public String getState()
  {
    return state;
  }

  public void setState( final String state )
  {
    this.state = state;
  }

  @XmlAttribute
  @JsonProperty
  private String state;

  public String getName()
  {
    return name;
  }

  public void setName( final String name )
  {
    this.name = name;
  }

  public String getEmailID()
  {
    return emailID;
  }

  public void setEmailID( final String emailID )
  {
    this.emailID = emailID;
  }

  public String getMobile()
  {
    return mobile;
  }

  public void setMobile( final String string )
  {
    this.mobile = string;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword( final String password )
  {
    this.password = password;
  }

  public String getCreationDate()
  {
    return creationDate;
  }

  public void setCreationDate( final String creationDate )
  {
    this.creationDate = creationDate;
  }
}

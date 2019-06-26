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

@XmlRootElement( name = "User" )
@XmlAccessorType( XmlAccessType.NONE )
@Entity
@Table( name = "UserTable" )
public class User
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @XmlAttribute
  @JsonProperty
  private int    id;

  public int getId()
  {
    return id;
  }

  public void setId( int id )
  {
    this.id = id;
  }

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

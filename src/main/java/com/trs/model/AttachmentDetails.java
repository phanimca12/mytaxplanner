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

@XmlRootElement( name = "AttachmentDetails" )
@XmlAccessorType( XmlAccessType.NONE )
@Entity
@Table( name = "AttachmentTable" )
public class AttachmentDetails
{
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private int    sno;

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
  private String file_name;

  @XmlAttribute
  @JsonProperty
  private String file_type;

  @XmlAttribute
  @JsonProperty
  private String file_path;

  public long getRequestID()
  {
    return requestID;
  }

  public void setRequestID( final long requestID )
  {
    this.requestID = requestID;
  }

  public String getReq_Date()
  {
    return req_Date;
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

  public String getFile_name()
  {
    return file_name;
  }

  public void setFile_name( final String file_name )
  {
    this.file_name = file_name;
  }

  public String getFile_type()
  {
    return file_type;
  }

  public void setFile_type( final String file_type )
  {
    this.file_type = file_type;
  }

  public String getFile_path()
  {
    return file_path;
  }

  public void setFile_path( final String file_path )
  {
    this.file_path = file_path;
  }
}
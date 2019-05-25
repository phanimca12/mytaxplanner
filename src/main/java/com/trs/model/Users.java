package com.trs.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Users" )
@XmlAccessorType( XmlAccessType.NONE )
public class Users
{
  @XmlElement( name = "User" )
  private List<User> userList;

  public List<User> getUserList()
  {
    return userList;
  }

  public void setUserList( final List<User> userList )
  {
    this.userList = userList;
  }
}

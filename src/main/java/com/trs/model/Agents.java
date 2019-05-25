package com.trs.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Agents" )
@XmlAccessorType( XmlAccessType.NONE )
public class Agents
{
  @XmlElement( name = "Agent" )
  private List<Agent> agentList;

  public List<Agent> getAgentList()
  {
    return agentList;
  }

  public void setAgentList( final List<Agent> agentList )
  {
    this.agentList = agentList;
  }
}

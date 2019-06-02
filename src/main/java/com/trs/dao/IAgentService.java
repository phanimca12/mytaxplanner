package com.trs.dao;

import java.util.List;

import com.trs.model.Agent;
import com.trs.model.ResponseModel;

public interface IAgentService
{
  public List<Agent> getAllAgents();

  public ResponseModel createNewAgent( final Agent agent ) throws Exception;

  public boolean isAgentrExist( String email, String mobile );

}

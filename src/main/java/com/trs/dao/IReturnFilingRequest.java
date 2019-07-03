package com.trs.dao;

import java.util.List;

import com.trs.model.ResponseModel;
import com.trs.model.ReturnFiling;

public interface IReturnFilingRequest
{
  // public List<Agent> getAllAgents();

  public ResponseModel createNewReturnRequest( final ReturnFiling returnFiling ) throws Exception;

  public List<ReturnFiling> getAllRequest( final int userID );

  // public boolean isAgentrExist( String email, String mobile );
}

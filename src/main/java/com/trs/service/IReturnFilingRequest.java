package com.trs.service;

import com.trs.model.ResponseModel;
import com.trs.model.ReturnFilingRequest;

public interface IReturnFilingRequest
{
  // public List<Agent> getAllAgents();

  public ResponseModel createNewReturnRequest( final ReturnFilingRequest returnFiling ) throws Exception;

  // public boolean isAgentrExist( String email, String mobile );
}

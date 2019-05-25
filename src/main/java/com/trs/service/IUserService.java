package com.trs.service;

import java.util.List;

import com.trs.model.ResponseModel;
import com.trs.model.User;

public interface IUserService
{

  public List<User> getAllUsers();

  public ResponseModel createNewUser( final User user ) throws Exception;

  public boolean isUserExist( String email, String mobile );

}

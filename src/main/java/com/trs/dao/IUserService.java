package com.trs.dao;

import java.util.List;

import com.trs.model.ResponseModel;
import com.trs.model.User;

public interface IUserService
{

  public List<User> getAllUsers();

  public ResponseModel createNewUser( final User user ) throws Exception;

  public boolean isUserExist( String email, String mobile );

  public boolean isAuthorizedUser( String emailID, String password );

  public int getUserID( String emailID );

  public List getUserDetails( String userID );

}
package com.trs.dao;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.trs.config.DBConfig;
import com.trs.constant.MyTaxReturnConstants;
import com.trs.logger.FileLogger;
import com.trs.model.ResponseModel;
import com.trs.model.User;
import com.trs.util.HibernateSessionCnf;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class UserService implements IUserService
{
  DBConfig      dbConfig  = new DBConfig();
  Logger        m_logger  = FileLogger.getInstance();

  static String className = UserService.class.getName();

  public List<User> getAllUsers()
  {

    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Query query = session.createQuery( "from User" );// here persistent class name is Emp
    return query.list();
  }

  public ResponseModel createNewUser( final User user ) throws Exception
  {
    ResponseModel model = null;
    try
    {
      final IUtility util = new Utility();
      final User createUser = new User();
      createUser.setName( user.getName() );
      createUser.setEmailID( user.getEmailID() );
      createUser.setPassword( user.getPassword() );
      createUser.setCreationDate( util.getCurrentDateTime() );
      createUser.setMobile( user.getMobile() );
      final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure( "hibernate.cfg.xml" ).build();
      final Metadata meta = new MetadataSources( ssr ).getMetadataBuilder().build();

      final SessionFactory factory = meta.getSessionFactoryBuilder().build();
      final Session session = factory.openSession();
      final Transaction t = session.beginTransaction();
      session.save( createUser );
      t.commit();

      model = new ResponseModel();
      model.setUser( createUser );
      model.setSuccessMessage( "created" );
    }
    catch ( final Exception e )
    {

      m_logger.log( Level.ALL, className + "\t" + e.getMessage(), new IOException( "Internal server error" ) );
    }
    return model;

  }

  public boolean isUserExist( final String email, final String mobile )
  {
    Query query = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.USEREXISTS_SQL )
                                 .addEntity( User.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_EMAIL, email );
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, className + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    return query.getResultList().size() > 0 ? true : false;
  }

  public boolean isAuthorizedUser( final String emailID, final String password )
  {
    // final Session session = dbConfig.getSessionFactory().openSession();
    m_logger.info( "Session started===" );
    Query query = null;

    try
    {

      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.AUTHORIZEDUSER_SQL )
                                 .addEntity( User.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_EMAILID, emailID )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_PASSWORD, password );

    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, className + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    finally
    {

    }
    return query.getResultList().size() > 0 ? true : false;

  }

  public int getUserID( final String emailID )
  {

    Query query = null;
    int UID = 0;
    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.GETUSERID_SQL )
                                 .addEntity( User.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_USEREMAILID, emailID );
      final List list = query.list();

      final Iterator it = list.iterator();

      while ( it.hasNext() )
      {
        final Object object = it.next();
        final User user = (User)object;
        UID = user.getId();
      }
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, className + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    return UID;
  }

  public String getUserName( final String emailID )
  {

    Query query = null;
    String UserName = null;
    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.GETUSERID_SQL )
                                 .addEntity( User.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_USEREMAILID, emailID );
      final List list = query.list();

      final Iterator it = list.iterator();

      while ( it.hasNext() )
      {
        final Object object = it.next();
        final User user = (User)object;
        UserName = user.getName();
      }
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, className + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    return UserName;
  }

  public String getUserEmailID( final long userID )
  {

    Query query = null;
    String UserEmailID = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.GETEMAILID_SQL )
                                 .addEntity( User.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_iID, userID );
      final List list = query.list();

      final Iterator it = list.iterator();

      while ( it.hasNext() )
      {
        final Object object = it.next();
        final User user = (User)object;
        UserEmailID = user.getEmailID();

      }
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, className + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    return UserEmailID;
  }

  public List getUserDetails( final String userID )
  {

    Query query = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.USERDETAILS_SQL )
                                 .addEntity( User.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_USERID, userID );
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, className + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    return query.list();
  }

  public String getUserPassword( final String emailID )
  {
    Query query = null;
    String password = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.GETUSERID_SQL )
                                 .addEntity( User.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_USEREMAILID, emailID );
      final List list = query.list();

      final Iterator it = list.iterator();

      while ( it.hasNext() )
      {
        final Object object = it.next();
        final User user = (User)object;
        password = user.getPassword();
      }
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, className + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    return password;
  }
}

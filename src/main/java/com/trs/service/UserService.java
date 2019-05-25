package com.trs.service;

import java.util.List;

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
import com.trs.model.ResponseModel;
import com.trs.model.User;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class UserService implements IUserService
{
  DBConfig dbConfig = new DBConfig();

  public List<User> getAllUsers()
  {

    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Query query = session.createQuery( "from User" );// here persistent class name is Emp
    return query.list();
  }

  public ResponseModel createNewUser( final User user ) throws Exception
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
    System.out.println( isUserExist( "aphani@dstws.com", "94410540522" ) );
    final ResponseModel model = new ResponseModel();
    model.setUser( createUser );
    model.setSuccessMessage( "User Created Successfully" );
    return model;

  }

  public boolean isUserExist( final String email, final String mobile )
  {

    final Session session = dbConfig.getSessionFactory().openSession();

    final Query query = session.createSQLQuery( MyTaxReturnConstants.USEREXISTS_SQL )
                               .addEntity( User.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_EMAIL, email )
                               .setParameter( MyTaxReturnConstants.PARAMETER_MOBILE, mobile );

    return query.getResultList().size() > 0 ? true : false;
  }

}

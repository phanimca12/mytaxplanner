package com.trs.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionCnf
{
  private static SessionFactory             sessionFactory;
  private static ServiceRegistry            serviceRegistry;
  private static final ThreadLocal<Session> threadLocal;

  static
  {
    try
    {

      serviceRegistry = new StandardServiceRegistryBuilder().configure( "hibernate.cfg.xml" ).build();
      final Metadata meta = new MetadataSources( serviceRegistry ).getMetadataBuilder().build();

      sessionFactory = meta.getSessionFactoryBuilder().build();

      /*   Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
      threadLocal = new ThreadLocal<Session>();

    }
    catch ( final Throwable t )
    {
      t.printStackTrace();
      throw new ExceptionInInitializerError( t );
    }
  }

  public static Session getSession()
  {
    Session session = threadLocal.get();
    if ( session == null )
    {
      session = sessionFactory.openSession();
      threadLocal.set( session );
    }
    return session;
  }

  public static void closeSession()
  {
    final Session session = threadLocal.get();
    if ( session != null )
    {
      session.close();
      threadLocal.set( null );
    }
  }

  public static void closeSessionFactory()
  {
    sessionFactory.close();
    StandardServiceRegistryBuilder.destroy( serviceRegistry );
  }
}

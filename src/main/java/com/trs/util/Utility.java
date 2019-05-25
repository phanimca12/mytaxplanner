package com.trs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Utility implements IUtility
{
  final DateFormat sdf         = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
  final Date       date        = new Date();
  final String     currentDate = null;
  final int        minValue    = 1000;
  final int        maxValue    = 5000;

  public String getCurrentDateTime()
  {

    return sdf.format( date );
  }

  public Session getHibernateSessionObj()
  {
    final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure( "hibernate.cfg.xml" ).build();
    final Metadata meta = new MetadataSources( ssr ).getMetadataBuilder().build();

    final SessionFactory factory = meta.getSessionFactoryBuilder().build();
    final Session session = factory.openSession();

    return session;
  }

  public long getRandomNumber()
  {
    // TODO Auto-generated method stub
    final long random = (long) ( Math.random() * maxValue + minValue );
    return random;
  }

}

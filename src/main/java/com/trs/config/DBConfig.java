package com.trs.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.trs.constant.MyTaxReturnConstants;

public class DBConfig implements IDBConfig
{
  StandardServiceRegistry ssr     = null;
  Metadata                meta    = null;
  SessionFactory          factory = null;

  public SessionFactory getSessionFactory()
  {
    ssr = new StandardServiceRegistryBuilder().configure( MyTaxReturnConstants.HIBERNATE_CONFIG_FILE ).build();
    meta = new MetadataSources( ssr ).getMetadataBuilder().build();

    factory = meta.getSessionFactoryBuilder().build();
    return factory;
  }
}
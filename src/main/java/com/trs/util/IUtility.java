package com.trs.util;

import org.hibernate.Session;

public interface IUtility
{
  public String getCurrentDateTime();

  public Session getHibernateSessionObj();

  public long getRandomNumber();

}

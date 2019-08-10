package com.trs.dao;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.trs.config.DBConfig;
import com.trs.constant.MyTaxReturnConstants;
import com.trs.logger.FileLogger;
import com.trs.model.Agent;
import com.trs.model.ResponseModel;
import com.trs.util.HibernateSessionCnf;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class AgentService implements IAgentService
{
  final DBConfig       dbConfig  = new DBConfig();
  Logger               m_logger  = FileLogger.getInstance();
  public static String classname = AgentService.class.getName();

  @Override
  public ResponseModel createNewAgent( final Agent agent ) throws Exception
  {
    ResponseModel model = null;
    try
    {
      final IUtility util = new Utility();
      final Agent createAgent = new Agent();
      createAgent.setName( agent.getName() );
      createAgent.setEmailID( agent.getEmailID() );
      createAgent.setPassword( agent.getPassword() );
      createAgent.setCreationDate( util.getCurrentDateTime() );
      createAgent.setMobile( agent.getMobile() );
      createAgent.setCountry( agent.getCountry() );
      createAgent.setState( agent.getState() );
      createAgent.setCity( agent.getCity() );
      createAgent.setAgentDescription( agent.getAgentDescription() );
      createAgent.setAgentCode( "A" + util.getRandomNumber() );

      final Session session = util.getHibernateSessionObj();
      final Transaction t = session.beginTransaction();
      session.save( createAgent );
      t.commit();

      model = new ResponseModel();
      model.setAgent( createAgent );
      model.setSuccessMessage( "Agent Created Successfully" );

    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new Exception( "Internal server error" ) );

    }
    return model;
  }

  @Override
  public boolean isAgentrExist( final String email, final String mobile )
  {
    Query query = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.AGENTEXISTS_SQL )
                                 .addEntity( Agent.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_AEMAIL, email );
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new Exception( "Internal server error" ) );
    }

    return query.getResultList().size() > 0 ? true : false;
  }

  @Override
  public List<Agent> getAllAgents()
  {
    Query query = null;

    try
    {
      query = HibernateSessionCnf.getSession().createQuery( "from Agent" );// here persistent class name is Emc
    }
    catch ( final Exception e )
    {

      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new Exception( "Internal server error" ) );
    }
    return query.list();
  }

  @Override
  public boolean isAuthorizedAgent( final String emailID, final String password )
  {

    Query query = null;

    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.AUTHORIZEDAGENT_SQL )
                                 .addEntity( Agent.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_AGENTEMAILID, emailID )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_AGENTPASSWORD, password );
    }
    catch ( final Exception e )
    {

      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new Exception( "Internal server error" ) );
    }
    return query.getResultList().size() > 0 ? true : false;
  }

  @Override
  public String getAgentID( final String emailID )
  {
    Query query = null;
    String AID = null;
    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.GETAGENTID_SQL )
                                 .addEntity( Agent.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_USEREMAILID, emailID );
      final List list = query.list();

      final Iterator it = list.iterator();

      while ( it.hasNext() )
      {
        final Object object = it.next();
        final Agent agent = (Agent)object;
        AID = agent.getAgentCode();
      }
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new Exception( "Internal server error" ) );

    }
    return AID;
  }

  @Override
  public String getAgentEmailID( final String AgentCode )
  {

    Query query = null;
    String AID = null;
    try
    {
      query = HibernateSessionCnf.getSession()
                                 .createSQLQuery( MyTaxReturnConstants.GETAGENT_EMAILID )
                                 .addEntity( Agent.class )
                                 .setParameter( MyTaxReturnConstants.PARAMETER_AGENTCODE, AgentCode );
      final List list = query.list();

      final Iterator it = list.iterator();

      while ( it.hasNext() )
      {
        final Object object = it.next();
        final Agent agent = (Agent)object;
        AID = agent.getEmailID();
      }
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new Exception( "Internal server error" ) );

    }
    return AID;
  }

  @Override
  public List getAgentInfo( final String Para_Name, final String Para_Value )
  {

    Query query = null;
    final String param = Para_Name.toLowerCase();
    try
    {
      switch ( param )

      {
        case "agentcode":

          query = HibernateSessionCnf.getSession()
                                     .createSQLQuery( MyTaxReturnConstants.AGENTDETAILS_SQL )
                                     .setParameter( MyTaxReturnConstants.PARAMETER_AGENTCODE, Para_Value );
          break;
        case "requestid":
          query = HibernateSessionCnf.getSession()
                                     .createSQLQuery( MyTaxReturnConstants.AGENTREQUEST_SQL )
                                     .setParameter( MyTaxReturnConstants.PARAMETER_REQUESTID, Para_Value );
          break;

      }
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, classname + "\t" + e.getMessage(), new IOException( "Internal server error" ) );

    }
    return query.list();
  }

}

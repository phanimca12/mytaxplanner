package com.trs.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.trs.config.DBConfig;
import com.trs.constant.MyTaxReturnConstants;
import com.trs.model.Agent;
import com.trs.model.ResponseModel;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class AgentService implements IAgentService
{
  final DBConfig dbConfig = new DBConfig();

  public ResponseModel createNewAgent( final Agent agent ) throws Exception
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

    final ResponseModel model = new ResponseModel();
    model.setAgent( createAgent );
    model.setSuccessMessage( "Agent Created Successfully" );
    return model;
  }

  public boolean isAgentrExist( final String email, final String mobile )
  {
    final Session session = dbConfig.getSessionFactory().openSession();

    final Query query = session.createSQLQuery( MyTaxReturnConstants.USEREXISTS_SQL )
                               .addEntity( Agent.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_EMAIL, email )
                               .setParameter( MyTaxReturnConstants.PARAMETER_MOBILE, mobile );

    return query.getResultList().size() > 0 ? true : false;
  }

  public List<Agent> getAllAgents()
  {
    final Utility util = new Utility();
    final Session session = util.getHibernateSessionObj();
    final Query query = session.createQuery( "from Agent" );// here persistent class name is Emp
    return query.list();
  }

  public boolean isAuthorizedAgent( final String emailID, final String password )
  {
    final Session session = dbConfig.getSessionFactory().openSession();

    final Query query = session.createSQLQuery( MyTaxReturnConstants.AUTHORIZEDAGENT_SQL )
                               .addEntity( Agent.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_AGENTEMAILID, emailID )
                               .setParameter( MyTaxReturnConstants.PARAMETER_AGENTPASSWORD, password );

    return query.getResultList().size() > 0 ? true : false;
  }

  public String getAgentID( final String emailID )
  {
    final Session session = dbConfig.getSessionFactory().openSession();

    final Query query = session.createSQLQuery( MyTaxReturnConstants.GETAGENTID_SQL )
                               .addEntity( Agent.class )
                               .setParameter( MyTaxReturnConstants.PARAMETER_USEREMAILID, emailID );
    final List list = query.list();
    String AID = null;
    final Iterator it = list.iterator();

    while ( it.hasNext() )
    {
      final Object object = it.next();
      final Agent agent = (Agent)object;
      AID = agent.getAgentCode();
    }
    session.close();
    return AID;
  }

}

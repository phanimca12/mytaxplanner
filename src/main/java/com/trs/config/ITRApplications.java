package com.trs.config;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.xml.datatype.XMLGregorianCalendar;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.Pair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import com.trs.controller.AgentController;
import com.trs.controller.FilingRequestController;
import com.trs.controller.UserController;
import com.trs.exception.GlobalExceptionMapper;
import com.trs.exception.UserCreationrExceptionMapper;
import com.trs.servlet.MyLoginServlet;

//@ApplicationPath( "/*" )
public class ITRApplications extends Application
{
  @Override
  public Set<Class<?>> getClasses()
  {
    final Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add( UserCreationrExceptionMapper.class );
    classes.add( GlobalExceptionMapper.class );
    return classes;
  }

  @Override
  public Set<Object> getSingletons()
  {
    final Set<Object> classes = new HashSet<Object>();

    classes.add( new UserController() );
    classes.add( new AgentController() );
    classes.add( new FilingRequestController() );
    classes.add( new MyLoginServlet() );
    final JacksonJaxbJsonProvider provider = getJacksonJaxbJsonProvider();

    if ( provider != null )
      classes.add( provider );

    return classes;
  }

  protected static JacksonJaxbJsonProvider getJacksonJaxbJsonProvider()
  {
    final JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
    final ObjectMapper mapper = new ObjectMapper();
    mapper.setAnnotationIntrospector( createJaxbJacksonAnnotationIntrospector() );
    mapper.setSerializationInclusion( JsonSerialize.Inclusion.NON_NULL );
    mapper.configure( SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, false );
    mapper.configure( SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false );
    // mapper.configure( DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false );
    mapper.setPropertyNamingStrategy( new JaxbJsonCompatibleNamingStrategy() );
    mapper.getDeserializationConfig().addMixInAnnotations( XMLGregorianCalendar.class,
                                                           XMLGregorianCalendarFilter.class );

    provider.setMapper( mapper );
    return provider;
  }

  private static Pair createJaxbJacksonAnnotationIntrospector()
  {

    final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
    final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

    return new AnnotationIntrospector.Pair( jacksonIntrospector, jaxbIntrospector );
  }

  public interface XMLGregorianCalendarFilter
  {
    @JsonIgnore
    public void setYear( BigInteger year );
    // any methods/field you want to ignore
  }

}
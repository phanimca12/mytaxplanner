package com.trs.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/HTTPSession" )
public class HttpSessionController
{

  @GET
  @Produces( MediaType.TEXT_PLAIN )
  public String sayPlainTextHello()
  {

    return "Hello Rest World";
  }
}

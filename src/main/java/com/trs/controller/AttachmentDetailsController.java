package com.trs.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.trs.dao.AttachmentDetailsImpl;
import com.trs.dao.IAttachmentDetails;
import com.trs.dao.UserService;
import com.trs.model.AttachmentDetailList;
import com.trs.model.AttachmentDetails;

@Path( "/attachments" )
public class AttachmentDetailsController
{
  @GET
  @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
  @Path( "/download/{param}" )
  public List<AttachmentDetails> getResponse( @PathParam( "param" ) final String uid )
  {

    final IAttachmentDetails attachments = new AttachmentDetailsImpl();

    final AttachmentDetailList attachList = new AttachmentDetailList();

    final UserService service = new UserService();
    final int UserID = service.getUserID( uid );

    attachList.setAttachmentDetailsList( attachments.getAllRequest( UserID ) );

    return attachList.getAttachmentDetailsList();

  }

}

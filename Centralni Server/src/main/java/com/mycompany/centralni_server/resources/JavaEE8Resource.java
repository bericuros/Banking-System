package com.mycompany.centralni_server.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("test")
public class JavaEE8Resource {
    
    @POST
    public Response ping(@FormParam("PB") String pb, @FormParam("Naziv") String naziv){
        return Response.status(Response.Status.OK).entity(pb + " " + naziv).build();
    }
}

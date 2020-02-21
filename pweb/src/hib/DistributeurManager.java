package hib;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import util.HibernateUtil;

import java.util.*;
import java.text.ParseException;


@Path("/managing")
public class DistributeurManager{
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Distributeur> ListAll() {
    	HibernateUtil.getSessionFactory()
        .getCurrentSession().beginTransaction();
        List<Distributeur>dst= DistributeurManagerServlet.listEvents();
        HibernateUtil.getSessionFactory()
        .getCurrentSession().getTransaction().commit();
        return dst;
    }
    
    
    @POST
    @Path("/create/{s}/{t}/{a}/{e}/{lo}/{la}/{i}/{c}")
    public Response create(@PathParam("s")String serie,
    		@PathParam("t")String type, 
    		@PathParam("a")String adr, 
    		@PathParam("e")String emp, 
    		@PathParam("lo")String lon, 
    		@PathParam("la")String lat, 
    		@PathParam("i")String inte, 
    		@PathParam("c")String com) throws ParseException {
    	
    	HibernateUtil.getSessionFactory()
        .getCurrentSession().beginTransaction();
        DistributeurManagerServlet.createAndStoreDistributeur(serie,type,adr,emp,lon,lat,inte,com);
        HibernateUtil.getSessionFactory()
        .getCurrentSession().getTransaction().commit();
        return Response.status(Response.Status.OK).build();
    }
    
    @POST
    @Path("/modify/{s}/{a}/{e}/{lo}/{la}/{i}/{c}")
    public Response modify(@PathParam("s")String serie, 
    		@PathParam("a")String adr, 
    		@PathParam("e")String emp, 
    		@PathParam("lo")String lon, 
    		@PathParam("la")String lat, 
    		@PathParam("i")String inte, 
    		@PathParam("c")String com) throws ParseException {
    	
    	HibernateUtil.getSessionFactory()
        .getCurrentSession().beginTransaction();
        DistributeurManagerServlet.ModifyDistributeur(serie,adr,emp,lon,lat,inte,com);
        HibernateUtil.getSessionFactory()
        .getCurrentSession().getTransaction().commit();
        return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("/delete/{s}")
    public Response modify(@PathParam("s")String serie) throws ParseException {
    	
    	HibernateUtil.getSessionFactory()
        .getCurrentSession().beginTransaction();
        DistributeurManagerServlet.DeleteDistributeur(serie);
        HibernateUtil.getSessionFactory()
        .getCurrentSession().getTransaction().commit();
        return Response.status(Response.Status.OK).build();
    }
}
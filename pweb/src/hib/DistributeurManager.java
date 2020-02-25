package hib;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import util.HibernateUtil;

import java.util.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    		@PathParam("c")String com) throws ParseException, UnsupportedEncodingException {
    	
    	serie = URLDecoder.decode(serie, "UTF-8");
    	adr = URLDecoder.decode(adr, "UTF-8");
    	emp = URLDecoder.decode(emp, "UTF-8");
    	lon = URLDecoder.decode(lon, "UTF-8");
    	lat = URLDecoder.decode(lat, "UTF-8");
    	type = URLDecoder.decode(type, "UTF-8");
    	inte = URLDecoder.decode(inte, "UTF-8");
    	com = URLDecoder.decode(com, "UTF-8");
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
    		@PathParam("c")String com) throws ParseException, UnsupportedEncodingException {
    	
    	serie = URLDecoder.decode(serie, "UTF-8");
    	adr = URLDecoder.decode(adr, "UTF-8");
    	emp = URLDecoder.decode(emp, "UTF-8");
    	lon = URLDecoder.decode(lon, "UTF-8");
    	lat = URLDecoder.decode(lat, "UTF-8");
    	inte = URLDecoder.decode(inte, "UTF-8");
    	com = URLDecoder.decode(com, "UTF-8");
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
    
    
    @POST
    @Path("/recepteur")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recepteurjson(String json) throws ParseException, JsonMappingException, JSONException, JsonProcessingException {
    	JSONObject receptionjson;
		receptionjson = new JSONObject(json);
		try {
			Recepteur.InsereRapport(Recepteur.jsontorapport(receptionjson));
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        return Response.status(Response.Status.OK).build();
    }
    
    
    @POST
    @Path("/recepteur")
    @Consumes(MediaType.APPLICATION_XML)
    public Response recepteurxml(String xml) throws ParseException {
    	final Element receptionxml;
    	final DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		DocumentBuilder build;
		try {
			build = fac.newDocumentBuilder();
			Document document;
			try {
				try {
					document = build.parse(new InputSource(new StringReader(xml)));
					receptionxml = document.getDocumentElement();
					Recepteur.InsereRapport(Recepteur.xmltorapport(receptionxml));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (SAXException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

        return Response.status(Response.Status.OK).build();
    }
}
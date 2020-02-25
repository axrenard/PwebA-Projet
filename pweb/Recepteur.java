package hib;

import javax.json.JsonObject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** import à ajouter */
import org.json.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import scala.collection.Set;
import util.HibernateUtil;

import javax.ws.rs.*;;

/**
 * Servlet implementation class recepteur
 */
@Path("/recepteur")
public class Recepteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recepteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rep = getMsgType((String)request.getServletContext().getAttribute("rapport"));
		final Element receptionxml;
		JSONObject receptionjson;
		try
		{
			final DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			try {
				builder = fac.newDocumentBuilder();
				Document document;
				try {
					document = builder.parse(rep);
					receptionxml = document.getDocumentElement();
					InsereRapport(xmltorapport(receptionxml));
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}		
		}
		catch (IOException e)
		{
			receptionjson = new JSONObject(request.getServletContext().getAttribute("rapport"));
			InsereRapport(jsontorapport(receptionjson));
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		this.getServletContext().getRequestDispatcher("").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static String getMsgType(String message) {
	    try {
	        new ObjectMapper().readTree(message);
	        return "JSON";
	    } catch (IOException e) {
	    }

	    try {
	        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(message)));
	        return "XML";
	    } catch (Exception e) {
	    }

	    return null;
	}
	
	public static Rapport xmltorapport(javax.swing.text.html.parser.Element x)
	{
		Set<String>err = newHashSet<>();
		Set<String>con = newHashSet<>();
		err.add(x.getAttribute("erreurs"));
		con.add(x.getAttribute("contenu"));
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(x.getAttribute("date"));
		Rapport r = new Rapport(x.getAttribute("serie"),date1,x.getAttribute("statut"),x.getAttribute("etat"),Float.parseFloat(x.getAttribute("temperature")),x.getAttribute("piece"),x.getAttribute("puce"),x.getAttribute("sanscontact"),err,con,Float.parseFloat(x.getAttribute("montant")));
		return r;
	}
	
	public static Rapport jsontorapport(JSONObject receptionjson)
	{
		Set<String>err = newHashSet<>();
		Set<String>con = newHashSet<>();
		err.add(receptionjson.getJSONArray("erreurs").getString(0));
		con.add(receptionjson.getJSONArray("contenu").getString(0));
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(receptionjson.getAttribute("date"));
		Rapport r = new Rapport(receptionjson.getString("serie"),date1,receptionjson.getString("statut"),receptionjson.getString("etat"),receptionjson.getFloat("temperature"),receptionjson.getString("piece"),receptionjson.getString("puce"),receptionjson.getString("sanscontact"),err,con,receptionjson.getFloat("montant"));
		return r;
	}
	
	protected static void InsereRapport(Rapport r)
	{
		HibernateUtil.getSessionFactory()
                .getCurrentSession().save(r);
	}
}

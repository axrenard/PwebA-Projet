package hib;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.Query;
/** import à ajouter */
import org.json.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.io.*;

import org.w3c.dom.Element;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import jdk.nashorn.internal.parser.JSONParser;
import util.HibernateUtil;
import java.util.Set;


public class Recepteur {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**String rep = getMsgType((String)request.getServletContext().getAttribute("rapport"));
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
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}		
		}
		catch (IOException e)
		{
			receptionjson = new JSONObject(request.getServletContext().getAttribute("rapport"));
			try {
				InsereRapport(jsontorapport(receptionjson));
			} catch (JSONException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
	*/	
	}
	
	/**public static String getMsgType(String message) {
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
	*/
	public static Rapport xmltorapport(Element receptionxml) throws ParseException
	{
		Set<Article>cont = (Set<Article>) receptionxml.getElementsByTagName("contenu").item(0).getTextContent;
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(receptionxml.getElementsByTagName("date").item(0).getTextContent());
		Rapport r = new Rapport(receptionxml.getElementsByTagName("serie").item(0).getTextContent(),date1,receptionxml.getElementsByTagName("statut").item(0).getTextContent(),receptionxml.getElementsByTagName("etat").item(0).getTextContent(),Float.parseFloat(receptionxml.getAttribute("temperature").item(0).getTextContent()),receptionxml.getElementsByTagName("piece").item(0).getTextContent(),receptionxml.getElementsByTagName("puce").item(0).getTextContent(),receptionxml.getElementsByTagName("sanscontact").item(0).getTextContent(),receptionxml.getElementsByTagName("erreurs").item(0).getTextContent(),cont,Float.parseFloat(receptionxml.getElementsByTagName("montant").item(0).getTextContent()));
		return r;
	}
	
	public static Rapport jsontorapport(JSONObject receptionjson) throws JSONException, ParseException, JsonMappingException, JsonProcessingException
	{
		ObjectMapper om = new ObjectMapper();
		DateFormat df =new SimpleDateFormat("HH:mm' 'dd/MM/yyyy");
		om.setDateFormat(df);
		Rapport r = om.readValue(receptionjson.toString(), Rapport.class);
		System.out.println(r.get_montant());
		System.out.println(r.get_erreurs());
		/**String[] er = om.readValue(receptionjson.getJSONArray("erreurs").getString(0), String[].class);
		Article[] con = om.readValue(receptionjson.getJSONArray("contenu").getString(0), Article[].class);
		Set<String>err = new HashSet<>();
		for(String s:er) {
			err.add(s);
		}
		Set<Article>cont= new HashSet<>();
		for(Article a:con) {
			cont.add(a);
		}
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(receptionjson.getString("date"));
		Rapport r = new Rapport(receptionjson.getString("serie"),date1,receptionjson.getString("statut"),receptionjson.getString("etat"),receptionjson.getFloat("temperature"),receptionjson.getString("piece"),receptionjson.getString("puce"),receptionjson.getString("sanscontact"),err,cont,receptionjson.getFloat("montant"));
		*/return r;
	}
	
	protected static void InsereRapport(Rapport r)
	{
		HibernateUtil.getSessionFactory()
        .getCurrentSession().beginTransaction();
		for(Article a : r.get_contenu()){
			HibernateUtil.getSessionFactory()
            .getCurrentSession().save(a);
		}
		HibernateUtil.getSessionFactory()
                .getCurrentSession().save(r);
		HibernateUtil.getSessionFactory()
        .getCurrentSession().getTransaction().commit();

	}
}
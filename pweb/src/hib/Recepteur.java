package hib;


/** import à ajouter */
import org.json.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import jdk.nashorn.internal.parser.JSONParser;
import util.HibernateUtil;
import java.util.Set;


public class Recepteur {

	public static Rapport xmltorapport(Element receptionxml) throws ParseException
	{
		NodeList nl = receptionxml.getElementsByTagName("article");
		Set<Article>cont = new HashSet<>();
		for(int i = 0; i < nl.getLength(); i++)
		{
			Node node = nl.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE)
			{
				Element el = (Element) node;
				String ref = el.getElementsByTagName("ref").item(0).getTextContent();
				Integer q = Integer.parseInt(el.getElementsByTagName("quantite").item(0).getTextContent());
				Article a = new Article(ref,q);
				cont.add(a);
			}
		}

		Date date1 = new SimpleDateFormat("HH:mm' 'dd/MM/yyyy").parse(receptionxml.getElementsByTagName("date").item(0).getTextContent());

		Rapport r = new Rapport(receptionxml.getElementsByTagName("serie").item(0).getTextContent(),date1,receptionxml.getElementsByTagName("statut").item(0).getTextContent(),receptionxml.getElementsByTagName("etat").item(0).getTextContent(),Float.parseFloat(receptionxml.getElementsByTagName("temperature").item(0).getTextContent()),receptionxml.getElementsByTagName("piece").item(0).getTextContent(),receptionxml.getElementsByTagName("puce").item(0).getTextContent(),receptionxml.getElementsByTagName("sanscontact").item(0).getTextContent(),receptionxml.getElementsByTagName("erreurs").item(0).getTextContent(),cont,Float.parseFloat(receptionxml.getElementsByTagName("montant").item(0).getTextContent()));

		return r;
	}
	
	public static Rapport jsontorapport(JSONObject receptionjson) throws JSONException, ParseException, JsonMappingException, JsonProcessingException{
	
		ObjectMapper om = new ObjectMapper();
		DateFormat df =new SimpleDateFormat("HH:mm' 'dd/MM/yyyy");
		om.setDateFormat(df);
		Rapport r = om.readValue(receptionjson.toString(), Rapport.class);
		return r;
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
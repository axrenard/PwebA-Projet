import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** import à ajouter */
import org.json.*;
import java.net.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.jws.WebService;
import com.fasterxml.jackson.*;

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
		String rep = getMsgType(request.getServletContext().getAttribute("rapport"));
		boolean a = true;
		try
		{
			final DocumentBuilder builder = DocumentBuilderFactory.newDocumentBuilder();		
			final Document document = builder.parse(request.getServletContext().getAttribut("rapport"));
			final Element receptionxml = document.getDocumentElement();
		}
		catch (IOException e)
		{
			a = false;
			JSONObject receptionjson = new JSONObject(request.getServletContext().getAttribut("rapport")); 
		}
		if(a)
		{
			InsereRapport(xmltorapport(receptionxml));
		}
		else
		{
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
	
	public static Rapport xmltorapport(Element x)
	{
		Rapport r = new rapport(x.getAttribute("serie"),x.getAttribute("date"),x.getAttribute("statut"),x.getAttribute("etat"),x.getAttribute("temperature"),x.getAttribute("piece"),x.getAttribute("puce"),x.getAttribute("sanscontact"),x.getAttribute("erreurs"),x.getAttribute("contenu"),x.getAttribute("montant"));
		return r;
	}
	
	public static Rapport jsontorapport(JsonObject j)
	{
		Rapport r = new rapport(j.getString("serie"),j.getString("date"),j.getString("statut"),j.getString("etat"),j.getDouble("temperature"),j.getString("piece"),j.getString("puce"),j.getString("sanscontact"),j.getElementById("erreurs"),j.getElementById("contenu"),j.getDouble("montant"));
		return r;
	}
	
	protected static void InsereRapport(Rapport r)
	{
		String query = "insert into rapports values (:serie, :date, :statut, :etat, :temperature, :piece, :puce, :sanscontact, :erreurs, :contenu, :montant)";
		Query q = HibernateUtil.getSessionFactory()
                .getCurrentSession().createQuery(query);
		q.setParameter("serie", serie);
		q.setParameter("date", date);
		q.setParameter("statut", statut);
		q.setParameter("etat", etat);
		q.setParameter("temperature", temperature);
		q.setParameter("piece", piece);
		q.setParameter("puce", puce);
		q.setParameter("sanscontact", sanscontact);
		q.setParameter("erreurs", erreurs);
		q.setParameter("contenu", contenu);
		q.setParameter("montant", montant);
		q.executeUpdate();
	}
}

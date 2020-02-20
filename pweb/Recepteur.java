
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** import � ajouter */
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
@WebService(urlPatterns="/recepteur")

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rep = getMsgType(request.getServletContext().getAttribute("rapport"));
		boolean a = true;
		try
		{
			final DocumentBuilder builder = DocumentBuilderFactory.newDocumentBuilder();		
			final Document document = builder.parse(request.getServletContext().getAttribut("rapport"));
			final Element reception = document.getDocumentElement();
			a = false;
		}
		catch (IOException e)
		{
			JSONObject reception = new JSONObject(request.getServletContext().getAttribut("rapport")); 
		}
		if(a)
		{
			/*stokage xml*/
		}
		else
		{
			/*stokage json*/
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
}

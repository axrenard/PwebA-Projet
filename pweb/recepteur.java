import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import à ajouter*/
import org.json.*;
import java.net.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Servlet implementation class recepteur
 */
@WebServlet(urlPatterns="/recepteur")

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
		ServletConfig config = getServletConfig();
		HttpSession session = request.getSession();
		String rep = getMsgType(request);
		if(rep.equals("XML"))
		{
			try 
			{
			  final DocumentBuilder builder = factory.newDocumentBuilder();		
			  final Document document = builder.parse(request);
			}
			catch (final ParserConfigurationException e) 
			{
			  e.printStackTrace();
			}
			catch (final SAXException e) 
			{
			  e.printStackTrace();
			}
			catch (final IOException e) 
			{
			  e.printStackTrace();
			}
			final Element reception = document.getDocumentElement();
			/*stokage reception*/
		}
		else
		{
			if(rep.equals("JSON"))
			{
				JSONObject reception = new JSONObject(request);
				/*stockage reception*/
			}
		}
		
		
		response.status(HttpServletResponse.SC_OK);
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

package hib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    protected void doGet(HttpServletRequest request,
            		HttpServletResponse response)
            		throws ServletException, IOException {

    		request.setAttribute("msg", "");

    		// Handle actions
    		if ( "store".equals(request.getParameter("action")) ) {

    			String serie = request.getParameter("serie");
    			String type = request.getParameter("type");
    			String adr = request.getParameter("adr");
				String emp = request.getParameter("emp");
				String lon = request.getParameter("lon");
				String lat =request.getParameter("lat");
				String inte = request.getParameter("int");
				String com = request.getParameter("com");
    			if ( "".equals(serie) || "".equals(type) ) {
    				request.setAttribute("msg", "Please enter serie number and type.");
    			} else {
    				request.setAttribute("msg", "Added distributeur.");
    				serie = URLEncoder.encode(serie, "UTF-8");
    				type = URLEncoder.encode(type, "UTF-8");
    				if ("".equals(adr)) {
    					adr = URLEncoder.encode("_","UTF-8");
    				}else {
    					adr = URLEncoder.encode(adr,"UTF-8");
    				}
    				if ("".equals(emp)) {
    					emp = URLEncoder.encode("_","UTF-8");
    				}else {
    					emp = URLEncoder.encode(emp,"UTF-8");
    				}
    				if ("".equals(lon)) {
    					lon = URLEncoder.encode("_","UTF-8");
    				}else {
    					lon = URLEncoder.encode(lon,"UTF-8");
    				}
    				if ("".equals(lat)) {
    					lat = URLEncoder.encode("_","UTF-8");
    				}else {
    					lat = URLEncoder.encode(lat,"UTF-8");
    				}
    				if ("".equals(inte)) {
    					inte = URLEncoder.encode("_","UTF-8");
    				}else {
    					inte = URLEncoder.encode(inte,"UTF-8");
    				}
    				if ("".equals(com)) {
    					com = URLEncoder.encode("_","UTF-8");
    				}else {
    					com = URLEncoder.encode(com,"UTF-8");
    				}

    			    URL url = new URL("http://localhost:8080/pweb/rest/managing/create/" +serie+"/"+type+
    			    				"/"+adr+"/"+emp+"/"+lon+"/"+lat+"/"+inte+"/"+com);
    			    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    			    conn.setRequestMethod("POST");
    			    Map<String, List<String>> header = conn.getHeaderFields();
    			    int responseCode = conn.getResponseCode();
    			    
    			}
    		}

    		if ( "delete".equals(request.getParameter("action")) ) {

    			String serie = request.getParameter("serie");

    			if ( "".equals(serie) ) {
    				request.setAttribute("msg", "Please enter serie number");
    			} else {
    				request.setAttribute("msg", "Deleted distributeur.");
    				 URL url = new URL("http://localhost:8080/pweb/rest/managing/delete/" +serie);
    				 HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    				 conn.setRequestMethod("DELETE");
    				 Map<String, List<String>> header = conn.getHeaderFields();
    				 int responseCode = conn.getResponseCode();

    			}
    		}

    		if ( "modify".equals(request.getParameter("action")) ) {

    			String serie = request.getParameter("serie");
    			String adr = request.getParameter("adr");
				String emp = request.getParameter("emp");
				String lon = request.getParameter("lon");
				String lat =request.getParameter("lat");
				String inte = request.getParameter("int");
				String com = request.getParameter("com");

    			if ( "".equals(serie) ) {
    				request.setAttribute("msg", "Please enter serie number");
    			} else {
    				request.setAttribute("msg", "Modify distributeur.");
    				serie = URLEncoder.encode(serie, "UTF-8");
    				if ("".equals(adr)) {
    					adr = URLEncoder.encode("_","UTF-8");
    				}else {
    					adr = URLEncoder.encode(adr,"UTF-8");
    				}
    				if ("".equals(emp)) {
    					emp = URLEncoder.encode("_","UTF-8");
    				}else {
    					emp = URLEncoder.encode(emp,"UTF-8");
    				}
    				if ("".equals(lon)) {
    					lon = URLEncoder.encode("_","UTF-8");
    				}else {
    					lon = URLEncoder.encode(lon,"UTF-8");
    				}
    				if ("".equals(lat)) {
    					lat = URLEncoder.encode("_","UTF-8");
    				}else {
    					lat = URLEncoder.encode(lat,"UTF-8");
    				}
    				if ("".equals(inte)) {
    					inte = URLEncoder.encode("_","UTF-8");
    				}else {
    					inte = URLEncoder.encode(inte,"UTF-8");
    				}
    				if ("".equals(com)) {
    					com = URLEncoder.encode("_","UTF-8");
    				}else {
    					com = URLEncoder.encode(com,"UTF-8");
    				}

    			    URL url = new URL("http://localhost:8080/pweb/rest/managing/modify/" +serie+
    			    				"/"+adr+"/"+emp+"/"+lon+"/"+lat+"/"+inte+"/"+com);
    			    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    			    conn.setRequestMethod("POST");
    			    Map<String, List<String>> header = conn.getHeaderFields();
    			    int responseCode = conn.getResponseCode();
    			}
    		}
    		
    		URL url = new URL("http://localhost:8080/pweb/rest/managing/list");
    		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    		conn.setRequestMethod("GET");
    		Map<String, List<String>> header = conn.getHeaderFields();
    		int responseCode = conn.getResponseCode();
    		BufferedReader br = null;
    		if (responseCode == 200) {
    		    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		    String strCurrentLine;
    		        while ((strCurrentLine = br.readLine()) != null) {
    		               System.out.println(strCurrentLine);
    		        }
    		}else {
    		request.setAttribute("msg", responseCode);
    		}
    		ServletContext context = getServletContext();
    		RequestDispatcher dispatcher = context.getRequestDispatcher("/gestion.jsp");
    		dispatcher.forward(request, response);


    }


}

package hib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;




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
    			if ( "".equals(serie) || "".equals(type)|| "".equals(adr)||"".equals(emp)||"".equals(lon)||"".equals(lat)||"".equals(inte)) {
    				request.setAttribute("msg", "veuillez rentrer toutes les données(commentaire facultatif)");
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
    			    if(responseCode!=200)
    			    	request.setAttribute("msg", "Erreur d'écriture vers la base de donnée");
    			    
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
    				 if(responseCode!=200)
     			    	request.setAttribute("msg", "Erreur d'écriture vers la base de donnée");

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
    			    if(responseCode!=200)
     			    	request.setAttribute("msg", "Erreur d'écriture vers la base de donnée");
    			}
    		}
    		
    		URL url = new URL("http://localhost:8080/pweb/rest/managing/list");
    		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    		conn.setRequestMethod("GET");
    		Map<String, List<String>> header = conn.getHeaderFields();
    		int responseCode = conn.getResponseCode();
    		BufferedReader br = null;
    		String doc ="";
    		if (responseCode == 200) {
    		    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		    String strCurrentLine;
    		        while ((strCurrentLine = br.readLine()) != null) {
    		               doc+=strCurrentLine;
    		        }
    		}
    		
    		ObjectMapper objectMapper = new ObjectMapper();
    		DateFormat df =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z[UTC]'");
    		objectMapper.setDateFormat(df);
    		Distributeur[] dist = objectMapper.readValue(doc, Distributeur[].class);
    		
    		List<Distributeur> app = new ArrayList<Distributeur>();
    		List<Distributeur> ver= new ArrayList<Distributeur>();
    		List<Distributeur> hor= new ArrayList<Distributeur>();
    		List<Distributeur> tri= new ArrayList<Distributeur>();
    		
    		for(Distributeur d : dist) {
    			Rapport r = null;
    			for(Rapport r2:d.get_rapports()) {
    				if(r==null)
    					r=r2;
    				else {
    					if(r.get_date().before(r2.get_date()))
    						r=r2;
    				}
    			}
    			

    			if (r != null) {
        			
    				Set<Rapport> rap = new HashSet<>();
    				rap.add(r);
    				d.set_rapports(rap);
    				
    				int indice=0;
        			for(Distributeur d2:tri) {
        				Rapport rapp= d2.get_rapports().iterator().next();
        				if(rapp.get_montant()>r.get_montant())
        					indice+=1;
        			}
        			tri.add(indice,d);
    				
    				if(r.get_statut().equals("hors service"))
    					hor.add(d);
    				
    				if(!r.get_etat().contentEquals("ok"))
    					ver.add(d);
    				
    				if(r.get_temperature()<=5 && d.get_type().contentEquals("boissons chaudes")) {
    					for(Article a:r.get_contenu()) {
    						if(a.get_quantite()<30) {
    							app.add(d);
    							break;
    						}
    					}
    				}
    				else if(r.get_temperature()>=25 && d.get_type().contentEquals("boissons froides")){
    					for(Article a:r.get_contenu()) {
    						if(a.get_quantite()<30) {
    							app.add(d);
    							break;
    						}
    					}
    				}
    				else {
    					for(Article a:r.get_contenu()) {
    						if(a.get_quantite()<10) {
    							app.add(d);
    							break;
    						}
    					}
    				}
    					
    			}
    		}
    		
    		request.setAttribute("app", app);
    		request.setAttribute("ver", ver);
    		request.setAttribute("hor", hor);
    		request.setAttribute("tri", tri);
    		
    		ServletContext context = getServletContext();
    		RequestDispatcher dispatcher = context.getRequestDispatcher("/gestion.jsp");
    		dispatcher.forward(request, response);


    }


}

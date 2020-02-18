package hib;

import util.HibernateUtil;


import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class DistributeurManagerServlet extends HttpServlet {

    private final SimpleDateFormat dateFormatter =
                            new SimpleDateFormat("dd.MM.yyyy");

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Begin unit of work
            HibernateUtil.getSessionFactory()
                    .getCurrentSession().beginTransaction();

            // Write HTML header
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Distributeur Manager</title></head><body>");

            // Handle actions
            if ( "store".equals(request.getParameter("action")) ) {

                String serie = request.getParameter("serie");
                String type = request.getParameter("type");

                if ( "".equals(serie) || "".equals(type) ) {
                    out.println("<b><i>Please enter serie number and type.</i></b>");
                } else {
                    createAndStoreDistributeur(serie, type);
                    out.println("<b><i>Added distributeur.</i></b>");
                }
            }

            // Print page
            printEventForm(out);
            listEvents(out);

            // Write HTML footer
            out.println("</body></html>");
            out.flush();
            out.close();

            // End unit of work
            HibernateUtil.getSessionFactory()
                    .getCurrentSession().getTransaction().commit();

        } catch (Exception ex) {
            HibernateUtil.getSessionFactory()
                    .getCurrentSession().getTransaction().rollback();
            throw new ServletException(ex);
        }
    }

    private void printEventForm(PrintWriter out) {
        out.println("<h2>Add new event:</h2>");
        out.println("<form>");
        out.println("N serie: <input name='serie' length='50'/><br/>");
        out.println("Type: <input name='type' length='20'/><br/>");
        out.println("<input type='submit' name='action' value='store'/>");
        out.println("</form>");
    }

    private void listEvents(PrintWriter out) {
        List result = HibernateUtil.getSessionFactory()
                        .getCurrentSession().createCriteria(Distributeur.class).list();
        if (result.size() > 0) {
            out.println("<h2>Events in database:</h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Event title</th>");
            out.println("<th>Event date</th>");
            out.println("</tr>");
            for (Iterator it = result.iterator(); it.hasNext();) {
                Distributeur dst = (Distributeur) it.next();
                out.println("<tr>");
                out.println("<td>" + dst.get_serie() + "</td>");
                out.println("<td>" + dst.get_type()+ "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
    }

    protected void createAndStoreDistributeur(String serie, String type) {
        Distributeur theEvent = new Distributeur(serie, type);

        HibernateUtil.getSessionFactory()
                        .getCurrentSession().save(theEvent);
    }

}

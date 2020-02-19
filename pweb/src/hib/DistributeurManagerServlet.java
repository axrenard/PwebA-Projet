package hib;

import util.HibernateUtil;


import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class DistributeurManagerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Begin unit of work
            HibernateUtil.getSessionFactory()
                    .getCurrentSession().beginTransaction();

            request.setAttribute("msg", "");

            // Handle actions
            if ( "store".equals(request.getParameter("action")) ) {

                String serie = request.getParameter("serie");
                String type = request.getParameter("type");

                if ( "".equals(serie) || "".equals(type) ) {
                	request.setAttribute("msg", "Please enter serie number and type.");
                } else {
                	request.setAttribute("msg", "Added distributeur.");
                    createAndStoreDistributeur(serie, type);
                }
            }
            
            if ( "delete".equals(request.getParameter("action")) ) {

                String serie = request.getParameter("serie");

                if ( "".equals(serie) ) {
                	request.setAttribute("msg", "Please enter serie number");
                } else {
                	request.setAttribute("msg", "Deleted distributeur.");
                    DeleteDistributeur(serie);
                }
            }
            request.setAttribute("liste",listEvents());
            
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/gestion.jsp");
            dispatcher.forward(request, response);


            // End unit of work
            HibernateUtil.getSessionFactory()
                    .getCurrentSession().getTransaction().commit();

        } catch (Exception ex) {
            HibernateUtil.getSessionFactory()
                    .getCurrentSession().getTransaction().rollback();
            throw new ServletException(ex);
        }
    }


    private List<Distributeur> listEvents() {
        List<Distributeur> result = HibernateUtil.getSessionFactory()
                        .getCurrentSession().createCriteria(Distributeur.class).list();
        return result;
    }

    protected void createAndStoreDistributeur(String serie, String type) {
        Distributeur theEvent = new Distributeur(serie, type);

        HibernateUtil.getSessionFactory()
                        .getCurrentSession().save(theEvent);
    }
    
    protected void DeleteDistributeur(String serie) {
        HibernateUtil.getSessionFactory()
                .getCurrentSession().createQuery("delete from Distributeur where serie = :serie")
                .setParameter("serie", serie)
                .executeUpdate();;

    }

}

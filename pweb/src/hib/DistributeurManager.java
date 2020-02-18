package hib;

import org.hibernate.*;
import org.hibernate.criterion.Expression;

import java.util.*;

import util.HibernateUtil;

public class DistributeurManager {

    public static void main(String[] args) {
        DistributeurManager mgr = new DistributeurManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreDistributeur("My Event", new Date());
        }
        else if (args[0].equals("list")) {
            List events = mgr.listEvents();
            for (int i = 0; i < events.size(); i++) {
                Event theEvent = (Event) events.get(i);
                System.out.println("Event: " + theEvent.getTitle() +
                                   " Time: " + theEvent.getDate());
            }
        }


        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreDistributeur(String title, Date theDate) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);

        session.save(theEvent);

        session.getTransaction().commit();

        return theEvent.getId();
    }


    private List listDistributeur() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createQuery("from Event").list();

        session.getTransaction().commit();

        return result;
    }


}

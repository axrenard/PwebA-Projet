package hib;

import org.hibernate.*;
import org.hibernate.criterion.Expression;

import java.util.*;

import util.HibernateUtil;

public class DistributeurManager {

    public static void main(String[] args) {
        DistributeurManager mgr = new DistributeurManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreDistributeur(args[1], args[2]);
        }
        else if (args[0].equals("list")) {
            List distribs = mgr.listDistributeur();
            for (int i = 0; i < distribs.size(); i++) {
                Distributeur thedistrib = (Distributeur) distribs.get(i);
                System.out.println("Event: " + thedistrib.get_serie() +
                                   "Type:"+thedistrib.get_type());
            }
        }


        HibernateUtil.getSessionFactory().close();
    }

    private String createAndStoreDistributeur(String serie, String type) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Distributeur thedistrib = new Distributeur(serie,type);

        session.save(thedistrib);

        session.getTransaction().commit();

        return thedistrib.get_serie();
    }


    private List listDistributeur() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createQuery("from Distributeur").list();

        session.getTransaction().commit();

        return result;
    }


}

package hib;

import util.HibernateUtil;


import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.query.Query;



public class DistributeurManagerServlet {   


    static List<Distributeur> listEvents() {
    	CriteriaBuilder builder = HibernateUtil.getSessionFactory().getCurrentSession().getCriteriaBuilder();
    	CriteriaQuery<Distributeur> criteria = builder.createQuery(Distributeur.class);
    	criteria.from(Distributeur.class);
    	List<Distributeur> contacts = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(criteria).getResultList();

        return contacts;
    }

    static void createAndStoreDistributeur(String serie, String type, String adr, String emp, String lon, String lat, String inte, String com) throws ParseException {
        Distributeur theEvent = new Distributeur(serie, type);

        if(!"_".equals(adr)) {
        	theEvent.set_adresse(adr);
        }
        if(!"_".equals(emp)) {
        	theEvent.set_emplacement(emp);
        }
        if(!"_".equals(lon)) {
        	Float longu = Float.parseFloat(lon);
        	theEvent.set_longitude(longu);
        }
        if(!"_".equals(lat)) {
            Float lati = Float.parseFloat(lat);
        	theEvent.set_latitude(lati);
        }
        if(!"_".equals(inte)) {
            Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(inte);
        	theEvent.set_intervention(date1);
        }
        if(!"_".equals(com)) {
        	theEvent.set_commentaire(com);
        }

        HibernateUtil.getSessionFactory()
                        .getCurrentSession().save(theEvent);
    }
    
    static void DeleteDistributeur(String serie) {
         Query q =HibernateUtil.getSessionFactory()
                .getCurrentSession().createQuery("from Distributeur where serie = :serie")
                .setParameter("serie", serie);
        Distributeur dst = (Distributeur)q.list().get(0);
        HibernateUtil.getSessionFactory()
        .getCurrentSession().delete(dst);

    }
    static void ModifyDistributeur(String serie, String adr, String emp, String lon, String lat, String inte, String com) throws ParseException {
        String query = "update from Distributeur set ";
        if(!"_".equals(adr)) {
        	query+= " adresse = :adr ";
        }
        if(!"_".equals(emp)) {
        	if(!"update from Distributeur set ".equals(query)) {
        		query+= ",";
        	}
        	query+= " emplacement= :emp ";
        }
        if(!"_".equals(lon)) {
        	if(!"update from Distributeur set ".equals(query)) {
        		query+= ",";
        	}
        	query+= " longitude = :lon ";
        }
        if(!"_".equals(lat)) {
            if(!"update from Distributeur set ".equals(query)) {
            	query+= ",";
            }
            query+= " latitude = :lat";
        }
        if(!"_".equals(inte)) {
            if(!"update from Distributeur set ".equals(query)) {
            	query+= ",";
            }
            query+= " intervention = :int ";
        }
        if(!"_".equals(com)) {
        	if(!"update from Distributeur set ".equals(query)) {
        		query+= ",";
        	}
        	query+= " commentaire = :com ";
        }
    	query+= " where serie = :serie ";

        Query q = HibernateUtil.getSessionFactory()
                .getCurrentSession().createQuery(query)
                .setParameter("serie", serie);
    	if(!"_".equals(adr)) {
    		q.setParameter("adr",adr);
        }
        if(!"_".equals(emp)) {
        	q.setParameter("emp",emp);
        }
        if(!"_".equals(lon)) {
        	Float longu = Float.parseFloat(lon);
        	q.setParameter("lon",longu);
        }
        if(!"_".equals(lat)) {
        	Float lati = Float.parseFloat(lat);
        	q.setParameter("lat",lati);
        }
        if(!"_".equals(inte)) {
        	Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(inte);
        	q.setParameter("int",date1);
        }
        if(!"_".equals(com)) {
        	q.setParameter("com",com);
        }
                q.executeUpdate();
    }

}

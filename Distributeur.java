package hib;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Distributeur {
	private String serie;
	private String type;
	private String adresse;
	private String emplacement;
	private Long longitude;
	private Long latitude;
	private Date intervention;
	private String commentaire;
	private Set<Rapport>rapports = new HashSet<>();

	
	public Distributeur(String s,String t,String a,String e, Long lo,Long la,Date i,String c) {
		serie = s;
		type =t;
		adresse = a;
		emplacement = e;
		longitude = lo;
		latitude = la;
		intervention = i;
		commentaire = c;
	}
	
	
	
	
}

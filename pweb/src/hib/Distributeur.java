package hib;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Distributeur {
	private String serie;
	private String type;
	private String adresse;
	private String emplacement;
	private Float longitude;
	private Float latitude;
	private Date intervention;
	private String commentaire;
	private Set<Rapport>rapports = new HashSet<>();

	public Distributeur() {
		
	}
	
	public Distributeur(String s,String t) {
		serie = s;
		type =t;
	}
	
	public String get_serie() {
		return serie;
	}
	
	public Date get_intervention() {
		return intervention;
	}
	
	public String get_type() {
		return type;
	}
	
	public String get_adresse() {
		return adresse;
	}
	
	public String get_emplacement() {
		return emplacement;
	}
	
	public Float get_longitude() {
		return longitude;
	}
	
	public Float get_latitude() {
		return latitude;
	}
		
	public String get_commentaire() {
		return commentaire;
	}
	
	public Set<Rapport> get_rapports() {
		return rapports;
	}
	
	
	public void set_serie(String s) {
		serie= s;
	}
	
	public void set_type(String t) {
		type = t;
	}
	
	public void set_adresse(String adr) {
		adresse = adr;
	}
	
	public void set_emplacement(String em) {
		emplacement = em;
	}
	
	public void set_longitude(Float l) {
		longitude =l;
	}
	
	public void set_latitude(Float l) {
		latitude =l;
	}
	
	public void set_intervention(Date i) {
		intervention =i;
	}
	
	public void set_commentaire(String c) {
		commentaire =c;
	}
	
	public void set_rapports(Set<Rapport> s) {
		rapports =s;
	}
	
}

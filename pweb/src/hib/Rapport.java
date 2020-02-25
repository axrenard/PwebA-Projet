package hib;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Rapport {
	private Integer id;
	private String serie;
	private Date date;
	private String statut;
	private String etat;
	private Float temperature;
	private String piece;
	private String puce;
	private String sanscontact;
	private String erreurs;
	private Set<Article>contenu =new HashSet<>();
	private Float montant;
	
	public Rapport(String s, Date d, String st, String e, Float t, String p, String pu, String sans, String er, Set<Article> c, Float m) {
		serie = s;
		date = d;
		statut = st;
		etat = e;
		temperature = t;
		piece =p;
		puce = p;
		sanscontact = sans;
		erreurs = er;
		contenu = c;
		montant = m;
	}
	
	public Integer get_id() {
		return id;
	}
	
	public Rapport() {
		
	}
	
	public String get_serie() {
		return serie;
	}
	
	public Date get_date() {
		return date;
	}
	
	public String get_statut() {
		return statut;
	}
	
	public String get_etat() {
		return etat;
	}
	
	public Float get_temperature() {
		return temperature;
	}
	
	public String get_piece() {
		return piece;
	}
	
	public String get_puce() {
		return puce;
	}
	
	public String get_sanscontact() {
		return sanscontact;
	}
	
	public String get_erreurs() {
		return erreurs;
	}
	
	public Set<Article> get_contenu() {
		return contenu;
	}
	
	public Float get_montant() {
		return montant;
	}
	
	public void set_serie(String s) {
		serie=s;
	}
	
	public void set_date(Date s) {
		date=s;
	}

	public void set_statut(String s) {
		statut=s;
	}
	
	public void set_etat(String s) {
		etat=s;
	}
	
	public void set_temperature(Float s) {
		temperature=s;
	}
	
	public void set_piece(String s) {
		piece=s;
	}
	
	public void set_puce(String s) {
		puce=s;
	}
	
	public void set_sanscontact(String s) {
		sanscontact=s;
	}
	
	public void set_montant(Float s) {
		montant=s;
	}
	
	public void set_erreurs(String e) {
		erreurs=e;
	}
	
	public void set_id(Integer s) {
		id=s;
	}
	
}

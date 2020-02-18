package hib;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Rapport {
	private String serie;
	private Date date;
	private String statut;
	private String etat;
	private Long temperature;
	private String piece;
	private String puce;
	private String sanscontact;
	private Set<String>erreurs = new HashSet<>();
	private Set<Article>contenu =new HashSet<>();
	private Long montant;
	
	public Rapport(String s, Date d, String st, String e, Long t, String p, String pu, String sans, Set<String> er, Set<Article> c, Long m) {
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
	
	public Long get_temperature() {
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
	
	public Set<String> get_erreurs() {
		return erreurs;
	}
	
	public Set<Article> get_contenu() {
		return contenu;
	}
	
	public Long get_montant() {
		return montant;
	}
}

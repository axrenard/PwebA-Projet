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
	private Set<String>erreurs = new HashSet<>();
	private Set<Article>contenu =new HashSet<>();
	private Float montant;
	
	public Rapport(String s, Date d, String st, String e, Float t, String p, String pu, String sans, Set<String> er, Set<Article> c, Float m) {
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
	
	public Set<String> get_erreurs() {
		return erreurs;
	}
	
	public Set<Article> get_contenu() {
		return contenu;
	}
	
	public Float get_montant() {
		return montant;
	}
}

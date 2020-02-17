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
}

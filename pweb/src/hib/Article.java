package hib;

public class Article {
	private Integer id;
	private Integer idrapport;
	private String ref;
	private Integer quantite;
	
	public Article(String r, Integer q) {
		ref = r;
		quantite = q;
	}
	
	public Article() {
		
	}
	
	public Integer get_id() {
		return id;
	}
	
	public Integer get_idrapport() {
		return idrapport;
	}
	
	public String get_ref() {
		return ref;
	}
	
	public Integer get_quantite() {
		return quantite;
	}
	
	public void set_id(Integer s) {
		id=s;
	}
	
	public void set_idrapport(Integer s) {
		idrapport=s;
	}
	
	public void set_ref(String s) {
		ref=s;
	}
	
	public void set_quantite(Integer s) {
		quantite=s;
	}
}

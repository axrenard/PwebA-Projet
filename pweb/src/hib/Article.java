package hib;

public class Article {
	private Integer id;
	private String ref;
	private Integer quantite;
	
	public Article(Integer i,String r, Integer q) {
		ref = r;
		quantite = q;
	}
	
	public Article() {
		
	}
	
	public Integer get_id() {
		return id;
	}
	
	public String get_ref() {
		return ref;
	}
	
	public Integer get_quantite() {
		return quantite;
	}
}

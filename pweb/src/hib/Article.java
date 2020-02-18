package hib;

public class Article {
	private String ref;
	private Integer quantite;
	
	public Article(String r, Integer q) {
		ref = r;
		quantite = q;
	}
	
	public String get_ref() {
		return ref;
	}
	
	public Integer get_quantite() {
		return quantite;
	}
}

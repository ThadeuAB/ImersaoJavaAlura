package Aula03;

import Aula04.ExtratorAPILocal;

public enum API {
	
	IMDB_TOP_MOVIES("https://imdb-api.com/en/API/Top250Movies/k_3yik16ht", new ExtratorImdb()),
	NASA("https://api.nasa.gov/planetary/apod?api_key=aw3W3Tkh8igG"
				+ "wVMdDjv0TPwMzdeUkhTpj5fE1eqX&start_date=2022-06-14&end_d"
				+ "ate=2022-06-20", new ExtratorNasa()),
	API_LOCAL("http://localhost:8080/linguagens", new ExtratorAPILocal());
	
	private String url;
	private ExtratorConteudo extrator;
	


	API(String url, ExtratorConteudo extrator ){
		this.url = url;
		this.extrator = extrator;
	}
	
	public String getUrl() {
		return url;
	}
	
	public ExtratorConteudo getExtrator() {
		return extrator;
	}

}

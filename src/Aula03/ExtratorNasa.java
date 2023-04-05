package Aula03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Aula01.JsonParser;

public class ExtratorNasa implements ExtratorConteudo {
	
	public List<Conteudo>extraiConteudos(String json){
		
		var parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);
		
		return listaDeAtributos.stream().map(atributos -> new Conteudo
				(atributos.get("title"),atributos.get("url")))
			.toList();
		
	}

}

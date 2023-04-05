package Aula03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Aula01.JsonParser;

public class ExtratorImdb implements ExtratorConteudo {
	
public List<Conteudo>extraiConteudos(String json){
		
		var parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);
		
		return listaDeAtributos.stream().map(atributos -> new Conteudo
				(atributos.get("title"),atributos.get("image").replaceAll("(@+)(.*).jpg$","$1.jpg")))
			.toList();
		
		
		
	}

}

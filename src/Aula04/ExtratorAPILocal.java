package Aula04;

import java.util.List;
import java.util.Map;

import Aula01.JsonParser;
import Aula03.Conteudo;
import Aula03.ExtratorConteudo;

public class ExtratorAPILocal implements ExtratorConteudo {
	
	public List<Conteudo>extraiConteudos(String json){
		
		var parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);
		
		return listaDeAtributos.stream().map(atributos -> new Conteudo
				(atributos.get("title"),atributos.get("image")))
			.toList();

	}
}

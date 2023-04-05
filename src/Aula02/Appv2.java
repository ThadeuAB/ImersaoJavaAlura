package Aula02;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import Aula01.JsonParser;

public class Appv2 {

	public static void main(String[] args) throws Exception {
		
		String url = "https://imdb-api.com/en/API/Top250Movies/k_3yik16ht";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		
		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		
		for (Map<String,String> filme: listaDeFilmes) {
			String urlImagem = filme.get("image");
			String titulo = filme.get("title");
			
			String nomeArquivo = "figurinhas/" + titulo + ".png";
			InputStream inputStream = new URL(urlImagem).openStream();
			
			var geradora = new GeradoraDeFigurinhas();
			geradora.cria(inputStream, nomeArquivo,"Topzera");
			
			System.out.println(filme.get("title"));
			System.out.println(filme.get("image"));
			System.out.println(filme.get("imDbRating"));
			System.out.println();
		}

	}

}

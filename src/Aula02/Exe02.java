package Aula02;

import java.io.File;
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

public class Exe02 {
	
public static void main(String[] args) throws Exception {
		
		String url = "https://imdb-api.com/en/API/Top250TVs/k_3yik16ht";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		var diretorio = new File("figurinhas/");
		diretorio.mkdir();
		
		var parser = new JsonParser();
		var geradora = new GeradoraDeFigurinhas();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		for (int index = 0; index < 5; index++) {
			var filme = listaDeFilmes.get(index);
			
			String urlImagem = filme.get("image");
			String imagemGrande[]  = urlImagem.split("@");
			imagemGrande[0] = imagemGrande[0] + "@.jpg";
	
			String titulo = filme.get("title");
								
			
			InputStream inputStream = new URL(imagemGrande[0]).openStream();
			String nomeArquivo = "figurinhas/" + titulo + ".png";
			
			geradora.cria(inputStream, nomeArquivo,"Topzera!!");
			
			System.out.println("\u001b[1m \u001b[31mTitulo: \u001b[0m" + filme.get("title"));
			System.out.println("\u001b[4m \u001b[35mRating: \u001b[0m" + filme.get("imDbRating"));
			double nota = Double.parseDouble(filme.get("imDbRating"));								
			int numEstrelas = (int) nota;
			
			for (int n = 1; n<= numEstrelas; n++) {
				
				System.out.print("⭐");
			}
			
			System.out.println("\n");
			System.out.println("\u001b[3m \u001b[32mPoster: \u001b[0m" + filme.get("image"));
			System.out.println("\n");
		}
		
		
	}

}

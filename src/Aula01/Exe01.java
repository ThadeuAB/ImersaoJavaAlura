package Aula01;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Exe01 {

	public static void main(String[] args) throws Exception {
		
			
			String url = "https://imdb-api.com/en/API/Top250TVs/k_3yik16ht";
			URI endereco = URI.create(url);
			var client = HttpClient.newHttpClient();
			var request = HttpRequest.newBuilder(endereco).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String body = response.body();
						
			var parser = new JsonParser();
			List<Map<String, String>> listaDeFilmes = parser.parse(body);
			
			for (Map<String,String> filme: listaDeFilmes) {
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



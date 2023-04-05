package Aula03;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import Aula02.GeradoraDeFigurinhas;

public class Appv3 {
	
public static void main(String[] args) throws Exception {
	
	Scanner scan = new Scanner(System.in);
	
	int escolha = 0;
	String url = "";
	API api = API.NASA;
	
	while (escolha < 1 || escolha > 2) {
		
		System.out.println("\u001b[1m****MENU***\u001b[0m");
		System.out.println("1 - Extrair figurinhas da NASA");
		System.out.println("2 - Extrair figurinhas do IMDB");
		escolha = scan.nextInt();
		
	}
	System.out.println("Digite texto a ser inserido na figurinha: ");
	String texto = scan.next();
	
	if (escolha == 1) {
		
		api = API.NASA;

		
	} else if (escolha == 2) {
		
		api = API.IMDB_TOP_MOVIES;

	}
	
	url = api.getUrl();
	
	var http = new ClienteHttp();
	String json = http.buscaDados(url);
	
	ExtratorConteudo extrator = api.getExtrator();
	List<Conteudo>conteudos = extrator.extraiConteudos(json);
	
	var geradora = new GeradoraDeFigurinhas();
	
	
	for (int index = 0; index <= 4; index++) {
		
		Conteudo conteudo = conteudos.get(index);
		
		InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
		String nomeArquivo = "figurinhas/" + conteudo.titulo() + ".png";
		
		geradora.cria(inputStream, nomeArquivo,texto);
		
		System.out.println("\u001b[1m \u001b[31mTitulo: \u001b[0m" + conteudo.titulo());		
		System.out.println("\n");
		System.out.println("\u001b[3m \u001b[32mImagem: \u001b[0m" + conteudo.urlImagem());
		System.out.println("\n");
	
   }
		
				
	}

}

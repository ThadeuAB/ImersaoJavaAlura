package Aula02;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Exe02B {
	
	public static void main(String[] args) throws Exception {
		
		var geradora = new GeradoraDeFigurinhas();
		InputStream inputStream = new FileInputStream(new File("entrada/capture.jpg"));
		String nomeArquivo = "figura.png";
		geradora.cria(inputStream, nomeArquivo,"Correndo para aula 03");
	}
}

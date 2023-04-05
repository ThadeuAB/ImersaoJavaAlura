package Aula02;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

		
		public void cria(InputStream inputStream, String nomeArquivo, String texto) throws Exception {
			
			//leitura da imagem
			//InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
			//InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYz"
			//		+ "MtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyN"
			//		+ "jAwNDUxODI@.jpg").openStream();
			BufferedImage imagemOriginal = ImageIO.read(inputStream);
			var diretorio = new File("figurinhas/");
			diretorio.mkdir();
			
			//cria nova imagem em memoria com transparencia e com tamanho novo
			int largura = imagemOriginal.getWidth();
			int altura = imagemOriginal.getHeight();
			int novaAltura = altura + 200;
			BufferedImage novaImagem = new BufferedImage(largura, novaAltura,BufferedImage.TRANSLUCENT);
			
			//copiar a imagem original para nova imagem (em memoria)
			Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
			graphics.drawImage(imagemOriginal, 0, 0, null);
			
			//configurar fonte
			var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 80);
			graphics.setFont(fonte);
			graphics.setColor(Color.RED);
						
			// escrever uma frase na nova imagem
			
			FontMetrics fontMetrics = graphics.getFontMetrics();
			Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
			int larguraTexto = (int)retangulo.getWidth();
			int posicaoTextox = (largura - larguraTexto) / 2;
			int posicaoTextoy = (novaAltura - 100);
			graphics.drawString(texto, posicaoTextox, posicaoTextoy);
			
			FontRenderContext fontRenderContext = graphics.getFontRenderContext();
			var textLayout = new TextLayout(texto,fonte, fontRenderContext);
			Shape outline = textLayout.getOutline(null);
			AffineTransform transform = graphics.getTransform();
			transform.translate(posicaoTextox,posicaoTextoy);
			graphics.setTransform(transform);
			var outlineStroke = new BasicStroke(largura * 0.004f);
			graphics.setStroke(outlineStroke);
			graphics.setColor(Color.BLACK);
			graphics.draw(outline);
			graphics.setClip(outline);
			
			// escrever a imagem nova em arquivo
			ImageIO.write(novaImagem, "png", new File(nomeArquivo));
			
			
		}
		
		//public static void main(String[] args) throws Exception {
		//	var geradora = new GeradoraDeFigurinhas();
		//	geradora.cria();
		//}

	}



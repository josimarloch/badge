/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author josimar
 */
public class ImageResizerService {

    private File arquivo;

    public ImageResizerService(File arquivo) {
        this.arquivo = arquivo;
    }

    public byte[] read(String file) throws FileNotFoundException, IOException {
        byte[] buffer = new byte[1024];

        InputStream is = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        while (is.read(buffer) != -1) {
            out.write(buffer);
        }
        try {
            return out.toByteArray();
        } finally {
            is.close();
            out.close();
        }

    }

    public File converteBmpToJPG() throws IOException {

        File out = new File(arquivo.getCanonicalPath() + ".bmp");

        BufferedImage buffer;

        buffer = ImageIO.read(arquivo);
        ImageIO.write(buffer, "JPG", out);
        return out;
    }

    public byte[] getMiniatura() throws IOException {
        return processa(200);
    }

    public byte[] getNormal() throws IOException {
        return processa(800);
    }
    public byte[] getNormal(int largura) throws IOException {
        return processa(largura);
    }
    public int getLarguraOriginal() throws IOException{
          BufferedImage imagem = ImageIO.read(this.arquivo);
        return imagem.getWidth();
    }
    public int getLargura(File foto) throws IOException{
          BufferedImage imagem = ImageIO.read(foto);
        return imagem.getWidth();
    }
    public int getAltura(File foto) throws IOException{
          BufferedImage imagem = ImageIO.read(foto);
        return imagem.getHeight();
    }
    private byte[] processa(int larguraAlvo) throws IOException {

        String file = this.arquivo.getCanonicalPath();
        boolean isPng = file.toUpperCase().endsWith("PNG");
        boolean isJpg = file.toUpperCase().endsWith("JPG");
        boolean isBmp = file.toUpperCase().endsWith("BMP");
        if (!isPng && !isJpg && !isBmp) {
            throw  new IOException("Arquivo Invalido ou nao suportado!");
        }
        BufferedImage imagem = ImageIO.read(arquivo);

        int originalWidth = imagem.getWidth();
        int originalHeight = imagem.getHeight();
        if (larguraAlvo > originalWidth) {
            larguraAlvo = originalWidth;
        }
        int altura_nova = (int) (originalHeight * larguraAlvo) / originalWidth;
        int type = BufferedImage.TYPE_INT_RGB;

        if (isPng) {
            type = BufferedImage.BITMASK;
        }
        BufferedImage new_img = new BufferedImage((int) larguraAlvo, (int) altura_nova, type);
        Graphics2D g = new_img.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(imagem, 0, 0, (int) larguraAlvo, (int) altura_nova, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (isPng) {
            ImageIO.write(new_img, "PNG", out);
        } else {
            ImageIO.write(new_img, "JPG", out);
        }
        try {
            return out.toByteArray();
        } finally {
            out.close();
        }
    }
    public void converterArayByteEmArquivo(File output, byte[] conteudo) throws FileNotFoundException, IOException {
        try (FileOutputStream out = new FileOutputStream(output)) {
            out.write(conteudo, 0, conteudo.length);
            out.flush();
        }
    }
}

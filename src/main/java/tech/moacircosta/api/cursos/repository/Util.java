package tech.moacircosta.api.cursos.repository;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.imageio.ImageIO;
//import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Util {

    public static String codificar(byte[] entrada) {
        Base64.Encoder base64Encoder = Base64.getEncoder();
//        String[] arrayImagem = new String[1];
//        arrayImagem[0] = base64Encoder.encodeToString(img);
//        StringBuilder imageString = new StringBuilder("data:image/jpeg;base64,");
//        imageString.append(base64Encoder.encodeToString(entrada));
        return base64Encoder.encodeToString(entrada);
    }

    public static byte[] decodificar(String imagemString) throws UnsupportedEncodingException {
//        Base64.Decoder base64Decoder = Base64.getDecoder();
//        return base64Decoder.decode(entrada.getBytes("utf-8"));
        return Base64.getMimeDecoder().decode(imagemString.split(",")[1]);
    }

    public static String prepareImagem(String url) {
        String imageString = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage arqRedacaoOrigin = null;
        try{
            arqRedacaoOrigin = ImageIO.read(new File(url));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(arqRedacaoOrigin, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*aqui*/
        byte[] imageAsByte = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encode(imageAsByte).toString();
    }

}

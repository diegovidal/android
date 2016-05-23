package com.dvidal.acessoweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by diegovidal on 22/05/16.
 */
public class Utils {

    public static String bytesToString(InputStream is) throws IOException {
        /*
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream buferFinal = new ByteArrayOutputStream();
        int bytesLidos;

        while ((bytesLidos = is.read(buffer)) != -1){
            buferFinal.write(buffer, 0, bytesLidos);
        }
        return new String(buferFinal.toByteArray(),"UTF-8");
     */

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String linha;

        while ((linha = reader.readLine()) != null ){
            sb.append(linha);
        }
        return  sb.toString();
    }
}

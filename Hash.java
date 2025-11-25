package com.company;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map.Entry;

public class Hash {

    private MessageDigest algoritmo;
    private Hash()
    {
        try {
            algoritmo = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    private static Hash hash = null;

    public synchronized static Hash getInstance()
    {
        if(hash == null )
            hash = new Hash();
        return hash;
    }

    public String toString(String entrada) {
        algoritmo.update(entrada.getBytes());
        byte[] bytes = algoritmo.digest();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
}

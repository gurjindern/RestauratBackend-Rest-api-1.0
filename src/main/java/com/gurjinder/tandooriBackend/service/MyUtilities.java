package com.gurjinder.tandooriBackend.service;

import org.springframework.util.Base64Utils;

import java.util.StringTokenizer;

public class MyUtilities {

    public static String getUsersnameFormAuthToken(String authToken){

        authToken=authToken.replaceFirst("Basic ","");
        byte[] decodeToByteArray= Base64Utils.decodeFromString(authToken);
        String decodedAuthToken=new String(decodeToByteArray);

        StringTokenizer tokenizer=new StringTokenizer(decodedAuthToken,":");
        String username=tokenizer.nextToken().toUpperCase();
        return username;
    }
}

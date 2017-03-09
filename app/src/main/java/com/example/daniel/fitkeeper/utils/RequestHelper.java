package com.example.daniel.fitkeeper.utils;

/**
 * Created by DANIEL on 08/03/2017.
 */

public class RequestHelper {

    public static String composeUrlPath(String entity, String id){
        return Constants.API_URL + entity + "/" + id;
    }

    public static String composeUrlPathWithParam(String entity, String param, String value){
        return Constants.API_URL + entity +  "?" + param + "=" + value;
    }
}

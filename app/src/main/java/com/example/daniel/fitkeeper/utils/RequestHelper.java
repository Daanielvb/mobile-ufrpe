package com.example.daniel.fitkeeper.utils;

import java.util.List;

/**
 * Created by DANIEL on 08/03/2017.
 */

public class RequestHelper {

    public static String composeUrlPath(String entity, String id) {
        return Constants.API_URL + entity + "/" + id;
    }

    public static String composeUrlPathWithParam(String entity, String param, String value) {
        return Constants.API_URL + entity + "?" + param + "=" + value;
    }

    public static String composeUrlPathWithMultipleParams(String entity, String fixedParam ,
                                                          List<Integer> values){
        String baseStr = Constants.API_URL + entity + "?" + fixedParam + "=" +  values.get(0).toString();
        for( int i = 1; i < values.size(); i++){
            baseStr += "&" + fixedParam + "=" + values.get(i).toString();
        }
        return baseStr;
    }
}

package com.francis.bestroute.utils;

/**
 * Created by zeven on 15/11/10.
 */
public class TagUtil {
    static String network_tag = "volley_";
    static String alc_tag = "ALC_";


    public static String generateNetworkTag(Object o){
        return network_tag + o.getClass().getSimpleName().trim();
    }

    public static String generateALCTag(Object o){
        return alc_tag + o.getClass().getSimpleName().trim();
    }
}

package com.suresh.androidapp.helpers;

import java.io.InputStream;

/**
 * Created by suresh on 11/14/16.
 */

public class WebHelper {

    public static String convertStreamToString(InputStream is){
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}

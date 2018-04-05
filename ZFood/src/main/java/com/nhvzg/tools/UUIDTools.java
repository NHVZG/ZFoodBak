package com.nhvzg.tools;

/**
 * Created by NHVZG on 2018/2/26.
 */
public class UUIDTools {
    public static String  getPrimaryKey(){
        String tempPK = UUID.randomUUID().toString();
        return tempPK.replaceAll("-", "M");
    }
}

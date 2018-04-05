package com.nhvzg.test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NHVZG on 2018/2/26.
 */
public class TestDate {
    @Test
    public void DateFormat() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sd=sdf.format(date);
        Date d=sdf.parse(sd);
        System.out.println(d);
    }

}

package com.dataimport.service;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class DateFormattingService {

    public Calendar format(String date) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formattedDate = dateFormat.parse(date);

        Calendar returnedFormattedDate = new GregorianCalendar();
        returnedFormattedDate.setTime(formattedDate);

        return returnedFormattedDate;

    }
}

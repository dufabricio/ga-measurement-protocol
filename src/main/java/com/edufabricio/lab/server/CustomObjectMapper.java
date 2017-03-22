package com.edufabricio.lab.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CustomObjectMapper extends ObjectMapper {

    public static final String JSON_DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    DateFormat df2 = new SimpleDateFormat(JSON_DEFAULT_DATE_FORMAT);

    public CustomObjectMapper() {
        //getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        setDateFormat(df2);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
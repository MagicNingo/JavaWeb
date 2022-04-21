package com.bus.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJsonDateConvertor implements JsonValueProcessor {
    private String pattern;
    private SimpleDateFormat formatter;

    public MyJsonDateConvertor() {
        pattern = "yyyy-MM-dd";
        formatter = new SimpleDateFormat(pattern);
    }

    public MyJsonDateConvertor(String pattern) {
        this.pattern = pattern;
        formatter = new SimpleDateFormat(pattern);
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        return process(o);
    }

    private Object process(Object o) {
        if (o instanceof Date) {
            return formatter.format((Date)o);
        }
        return o == null ? "" : o;
    }
}

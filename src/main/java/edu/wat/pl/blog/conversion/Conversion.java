package edu.wat.pl.blog.conversion;

import org.springframework.cglib.core.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class Conversion implements Converter{


    @Override
    public Object convert(Object o, Class aClass, Object o1) {
        List<String> result = new ArrayList<String>();
        for (String part : o.toString().split("[\n\t:;, ]")) {
            if (!part.trim().isEmpty()) {
                result.add(part.trim());
            }
        }
        return result;
    }
}

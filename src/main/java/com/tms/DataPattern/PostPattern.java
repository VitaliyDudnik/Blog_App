package com.tms.DataPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostPattern {

    public boolean postDataPattern(String title, String description) {
        if (title != null & !title.trim().isEmpty() & description != null & !description.trim().isEmpty()) {
            final Pattern tit = Pattern.compile("\\w{6,170}");
            final Pattern descrip = Pattern.compile("\\w{6,240}");
            Matcher m = tit.matcher(title);
            Matcher m1 = descrip.matcher(description);
            return m.find() & m1.find();
        }
        return false;
    }


    public boolean postDescriptionPattern(String description) {
        if (description != null & !description.trim().isEmpty()) {
            final Pattern descrip = Pattern.compile("\\w{6,240}");
            Matcher m = descrip.matcher(description);
            return m.find();
        }
        return false;
    }
}

package com.tms.DataPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentPattern {

    public boolean commentPattern(String text) {

        if (text != null) {

            final Pattern t = Pattern.compile("\\w{2,70}");
            Matcher m = t.matcher(text);
            return m.find();
        }
        return false;
    }
}

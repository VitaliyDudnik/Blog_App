package com.tms.DataPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPattern {

    public boolean userDataPattern(String name, String username, String password) {

        if (username != null & !username.trim().isEmpty() &
                password != null & !password.trim().isEmpty() &
                name != null & !name.trim().isEmpty()) {

            final Pattern n = Pattern.compile("(?=\\S+$).{2,16}", Pattern.CASE_INSENSITIVE);
            final Pattern pass = Pattern.compile("(?=.*[0-9])(?=\\S+$).{5,20}", Pattern.CASE_INSENSITIVE);
            final Pattern userN = Pattern.compile("(?=\\S+$).{2,16}", Pattern.CASE_INSENSITIVE);
            Matcher m = pass.matcher(password);
            Matcher m1 = userN.matcher(username);
            Matcher m2 = n.matcher(name);
            return m.find() & m1.find() & m2.find();
        }
        return false;
    }

    public boolean usernamePattern(String username) {

        if (username != null & !username.trim().isEmpty()) {

            final Pattern userN = Pattern.compile("(?=\\S+$).{2,16}", Pattern.CASE_INSENSITIVE);
            Matcher m = userN.matcher(username);
            return m.find();
        }
        return false;
    }

    public boolean passUname(String password, String username) {

        if (username != null & !username.trim().isEmpty() &
                password != null & !password.trim().isEmpty()) {

            final Pattern pass = Pattern.compile("(?=.*[0-9])(?=\\S+$).{5,20}", Pattern.CASE_INSENSITIVE);
            final Pattern userN = Pattern.compile("(?=\\S+$).{2,16}", Pattern.CASE_INSENSITIVE);
            Matcher m = pass.matcher(password);
            Matcher m1 = userN.matcher(username);
            return m.find() & m1.find();
        }
        return false;
    }

    public boolean pass(String password) {

        if (password != null & !password.trim().isEmpty()) {

            final Pattern pass = Pattern.compile("(?=.*[0-9])(?=\\S+$).{5,20}", Pattern.CASE_INSENSITIVE);
            Matcher m = pass.matcher(password);
            return m.find();
        }
        return false;
    }
}



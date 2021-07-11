package com.tms.storage.connection;

public class DbConnectionParameter {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "PASSWORD";

    public String getUrl() { return url; }

    public String getUser() { return user; }

    public String getPassword() { return password; }


}


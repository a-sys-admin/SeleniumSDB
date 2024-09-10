package dev.config;

public class Config {
    
    // Configurable credentials
    private String masterUsername = "master";
    private String devUsername = "dev";
    private String karthickUsername = "kvprasad";
    private String password = "P@ssw0rd"; // Store your password securely

    // Getters for credentials
    public String getMasterUsername() {
        return masterUsername;
    }

    public String getDevUsername() {
        return devUsername;
    }

    public String getKarthickUsername() {
        return karthickUsername;
    }

    public String getPassword() {
        return password;
    }
}


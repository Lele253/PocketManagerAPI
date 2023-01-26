package apis.Manga.API.request;

import lombok.Data;


public class AuthRequest {
    private String email;
    private String password;
    private String username;
    private String farbe;
    private long gesamtBudget;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getGesamtBudget() {
        return gesamtBudget;
    }
}

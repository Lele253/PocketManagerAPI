package apis.Manga.API.request;

import apis.Manga.API.Entety.User;

public class KategorieRequest {
    private long kategorieId;
    private String kategorieName;
    private String kategorieBudget;
    private User user;


    public KategorieRequest(String kategorieName, String kategorieBudget, User user) {
        this.kategorieName = kategorieName;
        this.kategorieBudget = kategorieBudget;
        this.user = user;
    }

    public long getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(long kategorieId) {
        this.kategorieId = kategorieId;
    }

    public String getKategorieName() {
        return kategorieName;
    }

    public void setKategorieName(String kategorieName) {
        this.kategorieName = kategorieName;
    }

    public String getKategorieBudget() {
        return kategorieBudget;
    }

    public void setKategorieBudget(String kategorieBudget) {
        this.kategorieBudget = kategorieBudget;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }




}

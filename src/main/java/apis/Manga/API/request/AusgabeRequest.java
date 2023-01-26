package apis.Manga.API.request;

import apis.Manga.API.Entety.Kategorie;

public class AusgabeRequest {
    private long ausgabeId;
    private String ausgabeName;
    private String ausgabePreis;
    private String ausgabeDatum;
    private Kategorie kategorie;


    public AusgabeRequest(String ausgabeName, String ausgabePreis, String ausgabeDatum) {
        this.ausgabeName = ausgabeName;
        this.ausgabePreis = ausgabePreis;
        this.ausgabeDatum = ausgabeDatum;
    }

    public long getAusgabeId() {
        return ausgabeId;
    }

    public String getAusgabeName() {
        return ausgabeName;
    }

    public String getAusgabePreis() {
        return ausgabePreis;
    }

    public String getAusgabeDatum() {
        return ausgabeDatum;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }
}

package apis.Manga.API.Entety;

import javax.persistence.*;

@Entity
public class Ausgabe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ausgabeId;
    private String ausgabeName;
    private String ausgabePreis;
    private String ausgabeDatum;

    @ManyToOne
    @JoinColumn(name = "kategorieId", referencedColumnName = "kategorieId")
    private Kategorie kategorie;


    public Ausgabe( String ausgabeName, String ausgabePreis, String ausgabeDatum,Kategorie kategorie) {
        this.ausgabeName = ausgabeName;
        this.ausgabePreis = ausgabePreis;
        this.ausgabeDatum = ausgabeDatum;
        this.kategorie = kategorie;
    }

    public Ausgabe() {

    }

    public String getAusgabeName() {
        return ausgabeName;
    }
    public void erzeuger(Kategorie kategorie){
        this.kategorie=kategorie;
    }
    public Kategorie getKategorie() {
        return kategorie;
    }


    public void setAusgabenName(String ausgabeName) {
        this.ausgabeName = ausgabeName;
    }

    public String getAusgabePreis() {
        return ausgabePreis;
    }

    public void setAusgabePreis(String ausgabePreis) {
        this.ausgabePreis = ausgabePreis;
    }

    public String getAusgabeDatum() {
        return ausgabeDatum;
    }

    public void setAusgabeDatum(String ausgabeDatum) {
        this.ausgabeDatum = ausgabeDatum;
    }
}




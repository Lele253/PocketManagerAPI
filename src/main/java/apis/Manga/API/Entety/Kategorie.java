package apis.Manga.API.Entety;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kategorie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long kategorieId;
    private String kategorieName;
    private String kategorieBudget;




    @OneToMany(mappedBy = "kategorie")
    private Set<Ausgabe> ausgabe = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "nutzerId", referencedColumnName = "nutzerId")
    private User user;

    public Kategorie(String kategorieName, String kategorieBudget, User user) {
        this.kategorieName = kategorieName;
        this.kategorieBudget = kategorieBudget;
        this.user = user;
    }

    public Kategorie() {

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

    public User getUser() {
        return user;
    }

    public long getKategorieId() {
        return kategorieId;
    }

    public void add(Kategorie kategorie) {
    }
    public void erzeuger(User user){
        this.user=user;
    }

}


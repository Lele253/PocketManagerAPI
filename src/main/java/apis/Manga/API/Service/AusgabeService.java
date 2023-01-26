package apis.Manga.API.Service;

import apis.Manga.API.Entety.Ausgabe;
import apis.Manga.API.Entety.Kategorie;
import apis.Manga.API.Repository.AusgabeRepository;
import apis.Manga.API.Repository.KategorieRepository;
import apis.Manga.API.request.AusgabeRequest;
import org.springframework.stereotype.Service;

@Service
public class AusgabeService {
    private AusgabeRepository ausgabeRepository;
    private KategorieRepository kategorieRepository;



    public AusgabeService(AusgabeRepository ausgabeRepository, KategorieRepository kategorieRepository) {
        this.ausgabeRepository = ausgabeRepository;
        this.kategorieRepository = kategorieRepository;
    }


    public String erzeugeAusgabe(AusgabeRequest ausgabeRequest, long kategorieId) {
        Kategorie kategorie = kategorieRepository.findById(kategorieId);
        Ausgabe ausgabe = new Ausgabe(ausgabeRequest.getAusgabeName(), ausgabeRequest.getAusgabePreis(),ausgabeRequest.getAusgabeDatum(),ausgabeRequest.getKategorie());
        ausgabe.erzeuger(kategorie);
        ausgabeRepository.save(ausgabe);
        return "ausgabe wurde erfolgreich angelegt";
    }

}

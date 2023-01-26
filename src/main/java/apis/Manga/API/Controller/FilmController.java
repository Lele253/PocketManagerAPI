package apis.Manga.API.Controller;
import java.util.List;
import apis.Manga.API.Entety.Ausgabe;
import apis.Manga.API.Entety.Kategorie;
import apis.Manga.API.Repository.AusgabeRepository;
import apis.Manga.API.Repository.KategorieRepository;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class FilmController {

    private KategorieRepository kategorieRepository;
    private AusgabeRepository ausgabeRepository;


    public FilmController(KategorieRepository kategorieRepository, AusgabeRepository ausgabeRepository) {
        this.kategorieRepository = kategorieRepository;
        this.ausgabeRepository = ausgabeRepository;

    }


    @CrossOrigin
    @GetMapping("/champ")
    public List<Ausgabe> getChamp() {
        return ausgabeRepository.findAll();
    }


    @CrossOrigin
    @GetMapping("/aufgabe/{nutzerId}")
    public Kategorie leseNutzerListe(@PathVariable long nutzerId) {
        Optional<Kategorie> kategorie = Optional.ofNullable(kategorieRepository.findById(nutzerId));
        if (kategorie.isPresent()) {
            return kategorie.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @DeleteMapping("/{filmId}")
    public Boolean deleteOrder(@PathVariable(value = "filmId") Long Id) {
        kategorieRepository.deleteById(Id);
        return true;
    }

    @CrossOrigin
    @GetMapping("/kategorie")
    public List<Kategorie> getClip() {
        return kategorieRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/kategorie/sortiert/{nutzerId}")
    public List<Kategorie> leseNutzerListe(@PathVariable Long nutzerId){
        List<Kategorie> clips = kategorieRepository.findAll();
        return  clips.stream().filter(c->c.getUser().getNutzerId() == nutzerId).collect(Collectors.toList());
    }

    @CrossOrigin
    @GetMapping("/ausgabe/sortiert/{kategorieId}")
    public List<Ausgabe> leseKategorieListe(@PathVariable Long kategorieId){
        List<Ausgabe> clips = ausgabeRepository.findAll();
        return  clips.stream().filter(c->c.getKategorie().getKategorieId() == kategorieId).collect(Collectors.toList());
    }

  /*@PutMapping("/film/{filmId}")
    public void  patchClip(@RequestBody Kategorie kategorieUpdate, @PathVariable Long filmId) {
        Optional<Kategorie> film = kategorieRepository.findById(filmId);
        if (!film.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Kategorie kategorieInstance = film.get();
        kategorieInstance.setTitel(kategorieUpdate.getTitel());
        kategorieInstance.setTitelbild(kategorieUpdate.getTitelbild());
        kategorieInstance.setErscheinungsjahr(kategorieUpdate.getErscheinungsjahr());
        kategorieInstance.setBewertung(kategorieUpdate.getBewertung());
        kategorieInstance.setKommentar(kategorieUpdate.getKommentar());
        kategorieInstance.setWatched((kategorieUpdate.isWatched()));
        kategorieRepository.save(kategorieInstance);
    }*/

    @CrossOrigin
    @GetMapping("/ausgabe")
    public List<Ausgabe> getAusgabe() {
        return ausgabeRepository.findAll();
    }
    }

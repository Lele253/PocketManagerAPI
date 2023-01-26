package apis.Manga.API.Service;

import apis.Manga.API.Entety.Kategorie;
import apis.Manga.API.Entety.User;
import apis.Manga.API.Repository.KategorieRepository;
import apis.Manga.API.Repository.UserRepository;
import apis.Manga.API.request.KategorieRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class KategorieService {
    private KategorieRepository kategorieRepository;
    private  UserRepository userRepository;



    public KategorieService(KategorieRepository kategorieRepository, UserRepository userRepository){
        this.kategorieRepository = kategorieRepository;
        this.userRepository = userRepository;
    }

    public String erzeugeKategorie(KategorieRequest kategorieRequest, long nutzerId) {
        User user = userRepository.findById(nutzerId);
        Kategorie kategorie = new Kategorie(kategorieRequest.getKategorieName(), kategorieRequest.getKategorieBudget(),kategorieRequest.getUser());
        kategorie.erzeuger(user);
        kategorieRepository.save(kategorie);
        return "Film wurde erfolgreich angelegt";
    }


    }


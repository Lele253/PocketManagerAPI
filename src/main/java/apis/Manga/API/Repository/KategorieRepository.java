package apis.Manga.API.Repository;

import apis.Manga.API.Entety.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KategorieRepository extends JpaRepository<Kategorie, Long> {
    Kategorie findById(long kategorieId);
}

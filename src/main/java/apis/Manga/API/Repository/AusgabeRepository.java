package apis.Manga.API.Repository;

import apis.Manga.API.Entety.Ausgabe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AusgabeRepository extends JpaRepository<Ausgabe, Long> {
}

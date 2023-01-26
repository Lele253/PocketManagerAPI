package apis.Manga.API;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class MangaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangaApiApplication.class, args);
	}
}

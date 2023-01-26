package apis.Manga.API.Controller;
import apis.Manga.API.Repository.AusgabeRepository;
import apis.Manga.API.Service.AusgabeService;
import apis.Manga.API.Service.KategorieService;
import apis.Manga.API.Entety.User;
import apis.Manga.API.Repository.UserRepository;
import apis.Manga.API.Security.JwtAuthentificationFilter;
import apis.Manga.API.Security.JwtTokenProvider;
import apis.Manga.API.request.AuthRequest;
import apis.Manga.API.request.AusgabeRequest;
import apis.Manga.API.request.KategorieRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AusgabeService ausgabeService;
    private KategorieService kategorieService;
   private UserRepository userRepository;
   private PasswordEncoder passwordEncoder;
   private AuthenticationManager authenticationManager;
   private JwtTokenProvider jwtTokenProvider;



    public AuthController(AusgabeService ausgabeService, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, KategorieService kategorieService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.kategorieService = kategorieService;
        this.ausgabeService = ausgabeService;

    }

    @CrossOrigin
    @PostMapping(value = "/regist")
    public ResponseEntity<User> register(@RequestBody AuthRequest authRequest){
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());
        if(userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setUsername(authRequest.getUsername());
        user.setFarbe(authRequest.getFarbe());

        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        User created = userRepository.save(user);
        return ResponseEntity.ok(created);

    }
    @CrossOrigin
    @GetMapping("/user")
    public Optional<User> getUser(){
        return userRepository.findByEmail(jwtTokenProvider.getUserMailFromToken( JwtAuthentificationFilter.x) );
    }
    @CrossOrigin
    @GetMapping("/user/all/{nutzerId}")
    public User leseNutzerListe(@PathVariable long nutzerId){
        Optional<User> user = Optional.ofNullable(userRepository.findById(nutzerId));
        if(user.isPresent()){
            return user.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/user/all")
    public List<User> getUserAll(){
        return userRepository.findAll();
    }


    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail() ,
                        authRequest.getPassword()
                )
        );
        return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
    }

    @CrossOrigin
    @PutMapping("user/{nutzerId}")
    public void  patchUser(@RequestBody User userUpdate, @PathVariable Long nutzerId){
        Optional<User> user = userRepository.findById(nutzerId);
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User userInstance = user.get();
        userInstance.setFarbe(userUpdate.getFarbe());
        userRepository.save(userInstance);
    }

    @CrossOrigin
    @DeleteMapping("/user/all/{nutzerId}")
    public Boolean deleteOrder1( @PathVariable (value = "nutzerId") Long Id) {
        userRepository.deleteById(Id);
        return true;
    }
    @CrossOrigin
    @PutMapping("user/all/{nutzerId}")
    public void  patchUser1(@RequestBody User userUpdate, @PathVariable Long nutzerId){
        Optional<User> user = userRepository.findById(nutzerId);
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User userInstance = user.get();
        userInstance.setUsername(userUpdate.getUsername());

        userRepository.save(userInstance);
    }
    @CrossOrigin
    @PostMapping(path="/kategorie/{nutzerId}")
    public String erzeugeKategorie(@RequestBody KategorieRequest kategorieRequest, @PathVariable long nutzerId){
        return kategorieService.erzeugeKategorie(kategorieRequest,nutzerId);
    }

    @CrossOrigin
    @PostMapping(path = "/ausgabe/{kategrieId}")
    public String erstelleAusgabe(@RequestBody AusgabeRequest ausgabeRequest, @PathVariable long kategrieId) {
        return ausgabeService.erzeugeAusgabe(ausgabeRequest, kategrieId);
    }



  /*  @CrossOrigin(origins = "http://ratetherank.com")
    @GetMapping("/clip/{nutzerId}")
    public Clip ladeClip(@PathVariable Long nutzerId){
        return clipService.ladeClip(nutzerId);
    }*/


}
 /*  @GetMapping("/clip/{Id}")
    public Clip leseNutzerListe(@PathVariable Long Id){
        Optional<Clip> clip = clipRepository.findById(Id);
        if(clip.isPresent()){
            return clip.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }*/


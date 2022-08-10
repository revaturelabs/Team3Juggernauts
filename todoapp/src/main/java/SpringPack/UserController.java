package SpringPack;

import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    @RestController
    @RequestMapping("produce")
    public class UserController {

        @Value("${server.port}")
        int serverPort;

        private UserRepo userRepo;

        @Autowired
        public void setProduceRepository(UserRepo userRepo) {
            this.userRepo = userRepo;
        }

        private List<User> users = new ArrayList<>(Arrays.asList(
                new User(1, "password1", "John@gmail.com" ),
                new User(2, "password2", "Jane@gmail.com")
        ));

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity getAllUser(){
            List<UserRepo> user = userRepo.findAll();
            return ResponseEntity.ok(user);
        }

        @GetMapping(path="{email}")
        public ResponseEntity getProduceById(@PathVariable("email") String email) {
            Optional<User> user = userRepo.findByName(email);

            if(user.isPresent()) {
                return ResponseEntity.ok(user.get());
            }

            return ResponseEntity.notFound().build();
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
            users.add(user);
            return ResponseEntity.created(new URI("http://localhost:"+serverPort+"/user"+user.getId())).build();
        }

        @PutMapping(path="{email}")
        public ResponseEntity updateUser(@RequestBody User user) {
            return ResponseEntity.noContent().build();
        }

//        @GetMapping(value = "/s/price", produces = MediaType.APPLICATION_JSON_VALUE)
//        public ResponseEntity getProduceInPriceRange() {
//            throw new IllegalArgumentException("Price argument is required");
//        }
    }


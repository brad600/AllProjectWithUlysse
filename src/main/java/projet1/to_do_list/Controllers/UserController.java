package projet1.to_do_list.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import projet1.to_do_list.Models.Taches.Taches;
import projet1.to_do_list.Models.User.User;
import projet1.to_do_list.Service.UserService;

import java.util.Set;
import java.util.UUID;

@Service
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

@Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return  ResponseEntity.ok().body( userService.createUser(user));
    }
    @PutMapping("/update/{usermatricule}")
    public ResponseEntity<User> updateUser(@PathVariable("usermatricule") UUID userMatricule , @RequestBody User user){
        return ResponseEntity.ok().body(userService.updateUser(userMatricule, user));
    }
    @DeleteMapping("/{usermatricule}")
    public ResponseEntity<String> deleteUserByMatricule(@PathVariable("usermatricule") UUID userMatricule){
        return ResponseEntity.ok().body(userService.deleteUserByMatricule(userMatricule));
    }
    @GetMapping("/{usermatricule}")
    public ResponseEntity<User> findUserByMatricule(@PathVariable("usermatricule") UUID userMatricule){
        return ResponseEntity.ok().body(userService.findUserByMatricule(userMatricule));
    }

    @GetMapping("/alltaches/{usermatricule}")
    public ResponseEntity<Set<Taches>> getAllTacheOfThisUser(@PathVariable("usermatricule") UUID userMatricule){
        return ResponseEntity.status(200).body(userService.getAllTacheOfThisUser(userMatricule));
    }

}

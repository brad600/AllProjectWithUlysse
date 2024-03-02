package projet1.to_do_list.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import projet1.to_do_list.Models.User.User;
import projet1.to_do_list.Service.UserService;

import java.util.UUID;

@Service
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    public final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.createUser(user);
        return  ResponseEntity.ok().body(user);
    }
    @PutMapping("/update/{usermatricule}")
    public ResponseEntity<User> updateUser(@PathVariable("usermatricule") UUID usermatricule , @RequestBody User user){
        userService.updateUser(usermatricule, user);
        return ResponseEntity.status(201).body(user);
    }
    @DeleteMapping("/{usermatricule}")
    public ResponseEntity<Void> deleteUserByMatricule(@PathVariable("usermatricule") UUID usermatricule){
        userService.deleteUserByMatricule(usermatricule);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{usermatricule}")
    public ResponseEntity<User> findUserByMatricule(@PathVariable("usermatricule") UUID usermatricule){
        userService.findUserByMatricule(usermatricule);
        return ResponseEntity.ok().build();
    }


}

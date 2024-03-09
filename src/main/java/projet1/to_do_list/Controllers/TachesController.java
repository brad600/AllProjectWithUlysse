package projet1.to_do_list.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import projet1.to_do_list.Models.Taches.Statut;
import projet1.to_do_list.Models.Taches.Taches;
import projet1.to_do_list.Models.User.User;
import projet1.to_do_list.Repository.TachesRepo;
import projet1.to_do_list.Service.TachesService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController

@RequiredArgsConstructor
@RequestMapping("/tache")
@Service
public class TachesController {

@Autowired
    private  TachesService tachesService;

    @PostMapping("/create")
    public String createTache(@RequestBody Taches taches){
        return  tachesService.createTache(taches);
    }


    @PutMapping("/associated/{userid}/{idtaches}")
    public String associatedTaskToUserById(@PathVariable("userid") int id, @PathVariable("idtaches")int taches){
        return tachesService.associatedTaskToUserById(id, taches);
    }


    @PutMapping("/update/{idTaches}")
    public ResponseEntity<Taches> updateTaches(@PathVariable("idTaches") int id , @RequestBody Taches taches){
        return ResponseEntity.status(201).body(tachesService.updateTaches(id, taches));
    }


    @DeleteMapping("/{idTaches}")
    public ResponseEntity<String> deleteTacheById(@PathVariable("idTaches") int id){
        return ResponseEntity.status(200).body(tachesService.deleteTacheById(id));
    }


    @GetMapping("/{idTaches}")
    public ResponseEntity<Taches> findTaskById(@PathVariable("idTaches") int id){
        return ResponseEntity.ok().body(tachesService.findTaskByMatricule(id));
    }

    @GetMapping("/allTaches")
    public ResponseEntity<List<Taches>> getAllTaches(){
        return ResponseEntity.ok().body(tachesService.getAllTaches());
    }
    @GetMapping("/numberOfTaches")
    public ResponseEntity<Long> getNumberOfTaches(){
        return ResponseEntity.ok().body(tachesService.getNumberOfTaches());
    }
    @GetMapping("/allTaches/{statutTache}")
    public ResponseEntity<List<Taches>> getTachesByStatut(@PathVariable("statutTache") Statut statut){
        return ResponseEntity.ok().body(tachesService.getTachesByStatut(statut));
    }

}

package projet1.to_do_list.Controllers;

import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet1.to_do_list.Service.TachesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tache")
@Service
public class TachesController {
    @Autowired
    private final TachesService tachesService;
}

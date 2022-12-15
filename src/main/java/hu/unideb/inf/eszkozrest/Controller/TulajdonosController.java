package hu.unideb.inf.eszkozrest.Controller;


import hu.unideb.inf.eszkozrest.Entity.TulajdonosEntity;
import hu.unideb.inf.eszkozrest.Repository.EszkozRepository;
import hu.unideb.inf.eszkozrest.Repository.TulajdonosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TulajdonosController {

    @Autowired
    EszkozRepository eszkozRepository;

    @Autowired
    TulajdonosRepository tulajdonosRepository;

    @GetMapping("/tulajdonosok")
    public ResponseEntity<List<TulajdonosEntity>> getAllTulajdonos() {
        return new ResponseEntity<>(tulajdonosRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/tulajdonos")
    public ResponseEntity<TulajdonosEntity> getTulajdonos(@RequestParam(required = true) long id) {
        return new ResponseEntity<>(tulajdonosRepository.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/tulajdonos")
    public ResponseEntity<TulajdonosEntity> saveTulajdonos(@RequestBody TulajdonosEntity auto) {
        return new ResponseEntity<>(tulajdonosRepository.save(auto), HttpStatus.CREATED);
    }

    @DeleteMapping("/tulajdonos")
    public ResponseEntity deleteTulajdonos(@RequestParam(required = true) long id) {
        tulajdonosRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/tulajdonos")
    public ResponseEntity<TulajdonosEntity> updateTulajdonos(@RequestBody TulajdonosEntity tulaj, @RequestParam long id) {
        TulajdonosEntity tulajdonosEntity = tulajdonosRepository.getOne(id);
        tulajdonosEntity.setNev(tulaj.getNev());
        tulajdonosEntity.setSzemelyi(tulaj.getSzemelyi());
        tulajdonosEntity.setSzuldatum(tulaj.getSzuldatum());
        return new ResponseEntity<>(tulajdonosRepository.save(tulajdonosEntity), HttpStatus.CREATED);
    }

}

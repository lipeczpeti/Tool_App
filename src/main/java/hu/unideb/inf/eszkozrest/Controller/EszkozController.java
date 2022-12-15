package hu.unideb.inf.eszkozrest.Controller;

import hu.unideb.inf.eszkozrest.Dto.EszkozDto;
import hu.unideb.inf.eszkozrest.Entity.EszkozEntity;
import hu.unideb.inf.eszkozrest.Repository.EszkozRepository;
import hu.unideb.inf.eszkozrest.Service.EszkozService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class EszkozController {

    @Autowired
    EszkozService eszkozService;

    @Autowired
    EszkozRepository eszkozRepository;

    @GetMapping("/eszkoz")
    public ResponseEntity<EszkozDto> getEszkoz(@RequestParam long id) {
        return new ResponseEntity<>(
                eszkozService.findById(id)
                , HttpStatus.OK);
    }

    @PostMapping("/eszkozInit")
    public ResponseEntity<List<EszkozDto>> saveEszkozok() {
        List<EszkozDto> eszkozok = Arrays.asList(
                new EszkozDto("Huawei", "Nova 5", "fehér telefon", "telefon", null),
                new EszkozDto("Huawei", "MatePad 11", "betört kijelző", "tablet", null),
                new EszkozDto("Apple", "iPhone XS", "akkumulátor probléma", "telefon", null)
        );
        return new ResponseEntity<>(eszkozService.saveAll(eszkozok), HttpStatus.CREATED);
    }

    @GetMapping("/eszkozok")
    public ResponseEntity<List<EszkozDto>> getEszkozok() {
        try {
            List<EszkozDto> eszkozok = eszkozService.listAll();
            if (eszkozok.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(eszkozok, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/eszkoz")
    public ResponseEntity<EszkozEntity> saveEszkoz(@RequestBody EszkozEntity eszkoz) {
        return new ResponseEntity<>(eszkozRepository.save(eszkoz), HttpStatus.CREATED);
    }

    @DeleteMapping("/eszkoz")
    public ResponseEntity deleteEszkoz(@RequestParam(required = true) long id) {
        eszkozRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/eszkoz")
    public ResponseEntity<EszkozEntity> updateEszkoz(@RequestBody EszkozEntity eszkoz, @RequestParam long id) {
        EszkozEntity eszkozEntity = eszkozRepository.getOne(id);
        eszkozEntity.setMarka(eszkoz.getMarka());
        eszkozEntity.setTipus(eszkoz.getTipus());
        eszkozEntity.setLeiras(eszkoz.getLeiras());
        eszkozEntity.setJelleg(eszkoz.getJelleg());

        return new ResponseEntity<>(eszkozRepository.save(eszkozEntity), HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        return null;
    }


}

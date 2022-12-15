package hu.unideb.inf.eszkozrest.Service;

import hu.unideb.inf.eszkozrest.Dto.TulajdonosDto;
import hu.unideb.inf.eszkozrest.Entity.EszkozEntity;
import hu.unideb.inf.eszkozrest.Entity.TulajdonosEntity;
import hu.unideb.inf.eszkozrest.Repository.EszkozRepository;
import hu.unideb.inf.eszkozrest.Repository.TulajdonosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TulajdonosService {

    @Autowired
    private TulajdonosRepository tulajdonosRepository;

    @Autowired
    private EszkozRepository eszkozRepository;

    public List<TulajdonosDto> findAll() {
        List<TulajdonosEntity> allOwners = tulajdonosRepository.findAll();
        List<TulajdonosDto> finalOwners = new ArrayList<>();

        for (TulajdonosEntity owner : allOwners) {
            TulajdonosDto newOwner = new TulajdonosDto();
            newOwner.setId(owner.getId());
            newOwner.setNev(owner.getNev());
            newOwner.setSzemelyi(owner.getSzemelyi());
            newOwner.setSzuldatum(owner.getSzuldatum());
            finalOwners.add(newOwner);
        }

        return finalOwners;

    }

    public void addOwner(long ownerId, long toolId) {
        Optional<TulajdonosEntity> tulajdonosEntity = tulajdonosRepository.findById(ownerId);
        Optional<EszkozEntity> eszkozEntity = eszkozRepository.findById(toolId);

        if (tulajdonosEntity.isPresent() && eszkozEntity.isPresent()) {
            eszkozEntity.get().setTulajdonos(tulajdonosEntity.get());
            eszkozRepository.save(eszkozEntity.get());
        }
    }

    public void newOwner(String name, String nationalId, Date birthday) {
        TulajdonosEntity newOwner = new TulajdonosEntity();
        newOwner.setNev(name);
        newOwner.setSzemelyi(nationalId);
        newOwner.setSzuldatum(birthday);

        tulajdonosRepository.save(newOwner);
    }

    public void deleteOwner(long ownerId) {
        tulajdonosRepository.deleteById(ownerId);
    }
}

package hu.unideb.inf.eszkozrest.Service.impl;

import hu.unideb.inf.eszkozrest.Dto.EszkozDto;
import hu.unideb.inf.eszkozrest.Dto.TulajdonosDto;
import hu.unideb.inf.eszkozrest.Entity.EszkozEntity;
import hu.unideb.inf.eszkozrest.Repository.EszkozRepository;
import hu.unideb.inf.eszkozrest.Service.EszkozService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EszkozServiceImpl implements EszkozService {

    @Autowired
    EszkozRepository eszkozRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<EszkozDto> saveAll(List<EszkozDto> list) {
        for (EszkozDto eszkozDto : list) {
            eszkozRepository.save(modelMapper.map(eszkozDto, EszkozEntity.class));
        }
        return null;
    }

    @Override
    public List<EszkozDto> listAll() {
        List<EszkozDto> eszkozok = new ArrayList<>();
        for (EszkozEntity ee : eszkozRepository.findAll()) {
            eszkozok.add(modelMapper.map(ee, EszkozDto.class));
        }
        return eszkozok;
    }

    @Override
    public EszkozDto save(EszkozDto eszkoz) {
        EszkozEntity eszkozEntity = modelMapper.map(eszkoz, EszkozEntity.class);
        eszkozEntity = eszkozRepository.save(eszkozEntity);
        return modelMapper.map(eszkozEntity, EszkozDto.class);
    }

    @Override
    public void deleteById(long id) {
        eszkozRepository.deleteById(id);
    }

    @Override
    public EszkozDto findById(long id) {
        Optional<EszkozEntity> eszkozEntity = eszkozRepository.findById(id);

        EszkozDto eszkozDto = null;
        if (eszkozEntity.isPresent()) {
            eszkozDto = new EszkozDto();
            eszkozDto.setId(eszkozEntity.get().getId());
            eszkozDto.setJelleg(eszkozEntity.get().getJelleg());
            eszkozDto.setLeiras(eszkozEntity.get().getLeiras());
            eszkozDto.setMarka(eszkozEntity.get().getMarka());
            eszkozDto.setTipus(eszkozEntity.get().getTipus());
            if (eszkozEntity.get().getTulajdonos() != null) {
                eszkozDto.setTulajdonos(new TulajdonosDto(
                        eszkozEntity.get().getTulajdonos().getId(),
                        eszkozEntity.get().getTulajdonos().getNev(),
                        eszkozEntity.get().getTulajdonos().getSzemelyi(),
                        eszkozEntity.get().getTulajdonos().getSzuldatum()
                ));
            }
        }

        return eszkozDto;
    }
}

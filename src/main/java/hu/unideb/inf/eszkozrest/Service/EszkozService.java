package hu.unideb.inf.eszkozrest.Service;

import hu.unideb.inf.eszkozrest.Dto.EszkozDto;

import java.util.List;

public interface EszkozService {

    List<EszkozDto> saveAll(List<EszkozDto> list);

    List<EszkozDto> listAll();

    EszkozDto save(EszkozDto eszkoz);

    void deleteById(long id);

    EszkozDto findById(long id);
}












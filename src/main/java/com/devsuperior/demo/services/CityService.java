package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.services.exceptions.DatabaseException;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;


    @Transactional(readOnly = true)
    public List<CityDTO> findAll(){
        List<CityDTO> list = repository.findAll(Sort.by("name"))
                .stream().map(CityDTO::new).toList();
        return list;
    }

    @Transactional(readOnly = true)
    public CityDTO findById(Long id){
        Optional<City> obj = repository.findById(id);
        City entity = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new CityDTO(entity);
    }

    @Transactional
    public CityDTO insert(CityDTO dto){
        City entity = new City();

        copyDtoToEntity(dto, entity);

        repository.save(entity);

        return new CityDTO(entity);

    }

    @Transactional
    public CityDTO update(Long id, CityDTO dto){
        try {
            City entity = repository.getReferenceById(id);

            copyDtoToEntity(dto, entity);

            repository.save(entity);

            return new CityDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found");
        }
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(CityDTO dto, City entity){
        entity.setName(dto.getName());
    }

}

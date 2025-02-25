package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;


    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable){
        Page<EventDTO> page = repository.findAll(pageable).map(EventDTO::new);
        return page;
    }

    @Transactional
    public EventDTO insert(EventDTO dto){
        Event entity = new Event();

        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);
        return new EventDTO(entity);
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto){
        try {
            Event entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);

            repository.save(entity);

            return new EventDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }


    private void copyDtoToEntity(EventDTO dto, Event entity){
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(new City(dto.getCityId(), null));
    }
}

package cz.cvut.fit.tjv.rocnamic.api.controller;


import cz.cvut.fit.tjv.rocnamic.api.converter.DtoWithId;
import cz.cvut.fit.tjv.rocnamic.business.AbstractCrudService;
import cz.cvut.fit.tjv.rocnamic.domain.EntityWithId;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public abstract class AbstractCrudController<E extends EntityWithId<ID>, DTO extends DtoWithId<ID>, ID extends Serializable> {
    protected AbstractCrudService<E, ID> service;
    protected Function<E, DTO> toDto;
    protected Function<DTO, E> toEntity;

    public AbstractCrudController(AbstractCrudService<E, ID> service, Function<E, DTO> toDto, Function<DTO, E> toEntity) {
        this.service = service;
        this.toDto = toDto;
        this.toEntity = toEntity;
    }


    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO e) {
        try {
            E newE = service.create(toEntity.apply(e));
            return ResponseEntity.ok(toDto.apply(newE));
        } catch (IllegalArgumentException | PropertyValueException exception) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<DTO> readAll() {
        return StreamSupport.stream(service.readAll().spliterator(), false).map(toDto).toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DTO> readOne(@PathVariable ID id) {
        return service.readById(id).map(e -> ResponseEntity.ok(toDto.apply(e))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody DTO e, @PathVariable ID id) {
        try {
            e.setId(id);
            service.update(toEntity.apply(e));
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
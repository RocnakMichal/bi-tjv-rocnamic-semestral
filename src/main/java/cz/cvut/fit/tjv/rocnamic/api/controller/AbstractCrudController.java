package cz.cvut.fit.tjv.rocnamic.api.controller;


import cz.cvut.fit.tjv.rocnamic.api.converter.DtoWithId;
import cz.cvut.fit.tjv.rocnamic.service.AbstractCrudService;
import cz.cvut.fit.tjv.rocnamic.domain.EntityWithId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Save given Entity")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entity successfully saved"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided Entity data is invalid",
                            content = @Content
                    )
            }
    )

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO e) {
        try {
            E newE = service.create(toEntity.apply(e));
            return ResponseEntity.ok(toDto.apply(newE));
        } catch (IllegalArgumentException | PropertyValueException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Read all Entities")
    @ApiResponse(
            responseCode = "200",
            description = "Entities successfully read"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<DTO> readAll() {
        return StreamSupport.stream(service.readAll().spliterator(), false).map(toDto).toList();
    }

    @Operation(summary = "Read Entity with given id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entity read"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DTO> readOne(@PathVariable ID id) {
        return service.readById(id).map(e -> ResponseEntity.ok(toDto.apply(e))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Updates Entity with given id with provided Entity json")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Entity successfully updated"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided Entity data is invalid"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid"
                    )
            }
    )
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

    @Operation(summary = "Deletes Entity with provided id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Entity successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid"
                    )
            }
    )
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
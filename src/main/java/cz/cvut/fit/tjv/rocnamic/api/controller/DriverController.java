package cz.cvut.fit.tjv.rocnamic.api.controller;

import cz.cvut.fit.tjv.rocnamic.api.converter.CarToDto;
import cz.cvut.fit.tjv.rocnamic.api.converter.CarToEntity;

import cz.cvut.fit.tjv.rocnamic.api.converter.DriverToEntity;
import cz.cvut.fit.tjv.rocnamic.api.converter.DriverToDto;
import cz.cvut.fit.tjv.rocnamic.business.DriverService;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;


import cz.cvut.fit.tjv.rocnamic.api.controller.AbstractCrudController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/driver")
public class DriverController extends AbstractCrudController<Driver, DriverDto, Long> {


    DriverToEntity DriverToEntity;
    DriverToDto DriverToDto;

    CarToEntity CarToEntity;
    CarToDto CarToDto;




    public DriverController(CarToDto carToDto, CarToEntity carToEntity,

                            DriverService service, DriverToDto driverToDto, DriverToEntity driverToEntity) {
        super(service, driverToDto, driverToEntity);
        this.DriverToDto = driverToDto;
        this.DriverToEntity = driverToEntity;
        this.CarToDto=carToDto;
        this.CarToEntity=carToEntity;
        ;
    }

    @GetMapping("/{id}/car")
    public ResponseEntity<Collection<CarDto>> readAllCars(@PathVariable Long id) {
        return service.readById(id).<ResponseEntity<Collection<CarDto>>>map(
                driver-> ResponseEntity.ok(driver.getCars().stream().map(CarToDto).toList())
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{idDriver}/car/{idCar}")
    public ResponseEntity<Void> moveCar(@PathVariable Long idDriver, @PathVariable Long idCar) {
        try {
            ((DriverService) service).moveCar(idDriver, idCar);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/car")
    public ResponseEntity<Collection<CarDto>> newCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        try {
            ((DriverService) service).newCar(id, CarToEntity.apply(carDto));
            return service.readById(id).<ResponseEntity<Collection<CarDto>>>map(
                    driver -> ResponseEntity.ok(driver.getCars().stream().map(CarToDto).toList())
            ).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }



}

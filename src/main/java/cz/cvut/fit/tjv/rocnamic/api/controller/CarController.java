package cz.cvut.fit.tjv.rocnamic.api.controller;


import cz.cvut.fit.tjv.rocnamic.api.converter.CarToDto;
import cz.cvut.fit.tjv.rocnamic.api.converter.CarToEntity;
import cz.cvut.fit.tjv.rocnamic.api.converter.DriverToDto;
import cz.cvut.fit.tjv.rocnamic.business.CarService;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/car")
public class CarController extends AbstractCrudController<Car, CarDto, Long> {
    DriverToDto driverToDto;

    public CarController(DriverToDto driverToDto, CarService service,
                         CarToDto carToDto, CarToEntity carToEntity) {
        super(service, carToDto, carToEntity);
        this.driverToDto = driverToDto;
    }


    @GetMapping("/{id}/driver")
    public ResponseEntity<DriverDto> readDriver(@PathVariable Long id) {
        return service.readById(id).map(
                car -> ResponseEntity.ok(driverToDto.apply(car.getDriver()))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{idCar}/driver/{idDriver}")
    public ResponseEntity<Void> setDriver(@PathVariable Long idCar, @PathVariable Long idDriver) {
        try {
            ((CarService) service).setDriver(idCar, idDriver);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }



    public ResponseEntity<CarDto> create(CarDto e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}


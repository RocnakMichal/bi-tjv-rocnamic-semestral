package cz.cvut.fit.tjv.rocnamic.api.controller;


import cz.cvut.fit.tjv.rocnamic.api.converter.CarToDto;
import cz.cvut.fit.tjv.rocnamic.api.converter.CarToEntity;
import cz.cvut.fit.tjv.rocnamic.api.converter.DriverToDto;
import cz.cvut.fit.tjv.rocnamic.business.CarService;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController extends AbstractCrudController<Car, CarDto, Long> {
    DriverToDto driverToDto;

    public CarController(DriverToDto driverToDto, CarService service,
                         CarToDto carToDto, CarToEntity carToEntity) {
        super(service, carToDto, carToEntity);
        this.driverToDto = driverToDto;
    }






    public ResponseEntity<CarDto> create(CarDto e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}


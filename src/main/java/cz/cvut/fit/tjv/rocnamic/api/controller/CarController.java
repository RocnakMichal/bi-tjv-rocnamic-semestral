package cz.cvut.fit.tjv.rocnamic.api.controller;


import cz.cvut.fit.tjv.rocnamic.api.converter.CarToDto;
import cz.cvut.fit.tjv.rocnamic.api.converter.CarToEntity;
import cz.cvut.fit.tjv.rocnamic.api.converter.DriverToDto;
import cz.cvut.fit.tjv.rocnamic.business.CarService;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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



    @Operation(summary = "Read Driver of Car")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Driver successfully read"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}/driver")
    public ResponseEntity<DriverDto> readDriver(@PathVariable Long id) {
        return service.readById(id).map(
                car -> ResponseEntity.ok(driverToDto.apply(car.getDriver()))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Changes Driver of Car")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Driver successfully changed"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid"
                    )
            }
    )
    @PutMapping("/{idCar}/driver/{idDriver}")
    public ResponseEntity<Void> setDriver(@PathVariable Long idCar, @PathVariable Long idDriver) {
        try {
            ((CarService) service).setDriver(idCar, idDriver);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    @Operation(summary = "Forbidden way of creating Car")
    @ApiResponse(
            responseCode = "405",
            content = @Content
    )
    public ResponseEntity<CarDto> create(CarDto e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}


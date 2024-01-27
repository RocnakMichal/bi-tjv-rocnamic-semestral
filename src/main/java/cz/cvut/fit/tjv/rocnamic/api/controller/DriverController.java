package cz.cvut.fit.tjv.rocnamic.api.controller;

import cz.cvut.fit.tjv.rocnamic.api.converter.*;

import cz.cvut.fit.tjv.rocnamic.service.DriverService;
import cz.cvut.fit.tjv.rocnamic.repository.DriverRepository;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/driver")
public class DriverController extends AbstractCrudController<Driver, DriverDto, Long> {

    DriverService driverService;
    DriverToEntity DriverToEntity;
    DriverToDto DriverToDto;

    CarToEntity CarToEntity;
    CarToDto CarToDto;

    CompanyToDto CompanyToDto;
    CompanyToEntity CompanyToEntity;


    @Autowired
    private DriverRepository driverRepository;

    public DriverController(CarToDto carToDto, CarToEntity carToEntity,

                            DriverService service, DriverToDto driverToDto, DriverToEntity driverToEntity,CompanyToEntity companyToEntity,CompanyToDto companyToDto) {
        super(service, driverToDto, driverToEntity);
        this.DriverToDto = driverToDto;
        this.DriverToEntity = driverToEntity;
        this.CarToDto=carToDto;
        this.CarToEntity=carToEntity;
        this.CompanyToDto=companyToDto;
        this.CompanyToEntity=companyToEntity;
    }


    @Operation(summary = "Read all Cars that belong to Driver")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cars successfully read"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}/car")
    public ResponseEntity<Collection<CarDto>> readAllCars(@PathVariable Long id) {
        return service.readById(id).<ResponseEntity<Collection<CarDto>>>map(
                driver-> ResponseEntity.ok(driver.getCars().stream().map(CarToDto).toList())
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Moves Car to Driver")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Car successfully moved"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid"
                    )
            }
    )

    @PutMapping("/{idDriver}/car/{idCar}")
    public ResponseEntity<Void> moveCar(@PathVariable Long idDriver, @PathVariable Long idCar) {
        try {
            ((DriverService) service).moveCar(idDriver, idCar);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Create new Car that belongs to Deriver")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Car successfully saved"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided Car is invalid",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid",
                            content = @Content
                    )
            }
    )
    @PostMapping("/{id}/car")
    public ResponseEntity<CarDto> newCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        try {
            return ResponseEntity.ok(CarToDto.apply(
                    ((DriverService) service).newCar(id, CarToEntity.apply(carDto))
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Read all Companies that belong to Driver")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Companies successfully read"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid",
                            content = @Content
                    )
            }
    )
    @GetMapping("/driver/deleteWithoutCar")
    public ResponseEntity<Void> deleteDriversWithoutCar() {
        Collection<Driver> drivers = driverRepository.getDriversWithoutACar();
        if(drivers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            drivers.forEach(driver -> service.deleteById(driver.getId()));
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Remove Company from Driver")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Drivers successfully removed"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided driver invalid",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}/company")
    public ResponseEntity<Collection<CompanyDto>> readAllCompanys(@PathVariable Long id) {
        return service.readById(id).<ResponseEntity<Collection<CompanyDto>>>map(
                driver -> ResponseEntity.ok(driver.getCompanys().stream().map(CompanyToDto).toList())
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add Company to Driver")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Company successfully saved"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided Company is invalid",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid",
                            content = @Content
                    )
            }
    )
    @PutMapping("/{idDriver}/company/{idCompany}")
    public ResponseEntity<Void> addCompany(@PathVariable Long idDriver, @PathVariable Long idCompany) {
        try {
            ((DriverService) service).addWork(idDriver, idCompany);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @Operation(summary = "Remove Company from Driver")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Company successfully removed"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{idDriver}/company/{idCompany}")
    public ResponseEntity<Void> removeCompany (@PathVariable Long idDriver, @PathVariable Long idCompany) {
        try {
            ((DriverService) service).removeWork(idDriver, idCompany);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

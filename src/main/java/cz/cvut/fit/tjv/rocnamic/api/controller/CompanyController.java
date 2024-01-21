package cz.cvut.fit.tjv.rocnamic.api.controller;

import cz.cvut.fit.tjv.rocnamic.api.converter.CompanyToDto;
import cz.cvut.fit.tjv.rocnamic.api.converter.CompanyToEntity;
import cz.cvut.fit.tjv.rocnamic.api.converter.DriverToDto;
import cz.cvut.fit.tjv.rocnamic.business.CompanyService;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/company")
public class CompanyController extends AbstractCrudController<Company, CompanyDto, Long> {

CompanyToEntity CompanyToEntity;
CompanyToDto CompanyToDto;

DriverToDto DriverToDto;

    public CompanyController(CompanyToDto companyToDto, CompanyToEntity companyToEntity, CompanyService service, DriverToDto driverToDto) {
        super(service, companyToDto, companyToEntity);
        this.CompanyToDto = companyToDto;
        this.CompanyToEntity = companyToEntity;
        this.DriverToDto = driverToDto;

    }

    @Operation(summary = "Reads all Drivers from Company")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Drivers successfully read"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid",
                            content = @Content
                    )
            }
    )

    @GetMapping("/{id}/driver")
    public ResponseEntity<Collection<DriverDto>> readAllDrivers(@PathVariable Long id) {
        return service.readById(id).<ResponseEntity<Collection<DriverDto>>>map(
                company -> ResponseEntity.ok(company.getDrivers().stream().map(DriverToDto).toList())
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add Drivers to Company")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Driver successfully added"
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid"
                    )
            }
    )
    @PutMapping("/{idCompany}/driver/{idDriver}")
    public ResponseEntity<Void> addAttend(@PathVariable Long idCompany, @PathVariable Long idDriver) {
        try {
            ((CompanyService) service).attend(idCompany, idDriver);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove Drivers to Company")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Driver successfully removed"
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Provided id is invalid"
                    )
            }
    )
    @DeleteMapping("/{idCompany}/driver/{idDriver}")
    public ResponseEntity<Void> removeAttend(@PathVariable Long idCompany, @PathVariable Long idDriver) {
        try {
            ((CompanyService) service).removeAttend(idCompany, idDriver);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

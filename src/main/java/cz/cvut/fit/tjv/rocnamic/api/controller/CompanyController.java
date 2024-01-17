package cz.cvut.fit.tjv.rocnamic.api.controller;

import cz.cvut.fit.tjv.rocnamic.api.converter.CompanyToDto;
import cz.cvut.fit.tjv.rocnamic.api.converter.CompanyToEntity;
import cz.cvut.fit.tjv.rocnamic.business.CompanyService;
import cz.cvut.fit.tjv.rocnamic.business.DriverService;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController extends AbstractCrudController<Company, CompanyDto, Long> {

CompanyToEntity CompanyToEntity;
CompanyToDto CompanyToDto;

    public CompanyController(CompanyToDto companyToDto, CompanyToEntity companyToEntity, CompanyService service) {
        super(service, companyToDto, companyToEntity);
        this.CompanyToDto = companyToDto;
        this.CompanyToEntity = companyToEntity;

    }
}

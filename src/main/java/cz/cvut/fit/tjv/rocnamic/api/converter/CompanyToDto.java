package cz.cvut.fit.tjv.rocnamic.api.converter;

import cz.cvut.fit.tjv.rocnamic.api.controller.CompanyDto;
import cz.cvut.fit.tjv.rocnamic.api.controller.DriverDto;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CompanyToDto implements Function<Company, CompanyDto> {
    @Override
    public CompanyDto apply(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setNumber_of_products(company.getNumber_of_products());
        return companyDto;
    }





}

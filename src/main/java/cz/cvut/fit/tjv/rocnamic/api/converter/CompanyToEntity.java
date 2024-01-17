package cz.cvut.fit.tjv.rocnamic.api.converter;

import cz.cvut.fit.tjv.rocnamic.api.controller.CompanyDto;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class CompanyToEntity implements Function<CompanyDto, Company> {
    @Override
    public Company apply(CompanyDto companyDto) {
        Company company = new Company();
        company.setId(companyDto.getId());
        company.setName(companyDto.getName());
        company.setNumber_of_products(companyDto.getNumber_of_products());
        return company;
    }
}
package cz.cvut.fit.tjv.rocnamic.service;


import cz.cvut.fit.tjv.rocnamic.domain.Company;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Collections;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CompanyValidationTest {
    @Autowired
    private CompanyService companyService;



    @Test
    public void testValidateCompanyWithValidName() {
        var company = new Company();
        company.setName(String.join("", Collections.nCopies(10, "a")));
        company.setNumber_of_products(1000);

        assertDoesNotThrow(() -> companyService.validate(company));
    }


    @Test
    public void testValidateCompanyWithInvalidName() {
        var company = new Company();
        company.setName(String.join("", Collections.nCopies(256, "a")));
        company.setNumber_of_products(1000);

        assertThrows(IllegalArgumentException.class, () -> companyService.validate(company));
    }
}
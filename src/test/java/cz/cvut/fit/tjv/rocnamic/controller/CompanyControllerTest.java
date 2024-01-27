package cz.cvut.fit.tjv.rocnamic.controller;

import cz.cvut.fit.tjv.rocnamic.api.controller.CompanyController;
import cz.cvut.fit.tjv.rocnamic.api.converter.CompanyToDto;
import cz.cvut.fit.tjv.rocnamic.service.CompanyService;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@ComponentScan(basePackages = "cz.cvut.fit.tjv.rocnamic.api.converter")
@Import({CompanyToDto.class})
public class CompanyControllerTest {
    @MockBean
    CompanyService companyService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testFindAll() throws Exception {
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Company");
        company1.setNumber_of_products(1000);
        Company company2 = new Company();
        company2.setId(2L);
        company2.setName("Lukas");
        company2.setNumber_of_products(1000);


        List<Company> companies = List.of(company1, company2);

        Mockito.when(companyService.readAll()).thenReturn(companies);

        mockMvc.perform(get("/company"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
}


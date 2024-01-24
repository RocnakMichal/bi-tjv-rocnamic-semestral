package cz.cvut.fit.tjv.rocnamic.api;



import cz.cvut.fit.tjv.rocnamic.api.controller.CompanyController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestApiTest {
    @Autowired
    CompanyController companyController;

    @Test
    public void contextLoadsTests() {
        Assertions.assertThat(companyController).isNotNull();
    }
}


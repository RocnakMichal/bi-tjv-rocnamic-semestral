package cz.cvut.fit.tjv.rocnamic.domain;

import cz.cvut.fit.tjv.rocnamic.dao.DriverRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DriverTest {
    @Autowired
    DriverRepository driverRepository;

    @Test
    public void testCreateReadDelete() {
        Driver driver = new Driver();
        driver.setName("Alois");
        driver.setSurname("Kral");

        driverRepository.save(driver);

        Iterable<Driver> drivers = driverRepository.findAll();
        Assertions.assertThat(drivers).extracting(Driver::getName).containsOnly("Alois");
        Assertions.assertThat(drivers).extracting(Driver::getSurname).containsOnly("Kral");

        driverRepository.deleteAll();
        Assertions.assertThat(driverRepository.findAll()).isEmpty();
    }


    @Test
    public void testReadDeleteEmpty() {


        Iterable<Driver> drivers = driverRepository.findAll();
        Assertions.assertThat(drivers).extracting(Driver::getName).isEmpty();
        Assertions.assertThat(drivers).extracting(Driver::getSurname).isEmpty();

        driverRepository.deleteAll();
        Assertions.assertThat(driverRepository.findAll()).isEmpty();
    }
}
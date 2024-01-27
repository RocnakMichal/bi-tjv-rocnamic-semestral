package cz.cvut.fit.tjv.rocnamic.domain;

import cz.cvut.fit.tjv.rocnamic.service.DriverService;
import cz.cvut.fit.tjv.rocnamic.repository.DriverRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DriverTest {
    @Autowired
    DriverRepository driverRepository;

    @MockBean
    DriverService driverService;

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
    public void testDeleteNonExistentDriver() {

        doThrow(NoSuchElementException.class).when(driverService).deleteById(-1L);

        assertThrows(NoSuchElementException.class, () -> driverService.deleteById(-1L));
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
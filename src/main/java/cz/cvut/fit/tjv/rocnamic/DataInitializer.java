package cz.cvut.fit.tjv.rocnamic;

import cz.cvut.fit.tjv.rocnamic.repository.CarRepository;
import cz.cvut.fit.tjv.rocnamic.repository.CompanyRepository;
import cz.cvut.fit.tjv.rocnamic.repository.DriverRepository;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Profile("!test")
public class DataInitializer {
    private final JdbcTemplate jdbcTemplate;
    private final DriverRepository driverRepository;
    private final CompanyRepository companyRepository;
    private final CarRepository carRepository;

    public DataInitializer(JdbcTemplate jdbcTemplate,
                           DriverRepository driverRepository,
                          CompanyRepository companyRepository,
                           CarRepository carRepository)
                          {
        this.jdbcTemplate = jdbcTemplate;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
        this.carRepository = carRepository;
    }

    @PostConstruct
    public void initializeData() {
   //    clearDB();

        Driver driver1 = createDriver("Tomas", "Cerny");
        Driver driver2 = createDriver("Adam", "Novotny");
        Driver driver3 = createDriver("Filip", "Pilny");
        Driver driver4 = createDriver("No", "Cars");
        Set<Company> companies = new HashSet<>();

        Company company1 = createCompany("Company", 1000);
        Company company2 = createCompany("Gasss", 15000);
        Company company3 = createCompany("Oxygen", 18250);


        long minDay = LocalDate.of(1995, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        LocalDate randomDate1 = LocalDate.ofEpochDay(randomDay);
        LocalDate randomDate2 = LocalDate.ofEpochDay(randomDay);
        LocalDate randomDate3 = LocalDate.ofEpochDay(randomDay);

        Car car1 = createCar("3E63014","Renault","Clio",randomDate1,driver1);
        Car car2 = createCar("6H71829","Ford","Focus",randomDate2,driver2);
        Car car3 = createCar("2A71508","Renault","Twingo",randomDate3,driver3);


        Set<Driver> drivers1 = new HashSet<>();
        Set<Driver> drivers2 = new HashSet<>();
        Set<Driver> drivers3 = new HashSet<>();

        drivers1.add(driver1);
        drivers1.add(driver2);
        company1.setDrivers(drivers1);

        drivers2.add(driver2);
        drivers2.add(driver3);
        company2.setDrivers(drivers2);

        drivers3.add(driver1);
        drivers3.add(driver3);
        company3.setDrivers(drivers3);
    }

    /* private void clearDB() {
        jdbcTemplate.execute("TRUNCATE TABLE driver, company_drivers, driver_companys, car,company");
    }*/

    private Driver createDriver(String name, String surname) {
        Driver driver = new Driver();
        driver.setName(name);
        driver.setSurname(surname);
        return driverRepository.save(driver);
    }

    private Car createCar(String licence_plate,String brand, String model, LocalDate bought_in,Driver driver) {
        Car car = new Car();
        car.setLicence_plate(licence_plate);
        car.setBrand(brand);
        car.setModel(model);
        car.setBoughtIn(bought_in);
        car.setDriver(driver);
        return carRepository.save(car);
    }

    private Company createCompany(String name, int number_of_product) {
        Company company = new Company();
        company.setName(name);
        company.setNumber_of_products(number_of_product);
        return companyRepository.save(company);
    }
}
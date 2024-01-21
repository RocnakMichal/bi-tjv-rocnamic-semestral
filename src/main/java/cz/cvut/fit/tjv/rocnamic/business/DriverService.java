package cz.cvut.fit.tjv.rocnamic.business;

import cz.cvut.fit.tjv.rocnamic.dao.CarRepository;
import cz.cvut.fit.tjv.rocnamic.business.CarService;
import cz.cvut.fit.tjv.rocnamic.dao.DriverRepository;
import cz.cvut.fit.tjv.rocnamic.dao.CompanyRepository;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Component
@Service
public class DriverService extends AbstractCrudService<Driver, Long> {

    private final CarService carService;

    private final CarRepository carRepository;
    private final CompanyRepository companyRepository;

    public DriverService(DriverRepository driverRepository,CarRepository carRepository,CarService carService,CompanyRepository companyRepository) {
        super(driverRepository);
        this.carService=carService;
        this.carRepository=carRepository;
        this.companyRepository=companyRepository;



    }

    @Override
    public void update(Driver e) throws NoSuchElementException, IllegalArgumentException {
        validate(e);
        Driver oldDriver = findOrThrow(e.getId());
        oldDriver.getCompanys().forEach(e::addCompany);
        super.update(e);
    }

    @Override
    public void deleteById(Long id) throws NoSuchElementException {
        Driver driver = findOrThrow(id);
        driver.getCompanys().forEach(company -> company.removeDriver(driver));
        companyRepository.saveAll(driver.getCompanys());
        super.deleteById(id);
    }

    @Override
    protected void validate(Driver driver) throws IllegalArgumentException {
        if(driver.getName().length()>255)
            throw new IllegalArgumentException();
    }



    public void moveCar(Long idDriver, Long idCar) throws NoSuchElementException {
        Driver driver = findOrThrow(idDriver);
        Car car = carService.findOrThrow(idCar);
        if (car.getDriver() != null) {
            car.getDriver().removeCar(car);
            repository.save(car.getDriver());
        }
       car.setDriver(driver);
        driver.addCar(car);
        carRepository.save(car);
        repository.save(driver);
    }

    public Car newCar(Long id, Car e) throws IllegalArgumentException, NoSuchElementException {
        Driver driver = findOrThrow(id);

        e.setDriver(driver);
        Car ret = carService.create(e);

        driver.addCar(e);

        repository.save(driver);
        return ret;
    }





    public void addCompany(Long idDriver, Long idCompany) throws NoSuchElementException{
        Driver driver = findOrThrow(idDriver);
        Company company = companyRepository.findById(idCompany).orElseThrow();

        company.addDriver(driver);
        driver.addCompany(company);

        repository.save(driver);
        companyRepository.save(company);
    }

    public void removeCompany(Long idDriver, Long idCompany) throws NoSuchElementException {
        Driver driver = findOrThrow(idDriver);
        Company company = companyRepository.findById(idCompany).orElseThrow();

        company.removeDriver(driver);
        driver.removeCompany(company);

        repository.save(driver);
        companyRepository.save(company);
    }

    public void addAttendee(Long idDriver, Long idCompany) throws NoSuchElementException{
        Driver driver = findOrThrow(idDriver);
        Company company = companyRepository.findById(idCompany).orElseThrow();


        company.addDriver(driver);
        driver.addAttendee(company);

        repository.save(driver);
        companyRepository.save(company);
    }

    public void removeAttendee(Long idDriver, Long idCompany) throws NoSuchElementException {
        Driver driver = findOrThrow(idDriver);
        Company company = companyRepository.findById(idCompany).orElseThrow();

        company.removeDriver(driver);
        driver.removeAttendee(company);

        repository.save(driver);
        companyRepository.save(company);
    }







}
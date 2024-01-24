package cz.cvut.fit.tjv.rocnamic.business;

import cz.cvut.fit.tjv.rocnamic.dao.CarRepository;
import cz.cvut.fit.tjv.rocnamic.dao.DriverRepository;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Service
public class CarService extends AbstractCrudService<Car, Long> {
    private final DriverRepository driverRepository;

    public CarService(CarRepository carRepository, DriverRepository driverRepository) {
        super(carRepository);
        this.driverRepository = driverRepository;
    }

    public List<String> getAllLicensePlates() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(Car::getLicence_plate)
                .collect(Collectors.toList());
    }
    public void setDriver(Long idCar, Long idDriver) {
        Car car = findOrThrow(idCar);
        Driver driver = driverRepository.findById(idDriver).orElseThrow();

        if (car.getDriver() != null) {
            car.getDriver().removeCar(car);
            driverRepository.save(car.getDriver());
        }

        car.setDriver(driver);
        driver.removeCar(car);

        repository.save(car);
        driverRepository.save(driver);
    }


    @Override
    public void update(Car e) throws NoSuchElementException, IllegalArgumentException {
        validate(e);
        Car oldCar = findOrThrow(e.getId());
        e.setDriver(oldCar.getDriver());
        super.update(e);
    }

    @Override
    public void deleteById(Long id) throws NoSuchElementException {
        Car car = findOrThrow(id);

        if (car.getDriver() != null) {
            car.getDriver().removeCar(car);
            car.setDriver(null);
        }
        super.deleteById(id);
    }

    @Override
    protected void validate(Car car) throws IllegalArgumentException {
        if (car.getModel().length() > 255)
            throw new IllegalArgumentException();
    }
}
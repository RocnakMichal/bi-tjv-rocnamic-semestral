package cz.cvut.fit.tjv.rocnamic.business;

import cz.cvut.fit.tjv.rocnamic.dao.CarRepository;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Component
@Service
public class CarService extends AbstractCrudService<Car, Long> {


    public CarService(CarRepository carRepository) {
        super(carRepository);

    }

    @Override
    public void update(Car e) throws NoSuchElementException, IllegalArgumentException {
        validate(e);
        Car oldCar = findOrThrow(e.getId());
        super.update(e);
    }

    @Override
    public void deleteById(Long id) throws NoSuchElementException {
        Car car = findOrThrow(id);


        super.deleteById(id);
    }

    @Override
    protected void validate(Car car) throws IllegalArgumentException {
            if(car.getModel().length()>255)
            throw new IllegalArgumentException();
    }
}
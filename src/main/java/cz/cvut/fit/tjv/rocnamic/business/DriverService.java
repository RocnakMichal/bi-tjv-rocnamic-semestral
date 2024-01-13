package cz.cvut.fit.tjv.rocnamic.business;

import cz.cvut.fit.tjv.rocnamic.dao.CarRepository;
import cz.cvut.fit.tjv.rocnamic.dao.DriverRepository;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Component
@Service
public class DriverService extends AbstractCrudService<Driver, Long> {


    public DriverService(DriverRepository driverRepository) {
        super(driverRepository);

    }

    @Override
    public void update(Driver e) throws NoSuchElementException, IllegalArgumentException {
        validate(e);
        Driver oldDriver = findOrThrow(e.getId());
        super.update(e);
    }

    @Override
    public void deleteById(Long id) throws NoSuchElementException {
        Driver driver = findOrThrow(id);


        super.deleteById(id);
    }

    @Override
    protected void validate(Driver driver) throws IllegalArgumentException {
        if(driver.getName().length()>255)
            throw new IllegalArgumentException();
    }
}
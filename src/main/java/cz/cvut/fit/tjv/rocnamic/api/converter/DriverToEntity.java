package cz.cvut.fit.tjv.rocnamic.api.converter;

import cz.cvut.fit.tjv.rocnamic.api.controller.CarDto;
import cz.cvut.fit.tjv.rocnamic.api.controller.DriverDto;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DriverToEntity implements Function<DriverDto, Driver> {
    @Override
    public Driver apply(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setId(driverDto.getId());
        driver.setName(driverDto.getName());
        driver.setSurname(driverDto.getSurname());
        return driver;
    }
}
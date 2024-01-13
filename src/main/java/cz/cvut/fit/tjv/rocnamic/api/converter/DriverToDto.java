package cz.cvut.fit.tjv.rocnamic.api.converter;

import cz.cvut.fit.tjv.rocnamic.api.controller.CarDto;
import cz.cvut.fit.tjv.rocnamic.api.controller.DriverDto;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DriverToDto implements Function<Driver, DriverDto> {
    @Override
    public DriverDto apply(Driver driver) {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setName(driver.getName());
        driverDto.setSurname(driver.getSurname());
        return driverDto;
    }
}
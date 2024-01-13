package cz.cvut.fit.tjv.rocnamic.api.converter;

import cz.cvut.fit.tjv.rocnamic.api.controller.CarDto;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarToDto implements Function<Car, CarDto> {
    @Override
    public CarDto apply(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setLicence_plate(car.getLicence_plate());
        carDto.setBrand(car.getBrand());
        carDto.setModel(car.getModel());
        carDto.setBoughtIn(car.getBoughtIn());
        return carDto;
    }
}

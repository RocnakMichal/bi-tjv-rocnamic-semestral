package cz.cvut.fit.tjv.rocnamic.api.converter;

import cz.cvut.fit.tjv.rocnamic.api.controller.CarDto;
import cz.cvut.fit.tjv.rocnamic.domain.Car;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CarToEntity implements Function<CarDto, Car> {
    @Override
    public Car apply(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setLicence_plate(carDto.getLicence_plate());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setBoughtIn(carDto.getBoughtIn());
        return car;
    }
}

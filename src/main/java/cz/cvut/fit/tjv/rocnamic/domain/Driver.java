package cz.cvut.fit.tjv.rocnamic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.*;


@Getter
@Setter
@Entity
@Table(name = "driver")
public class Driver extends EntityWithId<Long> {


    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "driver")
    private Set<Car> cars = new HashSet<>();


    public void addCar(Car car) {
        cars.add(Objects.requireNonNull(car));
    }

    public Collection<Car> getCars() {
        return Collections.unmodifiableCollection(cars);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }
}


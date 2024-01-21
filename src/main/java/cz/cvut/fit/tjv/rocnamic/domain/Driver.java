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



    @ManyToMany
    private Set<Company> companys= new HashSet<>();


    public Collection<Company> getCompanys() {
        return Collections.unmodifiableCollection(companys);
    }

    public void addCompany(Company company) {
        companys.add(Objects.requireNonNull(company));
    }

    public void removeCompany(Company company) {
        companys.remove(company);
    }


    public Collection<Company> getAttendees() {
        return Collections.unmodifiableCollection(companys);
    }
    public void addAttendee(Company attendee) {
        companys.add(Objects.requireNonNull(attendee));
    }

    public void removeAttendee(Company attendee) {
        companys.remove(attendee);
    }

}


package cz.cvut.fit.tjv.rocnamic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car extends EntityWithId<Long>{


    @NotEmpty(message = "SPZ must not be empty.")
    @Column(name = "licence_plate",nullable = false)
    private String licence_plate;


    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;


    @Column(name = "bought_in")
    private LocalDate boughtIn;

    @ManyToOne
    private Driver driver;

    public Car(Long id, String licence_plate, String brand, String model, LocalDate boughtIn) {
        this.id = id;
        this.licence_plate=licence_plate;
        this.brand = brand;
        this.model = model;
        this.boughtIn = boughtIn;
    }

    public Car() {

    }

    @Override
    public String toString() {
        return "Car{" +
                "licence_plate='" + licence_plate + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", boughtIn=" + boughtIn +
                ", id=" + id +
                '}';
    }

    //  @OneToOne(mappedBy = "car")
   // private Driver driver;

}
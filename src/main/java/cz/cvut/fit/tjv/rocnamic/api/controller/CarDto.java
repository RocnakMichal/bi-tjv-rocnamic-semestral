package cz.cvut.fit.tjv.rocnamic.api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fit.tjv.rocnamic.api.converter.DtoWithId;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class CarDto extends DtoWithId<Long> {

    private String licence_plate;

    private String brand;

    private String model;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d.M.yyyy")
    private LocalDate boughtIn;

    public CarDto(){};
    public CarDto(Long id,String licence_plate, String brand,String model,LocalDate boughtIn){
        this.id=id;
        this.licence_plate=licence_plate;
        this.brand=brand;
        this.model=model;
        this.boughtIn=boughtIn;
    }
}

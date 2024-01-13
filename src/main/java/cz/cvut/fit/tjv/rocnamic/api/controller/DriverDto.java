package cz.cvut.fit.tjv.rocnamic.api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fit.tjv.rocnamic.api.converter.DtoWithId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DriverDto extends DtoWithId<Long> {

    private String name;
    private String surname;

}
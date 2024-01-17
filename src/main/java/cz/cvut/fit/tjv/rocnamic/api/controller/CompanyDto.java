package cz.cvut.fit.tjv.rocnamic.api.controller;

import cz.cvut.fit.tjv.rocnamic.api.converter.DtoWithId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto extends DtoWithId<Long> {

    private String name;
    private long number_of_products;


}
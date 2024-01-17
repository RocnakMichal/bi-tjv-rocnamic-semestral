package cz.cvut.fit.tjv.rocnamic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.*;


@Getter
@Setter
@Entity
@Table(name = "company")
public class Company extends EntityWithId<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "number of products")
    private long number_of_products;


}
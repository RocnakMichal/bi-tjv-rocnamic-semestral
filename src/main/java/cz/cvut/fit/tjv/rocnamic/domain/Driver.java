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
    public class Driver extends EntityWithId<Long>{


        @Column(name = "surname",nullable = false)
        private String surname;

        @Column(name = "name",nullable = false)
        private String name;

        @OneToMany(mappedBy = "driver")
        private Set<Car> cars=new HashSet<>();

    }


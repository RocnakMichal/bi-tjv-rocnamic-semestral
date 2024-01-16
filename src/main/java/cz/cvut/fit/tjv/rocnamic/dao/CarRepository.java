package cz.cvut.fit.tjv.rocnamic.dao;

import cz.cvut.fit.tjv.rocnamic.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

}
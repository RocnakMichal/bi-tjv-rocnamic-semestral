package cz.cvut.fit.tjv.rocnamic.dao;

import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
    @Query("SELECT d FROM Driver d WHERE d.id NOT IN (SELECT c.driver.id FROM Car c)")
    Collection<Driver> getDriversWithoutACar();
}
package cz.cvut.fit.tjv.rocnamic.dao;

import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
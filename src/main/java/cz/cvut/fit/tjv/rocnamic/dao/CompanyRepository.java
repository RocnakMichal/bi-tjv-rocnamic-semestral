package cz.cvut.fit.tjv.rocnamic.dao;

import cz.cvut.fit.tjv.rocnamic.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
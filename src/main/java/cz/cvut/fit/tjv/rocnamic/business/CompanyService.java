package cz.cvut.fit.tjv.rocnamic.business;

import cz.cvut.fit.tjv.rocnamic.dao.CompanyRepository;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Component
@Service
public class CompanyService extends  AbstractCrudService<Company,Long>{


public CompanyService(CompanyRepository companyRepository){
    super(companyRepository);
}

    @Override
    public void update(Company e) throws NoSuchElementException, IllegalArgumentException {
        validate(e);
        Company oldCompany = findOrThrow(e.getId());
        super.update(e);
    }

    @Override
    public void deleteById(Long id) throws NoSuchElementException {
        Company company = findOrThrow(id);
        super.deleteById(id);
    }

    @Override
    protected void validate(Company company) throws IllegalArgumentException {
        if(company.getName().length()>255)
            throw new IllegalArgumentException();
    }


}

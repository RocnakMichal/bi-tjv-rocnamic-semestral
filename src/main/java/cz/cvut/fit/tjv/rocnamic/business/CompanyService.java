package cz.cvut.fit.tjv.rocnamic.business;

import cz.cvut.fit.tjv.rocnamic.dao.CompanyRepository;
import cz.cvut.fit.tjv.rocnamic.dao.DriverRepository;
import cz.cvut.fit.tjv.rocnamic.domain.Company;
import cz.cvut.fit.tjv.rocnamic.domain.Driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


//@Component
@Service
public class CompanyService extends  AbstractCrudService<Company,Long>{

    private final DriverRepository driverRepository;


    public CompanyService(CompanyRepository companyRepository,
                          DriverRepository driverRepository){
    super(companyRepository);
    this.driverRepository=driverRepository;
    }

    @Override
    public void update(Company e) throws NoSuchElementException, IllegalArgumentException {
        validate(e);
        Company oldCompany = findOrThrow(e.getId());
        oldCompany.getDrivers().forEach(e::addDriver);
        super.update(e);
    }

    @Override
    public void deleteById(Long id) throws NoSuchElementException {
        Company company = findOrThrow(id);

        company.getDrivers().forEach(driver -> driver.removeCompany(company));
        driverRepository.saveAll(company.getDrivers());
        super.deleteById(id);
    }

    @Override
    protected void validate(Company company) throws IllegalArgumentException {
        if(company.getName().length()>255)
            throw new IllegalArgumentException();
    }


    public void attend(Long Company, Long idDriver) throws NoSuchElementException{
       Company company = findOrThrow(Company);
        Driver driver = driverRepository.findById(idDriver).orElseThrow();

        company.addDriver(driver);
        driver.addAttendee(company);

        driverRepository.save(driver);
        repository.save(company);
    }

    public void removeAttend(Long idCompany, Long idDriver) throws NoSuchElementException {
        Company company = findOrThrow(idCompany);
        Driver driver = driverRepository.findById(idDriver).orElseThrow();

        company.removeDriver(driver);
        driver.removeAttendee(company);

        driverRepository.save(driver);
        repository.save(company);
    }




    public void addDriver(Long idCompany, Long idDriver) throws NoSuchElementException{
        Company company = findOrThrow(idCompany);
        Driver driver = driverRepository.findById(idDriver).orElseThrow();


        company.addDriver(driver);
        driver.addCompany(company);

        driverRepository.save(driver);
        repository.save(company);
    }

    public void removeDriver(Long idCompany, Long idDriver) throws NoSuchElementException {
        Company company = findOrThrow(idCompany);
        Driver driver = driverRepository.findById(idDriver).orElseThrow();

        company.removeDriver(driver);
        driver.removeCompany(company);

        driverRepository.save(driver);
        repository.save(company);
    }






}

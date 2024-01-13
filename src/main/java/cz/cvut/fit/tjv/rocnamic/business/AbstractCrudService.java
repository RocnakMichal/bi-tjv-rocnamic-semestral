package cz.cvut.fit.tjv.rocnamic.business;


import cz.cvut.fit.tjv.rocnamic.domain.EntityWithId;
import cz.cvut.fit.tjv.rocnamic.exception.EntityStateException;
import org.hibernate.PropertyValueException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractCrudService<E extends EntityWithId<ID>, ID extends Serializable> {
    protected final CrudRepository<E, ID> repository;

    public AbstractCrudService(CrudRepository<E, ID> repository) {
        this.repository = repository;
    }

    public E create(E e) throws IllegalArgumentException, PropertyValueException {
        validate(e);
        if (e.getId() != null && repository.existsById(e.getId()))
            throw new IllegalArgumentException();

        return repository.save(e);
    }

    public Optional<E> readById(ID id) {
        return repository.findById(id);
    }

    public Iterable<E> readAll() {
        return repository.findAll();
    }

    public void update(E e) throws NoSuchElementException {
        validate(e);
        findOrThrow(e.getId());
        repository.save(e);
    }

    public void deleteById(ID id) throws NoSuchElementException {
        repository.deleteById(id);
    }

    protected E findOrThrow(ID id) throws NoSuchElementException {
        if (id == null) throw new NoSuchElementException();
        return repository.findById(id).orElseThrow();
    }

    protected abstract void validate(E e) throws IllegalArgumentException;

    protected void deleteAll(Collection<E> es) {
        repository.deleteAll(es);
    }

    protected void saveAll(Collection<E> es) {
        repository.saveAll(es);
    }
}


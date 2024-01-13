package cz.cvut.fit.tjv.rocnamic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public abstract class EntityWithId<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue
    protected ID id;

    @JsonIgnore
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        EntityWithId<ID> e = (EntityWithId<ID>) obj;

        return getId() != null ? getId().equals(e.getId()) : e.getId() == null;
    }
}
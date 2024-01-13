package cz.cvut.fit.tjv.rocnamic.api.converter;

public abstract class DtoWithId<ID> {
    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}

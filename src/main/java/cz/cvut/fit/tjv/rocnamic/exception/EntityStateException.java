package cz.cvut.fit.tjv.rocnamic.exception;

public class EntityStateException extends RuntimeException {
    public <E> EntityStateException(E entity) {
        super("Illegal state of entity " + entity);
    }
}
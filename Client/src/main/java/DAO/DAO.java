package DAO;

import java.util.List;

public abstract class DAO<T> {
    public abstract void insert(T adr);
    public abstract T getById(int id);
    public abstract void update(T adr);
    public abstract void delete(T adr);
    public abstract List<T> getAll();
}
package evg.testt.service.impl;

import evg.testt.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseService <T , P extends JpaRepository<T,Integer>> implements Service<T> {

    protected  P dao;

    protected EntityManager em;

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Autowired
    public void setPersistence(P dao){
        this.dao = dao;
    }

    public List<T> getAll() throws SQLException {
        return dao.findAll();
    }

    public T getById(Integer id) throws SQLException {
        return dao.findOne(id);
    }

    public void delete(T o) throws SQLException {
        dao.delete(o);
    }

     public void insert(T o) throws SQLException {
        dao.save(o);
    }

    public void update(T o) throws SQLException {
        dao.save(o);
    }

    public boolean isExists(Integer id) throws SQLException {
        return dao.exists(id);
    }
}

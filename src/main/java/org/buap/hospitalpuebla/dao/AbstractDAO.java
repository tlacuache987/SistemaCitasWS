package org.buap.hospitalpuebla.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import org.buap.hospitalpuebla.model.Cita;

public abstract class AbstractDAO<T> {

    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, ((Cita) id).getIdCita());
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

}

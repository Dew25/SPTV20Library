/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    
    
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public T find(Long id){
        return getEntityManager().find(entityClass,id);
    };
    
    public List<T> findAll(){
        try {
            return getEntityManager().createQuery("SELECT entity FROM "+entityClass.getName()+" entity").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    };
    public void create(T entity){
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
    };
    public void edit(T entity){
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit(); 
    };
    
}

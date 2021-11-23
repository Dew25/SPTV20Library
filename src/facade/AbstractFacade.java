/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTV20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public T find(Long id){
        return em.find(entityClass,id);
    };
    
    public List<T> findAll(){
        try {
            return em.createQuery("SELECT entity FROM "+entityClass.getName()+" entity").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    };
    public void create(T entity){
        em.persist(entity);
    };
    public void edit(T entity){
        em.merge(entity);
    };
    
}

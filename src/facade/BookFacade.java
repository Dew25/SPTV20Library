/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class BookFacade extends AbstractFacade<Book>{
private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTV20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public BookFacade(Class<Book> entityClass) {
        super(entityClass);
    }
    
}

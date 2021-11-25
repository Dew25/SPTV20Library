/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import entity.History;
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
public class HistoryFacade extends AbstractFacade<History>{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTV20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
    }

    public History findHistoryByBook(Book book) {
        try {
            return (History) em.createQuery("SELECT h FROM History h WHERE h.book = :book")
                    .setParameter("book", book)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<History> findListGiverBooks() {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.returnBook = null")
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    
}

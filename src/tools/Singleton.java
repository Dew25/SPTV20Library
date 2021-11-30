/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class Singleton {
     private static Singleton instance;
     private EntityManager em;

    private Singleton() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTV20LibraryPU");
        em = emf.createEntityManager();
    }
     
     public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
     }

    public EntityManager getEntityManager() {
        return em;
    }

   
     
}

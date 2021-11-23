/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Author;

/**
 *
 * @author user
 */
public class AuthorFacade extends AbstractFacade<Author>{
    
    public AuthorFacade(Class<Author> entityClass) {
        super(entityClass);
    }
    
}

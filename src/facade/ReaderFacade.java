/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Reader;

/**
 *
 * @author user
 */
public class ReaderFacade extends AbstractFacade<Reader>{
    
    public ReaderFacade(Class<Reader> entityClass) {
        super(entityClass);
    }
    
}

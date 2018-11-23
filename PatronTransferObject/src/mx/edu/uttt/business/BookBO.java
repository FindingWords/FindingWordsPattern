/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uttt.business;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mx.edu.uttt.dao.BookDAO;
import mx.edu.uttt.trasnfer.BookVO;

/**
 *
 * @author galaxias
 */
public class BookBO {
   //Creacion de clase BookBo
    public void insertBooks(BookVO book){
        
        try {
            BookDAO bookDao=new BookDAO();
            bookDao.insert(book);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookBO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

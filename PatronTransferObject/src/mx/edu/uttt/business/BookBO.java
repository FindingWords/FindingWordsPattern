/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uttt.business;

import java.sql.SQLException;
import java.util.List;
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

    List<BookVO> Books;

    public void insertBooks(BookVO book) {

        BookDAO bookDao = new BookDAO();
        bookDao.insert(book);
        if (bookDao.insert(book)) {
            JOptionPane.showMessageDialog(null, "El libro se regiistro con exito");
        } else {
            JOptionPane.showMessageDialog(null, "Fallo al registrar");
        }
    }

    public List<BookVO> getAllBooks() {
        BookDAO bookDao = new BookDAO();
        return Books = bookDao.getAllBook();
    }

}

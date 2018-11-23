/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uttt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.uttt.trasnfer.BookVO;

/**
 *
 * @author galaxias
 */
public class BookDAO extends Configuration{
    
// DECALRACION DE VARIABLES PARA LA CONEXION
    private Connection conexion;
    private PreparedStatement pdst;

    public BookDAO() throws ClassNotFoundException, SQLException{
        try {
            Class.forName(DRIVER);
            this.conexion = DriverManager.getConnection(URL, usuario, password);
            this.conexion.setAutoCommit(false);
            System.out.println("Conexion Abierta");
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    //INSERTAR LIBROS
    //POR MEDIO DEL MODELO (OBJETO)
    public boolean insert(BookVO book) {
        boolean respuesta = false;
        int expResp = 0;
        try {
            String sql = 
   "INSERT INTO book (title,autor,editorial,dateed,page,cetegorie) VALUES (?,?,?,?,?,?)";
            pdst = this.conexion.prepareStatement(sql);
            pdst.setString(1, book.getTitle());
            pdst.setString(2, book.getAutor());
            pdst.setString(3, book.getEditorial());
            pdst.setDate(4, (Date) book.getDateed());
            pdst.setInt(5, book.getPage());
            pdst.setString(6,book.getCategorie());
                    
            expResp = pdst.executeUpdate();
            this.conexion.commit();
            if (expResp > 0) {
                respuesta = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return respuesta;
        }
    }
    
    
    //CONSULTA TODOS LOS LIBROS
    public List<BookVO> getAllBook() {
        List<BookVO> lisBook = new ArrayList();

        try {
            ResultSet rs = null;

            pdst = this.conexion.prepareStatement("SELECT * FROM book");
            rs = pdst.executeQuery();

            while (rs.next()) {
                BookVO book = new BookVO();
                book.setIdbook(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAutor(rs.getString(3));
                book.setEditorial(rs.getString(4));
                book.setDateed(rs.getDate(5));
                book.setPage(rs.getInt(6));
                book.setCategorie(rs.getString(7));

                lisBook.add(book);
            }
            rs.close();
            this.conexion.close();
            return lisBook;
        } catch (SQLException ex) {
            return null;
        } finally {
            return lisBook;
        }
    }

    //MODIFICA EL ID
    public int getMaxId() {
        int id = 0;
        try {
            ResultSet rs = null;
            pdst = this.conexion.prepareStatement("SELECT max (idbook) FROM book");
            rs = pdst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                System.out.println(id);
            }
            rs.close();
            this.conexion.close();
        } catch (SQLException ex) {
            return 0;
        } finally {
            return id;
        }
    }
    
    //ELIMINAR CATEGORIA
    public boolean deleteBook(int idbook) {
        boolean respuesta = false;
        int expResp = 0;
        try {
            String sql = "DELETE FROM book WHERE idbook = '" + idbook + "'";
            pdst = this.conexion.prepareStatement(sql);
            expResp = pdst.executeUpdate();
            this.conexion.commit();
            if (expResp > 0) {
                respuesta = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return respuesta;
        }
    }
    
}

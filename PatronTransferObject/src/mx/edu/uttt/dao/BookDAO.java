
package mx.edu.uttt.dao;

import java.sql.CallableStatement;
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

public class BookDAO extends Configuration{
    
// DECALRACION DE VARIABLES PARA LA CONEXION
    private Connection conexion;
    private PreparedStatement pdst;
 
    //INSERTAR LIBROS
    //POR MEDIO DEL MODELO (OBJETO) pdst = this.conexion.prepareStatement(sql);
    public boolean insert(BookVO book) {
        boolean respuesta = false;
        int expResp = 0;
        try {
            String sql = "INSERT INTO book (title,autor,editorial,dateed,pages,categorie) VALUES (?,?,?,?,?,?);";
            
             Connection con=new Configuration().obtenerConexion();
              PreparedStatement ps= con.prepareCall(sql);
           
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAutor());
            ps.setString(3, book.getEditorial());
            ps.setDate(4, (Date) book.getDateed());
            ps.setInt(5, book.getPages());
            ps.setString(6,book.getCategorie());
                    
            expResp = ps.executeUpdate();
            
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
  Connection con=new Configuration().obtenerConexion();
         PreparedStatement pdst=con.prepareStatement("SELECT * FROM book");
            rs = pdst.executeQuery();

          
             
            while (rs.next()) {
                BookVO book = new BookVO();
                book.setIdbook(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAutor(rs.getString(3));
                book.setEditorial(rs.getString(4));
                book.setDateed(rs.getDate(5));
                book.setPages(rs.getInt(6));
                book.setCategorie(rs.getString(7));

                lisBook.add(book);
            }
            rs.close();
            con.close();
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
    
//ELIMINA EL LIBRO
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
    
    
    public BookVO getBook(int idbook) {
        BookVO book = new BookVO();
        try {
            PreparedStatement consultaBook;
            ResultSet rs = null;
            consultaBook = this.conexion.prepareStatement("SELECT * FROM book WHERE nomcat = '" + idbook + "'");
            rs = consultaBook.executeQuery();

            while (rs.next()) {               
                book.setIdbook(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAutor(rs.getString(3));
                book.setEditorial(rs.getString(4));
                book.setDateed(rs.getDate(5));
                book.setPages(rs.getInt(6));
                book.setCategorie(rs.getString(7));
                
                System.out.println(book.getIdbook() + book.getTitle());
            }
            rs.close();
            this.conexion.close();
            return book;

        } catch (SQLException ex) {
            return null;
        } finally {
            return book;
        }
    }
    
    // 
    public boolean deleteBook() {
        boolean respuesta = false;
        int expResp = 0;
        try {
            String sql = "DELETE FROM book";
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

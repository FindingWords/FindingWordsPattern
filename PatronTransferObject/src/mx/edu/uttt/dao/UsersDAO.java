/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uttt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.uttt.trasnfer.UsersVO;

/**
 *
 * @author galaxias
 */
public class UsersDAO extends Configuration {

    // CONFIGURACION DE LA BASE DE DATOS
    private Connection conexion;
    private PreparedStatement pdst;

    public UsersDAO() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);
            this.conexion = DriverManager.getConnection(URL, usuario, password);
            this.conexion.setAutoCommit(false);
            System.out.println("Conexion Abierta with Finding Words");
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insert(UsersVO user) {
        boolean respuesta = false;
        int expResp = 0;
        try {
            String sql = "INSERT INTO users (passworder,named,maill) VALUES (?,?,?)";

            pdst = this.conexion.prepareStatement(sql);
            
            pdst.setString(1, user.getPassworder());
            pdst.setString(2, user.getNamed());
            pdst.setString(3, user.getMaill());
            expResp = pdst.executeUpdate();
            this.conexion.commit();
            if (expResp > 0) {
                respuesta = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return respuesta;
        }
    }
    
    
    }
      
    



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uttt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author galaxias
 */
public class Configuration {
  
  
   public Connection obtenerConexion(){
        Connection con=null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/findingwords","root","1234");
        } catch (ClassNotFoundException e){
            System.err.println(e.getMessage());
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }finally {
            return con;
        }
    }
  
}

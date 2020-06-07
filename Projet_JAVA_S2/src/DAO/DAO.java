/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;

/**
 *
 * @author Nassim
 */
public abstract class DAO <T>
{
    protected Connection connect = null;
    
    // Constructeur prenant en paramètre une connexion
    public DAO (Connection conn)
    {
        connect = conn; 
    }
    
    // Constructeur qui crée lui-même la connexion
    public DAO ()
    {
        try {
            // db parameters - ptest is the name of the database
            String url       = "jdbc:mysql://localhost:3306/projetjava2020";  //Remplqcer test_java_01 par le nom de la database dans PHPMyadmin
            String user      = "root";
            String password  = "root";

            // create a connection to the database
            connect = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
    }
    
    // Méthodes abstraites à implémenter par les classes filles
    public abstract int create (T obj);
    public abstract int update (T obj);
    public abstract int delete (T obj);
    public abstract T find (int id);
    
    
            
        
}

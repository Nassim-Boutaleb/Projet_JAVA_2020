/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modele.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Nassim Boutaleb , Oumou Sow , Grace Gnenago
 */
public class CoursDAO extends DAO{
    public CoursDAO (Connection conn)
    {
        super(conn);
    }
    
    /**
     *
     */
    public CoursDAO ()
    {
        super();
    }

    @Override
    public boolean create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
     /**
     *
     * @param id
     * @return
     */
    @Override
    public Cours find(int id) 
    {
        Cours co;
        co = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Cours en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM cours WHERE id = '"+id+"'");  
            
            // sinon récupérer Cours et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rs.getString(2);
                
                // Créer Cours
                co = new Cours(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Cours rempli ou null
        return co;
    }
    
    // rechercher un TypeCoursDAO à partir de son id
    // rend null si non trouvé 

    /**
     *
     * @param idc
     * @return
     */
    public Cours find (String nom)
    {
        Cours co;
         co = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Cours en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM cours WHERE nom = '"+nom+"'");  

            
            // sinon récupérer Cours et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rs.getInt(1);
                
                
                // Créer Cours
                co = new Cours(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Cours rempli ou null
        return co;
    }
    
    
    
}

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
 * @author Oumou Sow, Grace Gnenago, Nassim Boutaleb
*/
public class TypeCoursDAO extends DAO
{
    
    public TypeCoursDAO (Connection conn)
    {
        super(conn);
    }
    
    public TypeCoursDAO ()
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
    // rechercher un TypeCoursDAO à partir de son id
    // rend null si non trouvé 
    @Override
    public TypeCours find(int id) 
    {
        TypeCours ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer TypeCoursDAO en fonction de son id
            ResultSet rs=stmt.executeQuery("SELECT * FROM typecours WHERE id = '"+id+"'");  

            
            // sinon récupérer TypeCoursDAO et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rs.getString(2);
               
                // Créer TypeCoursDAO
                ut = new TypeCours(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner TypeCoursDAO rempli ou null
        return ut;
    }
    


    /**
     *
     * @param nom
     * @return
     */
    public TypeCours find (String nom)
    {
        TypeCours ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer TypeCoursDAO en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM typecours WHERE nom = '"+nom+"'");  
            
            // sinon récupérer TypeCoursDAO et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rs.getInt(1);
                
                
                // Créer TypeCoursDAO
                ut = new TypeCours(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner TypeCoursDAO rempli ou null
        return ut;
    }
}

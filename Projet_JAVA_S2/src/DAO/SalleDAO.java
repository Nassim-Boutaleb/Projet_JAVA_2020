/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modele.*;
import java.sql.*;

/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class SalleDAO extends DAO 
{
    
    public SalleDAO (Connection conn)
    {
        super(conn);
    }
    
    public SalleDAO ()
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
    public Salle find(int id) 
    {
        Salle salle = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Salle en fonction de son id
            ResultSet rs=stmt.executeQuery("SELECT * FROM salle WHERE id = '"+id+"'");  
            
            // sinon récupérer Salle et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rs.getString(2);
                int capacite = rs.getInt(3);
                
                int id_site=rs.getInt(4);
                Site site = Site.charger_site_BDD(id_site);
                
                // Créer Salle
                salle = new Salle(id,nom,capacite,site);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Salle rempli ou null
        return salle;    
    }
    
  

    /**
     *
     * @param nom
     * @return
     */
    public Salle find (String nom)
    {
        Salle salle = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Salle en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM salle WHERE nom = '"+nom+"'");  
            
            // sinon récupérer Salle et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rs.getInt(1);
                int capacite = rs.getInt(3);
                
                int id_site=rs.getInt(4);
                Site site = Site.charger_site_BDD(id_site);
                
                // Créer Salle
                salle = new Salle(id,nom,capacite,site);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Salle rempli ou null
        return salle;
    }
    
}

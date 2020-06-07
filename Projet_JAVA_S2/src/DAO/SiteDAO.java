/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modele.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class SiteDAO extends DAO <Site>
{
    
    public SiteDAO (Connection conn)
    {
        super(conn);
    }
    
    public SiteDAO ()
    {
        super();
    }

    @Override
    public int create(Site site) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Site site) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Site site) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Site find(int id) {
        Site ut = null;
        try 
        {
            Statement stmtSi1=connect.createStatement(); 

            // récupérer Site en fonction de son id
            ResultSet rsSi1=stmtSi1.executeQuery("SELECT * FROM site WHERE id = '"+id+"'");  

            
            // sinon récupérer Site et le rendre
            while (rsSi1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rsSi1.getString(2);
               
                // récupérer la liste des salles sur le site
                Statement stmtSi2=connect.createStatement();
                ResultSet rsSi2=stmtSi2.executeQuery("SELECT * FROM salle WHERE idSite = '"+id+"'");
                ArrayList<Salle> liste_salles = new ArrayList<>(100);
                while (rsSi2.next())
                {
                    int idSalle = rsSi2.getInt(1);
                    String nomSalle = rsSi2.getString(2);
                    int capaciteSalle = rsSi2.getInt(3);
                    Salle s = new Salle(idSalle, nomSalle, capaciteSalle,id);
                    liste_salles.add(s);
                }
                
                // Créer Site
                ut = new Site(id,nom,liste_salles);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Site rempli ou null
        return ut;
    }
    
    
    // rechercher un Site à partir de son id
    // rend null si non trouvé 

    /**
     *
     * @param nom
     * @return
     */
    public Site find (String nom)
    {
        Site ut = null;
        try 
        {
            Statement stmtSi1=connect.createStatement(); 

            // récupérer Site en fonction de son email
            ResultSet rsSi1=stmtSi1.executeQuery("SELECT * FROM site WHERE nom = '"+nom+"'");  

            
            // sinon récupérer Site et le rendre
            while (rsSi1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsSi1.getInt(1);
                
                // récupérer la liste des salles sur le site
                Statement stmtSi2=connect.createStatement();
                ResultSet rsSi2=stmtSi2.executeQuery("SELECT * FROM salle WHERE idSite = '"+id+"'");
                ArrayList<Salle> liste_salles = new ArrayList<>(100);
                while (rsSi2.next())
                {
                    int idSalle = rsSi2.getInt(1);
                    String nomSalle = rsSi2.getString(2);
                    int capaciteSalle = rsSi2.getInt(3);
                    Salle s = new Salle(idSalle, nomSalle, capaciteSalle,id);
                    liste_salles.add(s);
                }
              
                
                // Créer Site
                ut = new Site(id,nom,liste_salles);
            }
            

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Site rempli ou null
        return ut;
    }
    
    // cette méthode rend une liste avec tous les sites de la BDD
    public ArrayList <Site> getAllSites ()
    {
        Site ut = null;
        ArrayList <Site> liste_sites = new ArrayList<>(100);
        try 
        {
            Statement stmtSi1=connect.createStatement(); 

            // récupérer Site en fonction de son email
            ResultSet rsSi1=stmtSi1.executeQuery("SELECT * FROM site ");  

            
            // sinon récupérer Site et le rendre
            while (rsSi1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsSi1.getInt(1);
                String nom = rsSi1.getString(2);
                
                // récupérer la liste des salles sur le site
                Statement stmtSi2=connect.createStatement();
                ResultSet rsSi2=stmtSi2.executeQuery("SELECT * FROM salle WHERE idSite = '"+id+"'");
                ArrayList<Salle> liste_salles = new ArrayList<>(100);
                while (rsSi2.next())
                {
                    int idSalle = rsSi2.getInt(1);
                    String nomSalle = rsSi2.getString(2);
                    int capaciteSalle = rsSi2.getInt(3);
                    Salle s = new Salle(idSalle, nomSalle, capaciteSalle,id);
                    liste_salles.add(s);
                }
                
                // Créer Site
                ut = new Site(id,nom,liste_salles);
                
                // L'ajouter dans la liste
                liste_sites.add (ut);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Site rempli ou null
        return liste_sites;
    }
}
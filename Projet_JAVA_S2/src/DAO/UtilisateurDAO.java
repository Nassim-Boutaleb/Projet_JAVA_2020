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
 * @author Nassim
 */
public class UtilisateurDAO extends DAO<Utilisateur>
{

    public UtilisateurDAO (Connection conn)
    {
        super(conn);
    }
    
    public UtilisateurDAO ()
    {
        super();
    }
    
    @Override
    public int create(Utilisateur util)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Utilisateur util)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Utilisateur util) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Utilisateur find(int id) 
    {
        Utilisateur ut = null;
        try 
        {
            Statement stmtU1=connect.createStatement(); 

            // récupérer l'utilisateur en fonction de son id
            ResultSet rsU1=stmtU1.executeQuery("SELECT * FROM utilisateur WHERE id = '"+id+"'");  

            
            // sinon récupérer l'utilisateur et le rendre
            while (rsU1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String email = rsU1.getString(2);
                String passwd = rsU1.getString(3);
                String nom = rsU1.getString(4);
                String prenom = rsU1.getString(5);
                int droit = rsU1.getInt(6);
                
                // Créer l'utilisateur
                ut = new Utilisateur(id, email, passwd, nom, prenom, droit);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'utilisateur rempli ou null
        return ut;
    }
    
    // rechercher un utilisateur à partir de son email
    // rend null si non trouvé 
    public Utilisateur find (String email)
    {
        Utilisateur ut = null;
        try 
        {
            Statement stmtU1=connect.createStatement(); 

            // récupérer l'utilisateur en fonction de son email
            ResultSet rsU1=stmtU1.executeQuery("SELECT * FROM utilisateur WHERE email = '"+email+"'");  

            // regarder si on a des résultats :
            // récupération du résultat de l'ordre
            ResultSetMetaData rsetMeta = rsU1.getMetaData();

            // calcul du nombre de colonnes  (inutile ici)
            //int nbColonne = rsetMeta.getColumnCount();
            
            // sinon récupérer l'utilisateur et le rendre
            while (rsU1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsU1.getInt(1);
                String passwd = rsU1.getString(3);
                String nom = rsU1.getString(4);
                String prenom = rsU1.getString(5);
                int droit = rsU1.getInt(6);
                
                // Créer l'utilisateur
                ut = new Utilisateur(id, email, passwd, nom, prenom, droit);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'utilisateur rempli ou null
        return ut;
    }
    
    public Utilisateur find_nom_prenom (String nom, String prenom)
    {
        Utilisateur ut = null;
        try 
        {
            Statement stmtU1=connect.createStatement(); 

            // récupérer l'utilisateur en fonction de son email
            ResultSet rsU1=stmtU1.executeQuery("SELECT * FROM utilisateur WHERE Nom = '"+nom+"' AND Prenom = '"+prenom+"'");  

            // regarder si on a des résultats :
            // récupération du résultat de l'ordre
            ResultSetMetaData rsetMeta = rsU1.getMetaData();

            // calcul du nombre de colonnes  (inutile ici)
            //int nbColonne = rsetMeta.getColumnCount();
            
            // sinon récupérer l'utilisateur et le rendre
            while (rsU1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsU1.getInt(1);
                String passwd = rsU1.getString(3);
                String email = rsU1.getString(2);
                int droit = rsU1.getInt(6);
                
                // Créer l'utilisateur
                ut = new Utilisateur(id, email, passwd, nom, prenom, droit);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'utilisateur rempli ou null
        return ut;
    }
    
}

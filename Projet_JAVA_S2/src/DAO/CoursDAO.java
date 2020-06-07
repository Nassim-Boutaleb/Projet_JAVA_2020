/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modele.*;
import java.awt.Dimension;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nassim Boutaleb , Oumou Sow , Grace Gnenago
 */
public class CoursDAO extends DAO <Cours>
{
    public CoursDAO (Connection conn)
    {
        super(conn);
    }
    
    /**
     * Constructeur qui crée la connexion 
     */
    public CoursDAO ()
    {
        super();
    }

    @Override
    public int create(Cours c) 
    {
        int success = 0;  // 0= erreur requete,1= réussite , 99 = doublons
        
        // Récupérer le nom du nouveau cours
        String nom = c.getNom();
        
        // D'abord une vérification de doublons car nom n'est pas clé  !
        try 
        {
            Statement stmtC2=connect.createStatement(); 

            // récupérer Cours en fonction de son nom
            ResultSet rsC2=stmtC2.executeQuery("SELECT * FROM cours WHERE Nom = '"+nom+"'");  

            // sinon récupérer Cours et le rendre
            while (rsC2.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {      
                success = 99 ; // existe déjà !!
            }    
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
              
        
        // L'insérer dans la BDD
        if (success == 0)
        {
            try 
            {
                Statement stmtC1=connect.createStatement(); 

                // récupérer Cours en fonction de son nom
                success =stmtC1.executeUpdate("INSERT INTO cours (nom) VALUES ('"+nom+"')");
                
                

            }
            catch(SQLException e) 
            {
                System.out.println(e.getMessage());
            } 
            
        }
        
        
        return success;
        
    }

    @Override
     public int update(Cours c) 
     {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

     }
    
    public int update(Cours c, String nouveauNom) 
    {
        int success = 0;  // 0= erreur requete,1= réussite , 99 = doublons
        
        // Récupérer le nom du nouveau cours
        String nom = c.getNom();
        
        // D'abord une vérification de doublons car nom n'est pas clé  !
        try 
        {
            Statement stmtC2=connect.createStatement(); 

            // récupérer Cours en fonction de son nom
            ResultSet rsC2=stmtC2.executeQuery("SELECT * FROM cours WHERE Nom = '"+nouveauNom+"'");  

            // sinon récupérer Cours et le rendre
            while (rsC2.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {      
                success = 99 ; // existe déjà !!
            }    
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
              
        
        // Modifier dans dans la BDD
        if (success == 0)
        {
            try 
            {
                Statement stmtC1=connect.createStatement(); 

                // récupérer Cours en fonction de son nom
                success =stmtC1.executeUpdate("UPDATE cours SET Nom = '"+nouveauNom+"' WHERE Nom = '"+nom+"'");  

                connect.close();
            }
            catch(SQLException e) 
            {
                System.out.println(e.getMessage());
            } 
        }
        
        return success;
    }

    @Override
    public int delete(Cours cours) 
    {
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
            Statement stmtC1=connect.createStatement(); 

            // récupérer Cours en fonction de son nom
            ResultSet rsC1=stmtC1.executeQuery("SELECT * FROM cours WHERE id = '"+id+"'");  
            
            // sinon récupérer Cours et le rendre
            while (rsC1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rsC1.getString(2);
                
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
    
 

    /**
     *
     * @param nom
     * @return
     */
    public Cours find (String nom)
    {
        Cours co;
         co = null;
        try 
        {
            Statement stmtC1=connect.createStatement(); 

            // récupérer Cours en fonction de son nom
            ResultSet rsC1=stmtC1.executeQuery("SELECT * FROM cours WHERE nom = '"+nom+"'");  

            
            // sinon récupérer Cours et le rendre
            while (rsC1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsC1.getInt(1);
                
                
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
    
    /**
     * Cette méthode retourne la liste de tous les cours de la BDD
     * @return
     */
    public ArrayList <Cours> get_all_cours ()
    {
        ArrayList <Cours> liste_cours = new ArrayList<>(100);
        Cours cours = null;
        
        try 
        {
            Statement stmtC1=connect.createStatement(); 

            // récupérer Groupe en fonction de son nom
            ResultSet rsC1=stmtC1.executeQuery("SELECT * FROM cours ");  

            
            // sinon récupérer Groupe et le rendre
            while (rsC1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsC1.getInt(1);
                String nom = rsC1.getString(2);
                
                
                // Créer Groupe
                cours = new Cours(id,nom);
                
                // l'ajouter à la liste des groupes
                liste_cours.add(cours);
            }

              
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        return liste_cours;
        
    }
    
    
    
}

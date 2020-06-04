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
 * @author adjasow
 */
public class EnseignantDAO extends DAO
{
public EnseignantDAO (Connection conn)
{
  super (conn);
}


 public EnseignantDAO ()
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
    public boolean delete(){
            throw new UnsupportedOperationException("Not supported yet.");
}

   
    /**
     *
     * @param id
     * @return
     */
    
    @Override
   public Enseignant find(int id) 
   {
        Enseignant ut = null;
        
        // récupérer l'utilisateur associé ainsi que les données
        Utilisateur utilisat = Utilisateur.charger_utilisateur_BDD(id);
        String email = utilisat.getEmail();
        String passwd = utilisat.getPasswd();
        String nom = utilisat.getNom();
        String prenom = utilisat.getPrenom();
        int droit = utilisat.getDroit();
            
        try 
        {
            Statement stmt=connect.createStatement(); 

            // compteur de résultat
            int cpt = 0;
            
            // récupérer l'Enseignant en fonction de son email
            ResultSet rs=stmt.executeQuery("SELECT * FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 
            
            // Stocker les cours
            ArrayList <Cours> liste_cours = new ArrayList (500);
            
            // sinon récupérer l'Enseignant et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                cpt ++ ; // on a trouvé un enseignant
                int id_cours=rs.getInt(2);
                Cours cours = Cours.charger_cours_BDD(id_cours);
                liste_cours.add(cours);
                
            }
            
            if (cpt != 0)
            {
                // Créer l'Enseignant
                ut = new Enseignant(id, email, passwd, nom, prenom, droit,liste_cours);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Enseignant rempli ou null
        return ut;
    }

    
    // rechercher un utilisateur à partir de son email
    // rend null si non trouvé 
    public Enseignant find (String email)
    {
        Enseignant ut = null;
        
        // récupérer l'utilisateur associé ainsi que les données
        Utilisateur utilisat = Utilisateur.charger_utilisateur_BDD(email);
        int id = utilisat.getId();
        String passwd = utilisat.getPasswd();
        String nom = utilisat.getNom();
        String prenom = utilisat.getPrenom();
        int droit = utilisat.getDroit();
        
        
        try 
        {
            Statement stmt=connect.createStatement(); 

            // compteur de résultat
            int cpt = 0;
            
            // récupérer l'Enseignant en fonction de son email
            ResultSet rs=stmt.executeQuery("SELECT * FROM enseignant WHERE IdUtilisateur = '"+id+"'");  

            // Stocker les cours
            ArrayList <Cours> liste_cours = new ArrayList (500);
            
            // sinon récupérer l'Enseignant et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                
                cpt ++;
                int id_cours=rs.getInt(2);
                Cours cours = Cours.charger_cours_BDD(id_cours);
                liste_cours.add(cours);
                
            }
            
            if (cpt != 0)
            {
                // Créer l'Enseignant
                ut = new Enseignant(id, email, passwd, nom, prenom, droit,liste_cours);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Enseignant rempli ou null
        return ut;
    }
    
    public Enseignant find(Utilisateur utilisat) 
   {
        Enseignant ens = null;
        
        // récupérer les données de l'utilisateur
        int id = utilisat.getId();
        String email = utilisat.getEmail();
        String passwd = utilisat.getPasswd();
        String nom = utilisat.getNom();
        String prenom = utilisat.getPrenom();
        int droit = utilisat.getDroit();
         
        
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer l'Etudiant en fonction de son email
            ResultSet rs=stmt.executeQuery("SELECT * FROM etudiant WHERE IdUtilisateur = '"+id+"'");  

            // compteur de résultat
            int cpt = 0;
            
             // Stocker les cours
            ArrayList <Cours> liste_cours = new ArrayList (500);
            
            // sinon récupérer l'Etudiant et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                cpt ++;
                int id_cours=rs.getInt(2);
                Cours cours = Cours.charger_cours_BDD(id_cours);
                liste_cours.add(cours);
                
            }
            
            if (cpt != 0)
            {
                // Créer l'Enseignant
                ens = new Enseignant(id, email, passwd, nom, prenom, droit,liste_cours);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Etudiant rempli ou null
        return ens;
    }
    
}

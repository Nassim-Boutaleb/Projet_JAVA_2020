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
 * @author Oumou Sow, Grace Gnenago, Nassim Boutaleb
 */
public class EnseignantDAO extends DAO<Enseignant>
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
    public int create(Enseignant ens) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Enseignant ens) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Enseignant ens)
    {
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
        System.out.println(id);
        Utilisateur utilisat = Utilisateur.charger_utilisateur_BDD(id);
        String email = utilisat.getEmail();
        String passwd = utilisat.getPasswd();
        String nom = utilisat.getNom();
        String prenom = utilisat.getPrenom();
        int droit = utilisat.getDroit();
            
        try 
        {
            Statement stmt=connect.createStatement(); 

            
            // récupérer l'Enseignant en fonction de son id attention l'id apparait plusieurs fois donc distinct
            ResultSet rsE2=stmt.executeQuery("SELECT distinct(IdUtilisateur) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 

            while (rsE2.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                // Créer l'Enseignant (sans les cours)
                ut = new Enseignant(id, email, passwd, nom, prenom, droit);
                
            }
            
            // une fois l'enseignant créé (si créé), on va récupérer ses cours avec une nouvelle requete
            if (ut != null)
            {
                // récupérer les cours de l'enseignant
                ResultSet rsE3 =stmt.executeQuery("SELECT distinct(IdCours) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 
                
                while (rsE3.next())  
                {
                    // Créer le cours
                    int id_cours = rsE3.getInt(1);
                    Cours cours = Cours.charger_cours_BDD(id_cours);

                    // L'ajouter dans ens
                    ut.addCours(cours);
                }
            
            }
            
     
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Enseignant rempli ou null
        return ut;
    }

    


    /**
     * Recherche un enseignant a partir de son nom et prénom
     * @param nom
     * @param prenom
     * @return
     */
    public Enseignant find_nom_prenom (String nom , String prenom)
    {
        Enseignant ut = null;
        
        // récupérer l'utilisateur associé ainsi que les données
        Utilisateur utilisat = Utilisateur.charger_utilisateur_BDD_nom_prenom(nom, prenom);
        int id = utilisat.getId();
        String passwd = utilisat.getPasswd();
        String email = utilisat.getEmail();
        int droit = utilisat.getDroit();
        
        
        try 
        {
            Statement stmt=connect.createStatement(); 

            
            // récupérer l'Enseignant en fonction de son id attention l'id apparait plusieurs fois donc distinct
            ResultSet rsE2=stmt.executeQuery("SELECT distinct(IdUtilisateur) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 

            while (rsE2.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                // Créer l'Enseignant (sans les cours)
                ut = new Enseignant(id, email, passwd, nom, prenom, droit);
                
            }
            
            // une fois l'enseignant créé (si créé), on va récupérer ses cours avec une nouvelle requete
            if (ut != null)
            {
                // récupérer les cours de l'enseignant
                ResultSet rsE3 =stmt.executeQuery("SELECT distinct(IdCours) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 
                
                while (rsE3.next())  
                {
                    // Créer le cours
                    int id_cours = rsE3.getInt(1);
                    Cours cours = Cours.charger_cours_BDD(id_cours);

                    // L'ajouter dans ens
                    ut.addCours(cours);
                }
            
            }
            
     
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Enseignant rempli ou null
        return ut;
    }
    

    
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

            
            // récupérer l'Enseignant en fonction de son id attention l'id apparait plusieurs fois donc distinct
            ResultSet rsE2=stmt.executeQuery("SELECT distinct(IdUtilisateur) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 

            while (rsE2.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                // Créer l'Enseignant (sans les cours)
                ut = new Enseignant(id, email, passwd, nom, prenom, droit);
                
            }
            
            // une fois l'enseignant créé (si créé), on va récupérer ses cours avec une nouvelle requete
            if (ut != null)
            {
                // récupérer les cours de l'enseignant
                ResultSet rsE3 =stmt.executeQuery("SELECT distinct(IdCours) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 
                
                while (rsE3.next())  
                {
                    // Créer le cours
                    int id_cours = rsE3.getInt(1);
                    Cours cours = Cours.charger_cours_BDD(id_cours);

                    // L'ajouter dans ens
                    ut.addCours(cours);
                }
            
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

            
            // récupérer l'Enseignant en fonction de son id attention l'id apparait plusieurs fois donc distinct
            ResultSet rsE2=stmt.executeQuery("SELECT distinct(IdUtilisateur) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 
            
            // Stocker les cours
            ArrayList <Cours> liste_cours = new ArrayList (500);
            
           
            while (rsE2.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                // Créer l'Enseignant (sans les cours)
                ens = new Enseignant(id, email, passwd, nom, prenom, droit);
                
            }
            
            // une fois l'enseignant créé (si créé), on va récupérer ses cours avec une nouvelle requete
            if (ens != null)
            {
                // récupérer les cours de l'enseignant
                ResultSet rsE3 =stmt.executeQuery("SELECT distinct(IdCours) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 
                
                while (rsE3.next())  
                {
                    // Créer le cours
                    int id_cours = rsE3.getInt(1);
                    Cours cours = Cours.charger_cours_BDD(id_cours);

                    // L'ajouter dans ens
                    ens.addCours(cours);
                }
            
            }
            
     
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Enseignant rempli ou null
        return ens;
    }
    
    // Cette méthode renvoie une arrayList avec tous les ENSEIGNANTS stockés en BDD
    public ArrayList <Enseignant> getAllEnseignants ()
    {
        ArrayList<Enseignant> liste_enseignants = new ArrayList<>(100);
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer tous les enseignants attention un même id peut apparaitre plusieurs fois
            ResultSet rsE2=stmt.executeQuery("SELECT distinct (IdUtilisateur) FROM enseignant ");  

            while (rsE2.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsE2.getInt(1);

                // Il faut maintenant récupérer les infos contenues dans la table utilisateur à partir de l'ID
                Utilisateur utEtu = Utilisateur.charger_utilisateur_BDD(id);
                
                // Créer l'enseignant
                Enseignant ens = new Enseignant(id, utEtu.getEmail(), utEtu.getPasswd(), utEtu.getNom(), utEtu.getPrenom(), utEtu.getDroit());
                
                // Rechercher ses cours et les lui ajouter
                Statement stmtE3=connect.createStatement();
                ResultSet rsE3 =stmtE3.executeQuery("SELECT distinct(IdCours) FROM enseignant WHERE  IdUtilisateur = '"+id+"'"); 
                
                while (rsE3.next())  
                {
                    // Créer le cours
                    int id_cours = rsE3.getInt(1);
                    Cours cours = Cours.charger_cours_BDD(id_cours);

                    // L'ajouter dans ens
                    ens.addCours(cours);
                }   
                
                
                // Ajouter l'enseignant dans la liste des enseignants
                liste_enseignants.add(ens);
                
                
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
       
       return liste_enseignants;
    }
    
}

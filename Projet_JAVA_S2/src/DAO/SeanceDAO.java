/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modele.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 *
 * @author Oumou Sow, Nassim Boutaleb , Grace Gnenago
 */


public class SeanceDAO extends DAO{
    
    public SeanceDAO (Connection conn)
    {
        super(conn);
    }
    
    public SeanceDAO ()
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
    public Seance find(int id) 
    {
        
        Seance ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer la séance en fonction de son id
            ResultSet rs=stmt.executeQuery("SELECT * FROM seance WHERE id = '"+id+"'");  

            // compteur de résultat
            int cpt = 0;
            
            // Les variables
            int semaine = 0;
            LocalDate date = null;
            LocalTime heured = null;
            LocalTime heuref = null;
            int etat = 0;
            int id_cours = 0;
            int id_type = 0;
            Cours cours =null;
            TypeCours type = null ;
            
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                semaine = rs.getInt(2);
                
                java.sql.Date dateSQL = rs.getDate(3);
                date = dateSQL.toLocalDate();
                        
                java.sql.Time heuredSQL = rs.getTime(4);
                heured = heuredSQL.toLocalTime();
                
                java.sql.Time heurefSQL = rs.getTime(5);
                heuref = heurefSQL.toLocalTime();
                
                etat = rs.getInt(6);
                id_cours=rs.getInt(7);
                id_type=rs.getInt(8);
                
                cours= Cours.charger_cours_BDD(id_cours);
                type= TypeCours.charger_typecours_BDD(id_type);
                
            }
            
            // on a récupérer la séance reste à remplir les listes de groupes, salles, enseignants en visitant les tables 
            // correspondantes
            // déclaration des listes que l'on passera au constructeur
            ArrayList <Groupe> liste_groupes = new ArrayList <> (500); // liste des groupes issue de séances_groupes
            ArrayList <Salle> liste_salles = new ArrayList <> (500); // liste des salles issue de séances_salles
            ArrayList <Enseignant> liste_enseignants = new ArrayList <> (500); // liste des groupes issue de séances_enseignant
    
            // récupérer la liste des groupes
            rs=stmt.executeQuery("SELECT * FROM seance_groupes WHERE IdSeance = '"+id+"'");  
            
            while (rs.next())
            {
                int id_groupes = rs.getInt(2);
                Groupe groupe = Groupe.charger_groupe_BDD(id_groupes);
                liste_groupes.add (groupe);
            }
            
            // récupérer la liste des salles
            rs=stmt.executeQuery("SELECT * FROM seance_salle WHERE IdSeance = '"+id+"'");  
            
            while (rs.next())
            {
                int id_salles = rs.getInt(2);
                Salle salle = Salle.charger_salle_BDD(id_salles);
                liste_salles.add (salle);
            }
            
            // récupérer la liste des enseignants
            rs=stmt.executeQuery("SELECT * FROM seance_enseignant WHERE IdSeance = '"+id+"'");  
            
            while (rs.next())
            {
                int id_enseignants = rs.getInt(2);
                Enseignant enseign = Enseignant.charger_enseignant_BDD(id_enseignants);
                liste_enseignants.add (enseign);
            }
            
            // Si on atrouvé une séance avec l'ID : on crée la séance
            // sinon on renvoie null
            if (cpt !=0)
            {
                // Créer Seance
                ut = new Seance(id, semaine, date, heured, heuref, etat,cours,type,liste_groupes,liste_salles,liste_enseignants);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Seance rempli ou null
        return ut;
    }
    
  
    // Cette méthode retourne une liste de toutes les séances pour une semaine donnée
    public ArrayList<Seance> find_from_semaine (int semaine)
    {
        ArrayList<Seance> liste_seances = new ArrayList<>(500);
        Seance ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Seance en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM seance WHERE semaine = '"+semaine+"'");  

            // compteur de résultat
            int cpt = 0;
            
            // Les variables
            int id = 0;
            LocalDate date = null;
            LocalTime heured = null;
            LocalTime heuref = null;
            int etat = 0;
            int id_cours = 0;
            int id_type = 0;
            Cours cours =null;
            TypeCours type = null ;
            
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                id = rs.getInt(1);
                
                java.sql.Date dateSQL = rs.getDate(3);
                date = dateSQL.toLocalDate();
                        
                java.sql.Time heuredSQL = rs.getTime(4);
                heured = heuredSQL.toLocalTime();
                
                java.sql.Time heurefSQL = rs.getTime(5);
                heuref = heurefSQL.toLocalTime();
                
                etat = rs.getInt(6);
                id_cours=rs.getInt(7);
                id_type=rs.getInt(8);
                
                cours= Cours.charger_cours_BDD(id_cours);
                type= TypeCours.charger_typecours_BDD(id_type);
                
                // on a récupérer la séance reste à remplir les listes de groupes, salles, enseignants en visitant les tables 
                // correspondantes
                // déclaration des listes que l'on passera au constructeur
                ArrayList <Groupe> liste_groupes = new ArrayList <> (500); // liste des groupes issue de séances_groupes
                ArrayList <Salle> liste_salles = new ArrayList <> (500); // liste des salles issue de séances_salles
                ArrayList <Enseignant> liste_enseignants = new ArrayList <> (500); // liste des groupes issue de séances_enseignant

                // récupérer la liste des groupes
                 ResultSet rsG=stmt.executeQuery("SELECT * FROM seance_groupes WHERE IdSeance = '"+id+"'");  

                while (rsG.next())
                {
                    int id_groupes = rs.getInt(2);
                    Groupe groupe = Groupe.charger_groupe_BDD(id_groupes);
                    liste_groupes.add (groupe);
                }

                // récupérer la liste des salles
                 ResultSet rsS=stmt.executeQuery("SELECT * FROM seance_salle WHERE IdSeance = '"+id+"'");  

                while (rsS.next())
                {
                    int id_salles = rs.getInt(2);
                    Salle salle = Salle.charger_salle_BDD(id_salles);
                    liste_salles.add (salle);
                }

                // récupérer la liste des enseignants
                 ResultSet rsE=stmt.executeQuery("SELECT * FROM seance_enseignant WHERE IdSeance = '"+id+"'");  

                while (rsE.next())
                {
                    int id_enseignants = rs.getInt(2);
                    Enseignant enseign = Enseignant.charger_enseignant_BDD(id_enseignants);
                    liste_enseignants.add (enseign);
                }

                // Si on atrouvé une séance avec l'ID : on crée la séance
                // sinon on renvoie null
                if (cpt !=0)
                {
                    // Créer Seance
                    ut = new Seance(id, semaine, date, heured, heuref, etat,cours,type,liste_groupes,liste_salles,liste_enseignants);
                    liste_seances.add(ut);
                }
                
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner la liste des séances
        return liste_seances;
    }
    
}

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


public class SeanceDAO extends DAO<Seance>
{
    
    public SeanceDAO (Connection conn)
    {
        super(conn);
    }
    
    public SeanceDAO ()
    {
        super();
    }

    @Override
    public int create(Seance seance) 
    {
        // succès ?
        int chevauche = 0;
        int success = 0;

        // récupérer les données de la séance qu'il faudra insérer
        int semaine = seance.getSemaine();
        LocalDate dateS = seance.getDate();
        LocalTime heure_debutS = seance.getHeured();
        LocalTime heure_finS = seance.getHeuref();
        int etat = seance.getEtat();
        Cours cours = seance.getCours();
        TypeCours type = seance.getType();
        ArrayList <Groupe> liste_groupes = seance.getListeGroupes();
        ArrayList <Salle> liste_salles = seance.getListeSalles();
        ArrayList <Enseignant> liste_enseignants = seance.getListeEnseignants();
        
        
        try 
        {
            
            // Vérifier que les groupes soient libres à l'horaire demandé
            // récupérer les id des séances pour chaque groupe
            for (int i = 0; i < liste_groupes.size(); i++) 
            {
                int idGroupe = liste_groupes.get(i).getId();
                        
                Statement stmtSe51=connect.createStatement(); 
                ResultSet rsSe51=stmtSe51.executeQuery("SELECT * FROM seance_groupes WHERE IdGroupe = '"+idGroupe+"'"); 

                while (rsSe51.next())  // si rien n'est trouvé on ne rentre pas dans le while
                { 
                    // récupérer les données de chaque séance trouvée pour le groupe donné dont la date est la même
                    int idSeance = rsSe51.getInt(1);

                    Statement stmtSe52=connect.createStatement(); 
                    ResultSet rsSe52=stmtSe52.executeQuery("SELECT * FROM seance WHERE ID = '"+idSeance+"' AND Date = '"+dateS+"'"); 


                    while (rsSe52.next()) // Si une séance à la même date est trouvée
                    {
                        // récupérer les heures de chaque séances et les comparer avec celles de notre séance
                        java.sql.Time heureDebutSQL = rsSe52.getTime(4);
                        java.sql.Time heureFinSQL = rsSe52.getTime(5);
                        LocalTime heureDebut = heureDebutSQL.toLocalTime();
                        LocalTime heureFin = heureFinSQL.toLocalTime();
                        
                        // les comparer : si chevauchement incrémenter chevauche et quitter tout de suite le programme
                        if (heure_debutS.isAfter(heureDebut) && heure_debutS.isBefore(heureFin) )
                        {
                            chevauche++;
                            return 99;
                        }
                        else if (heure_finS.isAfter(heureDebut) && heure_finS.isBefore(heureFin) )
                        {
                            chevauche++;
                            return 99;
                        }
                        else if (heure_debutS.equals(heureDebut) && heure_finS.equals(heureFin) )
                        {
                            chevauche++;
                            return 99;
                        }
                        else if (heure_debutS.isBefore(heureDebut) && heure_finS.isAfter(heureFin) )
                        {
                            chevauche++;
                            return 99;
                        }
                        
                    }
                    
                }
            }
            
            
            // Vérifier que les profs soient libres à l'horaire demandé
            // récupérer les id des séances pour chaque profs
            for (int i = 0; i < liste_enseignants.size(); i++) 
            {
                int idEns = liste_enseignants.get(i).getId();
                        
                Statement stmtSe51=connect.createStatement(); 
                ResultSet rsSe51=stmtSe51.executeQuery("SELECT * FROM seance_enseignant WHERE IdEnseignant = '"+idEns+"'"); 

                while (rsSe51.next())  // si rien n'est trouvé on ne rentre pas dans le while
                { 
                    // récupérer les données de chaque séance trouvée pour le groupe donné dont la date est la même
                    int idSeance = rsSe51.getInt(1);

                    Statement stmtSe52=connect.createStatement(); 
                    ResultSet rsSe52=stmtSe52.executeQuery("SELECT * FROM seance WHERE ID = '"+idSeance+"' AND Date = '"+dateS+"'"); 


                    while (rsSe52.next()) // Si une séance à la même date est trouvée
                    {
                        // récupérer les heures de chaque séances et les comparer avec celles de notre séance
                        java.sql.Time heureDebutSQL = rsSe52.getTime(4);
                        java.sql.Time heureFinSQL = rsSe52.getTime(5);
                        LocalTime heureDebut = heureDebutSQL.toLocalTime();
                        LocalTime heureFin = heureFinSQL.toLocalTime();
                        
                        // les comparer : si chevauchement incrémenter chevauche et quitter tout de suite le programme
                        if (heure_debutS.isAfter(heureDebut) && heure_debutS.isBefore(heureFin) )
                        {
                            chevauche++;
                            return 100;
                        }
                        else if (heure_finS.isAfter(heureDebut) && heure_finS.isBefore(heureFin) )
                        {
                            chevauche++;
                            return 100;
                        }
                        else if (heure_debutS.equals(heureDebut) && heure_finS.equals(heureFin) )
                        {
                            chevauche++;
                            return 100;
                        }
                        else if (heure_debutS.isBefore(heureDebut) && heure_finS.isAfter(heureFin) )
                        {
                            chevauche++;
                            return 99;
                        }
                        
                    }
                    
                }
            }
            
            // Vérifier que les salles soient libres à l'horaire demandé
            // récupérer les id des séances pour chaque salle
            for (int i = 0; i < liste_salles.size(); i++) 
            {
                int idsalle = liste_salles.get(i).getId();
                        
                Statement stmtSe51=connect.createStatement(); 
                ResultSet rsSe51=stmtSe51.executeQuery("SELECT * FROM seance_salle WHERE IdSalle = '"+idsalle+"'"); 

                while (rsSe51.next())  // si rien n'est trouvé on ne rentre pas dans le while
                { 
                    // récupérer les données de chaque séance trouvée pour le groupe donné dont la date est la même
                    int idSeance = rsSe51.getInt(1);

                    Statement stmtSe52=connect.createStatement(); 
                    ResultSet rsSe52=stmtSe52.executeQuery("SELECT * FROM seance WHERE ID = '"+idSeance+"' AND Date = '"+dateS+"'"); 


                    while (rsSe52.next()) // Si une séance à la même date est trouvée
                    {
                        // récupérer les heures de chaque séances et les comparer avec celles de notre séance
                        java.sql.Time heureDebutSQL = rsSe52.getTime(4);
                        java.sql.Time heureFinSQL = rsSe52.getTime(5);
                        LocalTime heureDebut = heureDebutSQL.toLocalTime();
                        LocalTime heureFin = heureFinSQL.toLocalTime();
                        
                        // les comparer : si chevauchement incrémenter chevauche et quitter tout de suite le programme
                        if (heure_debutS.isAfter(heureDebut) && heure_debutS.isBefore(heureFin) )
                        {
                            chevauche++;
                            return 101;
                        }
                        else if (heure_finS.isAfter(heureDebut) && heure_finS.isBefore(heureFin) )
                        {
                            chevauche++;
                            return 101;
                        }
                        else if (heure_debutS.equals(heureDebut) && heure_finS.equals(heureFin) )
                        {
                            chevauche++;
                            return 101;
                        }
                        else if (heure_debutS.isBefore(heureDebut) && heure_finS.isAfter(heureFin) )
                        {
                            chevauche++;
                            return 101;
                        }
                        
                    }
                    
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        
        // Si aucun chevauchement détecté : on peut ajouter la séance dans les différentes tables
        if (chevauche == 0)
        {
            try 
            {
                Statement stmtSe53=connect.createStatement(); 

                // Insérer la séance dans seance
                java.sql.Date dateSQLNewSeance = java.sql.Date.valueOf(dateS);
                java.sql.Time heureDebutSQLSeance = java.sql.Time.valueOf(heure_debutS);
                java.sql.Time heureFinSQLSeance = java.sql.Time.valueOf(heure_finS);
                success =stmtSe53.executeUpdate("INSERT INTO seance (Semaine,Date,heure_debut,Heure_fin,Etat,IdCours,IdType) VALUES ('"+semaine+"','"+dateSQLNewSeance+"','"+heureDebutSQLSeance+"','"+heureFinSQLSeance+"','"+etat+"','"+cours.getId()+"','"+type.getId()+"')");  

                if (success != 0) // si opération réussie
                {
                    // récupérer l'ID de la séance qu'on vient d'insérer : comme ID est auto-incrémenté c'est l'ID max
                    int idSeance = 0;
                    
                    Statement stmtSe60=connect.createStatement(); 
                    ResultSet rsSe60=stmtSe60.executeQuery("SELECT MAX(ID) AS maxId FROM seance ");
                    
                    while (rsSe60.next()) // 1 seul !
                    {
                        idSeance = rsSe60.getInt("maxId");
                        System.out.println("IDNewSeance: "+idSeance);
                    }
                    

                    // insérer dans seance_enseignant
                    for (int i = 0; i < liste_enseignants.size(); i++) 
                    {
                        Statement stmtSe65=connect.createStatement(); 
                        success =stmtSe65.executeUpdate("INSERT INTO seance_enseignant (IdSeance,IdEnseignant) VALUES ('"+idSeance+"','"+liste_enseignants.get(i).getId()+"')");  
                    } 
                    
                    if (success !=0)
                    {
                        // Insérer dans seance_groupe
                        for (int i = 0; i < liste_groupes.size(); i++) 
                        {
                            Statement stmtSe66=connect.createStatement(); 
                            success =stmtSe66.executeUpdate("INSERT INTO seance_groupes (IdSeance,IdGroupe) VALUES ('"+idSeance+"','"+liste_groupes.get(i).getId()+"')");
                        }
                        
                        if (success != 0)
                        {
                            // Insérer dans seance_salle
                            for (int i = 0; i < liste_salles.size(); i++) 
                            {
                                Statement stmtSe67=connect.createStatement(); 
                                success =stmtSe67.executeUpdate("INSERT INTO seance_salle (IdSeance,IdSalle) VALUES ('"+idSeance+"','"+liste_salles.get(i).getId()+"')");
                            }
                        }
                        
                    }
                }
               
            } 
            catch (SQLException e) 
            {
                System.out.println(e.getMessage());
            } 
        }
        
        
        return success;

        
    }

    @Override
    public int update(Seance seance) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Seance seance) 
    {
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
            Statement stmtSe1=connect.createStatement(); 

            // récupérer la séance en fonction de son id
            ResultSet rsSe1=stmtSe1.executeQuery("SELECT * FROM seance WHERE id = '"+id+"'");  

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
            
            while (rsSe1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                semaine = rsSe1.getInt(2);
                
                java.sql.Date dateSQL = rsSe1.getDate(3);
                date = dateSQL.toLocalDate();
                        
                java.sql.Time heuredSQL = rsSe1.getTime(4);
                heured = heuredSQL.toLocalTime();
                
                java.sql.Time heurefSQL = rsSe1.getTime(5);
                heuref = heurefSQL.toLocalTime();
                
                etat = rsSe1.getInt(6);
                id_cours=rsSe1.getInt(7);
                id_type=rsSe1.getInt(8);
                
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
            rsSe1=stmtSe1.executeQuery("SELECT * FROM seance_groupes WHERE IdSeance = '"+id+"'");  
            
            while (rsSe1.next())
            {
                int id_groupes = rsSe1.getInt(2);
                Groupe groupe = Groupe.charger_groupe_BDD(id_groupes);
                liste_groupes.add (groupe);
            }
            
            // récupérer la liste des salles
            rsSe1=stmtSe1.executeQuery("SELECT * FROM seance_salle WHERE IdSeance = '"+id+"'");  
            
            while (rsSe1.next())
            {
                int id_salles = rsSe1.getInt(2);
                Salle salle = Salle.charger_salle_BDD(id_salles);
                liste_salles.add (salle);
            }
            
            // récupérer la liste des enseignants
            rsSe1=stmtSe1.executeQuery("SELECT * FROM seance_enseignant WHERE IdSeance = '"+id+"'");  
            
            while (rsSe1.next())
            {
                int id_enseignants = rsSe1.getInt(2);
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
            Statement stmtSe1=connect.createStatement(); 

            // récupérer Seance en fonction de son nom
            ResultSet rsSe1=stmtSe1.executeQuery("SELECT * FROM seance WHERE semaine = '"+semaine+"'"); 
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
            
            while (rsSe1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                cpt++;
                id = rsSe1.getInt(1);
                
                java.sql.Date dateSQL = rsSe1.getDate(3);
                date = dateSQL.toLocalDate();
                        
                java.sql.Time heuredSQL = rsSe1.getTime(4);
                heured = heuredSQL.toLocalTime();
                
                java.sql.Time heurefSQL = rsSe1.getTime(5);
                heuref = heurefSQL.toLocalTime();
                
                etat = rsSe1.getInt(6);
                id_cours=rsSe1.getInt(7);
                id_type=rsSe1.getInt(8);
                
                cours= Cours.charger_cours_BDD(id_cours);
                type= TypeCours.charger_typecours_BDD(id_type);
                
                // on a récupérer la séance reste à remplir les listes de groupes, salles, enseignants en visitant les tables 
                // correspondantes
                // déclaration des listes que l'on passera au constructeur
                ArrayList <Groupe> liste_groupes = new ArrayList <> (500); // liste des groupes issue de séances_groupes
                ArrayList <Salle> liste_salles = new ArrayList <> (500); // liste des salles issue de séances_salles
                ArrayList <Enseignant> liste_enseignants = new ArrayList <> (500); // liste des groupes issue de séances_enseignant

                // récupérer la liste des groupes
                Statement stmtSe2=connect.createStatement(); 
                ResultSet rsSe2=stmtSe2.executeQuery("SELECT * FROM seance_groupes WHERE IdSeance = '"+id+"'");  

                while (rsSe2.next())
                {
                    int id_groupes = rsSe2.getInt(2);
                    Groupe groupe = Groupe.charger_groupe_BDD(id_groupes);
                    liste_groupes.add (groupe);
                }

                // récupérer la liste des salles
                Statement stmtSe3=connect.createStatement(); 
                 ResultSet rsSe3=stmtSe3.executeQuery("SELECT * FROM seance_salle WHERE IdSeance = '"+id+"'");  

                while (rsSe3.next())
                {
                    int id_salles = rsSe3.getInt(2);
                    Salle salle = Salle.charger_salle_BDD(id_salles);
                    liste_salles.add (salle);
                }

                // récupérer la liste des enseignants
                Statement stmtSe4=connect.createStatement(); 
                 ResultSet rsSe4=stmtSe4.executeQuery("SELECT * FROM seance_enseignant WHERE IdSeance = '"+id+"'");  

                while (rsSe4.next())
                {
                    int id_enseignants = rsSe4.getInt(2);
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
    
    

    /**
     * Cette méthode retourne une liste de toutes les séances pour une semaine donnée et un groupe donné
     * @param semaine
     * @param groupe
     * @return
     */
    public ArrayList<Seance> find_from_semaine_and_group (int semaine, int id_groupe)
    {
        ArrayList<Seance> liste_seances = new ArrayList<>(500);
        Seance ut = null;
        try 
        {
            // On cherche d'abord dans séances_groupe en filtrant sur le groupe
            Statement stmtSe9=connect.createStatement(); 
            ResultSet rsSe9=stmtSe9.executeQuery("SELECT * FROM seance_groupes WHERE IdGroupe = '"+id_groupe+"'");  

            int cpt = 0; // compteur de résultats
            
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
            
            while (rsSe9.next())
            {
                cpt ++;
                id = rsSe9.getInt(1); // on récupère l'ID des séances pour le groupe
                
                // les listes
                ArrayList <Groupe> liste_groupes = new ArrayList <> (500); // liste des groupes issue de séances_groupes
                ArrayList <Salle> liste_salles = new ArrayList <> (500); // liste des salles issue de séances_salles
                ArrayList <Enseignant> liste_enseignants = new ArrayList <> (500); // liste des groupes issue de séances_enseignant

                // récupérer LA séance en fonction de la semaine (et du groupe car les ID sont déjà filtrés)
                Statement stmtSe1=connect.createStatement(); 
                ResultSet rsSe1=stmtSe1.executeQuery("SELECT * FROM seance WHERE semaine = '"+semaine+"' AND ID='"+id+"' "); 

                while (rsSe1.next())  // si rien n'est trouvé on ne rentre pas dans le while
                {                
                    java.sql.Date dateSQL = rsSe1.getDate(3);
                    date = dateSQL.toLocalDate();

                    java.sql.Time heuredSQL = rsSe1.getTime(4);
                    heured = heuredSQL.toLocalTime();

                    java.sql.Time heurefSQL = rsSe1.getTime(5);
                    heuref = heurefSQL.toLocalTime();

                    etat = rsSe1.getInt(6);
                    id_cours=rsSe1.getInt(7);
                    id_type=rsSe1.getInt(8);

                    cours= Cours.charger_cours_BDD(id_cours);
                    type= TypeCours.charger_typecours_BDD(id_type);

                    
                    // récupérer la liste des groupes
                    Statement stmtSe2=connect.createStatement(); 
                    ResultSet rsSe2=stmtSe2.executeQuery("SELECT * FROM seance_groupes WHERE IdSeance = '"+id+"'");  

                    while (rsSe2.next())
                    {
                        int id_groupes = rsSe2.getInt(2);
                        Groupe groupe = Groupe.charger_groupe_BDD(id_groupes);
                        liste_groupes.add (groupe);
                    }

                    // récupérer la liste des salles
                    Statement stmtSe3=connect.createStatement(); 
                     ResultSet rsSe3=stmtSe3.executeQuery("SELECT * FROM seance_salle WHERE IdSeance = '"+id+"'");  

                    while (rsSe3.next())
                    {
                        int id_salles = rsSe3.getInt(2);
                        Salle salle = Salle.charger_salle_BDD(id_salles);
                        liste_salles.add (salle);
                    }

                    // récupérer la liste des enseignants
                    Statement stmtSe4=connect.createStatement(); 
                     ResultSet rsSe4=stmtSe4.executeQuery("SELECT * FROM seance_enseignant WHERE IdSeance = '"+id+"'");  

                    while (rsSe4.next())
                    {
                        int id_enseignants = rsSe4.getInt(2);
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
            
            
            
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner la liste des séances
        return liste_seances;
    }
    
    /**
     * Cette méthode retourne une liste de toutes les séances d'une semaine donnée pour un enseignant donné
     * @param semaine
     * @param id_ens
     * @return
     */
    public ArrayList<Seance> find_from_semaine_and_enseignant (int semaine, int id_ens)
    {
        ArrayList<Seance> liste_seances = new ArrayList<>(500);
        Seance ut = null;
        try 
        {
            // On cherche d'abord dans séances_groupe en filtrant sur le groupe
            Statement stmtSe9=connect.createStatement(); 
            ResultSet rsSe9=stmtSe9.executeQuery("SELECT * FROM seance_enseignant WHERE IdEnseignant = '"+id_ens+"'");  

            int cpt = 0; // compteur de résultats
            
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
            
            while (rsSe9.next())
            {
                cpt ++;
                id = rsSe9.getInt(1); // on récupère l'ID des séances pour le groupe
                
                // les listes
                ArrayList <Groupe> liste_groupes = new ArrayList <> (500); // liste des groupes issue de séances_groupes
                ArrayList <Salle> liste_salles = new ArrayList <> (500); // liste des salles issue de séances_salles
                ArrayList <Enseignant> liste_enseignants = new ArrayList <> (500); // liste des groupes issue de séances_enseignant

                // récupérer LA séance en fonction de la semaine (et du groupe car les ID sont déjà filtrés)
                Statement stmtSe1=connect.createStatement(); 
                ResultSet rsSe1=stmtSe1.executeQuery("SELECT * FROM seance WHERE semaine = '"+semaine+"' AND ID='"+id+"' "); 

                while (rsSe1.next())  // si rien n'est trouvé on ne rentre pas dans le while
                {                
                    java.sql.Date dateSQL = rsSe1.getDate(3);
                    date = dateSQL.toLocalDate();

                    java.sql.Time heuredSQL = rsSe1.getTime(4);
                    heured = heuredSQL.toLocalTime();

                    java.sql.Time heurefSQL = rsSe1.getTime(5);
                    heuref = heurefSQL.toLocalTime();

                    etat = rsSe1.getInt(6);
                    id_cours=rsSe1.getInt(7);
                    id_type=rsSe1.getInt(8);

                    cours= Cours.charger_cours_BDD(id_cours);
                    type= TypeCours.charger_typecours_BDD(id_type);

                    
                    // récupérer la liste des groupes
                    Statement stmtSe2=connect.createStatement(); 
                    ResultSet rsSe2=stmtSe2.executeQuery("SELECT * FROM seance_groupes WHERE IdSeance = '"+id+"'");  

                    while (rsSe2.next())
                    {
                        int id_groupes = rsSe2.getInt(2);
                        Groupe groupe = Groupe.charger_groupe_BDD(id_groupes);
                        liste_groupes.add (groupe);
                    }

                    // récupérer la liste des salles
                    Statement stmtSe3=connect.createStatement(); 
                     ResultSet rsSe3=stmtSe3.executeQuery("SELECT * FROM seance_salle WHERE IdSeance = '"+id+"'");  

                    while (rsSe3.next())
                    {
                        int id_salles = rsSe3.getInt(2);
                        Salle salle = Salle.charger_salle_BDD(id_salles);
                        liste_salles.add (salle);
                    }

                    // récupérer la liste des enseignants
                    Statement stmtSe4=connect.createStatement(); 
                     ResultSet rsSe4=stmtSe4.executeQuery("SELECT * FROM seance_enseignant WHERE IdSeance = '"+id+"'");  

                    while (rsSe4.next())
                    {
                        int id_enseignants = rsSe4.getInt(2);
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
            
            
            
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner la liste des séances
        return liste_seances;
    }
    
}

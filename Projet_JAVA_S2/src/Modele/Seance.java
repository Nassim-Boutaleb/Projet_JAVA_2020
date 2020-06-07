/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.SeanceDAO;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class Seance implements Comparable<Seance>
{
    private int id;
    private int semaine;
    private LocalDate date;
    private LocalTime heure_debut;
    private LocalTime heure_fin;
    private int etat;
    private Cours cours;
    private TypeCours type;
    private ArrayList <Groupe> liste_groupes = new ArrayList <> (500); // liste des groupes issue de séances_groupes
    private ArrayList <Salle> liste_salles = new ArrayList <> (500); // liste des salles issue de séances_salles
    private ArrayList <Enseignant> liste_enseignants = new ArrayList <> (500); // liste des groupes issue de séances_enseignant
    
    public Seance ()
    {
        
    }
    
    public Seance (int id , int semaine , LocalDate date , LocalTime heured, LocalTime heuref , int etat, Cours cours, TypeCours type, ArrayList<Groupe> groupes, ArrayList<Salle> salles, ArrayList<Enseignant> enseignants)
    {
        this.id = id;
        this.semaine = semaine;
        this.date = date;
        this.heure_debut = heured;
        this.heure_fin = heuref;
        this.etat = etat;
        this.cours= cours;
        this.type=type;
        liste_enseignants = enseignants;
        liste_groupes = groupes;
        liste_salles = salles;
    }
    
    public void setSeance (int id , int semaine , LocalDate date , LocalTime heured, LocalTime heuref , int etat,ArrayList<Groupe> groupes, ArrayList<Salle> salles, ArrayList<Enseignant> enseignants)
    {
        this.id = id;
        this.semaine = semaine;
        this.date = date;
        this.heure_debut = heured;
        this.heure_fin = heuref;
        this.etat = etat;
        liste_enseignants = enseignants;
        liste_groupes = groupes;
        liste_salles = salles;
    }
    
    /**
     *
     * @return
     */
    public int getId () { return id; }
    public int getSemaine () { return semaine ; }
    public LocalDate getDate () { return date ; }
    public LocalTime getHeured () { return heure_debut ; }
    public LocalTime getHeuref () { return heure_fin ; }
    public int getEtat () { return etat; }
    public Cours getCours () { return cours; }
    public TypeCours getType () { return type; }
    public ArrayList<Groupe> getListeGroupes () { return liste_groupes; }
    public ArrayList<Salle> getListeSalles () { return liste_salles; }
    public ArrayList<Enseignant> getListeEnseignants () { return liste_enseignants; }
    
    
    
    // Cette méthode rend une séance à partir de son ID
    public static Seance charger_seance_BDD_id (int id)
    {
        
        // récupérer Seance depuis la BDD
        SeanceDAO udao = new SeanceDAO();
        Seance ut = udao.find(id);
        
        return ut;
    }
    
    // Cette méthode rend une liste de toutes les séances pour une semaine donnée
    public static ArrayList<Seance> charger_seance_BDD_semaine (int semaine)
    {
        
        // récupérer Seance depuis la BDD
        SeanceDAO udao = new SeanceDAO();
        ArrayList<Seance> liste_seances = udao.find_from_semaine(semaine);
        
        return liste_seances;
    }
    
    // Cette méthode rend une liste de toutes les séances pour une semaine donnée
    public static ArrayList<Seance> charger_seance_BDD_semaine_groupe (int semaine, int id_groupe)
    {
        
        // récupérer Seance depuis la BDD
        SeanceDAO udao = new SeanceDAO();
        ArrayList<Seance> liste_seances = udao.find_from_semaine_and_group(semaine,id_groupe);
        
        return liste_seances;
    }
    
    
    @Override
    public int compareTo (Seance s2)
    {
        LocalDate dateS2 = s2.getDate();
        
        if (date.isAfter(dateS2))  // .compareTo(dateS2) >0 
        {
            return 1;
        }
        else if (date.isEqual(dateS2) )
        {
            if (heure_debut.isAfter(s2.heure_debut))
            {
                return 1;
            }
            else 
            {
                return -1;
            }
        }
        else 
        {
            return -1;
        }
    } 
    
    
    public static ArrayList<Seance> charger_seance_BDD_semaine_prof (int semaine, int id_prof)
    {
        
        // récupérer Seance depuis la BDD
        SeanceDAO udao = new SeanceDAO();
        ArrayList<Seance> liste_seances = udao.find_from_semaine_and_enseignant(semaine,id_prof);
        
        return liste_seances;
    }

    
    /**
     * Cette méthode ajoute la séance dans la BDD
     * @return success = succès ou non de la requete INSERT INTO
     */
    public int ajouter_verifier_seance()
    {
        SeanceDAO sdao = new SeanceDAO();
        int success = sdao.create(this);
        return success;
    }
    
    /**
     * Cette méthode modifie la séance dans la BDD
     * @return success = succès ou non de la requete 
     */
    public int modifier_seance()
    {
        SeanceDAO sdao = new SeanceDAO();
        int success = sdao.update(this);
        return success;
    }
    
    public int supprimer_seance ()
    {
        SeanceDAO sdao = new SeanceDAO();
        int success = sdao.delete(this);
        return success;
    }
    
    /**
     * Cette méthode retourne un chiffre de 0 à 5 en fonction du jour
     * @return
     */
    public int getChiffreDate ()
    {
        Calendar calendar = new GregorianCalendar();
        System.out.println("Localdate: "+date+" id:  "+id);
        Date dateSD = java.sql.Date.valueOf(date);
        calendar.set(Calendar.WEEK_OF_YEAR,semaine);
        System.out.println("Semaine: "+semaine);
        calendar.set(Calendar.YEAR,2020);
        calendar.setTime(dateSD);
        System.out.println("DateSD: "+dateSD);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("LeJour: "+day);
        
        if (day == 2) // Lundi
        {
            return 0;
        }
        else if (day == 3) // mardi
        {
            return 1;
        }
        else if (day == 4) // 
        {
            return 2;
        }
        else if (day == 5) // 
        {
            return 3;
        }
        else if (day == 6) // 
        {
            return 4;
        }
        else if (day == 7) // 
        {
            return 5;
        }
        
        return 0;
        
        
    }
    
    /**
     * Cette méthode retourne un chiffre de 0 à ?? en fonction de l'heure
     * @return
     */
    public int getChiffreHeure ()
    {
        
        System.out.println("Heure connue: "+heure_debut);
        String heure_debutS = heure_debut.toString();
        
        if (heure_debutS.equals("08:30"))
        {
            return 1;
        }
        else if (heure_debutS.equals("10:15"))
        {
            return 2;
        }
        else if (heure_debutS.equals("12:00"))
        {
            return 3;
        }
        else if (heure_debutS.equals("13:45"))
        {
            return 4;
        }
        else if (heure_debutS.equals("15:30"))
        {
            return 5;
        }
        else if (heure_debutS.equals("17:15"))
        {
            return 6;
        }
        else if (heure_debutS.equals("19:00"))
        {
            return 7;
        }
        
        return 1;
        
    }
    
    
}

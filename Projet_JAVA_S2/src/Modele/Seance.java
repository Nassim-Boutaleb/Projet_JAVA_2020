/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.SeanceDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;


/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class Seance 
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
    public Cours getIdCours () { return cours; }
    public TypeCours getIdType () { return type; }
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
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.SalleDAO;
import java.util.ArrayList;

/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class Salle {
    
    private int id;
    private String nom;
    private int capacite;
    private int id_site;
    
    public Salle ()
    {
        
    }
    
    public Salle (int id ,String nom, int capacite, int site)
    {
        this.id = id; 
        this.nom = nom;
        this.capacite = capacite;
        this.id_site=site;
      
    }
    
    public void setSalle (int id, String nom, int capacite)
    {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
    }
    
    public int getId () { return id; }
    public String getNom () { return nom ; }
    public int getCapacite () { return capacite ; }
    public int getIdSite () { return id_site ; }
    
    /**
     * @param nom    
     * @return 
     */
    public static Salle charger_salle_BDD (String nom)
    {
        
        // récupérer Salle depuis la BDD
        SalleDAO udao = new SalleDAO();
        Salle ut = udao.find(nom);
        
        return ut;
    }
    
    public static Salle charger_salle_BDD (int id)
    {
        
        // récupérer Salle depuis la BDD
        SalleDAO udao = new SalleDAO();
        Salle ut = udao.find(id);
        
        return ut;
    }
    
    public static ArrayList<Salle> getListeSalles ()
    {
        ArrayList<Salle> liste_salles = new ArrayList<>(100);
        SalleDAO sdao = new SalleDAO();
        liste_salles = sdao.getAllSalles();
        return liste_salles;
    }
    
}

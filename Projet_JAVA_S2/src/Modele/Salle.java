/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.SalleDAO;

/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class Salle {
    
    private int id;
    private String nom;
    private int capacite;
    private Site site;
    
    public Salle ()
    {
        
    }
    
    public Salle (int id ,String nom, int capacite, Site site)
    {
        this.id = id; 
        this.nom = nom;
        this.capacite = capacite;
        this.site=site;
      
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
    public Site getIdSalla () { return site ; }
    
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.SiteDAO;
import java.util.ArrayList;

/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class Site 
{
    
    private int id;
    private String nom;
    private ArrayList <Salle> liste_salles = new ArrayList<>(100);
    
    public Site ()
    {
        
    }
    
    public Site (int id ,String nom , ArrayList <Salle> salles)
    {
        this.id = id; 
        this.nom = nom;
        liste_salles = salles;
      
    }
    
    public void setSite (int id, String nom , ArrayList <Salle> salles)
    {
        this.id = id;
        this.nom = nom;
        liste_salles = salles;
    }
    
    public int getId () { return id; }
    public String getNom () { return nom ; }
    public ArrayList <Salle> getListeSalles () {return liste_salles; }
    
    /**
     * @param nom    
     * @return     
     */
    public static Site charger_site_BDD (String nom)
    {
        
        // récupérer Site depuis la BDD
        SiteDAO udao = new SiteDAO();
        Site ut = udao.find(nom);
        
        return ut;
    }
    
    public static Site charger_site_BDD (int id)
    {
        
        // récupérer Site depuis la BDD
        SiteDAO udao = new SiteDAO();
        Site ut = udao.find(id);
        
        return ut;
    }
    
    
    public static ArrayList <Site> getListeSites ()
    {
        ArrayList <Site> liste_sites = new ArrayList<>(100);
        SiteDAO sdao = new SiteDAO();
        liste_sites = sdao.getAllSites();
        return liste_sites;
    }
    
}

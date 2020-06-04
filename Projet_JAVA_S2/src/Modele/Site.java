/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.SiteDAO;

/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class Site 
{
    
    private int id;
    private String nom;
    
    public Site ()
    {
        
    }
    
    public Site (int id ,String nom)
    {
        this.id = id; 
        this.nom = nom;
      
    }
    
    public void setSite (int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
    
    public int getId () { return id; }
    public String getNom () { return nom ; }
    
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
    
    
}

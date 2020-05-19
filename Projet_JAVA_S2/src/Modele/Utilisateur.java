/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.UtilisateurDAO;
import Vue.LoginPage;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JOptionPane;

/**
 *
 * @author Nassim
 */
public class Utilisateur {
    
    private int id;
    private String email;
    private String passwd;
    private String nom;
    private String prenom;
    private int droit;
    
    public Utilisateur ()
    {
        
    }
    
    public Utilisateur (int id , String email , String passwd , String nom, String prenom , int droit)
    {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = droit;
    }
    
    public void setUtilisateur (int id , String email , String passwd , String nom, String prenom , int droit)
    {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = droit;
    }
    
    public int getId () { return id; }
    public String getEmail () { return email ; }
    public String getPasswd () { return passwd ; }
    public String getNom () { return nom ; }
    public String getPrenom () { return prenom ; }
    public int getDroit () { return droit; }
    
    
    public static Utilisateur charger_utilisateur_BDD (String email)
    {
        
        // récupérer l'utilisateur depuis la BDD
        UtilisateurDAO udao = new UtilisateurDAO();
        Utilisateur ut = udao.find(email);
        
        return ut;
    }
    
}

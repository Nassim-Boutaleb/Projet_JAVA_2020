/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.*;
import java.awt.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Nassim
 */
public class AdminGestionControleur 
{
    private AdminGestionMainPage adminPage ;  // stockage de la vue (page graphique)
    private selectionEdtDialog sd;  // la boite de dialogue de séléction d'edt
    private AjouterSeanceDialog ajd;  // la boite de dialogue d'ajout de séance
    private int width;
    private int height;
    
    public AdminGestionControleur ()
    {
        // définir taille des fenetres
        //Récupérer taille utile de l'écran
        GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
         
        //Stocker cette taille
        Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();

        // récupérer la largeur et la heuteur
        width = (int)maximumWindowBounds.getWidth();
        height = (int)maximumWindowBounds.getHeight();
    }
    
    // Cette méthode ouvre une fenetre AdminGestionMainpage et la rend visible
    // page principale de gestion de l'admin
    public void ouvrirAdminpage ()
    {
        adminPage = new AdminGestionMainPage(height,width,this);
        adminPage.setVisible (true);
    }
    
    // cette méthode récupéère la liste des promotions, étudiants , enseignants , salles
    // présents dans la base de données et ouvre une fenetre/boite de dialogue
    // de type ??? pour permettre à l'admin de choisir l'edt qu'il veut afficher
    public void selectionner_afficher_edt ()
    {
        //Récupérer la liste des promotions
        
        // récupérer la liste des étudiants 
        
        // récupérer la liste des enseignants 
        
        // Récupérer la liste des sites
        
        // récupérer la lsite des salles
        
        // Afficher la boite de dialogue permettant de choisir l'edt désiré
        sd = new selectionEdtDialog(this,adminPage,"Sélectionner emploi du temps à afficher",true);
    }
    
    // Une fois le choix de l'edt à afficher effectué au niveau de la vue selectionEdtDialog
    // récupérer les infos de l'edt à afficher et l'afficher en appelant la méthode de la vue
    // AdminGestionMainPage correspondante
    // type = type d'edt à afficher (promo,etudiant,..) et donnée = caractérisation (nom promo, nom salle ,...)
    public void afficher_edt (String type, String donnee)
    {
        
    }
    
    
    // Clic sur ajouter une sénace : on va ouvrir une boite de dialogue qui va permettre l'ajout d'une séance
    public void affichage_menu_ajout_seance ()
    {
        ajd = new AjouterSeanceDialog(this, adminPage,"Ajouter une séance", true);
    }
    
    // Clic sur ajouter une matière
    public void ajouter_matière ()
    {
        String matiere = JOptionPane.showInputDialog(adminPage,"Saisir le nom de la matière à ajouter");
    }
    
    // Clic sur modifier une matière
    public void modifier_matiere ()
    {
        
    }
}

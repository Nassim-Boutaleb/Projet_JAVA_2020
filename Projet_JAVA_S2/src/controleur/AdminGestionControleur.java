/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import DAO.PromotionDAO;
import Vue.*;
import java.awt.*;
import javax.swing.JOptionPane;
import Modele.*;
import java.util.ArrayList;


/**
 *
 * @author Nassim
 */
public class AdminGestionControleur 
{
    private AdminGestionMainPage adminPage ;  // stockage de la vue (page graphique)
    
    // Les boites de dialogue
    private SelectionEdtDialog sd;  // la boite de dialogue de séléction d'edt
    private AjouterSeanceDialog ajd;  // la boite de dialogue d'ajout de séance
    private Modifier_supprimer_cours modSuppCours ; // la boite de dialogue de gestion d'un cours
   
    // tailles
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
    // de type JDialog pour permettre à l'admin de choisir l'edt qu'il veut afficher
    public void selectionner_afficher_edt ()
    {
        // Afficher la boite de dialogue permettant de choisir l'edt désiré
        sd = new SelectionEdtDialog(this,adminPage,"Sélectionner emploi du temps à afficher",true);
    }
    

    
    // elle renvoie la liste des promotions stockées en BDD sous forme de tableau de String
    /**
     *
     * @return
     */
    public String[] get_liste_promos ()
    {
        ArrayList <Promotion> liste_promo = new ArrayList<>(100);
        liste_promo = Promotion.getAllPromotions();
        
        String [] noms_promos = new String [liste_promo.size()];  // stocker les noms de promo
        for (int i = 0; i < liste_promo.size(); i++) 
        {
            noms_promos[i] = liste_promo.get(i).getNom();
        }
        
        return noms_promos;
        
        
    }
    
    // elle renvoie la liste des noms d'etudiants stockées en BDD sous forme de tableau de String
    /**
     *
     * @return
     */
    public String[] get_liste_etudiants ()
    {
        ArrayList <Etudiant> liste_etudiants = new ArrayList<>(100);
        liste_etudiants = Etudiant.getListeEtudiants();
        
        String [] noms_etudiants = new String [liste_etudiants.size()];  // stocker les noms des etudiants
        for (int i = 0; i < liste_etudiants.size(); i++) 
        {
            noms_etudiants[i] = liste_etudiants.get(i).getNom()+" "+liste_etudiants.get(i).getPrenom();
        }
        
        return noms_etudiants; 
    }
    
    // Une fois le choix de l'edt à afficher effectué au niveau de la vue SelectionEdtDialog
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
    
    // Clic sur modifier une matière : on ouvre une JDialog
    public void modifier_supprimer_matiere ()
    {
        modSuppCours = new Modifier_supprimer_cours(this, adminPage, "Gestion des cours", true);
    }
}

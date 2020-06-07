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
import com.sun.net.httpserver.Authenticator;
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
    


    /**
     *    Cette méthode ouvre une fenetre AdminGestionMainpage et la rend visible
     *    page principale de gestion de l'admin
     */
    public void ouvrirAdminpage ()
    {
        adminPage = new AdminGestionMainPage(height,width,this);
        adminPage.setVisible (true);
    }
    

    /**
     *    cette méthode récupéère la liste des promotions, étudiants , enseignants , salles
     *   présents dans la base de données et ouvre une fenetre/boite de dialogue
     *   de type JDialog pour permettre à l'admin de choisir l'edt qu'il veut afficher
     */
    public void selectionner_afficher_edt ()
    {
        // Afficher la boite de dialogue permettant de choisir l'edt désiré
        sd = new SelectionEdtDialog(this,adminPage,"Sélectionner emploi du temps à afficher",true);
    }
    

    
    
    /**
     * elle renvoie la liste des promotions stockées en BDD sous forme de tableau de String
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
    
    
    /**
     * Cette méthode renvoie la liste des noms d'etudiants stockées en BDD sous forme de tableau de String
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
    
    
    /**
     * Cette méthode renvoie la liste des noms d'enseignants stockées en BDD sous forme de tableau de String
     * @return
     */
    public String[] get_liste_enseignants ()
    {
        ArrayList <Enseignant> liste_ens = new ArrayList<>(100);
        liste_ens = Enseignant.getListeEnseignants();
        
        String [] noms_ens = new String [liste_ens.size()];  // stocker les noms des etudiants
        for (int i = 0; i < liste_ens.size(); i++) 
        {
            noms_ens[i] = liste_ens.get(i).getNom()+" "+liste_ens.get(i).getPrenom();
        }
        
        return noms_ens; 
    }
    
    
    /**
     * Cette méthode renvoie la liste des noms de sites stockées en BDD sous forme de tableau de String
     * @return
     */
    public String[] get_liste_sites ()
    {
        ArrayList <Site> liste_sites = new ArrayList<>(100);
        liste_sites = Site.getListeSites();
        
        String [] noms_sites = new String [liste_sites.size()];  // stocker les noms des etudiants
        for (int i = 0; i < liste_sites.size(); i++) 
        {
            noms_sites[i] = liste_sites.get(i).getNom();
        }
        
        return noms_sites; 
    }
    
     
    /**
     * Cette méthode renvoie la liste des noms de salles stockées en BDD sous forme de tableau de String
     * @return
     */
    public String[] get_liste_salles ()
    {
        ArrayList <Salle> liste_salles = new ArrayList<>(100);
        liste_salles = Salle.getListeSalles();
        
        String [] noms_salles = new String [liste_salles.size()];  // stocker les noms des etudiants
        for (int i = 0; i < liste_salles.size(); i++) 
        {
            noms_salles[i] = liste_salles.get(i).getNom();
        }
        
        return noms_salles; 
    }
    
    /**
     * Cette méthode retourne une array list avec tous les  sites
     * @return
     */
    public ArrayList<Site> get_list_sites_Sites ()
    {
        ArrayList <Site> liste_sites = new ArrayList<>(100);
        liste_sites = Site.getListeSites();
        
        return liste_sites;
    }
    
    /**
     * Cette méthode retourne un tableau de String avec les noms de groupes
     * @return
     */
    public String [] get_liste_groupes ()
    {
        ArrayList <Groupe> liste_groupes = new ArrayList<>(100);
        liste_groupes = Groupe.get_liste_groupes();
        
        String [] noms_groupes = new String [liste_groupes.size()];  // stocker les noms des etudiants
        for (int i = 0; i < liste_groupes.size(); i++) 
        {
            noms_groupes[i] = liste_groupes.get(i).getNom();
        }
        
        return noms_groupes;
    }
    
    /**
     * Cette méthode retourne un tableau de String avec les noms de cours
     * @return
     */
    public String [] get_liste_cours ()
    {
        ArrayList <Cours> liste_cours = new ArrayList<>(100);
        liste_cours = Cours.get_liste_cours();
        
        String [] noms_cours = new String [liste_cours.size()];  // stocker les noms des etudiants
        for (int i = 0; i < liste_cours.size(); i++) 
        {
            noms_cours[i] = liste_cours.get(i).getNom();
        }
        
        return noms_cours;
    }
    
    /**
     * Cette méthode retourne un tableau de String avec les types_cours de groupes
     * @return
     */
    public String [] get_liste_types_cours ()
    {
        ArrayList <TypeCours> liste_typesCours = new ArrayList<>(100);
        liste_typesCours = TypeCours.get_liste_types_cours();
        
        String [] noms_typeCours = new String [liste_typesCours.size()];  // stocker les noms des etudiants
        for (int i = 0; i < liste_typesCours.size(); i++) 
        {
            noms_typeCours[i] = liste_typesCours.get(i).getNom();
        }
        
        return noms_typeCours;
    }
    
    // Une fois le choix de l'edt à afficher effectué au niveau de la vue SelectionEdtDialog
    // récupérer les infos de l'edt à afficher et l'afficher en appelant la méthode de la vue
    // AdminGestionMainPage correspondante
    // type = type d'edt à afficher (promo,etudiant,..) et donnée = caractérisation (nom promo, nom salle ,...)
    public void afficher_edt (String type, String donnee)
    {
        
    }
    
    
    

    /**
     * Clic sur ajouter une sénace : on va ouvrir une boite de dialogue qui va permettre l'ajout d'une séance
     */
    public void affichage_menu_ajout_seance ()
    {
        ajd = new AjouterSeanceDialog(this, adminPage,"Ajouter une séance", true);
    }
    
    

    /**
     * Clic sur ajouter une matière
     */
    public void ajouter_matière ()
    {
        String matiere = JOptionPane.showInputDialog(adminPage,"Saisir le nom de la matière à ajouter");
        if (matiere == null)
        {
            JOptionPane.showMessageDialog(adminPage, "Vide !");
        }
        else if (matiere.equals(""))
        {
            JOptionPane.showMessageDialog(adminPage, "Vide !");
        }
        else
        {
            Cours cours = new Cours(0,matiere); // id = 0 car pas utile, l'insertion dans la BDD ne tient pas compte de l'ID qui va être auto-incrémenté par mySQL
            int success = cours.inserer_cours_BDD();
            if (success == 0)
            {
                JOptionPane.showMessageDialog(adminPage, "erreur !");
            }
            else if (success == 99)
            {
                JOptionPane.showMessageDialog(adminPage, "Ce cours existe déjà  !");
            }
        }
        
    }
    
    

    /**
     * Clic sur modifier une matière : on ouvre une JDialog
     */
    public void modifier_supprimer_matiere ()
    {
        modSuppCours = new Modifier_supprimer_cours(this, adminPage, "Gestion des cours", true);
    }
    
    /**
     * Cette méthode permet de changer le nom d'un cours en BDD (via appel au modèle)
     * @param nom_cours
     */
    public void renommer_cours (String matiere , String nouveauNom)
    {
        if (nouveauNom == null)
        {
            JOptionPane.showMessageDialog(adminPage, "Vide !");
        }
        else if (nouveauNom.equals(""))
        {
            JOptionPane.showMessageDialog(adminPage, "Vide !");
        }
        else
        {
            Cours cours = new Cours(0,matiere); // id = 0 car pas utile, l'insertion dans la BDD ne tient pas compte de l'ID qui va être auto-incrémenté par mySQL
            int success = cours.renommer_cours(nouveauNom);
            if (success == 0)
            {
                JOptionPane.showMessageDialog(adminPage, "erreur !");
            }
            else if (success == 99)
            {
                JOptionPane.showMessageDialog(adminPage, "Ce cours existe déjà  !");
            }
        }
    }
    
    public ArrayList<Promotion> get_liste_promo ()
    {
        ArrayList <Promotion> liste_promo = new ArrayList<>(100);
        liste_promo = Promotion.getAllPromotions();
        
        return liste_promo;
    }
    
    /**
     * Cette méthode ajoute une séance en base de données
     * @param ls
     */
    public void ajouter_seance(ArrayList<Seance> ls)
    {
        // Ajouter les séances une par une
        int success = 0;
        for (int i = 0; i < ls.size(); i++) 
        {
            success = ls.get(i).ajouter_verifier_seance();
        }
        if (success == 0)
        {
            JOptionPane.showMessageDialog(adminPage,"Erreur SQL");
        }
        else if (success == 99)
        {
            JOptionPane.showMessageDialog(adminPage,"Un des groupes a deja un cours prevu a cet horaire");
        }
        else if (success == 100)
        {
            JOptionPane.showMessageDialog(adminPage,"Un des enseignants a deja un cours prevu a cet horaire");
        }
        else if (success == 101)
        {
            JOptionPane.showMessageDialog(adminPage,"Une des salles a deja un cours prevu a cet horaire");
        }
    }
    
    /**
     * Cette méthode modifie une séance en base de données
     * @param ls
     */
    public void modifier_seance(ArrayList<Seance> ls)
    {
        // Ajouter les séances une par une
        int success = 0;
        for (int i = 0; i < ls.size(); i++) // size = 1
        {
            success = ls.get(i).modifier_seance();
        }
        if (success == 0)
        {
            JOptionPane.showMessageDialog(adminPage,"Erreur SQL");
        }
        else if (success == 99)
        {
            JOptionPane.showMessageDialog(adminPage,"Un des groupes a deja un cours prevu a cet horaire");
        }
        else if (success == 100)
        {
            JOptionPane.showMessageDialog(adminPage,"Un des enseignants a deja un cours prevu a cet horaire");
        }
        else if (success == 101)
        {
            JOptionPane.showMessageDialog(adminPage,"Une des salles a deja un cours prevu a cet horaire");
        }
    }
    
    /**
     * Cette méthode ouvre une fenetre graphique pour l'affichage de l'EDT de l'enseignant choisi
     * @param nom
     * @param prenom
     */
    public void afficher_edt_enseignant (String nom, String prenom)
    {
        Enseignant prof = Enseignant.charger_enseignant_BDD_nom_prenom(nom, prenom);
        EdtControleurProf contProf = new EdtControleurProf(prof,1,this);
    }
}

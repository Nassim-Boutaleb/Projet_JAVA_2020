/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;


import Modele.*;
import static Modele.Etudiant.charger_etudiant_BDD;
import Vue.*;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pezi
 */
public class EdtControleur {
    private EdtPage EdtPage ;  // stockage de la vue (page graphique)
    private int width;
    private int height;
    private int droit;
    private AdminGestionControleur agc;


    public EdtControleur (Etudiant etudiant, int droit, AdminGestionControleur agc)
    {
        // définir taille des fenetres
        //Récupérer taille utile de l'écran
        GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();

        //Stocker cette taille
        Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();

        // récupérer la largeur et la heuteur
        width = (int)maximumWindowBounds.getWidth();
        height = (int)maximumWindowBounds.getHeight();

        // ouvrir la page de login
        System.out.println(width);
        System.out.println(height);
        
        this.droit = droit;
        this.agc=agc;
        
        ouvrirEdt(etudiant);

    }

    // Méthode pour ouvrir une page de login

    public void ouvrirEdt (Etudiant etudiant)
    {
        // ouvrir et stocker la page de login et la rendre visible
        
        //if(utilisateur.getDroit()==4){
        //Etudiant  etudiant =charger_etudiant_BDD( )://utilisateur.getId());
        EdtPage = new EdtPage(width, height,this,etudiant,droit);

        EdtPage.setVisible(true);//}
    }
    
    public ArrayList<Seance>infosemaine()
    {
        ArrayList<Seance> ut = Seance.charger_seance_BDD_semaine(23);
        System.out.println(ut);
        return ut;
        
    }
        public ArrayList<Seance>infosemainegroupe(Groupe groupe,int numsemaine)
    {
        
        ArrayList<Seance> ut = Seance.charger_seance_BDD_semaine_groupe(numsemaine,groupe.getId());
        System.out.println(ut);
        return ut;
        
    }
    
    public void ouvrir_dialog_modifier_seance (Seance s)
        {
            ModifierSeanceDialog msd = new ModifierSeanceDialog(agc,EdtPage, "Modifier la seance", true,s);
        }
        
        public void supprimer_seance (Seance s)
        {
            int success = s.supprimer_seance();
            
            if (success == 0)
            {
                JOptionPane.showMessageDialog(EdtPage,"Erreur SQL");
            }
        }



}
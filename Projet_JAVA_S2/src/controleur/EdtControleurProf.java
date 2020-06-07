/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Modele.Enseignant;
import Modele.Etudiant;
import Modele.Groupe;
import Modele.Seance;
import Vue.EdtPage;
import Vue.edtProf;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author pezi
 */
public class EdtControleurProf {
    private edtProf edtProf ;  // stockage de la vue (page graphique)
    private int width;
    private int height;
    
        public EdtControleurProf (Enseignant Prof)
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
        
        ouvrirEdtProf(Prof);

    }
        public void ouvrirEdtProf (Enseignant Prof)
    {
        // ouvrir et stocker la page de login et la rendre visible
        
        //if(utilisateur.getDroit()==4){
        //Etudiant  etudiant =charger_etudiant_BDD( )://utilisateur.getId());
        edtProf = new edtProf(width, height,this,Prof);

        edtProf.setVisible(true);//}
    }
    
    public ArrayList<Seance>infosemaine()
    {
        ArrayList<Seance> ut = Seance.charger_seance_BDD_semaine(23);
        System.out.println(ut);
        return ut;
        
    }
        public ArrayList<Seance>infosemaineprof(int id_prof,int numsemaine)
    {
        
        ArrayList<Seance> ut = Seance.charger_seance_BDD_semaine_prof(numsemaine,id_prof);
        System.out.println(ut);
        return ut;
        
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


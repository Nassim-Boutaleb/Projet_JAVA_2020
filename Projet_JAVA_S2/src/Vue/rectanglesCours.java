/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Cours;
import Modele.Enseignant;
import Modele.Groupe;
import Modele.Salle;
import Modele.Seance;
import Modele.TypeCours;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pezi
 */
public class rectanglesCours extends JPanel {
        
            Seance seance;
           
            JPanel carre;
           
            JPanel titre;
            JPanel groupe;
            JPanel salle;
            JPanel format;
            JPanel enseignant;
            
    public rectanglesCours(Seance dlseance,float rand1,float rand2,float rand3){
        this.setPreferredSize(new Dimension(190, 100));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS ));
        seance=dlseance;
        seance.getCours().getNom();
        ArrayList <Groupe> liste_groupes =seance.getListeGroupes();
        ArrayList <Salle> liste_salles = seance.getListeSalles();
        ArrayList <Enseignant> liste_enseignants= seance.getListeEnseignants();
        TypeCours type = seance.getType();
        Cours Objetcours = seance.getCours();
        carre= new JPanel();
        titre= new JPanel();
        groupe= new JPanel();
        salle= new JPanel();
        format= new JPanel();
        enseignant=new JPanel();

        if(seance.getEtat()==3){
            carre.add(new JLabel("COURS ANNULE"));
            carre.setBackground( Color.orange);
            this.add(carre);
            }
        if(seance.getEtat()==1){
            carre.add(new JLabel("NON VALIDE"));
            carre.setBackground( Color.YELLOW);
            this.add(carre);
            }
            
      
            titre.add(new JLabel(Objetcours.getNom()+"  "));
            titre.setLayout(new BoxLayout(titre,BoxLayout.X_AXIS ));
            titre.setBackground( new Color(rand1,rand2,rand3));
            this.setBackground( new Color(rand1,rand2,rand3));
         //   color couleur =new color(10,30,50);
           for(int ugroupe=0;ugroupe<liste_groupes.size();ugroupe++){
           JLabel nomgroupe= new JLabel(liste_groupes.get(ugroupe).getNom()+"  ");
           groupe.setLayout(new BoxLayout(groupe,BoxLayout.X_AXIS ));
           groupe.add(nomgroupe);
           groupe.setBackground( new Color(rand1,rand2,rand3));
           }

           for(int usalles=0;usalles<liste_salles.size();usalles++){
           JLabel nomsalle= new JLabel(liste_salles.get(usalles).getNom()+"  ");
           salle.setLayout(new BoxLayout(salle,BoxLayout.X_AXIS ));
           salle.add(nomsalle);
           salle.setBackground(new Color(rand1,rand2,rand3));
           }
           
           for(int uprof=0;uprof<liste_enseignants.size();uprof++){
               
           JLabel nomprof= new JLabel(liste_enseignants.get(uprof).getNom()+"  ");
           enseignant.setLayout(new BoxLayout(enseignant,BoxLayout.X_AXIS ));
           enseignant.add(nomprof);
           enseignant.setBackground( new Color(rand1,rand2,rand3));
          
           }
        this.add(titre);
        this.add(enseignant);
        this.add(salle);
        this.add(groupe);
        
    }
    public JPanel coursrec(){
        return this;
    }
}
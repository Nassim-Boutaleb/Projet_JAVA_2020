/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import DAO.UtilisateurDAO;
import Modele.Utilisateur;
import controleur.EdtControleur;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pezi
 */
public class EdtPage extends JFrame implements ActionListener{
    private GridLayout calendar;
    private JFrame GlobalF;
    private JPanel panelGlobal;// panels
    private JPanel panelmenu;  
    private JPanel panelrecherche;
    private JPanel panelsemaines;
    private JPanel paneltableau;
    private JComboBox affichage;
    private JComboBox recherche;
    private JButton rechercher; 
    private JTextField saisieRecherche;
    private JMenuBar menubar;
    private EdtControleur edtc ;  // stocker le controleur 
    private int WINDOW_WIDTH = 900;
    private int WINDOW_HEIGHT = 900;


public EdtPage (int width , int height , EdtControleur edtC)
    {
        GlobalF= new JFrame();
        panelGlobal = new JPanel();
        // stocker le controleur
        this.edtc = edtC;
        
        // créer les différents panels 
        panelmenu = new JPanel();
        panelrecherche=new JPanel();
        panelsemaines=new JPanel();
        paneltableau=new JPanel();
        // Titre de la fenetre
        setTitle ("Emploi du temps");
        
        //Quitter le programme si on clique sur la croix 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // stocker les tailles à partir des parametres
        WINDOW_WIDTH = width;
        WINDOW_HEIGHT = height;
        
        // régler la taille
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // construire le panel global
        buildPanelmenu();
        buildPanelrecherche();
        
        // Ajouter le panel global à la frame
        panelGlobal.add(panelmenu);
        panelGlobal.add(panelrecherche);
        panelGlobal.setLayout(new GridLayout(2,1));
        add(panelGlobal);
        
        // Nécéssaire pour l'image de fond
        //pack();
       
        
       
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    private void buildPanelmenu() {
        
        menubar = new JMenuBar();
        JMenu edt = new JMenu("Emploi du temps ");
        JMenu cours = new JMenu("Récapitulatif des cours");
        JMenuItem size = new JMenuItem("size");
        edt.add(size);
        cours.add(size);
        menubar.add(edt);
        menubar.add(cours);
        panelmenu.add(menubar);
        
    }
    private void buildPanelrecherche(){
        
        
        String[] choices1 = { "En grille","En liste"};
        String[] choices2 = { "Saisie du nom"};
        affichage = new JComboBox(choices1);
        recherche = new JComboBox(choices2);
        rechercher = new JButton("Recherche");
        saisieRecherche = new JTextField("",30);
        panelrecherche.add(affichage);
        panelrecherche.add(recherche);
        panelrecherche.add(saisieRecherche);
        panelrecherche.add(rechercher);
        
        
    }
    private void buildPanelsemaines(){
        
    }
    private void buildPaneltableau(){
        
    }

 
}

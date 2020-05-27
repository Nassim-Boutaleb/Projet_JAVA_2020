/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import controleur.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Nassim
 */
public class Modifier_supprimer_cours extends JDialog implements ActionListener
{
    // Controleur
    private AdminGestionControleur controleur ;

    // tailles:
    private final int WIDTH = 700;
    private final int HEIGHT = 500;
    
    
    
    private JPanel panelGlobal ;// panel global
    private JButton B_ok; // Bouton de sortie
    
    

  
    private JPanel P_cours ;
    private String[] liste_cours = {"Traitement du signal 1","Porjet ING3","Mechkour"};
    private JComboBox CB_cours ;
    private JPanel P_Boutons;
    private JButton B_renommer ;
    private JButton B_supprimer ;
    

    // Paramètres : parent = la fenêtre parente (peut etre null) , modal = si la dialogue est bloquante ou non 
    public Modifier_supprimer_cours(AdminGestionControleur cont, JFrame parent, String titre, boolean modal )
    {
        super(parent,titre,modal);
        
        // sauvegarder le controleur
        this.controleur = cont;
        
        // Initialiser les listes pour les listes
        //this.liste_promo = liste_promo;
        
        // Créer le panel global
        panelGlobal = new JPanel(); 
        panelGlobal.setBackground(Color.white);
        buildPanel();
        
     
        add (panelGlobal);
        
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
    
    public void buildPanel ()
    {
        // Le panel global sera constitué d'une grille
        panelGlobal.setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();  // contraintes de grille
        
        constr.insets = new Insets(5, 0, 20, 0);  // Top-left_bottom_right  marges
        constr.anchor = GridBagConstraints.CENTER;
        
        // PANEL liste des cours
        P_cours = new JPanel();
        P_cours.setLayout(new GridLayout(0, 1));
        P_cours.setBackground(Color.white);
        
        CB_cours = new JComboBox(liste_cours);
        
        P_cours.add (new JLabel("Choisir la matière à modifier/supprimer"));
        P_cours.add(CB_cours);
        
        
        
        // Boutons renommer et supprimer le cours
        P_Boutons = new JPanel();
        P_Boutons.setLayout(new BoxLayout(P_Boutons,BoxLayout.LINE_AXIS));
        B_renommer = new JButton("Renommer la matière");
        B_supprimer = new JButton("Supprimer la matière");
        P_Boutons.add (B_renommer);
        P_Boutons.add(Box.createHorizontalStrut(20) );
        P_Boutons.add(B_supprimer);
        P_Boutons.setBackground(Color.white);
        
        // Le bouton OK
        B_ok = new JButton("Valider");

        // Ajouter les panels au panel global dans la grille
        constr.gridx = 0;  constr.gridy = 0;  // se positionner
        panelGlobal.add(P_cours,constr);
        
        constr.gridy = 1;
        panelGlobal.add(P_Boutons,constr);
        
        constr.gridy = 2;
        panelGlobal.add(B_ok,constr);
        
        

        
        
        
        
        
    }
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

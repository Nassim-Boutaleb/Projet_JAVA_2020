/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Site;
import controleur.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Nassim
 */
// Source : https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/24770-gerez-des-menus-et-des-boites-de-dialogue
/* La différence entre une JDialog et JFrame est que la JDialog bloque la JFrame parente et ne peut pas être réduite */
public class SelectionEdtDialog extends JDialog implements ActionListener, ItemListener
{
    // Controleur
    private AdminGestionControleur controleur ;

    // tailles:
    private final int WIDTH = 700;
    private final int HEIGHT = 500;
    private Dimension taillePanels = new Dimension(600,50);  // La taille des panels promo/etudiant/...
    
    
    private JPanel panelGlobal ;// panel global
    private JButton annuler;  // Bouton global d'annulation
    
    // liste des sites
    ArrayList<Site> liste_des_sites ;
    
    // Edt en fonction de la promo
    private JPanel P_promo ;
    private String[] liste_promo ;
    private JComboBox  CB_choixPromo ;
    private JButton B_Promo;
    
    // Edt en fonction de l'etudiant
    private JPanel P_etudiant ;
    private String[] liste_etudiant;
    private JComboBox  CB_choixEtudiant ;
    private JButton B_Etudiant;
    private JTextField T_etudiant;
    
    // Edt en fonction de l'enseignant
    private JPanel P_enesignant ;
    private String[] liste_enseignant; // = {"Jean-Pierre Segado","Michel Pullicino"};
    private JComboBox  CB_choixEnseignant ;
    private JButton B_Enseignant;
    private JTextField T_enseignant;
    
    // Edt en fonction de la salle
    private JPanel P_salle ;
    private String[] liste_site ;//=  {"Eiffel 1","Eiffel 2"};
    private JComboBox  CB_choixSite;
    private String[] liste_salles ; // = {"P445","P446"};
    private JComboBox  CB_choixSalles;
    private JButton B_Salle;

    // Paramètres : parent = la fenêtre parente (peut etre null) , modal = si la dialogue est bloquante ou non et les listes des promotions, étudiants et enseignants et salles (en f° du site ?)
    public SelectionEdtDialog (AdminGestionControleur cont, JFrame parent, String titre, boolean modal )
    {
        super(parent,titre,modal);
        
        // sauvegarder le controleur
        this.controleur = cont;
        
        // Initialiser les listes pour les listes
        liste_promo = controleur.get_liste_promos();
        liste_etudiant = controleur.get_liste_etudiants();
        liste_enseignant = controleur.get_liste_enseignants();
        liste_site = controleur.get_liste_sites();
        
        // par défaut la liste de salle est celle de E1
        liste_des_sites = controleur.get_list_sites_Sites();
        
        liste_salles = new String[liste_des_sites.get(0).getListeSalles().size()];
        for (int i = 0; i < liste_des_sites.get(0).getListeSalles().size(); i++) 
        {
            liste_salles[i] = liste_des_sites.get(0).getListeSalles().get(i).getNom();
        } 
        
        
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
        // Le panel global sera constitué d'une grille dans laquelle tout sera mis à la ligne
        panelGlobal.setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();  // contraintes de grille
        
        constr.insets = new Insets(5, 0, 5, 0);  // Top-left_bottom_right  marges
        constr.anchor = GridBagConstraints.CENTER;
        
        // Un panel pour le choix d'un edt en fonction de la promotion
        // Les éléments du panel (liste et bouton seront en ligne avec le box layout)
        P_promo = new JPanel();
        P_promo.setLayout(new BoxLayout(P_promo,BoxLayout.LINE_AXIS));
        P_promo.setBorder(BorderFactory.createTitledBorder("Affichage par promo"));
        P_promo.setBackground(Color.white);
        
        
        CB_choixPromo = new JComboBox(liste_promo);
        B_Promo = new JButton("Afficher l'EDT de la promotion");
        B_Promo.addActionListener(this);
        
        
        P_promo.add(CB_choixPromo);
        P_promo.add(Box.createHorizontalStrut(30)); // on ajoute un composant invisible, de largeur fixe égale à 20 pixels.
        P_promo.add (B_Promo);
        P_promo.setPreferredSize(taillePanels);
       
        // Un panel pour le choix d'un edt pour un étudiant
        P_etudiant = new JPanel();
        P_etudiant.setLayout(new BoxLayout(P_etudiant,BoxLayout.LINE_AXIS));
        P_etudiant.setBorder(BorderFactory.createTitledBorder("Affichage par etudiant"));
        P_etudiant.setBackground(Color.white);
        
        CB_choixEtudiant = new JComboBox(liste_etudiant);
        T_etudiant = new JTextField("Tapez numero etudiant");
        B_Etudiant = new JButton("Afficher l'EDT de l'etudiant");
        B_Etudiant.addActionListener(this);
        
        P_etudiant.add(CB_choixEtudiant);
        P_etudiant.add(new JLabel("  ou  ")); // on ajoute un composant invisible, de largeur fixe égale à 20 pixels.
        P_etudiant.add(T_etudiant);
        P_etudiant.add(Box.createHorizontalStrut(30));
        P_etudiant.add (B_Etudiant);
        P_etudiant.setPreferredSize(taillePanels);
        
        // Un panel pour le choix d'un edt pour un enseignant
        P_enesignant = new JPanel();
        P_enesignant.setLayout(new BoxLayout(P_enesignant,BoxLayout.LINE_AXIS));
        P_enesignant.setBorder(BorderFactory.createTitledBorder("Affichage par enseignant"));
        P_enesignant.setBackground(Color.white);
        
        CB_choixEnseignant = new JComboBox(liste_enseignant);
        T_enseignant = new JTextField("Tapez nom enseignant");
        B_Enseignant = new JButton("Afficher l'EDT de l'enseignant");
        B_Enseignant.addActionListener(this);
        
        P_enesignant.add(CB_choixEnseignant);
        P_enesignant.add(new JLabel("  ou  ")); // on ajoute un composant invisible, de largeur fixe égale à 20 pixels.
        P_enesignant.add(T_enseignant);
        P_enesignant.add(Box.createHorizontalStrut(30));
        P_enesignant.add (B_Enseignant);
        P_enesignant.setPreferredSize(taillePanels);
        
        // un panel pour le choix de l'edt par salle
        P_salle = new JPanel();
        P_salle.setLayout(new BoxLayout(P_salle,BoxLayout.LINE_AXIS));
        P_salle.setBorder(BorderFactory.createTitledBorder("Affichage par salle"));
        P_salle.setBackground(Color.white);
        
        CB_choixSite = new JComboBox(liste_site);
        CB_choixSite.addItemListener(this);
        CB_choixSalles = new JComboBox(liste_salles);
        B_Salle = new JButton("Afficher l'EDT de la salle");
        B_Salle.addActionListener(this);
        
        P_salle.add(CB_choixSite);
        P_salle.add(new JLabel("  puis choisir la salle  ")); // on ajoute un composant invisible, de largeur fixe égale à 20 pixels.
        P_salle.add(CB_choixSalles);
        P_salle.add(Box.createHorizontalStrut(30));
        P_salle.add (B_Salle);
        P_salle.setPreferredSize(taillePanels);
        
        // Annuler
        annuler = new JButton("Annuler");
        annuler.addActionListener(this);
        
        // Ajouter les panels au panel global dans la grille
        constr.gridx = 0;  constr.gridy = 0;  // se positionner
        panelGlobal.add(P_promo,constr);
        
        constr.gridy = 1;
        panelGlobal.add(P_etudiant,constr);
        
        constr.gridy = 2;
        panelGlobal.add(P_enesignant,constr);
        
        constr.gridy = 3;
        panelGlobal.add(P_salle,constr);
        
        constr.gridy = 4;
        panelGlobal.add(annuler,constr);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == B_Promo)
        {
            JOptionPane.showMessageDialog(this, "BBJ");
        }
        if (e.getSource() == annuler)
        {
            dispose();
        }
    }

    /**
     * cette méthode est appellée à chaque fois que le site sélectionné sur la combo box est modifié
     * @param item
     */
    @Override
    public void itemStateChanged(ItemEvent item) 
    {
        if (item.getSource() == CB_choixSite)
        {
            // Site séléctionné
            String siteChoisi = (String)CB_choixSite.getSelectedItem();
            
            if (siteChoisi.equals("E1")) // on affiche les salles de E1 (position 0 de la liste_des_sites )
            {
                // Modifier la liste_salles
                liste_salles = new String[liste_des_sites.get(0).getListeSalles().size()]; // réinitialiser la taille de la liste_salles
                for (int i = 0; i < liste_des_sites.get(0).getListeSalles().size(); i++) 
                {
                    liste_salles[i] = liste_des_sites.get(0).getListeSalles().get(i).getNom();
                } 
                
                // recréer la combo box
                CB_choixSalles.removeAllItems();
                for (int i = 0; i < liste_salles.length; i++) 
                {
                    CB_choixSalles.addItem(liste_salles[i]);
                }
            }
            else if (siteChoisi.equals("E2")) // on affiche les salles de E2 (position 1 de la liste_des_sites )
            {
                liste_salles = new String[liste_des_sites.get(1).getListeSalles().size()];
                for (int i = 0; i < liste_des_sites.get(1).getListeSalles().size(); i++) 
                {
                    liste_salles[i] = liste_des_sites.get(1).getListeSalles().get(i).getNom();
                } 
                
                // recréer la combo box
                CB_choixSalles.removeAllItems();
                for (int i = 0; i < liste_salles.length; i++) 
                {
                    CB_choixSalles.addItem(liste_salles[i]);
                }
            }
            else if (siteChoisi.equals("E3")) // on affiche les salles de E3 (position 2 de la liste_des_sites )
            {
                liste_salles = new String[liste_des_sites.get(2).getListeSalles().size()];
                for (int i = 0; i < liste_des_sites.get(2).getListeSalles().size(); i++) 
                {
                    liste_salles[i] = liste_des_sites.get(2).getListeSalles().get(i).getNom();
                } 
                
                // recréer la combo box
                CB_choixSalles.removeAllItems();
                for (int i = 0; i < liste_salles.length; i++) 
                {
                    CB_choixSalles.addItem(liste_salles[i]);
                }
            }
            else if (siteChoisi.equals("E4")) // on affiche les salles de E4 (position 3 de la liste_des_sites )
            {
                liste_salles = new String[liste_des_sites.get(3).getListeSalles().size()];
                for (int i = 0; i < liste_des_sites.get(3).getListeSalles().size(); i++) 
                {
                    liste_salles[i] = liste_des_sites.get(3).getListeSalles().get(i).getNom();
                } 
                
                // recréer la combo box
                CB_choixSalles.removeAllItems();
                for (int i = 0; i < liste_salles.length; i++) 
                {
                    CB_choixSalles.addItem(liste_salles[i]);
                }
            }
            else if (siteChoisi.equals("E5")) // on affiche les salles de E5 (position 4 de la liste_des_sites )
            {
                liste_salles = new String[liste_des_sites.get(4).getListeSalles().size()];
                for (int i = 0; i < liste_des_sites.get(4).getListeSalles().size(); i++) 
                {
                    liste_salles[i] = liste_des_sites.get(4).getListeSalles().get(i).getNom();
                } 
                
                // recréer la combo box
                CB_choixSalles.removeAllItems();
                for (int i = 0; i < liste_salles.length; i++) 
                {
                    CB_choixSalles.addItem(liste_salles[i]);
                }
            }
            else if (siteChoisi.equals("VELL")) // on affiche les salles de VELL (position 5 de la liste_des_sites )
            {
                liste_salles = new String[liste_des_sites.get(5).getListeSalles().size()];
                for (int i = 0; i < liste_des_sites.get(5).getListeSalles().size(); i++) 
                {
                    liste_salles[i] = liste_des_sites.get(5).getListeSalles().get(i).getNom();
                } 
                
                // recréer la combo box
                CB_choixSalles.removeAllItems();
                for (int i = 0; i < liste_salles.length; i++) 
                {
                    CB_choixSalles.addItem(liste_salles[i]);
                }
            }
            else if (siteChoisi.equals("CNAM")) // on affiche les salles de CNAM (position 6 de la liste_des_sites )
            {
                liste_salles = new String[liste_des_sites.get(6).getListeSalles().size()];
                for (int i = 0; i < liste_des_sites.get(6).getListeSalles().size(); i++) 
                {
                    liste_salles[i] = liste_des_sites.get(6).getListeSalles().get(i).getNom();
                } 
                
                // recréer la combo box
                CB_choixSalles.removeAllItems();
                for (int i = 0; i < liste_salles.length; i++) 
                {
                    CB_choixSalles.addItem(liste_salles[i]);
                }
            }
        }
    }
     
}

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
public class AjouterSeanceDialog extends JDialog implements ActionListener
{
    // Controleur
    private AdminGestionControleur controleur ;

    // tailles:
    private final int WIDTH = 700;
    private final int HEIGHT = 500;
    
    
    
    private JPanel panelGlobal ;// panel global
    private JButton B_annuler;  // Bouton global d'annulation
    private JButton B_valider; // Bouton de validation
    private JScrollPane S_panelGlobalDefilant;
    
    // Réglages de date
    private JPanel P_date ;
    private JFormattedTextField T_semaine = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private String[] liste_jours = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
    private JList<String> JL_jours;
    private JFormattedTextField T_heureDeb = new JFormattedTextField(DateFormat.getTimeInstance());
    private JFormattedTextField T_heureFin = new JFormattedTextField(DateFormat.getTimeInstance());
    
    // Réglages de promo, groupes, enseignants et salles
    private JPanel P_donnes;
    private String[] liste_promos = {"2021","2022"};
    private JComboBox CB_promos;
    
    private String[] liste_groupes = {"TD1","TD2","TD3"};
    private JList<String> JL_groupes ;
    
    private String[] liste_enseignants = {"JPS","Rendler","Mechkour"};
    private JList<String> JL_enseignants ;
    
    private String[] liste_salles = {"P304","P305","EM009"};
    private JList<String> JL_salles ;
    
    
    // réglages de cours
    private JPanel P_cours ;
    
    private String[] liste_cours = {"Traitement du signal 1","Porjet ING3","Mechkour"};
    private JComboBox CB_cours ;
    
    private String[] liste_types = {"CM","CI","TP"};
    private JComboBox CB_types;
    
    private String[] liste_etat = {"Validé","En attente de validation"};
    private JComboBox CB_etat ;
    

    // Paramètres : parent = la fenêtre parente (peut etre null) , modal = si la dialogue est bloquante ou non 
    public AjouterSeanceDialog (AdminGestionControleur cont, JFrame parent, String titre, boolean modal )//, String [] listePromo , String [] listeEtudiants , String [] listeEnseignants)
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
        
        // Ajouter le panel global au panel défilant et l'ajouter à la frame
        S_panelGlobalDefilant = new JScrollPane(panelGlobal);
        add (S_panelGlobalDefilant);
        
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
    
    public void buildPanel ()
    {
        // Le panel global sera constitué d'une grille
        panelGlobal.setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();  // contraintes de grille
        
        constr.insets = new Insets(5, 0, 5, 0);  // Top-left_bottom_right  marges
        constr.anchor = GridBagConstraints.CENTER;
        
        // PANEL REGLAGES DE DATE
        P_date = new JPanel();
        P_date.setLayout(new BoxLayout(P_date,BoxLayout.PAGE_AXIS)); // chaque élément sera sur une ligne différente
        P_date.setBorder(BorderFactory.createTitledBorder("Choisir la date et l'heure"));
        P_date.setBackground(Color.white);
        P_date.setPreferredSize(new Dimension(600, 300));
        
        // on va mettre les text field de semaine et date sur 2 lignes
        Box semaineDate = Box.createVerticalBox(); // Un box est un JPanel avec une BoxLayout (LINE_AXIS ou PAGES_AXIS)
        JL_jours = new JList<>(liste_jours);
        JL_jours.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JL_jours.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        T_semaine.setText("Saisir date");
        semaineDate.add (new JLabel ("Numéro de la semaine"));
        semaineDate.add(T_semaine);
        semaineDate.add(new JLabel("Jours de la semaine (Maintenir CTRL pour sélectionner plusieurs)"));
        semaineDate.add(JL_jours);
        
        // On va mettre les text fiels des heures de début et fin sur la même ligne
        Box heures = Box.createHorizontalBox();
        heures.add (new JLabel ("Heure de début"));
        heures.add(T_heureDeb);
        heures.add(new JLabel("Heure de fin"));
        heures.add(T_heureFin);
        
        // Ajouter les box au panel
        P_date.add (semaineDate);
        P_date.add (Box.createVerticalStrut(30));
        P_date.add (heures);
        
        // PANEL REGLAGES PROMO GROUPE PROF SALLE
        P_donnes = new JPanel();
        P_donnes.setLayout(new BoxLayout(P_donnes,BoxLayout.PAGE_AXIS)); // chaque élément sera sur une ligne différente
        P_donnes.setBorder(BorderFactory.createTitledBorder("Choisir les groupes et enseignants"));
        P_donnes.setBackground(Color.white);
        P_donnes.setPreferredSize(new Dimension(600, 300));
        
        P_donnes.add (new JLabel("Promotion:"));
        CB_promos = new JComboBox(liste_promos);
        P_donnes.add (CB_promos);
        
        P_donnes.add (new JLabel("Groupe (maintenir CTRL pour sélectionner plusieurs groupes)"));
        JL_groupes = new JList<>(liste_groupes);
        JL_groupes.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        P_donnes.add (JL_groupes);
        
        P_donnes.add (new JLabel("Enseignant(s) (maintenir CTRL pour sélectionner plusieurs enseignants)"));
        JL_enseignants = new JList<>(liste_enseignants);
        JL_enseignants.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        P_donnes.add (JL_enseignants);
        
        P_donnes.add (new JLabel("Salle(s) (maintenir CTRL pour sélectionner plusieurs salles)"));
        JL_salles = new JList<>(liste_salles);
        JL_salles.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        P_donnes.add (JL_salles);
        
        //PANEL REGLAGES DU COURS ET DY TYPE
        P_cours = new JPanel();
        P_cours.setLayout(new GridLayout(0,2));
        P_cours.setBorder(BorderFactory.createTitledBorder("Choisir la matière et le type de cours"));
        P_cours.setBackground(Color.white);
        P_cours.setPreferredSize(new Dimension(600,150));
        
        P_cours.add (new JLabel("Matière:  "));
        CB_cours = new JComboBox(liste_cours);
        P_cours.add (CB_cours);
        
        
        P_cours.add (new JLabel("Type de séance:  "));
        CB_types = new JComboBox(liste_types);
        P_cours.add (CB_types);
        
        P_cours.add (new JLabel("Etat de la séance:"));
        CB_etat = new JComboBox(liste_etat);
        P_cours.add (CB_etat);
        
        
        // Annuler et valider
        JPanel P_Boutons = new JPanel();
        P_Boutons.setLayout(new BoxLayout(P_Boutons,BoxLayout.LINE_AXIS));
        B_annuler = new JButton("Annuler");
        B_valider = new JButton("Valider et ajouter la séance");
        P_Boutons.add (B_annuler);
        P_Boutons.add(Box.createHorizontalStrut(20) );
        P_Boutons.add(B_valider);
        P_Boutons.setBackground(Color.white);
        
        
        // Ajouter les panels au panel global dans la grille
        constr.gridx = 0;  constr.gridy = 0;  // se positionner
        panelGlobal.add(P_date,constr);
        
        constr.gridy = 1;
        panelGlobal.add(P_donnes,constr);
        
        constr.gridy = 2;
        panelGlobal.add(P_cours,constr);
        
        constr.gridy = 3;
        panelGlobal.add(P_Boutons,constr);

        
        
        
        
        
    }
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

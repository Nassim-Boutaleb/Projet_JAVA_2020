/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.*;
import controleur.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Nassim
 */
public class AjouterSeanceDialog extends JDialog implements ActionListener, ItemListener
{
    // Controleur
    private AdminGestionControleur controleur ;

    // tailles:
    private final int WIDTH = 700;
    private final int HEIGHT = 500;
    
    private ArrayList<Promotion> liste_des_promotions;
    
    
    private JPanel panelGlobal ;// panel global
    private JButton B_annuler;  // Bouton global d'annulation
    private JButton B_valider; // Bouton de validation
    private JScrollPane S_panelGlobalDefilant;
    
    // Réglages de date
    private JPanel P_date ;
    private JCheckBox check_uneSemaine;
    private JFormattedTextField T_uneSemaine = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JCheckBox check_intervalleSemaine ;
    private JFormattedTextField T_intervalleSemaineD = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField T_intervalleSemaineF = new JFormattedTextField(NumberFormat.getIntegerInstance());
    
    private String[] liste_jours = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
    private JList<String> JL_jours;
    private JFormattedTextField T_heureDeb = new JFormattedTextField(DateFormat.getTimeInstance());
    private JFormattedTextField T_heureFin = new JFormattedTextField(DateFormat.getTimeInstance());
    
    // Réglages de promo, groupes, enseignants et salles
    private JPanel P_donnes;
    private String[] liste_promos;// = {"2021","2022"};
    private JComboBox CB_promos;
    
    private String[] liste_groupes; // = {"TD1","TD2","TD3"};
    private JList<String> JL_groupes ;
    JScrollPane scoll_groupes;
    
    private String[] liste_enseignants;// = {"JPS","Rendler","Mechkour"};
    private JList<String> JL_enseignants ;
    
    private String[] liste_salles; // = {"P304","P305","EM009"};
    private JList<String> JL_salles ;
    
    
    // réglages de cours
    private JPanel P_cours ;
    
    private String[] liste_cours; // {"Traitement du signal 1","Porjet ING3","Mechkour"};
    private JComboBox CB_cours ;
    
    private String[] liste_types = {"CM","CI","TP"};
    private JComboBox CB_types;
    
    private String[] liste_etat = {"Valide","En attente de validation"};
    private JComboBox CB_etat ;
    

    // Paramètres : parent = la fenêtre parente (peut etre null) , modal = si la dialogue est bloquante ou non 
    public AjouterSeanceDialog (AdminGestionControleur cont, JFrame parent, String titre, boolean modal )
    {
        super(parent,titre,modal);
        
        // sauvegarder le controleur
        this.controleur = cont;
        
        // Initialiser les listes pour les combo box
        liste_promos = controleur.get_liste_promos();
        liste_enseignants = controleur.get_liste_enseignants();
        System.out.println("Mp"+liste_enseignants.length);
        liste_salles = controleur.get_liste_salles();
        //liste_groupes = controleur.get_liste_groupes();
        liste_des_promotions = controleur.get_liste_promo();
        System.out.println("OOL: "+liste_des_promotions.get(0).getGroupes().size());
        liste_groupes = new String[liste_des_promotions.get(0).getGroupes().size()];
        for (int i = 0; i < liste_des_promotions.get(0).getGroupes().size(); i++) 
        {
            liste_groupes[i] = liste_des_promotions.get(0).getGroupes().get(i).getNom();
        }
        liste_cours = controleur.get_liste_cours();
        liste_types = controleur.get_liste_types_cours();
        
        
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
        
        Box choixUneSemaine =Box.createHorizontalBox(); // en ligne la case à cocher+ le text field
        T_uneSemaine.setText("Saisir date: ");
        check_uneSemaine = new JCheckBox();
        choixUneSemaine.add(check_uneSemaine); 
        choixUneSemaine.add (new JLabel ("Numéro de la semaine"));
        choixUneSemaine.add(T_uneSemaine);
        
        Box choixIntervalleSemaine =Box.createHorizontalBox(); // en ligne la case à cocher+ le text field
        T_intervalleSemaineD.setText("Saisir semaine début");
        T_intervalleSemaineF.setText("saisir semaine fin");
        check_intervalleSemaine = new JCheckBox();
        choixIntervalleSemaine.add(check_intervalleSemaine); 
        choixIntervalleSemaine.add (new JLabel ("Séance récurrente: du "));
        choixIntervalleSemaine.add(T_intervalleSemaineD);
        choixIntervalleSemaine.add(new JLabel ("  au  "));
        choixIntervalleSemaine.add(T_intervalleSemaineF);
        
        semaineDate.add (choixUneSemaine);
        semaineDate.add (choixIntervalleSemaine);
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
        
        
        
        P_donnes.add (new JLabel("Enseignant(s) (maintenir CTRL pour sélectionner plusieurs enseignants)"));
        JL_enseignants = new JList<>(liste_enseignants);
        JL_enseignants.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        JScrollPane scoll_enseignants = new JScrollPane(JL_enseignants);
        P_donnes.add (scoll_enseignants);
        
        P_donnes.add (new JLabel("Salle(s) (maintenir CTRL pour sélectionner plusieurs salles)"));
        JL_salles = new JList<>(liste_salles);
        JL_salles.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        JScrollPane scroll_salles = new JScrollPane(JL_salles);
        P_donnes.add (scroll_salles);
        
        P_donnes.add (new JLabel("Promotion:"));
        CB_promos = new JComboBox(liste_promos);
        CB_promos.addItemListener(this);
        P_donnes.add (CB_promos);
        
        P_donnes.add (new JLabel("Groupe (maintenir CTRL pour sélectionner plusieurs groupes)"));
        JL_groupes = new JList<>(liste_groupes);
        JL_groupes.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        scoll_groupes = new JScrollPane(JL_groupes);
        P_donnes.add (scoll_groupes);
        
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
        B_annuler.addActionListener(this);
        B_valider = new JButton("Valider et ajouter la séance");
        B_valider.addActionListener(this);
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
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == B_annuler)
        {
            dispose();
        }
        else if (e.getSource() == B_valider)
        {
            // Stocker les séances des différentes dates
            ArrayList<Seance> liste_des_seances_a_ajouter = new ArrayList<>(100);

            // Récupérer les données utilisateur
            String sSemaine = T_uneSemaine.getText();
            int semaine = 0;
            if ( ! sSemaine.equals(""))
            {
                semaine = Integer.parseInt(sSemaine);
            }
            
            
            int [] jours_indices = JL_jours.getSelectedIndices();
            ArrayList<String> jours_choisis = new ArrayList<>(100);
            for (int i = 0; i < jours_indices.length; i++) 
            {
                System.out.println("indices: "+jours_indices[i]);
                System.out.println(liste_jours[jours_indices[i]]);
                jours_choisis.add(liste_jours[jours_indices[i]]);
            }
            
            String sheureDebut = T_heureDeb.getText();
            LocalTime heureDebut = null;
            if (! sheureDebut.equals(""))
            {
               heureDebut = LocalTime.parse (sheureDebut); 
            }
            
            
            String sheureFin = T_heureFin.getText();
            LocalTime heureFin = null;
            if (! sheureFin.equals(""))
            {
                heureFin= LocalTime.parse(sheureFin);
            }
                 
                    
            
            String promotion =(String) CB_promos.getSelectedItem();
            
            int [] groupe_indices = JL_groupes.getSelectedIndices();
            ArrayList<String> groupe_choisis = new ArrayList<>(100);
            for (int i = 0; i < groupe_indices.length; i++) 
            {
                groupe_choisis.add(liste_groupes[groupe_indices[i]]);
            }
            
            int [] enseignants_indices = JL_enseignants.getSelectedIndices();
            ArrayList<String> enseignants_choisis = new ArrayList<>(100);
            for (int i = 0; i < enseignants_indices.length; i++) 
            {
                enseignants_choisis.add(liste_enseignants[enseignants_indices[i]]);
            }
            
            int [] salles_indices = JL_salles.getSelectedIndices();
            ArrayList<String> salles_choisis = new ArrayList<>(100);
            for (int i = 0; i < salles_indices.length; i++) 
            {
                salles_choisis.add(liste_salles[salles_indices[i]]);
            }
            
            String Ncours =(String) CB_cours.getSelectedItem();
            
            String Ntype_cours =(String) CB_types.getSelectedItem();
            
            String sEtat =(String) CB_etat.getSelectedItem();
            int etat= 0;
            if (sEtat.equals("Valide"))
            {
                etat = 2;
            }
            else
            {
                etat = 1;
            }
            
            // Créer les cours,type_cours,Groupes,Enseignants,salles
            Cours cours = Cours.charger_cours_BDD(Ncours);
            TypeCours type_cours = TypeCours.charger_typecours_BDD(Ntype_cours);
            
            ArrayList <Groupe> liste_groupes_G = new ArrayList<>(100);
            for (int i = 0; i < groupe_choisis.size(); i++) 
            {
                Groupe g = Groupe.charger_groupe_BDD(groupe_choisis.get(i));
                liste_groupes_G.add (g);
            }
            
            ArrayList <Enseignant> liste_enseignant_G = new ArrayList<>(100);
            for (int i = 0; i < enseignants_choisis.size(); i++) 
            {
                String nom_prenom = enseignants_choisis.get(i);
                String[] parties = nom_prenom.split(" ");
                String nom = parties[0];
                String prenom = parties[1];
                System.out.println("Nom"+nom);
                System.out.println("Prenom"+prenom);
                Enseignant g = Enseignant.charger_enseignant_BDD_nom_prenom(nom, prenom);
                liste_enseignant_G.add (g);
            }
            
            ArrayList <Salle> liste_salles_G = new ArrayList<>(100);
            for (int i = 0; i < salles_choisis.size(); i++) 
            {
                Salle g = Salle.charger_salle_BDD(salles_choisis.get(i));
                liste_salles_G.add (g);
            }
            
            // La date ...
            Calendar calendar = new GregorianCalendar();
 
            calendar.set(Calendar.WEEK_OF_YEAR,+semaine);
            calendar.set(Calendar.YEAR,2020);
            
            // Ajouter une séance par date
            for (int i = 0; i < jours_choisis.size(); i++) 
            {
                int jour = 7;
                if (jours_choisis.get(i).equals("Lundi"))
                {
                    jour = 2;
                }
                else if (jours_choisis.get(i).equals("Mardi"))
                {
                    jour = 3;
                }
                else if (jours_choisis.get(i).equals("Mercredi"))
                {
                    jour = 4;
                }
                else if (jours_choisis.get(i).equals("Jeudi"))
                {
                    jour = 5;
                }
                else if (jours_choisis.get(i).equals("Vendredi"))
                {
                    jour = 6;
                }
                else if (jours_choisis.get(i).equals("Samedi"))
                {
                    jour = 7;
                }
                System.out.println(jour);
                calendar.set(Calendar.DAY_OF_WEEK,jour);
            
                Date d = calendar.getTime();
                LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                System.out.println("DDDATE "+date);
                
                // Créer la séance
                Seance nouvSeance = new Seance(0, semaine, date , heureDebut, heureFin, etat, cours, type_cours, liste_groupes_G, liste_salles_G, liste_enseignant_G);
                liste_des_seances_a_ajouter.add (nouvSeance);
            }
            if (sSemaine.equals("") || jours_choisis.size() == 0 || sheureDebut.equals("") || sheureFin.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Informations manquantes");
            }
            else
            {
                 controleur.ajouter_seance(liste_des_seances_a_ajouter); 
            }
            

            
            dispose();
            
           
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        if (e.getSource() == CB_promos)
        {
            // Site séléctionné
            String promoChoisie = (String)CB_promos.getSelectedItem();
            System.out.println(promoChoisie);
            Promotion p = Promotion.charger_Promotion_BDD(promoChoisie);
            System.out.println("RRRS:"+p.getNom());
            // Modifier la liste_salles
            liste_groupes = new String[p.getGroupes().size()]; // réinitialiser la taille de la liste_salles
            for (int i = 0; i < p.getGroupes().size(); i++) 
            {
                liste_groupes[i] = p.getGroupes().get(i).getNom();
            } 

            // recréer la combo box
            JL_groupes.removeAll();
            P_donnes.remove(scoll_groupes);
            JL_groupes = new JList<>(liste_groupes);
            scoll_groupes = new JScrollPane(JL_groupes);
            P_donnes.add(scoll_groupes);
            SwingUtilities.updateComponentTreeUI(this);
            System.out.println("Im here");
            
        }
    }
    
}

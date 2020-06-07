/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import DAO.UtilisateurDAO;
import Modele.Cours;
import Modele.Enseignant;
import Modele.Etudiant;
import Modele.Groupe;
import Modele.Salle;
import Modele.Seance;
import Modele.TypeCours;
import Modele.Utilisateur;
import controleur.EdtControleur;
import controleur.EdtControleurProf;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author pezi
 */
public class edtProf extends JFrame implements ActionListener, ListSelectionListener, ItemListener
{
    private GridLayout calendar;
    private JPanel panelGlobal;// panels
    private JPanel panelliste;
    private JPanel panelmenu;
    private JPanel panelrecherche;
    private JPanel panelsemaines;
    private ArrayList<JPanel> liste_panels_jour = new ArrayList<>(500); // Liste des panels jours
    private JPanel panelmois;
    private JPanel paneltableau;
    private JPanel panelgrille;
    private JPanel panelheure;
    private JComboBox affichage;
    private JComboBox recherche;
    private JButton rechercher;
    private JTextField saisieRecherche;
    private JMenuBar menubar;
    
    private EdtControleurProf edtc ;  // stocker le controleur
    private Utilisateur utilisateur;
    private Enseignant prof;
    private int droit;
    
    private int WINDOW_WIDTH = 1280;
    private int WINDOW_HEIGHT = 660;
    private JList<String> lisetSemaines;
    private JScrollPane scrollsemaine;

    // Menu contextuel uniquement affiché sur clic droit par l'admin
    
    private ArrayList<JPopupMenu> liste_des_menus = new ArrayList<>(500);
    private ArrayList<JMenuItem> liste_des_items_modifier = new ArrayList<>(500);
    private ArrayList<JMenuItem> liste_des_items_supprimer = new ArrayList<>(500);
    private ArrayList<Seance> seances = new ArrayList<>(500);

    public edtProf (int width , int height , EdtControleurProf edtC,Enseignant Prof, int droit)
    {
        panelGlobal = new JPanel();
        // stocker le controleur
        this.edtc = edtC;
        this.prof=Prof;
        this.droit = droit;


        // créer les différents panels
        panelliste = new JPanel();
        panelmenu = new JPanel();
        //panelmenu.setBackground(Color.red);
        panelrecherche=new JPanel();
        panelsemaines=new JPanel();
        panelmois=new JPanel();
 

        // Titre de la fenetre
        setTitle ("Emploi du temps");

        //Quitter le programme si on clique sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // stocker les tailles à partir des parametres
       // WINDOW_WIDTH = width;
       // WINDOW_HEIGHT = height;

        // régler la taille
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // construire le panel global
        buildPanelmenu();
        //buildPanelrecherche();
        buildPanelsemaines();
        buildPaneltableau(23);
        buildpanelliste(23);
        //definir les tailles des panels

        // Ajouter le panel global à la frame
        panelGlobal.setLayout(new BoxLayout(panelGlobal,BoxLayout.PAGE_AXIS));
        //GridBagConstraints c = new GridBagConstraints();
        //c.fill =GridBagConstraints.VERTICAL;
        //c.gridwidth = 100;
        //c.gridheight=40;
        //c.gridx =0;
        //c.gridy=0;

        panelGlobal.add(panelmenu);
        /*c.gridwidth = 100;
        c.gridheight=20;
        c.gridx =0;
        c.gridy=50; */
        //panelGlobal.add(panelrecherche);
        /*c.gridwidth = 100;
        c.gridheight=20;
        c.gridx =0;
        c.gridy=100; */
        panelGlobal.add(panelsemaines);
        /*c.gridwidth =100 ;
        c.gridheight=20;
        c.gridx =0;
        c.gridy=120; */
        //panelGlobal.add(panelmois);
        /*c.gridwidth = 200;
        c.gridheight=200;
        c.gridx =0;
        c.gridy=150; */

        //panelGlobal.add(paneltableau);
        //JPanel pp = new JPanel();
        /*pp.add (new JLabel ("Graaaace"));
        pp.setBackground(Color.red);
        panelGlobal.add (pp); */
        scrollsemaine =new JScrollPane(panelliste);
        panelGlobal.add(scrollsemaine);


        add(panelGlobal);

        // Nécéssaire pour l'image de fond
        //pack();

        


    }

   

    private void buildPanelmenu() {

        String[] choices1 = { "En grille","En liste"};
        affichage = new JComboBox(choices1);
        affichage.setSelectedIndex(1);
        affichage.addItemListener(this);
        
        
        menubar = new JMenuBar();
        JMenu edt = new JMenu("Emploi du temps ");
        JMenu cours = new JMenu("Récapitulatif des cours");
        JMenuItem size = new JMenuItem("size");
        edt.add(size);
        cours.add(size);
        menubar.add(edt);
        menubar.add(cours);
        panelmenu.add(menubar);
        panelmenu.add(affichage);

    }
    private void buildPanelrecherche(){


        
        
      
        
        
        
        saisieRecherche = new JTextField("",30);
        panelrecherche.add(affichage);
        panelrecherche.add(recherche);
        panelrecherche.add(saisieRecherche);
        panelrecherche.add(rechercher);


    }
    private void buildPanelsemaines()
    {
        Font font = new Font("Arial",Font.PLAIN,11);

        String[] numerosSemaine = new String [500];
        int k = 0;
        for(int i=31;i<53;i++)
        {
            numerosSemaine[k] = new String(""+i+"   ");
            k++;

           if(i==52){
               i=0;
           }
           if(i==30){
               i=54;
           }
        }
        lisetSemaines = new JList(numerosSemaine);
        lisetSemaines.setPreferredSize(new Dimension(1250,20));
        lisetSemaines.setBackground(Color.lightGray);
        lisetSemaines.setFont(font);
        lisetSemaines.setVisibleRowCount(-1);
        lisetSemaines.addListSelectionListener(this);
        //lisetSemaines.setBackground(Color.red);
        lisetSemaines.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        panelsemaines.add(lisetSemaines);
        //panelsemaines.setBackground(Color.green);
        //panelsemaines.setPreferredSize(new Dimension(1250,30));



        String[] mois = { "Aout","Septembre","Octobre","Novembre","Decembre","Janvier","Fevrier","Mars","Avril","Mai","Juin","juillet"};
        for(int i=0;i<12;i++){
           panelmois.add(new JButton(mois[i]));

        }
               for(Component c : panelmois.getComponents()){
            if(c instanceof JButton){
                ((JButton)c).setPreferredSize(new Dimension(100,20));
            }
        }
               panelsemaines.add(panelmois);

    }

 private void buildPaneltableau(int num_semaine)
 {
// initialiser les panels
    paneltableau=new JPanel();
    panelgrille=new JPanel();


     // panel des heures (hope it will work !)
     //paneltableau.setLayout(new GridBagLayout());
     GridBagConstraints consTableau = new GridBagConstraints();
     //paneltableau.setBackground(Color.blue);
     panelheure = new JPanel();
     //panelheure.setBackground(Color.red);
     //panelheure.setPreferredSize(new Dimension(1200,30));
     panelheure.setLayout(new GridBagLayout() );
     GridBagConstraints cons = new GridBagConstraints();
    String[] heures ={"                      8h30","  10h00 ","10h15","11h45 ","12h00","13h30 ","13h45","15h15 ","15h30","17h00 ","17h15","18h45 ","19h00","    20h30"};
    cons.gridy = 0;
    cons.gridx = 0;



   for (int i = 0; i <heures.length ; i++)
     {
           JLabel lab = new JLabel(heures[i]);
           lab.setFont(new Font ("Dialog",Font.BOLD,12));
           panelheure.add(lab);
           cons.gridx ++;

           if (i % 2 == 0)
           {
                for (int j = 0; j < 20; j++)
                {
                    panelheure.add(new JLabel (" "));
                    cons.gridx ++;
                }
           }



     }     





        //panelgrille.setSize( 400,400 );
         panelgrille.setLayout( new GridBagLayout() );
         //panelgrille.setLayout(new GridLayout());
         GridBagConstraints c = new GridBagConstraints();
         c.fill =GridBagConstraints.HORIZONTAL;


        //Container container = panelgrille.getContentPane();

        ArrayList < JPanel > components = new ArrayList < JPanel >();
        ArrayList<Seance> seances = edtc.infosemaineprof(prof.getId(),num_semaine);
        Collections.sort(seances);
        int cpt = 0;
        
        
        
        JPanel temp = null;
        String[] jours = { "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};

        
        for(int i =0;i<6;i++)
        {
          temp = new JPanel();

          temp.add(new JLabel(jours[i]));
          c.gridy = i;
          c.gridx = 0;
          panelgrille.add(temp,c);
          int z =1;
          while(z<8) // placer en ligne
          {
            c.gridx = z;
            
            temp= new JPanel();  // cours ?
              Random rand = new Random();
             float rand1 =rand.nextFloat();
             float rand2 =rand.nextFloat();
             float rand3 =rand.nextFloat();
            
            if (cpt < seances.size() )
            {
                Seance s = seances.get(cpt);
                if (seances.get(cpt).getChiffreDate() == i)
                {
                    // il y a un cours ce jour
                    System.out.println("Heure recue: "+seances.get(cpt).getChiffreHeure()+" z: "+z);
                    if (seances.get(cpt).getChiffreHeure() == z)
                    {
                        // Il y a un cours à cet horaire
                        rectanglesCours cours = new rectanglesCours(s,rand1,rand2,rand3);  // new SeanceCarre()
                        //cours.add(new JLabel(seances.get(cpt).getCours().getNom()));
                        javax.swing.border.Border line =  BorderFactory.createLineBorder(Color.white);
                        cours.setPreferredSize(new Dimension(170, 100));
                        cours.setBackground( new Color(rand1,rand2,rand3));
                        cours.setBorder(line);
                        components.add(cours);

                        //container.add(temp);
                        panelgrille.add(cours,c);
                        cpt ++;
                        
                    }
                    else
                    {
                        javax.swing.border.Border line =  BorderFactory.createLineBorder(Color.white);
                        temp.setPreferredSize(new Dimension(170, 100));
                        temp.setBackground(Color.lightGray);
                        temp.setBorder(line);
                        components.add(temp);

                        //container.add(temp);
                        panelgrille.add(temp,c);
                    }
                }
                else 
                {
                    javax.swing.border.Border line =  BorderFactory.createLineBorder(Color.white);
                    temp.setPreferredSize(new Dimension(170,100));
                    temp.setBackground(Color.lightGray);
                    temp.setBorder(line);
                    components.add(temp);

                    //container.add(temp);
                    panelgrille.add(temp,c);
                }
            }
            else
            {
                javax.swing.border.Border line =  BorderFactory.createLineBorder(Color.white);
                temp.setPreferredSize(new Dimension(170,100));
                temp.setBackground(Color.lightGray);
                temp.setBorder(line);
                components.add(temp);

                //container.add(temp);
                panelgrille.add(temp,c);
            }
            
            
            z++;
          }
        }
        
        /*for (int i = 0; i < seances.size(); i++) 
        {
            JPanel p = new JPanel();  // (seances.get(i))
            p.setBackground(Color.blue);
            
            if (seances.get(i).getDate() == "Lundi" )
            {
                 if (seances.get(i).getHeured().equals("08:30:00"))
                 {
                     JPanel m = components.get(0);
                    c.gridx =0;
                    c.gridy=0;
                    panelgrille.add(p,c);
                 }
                
            }
            
        }*/
        
        /*JPanel p = new JPanel();
        JPanel m = components.get(5);
        panelgrille.remove(m);
        p.add(new JLabel("5"));
        p.setPreferredSize(new Dimension(160, 60));

        //components.set(5,p);
        p.setBackground(Color.blue);
        c.gridx =6;
        c.gridy=0;
        //c.gridwidth = 1;
        panelgrille.add(p,c); */

        /*for(Component e : panelgrille.getComponents()){
            if(e instanceof JPanel){
                ((JPanel)e).setPreferredSize(new Dimension(160,60));
            }
        } */
        //JScrollPane scroll = new JScrollPane(temp);


        //components.get( 5 ).setBackground( Color.BLUE );
        //components.get( 8 ).setBackground( Color.GREEN );
        consTableau.gridx = 0;
        consTableau.gridy = 0;
        paneltableau.add(panelheure);

        consTableau.gridy = 1;
        paneltableau.add(panelgrille);

        //paneltableau.setPreferredSize(new Dimension(1250,5));
    }

     private void buildpanelliste(int num_semaine)
     {

            JPanel semaine=new JPanel();
            //ArrayList<Seance> seances= edtc.infosemaine();
            seances= edtc.infosemaineprof(prof.getId(),num_semaine);
            liste_panels_jour = new ArrayList<>(500);
            
            
            Collections.sort(seances);

            semaine.setBorder(BorderFactory.createLineBorder(Color.red));
            semaine.setLayout(new BoxLayout(semaine,BoxLayout.Y_AXIS ));
            LocalDate dateseance = null;
            System.out.println(""+seances.size());
            int a=0;
            
            
            
            

            for( int i=0 ;i<seances.size();i++)
            {

               // Si on est connecté en tant qu'admin : construire le menu contextuel
   
               Seance seance = seances.get(i);
               JPanel jour = null;
               if(seance.getEtat()!=1)
               {

                    ArrayList <Groupe> liste_groupes =seance.getListeGroupes();
                    ArrayList <Salle> liste_salles = seance.getListeSalles();
                    ArrayList <Enseignant> liste_enseignants= seance.getListeEnseignants();
                    TypeCours type = seance.getType();
                    Cours Objetcours = seance.getCours();

                    jour=new JPanel();
                    JPanel cours=new JPanel();
                    JPanel date= new JPanel();
                    JPanel carre= new JPanel();
                    JPanel heure= new JPanel();
                    JPanel titre= new JPanel();
                    JPanel groupe= new JPanel();
                    JPanel salle= new JPanel();
                    JPanel format= new JPanel();

                    if(seance.getEtat()==3)
                    {
                        carre.add(new JLabel(" COURS ANNULE "));
                        carre.setBackground(Color.orange);
                    }
                    heure.add(new JLabel(" "+seance.getHeured()+"-"+seance.getHeuref()+" "));

                    jour.setBorder(BorderFactory.createLineBorder(Color.black));

                    //cours.setPreferredSize(new Dimension(400,60));

                    jour.setLayout(new GridBagLayout());
                    jour.setPreferredSize(new Dimension(620,110));
                    date.setPreferredSize(new Dimension(620,50));
                    date.setBackground(Color.red);

                    cours.setLayout(new GridBagLayout());
                    GridBagConstraints e = new GridBagConstraints();
                    GridBagConstraints o = new GridBagConstraints();

                    e.fill =GridBagConstraints.HORIZONTAL;
                    o.fill =GridBagConstraints.HORIZONTAL;

                    e.gridx=0;
                    e.gridy=5;
                    cours.add(carre,e);

                    e.gridx=5;
                    cours.add(heure,e);

                    e.gridx=10;
                    titre.add(new JLabel(Objetcours.getNom()+"  "));
                   cours.add(titre,e);

                   for(int ugroupe=0;ugroupe<liste_groupes.size();ugroupe++)
                   {

                        e.gridx=15;
                        JLabel nomgroupe= new JLabel(liste_groupes.get(ugroupe).getNom()+"  ");

                        groupe.setLayout(new BoxLayout(groupe,BoxLayout.Y_AXIS ));
                        groupe.add(nomgroupe);
                        cours.add(groupe,e);

                   }

                   for(int usalles=0;usalles<liste_salles.size();usalles++)
                   {

                        e.gridx=20;
                        JLabel nomsalle= new JLabel(liste_salles.get(usalles).getNom()+"  ");
                        salle.setLayout(new BoxLayout(salle,BoxLayout.Y_AXIS ));
                        salle.add(nomsalle);
                        cours.add(salle,e);

                   }

                   e.gridx=25;

                   JLabel nomtype= new JLabel(type.getNom()+" ");
                   format.add(nomtype);
                   cours.add(format,e);

                   o.gridx=0;
                   o.gridy=a;

                   if(i==0)
                   {
                     dateseance = seance.getDate();
                     date.add(new JLabel(" "+dateseance+" "));
                     //jour.add(date,o);
                     semaine.add(date);
                   }
                   else
                   {
                        if(seance.getDate().isEqual(dateseance)==false)
                        {
                           dateseance = seance.getDate();
                           date.add(new JLabel(" "+dateseance+" "));
                           //jour.add(date,o);
                           semaine.add(date);
                        }


                   }   



                   a=a+5;

                   o.gridy=a;

                  // date.add(new JLabel(" "+seance.getDate()+" "));
                   jour.add(cours,o);
                   semaine.add(jour);
                   
                   
                   
                   a=a+5;
               }
               liste_panels_jour.add(jour);
            }

        panelliste.add(semaine);
         System.out.println("SS"+seances.size());
         System.out.println("KK"+liste_panels_jour.size());
         
         // Créer autant de menus que de panels jour
         if (this.droit == 1)
        {
                liste_des_menus= new ArrayList<>(500);
                liste_des_items_modifier= new ArrayList<>(500);
                liste_des_items_supprimer= new ArrayList<>(500);
                for (int i = 0; i < liste_panels_jour.size(); i++) 
                {

                    JPopupMenu jModSupp = new JPopupMenu();
                    JMenu JMA_modifier_seance = new JMenu("Modifier la seance");
                    JMenu JMA_supp_seance = new JMenu("Supprimer la seance");
                    JMenuItem JMIA_mod_seance = new JMenuItem("Modifier la seance");
                    JMenuItem JMIA_supp_seance = new JMenuItem("Supprimer la seance");

                    JMIA_mod_seance.addActionListener(this);
                    JMIA_supp_seance.addActionListener(this);

                    JMA_modifier_seance.add(JMIA_mod_seance);
                    JMA_supp_seance.add(JMIA_supp_seance);
                    jModSupp.add(JMA_modifier_seance);
                    jModSupp.add(JMA_supp_seance);

                    liste_des_menus.add(jModSupp);
                    liste_des_items_modifier.add(JMIA_mod_seance);
                    liste_des_items_supprimer.add(JMIA_supp_seance);
                }
        }

         // Ajouter les listeners 
         if (this.droit == 1)
         {
            for (int i = 0; i < liste_panels_jour.size(); i++) 
            {
                JPopupMenu jpm = liste_des_menus.get(i);
                JPanel jourK = liste_panels_jour.get(i);
                if (droit == 1)
                {
                   liste_panels_jour.get(i).addMouseListener(new MouseAdapter() 
                   {
                        public void mouseReleased(MouseEvent event)
                        {
                            if(event.getButton() == MouseEvent.BUTTON3)
                            {
                                if(event.isPopupTrigger())
                                {   
                                    //System.out.println("Affiche popo");
                                    jpm.show(jourK, event.getX(), event.getY());
                                }    
                            }
                        }
                    });
                }
            } 
         }
         



    }

   @Override
    public void itemStateChanged(ItemEvent e) 
    {
        if (e.getSource() == affichage )
        {
            String type_affichage = (String) affichage.getSelectedItem();
            if (type_affichage.equals("En grille"))
            {
                remove(panelGlobal);
                
                panelGlobal = new JPanel();
  
                buildPaneltableau(23);
                //buildpanelliste(23);
           

                // Ajouter le panel global à la frame
                panelGlobal.setLayout(new BoxLayout(panelGlobal,BoxLayout.PAGE_AXIS));
                

                panelGlobal.add(panelmenu);
                
                panelGlobal.add(panelrecherche);
                
                panelGlobal.add(panelsemaines);
                

                panelGlobal.add(paneltableau);
                //scrollsemaine =new JScrollPane(panelliste);
                //panelGlobal.add(scrollsemaine);

                add(panelGlobal);

                SwingUtilities.updateComponentTreeUI(this);
            }
            else if (type_affichage.equals("En liste"))
            {
                
                remove(panelGlobal);
                
                panelGlobal = new JPanel();
  
                //buildPaneltableau(23);
                buildpanelliste(23);
           

                // Ajouter le panel global à la frame
                panelGlobal.setLayout(new BoxLayout(panelGlobal,BoxLayout.PAGE_AXIS));
                

                panelGlobal.add(panelmenu);
                
                panelGlobal.add(panelrecherche);
                
                panelGlobal.add(panelsemaines);
                

                //panelGlobal.add(paneltableau);
                scrollsemaine =new JScrollPane(panelliste);
                panelGlobal.add(scrollsemaine);

                add(panelGlobal);

                SwingUtilities.updateComponentTreeUI(this);
                
            
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if (e.getSource() == lisetSemaines )
        {
            String numero_semaine = (String)lisetSemaines.getSelectedValue();
            if (numero_semaine.length() >= 5)
            {
                numero_semaine = numero_semaine.substring(0,2);
            }
            else
            {
                numero_semaine = numero_semaine.substring(0,1);
            }
            
            
            
            // Code dont on a parlé
            // En fonction de si on est en grille ou en liste
            
            /*panelGlobal.remove(scrollsemaine);
            panelliste.removeAll();
            
            int numero_semaine_choie = Integer.parseInt(numero_semaine);
            System.out.println(numero_semaine_choie);
            
            buildpanelliste(numero_semaine_choie);
            
            scrollsemaine = new JScrollPane(panelliste);
            panelGlobal.add (scrollsemaine);
            SwingUtilities.updateComponentTreeUI(this); */
            
            String type_affichage = (String) affichage.getSelectedItem();
            if (type_affichage.equals("En grille"))
            {
                panelGlobal.remove(paneltableau);
                paneltableau.removeAll();
                
                int numero_semaine_choie = Integer.parseInt(numero_semaine);
                buildPaneltableau(numero_semaine_choie);
                panelGlobal.add(paneltableau);
                SwingUtilities.updateComponentTreeUI(this);
            } 
            else if (type_affichage.equals("En liste"))
            {
                // code pour afficher en liste
                System.out.println("ici");
                panelGlobal.remove(scrollsemaine);
                panelliste.removeAll();
                //panelGlobal.remove(paneltableau);

                int numero_semaine_choie = Integer.parseInt(numero_semaine);
                buildpanelliste(numero_semaine_choie);

                scrollsemaine = new JScrollPane(panelliste);
                panelGlobal.add (scrollsemaine);
                SwingUtilities.updateComponentTreeUI(this);
            }
            
        }
    }
    
     @Override
    public void actionPerformed(ActionEvent e) 
    {
        System.out.println("IAM: "+liste_des_items_modifier.size());
        for (int i = 0; i < liste_des_items_modifier.size(); i++) 
        {
            if (e.getSource() == liste_des_items_modifier.get(i))
            {
                JOptionPane.showMessageDialog(this, "Cliqué");
                JOptionPane.showMessageDialog(this, "Seance: "+seances.get(i).getId());
                Seance s = seances.get(i);
                edtc.ouvrir_dialog_modifier_seance(s);
            }
            
            else if (e.getSource() == liste_des_items_supprimer.get(i))
            {
                JOptionPane.showMessageDialog(this, "Cliqué supp");
                Seance s = seances.get(i);
                edtc.supprimer_seance(s);
            }
        }
    }
    
   

    
}
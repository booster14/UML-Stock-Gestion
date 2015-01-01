/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockgestion.UI;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import stockgestion.Controlleur.CommandeControlleur;
import stockgestion.Entite.*;

/**
 *
 * @author rubeus
 */
public class CommanderProduit extends javax.swing.JFrame {
    private static CommanderProduit instance = null;
    private List<Article> listArticles;

    private CommanderProduit() {
        initComponents();
        addActionListeners();
        setTitle("Commander des produits");
    }
    
    public static CommanderProduit getInstance(){
        if(instance == null){
            instance = new CommanderProduit();
        }
        return instance;
    }
    
    private void addActionListeners(){
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur.getInstance().retournerEcranAccueil(CommanderProduit.this);
            }
        });
        
        /**
         *    Menu click droit
         */
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JPopupMenu popupMenu = new JPopupMenu();        
        final JTextField quantite = new JTextField();
        JLabel texte = new JLabel("Quantité :");
        
        final JMenuItem commander = new JMenuItem("Commander");  
        JMenuItem annuler = new JMenuItem("Annuler");
        
        commander.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int[] indexes = table.getSelectedRows();
                for(int i=indexes.length-1; i>=0; i--){
                    System.out.println(quantite.getText());
                    Commande commande = new Commande(listArticles.get(indexes[i]), Integer.parseInt(quantite.getText()));
                    CommandeControlleur.getInstance().ajouter(commande);
                }            
                refreshTable(listArticles);
                quantite.setText("");
            }
        });  
        
        annuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                quantite.setText("");
            }
        });  
        
        popupMenu.add(texte);
        popupMenu.add(quantite);
        popupMenu.addSeparator();
        popupMenu.add(commander);
        popupMenu.add(annuler);        
        table.setComponentPopupMenu(popupMenu);
        
        /**
         *   Selection d'un article dans la liste
         */
        produitArrive.setVisible(false);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = table.getSelectedRow();     
                if(index != -1){
                    if(CommandeControlleur.getInstance().articleEnCommande(listArticles.get(index))){
                    produitArrive.setVisible(true);
                    } else{
                        produitArrive.setVisible(false);
                    }
                }                
            }
        });
        
        /**
         *  Declarer que le produit est arrive en stock
         */
        produitArrive.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CommandeControlleur.getInstance().updateStockArticleCommande(listArticles.get(table.getSelectedRow()));
                stockgestion.StockGestion.getInstance().refreshUI();
            }
        });
    }
    
    public void refreshTable(List<Article> listArticles){
        this.listArticles = listArticles;
        
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        
        CommandeControlleur controlleur = CommandeControlleur.getInstance();
        for(Article article: listArticles){
            if(controlleur.articleEnCommande(article)){
                Commande commande = controlleur.getCommandeArticle(article);
                tableModel.addRow(new Object[] {article.getNom(), article.getQuantite(), article.listFournisseurToString(), article.getPrix(), commande.getDate(), commande.getMontant()});
            } else{
                tableModel.addRow(new Object[] {article.getNom(), article.getQuantite(), article.listFournisseurToString(), article.getPrix()});
            }
        }
        table.setModel(tableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        back = new javax.swing.JButton();
        produitArrive = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        menuItemImprimer = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Quantité", "Fournisseur", "Prix unitaire", "Date commandé", "Montant commandé"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);

        back.setText("Retouner à l'écran d'accueil");

        produitArrive.setText("Produit arrivé");

        menu.setText("Options");

        menuItemImprimer.setText("Imprimer la page");
        menu.add(menuItemImprimer);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(produitArrive, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produitArrive, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemImprimer;
    private javax.swing.JButton produitArrive;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

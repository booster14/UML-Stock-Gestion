/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockgestion.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;
import stockgestion.Controlleur.ArticleControlleur;
import stockgestion.Entite.Article;
import javax.swing.table.DefaultTableModel;
import stockgestion.Controlleur.CaisseControlleur;
import stockgestion.Controlleur.ClientControlleur;
import stockgestion.Entite.Client;
import stockgestion.Outil.Verificateur;

/**
 *
 * @author rubeus
 */
public class Caisse extends javax.swing.JFrame {
    
private static Caisse instance = null;
private static stockgestion.Entite.Caisse caisse;
private Client client;
private boolean tiroirOuvert;
private boolean retourBool;
private boolean totalBool;
private Article article;

    private Caisse() {
        initComponents();
        addActionListeners();
        setTitle("Caisse");

        client = new Client();
        totalBool = false;
        retourBool = false;
        tiroirOuvert = false;
        tiroirButton.setText("Fermé");
    }
    
    public static Caisse getInstance(stockgestion.Entite.Caisse pcaisse){
        if(instance == null){
            instance = new Caisse();
            caisse = pcaisse;
        }
        return instance;
    }
    
    private void addActionListeners(){
        
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Caisse.this.setVisible(false);
                SessionCaisse.getInstance().setVisible(true);
                totalBool = false;
                retourBool = false;
                CaisseControlleur.getInstance().fermer(caisse);
                clearUI();
                ClientControlleur.getInstance().resetArticles(client);
            }
        });
        
        randomizer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                article = ArticleControlleur.getInstance().getRandomArticle();
                codeBarre.setText(String.valueOf(article.getCodeBarre()));
                
                passageArticle();
            }
        });
        
        enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                article = null;
                if(!(codeBarre.getText().isEmpty()) && Verificateur.isValidInt(codeBarre.getText())){
                    
                    for(Article a : ArticleControlleur.getInstance().getAllArticles()){
                        if(a.getCodeBarre()== Integer.parseInt(codeBarre.getText())){
                            article = ArticleControlleur.getInstance().getArticleByCodebarre(Integer.parseInt(codeBarre.getText()));
                            break;
                        }
                    }
                }
                
                passageArticle();
                
            }
        });
        
        totalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirTiroir();
            }
            
            
        });
        
        totalButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    totalBool = true;
                    totalButton.setForeground(Color.red);
                    ouvrirTiroir();
                    total.setText(String.valueOf(CaisseControlleur.getInstance().calculerSomme(caisse)));
                    tableTotalCaisse();
                }
            }
        });
        
        tiroirButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(tiroirOuvert) // fermeture du tiroir
                {
                    fermerTiroir();
                    
                    if(retourBool){
                        codeBarre.setText(null);
                        codeBarreEtat.setText(null);
                        Client client2 = new Client();
                        ClientControlleur.getInstance().retournerArticle(client2, article);
                        CaisseControlleur.getInstance().ajouterClient(caisse, client2);
                        retourBool = false;
                        retourArticle.setForeground(Color.black);
                        
                    }else if(totalBool){
                        totalBool = false;
                        totalButton.setForeground(Color.black);
                        clearUI();
                        caisse = new stockgestion.Entite.Caisse();
                        client = new Client();
                    }else{
                        clearUI();
                        ClientControlleur.getInstance().terminerVente(client);
                        
                        CaisseControlleur.getInstance().ajouterClient(caisse, client);
                        client = new Client();
                    }
                }
            }
        });
        
        retourArticle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                retourBool = true;
                retourArticle.setForeground(Color.red);
            }
        });
    }
    
    private void passageArticle(){
        if(article == null){
            codeBarreEtat.setForeground(Color.red);
            codeBarreEtat.setText("Code barre non reconnu");

        }else{
            codeBarreEtat.setForeground(Color.BLACK);
            codeBarreEtat.setText(article.getNom() + "\t\t" + article.getPrix());
            if(retourBool){
                ouvrirTiroir();
            }else{
                ClientControlleur.getInstance().ajouterArticle(client, article.getId());
                updateTable();
                total.setText(String.valueOf(ClientControlleur.getInstance().calculerSomme(client)));
            }
        } 
    }
    
    private void ouvrirTiroir(){
        tiroirOuvert = true;
        tiroirButton.setForeground(Color.red);
        tiroirButton.setText("Ouvert");
    }
    
    private void fermerTiroir(){
        tiroirOuvert = false;
        tiroirButton.setForeground(Color.black);
        tiroirButton.setText("Fermé");
    }
    
    private void clearUI(){
        codeBarre.setText(null);
        codeBarreEtat.setText(null);
        total.setText(null);
        resetTable();
    }
    
    private void updateTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Entry <Article, Integer> entry : client.getListArticles().entrySet()){
            if(entry.getValue() > 0){
                model.addRow(new Object[]{entry.getKey().getNom(), entry.getValue(), entry.getKey().getPrix()});
            }
        }
        table.setModel(model);
    }
    
    private void tableTotalCaisse(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Client c : caisse.getListClients()){
            for(Entry <Article, Integer> entry : c.getListArticles().entrySet()){
                model.addRow(new Object[]{entry.getKey().getNom(), entry.getValue(), entry.getKey().getPrix()});
            }
        }
        
        table.setModel(model);
    }
    
    private void resetTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        table.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();
        codeBarreLabel = new javax.swing.JLabel();
        retourArticle = new javax.swing.JButton();
        enter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        totalButton = new javax.swing.JButton();
        codeBarreEtat = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tiroirButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        codeBarre = new javax.swing.JTextField();
        randomizer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        back.setText("Fermer la caisse");

        codeBarreLabel.setText("Référence Article");

        retourArticle.setText("Retour");

        enter.setText("Entrée");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Article", "Quantité", "Prix Unitaire"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
        }

        totalButton.setText("Total");

        codeBarreEtat.setEditable(false);

        total.setEditable(false);
        total.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        total.setFocusable(false);

        jLabel1.setText("Total");

        jLabel2.setText("Tiroir");

        randomizer.setText("Passer un article");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tiroirButton, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(retourArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(codeBarreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(codeBarre, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(randomizer, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(codeBarreEtat, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codeBarreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(retourArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codeBarre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(codeBarreEtat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(randomizer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tiroirButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalButton, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caisse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextField codeBarre;
    private javax.swing.JTextField codeBarreEtat;
    private javax.swing.JLabel codeBarreLabel;
    private javax.swing.JButton enter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton randomizer;
    private javax.swing.JButton retourArticle;
    private javax.swing.JTable table;
    private javax.swing.JButton tiroirButton;
    private javax.swing.JTextField total;
    private javax.swing.JButton totalButton;
    // End of variables declaration//GEN-END:variables
}

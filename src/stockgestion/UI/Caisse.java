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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import stockgestion.Controlleur.ArticleControlleur;
import stockgestion.Entite.Article;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rubeus
 */
public class Caisse extends javax.swing.JFrame {
    
private static Caisse instance = null;
private HashMap<Article, Integer> listArticles;
private HashMap<Article, Integer> listTotalArticles;
private boolean tiroirOuvert;
private boolean retourBool;
private boolean totalBool;
private Article article;

    private Caisse() {
        initComponents();
        addActionListeners();
        setTitle("Caisse");

        totalBool = false;
        retourBool = false;
        tiroirOuvert = false;
        tiroirButton.setText("Fermé");
        listArticles = new HashMap<Article, Integer>();
        listTotalArticles = new HashMap<Article, Integer>();
    }
    
    public static Caisse getInstance(){
        if(instance == null){
            instance = new Caisse();
        }
        return instance;
    }
    
    private void addActionListeners(){
        
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Caisse.this.setVisible(false);
                InterfaceUtilisateur.getInstance().setVisible(true);
            }
        });
        
        enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                article = null;
                if(!(codeBarre.getText().isEmpty())){
                    
                    for(Article a : ArticleControlleur.getInstance().getAllArticles()){
                        if(a.getCodeBarre()== Integer.parseInt(codeBarre.getText())){
                            article = ArticleControlleur.getInstance().getArticleByCodebarre(Integer.parseInt(codeBarre.getText()));
                            break;
                        }
                    }
                }
                
                
                if(article == null){
                    codeBarreEtat.setForeground(Color.red);
                    codeBarreEtat.setText("Code barre non reconnu");
                    
                }else{
                    codeBarreEtat.setForeground(Color.BLACK);
                    codeBarreEtat.setText(article.getNom() + "\t\t" + article.getPrix());
                    if(retourBool){
                        ouvrirTiroir();
                    }else{
                        updateListArticles(article);
                        updateTable();
                        updateTotal();
                    }
                } 
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
                    ouvrirTiroir();
                    System.out.println("coucou");
                }
    }
});
        
        tiroirButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(tiroirOuvert) // fermeture du tiroir
                {
                    fermerTiroir();
                    codeBarre.setText(null);
                    codeBarreEtat.setText(null);
                    if(retourBool){
                        returnArticle(article);
                        retourBool = false;
                        retourArticle.setForeground(Color.black);
                        
                    }else if(totalBool){
                        System.out.println("batard");
                        totalBool = false;
                        end();
                    }else{
                        total.setText(null);
                        resetTable();
                        updateArticles();
                        updateListTotalArticles();
                        resetListArticles();
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
    
    private void ouvrirTiroir(){
        tiroirOuvert = true;
        tiroirButton.setText("Ouvert");
    }
    
    private void fermerTiroir(){
        tiroirOuvert = false;
        tiroirButton.setText("Fermé");
    }
    
    private void updateListArticles(Article article){
        for(Entry <Article, Integer> entry : listArticles.entrySet()){
            if(entry.getKey().getId() == article.getId()){
                entry.setValue(entry.getValue() + 1);
                return;
            }
        }
        
        listArticles.put(article, 1);
    }
    
    private void resetListArticles(){
        listArticles.clear();
    }
    
    private void updateTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Entry <Article, Integer> entry : listArticles.entrySet()){
            model.addRow(new Object[]{entry.getKey().getNom(), entry.getValue(), entry.getKey().getPrix()});
        }
        table.setModel(model);
    }
    
    private void resetTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        table.setModel(model);
    }
    
    private void updateArticles(){
        for(Entry <Article, Integer> entry : listArticles.entrySet()){
            entry.getKey().setQuantite(entry.getKey().getQuantite() - entry.getValue());
            ArticleControlleur.getInstance().editer(entry.getKey());
        }
    }
    
    private void returnArticle(Article article){
        int quantite = article.getQuantite()+1;
        article.setQuantite(quantite);
        ArticleControlleur.getInstance().editer(article);
        
        // et on ajoute la modif dans la listTotalArticles
        listTotalArticles.put(article, -1);
    }
    
    private void updateTotal(){
        double somme = 0;
        for(Entry <Article, Integer> entry : listArticles.entrySet()){
            somme += entry.getKey().getPrix() * entry.getValue();
        }
        total.setText(String.valueOf(somme));
    }
    
    private void updateListTotalArticles(){
        for(Entry <Article, Integer> entry : listArticles.entrySet()){
            listTotalArticles.put(entry.getKey(), entry.getValue());
        }
    }
    
    private void end(){
        //affichage de l'ensemble de transactions effectuées
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Entry <Article, Integer> entry : listTotalArticles.entrySet()){
            model.addRow(new Object[]{entry.getKey().getNom(), entry.getValue(), entry.getKey().getPrix()});
        }
        table.setModel(model);
        
        //affichage du total
        double somme = 0;
        for(Entry <Article, Integer> entry : listTotalArticles.entrySet()){
            somme += entry.getKey().getPrix() * entry.getValue();
        }
        total.setText(String.valueOf(somme));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        back.setText("Retourner à l'écran d'accueil");

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
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(retourArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(codeBarreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codeBarre, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeBarreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(retourArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeBarre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(codeBarreEtat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JButton retourArticle;
    private javax.swing.JTable table;
    private javax.swing.JButton tiroirButton;
    private javax.swing.JTextField total;
    private javax.swing.JButton totalButton;
    // End of variables declaration//GEN-END:variables
}

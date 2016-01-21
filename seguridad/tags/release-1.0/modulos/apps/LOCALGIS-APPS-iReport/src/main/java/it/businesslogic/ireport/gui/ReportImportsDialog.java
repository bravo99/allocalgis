/*
 * Copyright (C) 2005 - 2007 JasperSoft Corporation.  All rights reserved. 
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * ReportImportsDialog.java
 * 
 * Created on 29 settembre 2004, 0.56
 *
 */

package it.businesslogic.ireport.gui;
import it.businesslogic.ireport.gui.*;
import it.businesslogic.ireport.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.*;
import it.businesslogic.ireport.chart.*;
import java.util.*;
import it.businesslogic.ireport.util.I18n;
/**
 *
 * @author  Administrator
 */
public class ReportImportsDialog extends javax.swing.JDialog {
   
    private int dialogResult = javax.swing.JOptionPane.CANCEL_OPTION;
    private JReportFrame jReportFrame = null;
    /** Creates new form IReportChartDialog */
    public ReportImportsDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initFrame();  
    }
    
    public void initFrame()
    {
        javax.swing.DefaultListModel dlm =  new javax.swing.DefaultListModel() ;
        jList1.setModel(dlm );
        jList1.updateUI();       
        
        this.setSize(500,400);
        it.businesslogic.ireport.util.Misc.centerFrame(this);
        
        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jButtonCloseActionPerformed(e);
            }
        };
       
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);

        applyI18n();
        //to make the default button ...
        this.getRootPane().setDefaultButton(this.jButtonClose);
        
    }
    
    public ReportImportsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initFrame();
        
    }
    
    public void updateImportsList()
    {
        
        Vector values = new Vector();
        
        javax.swing.DefaultListModel dlm =  (javax.swing.DefaultListModel)jList1.getModel();
        dlm.removeAllElements();

        if (getJReportFrame() == null) {  jList1.updateUI(); return; }

        Enumeration var_enum = getJReportFrame().getReport().getImports().elements();
        while (var_enum.hasMoreElements())
        {
            String var = (String)var_enum.nextElement();
            dlm.addElement( var );
        }
        jList1.updateUI();
    }

    public JReportFrame getJReportFrame() {
        
        return jReportFrame;
    }

    public void setJReportFrame(JReportFrame jReportFrame) {

        this.jReportFrame = jReportFrame;
        updateImportsList();
    }

    public int getDialogResult() {
        return dialogResult;
    }

    public void setDialogResult(int dialogResult) {
        this.dialogResult = dialogResult;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelData = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jButtonNewImport = new javax.swing.JButton();
        jButtonModifyImport = new javax.swing.JButton();
        jButtonDeleteImport = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Report Imports");
        jPanelData.setLayout(new java.awt.GridBagLayout());

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });

        jScrollPane3.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelData.add(jScrollPane3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(100, 151));
        jPanel2.setPreferredSize(new java.awt.Dimension(140, 100));
        jButtonNewImport.setText("New import");
        jButtonNewImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewImportActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonNewImport, gridBagConstraints);

        jButtonModifyImport.setText("Modify import");
        jButtonModifyImport.setEnabled(false);
        jButtonModifyImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyImportActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(jButtonModifyImport, gridBagConstraints);

        jButtonDeleteImport.setText("Remove import");
        jButtonDeleteImport.setEnabled(false);
        jButtonDeleteImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteImportActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(jButtonDeleteImport, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonClose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 4);
        jPanelData.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanelData, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeleteImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteImportActionPerformed
        
        Object[] vars = jList1.getSelectedValues();
        
        for (int i=0; i<vars.length; ++i)
        {
            String var = (String)vars[i];
            this.getJReportFrame().getReport().getImports().remove(var);
        }
        
        this.updateImportsList();
        
    }//GEN-LAST:event_jButtonDeleteImportActionPerformed

    private void jButtonModifyImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyImportActionPerformed
       
        ImportDialog idialog = new ImportDialog(this, true);
        String var = (String)jList1.getSelectedValue() ;
        idialog.setImport( var );
        idialog.setVisible(true);
        if (idialog.getDialogResult() == javax.swing.JOptionPane.OK_OPTION)
        {
            this.getJReportFrame().getReport().getImports().remove(var);
            String var_new = idialog.getImport();
            if (!this.getJReportFrame().getReport().getImports().contains(var_new ))
            {
                this.getJReportFrame().getReport().getImports().add(var_new);
            }
            this.updateImportsList();
         }
        
        
    }//GEN-LAST:event_jButtonModifyImportActionPerformed

    private void jButtonNewImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewImportActionPerformed
        ImportDialog idialog = new ImportDialog(this, true);
        idialog.setVisible(true);
        if (idialog.getDialogResult() == javax.swing.JOptionPane.OK_OPTION)
        {
            String var_new = idialog.getImport();
            if (!this.getJReportFrame().getReport().getImports().contains(var_new ))
            {
                this.getJReportFrame().getReport().getImports().add(var_new);
            }
            this.updateImportsList();
         }
        
    }//GEN-LAST:event_jButtonNewImportActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        
         if (this.jList1.getSelectedIndex() >= 0) {
            this.jButtonModifyImport.setEnabled(true);
            this.jButtonDeleteImport.setEnabled(true);
        }
        else {
            this.jButtonModifyImport.setEnabled(false);
            this.jButtonDeleteImport.setEnabled(false);
        }
        
        
    }//GEN-LAST:event_jList1ValueChanged

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
       
        this.setDialogResult( javax.swing.JOptionPane.OK_OPTION);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCloseActionPerformed
    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDeleteImport;
    private javax.swing.JButton jButtonModifyImport;
    private javax.swing.JButton jButtonNewImport;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    public void applyI18n(){
                // Start autogenerated code ----------------------
                jButtonClose.setText(I18n.getString("reportImportsDialog.buttonClose","Close"));
                jButtonDeleteImport.setText(I18n.getString("reportImportsDialog.buttonDeleteImport","Remove import"));
                jButtonModifyImport.setText(I18n.getString("reportImportsDialog.buttonModifyImport","Modify import"));
                jButtonNewImport.setText(I18n.getString("reportImportsDialog.buttonNewImport","New import"));
                // End autogenerated code ----------------------
                this.setTitle(I18n.getString("reportImportsDialog.title","Report Imports"));
    }
}
/**
 * DefineFileExportUserStylePanel.java
 * © MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * DefineFileExportUserStylePanel.java
 *
 * Created on 28 de julio de 2004, 17:58
 */
package com.geopista.style.sld.ui.impl.panels;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.geopista.app.AppContext;
import com.geopista.style.sld.model.SLDStyle;
import com.vividsolutions.jump.workbench.model.Layer;
import com.vividsolutions.jump.workbench.ui.GUIUtil;

import es.enxenio.util.controller.Action;
import es.enxenio.util.controller.ActionForward;
import es.enxenio.util.controller.FrontController;
import es.enxenio.util.controller.FrontControllerFactory;
import es.enxenio.util.controller.Request;
import es.enxenio.util.ui.impl.AbstractPanel;

/**
 *
 * @author  Enxenio S.L.
 */
public class DefineFileExportUserStylePanel extends AbstractPanel implements ActionForward {

	SLDStyle sldStyle = null;
	Object[] selectedStyles = null;
	String importar = "";
	Layer layer = null;
	String layerName = null;
	
    private static AppContext aplicacion = (AppContext) AppContext.getApplicationContext();
	public void configure(Request request) {
		selectedStyles = (Object[])request.getAttribute("selectedStyles");
		sldStyle = (SLDStyle)request.getAttribute("SLDStyle");
		//Para saber si estamos eligiendo fichero para una importación o exportación
		importar = (String) request.getAttribute("importar");
		layer = (Layer)request.getAttribute("Layer");
		layerName = (String)request.getAttribute("LayerName");
        String etiqueta = aplicacion.getI18nString("SLDStyle.SeleccionFichero");
		if (importar.equals("true"))
			etiqueta += aplicacion.getI18nString("SLDStyle.ImportarFicheroEstilo");
		else
			etiqueta += aplicacion.getI18nString("SLDStyle.ExportarFicheroEstilo");
	    fileLbl.setText(etiqueta);
	}

	public boolean windowClosing() {
		Request theRequest = FrontControllerFactory.createRequest();
		FrontController fc =  FrontControllerFactory.getFrontController();
		Action theAction = fc.getAction("GetBack"); 
		ActionForward theActionForward = theAction.doExecute(theRequest);
		_container.forward(theActionForward, theRequest);
		return false;
	}

	public String getTitle() {
		if (importar.equals("true"))
			return aplicacion.getI18nString("SLDStyle.ImportacionEstiloFichero");
		else
			return aplicacion.getI18nString("SLDStyle.ExportacionEstiloFichero");
	}
    
    /** Creates new form InsertUpdateScaleRangePanel */
    public DefineFileExportUserStylePanel() {
        initComponents();
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        filePanel = new javax.swing.JPanel();
        fileLbl = new javax.swing.JLabel();
        fileTxt = new javax.swing.JTextField();
        fileBtn = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        setPreferredSize(new java.awt.Dimension(400, 125));
        filePanel.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        filePanel.add(fileLbl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        filePanel.add(fileTxt, gridBagConstraints);

        fileBtn.setText(aplicacion.getI18nString("SLDStyle.Examinar")+"...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        fileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	fileBtnActionPerformed(evt);
            }
        });
        filePanel.add(fileBtn, gridBagConstraints);

        add(filePanel, java.awt.BorderLayout.CENTER);

        okBtn.setText(aplicacion.getI18nString("SLDStyle.Aceptar"));
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        buttonPanel.add(okBtn);

        cancelBtn.setText(aplicacion.getI18nString("SLDStyle.Cancelar"));
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        buttonPanel.add(cancelBtn);

        add(buttonPanel, java.awt.BorderLayout.SOUTH);

    }//GEN-END:initComponents

    private void fileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileBtnActionPerformed
    	if (importar.equals("true")){
    		fileChooser = GUIUtil.createJFileChooserWithExistenceChecking();
	        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(aplicacion.getMainFrame())){
	            fileTxt.setText(fileChooser.getSelectedFile().getAbsolutePath());
	        }else
				JOptionPane.showMessageDialog(null, aplicacion.getI18nString("SLDStyle.SeleccionFichero"));
    	}else{
    		fileChooser = GUIUtil.createJFileXMLChooserWithOverwritePrompting();
			FileFilter fileFilter = new MyFileFilter(".xml", "XML");	
			fileChooser.setFileFilter(fileFilter);
			fileChooser.setAcceptAllFileFilterUsed(false);
	        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(aplicacion.getMainFrame())){
	        	if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".xml"))
	        		fileTxt.setText(fileChooser.getSelectedFile().getAbsolutePath());
	        	else
	    			JOptionPane.showMessageDialog(null, AppContext.getMessage("SLDStyle.SeleccionFicheroXML"));
	        }else
				JOptionPane.showMessageDialog(null, aplicacion.getI18nString("SLDStyle.SeleccionFichero"));
	    }
    }//GEN-LAST:event_fileBtnActionPerformed

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        if (importar.equals("true")&&!new File(fileTxt.getText()).exists())
			JOptionPane.showMessageDialog(null, aplicacion.getI18nString("SLDStyle.FicheroNoExiste"));   
        else{
			Request theRequest = FrontControllerFactory.createRequest();
			theRequest.setAttribute("FileExport", fileTxt.getText());
			theRequest.setAttribute("selectedStyles", selectedStyles);
			theRequest.setAttribute("SLDStyle", sldStyle);
			theRequest.setAttribute("Layer", layer);
			theRequest.setAttribute("LayerName", layerName);
			FrontController fc =  FrontControllerFactory.getFrontController();
			Action theAction;
			if (importar.equals("true"))
				theAction = fc.getAction("ImportCustomUserStyle"); 
			else
				theAction = fc.getAction("ExportCustomUserStyle"); 
			ActionForward theActionForward = theAction.doExecute(theRequest);
			_container.forward(theActionForward, theRequest);
		}
    }//GEN-LAST:event_okBtnActionPerformed


    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
		Request theRequest = FrontControllerFactory.createRequest();
		FrontController fc =  FrontControllerFactory.getFrontController();
		Action theAction = fc.getAction("GetBack"); 
		ActionForward theActionForward = theAction.doExecute(theRequest);
		_container.forward(theActionForward, theRequest);
    }//GEN-LAST:event_cancelBtnActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton okBtn;
    private javax.swing.JLabel fileLbl;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTextField fileTxt;
    private javax.swing.JButton fileBtn;
    private javax.swing.JPanel filePanel;
    // End of variables declaration//GEN-END:variables

    
    private static class MyFileFilter extends FileFilter {
        private FileFilter fileFilter;
        private String format;
        public MyFileFilter(String description, String format) {
            fileFilter = GUIUtil.createFileFilter(description,
                    new String[]{format});
            this.format = format;
        }
        public boolean accept(File f) {
            return fileFilter.accept(f);
        }

        public String getDescription() {
            return fileFilter.getDescription();
        }

        public String getFormat() {
            return format;
        }
    }
    
}

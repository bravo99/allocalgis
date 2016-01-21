/**
 * HistoricoPropiedadJDialog.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.app.cementerios.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import com.geopista.app.AppContext;
import com.geopista.app.cementerios.Constantes;
import com.geopista.app.cementerios.ElemJTableModel;
import com.geopista.app.utilidades.TableSorted;
import com.geopista.protocol.cementerios.HistoricoPropiedadBean;
import com.geopista.protocol.cementerios.PersonaBean;
import com.geopista.util.ApplicationContext;

/**
 *
 * @author yraton
 */
public class HistoricoPropiedadJDialog extends javax.swing.JDialog {

	private org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(HistoricoPropiedadJDialog.class);

	private static final long serialVersionUID = 1L;

	
	private String operacion;
	private String tipo;
    private ApplicationContext aplicacion;
    private javax.swing.JFrame desktop;
    private PersonaBean titularSelected;

	private BotoneraAceptarCancelarJPanel botoneraAceptarCancelarJPanel;
	

	public static final String DOBLE_CLICK="DOBLE_CLICK";

	/**Difuntos**/
	private ArrayList listaHistorico;
    private ArrayList<HistoricoPropiedadBean> listaHistoricoTabla;
    
    private ElemJTableModel historicoPropiedadJTableModel;
    private TableSorted tableHistoricoPropiedadSorted;
    private int selectedHistoricoPropiedadRow= -1;
	private HistoricoPropiedadBean historicoSelected;

	private Vector vgrupos = new Vector();
	
	private int patronContenedor = 1;
	
	private ArrayList actionListeners= new ArrayList();
    private String locale;
    

    private SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

    
    /** Creates new form InhumacionJDialog */
    public HistoricoPropiedadJDialog (JFrame desktop, String locale, String operacion,String tipo, PersonaBean titularSelected, ArrayList listaHistorico) throws Exception{
    	super(desktop);
        this.desktop= desktop;
        this.locale= locale;
        this.operacion= operacion;
        this.tipo=tipo;
        this.listaHistorico = listaHistorico;
        this.titularSelected = titularSelected;
        inicializar();
    }

    
    /** Creates new form BloqueJDialog */
    public HistoricoPropiedadJDialog(JFrame desktop, String locale) throws Exception{
        super(desktop);
        this.desktop= desktop;
        this.locale= locale;
        inicializar();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
 
    private void inicializar() {

    	this.aplicacion = (AppContext) AppContext.getApplicationContext();
        getContentPane().setLayout(new BorderLayout());
        renombrarComponentes();
        setModal(true);

        desktop = new javax.swing.JFrame();

        //Panel de datos generales
        historicosJPanel = new javax.swing.JPanel();
        TitularJPanel =  new javax.swing.JPanel();
        DatosGeneralesComunesJPanel = new javax.swing.JPanel();
        entidadJLabel = new javax.swing.JLabel();
        cementerioJLabel = new javax.swing.JLabel();
        entidadJTextField = new javax.swing.JTextField();
        cementerioJTextField = new javax.swing.JTextField();

    	//Datos Titular
    	datosTitularJPanel = new javax.swing.JPanel();
        DNIJLabel = new javax.swing.JLabel();
        DNIJTextField = new javax.swing.JTextField();
        nombreJLabel = new javax.swing.JLabel();
        nombreJTextField = new javax.swing.JTextField();
        sexolJLabel = new javax.swing.JLabel();
        apellidosJLabel = new javax.swing.JLabel();
        apellido1JTextField = new javax.swing.JTextField();
        domicilioPostalJLabel = new javax.swing.JLabel();
        domicilioPostalJTextField = new javax.swing.JTextField();
        apellido2JLabel = new javax.swing.JLabel();
        apellido2JTextField = new javax.swing.JTextField();
        mujerJCheckBox = new javax.swing.JCheckBox();
        hombreJCheckBox = new javax.swing.JCheckBox();
        estadoCivilJLabel = new javax.swing.JLabel();
        solteroJCheckBox = new javax.swing.JCheckBox();
        casadoJCheckBox = new javax.swing.JCheckBox();
        
        
        
        //botonera, tama�o por defecto y 
        
        botoneraAceptarCancelarJPanel= new BotoneraAceptarCancelarJPanel();
        botoneraAceptarCancelarJPanel.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
  				botoneraAceptarCancelarJPanel_actionPerformed();
  			}
  		});

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        setSize(820, 820);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        
        
        fechaNacimientoJLabel = new javax.swing.JLabel();
        try {
			fechaNacimientoJDateChooser = new JDateChooser(fecha.parse("01/01/2000"));
		} catch (ParseException e) {
			logger.error("Error estableciendo fechaNacimiento por defecto " + e);
			e.printStackTrace();
		}
		
        fechaNacimientoJDateChooser.setDateFormatString("dd/MM/yyyy");
        
        telefonoJLabel = new javax.swing.JLabel();
        telefonoJTextField = new javax.swing.JTextField();
        telefonoJTextField.setText("");
        
        poblacionJLabel = new javax.swing.JLabel();
        poblacionJTextField = new javax.swing.JTextField();
        poblacionJTextField.setText("");
        
        historicosJPanel = new javax.swing.JPanel();
        historicoJScrollPane = new javax.swing.JScrollPane();
        historicosTableJTable = new javax.swing.JTable();
        
        TitularJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        DatosGeneralesComunesJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        DatosGeneralesComunesJPanel.setPreferredSize(new Dimension(760, 70));
        DatosGeneralesComunesJPanel.setMinimumSize(new Dimension(760, 70));

        
        entidadJLabel.setText(aplicacion.getI18nString("cementerio.datosGenerales.tag2"));
        DatosGeneralesComunesJPanel.add(entidadJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        cementerioJLabel.setText(aplicacion.getI18nString("cementerio.datosGenerales.tag3"));
        DatosGeneralesComunesJPanel.add(cementerioJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));
        
        //marco el borde
        DatosGeneralesComunesJPanel.setBorder(new javax.swing.border.TitledBorder(aplicacion.getI18nString("cementerio.datosGenerales.tag1")));


        entidadJTextField.setText("");
        DatosGeneralesComunesJPanel.add(entidadJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 780, -1));
        cementerioJTextField.setText("");
        DatosGeneralesComunesJPanel.add(cementerioJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 780, -1));
        TitularJPanel.add(DatosGeneralesComunesJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 880, 70));

        datosTitularJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(aplicacion.getI18nString("cementerio.datosTitular.tag1")));
        datosTitularJPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        mujerJCheckBox.setText("Mujer");
        mujerJCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mujerJCheckBoxActionPerformed(evt);
            }
        });

        hombreJCheckBox.setText("Hombre");
        hombreJCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hombreJCheckBoxActionPerformed(evt);
            }
        });
        estadoCivilJLabel.setText("EstadoCivil:");

        solteroJCheckBox.setText("Soltero");
        solteroJCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solteroJCheckBoxActionPerformed(evt);
            }
        });

        casadoJCheckBox.setText("Casado");
        casadoJCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casadoJCheckBoxActionPerformed(evt);
            }
        });

        
        datosTitularJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DNIJLabel.setText("DNI/NIF");
        datosTitularJPanel.add(DNIJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 24, -1, -1));
        datosTitularJPanel.add(DNIJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 21, 100, -1));

        nombreJLabel.setText("Nombre");
        datosTitularJPanel.add(nombreJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 24, -1, -1));
        nombreJTextField.setText("");
        datosTitularJPanel.add(nombreJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 21, 138, -1));

        sexolJLabel.setText("Sexo: ");
        datosTitularJPanel.add(sexolJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, -1, -1));

        apellidosJLabel.setText("Apellido1");
        apellido1JTextField.setText("");
        
        datosTitularJPanel.add(apellidosJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 24, -1, -1));
        datosTitularJPanel.add(apellido1JTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 21, 158, -1));
        
        domicilioPostalJLabel.setText("Domicilio ");
        datosTitularJPanel.add(domicilioPostalJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 81, -1, -1));
        domicilioPostalJTextField.setText(" ");
        datosTitularJPanel.add(domicilioPostalJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 78, 424, -1));
        
        apellido2JLabel.setText("Apellido2");
        apellido2JTextField.setText("");
        datosTitularJPanel.add(apellido2JLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(671, 24, -1, -1));
        datosTitularJPanel.add(apellido2JTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 21, 127, -1));

        mujerJCheckBox.setText("Mujer");
        mujerJCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mujerJCheckBoxActionPerformed(evt);
            }
        });
        datosTitularJPanel.add(mujerJCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 51, -1, -1));

        hombreJCheckBox.setText("Hombre");
        datosTitularJPanel.add(hombreJCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 51, -1, -1));

        estadoCivilJLabel.setText("EstadoCivil:");
        datosTitularJPanel.add(estadoCivilJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 55, -1, -1));

        solteroJCheckBox.setText("Soltero");
        solteroJCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solteroJCheckBoxActionPerformed(evt);
            }
        });
        datosTitularJPanel.add(solteroJCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 51, -1, -1));

        casadoJCheckBox.setText("Casado");
        datosTitularJPanel.add(casadoJCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 51, -1, -1));

        fechaNacimientoJLabel.setText("Fecha Nacimiento");
        datosTitularJPanel.add(fechaNacimientoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 55, -1, -1));
        datosTitularJPanel.add(fechaNacimientoJDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 52, 158, -1));

        telefonoJLabel.setText("Telefono");
        datosTitularJPanel.add(telefonoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(671, 55, -1, -1));
        datosTitularJPanel.add(telefonoJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 52, 127, -1));

        poblacionJLabel.setText("Poblacion");
        datosTitularJPanel.add(poblacionJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 81, -1, -1));
        datosTitularJPanel.add(poblacionJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 78, 298, -1));

        TitularJPanel.add(datosTitularJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 880, 120));
        
        //PANEL Historico
        listaHistoricoTabla = new ArrayList<HistoricoPropiedadBean>();
        
        historicosJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Hist�rico Operaciones"));
        historicosJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        historicosTableJTable = new ElemTableRender(6); 
        historicosTableJTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        initHeadersHistoricoPropiedadJTable();
        
        /* Ordenacion de la tabla */
        tableHistoricoPropiedadSorted= new TableSorted(historicoPropiedadJTableModel);
        historicoPropiedadJTableModel.setTableSorted(tableHistoricoPropiedadSorted);
        tableHistoricoPropiedadSorted.setTableHeader(historicosTableJTable.getTableHeader());
        historicosTableJTable.setModel(tableHistoricoPropiedadSorted);
        historicosTableJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historicosTableJTable.setCellSelectionEnabled(false);
        historicosTableJTable.setColumnSelectionAllowed(false);
        historicosTableJTable.setRowSelectionAllowed(true);
        historicosTableJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        historicosTableJTable.getTableHeader().setReorderingAllowed(false);
        
        setInvisible(historicoPropiedadJTableModel.getColumnCount()-1,historicosTableJTable);
        historicoPropiedadJTableModel.setTable(historicosTableJTable);
        
        if (listaHistorico != null){
        	historicoPropiedadJTableModel.setModelData(listaHistorico);      	
  	  	}
        
        historicosTableJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
            	elemHistoricosJTableMouseReleased();
            }
            public void mouseClicked(java.awt.event.MouseEvent evt){
            	if(evt.getClickCount() == 2) {
            		getHistoricoSeleccionado();
            		for (Iterator <ActionListener>i = actionListeners.iterator(); i.hasNext();) {
                        ActionListener l = (ActionListener) i.next();
                        l.actionPerformed(new ActionEvent(this, 0, DOBLE_CLICK));
                    }
                }
            }
        });

        historicoJScrollPane.setViewportView(historicosTableJTable);
        
        
        historicoJScrollPane.setViewportView(historicosTableJTable);
        historicosJPanel.add(historicoJScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 860, 120)); //(10, 10, 860, 100)
        
        TitularJPanel.add(historicosJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 880, 150)); //(10, 260, 880, 150));
        
        getContentPane().add(TitularJPanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(botoneraAceptarCancelarJPanel, java.awt.BorderLayout.SOUTH);

        pack();
        
    }


    private void mujerJCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
//        if (mujerJCheckBox.isSelected()){
//        	PersonaBean dif;
//        	if (titularSelected.getPersona() != null){
//        		dif = difuntoSelected.getPersona();
//        	}else{
//        		dif = new PersonaBean();
//        	}
//        	dif.setSexo(Constantes.SEXO_MUJER);
//        	difuntoSelected.setPersona(dif);
//        }
    }

    private void hombreJCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
//        if (hombreJCheckBox.isSelected()){
//        	PersonaBean dif;
//        	if (difuntoSelected.getPersona() != null){
//        		dif = difuntoSelected.getPersona();
//        	}else{
//        		dif = new PersonaBean();
//        	}
//        	dif.setSexo(Constantes.SEXO_HOMBRE);
//        	difuntoSelected.setPersona(dif);
//        }
    }

    private void solteroJCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
//        if (solteroJCheckBox.isSelected()){
//        	PersonaBean dif;
//        	if (difuntoSelected.getPersona() != null){
//        		dif = difuntoSelected.getPersona();
//        	}else{
//        		dif = new PersonaBean();
//        	}
//        	dif.setEstado_civil(Constantes.ESTADO_CIVIL_SOLTERO);
//        	difuntoSelected.setPersona(dif);
//        }
    }


    private void casadoJCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
//        if (casadoJCheckBox.isSelected()){
//        	PersonaBean dif;
//        	if (difuntoSelected.getPersona() != null){
//        		dif = difuntoSelected.getPersona();
//        	}else{
//        		dif = new PersonaBean();
//        	}
//        	dif.setEstado_civil(Constantes.ESTADO_CIVIL_CASADO);
//        	difuntoSelected.setPersona(dif);
//        }
    }
    

    public void load (PersonaBean titular, boolean editable){
    	
		cementerioJTextField.setText(titular.getNombreCementerio() != null ?  titular.getNombreCementerio() : "");
		cementerioJTextField.setEditable(false);
		entidadJTextField.setText(titular.getEntidad() != null ?  titular.getEntidad() : "");
		entidadJTextField.setEditable(false);
		
		DNIJTextField.setText(titular.getDNI() != null ? titular.getDNI() : "DNI");
		DNIJTextField.setEditable(editable);
		
		nombreJTextField.setText(titular.getNombre()!= null ? titular.getNombre() : "Nombre");
		nombreJTextField.setEditable(editable);
		
		apellido1JTextField.setText(titular.getApellido1()!= null ? titular.getApellido1() : " 1� Apellido");
		apellido1JTextField.setEditable(editable);
		
		apellido2JTextField.setText(titular.getApellido2()!= null ? titular.getApellido2() : " 2� Apellido");
		apellido2JTextField.setEditable(editable);
		
		if ((titular.getSexo()!= null) && (titular.getSexo().equalsIgnoreCase(Constantes.SEXO_HOMBRE))){
			hombreJCheckBox.setSelected(true);			
		}else{
			mujerJCheckBox.setSelected(true);
		}
		hombreJCheckBox.setEnabled(editable);
		mujerJCheckBox.setEnabled(editable);
		
		if ((titular.getEstado_civil()!= null) && (titular.getEstado_civil().equalsIgnoreCase(Constantes.ESTADO_CIVIL_SOLTERO))){
			solteroJCheckBox.setSelected(true);
		}else{
			casadoJCheckBox.setSelected(true);
		}
		
		solteroJCheckBox.setEnabled(editable);
		casadoJCheckBox.setEnabled(editable);
		
		fechaNacimientoJDateChooser.setDate(titular.getFecha_nacimiento() != null ? titular.getFecha_nacimiento() : new Date());
		fechaNacimientoJDateChooser.setEnabled(editable);
		
		domicilioPostalJTextField.setText(titular.getDomicilio()!= null ? titular.getDomicilio() : "");
		domicilioPostalJTextField.setEditable(editable);
		
		poblacionJTextField.setText(titular.getPoblacion()!= null ? titular.getPoblacion() : "");
		poblacionJTextField.setEditable(editable);
		
		telefonoJTextField.setText(titular.getTelefono()!= null ? titular.getTelefono() : "");
		telefonoJTextField.setEditable(editable);
		
	}
    

    public void renombrarComponentes(){
  }
    
    private void initHeadersHistoricoPropiedadJTable(){
	   	this.historicoPropiedadJTableModel= new ElemJTableModel(new String[]{"Unidad", "Concesi�n", "Fecha Operaci�n", "Fecha Fin Concesi�n", "Comentario", "HIDDEN"},
	   																	new boolean[]{false, false, false, false, false, false}, locale);		
	   }

    public Object getHistoricoSeleccionado(){
        
    	selectedHistoricoPropiedadRow= historicosTableJTable.getSelectedRow();
	    if (selectedHistoricoPropiedadRow == -1){ 
	        	return null;
        }else{
        	historicoPropiedadJTableModel.setTableSorted(historicoPropiedadJTableModel.getTableSorted());
        	historicoPropiedadJTableModel.setRows(historicoPropiedadJTableModel.getRows());
        	return historicoPropiedadJTableModel.getObjetAt(selectedHistoricoPropiedadRow);
        }
    }
 
    public void addActionListener(ActionListener l) {
        this.actionListeners.add(l);
    }

    public void removeActionListener(ActionListener l) {
        this.actionListeners.remove(l);
    }

    
    private void fireActionPerformed() {
        for (Iterator i = actionListeners.iterator(); i.hasNext();) {
            ActionListener l = (ActionListener) i.next();
            l.actionPerformed(new ActionEvent(this, 0, null));
        }
    }

    
    /* M�todo que abre una ventana de confirmacion sobre la operacion que se esta llevando a cabo
    */
   private boolean confirmOption(){
       int ok= -1;
       ok= JOptionPane.showConfirmDialog(this, aplicacion.getI18nString("cementerio.optionpane.tag1"), aplicacion.getI18nString("cementerio.optionpane.tag2"), JOptionPane.YES_NO_OPTION);
       if (ok == JOptionPane.NO_OPTION){
           return false;
       }
       return true;
   }

   private void exitForm(java.awt.event.WindowEvent evt) {
  	   fireActionPerformed();
   }

   private void elemHistoricosJTableMouseReleased() {
   	
       Object obj = getHistoricoSeleccionado();
       if (obj instanceof HistoricoPropiedadBean){
       	setHistoricoSelected((HistoricoPropiedadBean) getHistoricoSeleccionado());
       }
   }
   
   private void setInvisible(int column, JTable jTable){
       /** columna hidden no visible */
       TableColumn col= jTable.getColumnModel().getColumn(column);
       col.setResizable(false);
       col.setWidth(0);
       col.setMaxWidth(0);
       col.setMinWidth(0);
       col.setPreferredWidth(0);
   }
   

    public HistoricoPropiedadBean getHistoricoSelected() {
	return historicoSelected;
}


public void setHistoricoSelected(HistoricoPropiedadBean historicoSelected) {
	this.historicoSelected = historicoSelected;
}


	public void botoneraAceptarCancelarJPanel_actionPerformed(){
  	
  	 if((!botoneraAceptarCancelarJPanel.aceptarPressed()) ||
          (botoneraAceptarCancelarJPanel.aceptarPressed() && operacion.equalsIgnoreCase(Constantes.OPERACION_MODIFICAR)?!confirmOption():false)){
      	historicoSelected= null;
      }
      else{
//      		actualizarDatosHistorico(historicoSelected);
        }
        fireActionPerformed();
    }    
    
    // Variables declaration - do not modify
    private javax.swing.JLabel DNIJLabel;
    private javax.swing.JTextField DNIJTextField;
    private javax.swing.JTextField apellido1JTextField;
    private javax.swing.JLabel apellido2JLabel;
    private javax.swing.JTextField apellido2JTextField;
    private javax.swing.JCheckBox casadoJCheckBox;
    private javax.swing.JLabel cementerioJLabel;
    private javax.swing.JLabel entidadJLabel;
    private javax.swing.JCheckBox hombreJCheckBox;
    private javax.swing.JTextField cementerioJTextField;
    private javax.swing.JTextField entidadJTextField;
    private javax.swing.JCheckBox mujerJCheckBox;
    private javax.swing.JTextField nombreJTextField;
    private javax.swing.JLabel sexolJLabel;
    private javax.swing.JCheckBox solteroJCheckBox;

    private javax.swing.JScrollPane historicoJScrollPane;
    private javax.swing.JTable historicosTableJTable;
    private javax.swing.JPanel historicosJPanel;
    
    private javax.swing.JPanel TitularJPanel;
    // End of variables declaration
    
    
    private javax.swing.JLabel domicilioPostalJLabel;
    private javax.swing.JLabel nombreJLabel;
    private javax.swing.JLabel apellidosJLabel;
    private javax.swing.JPanel DatosGeneralesComunesJPanel;
    private javax.swing.JPanel datosTitularJPanel;
    private javax.swing.JTextField domicilioPostalJTextField;
    private javax.swing.JLabel estadoCivilJLabel;
    

    private javax.swing.JLabel fechaNacimientoJLabel;
    private JDateChooser fechaNacimientoJDateChooser;

    private javax.swing.JLabel poblacionJLabel;
    private javax.swing.JTextField poblacionJTextField;
    private javax.swing.JLabel telefonoJLabel;
    private javax.swing.JTextField telefonoJTextField;
    
    
}
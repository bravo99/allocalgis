package com.geopista.app.eiel.models;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.geopista.app.eiel.beans.UsosCasasConsistoriales;
import com.vividsolutions.jump.I18N;

/**
 * Clase que implementa el modelo de datos de la tabla del panel BienesJPanel
 */

public class UsosCasasConsistorialesCompletoEIELTableModel  extends DefaultTableModel {
	
	private static String[] columnNames = {
            I18N.get("LocalGISEIEL","localgiseiel.tabla.cc.columna.uso"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.cc.columna.superficie"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.cc.columna.fecha_uso"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.cc.columna.observ_uso"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.cc.columna.codorden")};
	    
    public UsosCasasConsistorialesCompletoEIELTableModel() {        
    	
    }

    private ArrayList lstElementos = new ArrayList();

    /**
     * @return n�mero de columnas de la tabla
     */
    public int getColumnCount() {
    	if (columnNames!=null){
    		return columnNames.length;
    	}
    	else
    		return 0;
    }
    
    /**
     * @return n�mero de filas de la tabla
     */
    public int getRowCount() {
    	if (lstElementos != null)
    		return lstElementos.size();
    	else
    		return 0;
    }
    
    
    /**
     * Devuelve el nombre de la columna solicitada
     * @param col �ndice de la columna
     * @return nombre de la columna
     */
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    /**
     * Devuelve el objeto que contiene la celda en la posici�n indicada
     * @param row �ndice de la fila
     * @param col �ndice de la columna
     * 
     * @return Objeto contenido en la posici�n seleccionada
     */
    public Object getValueAt(int row, int col) {
        
        if (lstElementos.get(row)==null)
            return null;
        
        switch (col)
        {
        case 0: 
            return ((UsosCasasConsistoriales)lstElementos.get(row)).getUso();
        case 1: 
            return ((UsosCasasConsistoriales) lstElementos.get(row)).getSuperficieUso() != null ? ((UsosCasasConsistoriales) lstElementos
                    .get(row)).getSuperficieUso().toString()
                    : "";
        case 3: 
            return ((UsosCasasConsistoriales)lstElementos.get(row)).getFechaUso() != null ? ((UsosCasasConsistoriales) lstElementos
                    .get(row)).getFechaUso().toString()
                    : "";
        case 4: 
            return ((UsosCasasConsistoriales)lstElementos.get(row)).getObservacionesUso();
        case 5: 
            return ((UsosCasasConsistoriales)lstElementos.get(row)).getCodigoOrdenUso();
                
        default: 
            return null;
        
        }        
    }
    
  
    public UsosCasasConsistoriales getValueAt(int row) {
        
        return (UsosCasasConsistoriales)lstElementos.get(row);
        
    }
    /**
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  
     */
    public Class getColumnClass(int c) {
        
        if (getValueAt(0, c)!=null)        
            return getValueAt(0, c).getClass();
        else
            return String.class;
    }
    
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    
    /**
     * Establece los datos mostrados en el modelo
     * @param datos Datos a mostrar en el modelo
     */
    public void setData (ArrayList datos)
    {
        this.lstElementos = datos;
    }
    
    /**
     * Recupera los datos del modelo
     * @return Datos del modelo
     */
    public ArrayList getData (){
        return lstElementos;
    }    

}

package com.geopista.ui.plugin.infcatfisicoeconomico.paneles;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.geopista.app.catastro.intercambio.edicion.utils.EdicionOperations;
import com.geopista.app.catastro.intercambio.edicion.utils.EdicionUtils;
import com.geopista.app.catastro.intercambio.edicion.utils.HideableNode;
import com.geopista.app.catastro.intercambio.edicion.utils.HideableTreeModel;
import com.geopista.app.catastro.model.beans.BienInmuebleJuridico;
import com.geopista.app.catastro.model.beans.BienInmuebleCatastro;
import com.geopista.app.catastro.model.beans.ConstruccionCatastro;
import com.geopista.app.catastro.model.beans.Cultivo;
import com.geopista.app.catastro.model.beans.DireccionLocalizacion;
import com.geopista.app.catastro.model.beans.Expediente;
import com.geopista.app.catastro.model.beans.FincaCatastro;
import com.geopista.app.catastro.model.beans.Persona;
import com.geopista.app.catastro.model.beans.SueloCatastro;
import com.geopista.app.catastro.model.beans.Titular;
import com.geopista.app.catastro.model.beans.UnidadConstructivaCatastro;
import com.geopista.app.catastro.model.datos.economicos.DatosEconomicosFinca;
import com.geopista.app.catastro.model.datos.fisicos.DatosFisicosFinca;
import com.geopista.feature.Table;
import com.geopista.ui.plugin.infcatfisicoeconomico.dialogs.TreeRenderer;

public class InfoCatastralPanelTree extends JPanel  {
    
    private JTree tree;    
    
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";    
   
   // private static Expediente expediente=null;
    private static ArrayList lstReferencias = null;
    private boolean referenciasTraidas[];
    private ArrayList lstReferenciasBorradas = new ArrayList();
    
    DefaultMutableTreeNode parent = null;
     
    public InfoCatastralPanelTree(int modoSeleccion, ArrayList listReferencias) {
        super(new GridLayout(1,0));
        
        //InfoCatastralPanelTree.expediente = expediente;
        InfoCatastralPanelTree.lstReferencias = listReferencias;
        referenciasTraidas = new boolean[lstReferencias.size()];
        for(int i=0;i< referenciasTraidas.length;i++)
        {
            referenciasTraidas[i] = false;
        }
               
        Expediente exp = new Expediente();
        exp.setListaReferencias(listReferencias);
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(exp);
        
        //Crea los nodos
        createNodes(top);
        
        //Crea un arbol que permite la seleccion indicada en modoSeleccion
        HideableTreeModel ml = new HideableTreeModel( top );
        ml.activateFilter(true);
        tree = new JTree( ml );
        
        //tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(modoSeleccion);
        
        //Aspecto del arbol (renderer)
        tree.setCellRenderer(new TreeRenderer());  
        
        //tree.setName("DatosExpediente");
        tree.setEditable(false);
        
        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }
        
        //Crea el scroll pane donde se mostrar� el arbol
        //JScrollPane treeView = new JScrollPane(tree);        
        add(tree, null);
        tree.setSelectionRow(0);
    }
    
 
    private void createNodes(DefaultMutableTreeNode top) 
    {   
        //Recorre las fincas o bienes inmuebles catastrales
        Iterator it = lstReferencias.iterator();
        while(it.hasNext())
        {
            Object o = it.next();
            if (o instanceof FincaCatastro)
               top.add(new HideableNode((FincaCatastro)o));
            else if (o instanceof BienInmuebleCatastro)
            {
                BienInmuebleJuridico bij = new BienInmuebleJuridico();
                bij.setBienInmueble((BienInmuebleCatastro)o);
                top.add(new HideableNode(bij));
            }               
        }           
    }
    
    public DefaultMutableTreeNode findBINode(String numOrdenBienInmueble)
    {
        TreeNode root = (TreeNode)tree.getModel().getRoot();
        visitAllNodes(root, numOrdenBienInmueble);
        return parent;
    }
    
    public DefaultMutableTreeNode findFincaNode(String refFinca)
    {
        TreeNode root = (TreeNode)tree.getModel().getRoot();
        visitAllNodes(root, refFinca);
        return parent;
    }

 
    public void visitAllNodes(TreeNode node, String numOrdenBienInmueble) {
        
        if (((DefaultMutableTreeNode)node).getUserObject() instanceof BienInmuebleJuridico
                && ((BienInmuebleJuridico)((DefaultMutableTreeNode)node).
                        getUserObject()).getBienInmueble().getIdBienInmueble().getNumCargo()!=null
                && ((BienInmuebleJuridico)((DefaultMutableTreeNode)node).
                        getUserObject()).getBienInmueble().getIdBienInmueble().getNumCargo().equals(numOrdenBienInmueble))
        {
            parent = (DefaultMutableTreeNode)node;
        }
        if (node.getChildCount() >= 0) {
            for (Enumeration e=node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode)e.nextElement();
                visitAllNodes(n, numOrdenBienInmueble);
            }
        }    
    }

    public JTree addNode (Table table)
    {       
        HideableNode root = (HideableNode)tree.getModel().getRoot();
        root.add(new HideableNode(table));
        //tree = new JTree(root);
        HideableTreeModel ml = new HideableTreeModel( root );
        ml.activateFilter(true);
        tree = new JTree( ml );
        return tree;        
    }
    
        
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
       
        JFrame frame = new JFrame("Arbol");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
       /* InfoCatastralPanelTree newContentPane = 
            new InfoCatastralPanelTree(TreeSelectionModel.SINGLE_TREE_SELECTION, 
                    expediente);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        */
        //mostrar la ventana
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }      
    
    /**
     * @return Returns the tree.
     */
    public JTree getTree()
    {
        return tree;
    }
    /**
     * @param tree The tree to set.
     */
    public void setTree(JTree tree)
    {
        this.tree = tree;
    }
   
    /**
     * @param enabled True si est� habilitado
     */
    public void setEnabled (boolean enabled)
    {
        tree.setEnabled(enabled);        
    }
    
    /**
     * 
     * @param child
     * @return
     */
    public DefaultMutableTreeNode addObject(Object child) {
        HideableNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            //No hay selecci�n. Por defecto se marca el nodo ra�z
            parentNode = (HideableNode)tree.getModel().getRoot();
        } else {
            parentNode = (HideableNode)
                         (parentPath.getLastPathComponent());            
        }

        return addObject(parentNode, child, true);
    }
    /**
     * 
     * @param parent
     * @param child
     * @param shouldBeVisible
     * @return
     */
    public HideableNode addObject(HideableNode parent,
            Object child,
            boolean shouldBeVisible) {
        HideableNode childNode =
            //new HideableNode((Table)child);
        	new HideableNode(child);

        ((HideableTreeModel)tree.getModel()).insertNodeInto(childNode, parent,
                parent.getChildCount());
        
        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    public void removeObject(Object child) {
        
        ((HideableTreeModel)tree.getModel()).removeNodeFromParent((DefaultMutableTreeNode)child);
                
        return;
    }

    public FincaCatastro fillObject(DefaultMutableTreeNode nodoFinca)
    {
    	FincaCatastro fc = null;
        int traido = isTraidaFinca(nodoFinca);
        if(traido!=-1)
        {
            return (FincaCatastro)lstReferencias.get(traido);
        }

        /*if (nodoFinca.getUserObject() instanceof FincaCatastro
                && nodoFinca.children()==DefaultMutableTreeNode.EMPTY_ENUMERATION)*/
        if (nodoFinca.getUserObject() instanceof FincaCatastro)
        {  
        	fc = (FincaCatastro)nodoFinca.getUserObject();

        	if (!fc.isDelete()){
        		EdicionOperations oper = new EdicionOperations();
        		//fc = oper.obtenerInfoFinca(fc, expediente.getIdExpediente());

        		//if (!fc.isDelete()){

    			if(nodoFinca.children()==DefaultMutableTreeNode.EMPTY_ENUMERATION){

    				//EdicionOperations oper = new EdicionOperations();
    				//fc = oper.obtenerInfoFinca(fc, expediente.getIdExpediente());

    				//Recorre los bienes inmuebles que contiene la finca
    				if (fc.getLstBienesInmuebles()!=null)
    				{
    					Iterator itBIJ = fc.getLstBienesInmuebles().iterator();

    					while (itBIJ.hasNext())
    					{
    						BienInmuebleJuridico bij = (BienInmuebleJuridico)itBIJ.next();
    						HideableNode nodoBien = new HideableNode(bij);
    						nodoFinca.add(nodoBien);

    						Iterator itTitulares = bij.getLstTitulares().iterator();
    						while (itTitulares.hasNext())
    						{
    							Persona p = (Persona)itTitulares.next();
    							p.setBienInmueble(bij.getBienInmueble());
    							HideableNode nodoPersona = new HideableNode(p);
    							nodoBien.add(nodoPersona);                    
    						}
    					}
    				}

    				//Recorre las construcciones o locales que existen en la finca, 
    				//distinguiendo si son o no comunes
    				if (fc.getLstConstrucciones()!=null)
    				{                
    					Iterator itConstrucciones = fc.getLstConstrucciones().iterator();
    					while (itConstrucciones.hasNext())
    					{
    						ConstruccionCatastro cons = (ConstruccionCatastro)itConstrucciones.next();
    						HideableNode nodoCons = new HideableNode(cons);

    						//Si viene relleno el campo codigo del tipo de valor a aplicar
    						//en los datos de valoraci�n, se trata de una construcci�n com�n
    						if (cons.getDatosEconomicos().getCodTipoValor()!=null
    								&& !cons.getDatosEconomicos().getCodTipoValor().equals("")
    								&& cons.getDatosEconomicos().getCodModalidadReparto()!=null)
    						{                        
    							//Si la construccion es comun, rellena con sus repartos
    							cons = EdicionUtils.getConstruccionRepartos(cons,fc.getLstReparto(), fc.getLstBienesInmuebles(), fc.getLstConstrucciones()); 

    							nodoFinca.add(new HideableNode(cons));
    						}
    						else
    						{
    							DefaultMutableTreeNode hn = findBINode(cons.getNumOrdenBienInmueble());
    							parent = null;
    							if (hn!=null){
    								if(hn.getUserObject() instanceof BienInmuebleJuridico){
    									((BienInmuebleJuridico)hn.getUserObject()).getBienInmueble().getLstConstrucciones().add(cons);
    								}
    								((HideableNode)hn).add(nodoCons);    
    							}
    						}

    					}
    				}

    				//Recorre los cultivos que existen en la finca, distinguiendo si son o no comunes
    				if(fc.getLstCultivos()!=null)
    				{
    					Iterator itCultivos= fc.getLstCultivos().iterator();
    					while (itCultivos.hasNext())
    					{
    						Cultivo cultivo = (Cultivo)itCultivos.next();
    						HideableNode nodoCultivo = new HideableNode(cultivo);

    						//si no viene relleno el n�mero de orden, se trata de un cultivo com�n
    						/*if (cultivo.getIdCultivo().getNumOrden()==null &&
							cultivo.getIdCultivo().getNumOrden().trim().equals("") &&
							cultivo.getCodModalidadReparto()!=null)*/
    						if ((cultivo.getIdCultivo().getNumOrden()==null ||
    								cultivo.getIdCultivo().getNumOrden().trim().equals("")) &&
    								cultivo.getCodModalidadReparto()!=null)
    						{   
    							//Si el cultivo es comun, rellena con sus repartos
    							cultivo = EdicionUtils.getCultivosRepartos(cultivo,fc.getLstReparto(), fc.getLstBienesInmuebles()); 

    							nodoFinca.add(nodoCultivo);
    						}
    						else{
    							DefaultMutableTreeNode hn = findBINode(cultivo.getIdCultivo().getNumOrden());
    							if (hn!=null){
    								if(hn.getUserObject() instanceof BienInmuebleJuridico){
    									((BienInmuebleJuridico)hn.getUserObject()).getBienInmueble().getLstCultivos().add(cultivo);
    								}
    								((HideableNode)hn).add(nodoCultivo);  
    							}
    						}       
    					}
    				}
    				//Recorre las unidades constructivas que existen en la finca
    				if (fc.getLstUnidadesConstructivas()!=null)
    				{
    					Iterator itUC= fc.getLstUnidadesConstructivas().iterator();
    					while (itUC.hasNext())
    					{
    						UnidadConstructivaCatastro uc = (UnidadConstructivaCatastro)itUC.next();
    						HideableNode nodoUC = new HideableNode(uc);
    						nodoFinca.add(nodoUC);              
    					}
    				}
    				//Recorre los suelos que existen en la finca
    				if(fc.getLstSuelos()!=null)
    				{
    					Iterator itSuelos= fc.getLstSuelos().iterator();
    					while (itSuelos.hasNext())
    					{
    						SueloCatastro suelo = (SueloCatastro)itSuelos.next();
    						HideableNode nodoSuelo = new HideableNode(suelo);
    						nodoFinca.add(nodoSuelo);                          
    					}   

    				}
    			}
        		//}
        	}
        }
        rellenaListRefer(fc);
        return fc;
    }

    private void rellenaListRefer(Object fincaOBI)
    {
        for(int i =0; i<lstReferencias.size();i++)
        {
            if(fincaOBI instanceof FincaCatastro &&
                    ((FincaCatastro)lstReferencias.get(i)).getRefFinca().getRefCatastral()
                            .equalsIgnoreCase(((FincaCatastro)fincaOBI).getRefFinca().getRefCatastral()))
            {
                lstReferencias.set(i, fincaOBI);
                referenciasTraidas[i] = true;
            }
            else if (lstReferencias.get(i) instanceof BienInmuebleCatastro && 
            		fincaOBI instanceof BienInmuebleJuridico && 
            		((BienInmuebleCatastro)lstReferencias.get(i)).getIdBienInmueble().getIdBienInmueble()
                                .equalsIgnoreCase(((BienInmuebleJuridico)fincaOBI).getBienInmueble().getIdBienInmueble().getIdBienInmueble()))
                {
                    lstReferencias.set(i, fincaOBI);
                    referenciasTraidas[i] = true;
                }            
            else if(lstReferencias.get(i) instanceof BienInmuebleJuridico && 
            		fincaOBI instanceof BienInmuebleJuridico &&
                    ((BienInmuebleJuridico)lstReferencias.get(i)).getBienInmueble().getIdBienInmueble().getIdBienInmueble()
                            .equalsIgnoreCase(((BienInmuebleJuridico)fincaOBI).getBienInmueble().getIdBienInmueble().getIdBienInmueble()))
            {
                lstReferencias.set(i, fincaOBI);
                referenciasTraidas[i] = true;
            }
        }
    }

    private int isTraidaFinca(DefaultMutableTreeNode nodo)
    {
        int resultado = -1;
        FincaCatastro fc = (FincaCatastro)nodo.getUserObject();
        for(int i =0; i<lstReferencias.size();i++)
        {
            if(((FincaCatastro)lstReferencias.get(i)).getRefFinca().getRefCatastral().equalsIgnoreCase(fc.getRefFinca().getRefCatastral()))
            {
                if(referenciasTraidas[i])
                {
                    return i;
                }
                else
                {
                    return -1;
                }
            }
        }
        return resultado;
    }

    private int isTraidoBienInmuebleJuridico(DefaultMutableTreeNode nodo)
    {
        int resultado = -1;
        BienInmuebleJuridico bij = (BienInmuebleJuridico)nodo.getUserObject();
        for(int i =0; i<lstReferencias.size();i++)
        {
            if((lstReferencias.get(i) instanceof BienInmuebleJuridico &&((BienInmuebleJuridico)lstReferencias.get(i)).
                    getBienInmueble().getIdBienInmueble().getIdBienInmueble()
                    .equalsIgnoreCase(bij.getBienInmueble().getIdBienInmueble().getIdBienInmueble()))||(lstReferencias.get(i) instanceof BienInmuebleCatastro&&
            ((BienInmuebleCatastro)lstReferencias.get(i)).
                   getIdBienInmueble().getIdBienInmueble()
                    .equalsIgnoreCase(bij.getBienInmueble().getIdBienInmueble().getIdBienInmueble())))
            {
                if(referenciasTraidas[i])
                {
                    return i;
                }
                else
                {
                    return -1;
                }
            }
        }
        return resultado;
    }



    public BienInmuebleJuridico fillObjectBienInmueble(DefaultMutableTreeNode nodoBI)
    {
        BienInmuebleJuridico bij = null;
        int traido = isTraidoBienInmuebleJuridico(nodoBI);
        if(traido!=-1)
        {
            return (BienInmuebleJuridico)lstReferencias.get(traido);
        }
        if (nodoBI.getUserObject() instanceof BienInmuebleJuridico
                && nodoBI.children()==DefaultMutableTreeNode.EMPTY_ENUMERATION)
        {              
            EdicionOperations oper = new EdicionOperations();
            //bij = oper.obtenerInfoBien((BienInmuebleJuridico)nodoBI.getUserObject(),
              //      expediente.getIdExpediente());
        } 
        if (bij!=null && bij.getLstTitulares()!=null)
        {
            Iterator itTit = bij.getLstTitulares().iterator();
            while (itTit.hasNext())
            {                
                nodoBI.add(new HideableNode((Titular)itTit.next()));
            }
        }
        rellenaListRefer(bij);
        return bij;
    }
    
    private void collapseAll(JTree tree)
    {
        int row = tree.getRowCount() - 1;
        while (row >= 0) {
            tree.collapseRow(row);
            row--;
        }
    }


    public static ArrayList getLstReferencias() {
        return lstReferencias;
    }

    public void annadeFincaOBI(Object fincaOBI)
    {
        lstReferencias.add(fincaOBI);
        boolean aux[] = new boolean[lstReferencias.size()];
        for(int i = 0; i<referenciasTraidas.length;i++)
        {
            aux[i] = referenciasTraidas[i];
        }
        aux[aux.length-1] = true;
        referenciasTraidas = aux;
    }

    public HideableNode addObject(DefaultMutableTreeNode parent,
            Object child,
            boolean shouldBeVisible) {
        HideableNode childNode =
            //new HideableNode((Table)child);
        	new HideableNode(child);

        ((HideableTreeModel)tree.getModel()).insertNodeInto(childNode, parent,
                parent.getChildCount());

        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    public void eliminaFincaOBI(Object fincaOBI)
    {
        if(fincaOBI instanceof FincaCatastro)
        {
            boolean aux[] = new boolean[lstReferencias.size()];
            int cont = 0;
            for(int i = 0; i<lstReferencias.size();i++)
            {
                FincaCatastro fi = (FincaCatastro)lstReferencias.get(i);
                if(!fi.getRefFinca().getRefCatastral().equalsIgnoreCase(((FincaCatastro)fincaOBI).getRefFinca().getRefCatastral()))
                {
                    aux[i] = referenciasTraidas[cont];
                }
                else
                {
                    FincaCatastro finca = (FincaCatastro)lstReferencias.get(i);
                    fincaIsDelete(finca);
                    fincaIsDelete((FincaCatastro)fincaOBI);
                    aux[i] = false;
                }
                cont++;
            }
            referenciasTraidas = aux;
        }
        else if(fincaOBI instanceof BienInmuebleJuridico)
        {
            boolean aux[] = new boolean[lstReferencias.size()-1];
            int cont =0;
            for(int i = 0; i<lstReferencias.size();i++)
            {
                BienInmuebleJuridico bi = (BienInmuebleJuridico)lstReferencias.get(i);
                if(!bi.getBienInmueble().getIdBienInmueble().getIdBienInmueble().equalsIgnoreCase(((BienInmuebleJuridico)fincaOBI)
                        .getBienInmueble().getIdBienInmueble().getIdBienInmueble()))
                {
                    aux[i] = referenciasTraidas[cont];
                }
                else
                {
                    BienInmuebleJuridico bij = (BienInmuebleJuridico)lstReferencias.get(i);
                    bij.setDelete(true);
                    lstReferenciasBorradas.add(bij);
                    lstReferencias.remove(i);
                    i--;
                }
                cont++;
            }
            referenciasTraidas = aux;
        }
    }
    
    
    private void fincaIsDelete(FincaCatastro finca){
    	
    	if (finca != null){
    		
//    		finca.setDelete(true);
    		finca.setTIPO_MOVIMIENTO_DELETE("B");
    		finca.setBICE("");
    		DatosEconomicosFinca datosEconomicosEmpty = new DatosEconomicosFinca();
    		finca.setDatosEconomicos(datosEconomicosEmpty);
    		
    		DireccionLocalizacion direccionEmpty = new DireccionLocalizacion();
    		finca.setDirParcela(direccionEmpty);
    		
    		DatosFisicosFinca datosFisicosEmpty = new DatosFisicosFinca();
    		finca.setDatosFisicos(datosFisicosEmpty);
    		    		
    		if (finca.getLstBienesInmuebles() != null)
    			finca.getLstBienesInmuebles().clear();
    		if (finca.getLstConstrucciones() != null)
    			finca.getLstConstrucciones().clear();
    		if (finca.getLstCultivos() != null)
    			finca.getLstCultivos().clear();
    		if (finca.getLstImagenes() != null)
    			finca.getLstImagenes().clear();
    		if (finca.getLstReparto() != null)
    			finca.getLstReparto().clear();
    		if (finca.getLstSuelos() != null)
    			finca.getLstSuelos().clear();
    		if (finca.getLstUnidadesConstructivas() != null)
    			finca.getLstUnidadesConstructivas().clear();
    	}
    }

    public ArrayList getLstReferenciasBorradas()
    {
        return lstReferenciasBorradas;
    }

    public void setLstReferenciasBorradas(ArrayList lstReferenciasBorradas)
    {
        this.lstReferenciasBorradas = lstReferenciasBorradas;
    }

    public ArrayList getFincasEnMemoria()
    {
        ArrayList lstFincaMem = new ArrayList();
        for(int i =0;i<lstReferencias.size();i++)
        {
            if(referenciasTraidas[i])
            {
                lstFincaMem.add(lstReferencias.get(i));
            }
        }
        return lstFincaMem;
    }
}


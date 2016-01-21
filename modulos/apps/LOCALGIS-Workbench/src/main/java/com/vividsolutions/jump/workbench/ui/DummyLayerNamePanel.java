/*
 * The Unified Mapping Platform (JUMP) is an extensible, interactive GUI 
 * for visualizing and manipulating spatial features with geometry and attributes.
 *
 * Copyright (C) 2003 Vivid Solutions
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 * For more information, contact:
 *
 * Vivid Solutions
 * Suite #1A
 * 2328 Government Street
 * Victoria BC  V8T 5G5
 * Canada
 *
 * (250)385-6040
 * www.vividsolutions.com
 */
package com.vividsolutions.jump.workbench.ui;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;

import com.vividsolutions.jump.workbench.model.ILayerManager;
import com.vividsolutions.jump.workbench.model.Layer;
import com.vividsolutions.jump.workbench.model.Layerable;

/**
 *  Implements a minimal LayerNamePanel in a JPanel.
 */

public class DummyLayerNamePanel extends JPanel implements LayerNamePanel {

    private ArrayList selectedCategories = new ArrayList();
    private ArrayList selectedNodes = new ArrayList();
    private Layerable[] selectedLayerables=new Layerable[] {};;

    public Collection getSelectedCategories() {
        return selectedCategories;
    }

    public Collection selectedNodes(Class c) {
        return selectedNodes;
    }

    public Layer[] getSelectedLayers() {
        return null;
    }
    public Layerable[] getSelectedLayerables() {
    	return selectedLayerables;
    }
    public Layer chooseEditableLayer() {
        return TreeLayerNamePanel.chooseEditableLayer(this);
    }

    public void addListener(LayerNamePanelListener listener) {}
    public void removeListener(LayerNamePanelListener listener) {}

    public void dispose() {}

    public ILayerManager getLayerManager() {
        return null;
    }

	/* (non-Javadoc)
	 * @see com.vividsolutions.jump.workbench.ui.LayerNamePanel#selectLayers(com.vividsolutions.jump.workbench.model.Layer[])
	 */
	public void selectLayerables(Layerable[] layers)
	{
		selectedLayerables=layers;
		
	}

	@Override
	public void setTargetSelectedLayers(Layerable[] layers) {
		// TODO Auto-generated method stub
		
	}

}
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

package com.vividsolutions.jump.workbench.ui.warp;

import java.awt.Color;

import com.vividsolutions.jump.workbench.model.AbstractVectorLayerFinder;
import com.vividsolutions.jump.workbench.model.Layer;
import com.vividsolutions.jump.workbench.model.LayerManagerProxy;
import com.vividsolutions.jump.workbench.ui.renderer.style.PinEqualCoordinatesStyle;

/**
 * Retrieves the Incremental Warping Vectors layer and sets its styles.
 */
public class IncrementalWarpingVectorLayerFinder extends AbstractVectorLayerFinder {
    /** Same colour as pin icons */
    public static final Color COLOR = Color.green.darker().darker();
    public IncrementalWarpingVectorLayerFinder(LayerManagerProxy layerManagerProxy) {
        super("Incremental Warping Vectors", layerManagerProxy, COLOR);
    }    
    protected void applyStyles(Layer layer) {
        super.applyStyles(layer);
        if (null == layer.getStyle(PinEqualCoordinatesStyle.class)) {
            layer.addStyle(new PinEqualCoordinatesStyle(COLOR));
        }        
    }
}
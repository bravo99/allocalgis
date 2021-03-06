/**
 * SelectionTools.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 11.02.2005 for PIROL
 *
 * CVS header information:
 *  $RCSfile: SelectionTools.java,v $
 *  $Revision: 1.1 $
 *  $Date: 2009/07/03 12:31:54 $
 *  $Source: /usr/cvslocalgis/.CVSROOT/localgisdos/core/src/pirolPlugIns/utilities/SelectionTools.java,v $
 */
package pirolPlugIns.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jump.feature.Feature;
import com.vividsolutions.jump.feature.FeatureCollection;
import com.vividsolutions.jump.feature.FeatureCollectionWrapper;
import com.vividsolutions.jump.workbench.model.FenceLayerFinder;
import com.vividsolutions.jump.workbench.model.Layer;
import com.vividsolutions.jump.workbench.model.LayerManagerProxy;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
import com.vividsolutions.jump.workbench.ui.ISelectionManager;

/**
 * Class to easily handle selections and selection tools. Also has methods to
 * find features by given geometries.
 * 
 * @author Ole Rahn
 * 
 * FH Osnabr�ck - University of Applied Sciences Osnabr�ck
 * Project PIROL 2005
 * Daten- und Wissensmanagement
 * 
 */

public class SelectionTools extends ToolToMakeYourLifeEasier {
    protected PlugInContext context = null;
    
    public SelectionTools(PlugInContext context){
        super();
        this.context = context;
    }
    
    /**
     * create a selection out of the given features that is visible in the Jump map
     *@param features features to be selected
     */
    public void selectFeatures( List features ){
        SelectionTools.selectFeatures(features, this.context);
    }
    
    public static void selectLayer(PlugInContext context, Layer layer ){
        // TODO: how to select a layer???       
    }
    
    public static void selectFeatures( List features, PlugInContext context ){
        ISelectionManager sm = context.getLayerViewPanel().getSelectionManager(); 
        sm.clear();
        
        Map layer2FeatList = LayerTools.getLayer2FeatureMap(features, context);
        Layer[] layersWithFeatures = (Layer[])layer2FeatList.keySet().toArray(new Layer[0]);
        
        for ( int i=0; i<layersWithFeatures.length; i++ ){
            sm.getFeatureSelection().selectItems(layersWithFeatures[i], (ArrayList)layer2FeatList.get(layersWithFeatures[i]));
        }

    }
    
    public static List getSelectedFeaturesFromLayer(PlugInContext context, Layer layer){
        return new ArrayList( context.getLayerViewPanel().getSelectionManager().getFeaturesWithSelectedItems(layer) );
    }
    
    public static List getSelectedFeatures(PlugInContext context){
        return new ArrayList( context.getLayerViewPanel().getSelectionManager().getFeaturesWithSelectedItems() );
    }
    
    public List getSelectedFeatures(){
        return SelectionTools.getSelectedFeatures(this.context);
    }
    
    /**
     * 
     *@param context current PlugIn context
     *@return the geometry of the current fence, or null if there is currently no fence
     */
    public static Geometry getFenceGeometry(PlugInContext context){
        Layer fence = (Layer) new FenceLayerFinder((LayerManagerProxy)context.getLayerViewPanel()).getLayer();
        if (fence==null) return null;
        FeatureCollectionWrapper fenceCollWrap = fence.getFeatureCollectionWrapper();
		Feature fencePolygon = (Feature)fenceCollWrap.getUltimateWrappee().getFeatures().get(0);
		return fencePolygon.getGeometry();
    }
    
    /**
    *@return the geometry of the current fence, or null if there is currently no fence
    */
    public Geometry getFenceGeometry(){
        return SelectionTools.getFenceGeometry(this.context);
    }
    
    public List getFeaturesInFence(){
        Geometry fenceGeometry = this.getFenceGeometry();
        return this.getFeaturesInGeometry(fenceGeometry);
    }
    
    public List getFeaturesInGeometry(Geometry fenceGeometry){
        ArrayList featsInFence = new ArrayList();
        List layers = this.context.getLayerManager().getVisibleLayers(false);
        
        Iterator iter = layers.iterator();
        Layer tmp;
        while (iter.hasNext()){
            tmp = (Layer)iter.next();
            featsInFence.addAll(SelectionTools.getFeaturesInFenceInLayerAsList(tmp,fenceGeometry));
        }
        
        return featsInFence;
    }
    
    /**
     * Get a list of those features from the given layer that are included by the given fence geometry.
     * @param layer - Layer to search in
     * @param fenceGeometry - Geometry to search in
     */
    public static Feature[] getFeaturesInFenceInLayer( Layer layer, Geometry fenceGeometry ){
        return SelectionTools.getFeaturesOnTheSameSpot( layer, fenceGeometry, false );
    }
    
    /**
     * Get a list of those features from the given layer that are included by the given fence geometry.
     * @param layer - Layer to search in
     * @param fenceGeometry - Geometry to search in
     */
    public static List getFeaturesInFenceInLayerAsList( Layer layer, Geometry fenceGeometry ){
        Feature[] featureArray = SelectionTools.getFeaturesOnTheSameSpot( layer, fenceGeometry, false );
        ArrayList result = new ArrayList();
        
        for (int i=0; i<featureArray.length; i++){
            result.add(featureArray[i]);
        }
        
        return result;
    }
    
    /**
     * Get a list of features (a sub list of the given array) that are included by the given fence geometry.
     * @param featArray - Array of features to search in
     * @param fenceGeometry - Geometry to search in
     */
    public static Feature[] getFeaturesInFenceInLayer( Feature[] featArray, Geometry fenceGeometry ){
        return SelectionTools.getFeaturesOnTheSameSpot( featArray, fenceGeometry, false );
    }
    
    /**
     * Get a list of those features from the given layer that are included by the given fence geometry.
     * @param layer - Layer to search in
     * @param fenceGeometry - Geometry to search in
     * @param bothWays - sets if it's also a hit if the feature's geometry includes the fence geometry
     */
    public static Feature[] getFeaturesOnTheSameSpot( Layer layer, Geometry fenceGeometry, boolean bothWays ){
		FeatureCollection featColl = layer.getFeatureCollectionWrapper().getUltimateWrappee();
		return SelectionTools.getFeaturesOnTheSameSpot( FeatureCollectionTools.FeatureCollection2FeatureArray(featColl), fenceGeometry, bothWays);
    }
    
    /**
     * Get a list of features (a sub list of the given array) that are included by the given fence geometry.
     * @param featArray - Array of features to search in
     * @param fenceGeometry - Geometry to search in
     * @param bothWays - sets if it's also a hit if the feature's geometry includes the fence geometry
     */
    public static Feature[] getFeaturesOnTheSameSpot( Feature[] featArray, Geometry fenceGeometry, boolean bothWays ){
		ArrayList featuresInsideTheFence = new ArrayList();
		
		Envelope fenceEnv = fenceGeometry.getEnvelopeInternal();
		Envelope featEnv;
		Geometry featGeom = null;
		
		Feature feat;
		
		int numFeats = featArray.length;
		
		for (int i=0; i<numFeats; i++){
            feat = featArray[i];

			featGeom = feat.getGeometry();
			
			featEnv = featGeom.getEnvelopeInternal();
			
			// quick test, first:
			if (!fenceEnv.contains(featEnv) && !fenceEnv.intersects(featEnv) && !bothWays){
			    continue;
			} else if (bothWays){
			    if (!fenceEnv.contains(featEnv) && !fenceEnv.intersects(featGeom.getEnvelopeInternal()) && !featEnv.contains(fenceEnv) )
			        continue;
			}
			
			if (fenceGeometry.contains(featGeom) || fenceGeometry.intersects(featGeom)){
				featuresInsideTheFence.add(feat);
			} else if (bothWays){
			    if (featGeom.contains(fenceGeometry) || featGeom.intersects(fenceGeometry)){
			        featuresInsideTheFence.add(feat);
			    }
			}
		}
		return (Feature[])featuresInsideTheFence.toArray(new Feature[0]);
    }
}

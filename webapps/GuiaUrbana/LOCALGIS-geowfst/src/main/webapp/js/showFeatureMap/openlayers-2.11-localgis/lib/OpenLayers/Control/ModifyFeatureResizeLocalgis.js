
OpenLayers.Control.ModifyFeatureResizeLocalgis = OpenLayers.Class(OpenLayers.Control.ModifyFeature, {

	type: OpenLayers.Control.TYPE_TOOL,

	initialize: function(layer,options){
		OpenLayers.Control.ModifyFeature.prototype.initialize.apply(this, arguments);
		this.mode = OpenLayers.Control.ModifyFeature.RESIZE; 
	},
	
	CLASS_NAME: "OpenLayers.Control.ModifyFeatureResizeLocalgis"
});

/* Copyright (c) 2006-2007 MetaCarta, Inc., published under the BSD license.
 * See http://svn.openlayers.org/trunk/openlayers/release-license.txt 
 * for the full text of the license. */

/* 
 * @requires OpenLayers/BaseTypes.js
 */ 

(function() {
    /**
     * Before creating the OpenLayers namespace, check to see if
     * OpenLayers.singleFile is true.  This occurs if the
     * OpenLayers/SingleFile.js script is included before this one - as is the
     * case with single file builds.
     */
    var singleFile = (typeof OpenLayers == "object" && OpenLayers.singleFile);
    
    /**
     * Namespace: OpenLayers
     * The OpenLayers object provides a namespace for all things OpenLayers
     */
    OpenLayers = {
        
        /**
         * Property: _scriptName
         * {String} Relative path of this script.
         */
        _scriptName: (!singleFile) ? "lib/OpenLayers.js" : "OpenLayers.js",

        /**
         * Function: _getScriptLocation
         * Return the path to this script.
         *
         * Returns:
         * Path to this script
         */
        _getScriptLocation: function () {
            var scriptLocation = "";
            var scriptName = OpenLayers._scriptName;
         
            var scripts = document.getElementsByTagName('script');
            for (var i = 0; i < scripts.length; i++) {
                var src = scripts[i].getAttribute('src');
                if (src) {
                    var index = src.lastIndexOf(scriptName); 
                    // is it found, at the end of the URL?
                    if ((index > -1) && (index + scriptName.length == src.length)) {  
                        scriptLocation = src.slice(0, -scriptName.length);
                        break;
                    }
                }
            }
            return scriptLocation;
         }
    };
    /**
     * OpenLayers.singleFile is a flag indicating this file is being included
     * in a Single File Library build of the OpenLayers Library.
     * 
     * When we are *not* part of a SFL build we dynamically include the
     * OpenLayers library code.
     * 
     * When we *are* part of a SFL build we do not dynamically include the 
     * OpenLayers library code as it will be appended at the end of this file.
      */
    if(!singleFile) {
        var jsfiles = new Array(
            "OpenLayers/Util.js",
            "OpenLayers/BaseTypes.js",
            "OpenLayers/BaseTypes/Class.js",
            "OpenLayers/BaseTypes/Bounds.js",
            "OpenLayers/BaseTypes/Element.js",
            "OpenLayers/BaseTypes/LonLat.js",
            "OpenLayers/BaseTypes/Pixel.js",
            "OpenLayers/BaseTypes/Size.js",
            "OpenLayers/Console.js",
            "OpenLayers/Projection.js",
            "Rico/Corner.js",
            "Rico/Color.js",
            "OpenLayers/Ajax.js",
            "OpenLayers/Events.js",
            "OpenLayers/Map.js",
            "OpenLayers/Layer.js",
            "OpenLayers/Icon.js",
            
            
            "OpenLayers/Marker.js",
            "OpenLayers/Incidencia.js",
            "OpenLayers/Marker/Box.js",
            "OpenLayers/Popup.js",
            "OpenLayers/Tile.js",
            "OpenLayers/Tile/Image.js",
            "OpenLayers/Tile/WFS.js",
            "OpenLayers/Layer/Image.js",
            "OpenLayers/Layer/SphericalMercator.js",
            "OpenLayers/Layer/EventPane.js",
            "OpenLayers/Layer/FixedZoomLevels.js",
            "OpenLayers/Layer/Google.js",
            "OpenLayers/Layer/VirtualEarth.js",
            "OpenLayers/Layer/Yahoo.js",
            "OpenLayers/StyleMap.js",
            "OpenLayers/Style.js",
            "OpenLayers/Layer/HTTPRequest.js",
            "OpenLayers/Layer/Grid.js",
            
            
            "OpenLayers/Layer/WMS.js",
           
            "OpenLayers/Layer/Image.js",
            
            "OpenLayers/Layer/Markers.js",
            
            "OpenLayers/Layer/Vector.js",
            "OpenLayers/Layer/Boxes.js",
            "OpenLayers/Geometry.js",
            "OpenLayers/Geometry/Rectangle.js",
            "OpenLayers/Geometry/Collection.js",
            "OpenLayers/Geometry/Point.js",
            "OpenLayers/Geometry/MultiPoint.js",
            "OpenLayers/Geometry/Curve.js",
            "OpenLayers/Geometry/LineString.js",
            "OpenLayers/Geometry/LinearRing.js",        
            "OpenLayers/Geometry/Polygon.js",
            "OpenLayers/Geometry/MultiLineString.js",
            "OpenLayers/Geometry/MultiPolygon.js",
            "OpenLayers/Geometry/Surface.js",
            
            "OpenLayers/Renderer.js",
            "OpenLayers/Renderer/Elements.js",
            "OpenLayers/Renderer/SVG.js",
            "OpenLayers/Renderer/Canvas.js",
            "OpenLayers/Renderer/VML.js",
            "OpenLayers/Feature.js",
            "OpenLayers/Feature/Vector.js",
            "OpenLayers/Feature/WFS.js",
            "OpenLayers/Handler.js",
            "OpenLayers/Handler/Point.js",
            "OpenLayers/Handler/Path.js",
            "OpenLayers/Handler/Polygon.js",
            "OpenLayers/Handler/Feature.js",
            "OpenLayers/Handler/Drag.js",
            "OpenLayers/Handler/RegularPolygon.js",
            "OpenLayers/Handler/Box.js",
            "OpenLayers/Handler/MouseWheel.js",
            "OpenLayers/Handler/Keyboard.js",
            "OpenLayers/Handler/Feature.js",
            "OpenLayers/Handler/Incidencia.js",
            "OpenLayers/Handler/Marker.js",
            "OpenLayers/Control.js",
            "OpenLayers/Control/Attribution.js",
            "OpenLayers/Control/ZoomBox.js",
            "OpenLayers/Control/ZoomToMaxExtent.js",
            "OpenLayers/Control/DragPan.js",
            "OpenLayers/Control/Navigation.js",
            "OpenLayers/Control/MouseDefaults.js",
            "OpenLayers/Control/MousePosition.js",
            "OpenLayers/Control/Measure.js",
            "OpenLayers/Control/OverviewMap.js",
            "OpenLayers/Control/KeyboardDefaults.js",
            "OpenLayers/Control/PanZoom.js",
            "OpenLayers/Control/PanZoomBar.js",
            "OpenLayers/Control/ArgParser.js",
            "OpenLayers/Control/Permalink.js",
            "OpenLayers/Control/Scale.js",
            "OpenLayers/Control/LayerSwitcher.js",
            "OpenLayers/Control/DrawFeature.js",
            "OpenLayers/Control/DragFeature.js",
            "OpenLayers/Control/ModifyFeature.js",
            "OpenLayers/Control/Panel.js",
            "OpenLayers/Control/SelectFeature.js",
       	     "OpenLayers/Control/DragMarker.js",
             "OpenLayers/Control/RutasLocalgis.js",
             "OpenLayers/Control/WMSManager.js",
             "OpenLayers/Control/WithinCostLocalgis.js",
             "OpenLayers/Control/SalesManLocalgis.js",
             "OpenLayers/Control/WMSTime.js",
            "OpenLayers/Format.js",
            "OpenLayers/Format/XML.js",
            "OpenLayers/Format/GML.js",
            "OpenLayers/Format/KML.js",
            "OpenLayers/Format/GeoRSS.js",
            "OpenLayers/Format/WFS.js",
            "OpenLayers/Format/WKT.js",
            "OpenLayers/Format/JSON.js",
            "OpenLayers/Format/GeoJSON.js",
	     "OpenLayers/Layer/WFS.js",
	     "OpenLayers/Control/MouseToolbar.js",
	     "OpenLayers/Control/NavToolbar.js",
	     "OpenLayers/Control/EditingToolbar.js",
	     

            // Componentes a�adidos para localgis
	     "OpenLayers/Layer/Incidencias.js",
	     "../../reports/ReportLocalgis.js",
	     "OpenLayers/Control/ZoomInLocalgis.js",
            "OpenLayers/Control/ZoomOutLocalgis.js",
            "OpenLayers/Control/ZoomToEntidadLocalgis.js",
            "OpenLayers/Control/ZoomToComunidadLocalgis.js",
            "OpenLayers/Handler/ClickLocalgis.js",
            "OpenLayers/Layer/WMSLocalgis.js",
            "OpenLayers/MapLocalgis.js",
            "OpenLayers/PopupLocalgis.js",
            "OpenLayers/Control/IncidenciaLocalgis.js",            
            "OpenLayers/Control/GetFeatureInfoLocalgis.js",
            "OpenLayers/Control/LayerSwitcherLocalgis.js",
            "OpenLayers/Control/LayerInfoLocalgis.js",
            "OpenLayers/Control/MarkerLocalgis.js",
            "OpenLayers/Control/NavigationLocalgis.js",
            "OpenLayers/Control/OverviewMapLocalgis.js",
            "OpenLayers/Control/PrintLocalgis.js",
            "OpenLayers/Control/SaveLocalgis.js",
            "OpenLayers/Control/GpxLocalgis.js",
            "OpenLayers/Control/ScaleBar.js",
            "OpenLayers/Control/ScaleBarLocalgis.js",
            "OpenLayers/Control/SearchLocalgis.js",
            "OpenLayers/Control/ToolbarLocalgis.js",
            "OpenLayers/Control/ToolbarLocalgisReduced.js",
            "OpenLayers/Control/ToolbarLocalgisIncidencias.js",        
            "OpenLayers/Popup/AnchoredLocalgis.js",
            "OpenLayers/Popup/AnchoredBubbleLocalgis.js",
            "OpenLayers/LocalgisUtils.js",
            "OpenLayers/PositionMarkerLocalgis.js",
            "OpenLayers/PositionIncidenciaLocalgis.js",
            "OpenLayers/Control/ZoomToMaxExtentLocalgis.js",
            "OpenLayers/Control/ZoomBoxLocalgis.js",
            "OpenLayers/Control/MeasureLocalgis.js",
            "OpenLayers/Control/Measure.js",
            "OpenLayers/Control/MeasureAreaLocalgis.js",
            "OpenLayers/Rule.js",
            "OpenLayers/Layer/EventPane.js",
            "OpenLayers/Layer/Google.js",
            //"OpenLayers/Layer/Google/v3.js",
            "OpenLayers/Layer/FixedZoomLevels.js",
            "OpenLayers/Layer/SphericalMercator.js",
            "OpenLayers/Format/OSM.js",
            "OpenLayers/Layer/XYZ.js",
            "OpenLayers/Control/ZoomNucleo.js" 
        ); // etc.



        var allScriptTags = "";
        var host = OpenLayers._getScriptLocation() + "lib/";
    
        for (var i = 0; i < jsfiles.length; i++) {

            var currentScriptTag = "<script src='" + host + jsfiles[i] + "'></script>"; 
            allScriptTags += currentScriptTag;

        	/*if (/MSIE/.test(navigator.userAgent) || /Safari/.test(navigator.userAgent)) {
                var currentScriptTag = "<script src='" + host + jsfiles[i] + "'></script>"; 
                allScriptTags += currentScriptTag;
            } else {
                var s = document.createElement("script");
                s.src = host + jsfiles[i];
                var h = document.getElementsByTagName("head").length ? 
                           document.getElementsByTagName("head")[0] : 
                           document.body;
                h.appendChild(s);
            }*/
        }
        if (allScriptTags) document.write(allScriptTags);
    }
})();

/**
 * Constant: VERSION_NUMBER
 */
OpenLayers.VERSION_NUMBER="$Revision: 1.13 $";

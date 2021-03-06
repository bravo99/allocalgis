/* Copyright (c) 2006-2007 MetaCarta, Inc., published under the BSD license.
 * See http://svn.openlayers.org/trunk/openlayers/release-license.txt 
 * for the full text of the license. */


/**
 * @requires OpenLayers/Popup.js
 * 
 * Class: OpenLayers.Popup.AnchoredLocalgis
 * 
 * Inherits from:
 *  - <OpenLayers.PopupLocalgis>
 */
OpenLayers.Popup.AnchoredLocalgis = 
  OpenLayers.Class(OpenLayers.PopupLocalgis, {

    /** 
     * Parameter: relativePosition
     * {String} Relative position of the popup ("lr", "ll", "tr", or "tl").
     */
    relativePosition: null,

    /**
     * Parameter: anchor
     * {Object} Object to which we'll anchor the popup. Must expose a 
     *     'size' (<OpenLayers.Size>) and 'offset' (<OpenLayers.Pixel>).
     */
    anchor: null,

    /** 
    * Constructor: OpenLayers.Popup.AnchoredLocalgis
    * 
    * Parameters:
    * id - {String}
    * lonlat - {<OpenLayers.LonLat>}
    * size - {<OpenLayers.Size>}
    * contentHTML - {String}
    * anchor - {Object} Object which must expose a 'size' <OpenLayers.Size> 
    *     and 'offset' <OpenLayers.Pixel> (generally an <OpenLayers.Icon>).
    * closeBox - {Boolean}
    */
    initialize:function(id, lonlat, size, contentHTML, anchor, closeBox) {
        var newArguments = new Array(id, lonlat, size, contentHTML, closeBox);
        OpenLayers.PopupLocalgis.prototype.initialize.apply(this, newArguments);

        this.anchor = (anchor != null) ? anchor 
                                       : { size: new OpenLayers.Size(0,0),
                                           offset: new OpenLayers.Pixel(0,0)};
    },

    /** 
     * Method: draw
     * 
     * Parameters:
     * px - {<OpenLayers.Pixel>}
     * 
     * Returns: 
     * {DOMElement} Reference to a div that contains the drawn popup.
     */
    draw: function(px) {
        if (px == null) {
            if ((this.lonlat != null) && (this.map != null)) {
                px = this.map.getLayerPxFromLonLat(this.lonlat);
            }
        }
        
        //calculate relative position
        this.relativePosition = this.calculateRelativePosition(px);
        
        return OpenLayers.PopupLocalgis.prototype.draw.apply(this, arguments);
    },
    
    /** 
     * Method: calculateRelativePosition
     * 
     * Parameters:
     * px - {<OpenLayers.Pixel>}
     * 
     * Returns:
     * {String} The relative position ("br" "tr" "tl "bl") at which the popup
     *     should be placed.
     */
    calculateRelativePosition:function(px) {
        var lonlat = this.map.getLonLatFromLayerPx(px);        
        
        var extent = this.map.getExtent();
        var quadrant = extent.determineQuadrant(lonlat);
        
        return OpenLayers.Bounds.oppositeQuadrant(quadrant);
    }, 

    /**
     * Method: moveTo
     * Since the popup is moving to a new px, it might need also to be moved
     *     relative to where the marker is.
     * 
     * Parameters:
     * px - {<OpenLayers.Pixel>}
     */
    moveTo: function(px) {
        this.relativePosition = this.calculateRelativePosition(px);
        
        var newPx = this.calculateNewPx(px);
        
        var newArguments = new Array(newPx);        
        OpenLayers.PopupLocalgis.prototype.moveTo.apply(this, newArguments);
    },
    
    /**
     * Method: setSize
     * 
     * Parameters:
     * size - {<OpenLayers.Size>}
     */
    setSize:function(size) { 
        OpenLayers.PopupLocalgis.prototype.setSize.apply(this, arguments);

        if ((this.lonlat) && (this.map)) {
            var px = this.map.getLayerPxFromLonLat(this.lonlat);
            this.moveTo(px);
        }
    },  
    
    /** 
     * Method: calculateNewPx
     * 
     * Parameters:
     * px - {<OpenLayers.Pixel>}
     * 
     * Returns:
     * {<OpenLayers.Pixel>} The the new px position of the popup on the screen
     *     relative to the passed-in px.
     */
    calculateNewPx:function(px) {
        var newPx = px.offset(this.anchor.offset);

        var top = (this.relativePosition.charAt(0) == 't');
        newPx.y += (top) ? -this.size.h : this.anchor.size.h;
        
        var left = (this.relativePosition.charAt(1) == 'l');
        newPx.x += (left) ? -this.size.w : this.anchor.size.w;

        return newPx;   
    },

    CLASS_NAME: "OpenLayers.Popup.AnchoredLocalgis"
});

/*----------------    FILE HEADER  ------------------------------------------

This file is part of deegree.
Copyright (C) 2001 by:
EXSE, Department of Geography, University of Bonn
http://www.giub.uni-bonn.de/exse/
lat/lon Fitzke/Fretter/Poth GbR
http://www.lat-lon.de

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

Contact:

Andreas Poth
lat/lon Fitzke/Fretter/Poth GbR
Meckenheimer Allee 176
53115 Bonn
Germany
E-Mail: poth@lat-lon.de

Jens Fitzke
Department of Geography
University of Bonn
Meckenheimer Allee 166
53115 Bonn
Germany
E-Mail: jens.fitzke@uni-bonn.de

                 
 ---------------------------------------------------------------------------*/
package org.deegree_impl.services.wfs.protocol;

import java.util.HashMap;

import org.deegree.services.wfs.protocol.WFSBasicRequest;
import org.deegree.services.wfs.protocol.WFSNative;
import org.deegree_impl.services.OGCWebServiceRequest_Impl;


/**
*  The Interface definies the basic WFS request.
*
* <p>--------------------------------------------------------</p>
*
* @author Andreas Poth <a href="mailto:k.lupp@web.de>Katharina Lupp</a>
* @version $Revision: 1.1 $ $Date: 2009/07/09 07:25:33 $
*/
class WFSBasicRequest_Impl extends OGCWebServiceRequest_Impl implements WFSBasicRequest {
    private WFSNative native_ = null;

    /**
    * constructor initializing the class with the requests
    */
    WFSBasicRequest_Impl(String version, String request, String id, HashMap vendorSpecificParameter, 
                         WFSNative native_) {
        super(request, "WFS", version, id, vendorSpecificParameter);
        setNative(native_);
    }

    /**
     * The <Native> element is intended to allow access to vendor 
     * specific capabilities of any particular web feature server or datastore.
     * The <Native> tag simply delimits the vendor specific command or operation.
     */
    public WFSNative getNative() {
        return native_;
    }

    /**
    * sets the <Native>
    */
    public void setNative(WFSNative native_) {
        this.native_ = native_;
    }
    
    /** exports the request in its XML representation
     *
     */
    public String exportAsXML() {
        throw new NoSuchMethodError( "exportAsXML" );
    }
    
} 

/*
 * Changes to this class. What the people haven been up to:
 *
 * $Log: WFSBasicRequest_Impl.java,v $
 * Revision 1.1  2009/07/09 07:25:33  miriamperez
 * Rama �nica LocalGISDOS
 *
 * Revision 1.1  2009/04/13 10:16:50  miriamperez
 * Meto en LocalGISPrivado el m�dulo del geocoder al completo
 *
 * Revision 1.1  2006/08/31 11:15:30  angeles
 * *** empty log message ***
 *
 * Revision 1.1.1.1  2006/08/17 11:34:01  angeles
 * no message
 *
 * Revision 1.5  2004/01/26 08:10:37  poth
 * no message
 *
 * Revision 1.4  2003/05/05 15:52:50  poth
 * no message
 *
 * Revision 1.3  2003/04/10 07:32:35  poth
 * no message
 *
 * Revision 1.2  2003/04/07 07:26:53  poth
 * no message
 *
 * Revision 1.1.1.1  2002/09/25 16:01:24  poth
 * no message
 *
 * Revision 1.7  2002/08/15 10:01:40  ap
 * no message
 *
 * Revision 1.6  2002/08/09 15:36:30  ap
 * no message
 *
 * Revision 1.5  2002/05/14 14:39:51  ap
 * no message
 *
 * Revision 1.4  2002/05/13 16:11:02  ap
 * no message
 *
 * Revision 1.3  2002/04/26 09:05:36  ap
 * no message
 *
 * Revision 1.1  2002/04/04 16:17:15  ap
 * no message
 *
 */
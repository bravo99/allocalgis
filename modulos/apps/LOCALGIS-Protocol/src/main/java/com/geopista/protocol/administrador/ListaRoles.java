/**
 * ListaRoles.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.protocol.administrador;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


/**
 * Created by IntelliJ IDEA.
 * User: angeles
 * Date: 26-may-2004
 * Time: 11:08:57
 */
public class ListaRoles {
    private Hashtable hRoles;
    public  ListaRoles()
    {
          this.hRoles = new Hashtable();
    }
    public void add(Rol rol) {
        this.hRoles.put(rol.getId(),rol);
    }

    public Rol get(String sIdRol)
    {
        return (Rol)this.hRoles.get(sIdRol);
    }
    public void remove(Rol rolEliminado)
    {
        this.hRoles.remove(rolEliminado.getId());
    }
    public void set(Vector vRoles)
    {
        if (vRoles==null) return;
        for (Enumeration e=vRoles.elements();e.hasMoreElements();)
        {
            Rol auxRol=(Rol)e.nextElement();
            add(auxRol);
        }
    }

    public Hashtable gethRoles() {
        return hRoles;
    }
    
}

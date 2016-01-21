package com.geopista.protocol.licencias;

import java.util.Date;

/**
 * The GEOPISTA project is a set of tools and applications to manage
 * geographical data for local administrations.
 *
 * Copyright (C) 2004 INZAMAC-SATEC UTE
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,
 USA.
 *
 * For more information, contact:
 *
 *
 * www.geopista.com
 *
 *
 */


/**
 * Created by IntelliJ IDEA.
 * User: angeles
 * Date: 27-jun-2005
 * Time: 15:53:58
 */
public class Parametro {
    String tipo;
    java.util.Date valorDate;
    String valorString;

    public Parametro() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTipoDate()
    {
         tipo="Date";
    }

    public void setTipoString()
    {
         tipo="String";
    }
    public boolean isTipoDate()
    {
        return (tipo!=null&&tipo.equals("Date"));
    }

    public boolean isTipoString()
    {
       return (tipo!=null&&tipo.equals("String"));
    }

    public Object getValor() {
        if (tipo!=null && tipo.equals("Date"))
            return valorDate;
        if (tipo!=null && tipo.equals("String"))
            return valorString;
        return null;
    }

     public void setValorDate(java.util.Date date) {
        setTipoDate();
        valorDate=date;
    }
    public void setValorString(String string) {
        setTipoString();
        valorString=string;
    }
    public String getValorString()
    {
        return valorString;
    }
    public Date getValorDate()
    {
        return valorDate;
    }

}
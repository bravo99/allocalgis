/*
 * El presente software ha sido desarrollado por Balidea Consulting & Programming, con control de calidad y previo dise�o por Enxenio S.L., para la Diputaci�n de A Coru�a en el seno del proyecto LOURED (http://loured.es), inclu�do en el Plan AVANZA Local 2009-2011 del Ministerio de Industria, Energ�a y Turismo del Gobierno de Espa�a.  
 * Su distribuci�n se realiza bajo las condiciones establecidas por la licencia European Public License (EUPL), versi�n 1.1 o posteriores (http://http://joinup.ec.europa.eu/software/page/eupl/licence-eupl)
 */
package es.dc.a21l.fuente.cu;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AtributoValoresDto implements Serializable {
	
	private ValorFDDto listaValores;
	private AtributoHistoricoDto atributo;
	
	
	public ValorFDDto getListaValores() {
		return listaValores;
	}
	public void setListaValores(ValorFDDto listaValores) {
		this.listaValores = listaValores;
	}
	public AtributoHistoricoDto getAtributo() {
		return atributo;
	}
	public void setAtributo(AtributoHistoricoDto atributo) {
		this.atributo = atributo;
	}

}
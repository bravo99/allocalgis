/*
 * El presente software ha sido desarrollado por Balidea Consulting & Programming, con control de calidad y previo dise�o por Enxenio S.L., para la Diputaci�n de A Coru�a en el seno del proyecto LOURED (http://loured.es), inclu�do en el Plan AVANZA Local 2009-2011 del Ministerio de Industria, Energ�a y Turismo del Gobierno de Espa�a.  
 * Su distribuci�n se realiza bajo las condiciones establecidas por la licencia European Public License (EUPL), versi�n 1.1 o posteriores (http://http://joinup.ec.europa.eu/software/page/eupl/licence-eupl)
 */
package es.dc.a21l.usuario.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import es.dc.a21l.base.modelo.EntidadBase;

@Entity
@Table(name = "Tb_A21l_Rol")
public class Rol extends EntidadBase {
	
	@Column(name="Nombre",nullable=false)
	private String nombre;
	
	@Column(name="Descripcion")
	private String descripcion;
	
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="rol",orphanRemoval=true)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<UsuarioRol> usuarioRols= new HashSet<UsuarioRol>();
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="rol",orphanRemoval=true)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<RolElementoJerarquia> rolElementoJerarquias= new HashSet<RolElementoJerarquia>();
    
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<UsuarioRol> getUsuarioRols() {
		return usuarioRols;
	}
	public void setUsuarioRols(Set<UsuarioRol> usuarioRols) {
		this.usuarioRols = usuarioRols;
	}
	public Set<RolElementoJerarquia> getRolElementoJerarquias() {
		return rolElementoJerarquias;
	}
	public void setRolElementoJerarquias(
			Set<RolElementoJerarquia> rolElementoJerarquias) {
		this.rolElementoJerarquias = rolElementoJerarquias;
	}
}

/**
 * EntidadTipoMapDAO.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.localgis.web.core.dao;

import com.localgis.web.core.model.EntidadTipoMap;

/*
 * @Author dcaaveiro
 */
public interface EntidadTipoMapDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidad_tipo_map
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	int deleteByPrimaryKey(EntidadTipoMap key);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidad_tipo_map
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	void insert(EntidadTipoMap record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidad_tipo_map
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	void insertSelective(EntidadTipoMap record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidad_tipo_map
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	EntidadTipoMap selectByPrimaryKey(EntidadTipoMap key);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidad_tipo_map
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	int updateByPrimaryKeySelective(EntidadTipoMap record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidad_tipo_map
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	int updateByPrimaryKey(EntidadTipoMap record);
}
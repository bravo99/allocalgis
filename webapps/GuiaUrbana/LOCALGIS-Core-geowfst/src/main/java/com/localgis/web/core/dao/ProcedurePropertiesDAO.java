/**
 * ProcedurePropertiesDAO.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.localgis.web.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.localgis.web.core.model.ProcedureProperty;
import com.localgis.web.core.model.ProcedurePropertyKey;

public interface ProcedurePropertiesDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
    void deleteByPrimaryKey(ProcedurePropertyKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
	void deleteByPrimaryKeyId(ProcedurePropertyKey procedurePropertyKey);
	
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
    void insert(ProcedureProperty record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
    void insertSelective(ProcedureProperty record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
    ProcedureProperty selectByPrimaryKey(ProcedurePropertyKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
    List<ProcedureProperty> selectByProcedureId(String procedureId);
    
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
    HashMap<String, ProcedureProperty> selectByProcedureIdMap(String procedureId);
    
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
    void updateByPrimaryKeySelective(ProcedureProperty record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table geowfst.procedure_properties
     *
     * @ibatorgenerated Thu Mar 14 10:24:30 CET 2013
     */
    void updateByPrimaryKey(ProcedureProperty record);
}
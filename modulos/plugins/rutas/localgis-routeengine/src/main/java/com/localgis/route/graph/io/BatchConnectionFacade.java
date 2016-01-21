/**
 * BatchConnectionFacade.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.localgis.route.graph.io;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
/**
 * Experimental. Intento de agrupar queries en Geopistaconnections
 * 
 * Intercepta las llamadas a {@link #prepareStatement(String)} y las agrupa
 * en queries m�ltiples.
 * @author juacas
 *
 */
public class BatchConnectionFacade implements Connection
{

    private Connection connection;
    private BatchStatementSupport batchSupport;
/**
 * Utiliza un buffer de queries en los {@link PreparedStatement} que se ejecutan por lotes o AL CERRAR la conexi�n
 * 
 * @param connection
 * @param size tamaño del buffer de consultas enviadas a GeopistaConnection
 */
    public BatchConnectionFacade(Connection connection,int size)
    {
	this.connection=(Connection) connection;
	this.batchSupport=new BatchStatementSupport((Connection) connection, size, 14000);
    }


/**
 * commit pending queries
 * @throws SQLException 
 */
    public void close() throws SQLException
    {
	this.commit();
	this.connection.close();
    }
    /**
     * devuelve un statement con agrupaci�n de queries
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException
    {
	this.batchSupport.addBatch(sql);
        return (PreparedStatement) this.batchSupport;
    }
public <T> T unwrap(Class<T> iface) throws SQLException
{
    return connection.unwrap(iface);
}

public boolean isWrapperFor(Class<?> iface) throws SQLException
{
    return connection.isWrapperFor(iface);
}

public Statement createStatement() throws SQLException
{
    return connection.createStatement();
}



public CallableStatement prepareCall(String sql) throws SQLException
{
    return connection.prepareCall(sql);
}

public String nativeSQL(String sql) throws SQLException
{
    return connection.nativeSQL(sql);
}

public void setAutoCommit(boolean autoCommit) throws SQLException
{
    connection.setAutoCommit(autoCommit);
}

public boolean getAutoCommit() throws SQLException
{
    return connection.getAutoCommit();
}

public void commit() throws SQLException
{
   this.batchSupport.executeUpdateNow();
}

public void rollback() throws SQLException
{
    connection.rollback();
}

public boolean isClosed() throws SQLException
{
    return connection.isClosed();
}

public DatabaseMetaData getMetaData() throws SQLException
{
    return connection.getMetaData();
}

public void setReadOnly(boolean readOnly) throws SQLException
{
    connection.setReadOnly(readOnly);
}

public boolean isReadOnly() throws SQLException
{
    return connection.isReadOnly();
}

public void setCatalog(String catalog) throws SQLException
{
    connection.setCatalog(catalog);
}

public String getCatalog() throws SQLException
{
    return connection.getCatalog();
}

public void setTransactionIsolation(int level) throws SQLException
{
    connection.setTransactionIsolation(level);
}

public int getTransactionIsolation() throws SQLException
{
    return connection.getTransactionIsolation();
}

public SQLWarning getWarnings() throws SQLException
{
    return connection.getWarnings();
}

public void clearWarnings() throws SQLException
{
    connection.clearWarnings();
}

public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
{
    return connection.createStatement(resultSetType, resultSetConcurrency);
}

public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
{
    return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
}

public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
{
    return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
}

public Map<String, Class<?>> getTypeMap() throws SQLException
{
    return connection.getTypeMap();
}

public void setTypeMap(Map<String, Class<?>> map) throws SQLException
{
    connection.setTypeMap(map);
}

public void setHoldability(int holdability) throws SQLException
{
    connection.setHoldability(holdability);
}

public int getHoldability() throws SQLException
{
    return connection.getHoldability();
}

public Savepoint setSavepoint() throws SQLException
{
    return connection.setSavepoint();
}

public Savepoint setSavepoint(String name) throws SQLException
{
    return connection.setSavepoint(name);
}

public void rollback(Savepoint savepoint) throws SQLException
{
    connection.rollback(savepoint);
}

public void releaseSavepoint(Savepoint savepoint) throws SQLException
{
    connection.releaseSavepoint(savepoint);
}

public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
{
    return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
}

public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
{
    return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
}

public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
{
    return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
}

public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException
{
    return connection.prepareStatement(sql, autoGeneratedKeys);
}

public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException
{
    return connection.prepareStatement(sql, columnIndexes);
}

public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException
{
    return connection.prepareStatement(sql, columnNames);
}

public Clob createClob() throws SQLException
{
    return connection.createClob();
}

public Blob createBlob() throws SQLException
{
    return connection.createBlob();
}

public NClob createNClob() throws SQLException
{
    return connection.createNClob();
}

public SQLXML createSQLXML() throws SQLException
{
    return connection.createSQLXML();
}

public boolean isValid(int timeout) throws SQLException
{
    return connection.isValid(timeout);
}

public void setClientInfo(String name, String value) throws SQLClientInfoException
{
    connection.setClientInfo(name, value);
}

public void setClientInfo(Properties properties) throws SQLClientInfoException
{
    connection.setClientInfo(properties);
}

public String getClientInfo(String name) throws SQLException
{
    return connection.getClientInfo(name);
}

public Properties getClientInfo() throws SQLException
{
    return connection.getClientInfo();
}

public Array createArrayOf(String typeName, Object[] elements) throws SQLException
{
    return connection.createArrayOf(typeName, elements);
}

public Struct createStruct(String typeName, Object[] attributes) throws SQLException
{
    return connection.createStruct(typeName, attributes);
}


@Override
public void setSchema(String schema) throws SQLException {
	// TODO Auto-generated method stub
	
}


@Override
public String getSchema() throws SQLException {
	// TODO Auto-generated method stub
	return connection.getSchema();
}


@Override
public void abort(Executor executor) throws SQLException {
	// TODO Auto-generated method stub
	
}


@Override
public void setNetworkTimeout(Executor executor, int milliseconds)
		throws SQLException {
	// TODO Auto-generated method stub
	
}


@Override
public int getNetworkTimeout() throws SQLException {
	// TODO Auto-generated method stub
	return 0;
}

}
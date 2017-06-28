package com.firm.derbyproject.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GenericCRUDOperation {
	
	private static final Logger logger = Logger.getLogger(GenericCRUDOperation.class);
	private DerbyDBManager derbyManager;
	
	public GenericCRUDOperation(){
		this.derbyManager = new DerbyDBManager();
	}
	
	
	public List retrieveData(String sqlQuery){
		List lstResult = new ArrayList();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		logger.info("Query sql: "+sqlQuery);
		try{
			conn = this.derbyManager.getConnection();
			statement = conn.prepareStatement(sqlQuery);
			rs = statement.executeQuery();
			Integer numberColumn = rs.getMetaData().getColumnCount();
			while (rs.next()){
				Object[] row = new Object[numberColumn];
				int j=0;
				for (int i=1;i<=numberColumn;i++){
					row[j] = rs.getObject(i);
					j++;
				}
				lstResult.add(row);
			}
		}catch(SQLException e){
			logger.error(e.getMessage());
		}finally{
			try{
				if (rs!=null)
					rs.close();
				if (statement!=null)
					statement.close();	
				if (conn!=null)
					conn.close();
			}catch(SQLException e){
				logger.error("Error connection close!");
			}
		}
		return lstResult;
	}
	
	public void modifyDataOrTable(String sql){
		Connection conn = null;
		PreparedStatement st = null;
		try{
			conn = this.derbyManager.getConnection();
			st = conn.prepareStatement(sql);
			logger.info("query sql: "+sql);
			st.executeUpdate();
		}catch(SQLException e){
			logger.error(e.getMessage());
		}finally{
			try{
				if (st!=null)
					st.close();
				if (conn!=null)
					conn.close();
			}catch(SQLException e){
				logger.error("Error connection close!");
			}
		}
	}

}

package com.impetus.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DBUtil {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		final Connection connection = DBCPDataSourceFactory.getInstance().getConnection();
		return connection;
	}
	
	public static String insert(final String tableName, final List stdColumns) {
		//log.trace("Inside insert() : tableName : {} ,  stdColumns {} ", tableName, stdColumns);
		final List allColumns = new ArrayList();
		final StringBuffer buf = new StringBuffer("INSERT INTO ").append(tableName).append(" ( ");

		allColumns.addAll(stdColumns);
		//String test= StringUtils.join(stdColumns, ',');

		for (int i = 0; i < allColumns.size(); i++) {
			if (i > 0) {
				buf.append(", ");
			}

			buf.append(allColumns.get(i));
		}

		buf.append(" ) VALUES (").append(getQuestionMarks(allColumns.size()));

		System.out.println("Inside insert() : Insert query is  " +buf);
		//log.trace("Inside insert() : Insert query is  ", buf);
		return buf.append(")").toString();
	}
	
	protected static String getQuestionMarks(final int size) {
		final StringBuffer buf = new StringBuffer();

		for (int i = 0; i < size; i++) {
			if (i > 0) {
				buf.append(", "); //$NON-NLS-1$
			}

			buf.append("?"); //$NON-NLS-1$
		}

		return buf.toString();
	}
	
	
	public static void close(final Statement stmt, final ResultSet res, final Connection connection)  {
		try {
			if (res != null) {
				res.close();
			}
		} catch (final SQLException ex) {
			System.out.println("Exception" +ex.getStackTrace());
			//log.error("SQLException occured while closing connection ", ex);
			//throw new DAOException(ErrorCode.DB, ex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (final SQLException ex) {
				System.out.println("Exception" +ex.getStackTrace());
				//log.error("SQLException occured while closing statement ", ex);
				//throw new DAOException(ErrorCode.DB, ex);
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException ex) {
				System.out.println("Exception" +ex.getStackTrace());
				//log.error("SQLException occured while closing connection ", ex);
				//throw new DAOException(ErrorCode.DB, ex);
			}

		}
	}
	
	public static void bind(final PreparedStatement ps, final int pos, final String val) {
		try {
			if (null == val) {
				ps.setNull(pos, Types.VARCHAR);
			} else {
				ps.setString(pos, val);
			}

		} catch (final SQLException ex) {
			System.out.println("Exception occured while binding prepared statement with the values" +ex.getMessage());
			//log.error("Exception occured while binding prepared statement with the values", ex); //$NON-NLS-1$
			//throw new DAOException(ErrorCode.DB, ex);
		}
	}
	
	public static void bind(final PreparedStatement ps, final int pos, final Date val) {
		try {
			if (null == val) {
				ps.setNull(pos, Types.TIMESTAMP);
			} else {
				ps.setTimestamp(pos, new java.sql.Timestamp(val.getTime()));
			}
		} catch (final SQLException ex) {
			//log.error("Exception occured while binding prepared statement with the values", ex); //$NON-NLS-1$
			//throw new DAOException(ErrorCode.DB, ex);
		}
	}
}

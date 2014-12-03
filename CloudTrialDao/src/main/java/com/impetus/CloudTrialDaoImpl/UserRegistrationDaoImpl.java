package com.impetus.CloudTrialDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.impetus.CloudTrialDao.UserRegistrationDao;
import com.impetus.Util.DBUtil;
import com.impetus.model.UserBean;

public class UserRegistrationDaoImpl implements UserRegistrationDao{
	
	protected final static String tableName = "REGISTRATION_DETAILS";
	
	protected final static List<String> stdColumns = new ArrayList<String>();
	
	@Override
	public String toString() {
		return "UserRegistrationDaoImpl [toString()=" + super.toString() + "]";
	}

	static {
		stdColumns.add("EMAIL"); //$NON-NLS-1$
		stdColumns.add("TYPE"); //$NON-NLS-1$
		stdColumns.add("START_DATE"); //$NON-NLS-1$
		stdColumns.add("TERM");//$NON-NLS-1$
		stdColumns.add("REGION");//$NON-NLS-1$
		stdColumns.add("SOURCE");//$NON-NLS-1$
	}

	public int registerUser(UserBean user) {
		PreparedStatement ps = null;
		Connection connection = null;
		final int pos = 1;
		int rowCount = 0;

		try {
			connection = DBUtil.getConnection();
			ps = connection.prepareStatement(DBUtil.insert(tableName, stdColumns), Statement.RETURN_GENERATED_KEYS);
			this.bindStdColumns(ps, user, pos);

			rowCount = ps.executeUpdate();

			if (rowCount != 1) {
				//throw new Exception(ErrorCode.DB_INSERT, "Error inserting " + obj.getClass() + " in " + tableName
					//	+ ", affected rows = " + rowCount);
				
				System.out.println("rowCount-->"+rowCount);
			}

			final ResultSet rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				rowCount = rs.getInt(1);
			}

		} catch (final Exception e) {
			//log.error("Exception occured while inserting the record in Job table ", e);
			//throw new Exception(ErrorCode.DB, e);
			System.out.println("EXception" +e.getMessage());
		} finally {
			DBUtil.close(ps, null, connection);
		}
		//LogUtil.audit("Created new Job[{}] for user[{}]", rowCount, obj.getUserName());
		return rowCount;
	
	}
	protected int bindStdColumns(final PreparedStatement ps, final UserBean obj, int pos) {
		System.out.println("Inside  bindStdColumns() : Job : {}" +obj.toString());
		DBUtil.bind(ps, pos++, obj.getEmail());
		DBUtil.bind(ps, pos++, obj.getType());
		DBUtil.bind(ps, pos++, obj.getStartDate());
		DBUtil.bind(ps, pos++,  obj.getTerm());
		DBUtil.bind(ps, pos++, obj.getRegion());
		DBUtil.bind(ps, pos++, obj.getSource());
		
		
	
		return pos;
	}

}

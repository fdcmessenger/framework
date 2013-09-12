package org.sbbs.base.dao.hibernate;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.sbbs.base.dao.GeneralDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GeneralDAOImpl;

@Repository("generalDao")
public class GeneralDaoImpl extends GeneralDAOImpl implements GeneralDao {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	private HibernateTemplate hibernateTemplate;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public <T> boolean exists(Class<T> type, Serializable id) {
		T entity = this.find(type, id);
		return entity != null;
	}

	/**
	 * 如果目标数据库为SqlServer，务必注意在所有的存储过程开始都加入如下命令： SET NOCOUNT ON;(返回结果集问题) set
	 * ARITHABORT ON;(执行速度慢的问题)
	 */
	public CallReturn procedureCall(String procName, Object[] args) {

		String retMsg = "";
		int retCode = 0;
		CallReturn callReturn = new CallReturn();
		Connection con = null;
		try {
			con = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();

			//con.createStatement().execute("SET ARITHABORT ON");

			CallableStatement callStat = this.prepareCall(con, procName, args);
			callStat.executeUpdate();
			retCode = callStat.getInt(1);
			retMsg = callStat.getString(args.length + 2);
			callReturn.setReturnCode(retCode);
			//con.createStatement().execute("SET ARITHABORT OFF");

		} catch (Exception ex) {
			ex.printStackTrace();
			retMsg = ex.getMessage();
			callReturn.setReturnCode(300);

		} finally {

		}
		if (retMsg == null || retMsg.trim().equals("")) {
			if ((retCode != 100) && (retCode != 0))
				retMsg = "处理失败。";
			else
				retMsg = "处理成功。";
		}
		callReturn.setReturnMessage(retMsg);
		return callReturn;

	}

	public List callQuery(String procName, Object[] args) {
		String retMsg = "";
		int retCode = 0;
		ResultSet rs = null;
		try {
			Connection con = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			CallableStatement callStat = this.prepareCall(con, procName, args);
			callStat.executeQuery();
			rs = callStat.getResultSet();
			UpperCaseColumnMapRowMapper rm = new UpperCaseColumnMapRowMapper();
			ResultSetExtractor rse = new RowMapperResultSetExtractor(rm);
			return (List) rse.extractData(rs);

		} catch (Exception ex) {
			ex.printStackTrace();
			retMsg = ex.getMessage();
			return new ArrayList();

		} finally {
			JdbcUtils.closeResultSet(rs);
		}
	}

	private CallableStatement prepareCall(Connection con, String procName, Object[] args) throws SQLException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			sb.append("?");
			sb.append(",");
		}
		String callString = "{call " + procName + "(" + sb.toString() + "?,?)}";

		CallableStatement callStat = null;
		callStat = con.prepareCall(callString);
		int j = 1;
		for (int k = 0; k < args.length; k++) {
			callStat.setObject(j + 1, args[k]);
			j++;
		}
		callStat.registerOutParameter(1, Types.INTEGER);
		callStat.registerOutParameter(j + 1, Types.VARCHAR);

		return callStat;
	}
}

package shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseRepository implements Repository {
	
	private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private final String protocol = "jdbc:derby:";
	private final String dbName = "shopdb";
	private Connection conn;
	
	public DatabaseRepository() throws Exception {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(protocol + dbName); 
	}

	@Override
	public Seller findById(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Seller seller = null;
		
		try {
			String query = "select id, name, email from seller where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if ( ! rs.next() ) {
				throw new SQLException("No Data Fount!");
			}
			seller = new Seller(rs.getString(1), rs.getString(2), rs.getString(3));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return seller;
	}

	@Override
	public void add(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Seller seller) {
		// TODO Auto-generated method stub

	}

}

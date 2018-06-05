import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class Form implements Rowable {
	static final String DB_URL = "jdbc:mysql://localhost:3306/form_builder?useUnicode=yes&characterEncoding=UTF-8";
	static final String USER = "root";
	static final String PASS = "";

	private int id;
	private String name;
	private int sn; // submitions number

	public Form(int id, String name, int sn) {
		this.id = id;
		this.name = name;
		this.sn = sn;
	}

	public Form(int id, String name) {
		this(id, name, 0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getSn() {
		return sn;
	}

	@Override
	/*
	 * @return : HTML code of current form
	 */
	public String generateRow() {
		String result = "<tr class=\"t_object\">";

		result = result + "<td>" + getId() + "</td>\n";
		result = result + "<td>" + getName() + "</td>\n";
		result = result + "<td>" + getSn() + "</td>\n";
		result = result + "<td> <a href=\"SubmitForm?fid=" + id + "\">view</a> </td>\n";
		result = result + "<td> <a href=\"FormSubmissions?fid=" + id + "\">view</a> </td>\n";

		return result + "</tr>";
	}

	/*
	 * @return : form id after database insertion
	 */
	public int insert() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "INSERT INTO forms VALUES('',?,0)";

			stmt = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, name);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating forms failed, no rows affected.");
			}

			try (ResultSet generatedKeys = (ResultSet) stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return (int) generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating form failed, no ID obtained.");
				}
			}

		} catch (SQLException se) {
			System.out.println("JDBC BUG!!!");
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return -1;
	}

	/*
	 * @return : list of the forms executed form database
	 */
	public static LinkedList<Form> getForms() {
		LinkedList<Form> res = new LinkedList<Form>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "SELECT * FROM forms ORDER BY id ASC";
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ((java.sql.Statement) stmt).executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int sn = rs.getInt("sn");
				Form f = new Form(id, name, sn);
				res.add(f);
			}

			rs.close();
		} catch (SQLException se) {
			System.out.println("JDBC BUG!!!");
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return res;
	}

	/*
	 * @do: increase form submitions number in db
	 */
	public void increaseSN() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "UPDATE forms set sn=sn+1 WHERE id=" + id;

			stmt = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating forms failed, no rows affected.");
			}

			try (ResultSet generatedKeys = (ResultSet) stmt.getGeneratedKeys()) {
				if (!generatedKeys.next())
					throw new SQLException("Submiting form failed @ increase key, no ID obtained.");

			}

		} catch (SQLException se) {
			System.out.println("JDBC BUG!!!");
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}

}

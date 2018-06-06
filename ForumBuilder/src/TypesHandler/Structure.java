package TypesHandler;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;


public class Structure {

	public CopyOnWriteArrayList<Type> structure;
	public Form father;
	static final String DB_URL = "jdbc:mysql://localhost:3306/form_builder?useUnicode=yes&characterEncoding=UTF-8";
	static final String USER = "root";
	static final String PASS = "";

	public Structure() {
		structure = new CopyOnWriteArrayList<Type>();
	}

	public String getStructure(String property) {
		String result = "";

		for (Type t : structure)
			result = result + t.getHTML(property);

		return result;
	}

	public void restart() {
		structure = new CopyOnWriteArrayList<Type>();
	}

	public void add(Type t) {
		structure.add(t);
	}

	public void delete(int id) {
		for (Type t : structure)
			if (t.id == id) {
				structure.remove(t);
				break;
			}
	}

	/*
	 * @params : 'id' -> component id, 'newVal' -> component new value
	 */
	public void update(int id, String newVal) {
		for (Type t : structure) {
			if (t.getId() == id) {
				t.setResult(newVal);
				break;
			}
		}
	}

	public boolean isLegal() {
		for (Type t : structure)
			if (t.getResult() == null)
				return false;

		return true;
	}

	public Type getType(int qid) {
		Type t = null;
		for (Type ty : structure) {
			if (ty.id == qid) {
				int id = ty.id;
				String lname = ty.labelName;

				switch (ty.getType()) {
				case Type.Text:
					t = new TypeText(id, lname);
					break;
				case Type.Color:
					t = new TypeColor(id, lname);
					break;
				case Type.Date:
					t = new TypeDate(id, lname);
					break;
				case Type.Email:
					t = new TypeEmail(id, lname);
					break;
				case Type.Telephone:
					t = new TypeTel(id, lname);
					break;
				case Type.Number:
					t = new TypeNumber(id, lname);
					break;
				case Type.Paragraph:
					t = new TypeParagraph(id, lname);
					break;
				default:
					return null;
				}

				t.setResult(ty.result);
				break;
			}
		}

		return t;
	}

	public boolean isEmpty() {
		return structure.isEmpty();
	}

	public void publish(int id) {
		int i = 0;
		for (Type t : structure) {
			t.setOrder(i);
			t.insertType(id);
			i++;
		}

	}

	public String[] getTypesHeader() {
		String[] rs = new String[structure.size()];

		int i = 0;

		for (Type t : structure) {
			rs[i] = t.labelName;
			i++;
		}

		return rs;
	}

	public static Structure getStructure(int fid) {

		Structure res = new Structure();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "SELECT components.form_id,components.id,components.type,components.order,components.lable_name,forms.name AS name"
					+ " FROM components INNER JOIN forms ON forms.id=" + fid + " AND components.form_id=" + fid;
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ((java.sql.Statement) stmt).executeQuery(sql);

			while (rs.next()) {
				Type t;
				switch (rs.getInt("type")) {
				case Type.Text:
					t = new TypeText(rs.getInt("id"), rs.getString("lable_name"));
					break;
				case Type.Color:
					t = new TypeColor(rs.getInt("id"), rs.getString("lable_name"));
					break;
				case Type.Date:
					t = new TypeDate(rs.getInt("id"), rs.getString("lable_name"));
					break;
				case Type.Email:
					t = new TypeEmail(rs.getInt("id"), rs.getString("lable_name"));
					break;
				case Type.Telephone:
					t = new TypeTel(rs.getInt("id"), rs.getString("lable_name"));
					break;
				case Type.Number:
					t = new TypeNumber(rs.getInt("id"), rs.getString("lable_name"));
					break;
				case Type.Paragraph:
					t = new TypeParagraph(rs.getInt("id"), rs.getString("lable_name"));
					break;
				default:
					continue;
				}
				t.setOrder(rs.getInt("order"));
				res.add(t);
				res.father = new Form(fid, rs.getString("name"));

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

}

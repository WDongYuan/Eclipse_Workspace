package project;

import java.sql.*;
import java.util.ArrayList;

public class SqlManager {
	private Connection conn = null;
	private Statement stmt;

	public Connection getConnection() {
		Connection con = null; // 创建用于连接数据库的Connection对象
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCT";
		try {
			Class.forName(JDriver);// 加载Mysql数据驱动

			con = DriverManager.getConnection(connectDB, "sa", "yuanweidong");
			System.out.println("Connect successfully");

		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return con; // 返回所建立的数据库连接
	}

	public String getPassword(String id) {
		String pw = new String();
		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select Spassword from Member where S#='" + id + "';"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = stmt.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			// System.out.println("最后的查询结果为：");
			while (rs.next()) { // 判断是否还有下一个数据

				// 根据字段名获取相应的值
				pw = rs.getString("Spassword");

			}
			conn.close(); // 关闭数据库连接

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
		return pw;
	}

	public void query() {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from Student"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = stmt.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			System.out.println("最后的查询结果为：");
			while (rs.next()) { // 判断是否还有下一个数据

				// 根据字段名获取相应的值
				int id = rs.getInt("S#");
				String Sname = rs.getString("Sname");
				int Sage = rs.getInt("Sage");
				String Ssex = rs.getString("Ssex");
				String Dname = rs.getString("Dname");
				String Sclass = rs.getString("Sclass");

				// 输出查到的记录的各个字段的值
				System.out.println(id + " " + Sname + " " + Sage + " " + Ssex
						+ " " + Dname + " " + Sclass);

			}
			conn.close(); // 关闭数据库连接

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
	}

	public void getStuInfo(String id, Student stu) {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from Student where S#='" + id + "';"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = stmt.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			System.out.println("最后的查询结果为：");
			while (rs.next()) { // 判断是否还有下一个数据

				// 根据字段名获取相应的值
				stu.id = rs.getString("S#");
				stu.Sname = rs.getString("Sname");
				stu.Sage = rs.getInt("Sage");
				stu.Ssex = rs.getString("Ssex");
				stu.Dname = rs.getString("Dname");
				stu.Sclass = rs.getString("Sclass");

				// 输出查到的记录的各个字段的值
				// System.out.println(id+" "+Sname + " " + Sage + " " + Ssex +
				// " " + Dname + " " + Sclass);

			}
			conn.close(); // 关闭数据库连接

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
	}
	
	public void updateStudent(String sid,String sname, String ssex, int sage,String dname,String sclass) {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "update Student set Sname='"+sname+"', Ssex='"+ssex+"', Sage='"+sage+"', Dname='"+dname+"', Sclass='"+sclass+"' where S#='"+sid+"';"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			stmt.execute(sql); // 执行sql查询语句，返回查询数据的结果集
			conn.close(); // 关闭数据库连接

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
	}
	
	public void deleteCourse(String cid,String sid) {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "delete from SC where S#='"+sid+"' and C#='"+cid+"';"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			stmt.execute(sql); // 执行sql查询语句，返回查询数据的结果集
			conn.close(); // 关闭数据库连接

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
	}
	
	public void getCourse(String id, ArrayList clist) {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from Course,SC where SC.S#='" + id + "' and SC.C#=Course.C#;"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = stmt.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			System.out.println("最后的查询结果为：");
			while (rs.next()) { // 判断是否还有下一个数据

				Course c=new Course();
				// 根据字段名获取相应的值
				c.Cid = rs.getString("C#");
				c.Chours = rs.getInt("Chours");
				c.Cname = rs.getString("Cname");
				c.Credit = rs.getInt("Credit");
				c.Csemster = rs.getInt("Csemster");
				clist.add(c);
				// 输出查到的记录的各个字段的值
				// System.out.println(id+" "+Sname + " " + Sage + " " + Ssex +
				// " " + Dname + " " + Sclass);

			}
			conn.close(); // 关闭数据库连接

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
	}
	public void getAllCourse(ArrayList clist) {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from Course;"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = stmt.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			System.out.println("最后的查询结果为：");
			while (rs.next()) { // 判断是否还有下一个数据

				Course c=new Course();
				// 根据字段名获取相应的值
				c.Cid = rs.getString("C#");
				c.Chours = rs.getInt("Chours");
				c.Cname = rs.getString("Cname");
				c.Credit = rs.getInt("Credit");
				c.Csemster = rs.getInt("Csemster");
				clist.add(c);
				// 输出查到的记录的各个字段的值
				// System.out.println(id+" "+Sname + " " + Sage + " " + Ssex +
				// " " + Dname + " " + Sclass);

			}
			conn.close(); // 关闭数据库连接

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
	}
	public void ShowScore(String Sid,ArrayList clist) {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from Course,SC where SC.C#=Course.C# and SC.S#='"+Sid+"';"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = stmt.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			System.out.println("最后的查询结果为：");
			while (rs.next()) { // 判断是否还有下一个数据

				Course c=new Course();
				// 根据字段名获取相应的值
				c.Cid = rs.getString("C#");
				c.Chours = rs.getInt("Chours");
				c.Cname = rs.getString("Cname");
				c.Credit = rs.getInt("Credit");
				c.Csemster = rs.getInt("Csemster");
				c.Score=rs.getDouble("Score");
				clist.add(c);
				// 输出查到的记录的各个字段的值
				// System.out.println(id+" "+Sname + " " + Sage + " " + Ssex +
				// " " + Dname + " " + Sclass);

			}
			conn.close(); // 关闭数据库连接

		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
	}
	public boolean selectCourse(Course c,String Sid) {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from Course,SC where SC.S#='" + Sid + "' and SC.C#=Course.C# and Course.C#='"+c.Cid+"';"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = stmt.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			System.out.println("最后的查询结果为：");
			while (rs.next()){
				return false;
			}
			
			sql = "insert into SC (S#,C#) values ('"+Sid+"','"+c.Cid+"');"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			stmt.execute(sql); // 执行sql查询语句，返回查询数据的结果集
			conn.close(); // 关闭数据库连接
			return true;
		} catch (SQLException e) {
			System.out.println("操作失败");
			return false;
		}
	}
	
	
	public boolean register(String sid,String pw) {

		conn = getConnection(); // 同样先要获取连接，即连接到数据库
		try {
			String sql = "select * from Member where S#='" + sid +"';"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			ResultSet rs = stmt.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
			//System.out.println("最后的查询结果为：");
			while (rs.next()){
				return false;
			}
			
			sql = "insert into Member (S#,Spassword) values ('"+sid+"','"+pw+"');"; // 查询数据的sql语句
			stmt = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量

			stmt.execute(sql); // 执行sql查询语句，返回查询数据的结果集
			conn.close(); // 关闭数据库连接
			return true;
		} catch (SQLException e) {
			System.out.println("操作失败");
			return false;
		}
	}
}

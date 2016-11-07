package project;

import java.sql.*;
import java.util.ArrayList;

public class SqlManager {
	private Connection conn = null;
	private Statement stmt;

	public Connection getConnection() {
		Connection con = null; // ���������������ݿ��Connection����
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SCT";
		try {
			Class.forName(JDriver);// ����Mysql��������

			con = DriverManager.getConnection(connectDB, "sa", "yuanweidong");
			System.out.println("Connect successfully");

		} catch (Exception e) {
			System.out.println("���ݿ�����ʧ��" + e.getMessage());
		}
		return con; // ���������������ݿ�����
	}

	public String getPassword(String id) {
		String pw = new String();
		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "select Spassword from Member where S#='" + id + "';"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			ResultSet rs = stmt.executeQuery(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			// System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()) { // �ж��Ƿ�����һ������

				// �����ֶ�����ȡ��Ӧ��ֵ
				pw = rs.getString("Spassword");

			}
			conn.close(); // �ر����ݿ�����

		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
		}
		return pw;
	}

	public void query() {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "select * from Student"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			ResultSet rs = stmt.executeQuery(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()) { // �ж��Ƿ�����һ������

				// �����ֶ�����ȡ��Ӧ��ֵ
				int id = rs.getInt("S#");
				String Sname = rs.getString("Sname");
				int Sage = rs.getInt("Sage");
				String Ssex = rs.getString("Ssex");
				String Dname = rs.getString("Dname");
				String Sclass = rs.getString("Sclass");

				// ����鵽�ļ�¼�ĸ����ֶε�ֵ
				System.out.println(id + " " + Sname + " " + Sage + " " + Ssex
						+ " " + Dname + " " + Sclass);

			}
			conn.close(); // �ر����ݿ�����

		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
		}
	}

	public void getStuInfo(String id, Student stu) {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "select * from Student where S#='" + id + "';"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			ResultSet rs = stmt.executeQuery(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()) { // �ж��Ƿ�����һ������

				// �����ֶ�����ȡ��Ӧ��ֵ
				stu.id = rs.getString("S#");
				stu.Sname = rs.getString("Sname");
				stu.Sage = rs.getInt("Sage");
				stu.Ssex = rs.getString("Ssex");
				stu.Dname = rs.getString("Dname");
				stu.Sclass = rs.getString("Sclass");

				// ����鵽�ļ�¼�ĸ����ֶε�ֵ
				// System.out.println(id+" "+Sname + " " + Sage + " " + Ssex +
				// " " + Dname + " " + Sclass);

			}
			conn.close(); // �ر����ݿ�����

		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
		}
	}
	
	public void updateStudent(String sid,String sname, String ssex, int sage,String dname,String sclass) {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "update Student set Sname='"+sname+"', Ssex='"+ssex+"', Sage='"+sage+"', Dname='"+dname+"', Sclass='"+sclass+"' where S#='"+sid+"';"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			stmt.execute(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			conn.close(); // �ر����ݿ�����

		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
		}
	}
	
	public void deleteCourse(String cid,String sid) {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "delete from SC where S#='"+sid+"' and C#='"+cid+"';"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			stmt.execute(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			conn.close(); // �ر����ݿ�����

		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
		}
	}
	
	public void getCourse(String id, ArrayList clist) {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "select * from Course,SC where SC.S#='" + id + "' and SC.C#=Course.C#;"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			ResultSet rs = stmt.executeQuery(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()) { // �ж��Ƿ�����һ������

				Course c=new Course();
				// �����ֶ�����ȡ��Ӧ��ֵ
				c.Cid = rs.getString("C#");
				c.Chours = rs.getInt("Chours");
				c.Cname = rs.getString("Cname");
				c.Credit = rs.getInt("Credit");
				c.Csemster = rs.getInt("Csemster");
				clist.add(c);
				// ����鵽�ļ�¼�ĸ����ֶε�ֵ
				// System.out.println(id+" "+Sname + " " + Sage + " " + Ssex +
				// " " + Dname + " " + Sclass);

			}
			conn.close(); // �ر����ݿ�����

		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
		}
	}
	public void getAllCourse(ArrayList clist) {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "select * from Course;"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			ResultSet rs = stmt.executeQuery(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()) { // �ж��Ƿ�����һ������

				Course c=new Course();
				// �����ֶ�����ȡ��Ӧ��ֵ
				c.Cid = rs.getString("C#");
				c.Chours = rs.getInt("Chours");
				c.Cname = rs.getString("Cname");
				c.Credit = rs.getInt("Credit");
				c.Csemster = rs.getInt("Csemster");
				clist.add(c);
				// ����鵽�ļ�¼�ĸ����ֶε�ֵ
				// System.out.println(id+" "+Sname + " " + Sage + " " + Ssex +
				// " " + Dname + " " + Sclass);

			}
			conn.close(); // �ر����ݿ�����

		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
		}
	}
	public void ShowScore(String Sid,ArrayList clist) {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "select * from Course,SC where SC.C#=Course.C# and SC.S#='"+Sid+"';"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			ResultSet rs = stmt.executeQuery(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()) { // �ж��Ƿ�����һ������

				Course c=new Course();
				// �����ֶ�����ȡ��Ӧ��ֵ
				c.Cid = rs.getString("C#");
				c.Chours = rs.getInt("Chours");
				c.Cname = rs.getString("Cname");
				c.Credit = rs.getInt("Credit");
				c.Csemster = rs.getInt("Csemster");
				c.Score=rs.getDouble("Score");
				clist.add(c);
				// ����鵽�ļ�¼�ĸ����ֶε�ֵ
				// System.out.println(id+" "+Sname + " " + Sage + " " + Ssex +
				// " " + Dname + " " + Sclass);

			}
			conn.close(); // �ر����ݿ�����

		} catch (SQLException e) {
			System.out.println("��ѯ����ʧ��");
		}
	}
	public boolean selectCourse(Course c,String Sid) {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "select * from Course,SC where SC.S#='" + Sid + "' and SC.C#=Course.C# and Course.C#='"+c.Cid+"';"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			ResultSet rs = stmt.executeQuery(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()){
				return false;
			}
			
			sql = "insert into SC (S#,C#) values ('"+Sid+"','"+c.Cid+"');"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			stmt.execute(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			conn.close(); // �ر����ݿ�����
			return true;
		} catch (SQLException e) {
			System.out.println("����ʧ��");
			return false;
		}
	}
	
	
	public boolean register(String sid,String pw) {

		conn = getConnection(); // ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		try {
			String sql = "select * from Member where S#='" + sid +"';"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			ResultSet rs = stmt.executeQuery(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			//System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()){
				return false;
			}
			
			sql = "insert into Member (S#,Spassword) values ('"+sid+"','"+pw+"');"; // ��ѯ���ݵ�sql���
			stmt = (Statement) conn.createStatement(); // ��������ִ�о�̬sql����Statement����st���ֲ�����

			stmt.execute(sql); // ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			conn.close(); // �ر����ݿ�����
			return true;
		} catch (SQLException e) {
			System.out.println("����ʧ��");
			return false;
		}
	}
}

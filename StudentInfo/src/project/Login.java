package project;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener {
	JLabel title1 = new JLabel("学生信息管理系统");
	JLabel title2 = new JLabel("注册");
	JLabel u = new JLabel("用户名：");
	JLabel p = new JLabel("密码：");
	JLabel notice = new JLabel(" ");
	JTextField t1 = new JTextField("98020101",20);
	JPasswordField t2 = new JPasswordField("123",20);
	JButton b1 = new JButton("登陆");
	JButton b2 = new JButton("注册");

	public Login() {
		Font f = new Font("", Font.ITALIC, 50);
		title1.setFont(f);
		title1.setForeground(Color.RED);
		this.setTitle("登陆");

		NewJPanel bg = new NewJPanel("pic3.jpg");
		bg.setLayout(new GridLayout(4, 1));
		JPanel TITLE = new JPanel(new FlowLayout(FlowLayout.CENTER));
		TITLE.setOpaque(false);
		;
		JPanel user = new JPanel(new FlowLayout(FlowLayout.CENTER));
		user.setOpaque(false);
		JPanel pw = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pw.setOpaque(false);
		JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		bu.setOpaque(false);
		this.setTitle("登陆");
		getContentPane().add(bg);
		TITLE.add(title1);
		bg.add(TITLE);
		user.add(u);
		
		user.add(t1);
		bg.add(user);
		pw.add(p);
		pw.add(t2);
		bg.add(pw);
		bu.add(b1);
		bu.add(b2);
		bu.add(notice);
		b1.addActionListener(this);
		b2.addActionListener(this);
		bg.add(bu);

	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		String label = e.getActionCommand();
		if (target == b2) {
			register r = new register();
			this.setVisible(false);
		} else if (target == b1) {
			SqlManager sql = new SqlManager();
			String tmp1 = new String();
			String tmp2 = new String();
			tmp1 = sql.getPassword(t1.getText());
			tmp2 = String.valueOf(t2.getPassword());
			System.out.println("tmp1=" + tmp1);
			System.out.println("tmp2=" + tmp2);
			if (tmp1.equals(tmp2)) {
				notice.setText("登陆成功");
				try {
					homepage hp = new homepage(t1.getText());
					//sql.updateLastLogin(t1.getText());
					this.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				notice.setText("用户名不存在/密码错误");
			}
		}
	}

	public static void main(String[] args) {
		Login G = new Login();
		G.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		G.setSize(600, 300);
		G.setVisible(true);
	}
}

class register extends JFrame implements ActionListener {
	JLabel title = new JLabel("注册");
	JLabel u = new JLabel("用户名：");
	JLabel p = new JLabel("密码：");
	JLabel confirm = new JLabel("确认密码：");
	JLabel notice = new JLabel(" ");
	JTextField t1 = new JTextField(20);
	JPasswordField t2 = new JPasswordField(20);
	JPasswordField t3 = new JPasswordField(20);
	JButton b = new JButton("註冊");

	public register() {
		initialize();
		this.setSize(500, 300);
		this.setVisible(true);
	}

	void initialize() {
		this.setTitle("注册");
		NewJPanel bg = new NewJPanel("pic3.jpg");
		bg.setLayout(new GridLayout(5, 1));
		JPanel TITLE = new JPanel(new FlowLayout(FlowLayout.CENTER));
		TITLE.setOpaque(false);
		JPanel user = new JPanel(new FlowLayout(FlowLayout.CENTER));
		user.setOpaque(false);
		JPanel pw = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pw.setOpaque(false);
		JPanel cf = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cf.setOpaque(false);
		JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		bu.setOpaque(false);
		this.setTitle("注册");
		getContentPane().add(bg);
		TITLE.add(title);
		bg.add(TITLE);
		user.add(u);
		user.add(t1);
		bg.add(user);
		pw.add(p);
		pw.add(t2);
		bg.add(pw);
		cf.add(confirm);
		cf.add(t3);
		bg.add(cf);
		bu.add(b);
		bu.add(notice);
		bg.add(bu);
		b.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		String label = e.getActionCommand();
		if (target == b) {
			if (String.valueOf(t2.getPassword()).equals(
					String.valueOf(t3.getPassword()))) {
				SqlManager sql = new SqlManager();
				if (sql.register(t1.getText(), String.valueOf(t2.getPassword())) == true) {
					notice.setText("注册成功");
					this.setVisible(false);
					try {
						homepage h=new homepage(t1.getText());
						this.setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					notice.setText("用户名已存在");
				}
			} else {
				notice.setText("两次输入密码不一样");
			}
		}
	}
}

class homepage extends JFrame implements ActionListener {
	JLabel id = new JLabel("学号");
	JLabel Sname=new JLabel("姓名");
	JLabel Sage = new JLabel("年龄");
	JLabel Dname = new JLabel("专业");
	JLabel Ssex = new JLabel("性别");
	JLabel Sclass =new JLabel("班级");
	JLabel name1 = new JLabel();
	JLabel age1 = new JLabel();
	JLabel sex1 = new JLabel();
	JLabel class1 = new JLabel();
	JLabel dname1 = new JLabel();
	JLabel id1 = new JLabel();
	ArrayList clist=new ArrayList();
	//JTextArea motto1 = new JTextArea(3, 50);
	JButton b = new JButton("修改个人信息");
	JButton b1 = new JButton("课程信息");
	JButton b2 = new JButton("选课");
	JButton b3 = new JButton("成绩");
	JButton b4 = new JButton("删除选课");
	JList list = new JList();
	JScrollPane jsp = new JScrollPane(list);
	String Sid=new String();
	//diaryInform di = new diaryInform();

	// String st[]={"a","b","c","b","c","b","c","b","c","b","c"};
	public homepage(String id) throws SQLException {
		initialize(id);
		this.setSize(700, 400);
		this.setVisible(true);
		this.Sid=id;
	}

	void initialize(String id) throws SQLException {
		Font f = new Font("", Font.ITALIC, 20);
		this.id.setFont(f);
		this.id.setForeground(Color.RED);
		this.Sname.setFont(f);
		this.Sname.setForeground(Color.RED);
		this.Sage.setFont(f);
		this.Sage.setForeground(Color.RED);
		this.Ssex.setFont(f);
		this.Ssex.setForeground(Color.RED);
		this.Dname.setFont(f);
		this.Dname.setForeground(Color.RED);
		this.Sclass.setFont(f);
		this.Sclass.setForeground(Color.RED);
		this.setTitle("主页");
		NewJPanel bg = new NewJPanel("pic4.jpg");
		bg.setLayout(new GridLayout(1, 1));
		JPanel upbg = new JPanel(new BorderLayout(50, 50));
		upbg.setOpaque(false);
		JPanel upleftbg = new JPanel(new GridLayout(6, 1));
		upleftbg.setOpaque(false);
		JPanel bu1 = new JPanel(new GridLayout(1, 4));
		bu1.setOpaque(false);
		JPanel uprightbg = new JPanel(new BorderLayout(50, 50));
		uprightbg.setOpaque(false);
		JPanel uprightp1 = new JPanel(new GridLayout(4, 1, 20, 20));
		uprightp1.setOpaque(false);
		JPanel downbg = new JPanel(new GridLayout(3, 1));
		downbg.setOpaque(false);
		JPanel n = new JPanel(new FlowLayout(FlowLayout.LEFT));
		n.setOpaque(false);
		JPanel a = new JPanel(new FlowLayout(FlowLayout.LEFT));
		a.setOpaque(false);
//		JPanel m = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		m.setOpaque(false);
		JPanel g = new JPanel(new FlowLayout(FlowLayout.LEFT));
		g.setOpaque(false);
		JPanel c = new JPanel(new FlowLayout(FlowLayout.LEFT));
		c.setOpaque(false);
		JPanel d = new JPanel(new FlowLayout(FlowLayout.LEFT));
		d.setOpaque(false);
		JPanel i = new JPanel(new FlowLayout(FlowLayout.LEFT));
		i.setOpaque(false);
//		JPanel lt = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		lt.setOpaque(false);
		JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bu.setOpaque(false);

		
		
			SqlManager sql = new SqlManager();
			Student stu=new Student();
			
			sql.getStuInfo(id, stu);
		sql.getCourse(id, clist);
//
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("已选课程");
		for (int i1 = 0; i1 < clist.size(); i1++) {
			listModel.addElement(((Course)clist.get(i1)).Cid + "  " + ((Course)clist.get(i1)).Cname);
			//System.out.println(di.title[i1] + "  " + di.time[i1]);
		}
		list.setModel(listModel);
//
//		
//		
		list.setSize(new Dimension(100, 100));
		list.setSelectedIndex(0);

		this.setTitle("主页");
		getContentPane().add(bg);

		bg.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		i.add(this.id);
		this.id1.setFont(f);
		id1.setText(stu.id);
		i.add(id1);
		upleftbg.add(i);
		
		this.name1.setFont(f);
		name1.setText(stu.Sname);
		n.add(Sname);
		n.add(name1);
		upleftbg.add(n);
		
		a.add(Sage);
		this.age1.setFont(f);
		age1.setText(String.valueOf(stu.Sage));
		a.add(age1);
		upleftbg.add(a);
		
		g.add(Ssex);
		this.sex1.setFont(f);
		sex1.setText(stu.Ssex);
		g.add(sex1);
		upleftbg.add(g);
		
		
		d.add(Dname);
		this.dname1.setFont(f);
		dname1.setText(stu.Dname);
		d.add(dname1);
		upleftbg.add(d);
		
		c.add(Sclass);
		this.class1.setFont(f);
		class1.setText(stu.Sclass);
		c.add(class1);
		upleftbg.add(c);
		
		upbg.add("West", upleftbg);
//		b1.setPreferredSize(new Dimension(50,10));
//		b2.setPreferredSize(new Dimension(50,10));
//		b3.setPreferredSize(new Dimension(50,10));
//		bu1.add(b1);
//		bu1.add(b2);
//		bu1.add(b3);
//		bu1.add(b4);
		
		uprightp1.add(b1);
		uprightp1.add(b2);
		uprightp1.add(b3);
		uprightp1.add(b4);
		//uprightp1.add(bu1);
		uprightbg.add("West", uprightp1);
		uprightbg.add("Center", jsp);
		upbg.add("Center", uprightbg);
		//motto1.setText(info.motto);
		//motto1.setEditable(false);
		//m.add(motto);
		//m.add(motto1);
		//downbg.add(m);
		//lt.add(lastlogin);
		//lt.add(new JLabel(info.lastlogin));
		//downbg.add(lt);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		bu.add(b);
		upbg.add("South",bu);
		//bu.add(b);
		//downbg.add(bu);
		bg.add(upbg);
		//bg.add(downbg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		String label = e.getActionCommand();
		if (target == b) {
			try {
				Modify m=new Modify(Sid);
				this.setVisible(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (target == b1) {
//			mysqltest sql = new mysqltest();
//			try {
//				newDiary tmp = new newDiary(name1.getText());
//				this.setVisible(false);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			SqlManager sql = new SqlManager();
			int i = list.getSelectedIndex();
//			sql.getDiary(i, di);
			try {
				ShowCourse tmp = new ShowCourse((Course)clist.get(i-1),this.Sid);
				//this.setVisible(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (target == b2) {
			SqlManager sql = new SqlManager();
			try {
				CourseManager cm=new CourseManager(Sid);
				this.setVisible(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (target == b3) {
//			SqlManager sql = new SqlManager();
			try {
				ShowScore ss=new ShowScore(Sid);
				this.setVisible(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else if (target == b4) {
			SqlManager sql = new SqlManager();
			
//			int i = list.getSelectedIndex();
////			sql.getDiary(i, di);
//			try {
//				ShowCourse tmp = new ShowCourse((Course)clist.get(i),this.Sid);
			try {
				int i = list.getSelectedIndex();
				sql.deleteCourse(((Course)clist.get(i-1)).Cid,this.Sid);
				homepage h=new homepage(Sid);
				this.setVisible(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}

//class modify extends JFrame implements ActionListener {
//	JLabel name = new JLabel("用户名");
//	JLabel age = new JLabel("年龄");
//	JLabel motto = new JLabel("个性签名");
//	JLabel gender = new JLabel("性别");
//	JLabel lastlogin = new JLabel("上次登录");
//	JLabel name1 = new JLabel();
//	JTextField age1 = new JTextField(20);
//	JTextField gender1 = new JTextField(20);
//	JTextArea motto1 = new JTextArea(3, 50);
//	JButton b = new JButton("完成");
//
//	public modify(String name) throws SQLException {
//		initialize(name);
//		this.setSize(700, 400);
//		this.setVisible(true);
//	}
//
//	void initialize(String NAME) throws SQLException {
//		NewJPanel bg = new NewJPanel("pic4.jpg");
//		bg.setLayout(new GridLayout(6, 1));
//		JPanel n = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JPanel a = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		a.setOpaque(false);
//		n.setOpaque(false);
//		JPanel m = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		m.setOpaque(false);
//		JPanel g = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		g.setOpaque(false);
//		JPanel lt = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		lt.setOpaque(false);
//		JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		bu.setOpaque(false);
//		this.setTitle("修改资料");
//		// String name1=new String();
//		// String age1=new String();
//		// String gender1=new String();
//		// String motto1=new String();
//		// String lastlogin1=new String();
//		mysqltest sql = new mysqltest();
//		inform info = new inform();
//		sql.getInformation(NAME, info);
//		getContentPane().add(bg);
//		name1.setText(info.name);
//		n.add(name);
//		n.add(name1);
//		bg.add(n);
//		age1.setText(info.age);
//		a.add(age);
//		a.add(age1);
//		bg.add(a);
//		gender1.setText(info.gender);
//		g.add(gender);
//		g.add(gender1);
//		bg.add(g);
//		motto1.setText(info.motto);
//		motto1.setLineWrap(true);
//		m.add(motto);
//		m.add(motto1);
//		bg.add(m);
//		lt.add(lastlogin);
//		lt.add(new JLabel(info.lastlogin));
//		bg.add(lt);
//		b.addActionListener(this);
//		bu.add(b);
//		bg.add(bu);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object target = e.getSource();
//		String label = e.getActionCommand();
//		if (target == b) {
//			mysqltest sql = new mysqltest();
//			sql.modifyInformation(name1.getText(), age1.getText(),
//					gender1.getText(), motto1.getText());
//			try {
//				homepage tmp = new homepage(name1.getText());
//				this.setVisible(false);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
//
//}
//
//class newDiary extends JFrame implements ActionListener {
//	JLabel name1 = new JLabel();
//	JLabel title = new JLabel("题目");
//	JLabel text = new JLabel("正文");
//	JTextArea title1 = new JTextArea(1, 50);
//	JTextArea text1 = new JTextArea(30, 50);
//	JScrollPane jp = new JScrollPane(text1);
//	JButton b = new JButton("保存");
//
//	public newDiary(String name) throws SQLException {
//		initialize(name);
//		this.setSize(700, 400);
//		this.setVisible(true);
//	}
//
//	void initialize(String NAME) throws SQLException {
//		name1.setText(NAME);
//		NewJPanel bg = new NewJPanel("pic5.jpg");
//		bg.setLayout(new BorderLayout());
//		JPanel ti = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		JPanel te = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		ti.setOpaque(false);
//		te.setOpaque(false);
//		JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		bu.setOpaque(false);
//		text1.setLineWrap(true);
//		this.setTitle("创建新日记");
//		getContentPane().add(bg);
//		ti.add(title);
//		ti.add(title1);
//		bg.add("North", ti);
//		te.add(text);
//		te.add(jp);
//		bg.add("Center", te);
//		bu.add(b);
//		bg.add("South", bu);
//		b.addActionListener(this);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object target = e.getSource();
//		String label = e.getActionCommand();
//		if (target == b) {
//			mysqltest sql = new mysqltest();
//			sql.saveDiary(name1.getText(), title1.getText(), text1.getText());
//			try {
//				homepage tmp = new homepage(name1.getText());
//				this.setVisible(false);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
//
//}
//
//class modifyDiary extends JFrame implements ActionListener {
//	JLabel name1 = new JLabel();
//	JLabel title = new JLabel("题目");
//	JLabel text = new JLabel("正文");
//	JTextArea title1 = new JTextArea(1, 50);
//	JTextArea text1 = new JTextArea(30, 50);
//	JScrollPane jp = new JScrollPane(text1);
//	JButton b = new JButton("保存");
//	String tmp_time = new String();
//
//	public modifyDiary(diaryInform di) throws SQLException {
//		initialize(di);
//		this.setSize(700, 400);
//		this.setVisible(true);
//	}
//
//	void initialize(diaryInform di) throws SQLException {
//		tmp_time = di.selectedTime;
//		name1.setText(di.name);
//		NewJPanel bg = new NewJPanel("pic5.jpg");
//		bg.setLayout(new BorderLayout());
//		JPanel ti = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		ti.setOpaque(false);
//		JPanel te = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		te.setOpaque(false);
//		JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		bu.setOpaque(false);
//		text1.setLineWrap(true);
//		this.setTitle("修改日记");
//		getContentPane().add(bg);
//		ti.add(title);
//		title1.setText(di.selectedTitle);
//		ti.add(title1);
//		bg.add("North", ti);
//		te.add(text);
//		text1.setText(di.selectedDiary);
//		te.add(jp);
//		bg.add("Center", te);
//		bu.add(b);
//		bg.add("South", bu);
//		b.addActionListener(this);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object target = e.getSource();
//		String label = e.getActionCommand();
//		if (target == b) {
//			mysqltest sql = new mysqltest();
//			sql.saveModifiedDiary(name1.getText(), title1.getText(),
//					text1.getText(), tmp_time);
//			try {
//				homepage tmp = new homepage(name1.getText());
//				this.setVisible(false);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
//
//}
//
//class readDiary extends JFrame implements ActionListener {
//	JLabel name1 = new JLabel();
//	JLabel title = new JLabel("题目");
//	JLabel text = new JLabel("正文");
//	JTextArea title1 = new JTextArea(1, 50);
//	JTextArea text1 = new JTextArea(30, 50);
//	JScrollPane jp = new JScrollPane(text1);
//	JButton b = new JButton("关闭");
//	String tmp_time = new String();
//
//	public readDiary(diaryInform di) throws SQLException {
//		initialize(di);
//		this.setSize(700, 400);
//		this.setVisible(true);
//	}
//
//	void initialize(diaryInform di) throws SQLException {
//		tmp_time = di.selectedTime;
//		name1.setText(di.name);
//		NewJPanel bg = new NewJPanel("pic5.jpg");
//		bg.setLayout(new BorderLayout());
//		JPanel ti = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		ti.setOpaque(false);
//		JPanel te = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		te.setOpaque(false);
//		JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		bu.setOpaque(false);
//		text1.setLineWrap(true);
//		this.setTitle("阅读日记");
//		getContentPane().add(bg);
//		ti.add(title);
//		title1.setText(di.selectedTitle);
//		title1.setEditable(false);
//		ti.add(title1);
//		bg.add("North", ti);
//		te.add(text);
//		text1.setText(di.selectedDiary);
//		text1.setEditable(false);
//		te.add(jp);
//		bg.add("Center", te);
//		bu.add(b);
//		bg.add("South", bu);
//		b.addActionListener(this);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object target = e.getSource();
//		String label = e.getActionCommand();
//		if (target == b) {
//			this.setVisible(false);
//			;
//		}
//	}
//
//}
//

class CourseManager extends JFrame implements ActionListener {
	String Sid;
	JButton b1 = new JButton("选课");
	JButton b2 = new JButton("查询");
	JButton b3 = new JButton("返回");
	//JButton b3 = new JButton("成绩");
	JList list = new JList();
	JScrollPane jsp = new JScrollPane(list);
	//JTextArea title1 = new JTextArea(1, 50);
	//JTextArea text1 = new JTextArea(30, 50);
	//JScrollPane jp = new JScrollPane(text1);
	//JButton b = new JButton("返回");
	ArrayList clist=new ArrayList();
	JLabel notice = new JLabel(" ");
	//String tmp_time = new String();

public CourseManager(String Sid) throws SQLException {
	Font f = new Font("", Font.ITALIC, 20);
	this.setSize(700, 400);
	this.setVisible(true);
	this.Sid=Sid;
	//Csemster.setForeground(Color.RED);
	initialize();
}

void initialize() throws SQLException {
	//tmp_time = di.selectedTime;
	//name1.setText(di.name);
	SqlManager sql=new SqlManager();
	sql.getAllCourse(clist);
	//
	DefaultListModel listModel = new DefaultListModel();
	for (int i1 = 0; i1 < clist.size(); i1++) {
		listModel.addElement(((Course)clist.get(i1)).Cid + "  " + ((Course)clist.get(i1)).Cname);
		//System.out.println(di.title[i1] + "  " + di.time[i1]);
	}
	list.setModel(listModel);
	NewJPanel bg = new NewJPanel("pic5.jpg");
	bg.setLayout(new BorderLayout(6,1));
	JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
	bu.setOpaque(false);
	//text1.setLineWrap(true);
	this.setTitle("课程管理");
	getContentPane().add(bg);
	bg.add(jsp);
	bu.add(b1);
	bu.add(b2);
	bu.add(b3);
	bu.add(notice);
	bg.add("South",bu);
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent e) {
	Object target = e.getSource();
	String label = e.getActionCommand();
	if (target == b3) {
		try {
			homepage h=new homepage(this.Sid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setVisible(false);
	}
	else if(target ==b2)
	{
		SqlManager sql = new SqlManager();
		int i = list.getSelectedIndex();
//		sql.getDiary(i, di);
		try {
			ShowCourse tmp = new ShowCourse((Course)clist.get(i),this.Sid);
			//this.setVisible(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	else if(target ==b1)
	{
		SqlManager sql = new SqlManager();
		int i = list.getSelectedIndex();
//		sql.getDiary(i, di);
		if(sql.selectCourse((Course)clist.get(i),Sid)==true)
		{
			notice.setText(notice.getText()+"选课成功");
		}
		else
		{
			notice.setText(notice.getText()+"选课失败");
		}
	}
}

}

class ShowScore extends JFrame implements ActionListener {
	String Sid;
	//JButton b1 = new JButton("选课");
	JButton b2 = new JButton("查询");
	JButton b3 = new JButton("返回");
	//JButton b3 = new JButton("成绩");
	JList list = new JList();
	JScrollPane jsp = new JScrollPane(list);
	//JTextArea title1 = new JTextArea(1, 50);
	//JTextArea text1 = new JTextArea(30, 50);
	//JScrollPane jp = new JScrollPane(text1);
	//JButton b = new JButton("返回");
	ArrayList clist=new ArrayList();
	JLabel notice = new JLabel(" ");
	//String tmp_time = new String();

public ShowScore(String Sid) throws SQLException {
	Font f = new Font("", Font.ITALIC, 20);
	this.setSize(700, 400);
	this.setVisible(true);
	this.Sid=Sid;
	//Csemster.setForeground(Color.RED);
	initialize();
}

void initialize() throws SQLException {
	//tmp_time = di.selectedTime;
	//name1.setText(di.name);
	SqlManager sql=new SqlManager();
	sql.ShowScore(this.Sid,clist);
	//
	DefaultListModel listModel = new DefaultListModel();
	for (int i1 = 0; i1 < clist.size(); i1++) {
		listModel.addElement(((Course)clist.get(i1)).Cname + "  " + ((Course)clist.get(i1)).Score);
		//System.out.println(di.title[i1] + "  " + di.time[i1]);
	}
	list.setModel(listModel);
	NewJPanel bg = new NewJPanel("pic5.jpg");
	bg.setLayout(new BorderLayout(6,1));
	JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
	bu.setOpaque(false);
	//text1.setLineWrap(true);
	this.setTitle("成绩查询");
	getContentPane().add(bg);
	bg.add(jsp);
	//bu.add(b1);
	bu.add(b2);
	bu.add(b3);
	bu.add(notice);
	bg.add("South",bu);
	//b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent e) {
	Object target = e.getSource();
	String label = e.getActionCommand();
	if (target == b3) {
		try {
			homepage h=new homepage(this.Sid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setVisible(false);
	}
	else if(target ==b2)
	{
		SqlManager sql = new SqlManager();
		int i = list.getSelectedIndex();
//		sql.getDiary(i, di);
		try {
			ShowCourse tmp = new ShowCourse((Course)clist.get(i),this.Sid);
			//this.setVisible(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
//	else if(target ==b1)
//	{
//		SqlManager sql = new SqlManager();
//		int i = list.getSelectedIndex();
////		sql.getDiary(i, di);
//		if(sql.selectCourse((Course)clist.get(i),Sid)==true)
//		{
//			notice.setText(notice.getText()+"选课成功");
//		}
//		else
//		{
//			notice.setText(notice.getText()+"选课失败");
//		}
//	}
}
}


class ShowCourse extends JFrame implements ActionListener {
	JLabel Cid ;
	JLabel Cname;
	JLabel Credit ;
	JLabel Chours;
	JLabel Csemster;
	String Sid;
	//JTextArea title1 = new JTextArea(1, 50);
	//JTextArea text1 = new JTextArea(30, 50);
	//JScrollPane jp = new JScrollPane(text1);
	JButton b = new JButton("关闭");
	//String tmp_time = new String();

public ShowCourse(Course c,String Sid) throws SQLException {
	Font f = new Font("", Font.ITALIC, 20);
	this.setSize(700, 400);
	this.setVisible(true);
	Cid=new JLabel("课程号："+c.Cid);
	Cid.setFont(f);
	//Cid.setForeground(Color.RED);
	Cname=new JLabel("课程名："+c.Cname);
	Cname.setFont(f);
	//Cname.setForeground(Color.RED);
	Credit=new JLabel("学分："+c.Credit);
	Credit.setFont(f);
	//Credit.setForeground(Color.RED);
	Chours=new JLabel("课时："+c.Chours);
	Chours.setFont(f);
	//Chours.setForeground(Color.RED);
	Csemster=new JLabel("学期："+c.Csemster);
	Csemster.setFont(f);
	this.Sid=Sid;
	//Csemster.setForeground(Color.RED);
	initialize(c);
}

void initialize(Course c) throws SQLException {
	//tmp_time = di.selectedTime;
	//name1.setText(di.name);
	NewJPanel bg = new NewJPanel("pic5.jpg");
	bg.setLayout(new GridLayout(6,1));
	JPanel cid = new JPanel(new FlowLayout(FlowLayout.CENTER));
	cid.setOpaque(false);
	JPanel cname = new JPanel(new FlowLayout(FlowLayout.CENTER));
	cname.setOpaque(false);
	JPanel credit = new JPanel(new FlowLayout(FlowLayout.CENTER));
	credit.setOpaque(false);
	JPanel chours = new JPanel(new FlowLayout(FlowLayout.CENTER));
	chours.setOpaque(false);
	JPanel csemster = new JPanel(new FlowLayout(FlowLayout.CENTER));
	csemster.setOpaque(false);
	JPanel bu = new JPanel(new FlowLayout(FlowLayout.CENTER));
	bu.setOpaque(false);
	//text1.setLineWrap(true);
	this.setTitle("课程");
	getContentPane().add(bg);
	cid.add(Cid);
	bg.add(cid);
	cname.add(Cname);
	bg.add(cname);
	credit.add(Credit);
	bg.add(credit);
	chours.add(Chours);
	bg.add(chours);
	csemster.add(Csemster);
	bg.add(csemster);
	bu.add(b);
	bg.add(bu);
	b.addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent e) {
	Object target = e.getSource();
	String label = e.getActionCommand();
	if (target == b) {
			this.setVisible(false);
	}
}

}




class Modify extends JFrame implements ActionListener {
	JLabel Sid = new JLabel("学号");
	JLabel Sname=new JLabel("姓名");
	JLabel Sage = new JLabel("年龄");
	JLabel Dname = new JLabel("专业");
	JLabel Ssex = new JLabel("性别");
	JLabel Sclass =new JLabel("班级");
	String sid=new String();
	//JTextArea title1 = new JTextArea(1, 50);
	//JTextArea text1 = new JTextArea(30, 50);
	//JScrollPane jp = new JScrollPane(text1);
	JButton b = new JButton("保存");
	JTextField sname = new JTextField(20);
	JTextField sclass = new JTextField(20);
	JTextField sage = new JTextField(20);
	JTextField ssex = new JTextField(20);
	JTextField dname = new JTextField(20);
	//String tmp_time = new String();

public Modify(String Sid) throws SQLException {
	Font f = new Font("", Font.ITALIC, 20);
	this.setSize(700, 400);
	this.setVisible(true);
	this.sid=Sid;
	//Csemster.setForeground(Color.RED);
	initialize();
}

void initialize() throws SQLException {
	//tmp_time = di.selectedTime;
	//name1.setText(di.name);
	NewJPanel bg = new NewJPanel("pic5.jpg");
	bg.setLayout(new GridLayout(6,1));
	JPanel dn = new JPanel(new FlowLayout(FlowLayout.LEFT));
	dn.setOpaque(false);
	JPanel sn = new JPanel(new FlowLayout(FlowLayout.LEFT));
	sn.setOpaque(false);
	JPanel c = new JPanel(new FlowLayout(FlowLayout.LEFT));
	c.setOpaque(false);
	JPanel s = new JPanel(new FlowLayout(FlowLayout.LEFT));
	s.setOpaque(false);
	JPanel i = new JPanel(new FlowLayout(FlowLayout.LEFT));
	i.setOpaque(false);
	JPanel a = new JPanel(new FlowLayout(FlowLayout.LEFT));
	a.setOpaque(false);
	JPanel bu = new JPanel(new FlowLayout(FlowLayout.LEFT));
	bu.setOpaque(false);
	//text1.setLineWrap(true);
	
	SqlManager sql = new SqlManager();
	Student stu=new Student();
	sql.getStuInfo(sid, stu);
	
	this.setTitle("个人信息");
	getContentPane().add(bg);
	
	
	i.add(Sid);
	i.add(new JLabel(stu.id));
	bg.add(i);
	
	sn.add(Sname);
	sname.setText(stu.Sname);
	sn.add(sname);
	bg.add(sn);
	
	dn.add(Dname);
	dname.setText(stu.Dname);
	dn.add(dname);
	bg.add(dn);
	
	a.add(Sage);
	sage.setText(String.valueOf(stu.Sage));
	a.add(sage);
	bg.add(a);
	
	s.add(Ssex);
	ssex.setText(stu.Ssex);
	s.add(ssex);
	bg.add(s);
	
	c.add(Sclass);
	sclass.setText(stu.Sclass);
	c.add(sclass);
	bg.add(c);
	
	
	
	
	bu.add(b);
	bg.add(bu);
	b.addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent e) {
	Object target = e.getSource();
	String label = e.getActionCommand();
	if (target == b) {
			this.setVisible(false);
			SqlManager sql=new SqlManager();
			sql.updateStudent(sid,sname.getText(),ssex.getText(),Integer.valueOf(sage.getText()),dname.getText(),sclass.getText());
			try {
				homepage h=new homepage(sid);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}

}




class NewJPanel extends JPanel {
	String str = new String();

	public NewJPanel(String tmp) {
		str = tmp;
	}
	
	public void paintComponent(Graphics g) {
		int x = 0, y = 0;
		java.net.URL imgURL = getClass().getResource(str);
		// test.jpg是测试图片，与Demo.java放在同一目录下
		ImageIcon icon = new ImageIcon(imgURL);
		g.drawImage(icon.getImage(), 0, 0, getSize().width, getSize().height,
				this);
		// while(true)
		// {
		// g.drawImage(icon.getImage(),x,y,this);
		// if(x>getSize().width && y>getSize().height)break;
		// //这段代码是为了保证在窗口大于图片时，图片仍能覆盖整个窗口
		// if(x>getSize().width)
		// {
		// x=0;
		// y+=icon.getIconHeight();
		// }
		// else
		// x+=icon.getIconWidth();
		// }
	}

}

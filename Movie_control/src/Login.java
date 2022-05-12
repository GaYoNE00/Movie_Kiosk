import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.print.attribute.standard.Sides;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mysql.cj.protocol.Message;

public class Login extends JFrame implements ActionListener, MouseListener{
	private JTextField tfID;
	private JPasswordField tfPW;
	private JButton btnLg;
	private JLabel lblID;
	private JLabel lblPW;
	private JLabel FindID;
	private JLabel FindPW;
	private JPanel panelCenter;
	private JPanel panelright;
	private JPanel panelbelow;
	private JLabel signUp;
	private Font NomalFont1;
	private String sid;
	private String spw;
	private JLabel llogo;
	
	
	public Login(String title) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 250);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setTitle(title);
		pn();

		setVisible(true);
		
	}
	public void pn() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(75,14,116));
		
		ImageIcon logo = new ImageIcon("image/레알로고_최신(로그인폼).png");
		ImageIcon iconBtn = new ImageIcon("image/로그인버튼_찐찐막.png");
		
		llogo = new JLabel(logo);
		jp.add(llogo);
		llogo.setBounds(5,0, 270 ,60);
		lblID = new JLabel("아이디");
		lblPW = new JLabel("비밀번호");		
		tfID = new JTextField(15);
		tfPW = new JPasswordField(15);
		
		NomalFont1 = new Font("HY중고딕",Font.BOLD,11);
		
		jp.add(lblID);
		jp.add(tfID);
		jp.add(lblPW);
		jp.add(tfPW);

		lblID.setForeground(Color.white);
		lblID.setBounds(20,70,50,25);
		lblPW.setBounds(15,105,50,25);
		lblPW.setForeground(Color.white);
		tfID.setBounds(70,70,180,25);
		tfPW.setBounds(70,105,180,25);
		btnLg = new JButton(iconBtn);
		jp.add(btnLg);
		btnLg.addActionListener(this);
		
		btnLg.setBounds(18,140,250,40);
		
		FindID = new JLabel("아이디 찾기 ");
		FindID.setForeground(Color.white);
		FindPW = new JLabel("/  비밀번호 찾기");
		FindPW.setForeground(Color.white);
		signUp = new JLabel("/ 회원가입");
		signUp.setForeground(Color.white);

		jp.add(FindID);
		FindID.addMouseListener(this);
		jp.add(FindPW);
		FindPW.addMouseListener(this);
		jp.add(signUp);
		signUp.addMouseListener(this);
		
		FindID.setFont(NomalFont1);
		FindID.setBounds(30,185,70,20);
		FindPW.setFont(NomalFont1);
		FindPW.setBounds(95,185,90,20);
		signUp.setFont(NomalFont1);
		signUp.setBounds(190,185,70,20);
		
		add(jp, BorderLayout.CENTER);
		
	}
	public class Dba {
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		//드라이버 주소
		static final String DB_URL ="jdbc:mysql://localhost:3306/test";
		//DB접속
		//localhost 는 접속하려는 데이터베이스 주소를 입력하시면 됩니다.
		//3306은 데이터베이스에 접속할때 사용하는 포트 번홍비니다. 설치할때설정한 것
		//databasename에는 접속하려는 database의 name을 입력해 줍니다.
		static final String USERNAME = "root";
		static final String PASSWORD = "1234";
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs= null;
		
		
		public int DBb(int a){
			Dba db = new Dba();
			
			System.out.print("User Table 접속 : ");
			
			try {
				Class.forName(db.JDBC_DRIVER);
				//class클래스의 forName()함수를 이용해서 해당클래스를메모리로로드
				//url, id, password 를 입력하여 데이터페이스에 접속
				conn=DriverManager.getConnection(db.DB_URL,db.USERNAME,db.PASSWORD);
				stmt = conn.createStatement();
			
				if(conn !=null) {
					System.out.println("성공");
					switch (a) {
					case 1:
						System.out.println("Select문");
						String sql = "Select S_Index, name, Sid, Spw from staff where Sid = '" + sid +"' and Spw = '" + spw + "'";
						rs = stmt.executeQuery(sql);
						
						if(rs.next()) {
								int S_Index = rs.getInt(1);
								String name = rs.getString(2);
								JOptionPane.showConfirmDialog(null, name + "님 환영합니다!!!","로그인",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null);

								
								return S_Index;
						}else {
							JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 올바르지 않습니다!","오류",JOptionPane.ERROR_MESSAGE);	
						}
						
						break;
					case 2:
						System.out.println("2");						
						break;
					case 3:
						System.out.println("3");						
						break;

					default:
						System.out.println("4");
						break;
					}
				}else {
					System.out.println("실패");				
				}
			} catch (ClassNotFoundException e){
				System.out.println("class not found exection");
			} catch (Exception e) {
				System.out.println("sql exception : " + e.getMessage());
			}
			return 0;
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLg) {
			System.out.println("서버 접속시도...");
			sid = tfID.getText();
			spw = tfPW.getText();
			if(sid.equals("") || spw.equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸을 모두 입력해주세요!","오류",JOptionPane.ERROR_MESSAGE);
			}else {
				Dba db = new Dba();
				int getSid = db.DBb(1);
				System.out.println(getSid);
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==FindID) {
			System.out.println("아이디 찾기");					
		}else if(e.getSource()==FindPW) {
			System.out.println("비밀번호 찾기");					
		}else if(e.getSource()==signUp) {
			System.out.println("회원가입");								
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

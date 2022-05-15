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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private JButton lgExitbtn;
	
	
	public Login(String title) {
		setDefaultCloseOperation(Login.DISPOSE_ON_CLOSE);
		setSize(300, 250);
		setLocationRelativeTo(null);
//		setExtendedState(MAXIMIZED_BOTH); 창 최대화
		setUndecorated(true);
		setLayout(new BorderLayout());
		setTitle(title);
		
		LGtitlebar();
		pn();
//		winEvent();
		
		setVisible(true);
		tfID.requestFocus();
	}
	private void LGtitlebar() {
		JPanel lgtitlebar = new JPanel();
		lgtitlebar.setLayout(new BorderLayout());
		lgtitlebar.setBackground(new Color(31,17,55));

//		ImageIcon lgexitbtnic = new ImageIcon("image/imgLogin/exit.jpg");
//		Image imgexit = lgexitbtnic.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//		lgexitbtnic = new ImageIcon(imgexit);
//		
		lgExitbtn = new JButton("X");
		JButton lgExitbtn1 = new JButton("X");
//		lgExitbtn.setBackground(new Color(255,184,28));
		lgExitbtn.setBackground(Color.red);
		lgExitbtn.setForeground(Color.white);
		lgExitbtn.setFocusPainted(false);
		

		lgExitbtn.addActionListener(this);

		lgtitlebar.add(lgExitbtn, BorderLayout.EAST);

		
		add(lgtitlebar, BorderLayout.NORTH);
	}
	
	public void pn() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(75,14,116));
		
		ImageIcon logo = new ImageIcon("image/logo/레알로고_최신(로그인폼).png");
		ImageIcon iconBtn = new ImageIcon("image/logo/로그인버튼_찐찐막.png");
		
		llogo = new JLabel(logo);
		jp.add(llogo);
		llogo.setBounds(5,0, 270 ,60);
		lblID = new JLabel("아이디");
		lblPW = new JLabel("비밀번호");		
		tfID = new JTextField(15);
		tfPW = new JPasswordField(15);
		
		NomalFont1 = new Font("HY중고딕",Font.BOLD,15);
		
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
		
		btnLg.setBounds(30,140,220,25);
		
		FindID = new JLabel("아이디 찾기");
		FindID.setForeground(Color.white);
		FindPW = new JLabel("비밀번호 찾기");
		FindPW.setForeground(Color.white);


		jp.add(FindID);
		FindID.addMouseListener(this);
		jp.add(FindPW);
		FindPW.addMouseListener(this);

		
		FindID.setFont(NomalFont1);
		FindID.setBounds(40,185,100,20);
		FindPW.setFont(NomalFont1);
		FindPW.setBounds(145,185,100,20);
		
		add(jp, BorderLayout.CENTER);
		
	}
	public class DbLG {
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		//드라이버 주소
		static final String DB_URL ="jdbc:mysql://localhost:3306/movie";
		//DB접속
		//localhost 는 접속하려는 데이터베이스 주소를 입력하시면 됩니다.
		//3306은 데이터베이스에 접속할때 사용하는 포트 번홍비니다. 설치할때설정한 것
		//databasename에는 접속하려는 database의 name을 입력해 줍니다.
		static final String USERNAME = "root";
		static final String PASSWORD = "1234";
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs= null;
		
		
		public int DBlg(int a) throws SQLException{

			System.out.print("User Table 접속 : ");
			
			try {
				Class.forName(JDBC_DRIVER);
				//class클래스의 forName()함수를 이용해서 해당클래스를메모리로로드
				//url, id, password 를 입력하여 데이터페이스에 접속
				conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
				stmt = conn.createStatement();
				
				if(conn !=null) {
					System.out.println("성공");
					switch (a) {
					case 1: //로그인 db조회
						System.out.println("Select문");
						String sql = "Select S_Index, name, Sid, Spw from staff where Sid = '" + sid +"' and Spw = '" + spw + "'";
						rs = stmt.executeQuery(sql);
						
						if(rs.next()) {
								int S_Index = rs.getInt(1);
								String name = rs.getString(2);
								JOptionPane.showConfirmDialog(null, name + "님 환영합니다.","로그인",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null);
								
								StaffForm st  = new StaffForm(S_Index);
								
								dispose();

								return S_Index;
								
						}else {
							JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 올바르지 않습니다!","오류",JOptionPane.ERROR_MESSAGE);	
						}
						break;
					case 2: //아이디 찾기
						System.out.println("2");	
						break;
					case 3: //비밀번호 찾기
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
			}finally {
				rs.close();
				stmt.close();
				conn.close();
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
				DbLG dblg = new DbLG();
				int getSid = 0;
				try {
					getSid = dblg.DBlg(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(getSid);
				
			}
		}else if(e.getSource() == lgExitbtn){
			System.exit(0);
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

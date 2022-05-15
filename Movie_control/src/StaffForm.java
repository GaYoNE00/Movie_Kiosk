import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Year;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class StaffForm extends JFrame implements ActionListener{
	
	private JMenuItem stfitemLogout;
	private JMenuItem stfiteminfo;
	private JButton stfbtnMvEdit;
	private JButton stfbtnMvTime;
	private JButton stfbtnSnEdit;
	
	private JButton stfbtnStEdit;
	private int who = 0;

	private JLabel mvIdlbl;
	private JLabel mvTitlelbl;
	private JLabel mvAgelbl;
	private JLabel mvTypelbl;
	
	
	private String colName[] = {"번호", "영화제목", "등급", "장르", "개봉일","이미지"};
	private DefaultTableModel model= new DefaultTableModel(colName,0);
	
	private JTable mvTb = new JTable(model);
	private JScrollPane	mvsr = new JScrollPane(mvTb);
	public StaffForm(int title) {
		
		
		setDefaultLookAndFeelDecorated(true);	//기본 디자인 설정	
		setTitle("Inhabox");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(1000, 500);
		setLayout(new BorderLayout());
		who = title;
		
		setMenu();
		west();
		center();
		
		setVisible(true);
	}
	
	
	private void setMenu() {
		
		JMenuBar stfmenubar = new JMenuBar();
		JMenu stfmenu = new JMenu("계정");
		JMenu stfhelp = new JMenu("도움말");
		
		stfitemLogout = new JMenuItem("로그아웃");
		stfitemLogout.addActionListener(this);
		stfiteminfo = new JMenuItem("프로그램 정보");
		
		stfmenu.add(stfitemLogout);
		stfhelp.add(stfiteminfo);
		
		
		stfmenubar.add(stfmenu);
		stfmenubar.add(stfhelp);
		
		setJMenuBar(stfmenubar);	
	}
	
	
	private void west() {
		JPanel stfWest = new JPanel();
		stfWest.setLayout(new GridLayout(1,2,10,10));
		stfWest.setBackground(new Color(52, 29, 92));
		
	//--------------------오른쪽 패널--------------------------	
		JPanel rp1 = new JPanel();
		rp1.setLayout(new GridLayout(5,1,0,40));
		rp1.setBorder(BorderFactory.createEmptyBorder(50,5,30,5));
		rp1.setBackground(new Color(62, 35, 110));
		
		stfbtnMvEdit = new JButton("영화 관리");
		stfbtnMvEdit.setBackground(new Color(93, 52, 165));
		stfbtnMvEdit.setForeground(Color.white);

		stfbtnMvTime = new JButton("상영 관리");
		stfbtnMvTime.setBackground(new Color(93, 52, 165));
		stfbtnMvTime.setForeground(Color.white);

		stfbtnSnEdit = new JButton("매점 관리");
		stfbtnSnEdit.setBackground(new Color(93, 52, 165));
		stfbtnSnEdit.setForeground(Color.white);
	
		stfbtnStEdit = new JButton("직원 관리");
		stfbtnStEdit.setBackground(new Color(93, 52, 165));
		stfbtnStEdit.setForeground(Color.white);

		rp1.add(stfbtnMvEdit);
		rp1.add(stfbtnMvTime);
		rp1.add(stfbtnSnEdit);
		rp1.add(stfbtnStEdit);

		if(who == 1) {
			stfbtnStEdit.setVisible(true);
		}else {
			stfbtnStEdit.setVisible(false);			
		}
	//-----------------------왼쪽패널-----------------------	
		JPanel rp = new JPanel();
		rp.setLayout(new GridLayout(5,1,0,40));
		rp.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		rp.setBackground(new Color(62, 35, 110));
		rp.setBorder(BorderFactory.createEmptyBorder(50,5,30,5));


		
		stfWest.add(rp);
		stfWest.add(rp1);

		 add(stfWest, BorderLayout.WEST);

	}	

	
	private void center() {
		
		JPanel stfcenter = new JPanel();
		stfcenter.setLayout(new FlowLayout());
		stfcenter.setBackground(new Color(72, 41, 128));
		
		stfcenter.add(mvsr);
		
		stDba stdba = new stDba();
		
		
		try {
			stdba.stDBa(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		final TableColumnModel columnModel = mvTb.getColumnModel();
		for(int col = 0; col < mvTb.getColumnCount(); col++) {
			int wd = 50;
			for(int row = 0; row < mvTb.getRowCount(); row++) {
				TableCellRenderer redd = mvTb.getCellRenderer(row, col);
				Component comp = mvTb.prepareRenderer(redd, row, col);
				wd = Math.max(comp.getPreferredSize().width+1, wd);
			}
			columnModel.getColumn(col).setPreferredWidth(wd);
		}
		

		add(stfcenter, BorderLayout.CENTER);
		
		
	}
	
	
	public class stDba {
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		//드라이버 주소
		static final String DB_URL ="jdbc:mysql://localhost:3306/movie";
		//DB접속
		//localhost 는 접속하려는 데이터베이스 주소를 입력하시면 됩니다.
		//3306은 데이터베이스에 접속할때 사용하는 포트 번호입니다. 설치할때설정한 것
		//databasename에는 접속하려는 database의 name을 입력해 줍니다.
		static final String USERNAME = "root";
		static final String PASSWORD = "1234";
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rsa= null;
		
		
		public void stDBa(int stState) throws SQLException{
			
			System.out.print("Movie DB 접속 : ");
			
			try {
				Class.forName(JDBC_DRIVER);
				//class클래스의 forName()함수를 이용해서 해당클래스를메모리로로드
				//url, id, password 를 입력하여 데이터페이스에 접속
				conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
				stmt = conn.createStatement();
				
				if(conn !=null) {
					System.out.println("접속 성공");
					
					switch (stState) {
					case 1:
						System.out.println("영화 조회 : ...");
						String sql = "Select mvID, mvTitle, mvAge, mvType, mvWhen, mvimg from movieinfo;";
						rsa = stmt.executeQuery(sql);			
						if(rsa.next()) {							
							sql = "Select mvID, mvTitle, mvAge, mvType, mvWhen, mvimg from movieinfo;";
							rsa = stmt.executeQuery(sql);			
							while(rsa.next()) {
								byte[] image = null;
								image = rsa.getBytes(6);
								Image img = Toolkit.getDefaultToolkit().createImage(image);
								ImageIcon icon = new ImageIcon(img);
								model.addRow(new Object[] {rsa.getInt(1), rsa.getString(2), rsa.getInt(3)+"세", rsa.getString(4), rsa.getString(5), icon});									

							}
															
						}else {
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
				System.out.println(e.getMessage());				
				System.out.println("class not found exection");
			} catch (Exception e) {
				System.out.println(e.getMessage());				
				System.out.println("sql exception : " + e.getMessage());
			}finally {
				rsa.close();
				stmt.close();
				conn.close();
			}

		}
	}
	
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == stfitemLogout) {
			int loginfo = JOptionPane.showConfirmDialog(null, "로그이웃 하시겠습니까?","로그아웃",JOptionPane.YES_NO_OPTION);			
			if(loginfo == JOptionPane.CLOSED_OPTION) {
				
			}else {
				dispose();
				Login lg = new Login("로그인");

			}
		}
	}

}

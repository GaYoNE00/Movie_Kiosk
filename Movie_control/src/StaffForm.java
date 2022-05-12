import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StaffForm extends JFrame implements ActionListener{
	
	private JMenuItem stfitemLogout;
	private JMenuItem stfiteminfo;

	public StaffForm(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 300);
		setLayout(new BorderLayout());

		
		
		
		setMenu();
		
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
	
	public void west() {
		JPanel stfWest = new JPanel();
		
		
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

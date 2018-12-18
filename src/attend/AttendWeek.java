package attend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Frames.Connect_to;

public class AttendWeek extends JPanel{
	private String[][] student;	//�л� �̸� �迭
	//private ArrayList<ArrayList<String>> student;
	private JComboBox myWeek;	//�� ���� �ȳ�
	
	//private JRadioButton[][] weekState;	//�ֺ� ����, 3*41
	//private ButtonGroup[] weekGroup;		//��ư�׷�
	//private JLabel[] studentLabel;	//�л���
	private ArrayList< ArrayList<JRadioButton> > weekState;
	private ArrayList<ButtonGroup> weekGroup;	//��ư�׷�
	private ArrayList<JLabel> studentLabel;
	
	private String currentWeek;	//���� �̸�
	private String[] weekName;	//�� �̸�
	
	JPanel studentPanel;
	JPanel wPanel;
	
	int length;	//����
	
	//��ũ��
	JScrollPane scrollpane;
	
	JButton saveButton;
	
	public AttendWeek(){
		//setLayout(new GridLayout(0,1));
		setLayout(new BorderLayout());
		createComboBox();
		createSaveBtn();
		currentWeek = weekName[0];	//default��
		attendPrint();
		updateUI();
	}
	
	public void createSaveBtn() {
		saveButton = new JButton("����");
		wPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = Connect_to.makeConnection();
				PreparedStatement pstmt = null;
				
				//String currentState = null;
				try {
					//String sql = new String("UPDATE attend SET "+ currentWeek+"= (?) where name = (?)");
					//pstmt = con.prepareStatement(sql);
					String sql;
					Statement stmt = con.createStatement();
					int rs;
					String temp = null;
					
					for(int i=0;i<length; i++) {
						for(int j=0;j<3;j++) {
							if(weekState.get(i).get(j).isSelected()) {
								temp = weekState.get(i).get(j).getText();
								break;
							}
						}
						sql = "UPDATE attend SET " + currentWeek + " = \"" +temp+"\" where name = \""+student[i][0]+"\""; 
						rs = stmt.executeUpdate(sql);
						if(rs > 1) {
							System.out.println("������Ʈ ����");
						}
					}
					
				}catch(SQLException se1){
					se1.printStackTrace();
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					try{
						if(pstmt!=null)
							pstmt.close();
					}catch(SQLException se2){
				}
				try{
					if(con!=null)
						con.close();
					}catch(SQLException se){
						se.printStackTrace();
					}
				}
			}
		});
	}
	
	public void createComboBox() {
		weekName = new String[16];
		
		for(int i=0;i<16;i++) {
			weekName[i] = (i+1)+"week";
		}
		
		myWeek = new JComboBox(weekName);
		
		myWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int index = cb.getSelectedIndex();
				currentWeek = weekName[index];
				attendPrint();
			}
		});
		wPanel = new JPanel();
		wPanel.setLayout(new FlowLayout());
		wPanel.add(myWeek);
		//add(wPanel,0);
		add(wPanel,BorderLayout.NORTH);
		//add(myWeek);
	}
	
	public void attendPrint() {
		Connection con = Connect_to.makeConnection();
		PreparedStatement pstmt = null;
		if(studentPanel != null)
			studentPanel.removeAll();
		studentPanel = new JPanel();
		studentPanel.setLayout(new GridLayout(0,4));	//4�๫�ѿ�
		student = new String[40][2];
		
		try {
			String sql = new String("SELECT name,"+currentWeek+" from attend");
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // resultset�̶�� ���� Ÿ�����ִµ� ����ٰ� exeuteuery(string) �־��ָ� 
			length = 0;
			
			while(rs.next()) {
				student[length][0] = rs.getString("name");
				student[length][1] = rs.getString(currentWeek);
				length++;
			}
			//weekGroup = new ButtonGroup[length];
			//weekState = new JRadioButton[length][3];
			
			if(weekGroup != null) {
				weekGroup.clear();
			}
			if(studentLabel != null) {
				studentLabel.clear();
			}
			if(weekState != null) {
				System.out.println("d");
				weekState.clear();
			}
			weekGroup = new ArrayList<>();
			studentLabel = new ArrayList<>();
			weekState = new ArrayList<>();
			
			for(int j=0;j<length; j++) {
				weekState.add(new ArrayList<>());
				
				weekState.get(j).add(new JRadioButton("�⼮"));
				weekState.get(j).add(new JRadioButton("����"));
				weekState.get(j).add(new JRadioButton("�Ἦ"));
				
				studentLabel.add(new JLabel(student[j][0]));
				studentPanel.add(studentLabel.get(j));
				//studentLabel[j] = new JLabel(student[j][0]);
				
				ButtonGroup group = new ButtonGroup();
				for(int k=0;k<3;k++) {
					//weekGroup[j].add(weekState[j][k]);
					//group.add(weekState[j][k]);
					//studentPanel.add(weekState[j][k]);
					group.add(weekState.get(j).get(k));
					studentPanel.add(weekState.get(j).get(k));
					weekState.get(j).get(k).setSelected(false);
				}
				weekGroup.add(group);
				
				if(student[j][1].equals("�⼮")) {
					//weekState[j][0].setSelected((true));
					weekState.get(j).get(0).setSelected(true);
				}
				else if(student[j][1].equals("����")) {
					//weekState[j][1].setSelected((true));
					weekState.get(j).get(1).setSelected(true);
				}
				else if(student[j][1].equals("�Ἦ")) {
					//weekState[j][2].setSelected((true));
					weekState.get(j).get(2).setSelected(true);
				}
			}
			
			rs.close();
			pstmt.close();
			con.close();				
			}catch(SQLException se1){
				se1.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try{
					if(pstmt!=null)
						pstmt.close();
				}catch(SQLException se2){
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		if(scrollpane != null) {
			scrollpane.removeAll();
			scrollpane.setVisible(false);
			
		}
			//scrollpane.removeAll();
		
		scrollpane = new JScrollPane(studentPanel);
		scrollpane.setPreferredSize(new Dimension(400,400));

		add(scrollpane,BorderLayout.CENTER);
		//scrollpane.updateUI();
		revalidate();
		
		scrollpane.setVisible(true);
	}
}
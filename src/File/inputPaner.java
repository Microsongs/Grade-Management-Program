package File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Frames.Connect_to;
import score.scoreCheck;

public class inputPaner extends JPanel{
	String name;
	String Stunum;
	public inputPaner(){
		JLabel nameL = new JLabel("이름");
		JLabel numL = new JLabel("학번");
		JTextField nameT = new JTextField(14);
		JTextField numT = new JTextField(6);
		JButton OK = new JButton("등록");
	
		this.add(nameL);
		this.add(nameT);
		this.add(numL);
		this.add(numT);
		this.add(OK);
		
		OK.addActionListener(new MyActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = nameT.getText();
				Stunum = numT.getText();
				inputDB();
				try {
					scoreCheck score= new scoreCheck();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		
	}
	public void inputDB() {
		Connection con = Connect_to.makeConnection();
		try {
			String sql = "insert into student(stuid,name,atd, middle, final, assignment) values(?,?,0,0,0,0)";
		 	PreparedStatement pstmt = con.prepareStatement(sql);
		 	pstmt.setString(1, Stunum);
		 	pstmt.setString(2, name);
		 	pstmt.executeUpdate();
		 
		 	String sql2 = "insert into attend(stuid, name, 1week, 2week,3week,4week,5week,6week,7week,"
		 		+ "8week,9week,10week,11week,12week,13week,14week,15week,16week) "
		 		+ "values(?,?,'결석','결석','결석','결석','결석','결석','결석','결석','결석','결석','결석',"
		 		+ "'결석','결석','결석','결석','결석')";
		 	PreparedStatement pstmt2 = con.prepareStatement(sql2);
		 	pstmt2.setString(1, Stunum);
		 	pstmt2.setString(2, name);
		 	pstmt2.executeUpdate();
		 
		 	repaint();
		 
		}catch(Exception ex){
			ex.printStackTrace();
			}finally{
				try{
					if(con!=null)
						con.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
	 
		}
 
	}
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
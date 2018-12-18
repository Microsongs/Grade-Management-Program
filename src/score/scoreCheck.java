package score;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Frames.Connect_to;

public class scoreCheck extends JPanel{
	private Connection con = Connect_to.makeConnection();
	private Statement stmt = con.createStatement();
	private Object[][] students;
	
	public scoreCheck() throws SQLException {
		setPreferredSize(new Dimension(600,500));
		scorePrint();
		createPanel();
	}
	public void scorePrint() throws SQLException {
		String sql = "select * from student";
		ResultSet rs = stmt.executeQuery(sql);
		rs.last();
		int row = rs.getRow();
		students = new Object[row][8];
		rs.beforeFirst();
		int i = 0;
		while(rs.next()) {
			for(int j = 0; j < 8; j++) {
				students[i][j] = rs.getObject(j+1);
			}
			i++;
		}
	}
	public void createPanel() {
		
		String[] title = {"학생ID","이름","중간점수","기말점수","과제점수","출석점수","총점","학점"}; 
		JTable jTable = new JTable(students, title);
		jTable.setEnabled(false);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		//jScrollPane.setEnabled(false);
		//jScrollPane.setPreferredSize(new Dimension(800,300));
		add(jScrollPane);
	}
}

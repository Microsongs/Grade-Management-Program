package File;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Frames.Connect_to;


public class loadFfile extends JFrame{
	File selectedFile;
	public loadFfile() throws IOException{
		 JFileChooser fileChooser = new JFileChooser();
		 
		 fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop"));
		 FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("csv 파일","csv");
		 fileChooser.addChoosableFileFilter(csvfilter);
         
	        //파일오픈 다이얼로그 를 띄움
	        int result = fileChooser.showOpenDialog(this);
	         
	        if (result == JFileChooser.APPROVE_OPTION) {
	            //선택한 파일의 경로 반환
	           selectedFile = fileChooser.getSelectedFile();
	           CSV2DB();
	        }
	        
	}
	public void CSV2DB() throws IOException {
		ArrayList<Student> list = new ArrayList<Student>();
		
		BufferedReader in = new BufferedReader(new FileReader(selectedFile));
		 
		String str;
		try {
			while(true) {
			str = in.readLine();
			if(str == null) break;
			
			StringTokenizer tokens = new StringTokenizer(str,",");
			while(tokens.hasMoreElements()) {
				Student s = new Student();
				s.setStuid(tokens.nextToken());
				s.setName(tokens.nextToken());
				s.setMiddle(tokens.nextToken());
				s.setFin(tokens.nextToken());
				s.setAssignment(tokens.nextToken());
				s.setAtd(tokens.nextToken());
				
				list.add(s);
				}
			}
		}catch(Exception e) {
			class errorLog extends JDialog implements ActionListener{
				public errorLog() {
					JOptionPane.showMessageDialog(null, "잘못된 데이터를 불러왔습니다.", "Failure", JOptionPane.ERROR_MESSAGE);
				    pack();
				    setVisible(true);
				}
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					 dispose();
				}
				
			}
			new errorLog();	//에러 발생
		}
		
		in.close();
		
		 Connection con = Connect_to.makeConnection();
		 try {
			 
			 int size = list.size();
			 try {
				 for(int i =1; i<size; i++) {
					 Student stud = list.get(i);
					 String sql = "insert into student(stuid, name, middle, final, assignment, atd, total, grade)"
							 + " values(?, ?, ?, ?, ?, ?, null, null)";
					 PreparedStatement pstmt = con.prepareStatement(sql);
					 pstmt.setString(1, stud.getStuid());
					 pstmt.setString(2, stud.getName());
					 pstmt.setInt(3, Integer.parseInt(stud.getMiddle()));
					 pstmt.setInt(4, Integer.parseInt(stud.getFin()));
					 pstmt.setInt(5, Integer.parseInt(stud.getAssignment()));
					 pstmt.setInt(6, Integer.parseInt(stud.getAtd()));
					 pstmt.executeUpdate();
					 String sql2 = "insert into attend(stuid, name, 1week, 2week,3week,4week,5week,6week,7week,"
							 + "8week,9week,10week,11week,12week,13week,14week,15week,16week) "
							 + "values(?,?,'출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석',"
							 + "'출석','출석','출석','출석','출석')";
					 PreparedStatement pstmt2 = con.prepareStatement(sql2);
					 pstmt2.setString(1,stud.getStuid());
					 pstmt2.setString(2, stud.getName());
					 pstmt2.executeUpdate();
				 }
			 }catch(Exception e) {
				dialog dialog = new dialog();
				
				
			 }
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
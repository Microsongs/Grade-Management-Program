package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Frames.Connect_to;


public class saveFfile extends JFrame{
	File selectedFile;
	public saveFfile() throws IOException{
	 JFileChooser fileChooser = new JFileChooser();
	 
	 fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop"));
	 FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("csv 파일","csv");
	 fileChooser.addChoosableFileFilter(csvfilter);
    
       //파일오픈 다이얼로그 를 띄움
       int result = fileChooser.showSaveDialog(this);
        
       if (result == JFileChooser.APPROVE_OPTION) {
           //선택한 파일의 경로 반환
           selectedFile = fileChooser.getSelectedFile();
           DB2CSV();
          
       }
	}
	public void DB2CSV() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(selectedFile));
		
		Connection con = Connect_to.makeConnection();
		try {
			String sql = "select * from student";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			out.append("stuid");
			out.append(",");
			out.append("name");
			out.append(",");
			out.append("middle");
			out.append(",");
			out.append("final");
			out.append(",");
			out.append("assignment");
			out.append(",");
			out.append("atd");
			out.append('\n');
			while(rs.next()) {
				out.append(rs.getString(1));
				out.append(',');
				out.append(rs.getString(2));
				out.append(',');
				out.append(rs.getString(3));
				out.append(',');
				out.append(rs.getString(4));
				out.append(',');
				out.append(rs.getString(5));
				out.append(',');
				out.append(rs.getString(6));
				out.append('\n');
			}
			out.flush();
			out.close();
			con.close();
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
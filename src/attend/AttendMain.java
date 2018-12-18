package attend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Frames.Connect_to;
import score.inputScore;

public class AttendMain extends JPanel{
	private String[] printWeek;
	private String[][] attendState;
	private JButton updateButton;	//버튼
	
	//생성자
	public AttendMain(){
		//setLayout(new GridLayout(2,1));
		setLayout(new BorderLayout());
		attendButton();
		attendPrint();
	}
	
	public void attendButton() {
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout());
		updateButton = new JButton("출석점수 반영");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = Connect_to.makeConnection();
				PreparedStatement pstmt = null;
				
				try {
					String sql;
					Statement stmt = con.createStatement();
					int rs;
					String temp = null;
					
					sql = "select * from attend"; // string 변수에다 넣어줌
					ResultSet temprs = stmt.executeQuery(sql); // resultset이라는 변수 타입이있는데 여기다가 exeuteuery(string) 넣어주면 
					
					int attendNum[] = new int[40];	//배열 초기화
					String stuid[] = new String[40];	//배열 초기화
					int nameTemp = 0;
					
					while(temprs.next()) {
						int maxPoint = 100;
						String tempString;
						stuid[nameTemp] = temprs.getString(1);
						for(int i=1;i<=16;i++) {
							tempString = temprs.getString(i+1);
							if(tempString.equals("지각") && maxPoint >= 5) {
								maxPoint -= 5;
							}
							else if(tempString.equals("결석") && maxPoint >= 10) {
								maxPoint -= 10;
							}
							if(maxPoint == 0) {
								break;
							}
						}
						attendNum[nameTemp] = maxPoint;
						nameTemp++;
					}
					
					for(int i=0;i<nameTemp; i++) {
						//sql = "update score1.student set atd = "+attendNum[i]+" where 'name' = '" + attendState[i][0] + "'";
						sql = "update score1.student set atd = "+attendNum[i]+" where stuid = '" + stuid[i] + "'";
						//System.out.println(stuid[i] +" : " + attendNum[i]);
						rs = stmt.executeUpdate(sql);
						if(rs > 1) 
							System.out.println("업데이트 실패");
					}
					inputScore score = new inputScore();
					score.totalCalculate();
					score.setGradeValue();
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		subPanel.add(updateButton);
		add(subPanel,BorderLayout.NORTH);
	}
	
	//전체 함수를 보여주는 함수
	public void attendPrint() {
		Connection con = Connect_to.makeConnection(); // DB 연결
		Statement stmt = null;
		
		attendState = new String[40][17];
		int nameTemp = 0;	//이름별위치
		
		try {
			stmt = con.createStatement();
			String sql;
			sql = "select * from attend"; // string 변수에다 넣어줌
			ResultSet rs = stmt.executeQuery(sql); // resultset이라는 변수 타입이있는데 여기다가 exeuteuery(string) 넣어주면 
			while(rs.next()) {
				//name[nameTemp] = rs.getString("name");
				attendState[nameTemp][0] = rs.getString("name");
				for(int i=1;i<=16;i++) {
					attendState[nameTemp][i] = rs.getString(i+1);
				}
				nameTemp++;
			}
			
			rs.close();
			stmt.close();
			con.close();				
			}catch(SQLException se1){
				se1.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		printWeek = new String[]{"이름","1주","2주","3주","4주","5주","6주","7주","8주","9주","10주","11주","12주","13주","14주","15주","16주"};
		//테이블 모델 추가, 학생, 이름
		DefaultTableModel mod = new DefaultTableModel(attendState,printWeek) {
			public boolean isCellEditable(int rowIndex,int mCollIndex) {
				return false;
			}
		};
		
		createTable();
	}
	
	//테이블을 생성하는 메서드
	public void createTable() {
		DefaultTableModel mod = new DefaultTableModel(attendState,printWeek) {
			public boolean isCellEditable(int rowIndex,int mCollIndex) {
				return false;
			}
		};
		JTable table = new JTable(mod);
		table.getTableHeader().setReorderingAllowed(false);	//이동 불가
		table.getTableHeader().setResizingAllowed(false);
		table.setRowHeight(800,600);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(800,400));
		
		add(scrollpane,BorderLayout.CENTER);
	}
}

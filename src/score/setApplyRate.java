package score;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Frames.Connect_to;

public class setApplyRate extends JPanel{
	private Connection con = Connect_to.makeConnection();
	private Statement stmt = con.createStatement();
	private String m_rate, f_rate, at_rate, as_rate;
	private int m_update, f_update, at_update, as_update;
	private JPanel setRateContainer;
	private JTextField midInput, finalInput, assignInput, atdInput;
	private JButton applyUpdate;
	private inputScore minput;
	
	// 생성자
	public setApplyRate() throws SQLException{
		JPanel jp = new JPanel();
		minput = new inputScore();
		//this.setLocation(100,150);
		createApplyRate();
		add(setRateContainer);
	}
	
	// panel을 생성하고 db를 연결
	public void createApplyRate() throws SQLException {
		String sql = "select * from rate";
		
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		m_rate = Integer.toString(rs.getInt("mid_rate"));
		f_rate = Integer.toString(rs.getInt("final_rate"));
		at_rate = Integer.toString(rs.getInt("atd_rate"));
		as_rate = Integer.toString(rs.getInt("assign_rate"));
		
		createPanel();
		applyUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_update = Integer.parseInt(midInput.getText());
				f_update = Integer.parseInt(finalInput.getText());
				at_update = Integer.parseInt(atdInput.getText());
				as_update = Integer.parseInt(assignInput.getText());
				int sum = m_update + f_update + at_update + as_update;
				if(sum!=100) {
					JOptionPane.showMessageDialog(null, "비율을 100으로 맞춰서 입력바랍니다.");
				}
				else {
					String update = "update rate set mid_rate = "+m_update+", final_rate = "+f_update+", assign_rate = "+as_update
							+", atd_rate = "+at_update;
					try {
						stmt.executeUpdate(update);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "수정되었습니다.");
					minput.totalCalculate();
					minput.setGradeValue();
				}
			}
			
		});
	}
	
	// 반영비율을 수정할수있는 component들이 포함되어 있는 panel생성
	public void createPanel() {
		//setTitle("반영 비율 설정");
		setRateContainer = new JPanel();
		//setContentPane(setRateContainer);
		JPanel bottom = new JPanel();
		JPanel top = new JPanel();
		setRateContainer.setLayout(new GridLayout(0,1));
		bottom.setLayout(new GridLayout(1,0,10,10));
		JLabel rateinfo = new JLabel("< 점수 반영 비율(%) >");
		top.add(rateinfo);
		JLabel midRate = new JLabel("중간시험 : ");
		//midRate.setLocation(20,20);
		midRate.setSize(100, 20);
					
		midInput = new JTextField();
		midInput.setText(m_rate);
		//midInput.setLocation(110, 20);
		midInput.setSize(150, 20);
		
		JLabel finalRate = new JLabel("기말시험: ");
		//finalRate.setLocation(20, 45);
		finalRate.setSize(100, 20);
		
		finalInput = new JTextField();
		finalInput.setText(f_rate);
		//finalInput.setLocation(110, 45);
		finalInput.setSize(150,20);
		
		JLabel assignRate = new JLabel("과제점수: ");
		//assignRate.setLocation(20, 70);
		assignRate.setSize(100, 20);
		
		assignInput = new JTextField();
		assignInput.setText(as_rate);
		//assignInput.setLocation(110, 70);
		assignInput.setSize(150, 20);
		
		JLabel atdRate = new JLabel("출석점수: ");
		//atdRate.setLocation(20, 95);
		atdRate.setSize(100, 20);
		
		atdInput = new JTextField();
		atdInput.setText(at_rate);
		//atdInput.setLocation(110, 95);
		atdInput.setSize(150, 20);
		
		applyUpdate = new JButton("수정");
		applyUpdate.setSize(100,20);
		//applyUpdate.setLocation(110, 130);
		
		//setRateContainer.setLayout(null);
		
		bottom.add(midRate);
		bottom.add(midInput);
		bottom.add(finalRate);
		bottom.add(finalInput);
		bottom.add(assignRate);
		bottom.add(assignInput);
		bottom.add(atdRate);
		bottom.add(atdInput);
		bottom.add(applyUpdate);
		
		setRateContainer.add(top);
		setRateContainer.add(bottom);
		//setSize(250, 200);
		//setResizable(false);
		//setVisible(true);
		
	}
	
}
package score;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import Frames.mainFrame;

public class setRankRate extends JPanel{
	private Statement stmt;
	private Connection con;
	private String ap_rate, az_rate, bp_rate, bz_rate, cp_rate, cz_rate, dz_rate, fz_rate;
	private int ap_update, az_update, bp_update, bz_update, cp_update, cz_update, dz_update, fz_update;
	private JPanel setRateContainer;
	private JButton updateRate;
	private JTextField apInput, azInput, bpInput, bzInput, cpInput, czInput, dzInput, fzInput;
	private inputScore minput;
	
	public setRankRate() throws SQLException{
		minput = new inputScore();
		//this.setLocation(100, 150);
		setPreferredSize(new Dimension(500,600));
		createRankRate();
		add(setRateContainer);
	}
	
	// DB에 연결하고 Frame을 생성
	public void createRankRate() throws SQLException {
		con = Connect_to.makeConnection();
		stmt = con.createStatement();		
		
		String sql = "select * from myrank";
		
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		ap_rate = Integer.toString(rs.getInt("aplus"));
		az_rate = Integer.toString(rs.getInt("azero"));
		bp_rate = Integer.toString(rs.getInt("bplus"));
		bz_rate = Integer.toString(rs.getInt("bzero"));
		cp_rate = Integer.toString(rs.getInt("cplus"));
		cz_rate = Integer.toString(rs.getInt("czero"));
		dz_rate = Integer.toString(rs.getInt("d"));
		fz_rate = Integer.toString(rs.getInt("f"));
		createPanel();
		
		// 수정button이 클릭되면 수정된 값을 받아 db에 update
		updateRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ap_update = Integer.parseInt(apInput.getText());
				az_update = Integer.parseInt(azInput.getText());
				bp_update = Integer.parseInt(bpInput.getText());
				bz_update = Integer.parseInt(bzInput.getText());
				cp_update = Integer.parseInt(cpInput.getText());
				cz_update = Integer.parseInt(czInput.getText());
				dz_update = Integer.parseInt(dzInput.getText());
				fz_update = Integer.parseInt(fzInput.getText());
				int sum = ap_update + az_update + bp_update + bz_update + cp_update + cz_update + dz_update + fz_update;
				if(sum != 100 || sum < 0) {
					JOptionPane.showMessageDialog(null, "비율을 100에 맞춰서 입력바랍니다.");
				}
				else {
					String rate = "update myrank set aplus = "+ap_update+", azero = "+az_update+", bplus = "+bp_update
							+", bzero = "+bz_update+", cplus = "+cp_update+", czero = "+cz_update+", d ="+dz_update+", f = "+fz_update;
					try {
						stmt.executeUpdate(rate);
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
	
	// 등급 비율을 설정하는 component들이 포함되어 있는 Panel 생성
	public void createPanel() {
		//setTitle("등급 비율 설정");
		setRateContainer = new JPanel();
		//setContentPane(setRateContainer);
		setRateContainer.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		//p1.setPreferredSize(new Dimension(50,20));
		p1.setLayout(new GridLayout(0,1,30,30));
		
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(100,20));
		p2.setLayout(new GridLayout(0,1,30,30));
		
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		
		JLabel rankInfo = new JLabel("< 등급 비율 반영(%) >");
		p4.add(rankInfo);
		JLabel apRate = new JLabel("A+ : ");
		//apRate.setLocation(20,20);
		apRate.setSize(30, 20);
					
		apInput = new JTextField();
		apInput.setText(ap_rate);
		//apInput.setLocation(70, 20);
		apInput.setSize(100, 20);
		
		
		
		JLabel azRate = new JLabel("A0 : ");
		//azRate.setLocation(20,45);
		azRate.setSize(30, 20);
					
		azInput = new JTextField();
		azInput.setText(az_rate);
		//azInput.setLocation(70, 45);
		azInput.setSize(100, 20);
		
		JLabel bpRate = new JLabel("B+ : ");
		//bpRate.setLocation(20,70);
		bpRate.setSize(30, 20);
					
		bpInput = new JTextField();
		bpInput.setText(bp_rate);
		//bpInput.setLocation(70, 70);
		bpInput.setSize(100, 20);
		
		JLabel bzRate = new JLabel("B0 : ");
		//bzRate.setLocation(20, 95);
		bzRate.setSize(30, 20);
					
		bzInput = new JTextField();
		bzInput.setText(bz_rate);
		//bzInput.setLocation(70, 95);
		bzInput.setSize(100, 20);
		
		JLabel cpRate = new JLabel("C+ : ");
		//cpRate.setLocation(20, 120);
		cpRate.setSize(30, 20);
					
		cpInput = new JTextField();
		cpInput.setText(cp_rate);
		//cpInput.setLocation(70, 120);
		cpInput.setSize(100, 20);
		
		JLabel czRate = new JLabel("C0 : ");
		//czRate.setLocation(20, 145);
		czRate.setSize(30, 20);
					
		czInput = new JTextField();
		czInput.setText(cz_rate);
		//czInput.setLocation(70, 145);
		czInput.setSize(100, 20);
		
		JLabel dzRate = new JLabel("D0 : ");
		//dzRate.setLocation(20, 170);
		dzRate.setSize(30, 20);
					
		dzInput = new JTextField();
		dzInput.setText(dz_rate);
		//dzInput.setLocation(70, 170);
		dzInput.setSize(100, 20);
		
		JLabel fzRate = new JLabel("F0 : ");
		//fzRate.setLocation(20, 195);
		fzRate.setSize(30, 20);
					
		fzInput = new JTextField();
		fzInput.setText(fz_rate);
		//fzInput.setLocation(70, 195);
		fzInput.setSize(100, 20);
		
		updateRate = new JButton("수정");
		//updateRate.setLocation(70, 235);
		updateRate.setSize(100,20);
		
		//setRateContainer.setLayout(null);
		
		p1.add(apRate);
		p2.add(apInput);
		p1.add(azRate);
		p2.add(azInput);
		p1.add(bpRate);
		p2.add(bpInput);
		p1.add(bzRate);
		p2.add(bzInput);
		p1.add(cpRate);
		p2.add(cpInput);
		p1.add(czRate);
		p2.add(czInput);
		p1.add(dzRate);
		p2.add(dzInput);
		p1.add(fzRate);
		p2.add(fzInput);
		
		p3.add(updateRate);
		
		//setRateContainer.setSize(400, 50);
		setRateContainer.add(p1, BorderLayout.WEST);
		setRateContainer.add(p2, BorderLayout.CENTER);
		setRateContainer.add(p3, BorderLayout.SOUTH);
		setRateContainer.add(p4, BorderLayout.NORTH);
		//setSize(250, 300);
		//setResizable(false);
		//setVisible(true);
	}
	
}
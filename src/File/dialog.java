package File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class dialog extends JDialog implements ActionListener{

	
	public dialog() {
		JOptionPane.showMessageDialog(null, "이미 등록된 정보가 있습니다.", "Failure", JOptionPane.ERROR_MESSAGE);
	    pack();
	    setVisible(true);
	     
	}
	     
	     public void actionPerformed(ActionEvent e) {
	         dispose();
	     }
}

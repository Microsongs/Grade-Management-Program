package File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class dialog extends JDialog implements ActionListener{

	
	public dialog() {
		JOptionPane.showMessageDialog(null, "�̹� ��ϵ� ������ �ֽ��ϴ�.", "Failure", JOptionPane.ERROR_MESSAGE);
	    pack();
	    setVisible(true);
	     
	}
	     
	     public void actionPerformed(ActionEvent e) {
	         dispose();
	     }
}

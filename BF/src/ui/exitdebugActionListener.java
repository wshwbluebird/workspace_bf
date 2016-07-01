package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class exitdebugActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame2.debugpanel.setVisible(false);
		MainFrame2.debugpanel = null;
		MainFrame2.exitdebug.setForeground(Color.GRAY);
//		MainFrame2.textAreacode.setVisible(true);
//		MainFrame2.textAreainput.setVisible(true);
		
	}

}

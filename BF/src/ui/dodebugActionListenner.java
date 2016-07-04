package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dodebugActionListenner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		MainFrame2.exitdebug.addActionListener(new exitdebugActionListener());
		MainFrame2.exitdebug.setForeground(Color.BLACK);
		MainFrame2.debugpanel = new DebugPanel(MainFrame2.textAreacode.getText(),MainFrame2.textAreainput.getText());
		MainFrame2.debugpanel.setSize(500,350);
		
		
	
	
		
		
		
		
		
		
		
	}

}

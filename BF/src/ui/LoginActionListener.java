package ui;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public  class LoginActionListener implements ActionListener{
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("sign up")){
	    MainFrame2.signupD = new signupDialog();
	    MainFrame2.signupD.setBounds(500, 360, 400, 180);
	    MainFrame2.signupD.show();
		}else if (cmd.equals("sign in")){
			MainFrame2.signinD = new signinDialog();
			MainFrame2.signinD.setBounds(500, 360, 400, 180);
			MainFrame2.signinD.show();
	    }
		
	}
	
}

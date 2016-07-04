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
	    MainFrame2.signupD = new signupDialog();  //进入注册 对话框
	    MainFrame2.signupD.setBounds(500, 360, 400, 180);
	    MainFrame2.signupD.show();
		}else if (cmd.equals("sign in")){
			MainFrame2.signinD = new signinDialog();  // 进入 登入对话框
			MainFrame2.signinD.setBounds(500, 360, 400, 180);
			MainFrame2.signinD.show();
	    }
		
	}
	
}

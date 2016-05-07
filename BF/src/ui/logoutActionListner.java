package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logoutActionListner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame2.username="";
		MainFrame2.CurrentFileName="";
		MainFrame2.loginState=false;
		MainFrame2.login.remove(MainFrame2.logout);
		MainFrame2.login.setText("Login");
		MainFrame2.login.add(MainFrame2.signin);
		MainFrame2.login.add(MainFrame2.signup);
		MainFrame2.textAreacode.setText("");
		MainFrame2.textAreainput.setText("");
		MainFrame2.resultLabel.setText("result");
		MainFrame2.openMenuItem.removeAll();
		MainFrame2.versionMenu.removeAll();

	}

}

package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import rmi.RemoteHelper;

public class SaveActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = MainFrame2.textAreacode.getText();
		try {
			RemoteHelper.getInstance().getIOService().writeFile(code, MainFrame2.username, MainFrame2.CurrentFileName);
			System.out.println("fdsd");
			new fileOpenmake();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	  }
   }
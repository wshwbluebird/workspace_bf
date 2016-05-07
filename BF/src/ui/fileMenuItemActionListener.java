package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import rmi.RemoteHelper;

public class fileMenuItemActionListener implements ActionListener {
	/**
	 * 子菜单响应事件
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("New")) {
			MainFrame2.newfileD =new newFileDialog();
			MainFrame2.newfileD.setBounds(500, 360, 400, 180);
			MainFrame2.newfileD.show();
			MainFrame2.textAreacode.setText("");
		}
	}
}

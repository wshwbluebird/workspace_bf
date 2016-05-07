package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import rmi.RemoteHelper;

public class codeExecutedActionListenrer implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = MainFrame2.textAreacode.getText();
		String input = MainFrame2.textAreainput.getText();
		try {
			String result = RemoteHelper.getInstance().getExecuteService().execute(code, input);
			MainFrame2.resultLabel.setText(result);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}

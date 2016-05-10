package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import rmi.RemoteHelper;

public class undoredoActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("pointer    "+MainFrame2.urcontrol.pointer);
		String cmd = e.getActionCommand();
		System.out.println("++");
		if(cmd.equals("undo")&&MainFrame2.urcontrol.pointer>1&&MainFrame2.loginState){
			try {
				MainFrame2.urcontrol.pointer--;
				System.out.println("--");
				String text =  RemoteHelper.getInstance().getTempService()
						.getPointFile(MainFrame2.urcontrol.pointer);
				if(text.equals("####nothing^^")){
					MainFrame2.urcontrol.pointer++;
				}else{
					MainFrame2.textAreacode.setText(text);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MainFrame2.urcontrol.checkredo();
			MainFrame2.urcontrol.checkundo();
		}else if(cmd.equals("redo")&&MainFrame2.urcontrol.checkredo()&&MainFrame2.loginState){
			try {
				String text =  RemoteHelper.getInstance().getTempService()
						.getPointFile(++MainFrame2.urcontrol.pointer);
				
				if(text.equals("####nothing^^")){
					MainFrame2.urcontrol.pointer--;
				}else{
					MainFrame2.textAreacode.setText(text);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MainFrame2.urcontrol.checkredo();
			MainFrame2.urcontrol.checkundo();
	    }
       
    }
}

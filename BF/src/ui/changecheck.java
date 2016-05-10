package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import rmi.RemoteHelper;

public class changecheck implements KeyListener{
	String temp1 ;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(MainFrame2.threadflag&&!(e.getKeyChar()=='\n')){
			temp1 = MainFrame2.textAreacode.getText();
			
			MainFrame2.fileedit.setText("edit");
			mythread  t= new mythread();
			Thread t1 = new Thread(t);
			t1.start();
		}
			  
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
   class mythread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		MainFrame2.threadflag = false;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp2 = MainFrame2.textAreacode.getText();
		if(!temp2.equals(temp1)){
			try {
				RemoteHelper.getInstance().getTempService().createTempFile(temp2, ++MainFrame2.urcontrol.pointer);
				MainFrame2.urcontrol.maxpointer = MainFrame2.urcontrol.pointer;
				MainFrame2.urcontrol.checkredo();
				MainFrame2.urcontrol.checkundo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		MainFrame2.threadflag = true;
	}
	   
   }
}
   
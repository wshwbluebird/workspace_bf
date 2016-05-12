package ui;

import java.awt.Color;
import java.rmi.RemoteException;

import rmi.RemoteHelper;

public class undoredoController {
	int maxpointer;
	int pointer;
	public undoredoController(){
		maxpointer = 0;
		pointer = 0;
		MainFrame2.textAreacode.addKeyListener(new changecheck());
		try {
			System.out.println("bbbbbbbbbbbbbbb\naaaaaaaaa");
			String code = MainFrame2.textAreacode.getText();
			RemoteHelper.getInstance().getTempService().createTempFile(code, ++pointer);
			maxpointer++;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean checkundo(){
		//MainFrame2.undo.removeAll();	
		//MainFrame2.undo.removeActionListener(null);
		if(pointer>1){
			//MainFrame2.undo.addActionListener(new undoredoActionListener());
			System.out.println("holy )))");
			MainFrame2.undo.setForeground(Color.black);
			return true;
		}else {
			MainFrame2.undo.setForeground(Color.gray);
			return false;
		}
	}
	public boolean checkredo(){
		//MainFrame2.redo.removeAll();
		//MainFrame2.redo.removeActionListener(null);
		if(pointer<maxpointer){		
			//MainFrame2.redo.addActionListener(new undoredoActionListener());
			MainFrame2.redo.setForeground(Color.black);
			return true;
		}else {
			MainFrame2.redo.setForeground(Color.gray);
			return false;
		}
	}
	public void refresh(){
		this.maxpointer = 0;
		this.pointer = 0;
//		MainFrame2.textAreacode.addKeyListener(new changecheck());
		try {
			String code = MainFrame2.textAreacode.getText();
			RemoteHelper.getInstance().getTempService().createTempFile(code, ++pointer);
			maxpointer++;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

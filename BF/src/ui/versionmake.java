package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JMenuItem;

import rmi.RemoteHelper;

public class versionmake implements ActionListener{
         String versionlistInfo;
	     public versionmake(){
	    	 try {
	    		MainFrame2.versionMenu.removeAll();
	    		if(MainFrame2.CurrentFileName!=null&&!"".equals(MainFrame2.CurrentFileName.trim())){
				versionlistInfo = RemoteHelper.getInstance().getIOService()
						 .controlRead(MainFrame2.username,MainFrame2.CurrentFileName);
				System.out.println("after version info read?");
				String[] versionlist = versionlistInfo.split(";");
				for (int i = 0; i < versionlist.length; i++) {
					JMenuItem file1 = new JMenuItem(versionlist[i]);
					
					file1.addActionListener(this);
					MainFrame2.versionMenu.add(file1);
				 }
	    		}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
         }
		@Override
		public void actionPerformed(ActionEvent e) {
			String versionname = e.getActionCommand();
			try {
				String content =  RemoteHelper.getInstance().getIOService()
						 .versionRead(MainFrame2.username,MainFrame2.CurrentFileName, versionname);
				MainFrame2.textAreacode.setText(content);
				MainFrame2.resultLabel.setText("result");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
         
}

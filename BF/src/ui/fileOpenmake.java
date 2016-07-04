package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JMenuItem;

import rmi.RemoteHelper;

public class fileOpenmake implements ActionListener{
      String fileListInfo;
      public fileOpenmake() {
    	  
    	  MainFrame2.openMenuItem.removeAll();//重写  open键 下面的内容
    	  try {
    		  String last = RemoteHelper.getInstance().getIOService().controlRead(MainFrame2.username, "xml");
    		  //username.xml 用于保存最新建立的文件的名字
    		  System.out.println("last:  "+last);
  			if(!"".equals(last.trim())){//如果上次有文件   那么直接加载进来
  				MainFrame2.CurrentFileName = last;
  				//调用服务器方法   获取 last文件的内容 直接显示到代码版上
  				String t = RemoteHelper.getInstance().getIOService().readFile(MainFrame2.username, last);
  				System.out.println("text:    "+t);
  				MainFrame2.textAreacode.setText(t);
  				MainFrame2.filename.setText(MainFrame2.CurrentFileName);
  				//  撤销重做的 坚实线程更新
  				MainFrame2.urcontrol.refresh();
  				System.out.println("open make");
  			}
			fileListInfo = RemoteHelper.getInstance().getIOService().readFileList(MainFrame2.username);
			System.out.println("fileinfo:"+fileListInfo  );
			String[] filelist = fileListInfo.split(";");
			for (int i = 0; i < filelist.length; i++) {
				JMenuItem file1 = new JMenuItem(filelist[i]);
				file1.addActionListener(this);
				MainFrame2.openMenuItem.add(file1);
			}
			System.out.println("befor version make?");
			new versionmake();
			System.out.println("wrong?");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String filename = e.getActionCommand();
		MainFrame2.CurrentFileName = filename;
		MainFrame2.filename.setText(MainFrame2.CurrentFileName);
		String value;
		try {
			value = RemoteHelper.getInstance().getIOService().readFile( MainFrame2.username, filename);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			value = "";
		}
		MainFrame2.textAreacode.setText(value);
		MainFrame2.resultLabel.setText("result");
		MainFrame2.urcontrol.refresh();;
		new versionmake();
		
	}
}

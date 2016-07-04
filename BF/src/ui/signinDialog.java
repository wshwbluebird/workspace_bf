

package ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rmi.RemoteHelper;
import ui.MainFrame2;

public class signinDialog extends JDialog implements ActionListener{
	private JLabel label1 = new JLabel("userId");
    private JLabel label2 = new JLabel("password");
    private JTextField userIdtext = new JTextField(50);
    private JPasswordField passwordtext = new JPasswordField(50);
    private JButton affirm = new JButton("affirm");  //确认按钮 的事件监听
    private JButton cancel = new JButton("cancle");
    private String userId="";
    private String password="";
    
    public signinDialog() {
    	 //super(parent,modal);
    	//对话框的 样子   
         setTitle("Sign in BF ide");
         setSize(260,140);
         setResizable(false);
         setLayout(null);
         add(label1);
         add(label2);
         label1.setBounds(50, 30, 65, 20);
         label2.setBounds(50, 60, 65, 20);
         add(userIdtext);
         add(passwordtext);
         userIdtext.setBounds(120, 30, 120, 20);
         passwordtext.setBounds(120, 60, 120, 20);
         add(affirm);
         add(cancel);
         affirm.setBounds(60, 100, 90, 25);
         cancel.setBounds(140, 100, 90, 25);
         affirm.addActionListener(this);
         cancel.addActionListener(this);
	
    }


	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==affirm){
			
			userId = userIdtext.getText();
			password = passwordtext.getText();
			//读取用户输入饿用户名 密码
			try {
				//调用服务器方法进行判定
				if(RemoteHelper.getInstance().getUserService().login(userId, password)){
					JOptionPane.showMessageDialog(null, "Welcome  "+userId,
							"sign in successful",  JOptionPane.PLAIN_MESSAGE); 
				MainFrame2.username = userId;
				MainFrame2.loginState = true;
				
				//main frame 中的组建 替换
				MainFrame2.login.setText(MainFrame2.username);
				MainFrame2.login.remove(MainFrame2.signin);
				MainFrame2.login.remove(MainFrame2.signup);
				MainFrame2.login.add(MainFrame2.logout);
				//需要打开 上次的文件   以及按钮的添加更改
				new fileOpenmake();
    			
				}else{
					JOptionPane.showMessageDialog(null, "wrong userId or wrong passname!",
							"sign in failed",  JOptionPane.ERROR_MESSAGE); 

				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	    MainFrame2.signinD.setVisible(false);
	}

	
}

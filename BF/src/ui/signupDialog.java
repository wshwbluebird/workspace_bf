package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rmi.RemoteHelper;

public class signupDialog extends JDialog implements ActionListener{
	JLabel label1 = new JLabel("userId");
    JLabel label2 = new JLabel("password");
    JTextField userIdtext = new JTextField(50);
    JPasswordField passwordtext = new JPasswordField(50);
    JButton affirm = new JButton("affirm");
    JButton cancel = new JButton("cancle");
   
    
     public signupDialog() {
    	 //super(parent,modal);
    	 //设置 窗体的样式
         setTitle("Sign up for BF ide");
         setSize(260,140);
         setResizable(false);
         setLayout(null);
         add(label1);
         add(label2);
         label1.setBounds(50, 30, 65, 20);
         label2.setBounds(50, 60, 65, 20);
         add(userIdtext);
         add(passwordtext);
//         userId.setText(Integer.toString(ta.getRows()));
//         password.setText(Integer.toString(ta.getColumns()));
         userIdtext.setBounds(120, 30, 120, 20);
         passwordtext.setBounds(120, 60, 120, 20);
         add(affirm);
         add(cancel);
         affirm.setBounds(60, 100, 90, 25);
         cancel.setBounds(140, 100, 90, 25);
         affirm.addActionListener(this);
         cancel.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==affirm){
			
			String userId = userIdtext.getText();
			String password = passwordtext.getText();
			//获取用户想要注册的用户名和密码
			boolean succ;
			try {
				//调用 服务器方法 看注册是否合法
				//如果服务器会直接创建这个新的  用户信息
				succ = RemoteHelper.getInstance().getUserService().signup(userId, password);
				if(succ){
					JOptionPane.showMessageDialog(null, "sign up successful !",
							"sign up successful",  JOptionPane.PLAIN_MESSAGE); 
				}else{
					JOptionPane.showMessageDialog(null, "username exists",
							"sign up failed",  JOptionPane.ERROR_MESSAGE); 
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		MainFrame2.signupD.setVisible(false);
	}
}

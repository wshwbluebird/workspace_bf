package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rmi.RemoteHelper;




public class MainFrame2 extends JFrame {
	/**
	 * 
	 */
	static JTextArea textAreacode;
	static JTextArea textAreainput;
	static JLabel resultLabel;
    static JMenu login ;
	static JMenu openMenuItem;
	static JMenuItem signin = new JMenuItem("sign in");
	static JMenuItem signup = new JMenuItem("sign up");
	static JMenuItem logout = new JMenuItem("logout");
	static JMenu versionMenu = new JMenu("Version");
	static boolean loginState = false;
	static String username;
	static signinDialog  signinD;
	static signupDialog  signupD;
	static newFileDialog newfileD;
	static String CurrentFileName;
	public  MainFrame2() {
		// 创建窗体
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());	
		JPanel MenuBarPanel = new JPanel();
		frame.add(MenuBarPanel,BorderLayout.NORTH);
		JMenuBar menuBarleft = new JMenuBar();
		JMenuBar menuBarright = new JMenuBar();
		MenuBarPanel.add(menuBarleft);
		MenuBarPanel.add(menuBarright);
		JMenu fileMenu = new JMenu("File");
		JMenu runMenu = new JMenu("Run");		
		login = new JMenu();	
		login.setText("login");
		login.add(signin);
		login.add(signup);
		menuBarleft.add(fileMenu);
		menuBarleft.add(runMenu);
		menuBarleft.add(versionMenu);
		menuBarright.add(login);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		openMenuItem = new JMenu("Open");
		fileMenu.add(openMenuItem);
		JMenuItem file1 = new JMenuItem("file1");
		JMenuItem file2 = new JMenuItem("file2");
		JMenuItem file3 = new JMenuItem("file3");
		openMenuItem.add(file1);
		openMenuItem.add(file2);
		openMenuItem.add(file3);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem executeMenuItem = new JMenuItem("Execute");
		runMenu.add(executeMenuItem);
		
		textAreacode = new JTextArea();
		textAreacode.setMargin(new Insets(10, 10, 10, 10));
		textAreacode.setBackground(Color.LIGHT_GRAY);
		frame.add(textAreacode, BorderLayout.CENTER);

		// 显示结果
		JPanel IOpanel  = new JPanel();
		frame.add(IOpanel, BorderLayout.SOUTH);
		
		textAreainput = new JTextArea();
		textAreainput.setMargin(new Insets(10, 10, 10, 10));
		textAreainput.setBackground(Color.LIGHT_GRAY);
		IOpanel.add(textAreainput);
		resultLabel = new JLabel();
		resultLabel.setText("result");
		IOpanel.add(resultLabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocation(400, 200);
		frame.setVisible(true);
		
		
		
		
		//集中加事件监听器
		newMenuItem.addActionListener(new fileMenuItemActionListener());
		executeMenuItem.addActionListener(new codeExecutedActionListenrer());
		saveMenuItem .addActionListener(new SaveActionListener());
		signin.addActionListener(new LoginActionListener());
		signup.addActionListener(new LoginActionListener());
		logout.addActionListener(new logoutActionListner());
		
		
	}




   
   
    
    
    //
}



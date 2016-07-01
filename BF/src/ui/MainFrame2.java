package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.beans.FeatureDescriptor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;




public class MainFrame2 extends JFrame {
	/**
	 * 
	 */
	static JFrame frame ;
	static JTextArea textAreacode;
	static JTextArea textAreainput;
	static JTextArea resultLabel;
	static JLabel filename = new JLabel("File");
	static JLabel fileedit = new JLabel("null");
    static JMenu login;
	static JMenu openMenuItem;
	static JMenu debug = new JMenu("Debug");   //设置标签
	static JMenuItem signin = new JMenuItem("sign in");
	static JMenuItem signup = new JMenuItem("sign up");
	static JMenuItem logout = new JMenuItem("logout");
	static JMenuItem dodebug = new JMenuItem("debug");
	static JMenuItem exitdebug = new JMenuItem("exit");
	static JMenu versionMenu = new JMenu("Version");
	static JMenu tool = new JMenu("Tool");
	static JMenuItem undo = new JMenuItem("undo");
	static JMenuItem redo = new JMenuItem("redo");
	static boolean loginState = false;
	static String username;
	static signinDialog  signinD;
	static signupDialog  signupD;
	static newFileDialog newfileD;
	static String CurrentFileName;
	static boolean threadflag = true;
	static undoredoController urcontrol;
	
	static DebugPanel debugpanel;;
	public  MainFrame2() {
		// 鍒涘缓绐椾綋
		
	    frame = new JFrame("BF Client");
		frame.setSize(500, 400);
		System.out.println(frame.getHeight());
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
		menuBarleft.add(tool);
		menuBarleft.add(debug);
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
		
		tool.add(undo);
		tool.add(redo);
		textAreacode = new JTextArea();
		//textAreacode.setSize(200,200);
		textAreacode.setMargin(new Insets(10, 10, 10, 10));
		textAreacode.setBackground(Color.LIGHT_GRAY);
		textAreacode.setText("");
		textAreacode.setLineWrap(true);
		JPanel IOpanel  = new JPanel(null);
	
		//IOpanel.setSize(400,100);
		System.out.println(frame.getHeight());
		IOpanel.setSize(frame.getWidth(), frame.getHeight()/4);
		textAreainput = new JTextArea();
		//textAreainput.setMargin(new Insets(10, 10, 10, 10));
		textAreainput.setBackground(Color.black);
		textAreainput.setForeground(Color.white);
		textAreainput.setCaretColor(Color.white);
		IOpanel.add(textAreainput);//IOpanel  加载了 IO内容的 输入 和结果的版面
		resultLabel = new JTextArea();
		resultLabel.setBackground(Color.black);
		resultLabel.setForeground(Color.white);
		resultLabel.setText("result");
		IOpanel.add(resultLabel);
		frame.add(IOpanel, BorderLayout.SOUTH);
		IOpanel.setVisible(true);
		 //***************************************************
        JPanel middlepanel  = new JPanel(null); 
        frame.add(middlepanel, BorderLayout.CENTER);
        filename.setBounds(0,0,100,20);
        textAreacode.setBounds(0, 20,500,180);
        IOpanel.setBounds(0,200,450,180);
        JLabel inputlable = new JLabel("Input");
        JLabel outputlable = new JLabel("Output");
        inputlable.setBounds(0,0,50,20);
        outputlable.setBounds(225,0,50,20);
        textAreainput.setBounds(0,20,220,170);
        resultLabel.setBounds(225,20, 220, 170);
        
        middlepanel.add(filename);
        middlepanel.add(textAreacode);
        middlepanel.add(IOpanel);
        
        debug.add(dodebug);
        debug.add(exitdebug);
        
        
        IOpanel.add(inputlable);
        IOpanel.add(outputlable);
        frame.add(fileedit,BorderLayout.SOUTH);
        //***************************************************
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     //frame.pack();
 		
		
	    undo.setForeground(Color.gray);
	    redo.setForeground(Color.gray);
		
	    MainFrame2.exitdebug.setForeground(Color.GRAY);
	    //设置frame的基本信息
		frame.setLocation(400, 200);
		frame.setVisible(true);
		frame.setResizable(false);
		//frame.pack();
		//闆嗕腑鍔犱簨浠剁洃鍚櫒
		newMenuItem.addActionListener(new fileMenuItemActionListener());
		executeMenuItem.addActionListener(new codeExecutedActionListenrer());
		saveMenuItem .addActionListener(new SaveActionListener());
		signin.addActionListener(new LoginActionListener());
		signup.addActionListener(new LoginActionListener());
		logout.addActionListener(new logoutActionListner());
		undo.addActionListener(new undoredoActionListener());
		redo.addActionListener(new undoredoActionListener());
		
		dodebug.addActionListener(new dodebugActionListenner());
//		exitdebug.addActionListener(new exitdebugActionListener());
		//暂时关闭  撤销重做系统
		//urcontrol= new undoredoController();
		
	}




   
   
    
    
    //
}



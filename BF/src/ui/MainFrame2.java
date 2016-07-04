package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.beans.FeatureDescriptor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;




public class MainFrame2 extends JFrame {
	private static final Component sqAreacode = null;
	/**
	 * 
	 */
	static JFrame frame ;    //标准的框架
	
	static JTextArea textAreacode;  //输入代码
	static JTextArea textAreainput; //BF输入区
	static JTextArea resultLabel;  // 用于显示输出结果的 标签区域
	
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
	static String CurrentFileName; //当前 文件的名字
	static boolean threadflag = true;  //用于对线程的优化
	static undoredoController urcontrol; // 撤销重做的控制
	
	static DebugPanel debugpanel;  //新的debug模块
	
	public  MainFrame2() {
		
		//设定 frame的基本信息
	    frame = new JFrame("BF Client");
		frame.setSize(500, 400);
		System.out.println(frame.getHeight());
		frame.setLayout(new BorderLayout());	
		
		
		
		
		//设置一个 放menubar的panel
		JPanel MenuBarPanel = new JPanel();
		frame.add(MenuBarPanel,BorderLayout.NORTH);
		//两个 bar  一左一右
		JMenuBar menuBarleft = new JMenuBar();
		JMenuBar menuBarright = new JMenuBar();
		MenuBarPanel.add(menuBarleft);
		MenuBarPanel.add(menuBarright);
		//添加menubar的子组建
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
		
		//设使代码区的信息
		textAreacode = new JTextArea();
		
		textAreacode.setMargin(new Insets(10, 10, 10, 10));
		textAreacode.setBackground(Color.LIGHT_GRAY);
		textAreacode.setText("");
		textAreacode.setLineWrap(true);
		JScrollPane sqcodeArea = new JScrollPane(textAreacode);
		JPanel IOpanel  = new JPanel(null);
	
		//IOpanel.setSize(400,100);
		//System.out.println(frame.getHeight());
		//设置 一个的IOpanel 用来放置 输入 和 结果返回的部分 
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
		//middle panel 用于放置文件名标签 和主要的那个代码区
        JPanel middlepanel  = new JPanel(null); 
        frame.add(middlepanel, BorderLayout.CENTER);
        filename.setBounds(0,0,100,20);
        //textAreacode.setBounds(0, 20,500,180);
        sqcodeArea.setBounds(0, 20,500,180);
        
        
        
        //调整 各个区在 frame中的总大小
        IOpanel.setBounds(0,200,450,180);
        JLabel inputlable = new JLabel("Input");
        JLabel outputlable = new JLabel("Output");
        inputlable.setBounds(0,0,50,20);
        outputlable.setBounds(225,0,50,20);
        textAreainput.setBounds(0,20,220,170);
        resultLabel.setBounds(225,20, 220, 170);
        
        middlepanel.add(filename);
        //middlepanel.add(textAreacode);
        middlepanel.add(sqcodeArea);
        middlepanel.add(IOpanel);
        
        debug.add(dodebug);
        debug.add(exitdebug);
        
        
        IOpanel.add(inputlable);
        IOpanel.add(outputlable);
        frame.add(fileedit,BorderLayout.SOUTH);
        //***************************************************
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     //frame.pack();
 		
		//对一些按钮 设置初始的颜色
	    undo.setForeground(Color.gray);
	    redo.setForeground(Color.gray);
		
	    MainFrame2.exitdebug.setForeground(Color.GRAY);
	    //设置frame的基本信息
		frame.setLocation(400, 200);
		frame.setVisible(true);
		frame.setResizable(false);
		//frame.pack();
		//为按钮添加事件监听器
		//为new 按钮添加事件监听器
		newMenuItem.addActionListener(new fileMenuItemActionListener());
		//为执行按钮添加事件监听器
		executeMenuItem.addActionListener(new codeExecutedActionListenrer());
		//为保存 按钮添加事件监听器
		saveMenuItem .addActionListener(new SaveActionListener());
		//登陆
		signin.addActionListener(new LoginActionListener());
		//注册
		signup.addActionListener(new LoginActionListener());
		//登出
		logout.addActionListener(new logoutActionListner());
		//撤销重做的按钮 事件监听
		undo.addActionListener(new undoredoActionListener());
		redo.addActionListener(new undoredoActionListener());
		
		//为debug 按钮设置事件监听
		dodebug.addActionListener(new dodebugActionListenner());
//		exitdebug.addActionListener(new exitdebugActionListener());
		//打开 撤销重做控制  对代码区代码的监听
		urcontrol= new undoredoController();
		
	}




   
   
    
    
    //
}



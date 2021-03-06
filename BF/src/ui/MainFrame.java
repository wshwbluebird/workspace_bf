package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import rmi.RemoteHelper;


public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JLabel resultLabel;

	public MainFrame() {
		// 鍒涘缓绐椾綋
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(null);
		
		frame.setLocation(400, 200);
		
		textArea = new JTextArea();
		textArea.setBounds(20, 20, frame.getHeight()/2, frame.getWidth()/2);
		textArea.setBackground(Color.blue);
		frame.add(textArea);
		frame.setSize(500, 400);

//		JMenuBar menuBar = new JMenuBar();
//		JMenu fileMenu = new JMenu("File");
//		JMenu executeMenu = new JMenu("Run");
//		JMenu login = new JMenu("login");
////		login.setl
////		menuBar.add(fileMenu);
//		menuBar.add(executeMenu);
//		JMenuItem newMenuItem = new JMenuItem("New");
//		fileMenu.add(newMenuItem);
//		JMenuItem openMenuItem = new JMenuItem("Open");
//		fileMenu.add(openMenuItem);
//		JMenuItem saveMenuItem = new JMenuItem("Save");
//		fileMenu.add(saveMenuItem);
//		JMenuItem runMenuItem = new JMenuItem("Run");
//		fileMenu.add(runMenuItem);
//		frame.setJMenuBar(menuBar);
//
//		newMenuItem.addActionListener(new fileMenuItemActionListener());
//		openMenuItem.addActionListener(new fileMenuItemActionListener());
//		saveMenuItem.addActionListener(new SaveActionListener());
//		runMenuItem.addActionListener(new fileMenuItemActionListener());
//
//		textArea = new JTextArea();
//		textArea.setMargin(new Insets(10, 10, 10, 10));
//		textArea.setBackground(Color.LIGHT_GRAY);
//		frame.add(textArea, BorderLayout.CENTER);
//
//		// 鏄剧ず缁撴灉
//		resultLabel = new JLabel();
//		resultLabel.setText("result");
//		frame.add(resultLabel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	class fileMenuItemActionListener implements ActionListener {
		/**
		 * 瀛愯彍鍗曞搷搴斾簨浠�
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				String value;
				try {
					value = RemoteHelper.getInstance().getIOService().readFile( "admin", "code");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					value = "nofile";
				}
				textArea.setText(value);
			} else if (cmd.equals("Save")) {
				textArea.setText("Save");
			} else if (cmd.equals("Run")) {
				
				resultLabel.setText("Hello, result");
			}
		}
	}

	
}

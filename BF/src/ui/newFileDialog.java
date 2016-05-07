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

public class newFileDialog extends JDialog implements ActionListener{
	private JLabel label1 = new JLabel("new file name");
    private JTextField newFileName = new JTextField(50);
    private JButton affirm = new JButton("affirm");
    private JButton cancel = new JButton("cancle");
    private String input;
    public newFileDialog() {
    	 //super(parent,modal);
         setTitle("Name a new File");
         setSize(300,190);
         setResizable(false);
         setLayout(null);
         add(label1);
         label1.setBounds(50, 30, 65, 20);
         add(newFileName);
         newFileName.setBounds(120, 30, 120, 20);
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
			
			input = newFileName.getText();
			
				if(!"".equals(input.trim())){
				MainFrame2.CurrentFileName = input;
							
				}else{
					JOptionPane.showMessageDialog(null, "file name can not be blank",
							"creat new failed",  JOptionPane.ERROR_MESSAGE); 

				}
			
			
		}
	    MainFrame2.newfileD.setVisible(false);
	}

	
}

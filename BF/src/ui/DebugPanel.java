package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class DebugPanel extends JFrame  implements ActionListener{
     
	JMenuBar bar;
	JButton next = new JButton("next");
	JButton nextR = new JButton("next register");
	JButton restart = new JButton("Restart");
	JButton quit = new JButton("Quit");
	JScrollPane sqcodeArea;
	JScrollPane sqinputArea;
	JScrollPane sqoutputArea;
	JScrollPane sqregisterArea;
	
	private String textcode;
	private String textinput;
	private String textregiser;
	private String textoutput = "";
	
	JTextArea outputArea = new JTextArea("");
	JTextArea registerArea = new JTextArea("");
			
	
	DefaultHighlighter.DefaultHighlightPainter light = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
	
	int codepos = 0;
	int inputpos = 0;
	int currentregpos = 0;
	int maxreg=0;
	Highlighter codelighter;
	Highlighter inputlighter;
	Highlighter reglighter;
	
	char[] chararray;
	char[] inputvalue;
	int[] loc;
	char[] charvalue;
	public DebugPanel(String textcode,String textinput){
		//super();
		this.setVisible(true);
		this.textcode = textcode;
		this.textinput = textinput;
		this.textregiser = "[0]";
		//this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		bar  = new JMenuBar();
		bar.add(next);
		bar.add(nextR);
		bar.add(restart);
		bar.add(quit);
		System.out.println(MainFrame2.textAreacode.getText());
		System.out.println("dsfd:"+ textinput);
		JTextArea codeArea = new JTextArea("");
		JTextArea inputArea = new JTextArea("");
		
		
		sqcodeArea = new JScrollPane(codeArea);
		sqinputArea= new JScrollPane(inputArea);
		sqoutputArea = new JScrollPane(outputArea);
		sqregisterArea = new JScrollPane(registerArea);
		
		codeArea.setBackground(Color.gray);
		codeArea.setLineWrap(true);
		inputArea.setBackground(Color.gray);
		outputArea.setBackground(Color.gray);
		registerArea.setBackground(Color.gray);
		registerArea.setLineWrap(true);
		
		codeArea.setText(this.textcode);
		inputArea.setText(this.textinput);
		registerArea.setText(this.textregiser);
		outputArea.setText(this.textoutput);
		
		JLabel code = new JLabel("code");
		JLabel input = new JLabel("input");
		JLabel output = new JLabel("output");
		JLabel register = new JLabel("register");
		this.setLayout(null);
		this.add(bar);
		bar.setBounds(0, 0, 500, 30);
		this.add(code);
		this.add(sqcodeArea);
		code.setBounds(0, 30, 50, 20);
		sqcodeArea.setBounds(0, 50, 500, 60);
		
		this.add(register);
		this.add(sqregisterArea);
		register.setBounds(0, 110, 50, 20);
		sqregisterArea.setBounds(0, 130, 500, 60);
		
		this.add(input);
		this.add(sqinputArea);
		input.setBounds(0, 190, 50, 20);
		sqinputArea.setBounds(0,210,500,60);
		
		this.add(output);
		this.add(sqoutputArea);
		output.setBounds(0, 270, 50, 20);
		sqoutputArea.setBounds(0, 290, 500, 60);
		
		 codelighter  = codeArea.getHighlighter();
		 inputlighter = inputArea.getHighlighter();
		 reglighter = registerArea.getHighlighter();
		
		next.addActionListener(this);
		nextR.addActionListener(this);
		restart.addActionListener(this);
		quit.addActionListener(new exitdebugActionListener());
		
		
		
		//½øÐÐÀ¨ºÅµÄÆ¥Åä
		int[] left = new int[textcode.length()];
		loc = new int[textcode.length()];
		int rear = 0;
		for (int i = 0; i < textcode.length(); i++) {
			if(textcode.charAt(i)=='[')  left[rear++]  = i;
			else if(textcode.charAt(i)==']') {
				loc[i] = left[--rear];
				loc[loc[i]] = i;
			}		
		}
		chararray  = textcode.toCharArray();
		inputvalue  = textinput.toCharArray();
		charvalue = new char[textcode.length()];
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == next){
			if(codepos<textcode.length()){
			debugging();
			changehighlight();
			}
		}else if(e.getSource()==nextR){
			       if(codepos<textcode.length()&&(chararray[codepos]=='+'||chararray[codepos]=='-')) {
			    	   while(chararray[codepos]=='+'||chararray[codepos]=='-'){
			    		   debugging();
			    	   }
			       }else if(codepos<textcode.length()){
			    	   debugging();
			       }
			      changehighlight();
		}else if(e.getSource()==restart){
			codepos = 0;
			currentregpos = 0;
			maxreg = 0;
			inputpos = 0;
			textregiser = "[0]";
			registerArea.setText(textregiser);
			textoutput = "";
			outputArea.setText(textoutput);
			changehighlight();
		}
		
		
	}
	
	
	
	private void changehighlight(){
		try {
	         System.out.println("changeHighlight");
	         codelighter.removeAllHighlights(); 
	         if(codepos<=textcode.length()&&codepos>0){
	                codelighter.addHighlight(codepos-1, codepos, light);
	         }
	         inputlighter.removeAllHighlights();
	         if(inputpos<=textinput.length()&&inputpos>0){
	        	    inputlighter.addHighlight(inputpos-1, inputpos, light);
	         }
	         reglighter.removeAllHighlights();
	         reglighter.addHighlight(getleft(),getright()+1, light);
           } catch (BadLocationException e1) {
	         //e1.printStackTrace();
           }	
	}
	
	 private int getleft(){
		int ans = 0;
		for (int i = 0; i < currentregpos; i++) {
			ans = textregiser.indexOf('[', ans+1);
		}
		//System.out.println(ans);
		return ans;
	}
	
	  private int getright(){
		int ans = 0;
		for (int i = 0; i <=currentregpos; i++) {
			ans = textregiser.indexOf(']', ans+1);
		}
		//System.out.println(ans);
		return ans;
	  }
	  
	  private void debugging(){
		  if('>'==(chararray[codepos]) ){
				currentregpos++;
				if(currentregpos>maxreg){
					maxreg++;
					textregiser = textregiser +" [0]";
					registerArea.setText(textregiser);
				}
			}else if('<'==(chararray[codepos]) ){
				currentregpos--;
			}else if('+'==(chararray[codepos]) ){
				charvalue[currentregpos]++;
				int temp = Integer.parseInt(textregiser.substring(getleft()+1,getright()));
				System.out.println("temp before: "+temp);
				temp++;
				textregiser = textregiser.substring(0,getleft()+1)+String.valueOf(temp)+textregiser.substring(getright());
				System.out.println(textregiser);
				registerArea.setText(textregiser);
			}else if('-'==(chararray[codepos]) ){
				charvalue[currentregpos]--;
				int temp = Integer.parseInt(textregiser.substring(getleft()+1,getright()));
				System.out.println("temp before: "+temp);
				temp--;
				textregiser = textregiser.substring(0,getleft()+1)+String.valueOf(temp)+textregiser.substring(getright());
				registerArea.setText(textregiser);
			}else if(','==(chararray[codepos]) ){
				charvalue[currentregpos]=inputvalue[inputpos++];
			}else if('.'==(chararray[codepos]) ){
				//System.out.print(charvalue[pointer])
				textoutput+=charvalue[currentregpos];
				outputArea.setText(textoutput);
			}else if('['==(chararray[codepos]) ){
				if(charvalue[currentregpos]==0){
					codepos = loc[codepos];
				}
			}else if(']'==(chararray[codepos]) ){				
				   codepos = loc[codepos]-1;				
			}	 
			codepos++;
	  }
	
}

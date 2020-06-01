import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class MathGame extends JFrame{

	String maxNum = "";
	int quantity = 1;
	JLabel task[] ;
	int taskMax[];
	int taskNext[];
	int S[] = new int[10];
	JTextField answer[];
	JPanel panel;
	JLabel color[];
	ImageIcon iconR = new ImageIcon("images/redButton.png");
	ImageIcon iconG = new ImageIcon("images/greenButton.png");
	ImageIcon iconW = new ImageIcon("images/whiteButton.png");
	
	public double diapason = 2*Math.PI;
    public int dotNum = (int) (diapason/Math.PI*1000);
    public int xo[] = new int[dotNum+1];
    public int yo[] = new int[dotNum+1];
    public int a = 2;
    public int b = 1;
    public int UIstep = 40;
	
	public MathGame() {
        super("Math Game");
        createGUI();
   }

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);
        add(panel);
        setPreferredSize(new Dimension(300, 750));
        
        int xstart = 130;
        int ystart = 200;
        double step = (double) diapason/dotNum;
        for (int i = 0; i < xo.length; i++) {
        	double r = b + a*Math.cos(step*i);
			double x = r*Math.cos(step*i);
			double y = r*Math.sin(step*i);
			xo[i] = (int) (x*UIstep+xstart);
			yo[i] = (int) (ystart-y*UIstep);
		}
        for (int i = 0; i < xo.length; i++) {
			JLabel l = new JLabel();
			l.setBorder(BorderFactory.createLineBorder(Color.blue));
			l.setBounds(xo[i],yo[i],3,3);
			panel.add(l);
		}
       
        
        
        JLabel labelKnowledje = new JLabel("Введіть найбільше ваше число:");
        labelKnowledje.setBounds(20, 20, 200, 30);
        panel.add(labelKnowledje);
        
        JTextField textFieldKnowledje = new JTextField();
        textFieldKnowledje.setBounds(230, 20, 40, 30);
        panel.add(textFieldKnowledje);
        
        JLabel labelChoice = new JLabel("Виберіть кількість прикладів:");
        labelChoice.setBounds(20,70,200,30);
        panel.add(labelChoice);
        
        SpinnerModel numbers = new SpinnerNumberModel(1,1,10,1);
        JSpinner spinner = new JSpinner(numbers);
        spinner.setBounds(230, 70,450,30);
        panel.add(spinner);
        
        JButton buttonStart = new JButton("Старт");
        buttonStart.setBounds(110, 120, 80, 30);
        buttonStart.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ae) {
            	  maxNum = textFieldKnowledje.getText(); 
            	  quantity = (int) spinner.getValue();
            	  if(exceptionCheck()) {
            		  createGame();
            	  }
            	  else {
            		  JOptionPane.showMessageDialog(null, "ERROR: заповніть число!");
            	  }
              }
            });
        panel.add(buttonStart);
        JButton buttonCheck = new JButton("Перевірити");
        buttonCheck.setBounds(90, 160, 120, 30);
        buttonCheck.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ae) {
            	  checkGame();
            	  finalGame();
              }
            });
        panel.add(buttonCheck);
        task = new JLabel[10];
		for (int i = 0; i < task.length; i++) {
			task[i] = new JLabel();
			task[i].setBounds(20, 210+50*i, 150, 30);
			task[i].setVisible(false);
			panel.add(task[i]);
		}
		answer = new JTextField[10];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = new JTextField();
			answer[i].setBounds(180, 210+50*i, 40, 30);
			answer[i].setVisible(false);
			panel.add(answer[i]);
		}
		color = new JLabel[10];
		for (int i = 0; i < color.length; i++) {
			color[i] = new JLabel();
			color[i].setBounds(240, 210+50*i, 25, 25);
			color[i].setIcon(iconW);
			color[i].setVisible(false);
			panel.add(color[i]);
		}
	}

	private boolean exceptionCheck() {
		if(maxNum.equals(""))
			return false;
		for (int i = 0; i < quantity; i++) {
			task[i].setVisible(true);
			answer[i].setVisible(true);
			color[i].setVisible(true);
		}
		
		for (int i = 0; i < maxNum.length(); i++) {
			char ch = maxNum.charAt(i);
			if('0'>ch||ch>'9')
				return false;
		}
		return true;
	}
	private void createGame() {
		int num = Integer.parseInt(maxNum)+1;
		int a = 0;
		int b = 0;
		int c = 0;
		String SText = "";
		Random rand = new Random();
		for (int i = 0; i < quantity; i++) {
			int type = rand.nextInt(4);
			if(type==0) {
				a = rand.nextInt(num);
				b = rand.nextInt(num-a);
				S[i] = a+b;
				SText = a+"+"+b;
				task[i].setText(i+1+". "+SText);
			}
			else if(type==1) {
				b = rand.nextInt(num);
				a = rand.nextInt(num-b)+b;
				S[i] = a-b;
				SText = a+"-"+b;
				task[i].setText(i+1+". "+SText);
			}
			else if(type==2) {
				a = rand.nextInt(num);
				b = rand.nextInt(num-a);
				c = rand.nextInt(a+b+1);
				S[i] = a+b-c;
				SText = a+"+"+b+"-"+c;
				task[i].setText(i+1+". "+SText);
			}		
			else if(type==3) {
				a = rand.nextInt(num);
				b = rand.nextInt(num-a);
				c = rand.nextInt(a+b+1);
				S[i] = a+b-c;
				SText = a+"-"+c+"+"+b;
				task[i].setText(i+1+". "+SText);
			}
		}
		 
	}

	private void finalGame() {
		int check = 0;
		for (int i = 0; i < quantity; i++) {
			if(color[i].getIcon().equals(iconG))
				check++;
		}
		if(check==quantity)
			JOptionPane.showMessageDialog(null, "         ВИ ВИГРАЛИ !");
	}
	private void checkGame() {
		int check = 0;
		for (int i = 0; i < quantity; i++) {
			if(!answer[i].getText().equals("")) {
				check++;
			}
		}
		if(check==quantity) {
			for (int i = 0; i < quantity; i++) {
				if(S[i]==Integer.parseInt(answer[i].getText())) {
					color[i].setIcon(iconG);
				}
				else {
					color[i].setIcon(iconR);
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "ERROR: заповніть  відповіді!");
		}
		
		
	}
	public static void main(String[] args) {
		MathGame frame = new MathGame();
        frame.pack();
        frame.setVisible(true);
	}
}

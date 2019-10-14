import java.awt.BorderLayout;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Timer;
import java.util.Date;
import java.text.SimpleDateFormat;




public class CheckersView extends JFrame { //this class handle the view only
	
	
	private myButton [][] buttons;
	private String playerOne;
	private String playerTwo;
	private JPanel playBoard;
	private JPanel gameData;
	private JPanel scorePanel;
	private JLabel turnField;
	private JLabel turnLabel;
	private JLabel timerLabel;
	private JButton turnIcon;
	private JLabel whiteCounterField;
	private JLabel whiteCounterLabel;
	private JLabel blackCounterField;
	private JLabel blackCounterLabel;
	private myIcon white;
	private myIcon black;
	private myIcon blackQueen;
	private myIcon whiteQueen;
	
	private final ClockListener clock = new ClockListener();
	private final Timer timer = new Timer(53, (ActionListener) clock);
	private final SimpleDateFormat date = new SimpleDateFormat("mm.ss");
	private long startTime;
	
	
	
	public myButton[][] getButtons() {
		return buttons;
	}
	
	public myIcon getBlackIcon()
	{
		return this.black;
	}
	public myIcon getWhiteIcon()
	{
		return this.white;
	}
	public myIcon getBlackQueen()
	{
		return this.blackQueen;
	}
	public myIcon getWhiteQueen()
	{
		return this.whiteQueen;
	}
	public void setTurnField (String text)
	{
		this.turnField.setText(text);
	}
	public void setTurnIcon (myIcon icon)
	{
		this.turnIcon.setIcon(icon);
	}
	public void setWhiteCounterField (String text)
	{
		this.whiteCounterField.setText(text);
	}
	public void setBlackCounterField (String text)
	{
		this.blackCounterField.setText(text);
	}
	public String getPlayerOne()
	{
		return playerOne;
	}
	public String getPlayerTwo()
	{
		return playerTwo;
	}
	


	
	public CheckersView()
	{
	super( "Checkers" );
	setLayout(new BorderLayout() ) ; // setting the main frame layout
	
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	this.setSize(800, 800);//setting the size of main frame
	
	
	playBoard = new JPanel(new GridLayout(8,8)); // setting the play board > 64 buttons 
	gameData = new JPanel(new GridLayout(1,4)); // setting the lower information panel include current turn, current player icon and timer 
	scorePanel = new JPanel(new GridLayout(1,4)); // setting the upper information panel includes amount of pieces on the board of the white and black players 
	
	playerOne = JOptionPane.showInputDialog(this,"Please enter first player name:","Player 1",JOptionPane.PLAIN_MESSAGE); //asks and sets the first player name
	playerTwo = JOptionPane.showInputDialog(this,"Please enter second player name:","Player 2",JOptionPane.PLAIN_MESSAGE); //asks and sets the second player name
	turnField = new JLabel();
	turnLabel = new JLabel("The turn of the:");
	
	turnIcon = new JButton(""); //setting button to show the current turn icon
	turnIcon.setBorder(BorderFactory.createEmptyBorder()); //removes the border from icon button
	turnIcon.setContentAreaFilled(false); //removes the button style from icon button
	
	whiteCounterField = new JLabel();
	blackCounterField = new JLabel();
	whiteCounterLabel = new JLabel("(White) " +getPlayerOne() + "'s pices:");
	blackCounterLabel = new JLabel("(Black) "+getPlayerTwo() + "'s pices:");
	timerLabel = new JLabel(); //setting label that stores the timer
	
	//adding all the components to the relevant panels
	scorePanel.add(whiteCounterLabel);
	scorePanel.add(whiteCounterField);
	scorePanel.add(blackCounterLabel);
	scorePanel.add(blackCounterField);
	gameData.add(turnLabel);
	gameData.add(turnField);
	gameData.add(turnIcon);
	gameData.add(timerLabel);
	
	
    buttons = new myButton[8][8]; //defines 2D array for all the board buttons
 
    this.add(scorePanel, BorderLayout.NORTH);
    this.add(playBoard,BorderLayout.CENTER);
    this.add(gameData,BorderLayout.SOUTH);
	this.setResizable(false); 
}
    public void setPlayBoard (ActionListener listenForBoardButton) //builds the play board
    {
    	
        white = new myIcon( getClass().getResource( "White.png" ),"soldier", "white" ); //Sets the icon for white soldier
        black = new myIcon( getClass().getResource( "Black.png" ),"soldier", "black" ); //Sets the icon for black soldier
        blackQueen = new myIcon( getClass().getResource( "BlackQueen.png" ),"queen", "black" ); //Sets the icon for black queen
        whiteQueen = new myIcon( getClass().getResource( "WhiteQueen.png" ),"queen", "white" ); //Sets the icon for white queen
        Color wheat = new Color (245,222,179); //Sets the color for the "white" tile
        Color saddleBrown = new Color (139,69,19); ////Sets the color for the "black" tile
        
        startTime = System.currentTimeMillis(); //stores the current time
        timer.start(); // starts the timer
        
        
        for(int i = 0 ; i < 8 ; i++) //sets the buttons on the board and paint them and also adding action listener to it
        {
        	
        	for(int j = 0 ; j<8 ; j++)
        	{
        	
        		buttons[i][j] = new myButton(Integer.parseInt(""+i+""+j), null);
        		buttons[i][j].setActionCommand(""+i+""+j);
        		buttons[i][j].addActionListener( listenForBoardButton );
        		
        		
        		
        		if(i%2==0 && j%2 != 0)
        		{
        			buttons[i][j].setBackground(saddleBrown);
        			buttons[i][j].setColor("black");
        			if(i==0 || i==2)
        			{
        				buttons[i][j].setIcon(white);
           			}
        			else if(i==6)
        			{
        				buttons[i][j].setIcon(black);
        			}
      			
        		}
        		else if(i%2 != 0 && j%2 == 0)
        		{
        			buttons[i][j].setBackground(saddleBrown);
        			buttons[i][j].setColor("black");
           		 if(i==5 || i==7)
           			{
           				buttons[i][j].setIcon(black);
           			}
           		 else if(i==1)
           		 {
           			buttons[i][j].setIcon(white);
           		 }
        		}
        		else if(i%2 == 0 && j%2 == 0)
        		{
        			buttons[i][j].setBackground(wheat);
        			buttons[i][j].setEnabled(false);
        			buttons[i][j].setColor("white");

        		}
        		else if(i%2 != 0 && j%2 != 0)
        		{
        			buttons[i][j].setBackground(wheat);
        			buttons[i][j].setEnabled(false);
        			buttons[i][j].setColor("white");
        		}
        		playBoard.add(buttons[i][j]);
        	}
        }
    	
    }
   

	public void displayErrorMessage(String errorMessage) //shows error messages
	{
		JOptionPane.showMessageDialog(CheckersView.this, errorMessage);
	}

    private class ClockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           updateClock();
        }
    private void updateClock() 
    {
            Date elapsed = new Date(System.currentTimeMillis() - startTime);
            timerLabel.setText("Time elapsed: "+ date.format(elapsed));
        }
    }
}

	







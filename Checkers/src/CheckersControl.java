import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CheckersControl { //this class combines the view and the model and controls them
	
	private CheckersModel theModel;
	private CheckersView theView;
	String firstCode="";
	
	public CheckersControl(CheckersModel theModel , CheckersView theView)
	{
		this.theModel = theModel;
		this.theView = theView;
		
		this.theView.setPlayBoard(new CheckersListener());
		
		if(theModel.getRandomStartTurn() == 1)
		{
			theView.displayErrorMessage(theView.getPlayerOne() + " starts the game (White)");
			theView.setTurnField(theView.getPlayerOne());
			theView.setTurnIcon(theView.getWhiteIcon());
			
		}
		else
		{
			theView.displayErrorMessage(theView.getPlayerTwo() +" starts the game (Black)");
			theView.setTurnField(theView.getPlayerTwo());
			theView.setTurnIcon(theView.getBlackIcon());
		}
	}
	
	
	private class CheckersListener implements ActionListener 
	{
		
	   public void actionPerformed( ActionEvent event )
	   {
		   
  	 	  if (firstCode.equals(""))
	 	  {
 	 			firstCode = event.getActionCommand(); //gets the first click on a tile (from)
 	 			
 	 		 if(theModel.turns(theView.getButtons()[(int)(firstCode.charAt(0)-48)][(int)(firstCode.charAt(1)-48)]))
 	 		 { 
	 			theModel.setJustAte(theModel.canEat(theView.getButtons(), (int)(firstCode.charAt(0)-48), (int)(firstCode.charAt(1)-48)));
	 			
	 		 }
 	 		else
 	 		 {
 	 			if(theView.getButtons()[(int)(firstCode.charAt(0)-48)][(int)(firstCode.charAt(1)-48)].getIcon() == null) //shows error if the player chose tile without a player
 	 			{
 	 	 			 theView.displayErrorMessage("Choose tile with player!");
 	 	 			 firstCode = "";
 	 	 			 return;
 	 			}
 	 			else //shows error if it is not the player turn
 	 			{
	 	 			 theView.displayErrorMessage("Not your turn");
	 	 			 firstCode = "";
	 	 			 return;
 	 			}
 	 		 }
	 	  }
	 	  else
	 	  {
	 		  String secondCode = event.getActionCommand(); //gets the second click on a tile (to)
	 		  if(secondCode.equals(firstCode)) //shows error if the player chose the same tile in the first and second click
	 		  {
	 			 theView.displayErrorMessage("You selected the same tile, try again");
	 			 firstCode = "";
	 			 return;
	 		  }
	 		  
	 		  int firstLine = (int)(firstCode.charAt(0)-48);
			  int firstCol=(int)(firstCode.charAt(1)-48);
			  int secondLine = (int)(secondCode.charAt(0))-48;
	 		  int secondCol = (int)(secondCode.charAt(1))-48;
	 		  String winMassage = "";
	 		  
	 		  if(theModel.turns(theView.getButtons()[firstLine][firstCol]) && (theModel.soldierEat(theView.getButtons(), firstLine, firstCol, secondLine, secondCol) || theModel.soldierMove(theView.getButtons(),firstLine, firstCol, secondLine, secondCol)||theModel.queenMove(theView.getButtons(),firstLine, firstCol, secondLine, secondCol)))
	 		  {
	 			 theModel.unBorder(theView.getButtons(), firstLine, firstCol); //if there is any tile with red border this step removes it
	 			 theModel.movement(theView.getButtons(), firstLine, firstCol, secondLine, secondCol); //do the move on the board
	 			 theModel.lockOrUnlock(theView.getButtons(), false); //unlock all the tiles to be clickable
	 			
	 			
	 			if(theModel.getJustAte() && theModel.canEat(theView.getButtons(), secondLine, secondCol)) //if opponent ate soldier and there is another one near by so gives him another turn
	 			{
	 				if(theModel.getRandomStartTurn() == 1)
	 				{
	 					theModel.setRandomStartTurn(2);
	 				}
	 				else
	 				{
	 					theModel.setRandomStartTurn(1);
	 				}
	 				
	 			}
	 			else
	 			{
	 				theModel.setJustAte(false);
	 			}
	 			 
		 		 
	  
	 		  
		 		  if(theModel.getRandomStartTurn() == 1) //Checks who the turn is and setting the parameters on the board
		 		  {
		 			theModel.setRandomStartTurn(theModel.getRandomStartTurn()+1);
		 			theView.setTurnField(theView.getPlayerTwo());
		 			theView.setTurnIcon(theView.getBlackIcon());
		 			theView.setWhiteCounterField(""+theModel.getWhitePices());
		 			theView.setBlackCounterField(""+theModel.getBlackPices());
		 			
		 			
		 		  }
		 		  else
		 		  {
		 			 theModel.setRandomStartTurn(theModel.getRandomStartTurn()-1);
		 			theView.setTurnField(theView.getPlayerOne());
		 			theView.setTurnIcon(theView.getWhiteIcon());
		 			theView.setWhiteCounterField(""+theModel.getWhitePices());
		 			theView.setBlackCounterField(""+theModel.getBlackPices());
		 			
		 			
		 		  }
		 		 theModel.mustEat(theView.getButtons()); //checks every turn if there is any opponent soldier nearby to eat
		 		 theModel.isQueen(theView.getButtons(), theView.getBlackQueen(), theView.getWhiteQueen()); //checks every turn if there is any soldier that needs to be upgraded to be a queen
		 		 winMassage = theModel.winChecking(theView.getButtons()); //checks every turn if there is a win
		 		 if(!winMassage.equals("false"))
		 		 {
		 			 theView.displayErrorMessage(winMassage);
		 			System.exit(0);
		 		 }
		 		 
	 		  }
	 		  
	 		  firstCode=""; //reset the clicks after the turn
	 	  }
	 	  
 	  }
   }
	

}

import java.awt.Button;
import java.awt.Color;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CheckersModel  { //this class handle all the logic and calculations only
	
	private int randomStartTurn;
	private int whitePices;
	private int blackPices;
	private boolean justAte;
	

	public CheckersModel() { //constructor
		super();
		Random rand = new Random();
		this.randomStartTurn = (rand.nextInt(2))+1;
		setWhitePices(12);
		setBlackPices(12);
		justAte = false;

	}
	
	
	public boolean getJustAte()
	{
		return justAte;
	}
	
	
	public void setJustAte(boolean justAte)
	{
		this.justAte = justAte;
	}
	
	
	public int getWhitePices() { //get data from whitePices property
		return whitePices;
	}

	
	public void setWhitePices(int whitePices) { //set data to whitePices property
		this.whitePices = whitePices;
	}

	
	public int getBlackPices() { //get data from blackPices property
		return blackPices;
	}

	
	public void setBlackPices(int blackPices) { //set data to blackPices property
		this.blackPices = blackPices;
	}

	
	
	public int getRandomStartTurn() { //get data from randomStartTurn property
		return randomStartTurn;
	}
	
	
	public void setRandomStartTurn(int randomStartTurn) { //set data to randomStartTurn property

		this.randomStartTurn = randomStartTurn;
	}
	
	
	
	public boolean turns(myButton button) //checks if the player that playing it's his turn now 
	{
		boolean flag = false;
		if(button.getIcon() != null)
		{
			if(randomStartTurn == 1 && ((myIcon)button.getIcon()).getColor().equals("white"))// white turn
			{
			
				flag = true;
			}
			else if (randomStartTurn == 2 && ((myIcon)button.getIcon()).getColor().equals("black"))//black turn
			{
				
				flag = true;
			}
		}
		return flag;
	}
	
	
	
	public void movement (myButton [][] buttons, int firstLine, int firstCol, int secondLine, int secondCol) //doing the actual movement on the board
	{
		  buttons[secondLine][secondCol].setIcon(buttons[firstLine][firstCol].getIcon());
		  buttons[firstLine][firstCol].setIcon(null);
	}
	
	
	
	public void mustEat(myButton [][] buttons) //check if there is some opponent piece to eat
	{
		boolean flag = false;
		
		for(int i = 0 ; i < 8 ; i++)
		{
			for (int j = 0 ; j<8 ; j++)
			{
				if(randomStartTurn == 1)
				{
					if(buttons[i][j].getIcon() != null && ((myIcon) buttons[i][j].getIcon()).getColor().equals("white"))
					{
						flag = canEat(buttons, i, j);
					}
					
				}
				else
				{
					if(buttons[i][j].getIcon() != null && ((myIcon) buttons[i][j].getIcon()).getColor().equals("black"))
					{
						flag = canEat(buttons, i, j);
					}
					
				}
				if(flag)
				{
					return;
				}
			}
		}
	}

	
	
	public boolean soldierEat (myButton [][] buttons, int firstLine, int firstCol, int secondLine, int secondCol) //checks the limitation of eating another player soldier
	{
		boolean flag = false;
		if(((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("black") || (((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("white")  && justAte))
		{
			
			if(secondLine == firstLine-2 && secondCol == firstCol-2 && buttons[secondLine][secondCol].getIcon() == null && buttons[firstLine-1][firstCol-1].getIcon() != null && ( ((myIcon) buttons[firstLine-1][firstCol-1].getIcon()).getColor().equals("white") || (((myIcon) buttons[firstLine-1][firstCol-1].getIcon()).getColor().equals("black")&&justAte)) )
			{
				buttons[firstLine-1][firstCol-1].setIcon(null);	//black soldier eat white soldier from left in diagonally way
				flag = true;
			}
			else if(secondLine == firstLine-2 && secondCol == firstCol+2 && buttons[secondLine][secondCol].getIcon()== null && buttons[firstLine-1][firstCol+1].getIcon() != null && ( ((myIcon) buttons[firstLine-1][firstCol+1].getIcon()).getColor().equals("white") || (((myIcon) buttons[firstLine-1][firstCol+1].getIcon()).getColor().equals("black")&&justAte)) )
			{
				buttons[firstLine-1][firstCol+1].setIcon(null); //black soldier eat white soldier from right in diagonally way
				flag = true;
			}
			
			if(((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("black")&&flag)
			{
				whitePices--;
				return flag;
			}
			else if(((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("white") && flag)
			{
				blackPices--;
				return flag;
			}

		}
		if(((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("white")  || (((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("black")  && justAte ))
		{
			
			if(secondLine == firstLine+2 && secondCol == firstCol-2 && buttons[secondLine][secondCol].getIcon()== null && buttons[firstLine+1][firstCol-1].getIcon() != null && (((myIcon) buttons[firstLine+1][firstCol-1].getIcon()).getColor().equals("black") || (((myIcon) buttons[firstLine+1][firstCol-1].getIcon()).getColor().equals("white") && justAte)))
			{
				buttons[firstLine+1][firstCol-1].setIcon(null); //white soldier eat black soldier from right in diagonally way
				flag = true;
			}
			else if(secondLine == firstLine+2 && secondCol == firstCol+2 && buttons[secondLine][secondCol].getIcon()== null && buttons[firstLine+1][firstCol+1].getIcon() != null && (((myIcon) buttons[firstLine+1][firstCol+1].getIcon()).getColor().equals("black")||((myIcon) buttons[firstLine+1][firstCol+1].getIcon()).getColor().equals("white") && justAte))
			{
				buttons[firstLine+1][firstCol+1].setIcon(null); //white soldier eat black soldier from left in diagonally way
				flag = true;
			}

			if(flag)
			{
				this.reducedPlayer(buttons, firstLine, firstCol);
			}
			
		}
			
	return flag;
	}
	
	
	
	public boolean soldierMove (myButton [][] buttons,int firstLine, int firstCol, int secondLine, int secondCol ) //checks the limitation of the movement of the player soldier
	{
		boolean flag = false;
		

			if(((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("black")  || (((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("white")  && justAte))
			{
				
				if(secondLine == firstLine-1 && secondCol == firstCol-1 && buttons[secondLine][secondCol].getIcon()== null)
				{
					flag = true;
				}
				else if(secondLine == firstLine-1 && secondCol == firstCol+1 && buttons[secondLine][secondCol].getIcon()== null)
				{
					flag = true;
				}
			}
			if(((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("white") || (((myIcon)buttons[firstLine][firstCol].getIcon()).getColor().equals("black")  && justAte ))
			{
				
				if(secondLine == firstLine+1 && secondCol == firstCol-1 && buttons[secondLine][secondCol].getIcon()== null)
				{
					flag = true;
				}
				else if(secondLine == firstLine+1 && secondCol == firstCol+1 && buttons[secondLine][secondCol].getIcon()== null)
				{
					flag = true;
				}

			}
			
		
		return flag;
			
			
	}
	
	
	public boolean canEat (myButton [][] buttons, int newLine, int newCol) //checks if it's allowed to eat opponent soldier
	{
		boolean flag = false;
		if(buttons[newLine][newCol].getIcon() != null  )
		{
			String currentColor = ((myIcon)buttons[newLine][newCol].getIcon()).getColor();
				
			if(((myIcon)buttons[newLine][newCol].getIcon()).getColor().equals("black") || (((myIcon)buttons[newLine][newCol].getIcon()).getColor().equals("white") && justAte) || ((myIcon)buttons[newLine][newCol].getIcon()).getType().equals("queen") )
			{
				if(newCol > 1 && newLine > 1  && buttons[newLine-1][newCol-1].getIcon() != null &&  !(((myIcon)buttons[newLine-1][newCol-1].getIcon()).getColor().equals(currentColor)) && buttons[newLine-2][newCol-2].getIcon() == null )
				{
					
					buttons[newLine-2][newCol-2].setBorder(BorderFactory.createLineBorder(Color.RED, 4));
					this.lockOrUnlock(buttons, true);
					buttons[newLine][newCol].setEnabled(true);
					buttons[newLine-2][newCol-2].setEnabled(true);
					flag =true;
					
				}
	
				if(newCol < 6 && newLine > 1 && buttons[newLine-1][newCol+1].getIcon() != null &&  !(((myIcon)buttons[newLine-1][newCol+1].getIcon()).getColor().equals(currentColor)) && buttons[newLine-2][newCol+2].getIcon() == null )
				{
					
					buttons[newLine-2][newCol+2].setBorder(BorderFactory.createLineBorder(Color.RED, 4));
					if(flag)
					{
						buttons[newLine-2][newCol+2].setEnabled(true);
						buttons[newLine][newCol].setEnabled(true);
					}
					else
					{
						this.lockOrUnlock(buttons, true);
						buttons[newLine-2][newCol+2].setEnabled(true);
						buttons[newLine][newCol].setEnabled(true);
					}
					flag = true;
				}
			}
			if(((myIcon)buttons[newLine][newCol].getIcon()).getColor().equals("white") || (((myIcon)buttons[newLine][newCol].getIcon()).getColor().equals("black") && justAte) || ((myIcon)buttons[newLine][newCol].getIcon()).getType().equals("queen") )
			{
				if(newCol > 1 && newLine < 6 && buttons[newLine+1][newCol-1].getIcon() != null && !(((myIcon)buttons[newLine+1][newCol-1].getIcon()).getColor().equals(currentColor)) && buttons[newLine+2][newCol-2].getIcon() == null )
				{
					
					buttons[newLine+2][newCol-2].setBorder(BorderFactory.createLineBorder(Color.RED, 4));
					if(flag)
					{
						buttons[newLine+2][newCol-2].setEnabled(true);
						buttons[newLine][newCol].setEnabled(true);
					}
					else
					{
						this.lockOrUnlock(buttons, true);
						buttons[newLine+2][newCol-2].setEnabled(true);
						buttons[newLine][newCol].setEnabled(true);
					}
					flag = true;
				}
				if(newCol < 6 && newLine < 6 && buttons[newLine+1][newCol+1].getIcon() != null && !(((myIcon)buttons[newLine+1][newCol+1].getIcon()).getColor().equals(currentColor)) && buttons[newLine+2][newCol+2].getIcon() == null )
				{
					
					buttons[newLine+2][newCol+2].setBorder(BorderFactory.createLineBorder(Color.RED, 4));
					if(flag)
					{
						buttons[newLine+2][newCol+2].setEnabled(true);
						buttons[newLine][newCol].setEnabled(true);
					}
					else
					{
						this.lockOrUnlock(buttons, true);
						buttons[newLine+2][newCol+2].setEnabled(true);
						buttons[newLine][newCol].setEnabled(true);
					}
					flag = true;
				}
			}
			
		}
		
		return flag;

	}
	
	
	public void unBorder (myButton [][] buttons, int selectLine, int selectCol) //removing the red border from the tiles
	{
		if(buttons[selectLine][selectCol].getIcon() != null)
		{
			
			
			if(selectLine > 1 && selectCol >1)
			{
			buttons[selectLine-2][selectCol-2].setBorder(BorderFactory.createEmptyBorder());
			
			}
			if(selectLine < 6 && selectCol < 6)
			{
			buttons[selectLine+2][selectCol+2].setBorder(BorderFactory.createEmptyBorder());
			
			}
			if(selectLine > 1 && selectCol < 6)
			{
			buttons[selectLine-2][selectCol+2].setBorder(BorderFactory.createEmptyBorder());
			
			}
			if(selectLine < 6 && selectCol > 1)
			{
			buttons[selectLine+2][selectCol-2].setBorder(BorderFactory.createEmptyBorder());
			
			}
		}
	}
	
	
	public void lockOrUnlock (myButton [][] buttons ,boolean lock) //locking or unlocking all the tiles if needed
	{

			for(int i =0 ; i < 8 ; i++)
			{
				for(int j = 0 ; j < 8 ; j++)
				{
					if(i%2==0 && j%2 != 0 && lock)
					{
						buttons[i][j].setEnabled(false);
						

					}
					else if(i%2==0 && j%2 != 0 && !lock)
					{
						buttons[i][j].setEnabled(true);
					}
					else if(i%2 != 0 && j%2 == 0 && lock)
					{
						buttons[i][j].setEnabled(false);

					}
					else if(i%2 != 0 && j%2 == 0 && !lock)
					{
						buttons[i][j].setEnabled(true);
					}
					
				}
			}
		

		
	}
	
	public void isQueen(myButton [][] buttons, myIcon blackQueen, myIcon whiteQueen) // checks if the soldier needs to be promoted to be a queen
	{
		for(int j = 0 ; j < 8 ; j++)
		{
			if(buttons[0][j].getIcon() != null && ((myIcon)buttons[0][j].getIcon()).getColor().equals("black"))
			{
				buttons[0][j].setIcon(blackQueen);
				
				
			}
			if(buttons[7][j].getIcon() != null && ((myIcon)buttons[7][j].getIcon()).getColor().equals("white"))
			{
				buttons[7][j].setIcon(whiteQueen);
			}
		}
	}
	
	public boolean queenMove (myButton [][] buttons, int firstLine, int firstCol, int secondLine, int secondCol) //checks the limitation of the queen moves
	{
		boolean flag = false;
		String currentColor = ((myIcon)buttons[firstLine][firstCol].getIcon()).getColor();
		
		if(((myIcon)buttons[firstLine][firstCol].getIcon()).getType().equals("queen"))
		{

			if(firstLine > secondLine && firstCol > secondCol)
			{
				
				int i = firstLine-1;
				int j = firstCol-1;
				
				for(;i>secondLine ; i--, j--)
				{
					
					if(buttons[i][j].getIcon() != null)
					{
						
						if(i == secondLine + 1 && j == secondCol + 1  && !(((myIcon)buttons[i][j].getIcon()).getColor().equals(currentColor)))
						{
							buttons[i][j].setIcon(null);
							this.reducedPlayer(buttons, firstLine, firstCol);
							return true;
							
						}
						else
						{
							return false;
						}
					}
				}
				flag = true;
				
			}
			else if(firstLine > secondLine && firstCol < secondCol)
			{
				int i = firstLine-1;
				int j = firstCol+1;
				
				for(;i>secondLine ; i--, j++)
				{
					
					if(buttons[i][j].getIcon() != null)
					{
						
						if(i == secondLine + 1 && j == secondCol - 1  && !(((myIcon)buttons[i][j].getIcon()).getColor().equals(currentColor)))
						{
							buttons[i][j].setIcon(null);
							this.reducedPlayer(buttons, firstLine, firstCol);
							return true;
							
						}
						else
						{
							return false;
						}
					}
				}
				flag = true;
				
			}
			else if(firstLine < secondLine && firstCol > secondCol)
			{
				int i = firstLine+1;
				int j = firstCol-1;
				
				for(;i<secondLine ; i++, j--)
				{
					
					if(buttons[i][j].getIcon() != null)
					{
						
						if(i == secondLine - 1 && j == secondCol + 1  && !(((myIcon)buttons[i][j].getIcon()).getColor().equals(currentColor)))
						{
							buttons[i][j].setIcon(null);
							this.reducedPlayer(buttons, firstLine, firstCol);
							return true;
							
						}
						else
						{
							return false;
						}
					}
				}
				flag = true;
			}
			else if(firstLine < secondLine && firstCol < secondCol)
			{
				int i = firstLine+1;
				int j = firstCol+1;
				
				for(;i<secondLine ; i++, j++)
				{
					if(buttons[i][j].getIcon() != null)
					{
						if(i == secondLine - 1 && j == secondCol - 1  && !(((myIcon)buttons[i][j].getIcon()).getColor().equals(currentColor)))
						{
							buttons[i][j].setIcon(null);
							this.reducedPlayer(buttons, firstLine, firstCol);
							return true;
						}
						else
						{
							return false;
						}
					}
				}
				flag = true;
				
			}
		}
		
		return flag;
	}
	
	public String winChecking(myButton [][] buttons) //checks for possible win
	{
		String winnerIs = "";
		if(this.getBlackPices() == 0 )
			winnerIs = "White Player Win!!";
		else if(this.getWhitePices() == 0)
			winnerIs = "Black Player Win!!";
		else
			winnerIs = "false";
		
		return winnerIs;
	}
	
	
	public void reducedPlayer (myButton [][] buttons, int eaterLine, int eaterCol) //eliminate the opponent soldier when was eaten 
	{
		if(((myIcon)buttons[eaterLine][eaterCol].getIcon()).getColor().equals("black"))
		{
			whitePices--;
			
		}
		else if(((myIcon)buttons[eaterLine][eaterCol].getIcon()).getColor().equals("white"))
		{
			blackPices--;
			
		}
	}



}

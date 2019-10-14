import javax.swing.JFrame;

public class MVCCheckers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CheckersModel theModel = new CheckersModel();
		CheckersView theView = new CheckersView();
		CheckersControl theController = new CheckersControl(theModel, theView);
	    theView.setVisible( true ); // display frame
	    

	}

}

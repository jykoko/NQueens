package controller;

import javax.swing.JOptionPane;
import view.NQueensView;
import model.NQueensModel;

/**
 * <p>The Controller for NQueens updates the Model based on the user's input. Once the Model is updated, the Controller
 * then updates the View based on the Model's updated values.<p>
 * 
 * @author Jacob Koko
 */

public class NQueensController 
{
	private NQueensModel myModel;
	private NQueensView myView;
	private int myParsedInput;
	
    public NQueensController()
    {
       String input = JOptionPane.showInputDialog(null, "How many squares would you like in the grid? (N x N)");
       myParsedInput = Integer.parseInt(input);
       
       myModel = new NQueensModel(myParsedInput);
 	   myView = new NQueensView(this);
    }
    
    /**
     * <p>This method calls the recursive method in the Model, then updates the View based on the first solution
     *    found to the NQueens problem.<p>
     */
    public void solvePuzzle(Integer params)
    {
    	myModel.resetMyCounter();
    	myModel.solvePuzzle();
    	
    	myView.setBoard(myModel.getBoard());
    	myView.setSolutionsLabel("" + myModel.getMyCounter());
    }
    
    /**
     * <p>This method is used to allow the user to select any number of squares for the NQueens problem.
     *    It kills the current GUI frame, and opens a new View.<p>
     */
    public void numberOfSquares(Integer params)
    {
    	myView.killCurrentFrame();
    	new NQueensController();
    }
    
    public int getNumberOfSquares()
    {
    	return myParsedInput;
    }
}
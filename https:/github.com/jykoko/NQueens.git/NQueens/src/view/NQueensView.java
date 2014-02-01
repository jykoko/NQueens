package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.NQueensController;

/**
 * <p>The View I developed for NQueens is simple and user friendly. It utilizes Layout managers to ensure
 * that the View can adjust to the user's screen. The first solution found is displayed clearly for the user 
 * to see.<p>
 * 
 * @author Jacob Koko
 */

public class NQueensView extends JFrame
{
	private static final long serialVersionUID = 1L;
	private NQueensButtonListener myButtonListener;
	private NQueensController myController;
	private JButton[][]myBoardButtons;
	private JLabel mySolutionsLabel;
	private Container myContainer;
	private JFrame myMainView;
	private int myBoardSize;
	  
	public NQueensView(NQueensController controller)
	{
	    myController = controller;
	    displayGUI();
	}
	
   /**
	* <p>Method used to display all the components of the GUI.<p>
	*/
	public void displayGUI()
	{
		 myMainView = new JFrame();
		 myMainView.setSize(800, 750);
		 myMainView.setTitle("NQueens Solution Application");
		 myMainView.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 myContainer = myMainView.getContentPane();
		 myContainer.setLayout(new BorderLayout());
		 
		 myBoardSize = myController.getNumberOfSquares();
		 
		 JPanel gridPanel = new JPanel();
		 gridPanel.setLayout(new GridLayout(myBoardSize,myBoardSize,myBoardSize,myBoardSize));
		 myContainer.add(gridPanel,BorderLayout.NORTH);
		 
		 myBoardButtons = new JButton[myBoardSize][myBoardSize];
		 
		 for(int i = 0; i < myBoardButtons.length; i++)
		 {
			 for(int j = 0 ; j < myBoardButtons[i].length; j++)
			 {
				 myBoardButtons[i][j] = new JButton();
				 myBoardButtons[i][j].setText("X");
				 myBoardButtons[i][j].setFont(new Font("Georgia", Font.BOLD, 20));
				 myBoardButtons[i][j].setPreferredSize(new Dimension(100, 60));
				 myBoardButtons[i][j].setEnabled(false);
			     gridPanel.add(myBoardButtons[i][j]);
			 }
		 }
		 
		 TitledBorder border;
		 border = BorderFactory.createTitledBorder("NQueens");
		 border.setTitleFont(new Font("Georgia", Font.BOLD, 14));
	     gridPanel.setBorder(border);
	     
	     JPanel btnPanel = new JPanel();
		 btnPanel.setLayout(new FlowLayout());
		 myContainer.add(btnPanel,BorderLayout.SOUTH);
		 
		 JButton mySolveButton = new JButton("Solve NQueens");
		 mySolveButton.setVisible(true);
		 mySolveButton.setSize(100,25);
		 mySolveButton.setFont(new Font("Georgia", Font.BOLD, 14));
		 associateListener(mySolveButton, 100);
		 btnPanel.add(mySolveButton);
		 
		 JButton myNumberSquares = new JButton("# Of Squares");
		 myNumberSquares.setVisible(true);
		 myNumberSquares.setSize(100,25);
		 myNumberSquares.setFont(new Font("Georgia", Font.BOLD, 14));
		 associateListener(myNumberSquares, 200);
		 btnPanel.add(myNumberSquares);
		 
		 mySolutionsLabel = new JLabel("");
		 mySolutionsLabel.setSize(100, 25);
		 mySolutionsLabel.setFont(new Font("Georgia", Font.PLAIN, 16));
		 myContainer.add(mySolutionsLabel);
	
		 this.pack();
		 myMainView.setVisible(true);
	 }
	 
	/**
	 * <p>Simple reflection for the buttons in the GUI.<p>
	 */
	 public void associateListener(JButton button, int menuParameter)
	 {
		   try
		   {
			  Class<?> cont = myController.getClass();
			  Class<?> parameters = Class.forName("java.lang.Integer");
			  
			  Method solvePuzzle = cont.getMethod("solvePuzzle", parameters);
			  Method setNumberOfSquares = cont.getMethod("numberOfSquares", parameters);
			  
			  Object param = new Object();
			  param = menuParameter;
			  
			  if(menuParameter == 100)
			  {
				  myButtonListener = new NQueensButtonListener(myController, solvePuzzle, param);
				  button.addMouseListener(myButtonListener);
			  }
			  else if(menuParameter == 200)
			  {
				  myButtonListener = new NQueensButtonListener(myController, setNumberOfSquares, param);
				  button.addMouseListener(myButtonListener);
			  }
		   }
		   catch(ClassNotFoundException e)
		   {
			   e.printStackTrace();
		   }
		   catch(SecurityException e)
		   {
			   e.printStackTrace();
		   }
		   catch(NoSuchMethodException e)
		   {
			   e.printStackTrace();
		   }
	}
	
   /**
    * <p>Set the label based on the number of solutions possible for the current number of squares in the grid.<p>
	*/
	public void setSolutionsLabel(String newText)
	{
		mySolutionsLabel.setText("There are " + newText.toUpperCase() + " possible solutions to this problem...");
	}
    
	/**
	 * <p>Show the first solution found.<p>
	 */
	public void setBoard(boolean[][] board) 
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j])
				{
					myBoardButtons[i][j].setText("");
					myBoardButtons[i][j].setIcon(new ImageIcon("Resources/Crown-1.png"));
					myBoardButtons[i][j].setEnabled(true);
					myBoardButtons[i][j].setForeground(Color.red);
					System.out.println("correct row: " + i + " correct column: " +  j);
				}
			}
		}
		
		myMainView.repaint();
	}
    
   /**
    * <p>Dispose of the current frame so a new one can be opened.<p>
    */
	public void killCurrentFrame() 
	{
		myMainView.dispose();
	}
}
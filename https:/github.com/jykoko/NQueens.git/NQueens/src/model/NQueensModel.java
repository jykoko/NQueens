package model;

/**
 * <p>The Model for NQueens stores all the logic for generating recursive solutions to the NQueens problem.<p>
 * 
 * @author Jacob Koko
 */

public class NQueensModel 
{
   private boolean[][] myOrigBoard;
   private boolean[][] myBoard;
   public int myCounter = 0;
   private int myNumQueens;
   
   public NQueensModel(int nQueens)
   {
	   myNumQueens = nQueens;
	   myBoard = new boolean[nQueens][nQueens];
	   myOrigBoard = new boolean[nQueens][nQueens];
   }
   
   /**
    * <p>The Public solvePuzzle method is to be called from the Controller. It keeps solvePuzzle at 0.<p>
    *    
    */
   public boolean solvePuzzle()
   {
	   solvePuzzle(0);
	   return false;
   }
   
   /**
    * <p>Recursive call to generate solutions to the NQueens problem.<p>
    *    
    * @param number of columns
    */
   private boolean solvePuzzle(int ncolumns)
   {
	   if(ncolumns >= myNumQueens)
	   {
		   myCounter++;
		   
		   /*
		    * grab the first solution and store it for setting the view
		    */
		   if(myCounter == 1)
		   {
			   for(int i = 0; i < myBoard.length;i++)
			   {
				   for(int j = 0; j < myBoard[i].length;j++)
				   {
					   myOrigBoard[i][j] = myBoard[i][j];
				   }
			   }
		   }
	   }
	   else
	   {
		   for(int i = 0; i < myBoard.length; i++)
		   {
			   if(isSafeMove(i, ncolumns))
			   {
				   placeQueen(i, ncolumns);
				   
				   System.out.println("row: " + i + " column: " + ncolumns);
				   
				   if(solvePuzzle(ncolumns + 1))
				   {
					  return true;
				   }
				   else
				   {
					  removeQueen(i, ncolumns);
					  System.out.println("remove row: " + i + " remove column: " + ncolumns);
				   }
			   }
		   }
	   }
	   return false;
   }
   
   /**
    * <p>Checking that a queen can make a safe move. It does so by returning true if it is safe to move 
    *    diagonally and to the left.<p>
    *    
    * @param row
    * @param column
    */
   private boolean isSafeMove(int row, int column)
   {
	   if(column == 0)
	   {
		   return true;
	   }
	   else if(checkUpperDiag(row, column) && checkLowerDiag(row,column) && checkLeft(row, column))
	   {
		   return true;
	   }
	   
	   return false;
   }
   
   /**
    * <p>Check every cell of the grid to the upper left diagonal of the current cell where a particular queen has been found.<p>
    *    
    * @param row
    * @param column
    */
   public boolean checkUpperDiag(int row, int column)
   {
	   for (int i = row, j = column; j >= 0 && i >= 0; i--, j--) 
	   {
           if (myBoard[i][j]) 
           {
               return false;
           }
	   }
	   
	   return true;
   }
   
   /**
    * <p>Check every cell of the grid to the lower left diagonal of the current cell where a particular queen has been found.<p>
    *    
    * @param row
    * @param column
    */
   public boolean checkLowerDiag(int row, int column)
   {
	   for (int i = row, j = column; j >= 0 && i < myBoard.length; i++, j--) 
	   {
           if (myBoard[i][j]) 
           {
               return false;
           }
       }
	   
	   return true;
   }
   
   /**
    * <p>Check every column to the left of the current row where a particular queen has been found.<p>
    *    
    * @param row
    * @param column
    */
   public boolean checkLeft(int row, int column)
   {
	   for(int i = column; i >= 0; i--)
	   {
		   if(myBoard[row][i])
		   {
			   return false;
		   }
	   }
	   
	   return true;
   }
   
   /**
    * <p>If a queen does not exist in a particular location in the grid, then set it 
    *    to true(place it).<p>
    *    
    * @param row
    * @param column
    */
   public boolean placeQueen(int row, int column)
   {
	   if(!myBoard[row][column])
	   {
		   myBoard[row][column] = true;
		   return true;
	   }
	   
	   return false;
   }
   
   /**
    * <p>Used when backtracking. If a queen exists in a particular location in the grid, then set it 
    *    to false(remove it).<p>
    *    
    * @param row
    * @param column
    */
   public boolean removeQueen(int row, int column)
   {
	   if(myBoard[row][column])
	   {
		   myBoard[row][column] = false;
		   return false;
	   }
	   
	   return true;
   }
   
   public String toString()
   {   
	   return "The board length is: " + myBoard.length;
   }
   
   public boolean[][] getBoard()
   {
	   return myOrigBoard;
   }
   
   public int getMyCounter()
   {
	   return myCounter;
   }
   
   public void resetMyCounter()
   {
	   myCounter = 0;
   }
}

class Notes {
	public static void main(String[] args) {

	}

	public static int dfs(String[][] arr) {

		//start at the bottom left
		int curRow = arr.length - 1; //last the row
		int curCol = 0; //first column

		//keep track of number of moves
		int noOfMoves = 0;

		return dfsHelper(arr, curRow, curCol, noOfMoves);
		
	}

	public static int dfsHelper(String[][] arr, int curRow, int curCol, int noOfMoves) {

		//base case
		//there isn't just one

		//base case 1: When you go out of bounds
		//return -1

		//base case 2: reach the finish
		//check the value at a move and see if it is the finish

		//base case 3: Are we are inside a wall
		//return -1
		

		//recursive call to move right

		//recursive call to move up

		//compare and choose the pathway(noOfMoves) that was shorter
		//ensure that the number you send back is a valid number.

		//return the shorter pathway(noOfMoves)

		return 0;
	}
}

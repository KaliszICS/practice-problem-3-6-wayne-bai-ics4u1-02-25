public class PracticeProblem {
	// Note that this solution can account for starting at not just the bottom left corner, and ending at any place on the grid.
	// Note as well that this solution is not very optimized, as it makes redundant 'backwards' checks. This can be remedied by storing the direction that the previous "search" call used, and not going in that direction.
	// Note that this solution will trigger a stack overflow on large sets of data. To avoid this, change directions only when hitting an obstacle or border, instead of on every iteration.
	
	public static void main(String args[]) {}

	public static final String START = "S";
	public static final String FINISH = "F";
    public static final String WALL = "*";
	public static final int LARGE =(int) Math.pow(2,31)-1;

	public static int nonNegativeMin(int[] args) {
		assert args.length > 0;
		int min = LARGE;

		for (int i = 0; i < args.length; i++) {
			if (args[i] >= 0 && args[i] < min) { // non-negative && smaller than min
				min = args[i];
			}
		}
		return min;
	}

	public static int[][] getEmptyInt2d(String[][] arr) {
		int[][] newArr = new int[arr.length][];

		for (int i = 0; i < arr.length; i++) {
			newArr[i] = new int[arr[i].length];
		}

		return newArr;
	}

	public static int search(String[][] grid, int[][] stepsCounts, int myX, int myY, int stepCount) {
		if (
			stepCount == -1 || // square one
			(
				myY >= 0 && myY < grid.length &&// y within bounds
				myX >= 0 && myX < grid[myY].length && // x within bounds
				!grid[myY][myX].equals(START) && // not the starting square
                !grid[myY][myX].equals(WALL) &&
				(stepsCounts[myY][myX] == 0 || stepsCounts[myY][myX] > stepCount) // this is a shorter path or an unchecked path
			)
		) {
			stepCount += 1;
			// base cases
			stepsCounts[myY][myX] = stepCount; // update its status because it was searched
			if (grid[myY][myX].equals(FINISH)) {
				return stepCount;
			}

			return nonNegativeMin(new int[] {
				// search(grid, stepsCounts, myX, myY + 1, stepCount),
				search(grid, stepsCounts, myX, myY - 1, stepCount),
				// search(grid, stepsCounts, myX - 1, myY, stepCount),
				search(grid, stepsCounts, myX + 1, myY, stepCount)
			});
		}
		return -1; // negative to avoid being tagged by search
	}

	public static int searchMazeMoves(String[][] arr2d) {

		int[][] stepCounterArray = getEmptyInt2d(arr2d);

		for (int y = 0; y < arr2d.length; y++) {
			for (int x = 0; x < arr2d[y].length; x++) {
				if (arr2d[y][x].equals(START)) {
					int result = search(arr2d, stepCounterArray, x,y, -1);
                    if (result == LARGE) {
                        return -1;
                    }
                    return result;
				}
			}
		}
		return -1; // no "S" was provided
	}

	public static void count(String[][] grid, int[][] stepsCounts, int myX, int myY, int stepCount, int[] totalPaths) {
		System.out.println(myX + ", " + myY);
		if (
			stepCount == -1 || // square one
			(
				myY >= 0 && myY < grid.length &&// y within bounds
				myX >= 0 && myX < grid[myY].length && // x within bounds
				!grid[myY][myX].equals(START) && // not the starting square
                !grid[myY][myX].equals(WALL) &&
				(stepsCounts[myY][myX] == 0 || stepsCounts[myY][myX] >= stepCount) // this is a shorter path or an unchecked path
			)
		) {
			stepCount += 1;
			// base cases
			stepsCounts[myY][myX] = stepCount; // update its status because it was searched
			if (grid[myY][myX].equals(FINISH)) {
				totalPaths[0] += 1;
				return;
			}

				// search(grid, stepsCounts, myX, myY + 1, stepCount);
				// Beware that this will create a stack overflow due to cycling (up down up down, left right left right, up right left down up right left down ,etc.)
				count(grid, stepsCounts, myX, myY - 1, stepCount, totalPaths);
				// search(grid, stepsCounts, myX - 1, myY, stepCount)
				count(grid, stepsCounts, myX + 1, myY, stepCount, totalPaths);
		}
		return;
	}
	
	public static int noOfPaths(String[][] arr) {
		int[] trackerArray = new int[]{0};// funny way to make a private variable
		count(arr, getEmptyInt2d(arr), 0, arr.length - 1, -1, trackerArray);
		return trackerArray[0];
	}

}

/*
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=19085397)
# Instructions  

1. Create a method called _searchMazeMoves_, which takes a 2-D String array as a parameter, that searches a maze to find the minimum number of moves required to go from the start to the finish.  It will return the number of moves as an integer.

The start will always be the bottom left and denoted by an "S".  The finish can be anywhere and will always be denoted by an "F".  

2. Create a method called _noOfPaths_, which takes a 2-D String array as a parameter, that searches a maze to find how many pathways lead from the start to the finish. It will return the paths as an integer.

The start will always be the bottom left and denoted by an "S".  The finish can be anywhere and will always be denoted by an "F".

*/
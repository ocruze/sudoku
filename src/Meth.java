
public class Meth {

	/**
	 * affiche une grille
	 * 
	 * @param grid
	 */
	public static void displayGrid(int[][] grid) {
		System.out.println("\n1   2   3       4   5   6       7   8   9\n");

		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				if (grid[row][column] == 0)
					System.out.print("_   ");
				else
					System.out.print(grid[row][column] + "   ");
				if (column == grid[row].length * 1 / 3 - 1 || column == grid[row].length * 2 / 3 - 1)
					System.out.print("|   ");
			}
			System.out.println((row + 1) + "\n");
			if (row == grid.length * 1 / 3 - 1 || row == grid.length * 2 / 3 - 1)
				// System.out.println("-----------------------------------------");
				System.out.println("⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻");

		}
	}

	/**
	 * copie une grille
	 * 
	 * @param grid
	 * @return
	 */
	public static int[][] copyGrid(int[][] grid) {
		int[][] newGrid;
		newGrid = new int[grid.length][grid[0].length];
		for (int row = 0; row < newGrid.length; row++) {
			for (int column = 0; column < newGrid[row].length; column++) {
				newGrid[row][column] = grid[row][column];
			}
		}
		return newGrid;
	}

	/**
	 * change la valeur d'une case
	 * 
	 * @param grid
	 * @param row
	 * @param column
	 * @param num
	 */
	public static void changeValue(int[][] grid, int row, int column, int num) {
		grid[row][column] = num;
	}

	/**
	 * met la valeur d'une case à zéro
	 * 
	 * @param grid
	 * @param row
	 * @param column
	 */
	public static void removeValue(int[][] grid, int row, int column) {
		changeValue(grid, row, column, 0);
	}

	/**
	 * vérifie si le nombre existe déjà sur la même ligne
	 * 
	 * @param grid
	 * @param row
	 * @param num
	 * @return
	 */
	public static boolean horizontalAlreadyPresent(int[][] grid, int row, int num) {
		for (int column = 0; column < grid[row].length; column++) {
			if (num == grid[row][column])
				return true;
		}
		return false;
	}

	/**
	 * vérifie si le nombre existe déjà sur la même colonne
	 * 
	 * @param grid
	 * @param column
	 * @param num
	 * @return
	 */
	public static boolean verticalAlreadyPresent(int[][] grid, int column, int num) {// vertical check : if number
																						// already exists
		// or not
		for (int row = 0; row < grid.length; row++) {
			if (num == grid[row][column])
				return true;
		}
		return false;
	}

	/**
	 * vérifie si le nombre existe déjà sur la même région
	 * 
	 * @param grid
	 * @param row
	 * @param column
	 * @param num
	 * @return
	 */
	public static boolean blockAlreadyPresent(int[][] grid, int row, int column, int num) {
		int r = 0, c = 0;

		// on récupère la coordonnée haut-gauche du block concerné
		if (row >= 0 && row <= 2) {
			r = 0;

		} else if (row >= 3 && row <= 5) {
			r = 3;

		} else if (row >= 6 && row <= 8) {
			r = 6;
		}
		if (column >= 0 && column <= 2)
			c = 0;
		else if (column >= 3 && column <= 5)
			c = 3;
		else if (column >= 6 && column <= 8)
			c = 6;

		for (int h = r; h <= (r + 2); h++) {
			for (int w = c; w <= (c + 2); w++) {
				if (grid[h][w] == num)
					return true;
			}
		}
		return false;
	}

	/**
	 * vérifie si le joueur a gagné, c'est-à-dire si la grille est complétée avec
	 * des valeurs non nulles
	 * 
	 * @param grid
	 * @return
	 */
	public static boolean winGame(int[][] grid) {
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				if (grid[row][column] == 0)
					return false;
			}
		}
		return true;
	}

	/**
	 * vérifie si le changement de la valeur d'une certaine case est autorisé
	 * 
	 * @param grid
	 * @param row
	 * @param column
	 * @return
	 */
	public static boolean changeRemovePermitted(int[][] grid, int row, int column) {
		return (grid[row][column] == 0);
	}
}

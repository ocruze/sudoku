
public class GenerateGrid {

	/**
	 * crée une grille de Sudoku
	 * 
	 * @return
	 */
	public static int[][] generateNewGrid() {
		int row = 9, column = 9;
		int lowerBound = 1, upperBound = 9;
		boolean horizontalAlreadyPresent, verticalAlreadyPresent, blockAlreadyPresent;

		int[][] grid;
		grid = new int[row][column];
		int counter = 0;

		int random;

		for (row = 0; row < grid.length; row++) {
			for (column = 0; column < grid[row].length; column++) {
				do {
					random = (int) (Math.random() * ((upperBound - lowerBound) + 1) + lowerBound);
					horizontalAlreadyPresent = Meth.horizontalAlreadyPresent(grid, row, random);
					verticalAlreadyPresent = Meth.verticalAlreadyPresent(grid, column, random);
					blockAlreadyPresent = Meth.blockAlreadyPresent(grid, row, column, random);

					if (horizontalAlreadyPresent || verticalAlreadyPresent || blockAlreadyPresent)
						counter++;
					if (counter > 1 && counter % 50 == 0) {// si le programme se bloque à une case et qu'il n'a toujours
															// pas réussi à trouver un bon nombre après 50 essais, il va
															// recommencer cette ligne
						clearRow(grid, row);
						column = 0;
					}

					if (counter % 2000 == 0) {// si le programme se bloque à une case et qu'il n'a toujours pas réussi à
												// trouver un bon nombre après 2000 essais, il va recommencer la grille
						clearGrid(grid);
						row = 0;
						column = 0;
						break;
					}
				} while (horizontalAlreadyPresent || verticalAlreadyPresent || blockAlreadyPresent);
				grid[row][column] = random;
			}
		}
		return grid;
	}

	/**
	 * vide une ligne
	 * 
	 * @param grid
	 * @param row
	 */
	public static void clearRow(int[][] grid, int row) {
		for (int index = 0; index < grid.length; index++) {
			Meth.removeValue(grid, row, index);
		}
	}

	/**
	 * vide la grille entièrement
	 * 
	 * @param grid
	 */
	public static void clearGrid(int[][] grid) {
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				Meth.removeValue(grid, row, column);
			}
		}
	}

	/**
	 * met des blancs dans la grille, autrement dit, enlève des nombres de la grille
	 * 
	 * @param grid
	 * @param howMany
	 */
	public static void putSomeBlanks(int[][] grid, int howMany) {
		int lowerBound = 0, upperBound = 8;
		int randomRow, randomColumn;
		for (int i = 1; i <= howMany; i++) {
			randomRow = (int) (Math.random() * ((upperBound - lowerBound) + 1) + lowerBound);
			randomColumn = (int) (Math.random() * ((upperBound - lowerBound) + 1) + lowerBound);
			Meth.removeValue(grid, randomRow, randomColumn);
		}
	}
}

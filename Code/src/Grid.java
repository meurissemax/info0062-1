/**
* This class is used to create the grid of the game. The grid is
* responsible for managing the entire simulation of the game.
*
* It initials a {@link grid} of {@link Cell} and creates a cell per
* box of the grid. Then, for each update of the grid, the living
* neighbors of each cell are counted to determine if the cell will
* live or die after updating the grid.
*
* The grid also initializes the borders ({@link borderLeft} and
* {@link borderRight}) that will be incremented by the cells therein.
*
* @author	M. Meurisse
*/
public class Grid {
	private int gridSize;
	private Cell[][] grid;

	private Border borderLeft;
	private Border borderRight;

	/**
	* This constructor creates a grid of a determined size and its
	* left and right borders.
	*
	* On an area of a determined dimension (centered in the middle
	* of the grid), cells are created randomly. Anywhere else on the
	* grid, dead cells are created.
	*
	* @param gridSize 				the size of the grid
	* @param spawnSize 				the size of the center area of the
	*								grid where the cells are randomly
	*								initialized
	* @throws WrongArgsException 	the exception that can be generated
	*								if the dimensions of the grid and the
	*								area of appearance do not comply
	*/
	public Grid(int gridSize, int spawnSize) throws WrongArgsException {
		if(gridSize < 2 || spawnSize > gridSize || ((gridSize-spawnSize) % 2) != 0) {
			throw new WrongArgsException("Error: dimensions of the grid are incorrect.");
		} else {
			this.gridSize = gridSize;
			grid = new Cell[gridSize][gridSize];

			borderLeft = new Border(gridSize);
			borderRight = new Border(gridSize);

			int beginSpawn = (gridSize - spawnSize) / 2;
			int endSpawn = beginSpawn + spawnSize;

			for(int i = 0; i < gridSize; i++) {
				for(int j = 0; j < gridSize; j++) {
					if(j == 0) {
						if(spawnSize == gridSize)
							grid[i][j] = new BorderCell(i, borderLeft);
						else
							grid[i][j] = new BorderCell(false, i, borderLeft);
					}

					if(j == gridSize - 1) {
						if(spawnSize == gridSize)
							grid[i][j] = new BorderCell(i, borderRight);
						else
							grid[i][j] = new BorderCell(false, i, borderRight);
					}

					if(i >= beginSpawn && j >= beginSpawn && i < endSpawn && j < endSpawn) {
						if(j != 0 && j != gridSize - 1)
							grid[i][j] = new Cell();
					} else {
						if(j != 0 && j != gridSize - 1)
							grid[i][j] = new Cell(false);
					}
				}
			}
		}
	}

	/**
	* This method counts the number of living cells around the cell at
	* the (x, y) position.
	*
	* @param x 	horizontal position of the cell in the grid (matrix
	*			convention)
	* @param y 	vertical position of the cell in the grid (matrix
	*			convention)
	* @return 	the number of living cells around the concerned cell
	*/
	private int nbLivingNeighbors(int x, int y) {
		int count = 0;

		for(int i = x - 1; i <= x + 1; i++) {
			for(int j = y - 1; j <= y + 1; j++) {
				if(i >= 0 && j >= 0 && i < gridSize && j < gridSize && (i != x || j != y)) {
					if(grid[i][j].getCurrentState())
						count++;
				}
			}
		}

		return count;
	}

	/**
	* This method determines, depending on the number of its living
	* neighbors, whether the cell should live or die.
	*
	* @param x 	horizontal position of the cell in the grid (matrix
	*			convention)
	* @param y 	vertical position of the cell in the grid (matrix
	*			convention)
	* @return 	the next state of the cell (true if the cell will
	*			live and false if the cell will die)
	*/
	private boolean nextCellState(int x, int y) {
		int count = nbLivingNeighbors(x, y);

		if(grid[x][y].getCurrentState()) {
			if(count < 2 || count > 3)
				return false;
			else
				return true;
		} else {
			if(count == 3)
				return true;
		}

		return false;
	}

	/**
	* This method updates the grid.
	*
	* First, it changes the next state of each cell in the grid and, if
	* it is a cell on a border, updates the border counters. Then, it
	* updates the current state of each cell in the grid.
	*/
	public void updateGrid() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				grid[i][j].changeState(nextCellState(i, j));

				if(j == 0)
					((BorderCell)grid[i][j]).updateBorder(i, borderLeft);

				if(j == (gridSize - 1))
					((BorderCell)grid[i][j]).updateBorder(i, borderRight);
			}
		}

		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				grid[i][j].updateState();
			}
		}
	}

	/**
	* This method displays all the cells in the grid (depending on
	* their state) and the counters for each border.
	*/
	public void displayGrid() {
		for(int i = 0; i < gridSize; i++) {
			System.out.print(borderLeft.displayBorder(i) + " ");

			for(int j = 0; j < gridSize; j++) {
				System.out.print(grid[i][j].displayState() + " ");
			}

			System.out.println(borderRight.displayBorder(i) + " ");
		}

		System.out.println();
	}
}

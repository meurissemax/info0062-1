/**
* This class is the main class of the program. It initials the
* {@link gameGrid} and updates it (once every {@link MS_TO_WAIT}
* milliseconds).
*
* @author	M. Meurisse
*/
public class GameOfLife {
	private final static int MS_TO_WAIT = 300;

	private static int gridSize, spawnSize;
	private static Grid gameGrid;

	/**
	* This is the main method of the program. It initials the
	* grid according to the dimensions entered by the user, updates
	* it and displays it.
	*
	* @param args 	table of parameters entered by the user when the
	*				program is launched
	*/
	public static void main(String[] args) {
		if(args.length == 2) {
			try {
				gridSize = Integer.parseInt(args[0]);
				spawnSize = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				System.err.println(e.getMessage());

				return;
			}

			try {
				gameGrid = new Grid(gridSize, spawnSize);
			} catch(WrongArgsException e) {
				System.err.println(e.getMessage());

				return;
			}

			gameGrid.displayGrid();

			while(true) {
				gameGrid.updateGrid();
				gameGrid.displayGrid();

				try {
					Thread.sleep(MS_TO_WAIT);
				} catch(InterruptedException e) {
					System.err.println(e.getMessage());

					return;
				}
			}
		} else {
			String usage = "Usage: java GameOfLife [gridSize] [spawnSize]";
			System.err.println(usage);

			return;
		}
	}
}

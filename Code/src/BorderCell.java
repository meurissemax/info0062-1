/**
* This class inherits the {@link Cell} class. It creates the
* cells on the borders. These have, in addition to normal
* cells, a method for incrementing the border counters.
*
* @author	M. Meurisse
*/
public class BorderCell extends Cell {
	/**
	* This constructor calls the corresponding constructor
	* of the {@link Cell} class and updates the counter of
	* the border on which the cell is located at the line
	* position when the cell is created.
	*
	* @param line	the position of the cell on the border
	* @param b 		the border where the cell is located
	*/
	public BorderCell(int line, Border b) {
		super();

		if(currentState)
			b.addCell(line);
	}

	/**
	* This constructor calls the corresponding constructor
	* of the {@link Cell} class and updates the counter of
	* the border on which the cell is located at the line
	* position when the cell is created.
	*
	* @param initialState	the initial state of the cell
	*						created
	* @param line			the position of the cell on the border
	* @param b 				the border where the cell is located
	*/
	public BorderCell(boolean initialState, int line, Border b) {
		super(initialState);

		if(currentState)
			b.addCell(line);
	}

	/**
	* This method updates the counter of the border on which
	* the cell is located at the line position when the cell
	* is created or dies.
	*
	* @param line	the position of the cell on the border
	* @param b 		the border where the cell is located
	*/
	public void updateBorder(int line, Border b) {
		if(currentState != nextState)
			if(nextState)
				b.addCell(line);
			else
				b.removeCell(line);
	}
}

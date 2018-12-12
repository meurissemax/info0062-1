import java.util.Random;

/**
* This class creates the cells that make up the grid. Each
* cell is defined by its {@link currentState} (the state it
* has on the grid) and its {@link nextState} (the state it
* will have after updating the grid).
*
* The state of the cell is true if the cell is alive and false
* if the cell is dead.
*
* @author	M. Meurisse
*/
public class Cell {
	protected final static String STRING_DEAD = "-";
	protected final static String STRING_ALIVE = "*";

	protected boolean currentState;
	protected boolean nextState;

	/**
	* This constructor randomly initializes the state of the
	* created cell.
	*/
	public Cell() {
		Random randState = new Random();

		if(randState.nextInt(2) > 0)
			currentState = true;
		else
			currentState = false;

		nextState = false;
	}

	/**
	* This constructor initializes the state of the created cell
	* to an initial state.
	*
	* @param initialState	the initial state (true or false) of
	*						the created cell
	*/
	public Cell(boolean initialState) {
		currentState = initialState;
		nextState = false;
	}

	/**
	* This method provides the {@link currentState} of a cell.
	*
	* @return	true if the cell is alive and false if the
	*			cell is dead
	*/
	public boolean getCurrentState() {
		return currentState;
	}

	/**
	* This method provides the {@link nextState} of a cell.
	*
	* @return	true if the cell will be created (or remain
	*			alive) and false if the cell will die (or
	*			remain dead)
	*/
	public boolean getNextState() {
		return nextState;
	}

	/**
	* This method is used to define the {@link nextState} of a
	* cell.
	*
	* @param nextState 		the next state of the cell
	*/
	public void changeState(boolean nextState) {
		this.nextState = nextState;
	}

	/**
	* This method updates the {@link currentState} of the cell
	* by assigning it its {@link nextState}.
	*/
	public void updateState() {
		currentState = nextState;
	}

	/**
	* This method returns the {@link currentState} of the cell
	* as a String for display.
	*
	* @return 	the current state of the cell, in format of a
	*			String ({@link STRING_ALIVE} if the cell is alive
	*			and {@link STRING_DEAD} if the cell is dead)
	*/
	public String displayState() {
		if(currentState)
			return STRING_ALIVE;
		else
			return STRING_DEAD;
	}
}

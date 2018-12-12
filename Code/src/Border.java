/**
* This class creates the left and right borders of the game. Each border
* contains 2 counters: one to count the number of cells appearing on the
* border and the other the number of dead cells. These counters are
* incremented by the {@link BorderCell}.
*
* @author	M. Meurisse
*/
public class Border {
	private int[] countAlive;
	private int[] countDead;

	/**
	* The constructor of this class counts as many counters as
	* elements on the borders, and initializes each counter to 0.
	*
	* @param nbElements 	the number of elements on a border
	*/
	public Border(int nbElements) {
		countAlive = new int[nbElements];
		countDead = new int[nbElements];

		for(int i = 0; i < nbElements; i++) {
			countAlive[i] = 0;
			countDead[i] = 0;
		}
	}

	/**
	* This method increments the created cell counter.
	*
	* @param element 	the element of the border on which the cell
	*					was created
	*/
	public void addCell(int element) {
		countAlive[element]++;
	}

	/**
	* This method increments the dead cell counter.
	*
	* @param element 	the element of the border on which the cell
	*					is dead
	*/
	public void removeCell(int element) {
		countDead[element]++;
	}

	/**
	* This method display the counter of an element of the border.
	*
	* @param element 	the element whose counter will be displayed
	* @return 			the counter in a String format
	*/
	public String displayBorder(int element) {
		String display = "[+ "+ String.format("%3d", countAlive[element]) +"/- "+ String.format("%3d", countDead[element]) +"]";

		return display;
	}
}

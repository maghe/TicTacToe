package com.topdesk.cases.tictactoe;

/**
 * A GameBoard is a snapshot in an ongoing tic-tac-toe game. The tic-tac-toe
 * board is constructed as a 3x3 array of unique Cells. For a game that is about
 * to start, all cells of the GameBoard are set to the empty state. 
 * <p>
 * Instances of implementations of this interface are immutable.
 */
public interface GameBoard {

	/**
	 * @returns the CellState at CellLocation.
	 */
	CellState getCellState(CellLocation cellLocation);
}

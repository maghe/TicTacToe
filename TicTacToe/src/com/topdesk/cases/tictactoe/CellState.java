package com.topdesk.cases.tictactoe;

/**
 * A state that a cell may have.
 */
public enum CellState {
	/** this cell is occupied by player X */
	OCCUPIED_BY_X,

	/** this cell is occupied by player O */
	OCCUPIED_BY_O,

	/** this cell is Empty */
	EMPTY;
}

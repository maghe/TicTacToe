package com.topdesk.cases.tictactoe;

/**
 * Location of a cell within a GameBoard. No two cells can share the same
 * location.
 */
public enum CellLocation {
	/** The Cell in the top row and leftmost column */
	TOP_LEFT,

	/** The Cell in the top row and centre column */
	TOP_CENTRE,

	/** The Cell in the top row and rightmost column */
	TOP_RIGHT,

	/** The Cell in the centre row and leftmost column */
	CENTRE_LEFT,

	/** The Cell in the centre row and centre column */
	CENTRE_CENTRE,

	/** The Cell in the centre row and rightmost column */
	CENTRE_RIGHT,

	/** The Cell in the bottom row and leftmost column */
	BOTTOM_LEFT,

	/** The Cell in the bottom row and centre column */
	BOTTOM_CENTRE,

	/** The Cell in the bottom row and rightmost column */
	BOTTOM_RIGHT;
}

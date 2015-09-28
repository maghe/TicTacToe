package com.topdesk.cases.tictactoe;

/**
 * A {@code Consultant} advises which move to make in an in-progress game of
 * Tic-tac-toe.
 *
 * <p>
 * Tic-tac-toe is a game for two players, player X and player O, who take turns
 * marking the spaces in a 3x3 grid of Cells. The player who succeeds in placing
 * three respective marks in a horizontal, vertical, or diagonal row wins the
 * game. When the game has not started yet, player X is the first to make a
 * move.
 *
 * <p>
 * See: http://en.wikipedia.org/wiki/Tic-tac-toe for complete game dynamics.
 *
 * <p>
 * The consultant should advice a move following an optimal strategy, i.e. the
 * outcome of the game should lead to the best possible result for the player to
 * make a move.
 *
 * <p>
 * Winning is better than drawing is better than losing.
 * 
 * <p>
 * The consultant must adhere to the traditional rules of the game as documented
 * above.
 * 
 * <p>
 * Instances should provide a void (no arguments) constructor.
 * 
 * <p>
 * Instances of implementations of this interface are immutable.
 */
public interface Consultant {

	/**
	 * Suggests a move by following an optimal strategy for the game in
	 * progress. For some games there can be more than one best suggestion.
	 * 
	 * <p>
	 * This method should figure out itself whose turn (X or O) it is to play,
	 * before making a suggestion.
	 * 
	 * <p>
	 * If the given parameter {@code gameBoard} represents a situation that
	 * cannot occur with normal play, the behavior of this method is undefined.
	 * An example of such a situation can be a {@code gameBoard} where two cells
	 * are occupied by X and where the rest of the cells are empty.
	 * 
	 * @param gameBoard
	 *            an in-progress game.
	 * @return The CellLocation that this consultant chooses to suggest. Must be
	 *         a cell having EMPTY state
	 * @throws NullPointerException
	 *             if {@code gameBoard} is {@code null}
	 * @throws IllegalStateException
	 *             if {@code gameBoard} represents a game that is over, i.e.
	 *             already won by either X or O, or there are no empty cells
	 *             left.
	 */
	CellLocation suggest(GameBoard gameBoard);
}

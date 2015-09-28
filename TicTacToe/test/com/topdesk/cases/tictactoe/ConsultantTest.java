package com.topdesk.cases.tictactoe;

import static com.google.common.base.Preconditions.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.topdesk.cases.tictactoe.yoursolution.YourConsultant;

public class ConsultantTest {

	private Consultant consultant;

	@Before
	public void setPlayer() {
		consultant = new YourConsultant();
	}

	@Test
	public void everyMoveIsGoodOnEmptyBoard() {
		assertNotNull(consultant.suggest(board("empty")));
	}

	@Test
	public void onlyOneMoveLeft() {
		assertEquals(CellLocation.BOTTOM_LEFT, consultant.suggest(board("only_move")));
	}

	@Test
	public void firstMoveO() {
		inList(consultant.suggest(board("first_move_o01")), CellLocation.TOP_LEFT, CellLocation.TOP_RIGHT, CellLocation.BOTTOM_LEFT, CellLocation.BOTTOM_RIGHT);
	}

	@Test
	public void oneWinningMove() {
		assertEquals(CellLocation.TOP_LEFT, consultant.suggest(board("one_winning_move01")));
	}

	@Test
	public void oneMoveNotLosing() {
		assertEquals(CellLocation.CENTRE_LEFT, consultant.suggest(board("one_move_not_losing01")));
	}

	@Test
	public void onlyOneNotWinningMove() {
		assertFalse(consultant.suggest(board("only_one_not_winning_move01")).equals(CellLocation.CENTRE_LEFT));
	}

	@Test
	public void fork00() {
		inList(consultant.suggest(board("fork00")), CellLocation.TOP_RIGHT, CellLocation.BOTTOM_RIGHT);
	}

	@Test(expected = IllegalStateException.class)
	public void drawn() {
		consultant.suggest(board("drawn01"));
	}

	@Test(expected = IllegalStateException.class)
	public void oWon() {
		consultant.suggest(board("o_won01"));
	}

	@Test(expected = IllegalStateException.class)
	public void xWon() {
		consultant.suggest(board("x_won01"));
	}

	// Helper stuff below

	private static void inList(Object actual, Object... validItems) {
		if (!Lists.newArrayList(validItems).contains(actual)) {
			fail("Expected one of: " + Arrays.toString(validItems) + " but is: " + actual);
		}
	}

	private static GameBoard board(String file) {
		Path path = FileSystems.getDefault().getPath(".\\test", file + ".txt");
		final List<String> lines;
		try {
			lines = Files.readAllLines(path, Charset.defaultCharset());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return new GameBoard() {

			@Override
			public CellState getCellState(CellLocation field) {
				Coordinate coord = FIELD_COORDINATE.get(checkNotNull(field));
				switch (lines.get(coord.y).charAt(coord.x)) {
				case 'x':
					return CellState.OCCUPIED_BY_X;
				case 'o':
					return CellState.OCCUPIED_BY_O;
				default:
					return CellState.EMPTY;
				}
			}
		};
	}

	private static final Map<CellLocation, Coordinate> FIELD_COORDINATE = new EnumMap<>(CellLocation.class);
	static {
		FIELD_COORDINATE.put(CellLocation.TOP_LEFT, Coordinate.of(0, 0));
		FIELD_COORDINATE.put(CellLocation.TOP_CENTRE, Coordinate.of(1, 0));
		FIELD_COORDINATE.put(CellLocation.TOP_RIGHT, Coordinate.of(2, 0));

		FIELD_COORDINATE.put(CellLocation.CENTRE_LEFT, Coordinate.of(0, 1));
		FIELD_COORDINATE.put(CellLocation.CENTRE_CENTRE, Coordinate.of(1, 1));
		FIELD_COORDINATE.put(CellLocation.CENTRE_RIGHT, Coordinate.of(2, 1));

		FIELD_COORDINATE.put(CellLocation.BOTTOM_LEFT, Coordinate.of(0, 2));
		FIELD_COORDINATE.put(CellLocation.BOTTOM_CENTRE, Coordinate.of(1, 2));
		FIELD_COORDINATE.put(CellLocation.BOTTOM_RIGHT, Coordinate.of(2, 2));
	}

	private static final class Coordinate {
		private static final Coordinate[] VALUES = {
				new Coordinate(0, 0),
				new Coordinate(0, 1),
				new Coordinate(0, 2),
				new Coordinate(1, 0),
				new Coordinate(1, 1),
				new Coordinate(1, 2),
				new Coordinate(2, 0),
				new Coordinate(2, 1),
				new Coordinate(2, 2) };

		final int x;
		final int y;

		private Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		static Coordinate of(int x, int y) {
			return VALUES[x * 3 + y];
		}
	}
}

package com.bomberman;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
	static char[][] grid;
	static int[] playerPosition = new int[2];
	static int[] keyPosition = new int[2];
	static Set<String> villainPos = new HashSet<>();
	Scanner scan;

	Game() {
		scan = new Scanner(System.in);
		initializeGrid();
	}

	private void initializeGrid() {
		System.out.println("Enter map size");
//		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		grid = new char[size][size];
		wall(grid);
		printGrid();
		System.out.println("Enter the player positions(ex:CB c->row , B->col)");
		String player = scan.next();
		playerPosition[0] = (int) player.charAt(0) - 65;
		playerPosition[1] = (int) player.charAt(1) - 65;
		System.err.println(playerPosition[0] + " " + playerPosition[1]);
		grid[playerPosition[0]][playerPosition[1]] = 'P';
		System.out.println("Enter the Key position");
		String key = scan.next();
		keyPosition[0] = (int) key.charAt(0) - 65;
		keyPosition[1] = (int) key.charAt(1) - 65;
		grid[keyPosition[0]][keyPosition[1]] = 'K';
		System.out.println("Enter the Number of Villain");
		int villainCount = scan.nextInt();
		int row = 0, col = 0;
		for (int i = 0; i < villainCount; i++) {
			System.out.println("Enter the villain position");
			String villain = scan.next();
			row = (int) villain.charAt(0) - 65;
			col = (int) villain.charAt(1) - 65;

//			String pos = (int) (villain.charAt(0) - 65) + "," + (int) (villain.charAt(1) - 65);
			villainPos.add(row + "," + col);
			grid[row][col] = 'V';

		}
		printGrid();
	}

	private static void printGrid() {
		System.out.print("  ");
		for (int i = 0; i < grid.length; i++) {
			System.out.print((char) (i + 'A') + " ");
		}
		System.out.println();

		for (int i = 0; i < grid.length; i++) {
			System.out.print((char) (i + 'A') + " ");
			for (int j = 0; j < grid.length; j++) {
//				System.out.println();
				System.out.print(grid[i][j] != 0 ? grid[i][j] + " " : "  ");
			}
			System.out.println();
		}

	}

	private static void wall(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			grid[0][i] = '*';
			grid[i][0] = '*';
			grid[i][grid.length - 1] = '*';
			grid[grid.length - 1][i] = '*';
		}
		for (int i = 2; i < grid.length - 2; i += 2) {
			for (int j = 2; j < grid.length - 2; j += 2) {
				grid[i][j] = '*';
			}
		}
//		for (int i = 0; i < grid.length; i++) {
//			for (int j = 0; j < grid.length; j++) {
//				System.out.print(grid[i][j] != 0 ? grid[i][j] + " " : "  ");
//			}
//			System.out.println();
//		}

	}

	public void start() {
		System.out.print("Enter the direction : ");
		char direction = scan.next().charAt(0);
		int row = playerPosition[0];
		int col = playerPosition[1];
		switch (direction) {
		case 'W': {
			if (grid[row - 1][col] != '*' || grid[row - 1][col] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
			}
			grid[row - 1][col] = 'P';
			playerPosition[0] = row - 1;
			break;
		}
		case 'S': {
			if (grid[row + 1][col] != '*' || grid[row + 1][col] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
			}
			grid[row + 1][col] = 'P';
			playerPosition[0] = row + 1;
			break;
		}
		case 'A': {
			if (grid[row][col - 1] != '*' || grid[row][col - 1] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
			}
			grid[row][col - 1] = 'P';
			playerPosition[1] = col - 1;
			break;
		}
		case 'D': {
			if (grid[row][col + 1] != '*' || grid[row][col + 1] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
			}
			grid[row][col + 1] = 'P';
			playerPosition[1] = col + 1;
			break;
		}

		}

	}

}

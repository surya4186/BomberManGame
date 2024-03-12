package com.bomberman;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Game {
	static char[][] grid;
	static int[] playerPosition = new int[2];
	static int[] keyPosition = new int[2];
	static Set<String> villainPos = new HashSet<>();
	static Queue<int[]> bombList = new ArrayDeque<>();
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
//		System.err.println(playerPosition[0] + " " + playerPosition[1]);
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
			villainPos.add(row + "," + col);
			grid[row][col] = 'V';

		}
		System.out.println("Enter the Number of Bricks");
		int brick = scan.nextInt();
		System.out.println("Enter the Bricks position");
		for (int i = 0; i < brick; i++) {
			String bricks = scan.next();
			row = (int) bricks.charAt(0) - 65;
			col = (int) bricks.charAt(1) - 65;
			grid[row][col] = 'B';

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

	}

	public void start() {
		System.out.print("Enter the direction : ");
		char direction = scan.next().charAt(0);
		int row = playerPosition[0];
		int col = playerPosition[1];
		PlantBomb();
		switch (direction) {
		case 'W': {
			if (grid[row - 1][col] != '*' || grid[row - 1][col] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
				grid[row - 1][col] = 'P';
				playerPosition[0] = row - 1;
				printGrid();
			}

			break;
		}
		case 'S': {
			if (grid[row + 1][col] != '*' || grid[row + 1][col] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
				grid[row + 1][col] = 'P';
				playerPosition[0] = row + 1;
				printGrid();
			}

			break;
		}
		case 'A': {
			if (grid[row][col - 1] != '*' || grid[row][col - 1] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
				grid[row][col - 1] = 'P';
				playerPosition[1] = col - 1;
				printGrid();
			}

			break;
		}
		case 'D': {
			if (grid[row][col + 1] != '*' || grid[row][col + 1] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
				grid[row][col + 1] = 'P';
				playerPosition[1] = col + 1;
				printGrid();
			}

			break;
		}
		case 'Q': {
			if (grid[row - 1][col - 1] != '*' || grid[row - 1][col - 1] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
				grid[row - 1][col - 1] = 'P';
				playerPosition[0] = row - 1;
				playerPosition[1] = col - 1;
				printGrid();
			}

			break;
		}
		case 'R': {
			if (grid[row - 1][col + 1] != '*' || grid[row - 1][col + 1] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
				grid[row - 1][col + 1] = 'P';
				playerPosition[0] = row - 1;
				playerPosition[1] = col + 1;
				printGrid();
			}

			break;
		}
		case 'Z': {
			if (grid[row + 1][col - 1] != '*' || grid[row + 1][col - 1] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
				grid[row + 1][col - 1] = 'P';
				playerPosition[0] = row + 1;
				playerPosition[1] = col - 1;
				printGrid();
			}

			break;
		}
		case 'C': {
			if (grid[row + 1][col + 1] != '*' || grid[row + 1][col + 1] != 'B') {
				if (grid[row][col] != 'X') {
					grid[row][col] = ' ';
				}
				grid[row + 1][col + 1] = 'P';
				playerPosition[0] = row + 1;
				playerPosition[1] = col + 1;
				printGrid();
			}

			break;
		}

		}
		if (villainPos.contains(playerPosition[0] + "," + playerPosition[1])) {
			System.out.println("You lose !!!");
			System.exit(0);
		} else if (grid[playerPosition[0]][playerPosition[1]] == 'K') {
			System.out.println("You Won !!!");

		}
		printGrid();

	}

	private void PlantBomb() {
		while (true) {
			int row = playerPosition[0];
			int col = playerPosition[1];
			System.out.print("1.Plant bomb \n2.detonates\n3.CONTINUE\n CHOICE : ");
			int choice = scan.nextInt();
			if (choice == 1) {
				if (bombList.size() != 0) {
					System.out.println("You must detonates the bomb");
					System.out.print("blast...\n1.yes\n2.no");
					choice = scan.nextInt();
					if (choice == 1) {
						if (bombList.size() == 0) {
							System.out.println("No bomb to detonates");
						} else {
							blast();

						}

					}
				} else {
					grid[row][col] = 'X';
					bombList.add(playerPosition);
				}
			} else if (choice == 2) {
				blast();
			} else {
				return;

			}
		}

	}

	private void blast() {
		boolean player = true;
		for (int[] bomb : bombList) {
			if (grid[bomb[0] - 1][bomb[1]] == 'P' || grid[bomb[0] + 1][bomb[1]] == 'P'
					|| grid[bomb[0]][bomb[1] - 1] == 'P' || grid[bomb[0]][bomb[1] + 1] == 'P') {
				player = true;

			}
			grid[bomb[0]][bomb[1]] = ' ';
			if (grid[bomb[0] - 1][bomb[1]] != '*' && grid[bomb[0] - 1][bomb[1]] != 'K') {
				grid[bomb[0] - 1][bomb[1]] = ' ';
			}

			if (grid[bomb[0] + 1][bomb[1]] != '*' && grid[bomb[0] + 1][bomb[1]] != 'K') {
				grid[bomb[0] + 1][bomb[1]] = ' ';

			}

			if (grid[bomb[0]][bomb[1] - 1] != '*' && grid[bomb[0]][bomb[1] - 1] != 'K') {
				grid[bomb[0]][bomb[1] - 1] = ' ';

			}

			if (grid[bomb[0]][bomb[1] + 1] != '*' && grid[bomb[0]][bomb[1] + 1] != 'K') {
				grid[bomb[0]][bomb[1] + 1] = ' ';

			}

		}
		if (player) {
			printGrid();
			System.out.println("lost Player Died !!!");
			System.exit(0);
		}

	}

}
//////9,1,3,5,4,2  12;
////9,1,2
////3,
//7
//34 7
//5,6,3,4,7;

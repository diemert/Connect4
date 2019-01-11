package app;

public class Board {
	private char[][] board;
	private int size;
	private int goal;
	private int score1;
	private int score2;
	public Board(int size) {
		board = new char[size][size];
		reset();
	}
	
	public void setSize (int newSize) {
		size = newSize;
	}
	
	public void setGoal (int newGoal) {
		goal = newGoal;
	}
	// adding pieces
	public boolean dropPiece (char player, int column) {
		if (column >= board.length || column < 0) return false;
		else {
			for (int r = board.length - 1; r >= 0; r--) {
				if (board[r][column] == '-') {
					board[r][column] = player;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean anotherPlayPossible() {
		// check for '-'
		for (char[] row : board) {
			for (int c = 0; c < row.length; c++) {
				if (row[c] == '-') return true;
			}
		}
		// else
		return false;
	}
	
	public boolean isWinner(char player) {
		if (verticalWin(player)) return true;
		else if (horizontalWin(player)) return true;
		else if (diagonalPosWin(player)) return true;
		else if (diagonalNegWin(player)) return true;
		return false;
	}
	
	public boolean tieGame() {
		return !anotherPlayPossible();
	}
	
	public boolean diagonalNegWin(char player) {
		for(int r = 0; r < board.length; r++) {
			int count = 0;
			int row_copy = r;
			for(int c = 0; r+c < size; c++) {
				if(board[row_copy][c] == player) count++;
				
				else count = 0;
				
				row_copy++;
				if (count == goal) return true;
			}
		}
		for(int c = 1; c <= size-goal; c++) {
			int count = 0;
			for(int r = 0; r+c < size; r++) {
				if(board[r][c+r] == player) count++;
				else count = 0;
				
				if (count == goal) return true;
			}
		}
		
		return false;
	}
	
	public boolean diagonalPosWin(char player) {
		for(int r = size-goal; r < board.length; r++) {
			int count = 0;
			for(int c = 0; c <= r; c++) {
				if(board[r-c][c] == player) count++;
				else count = 0;
				
				if (count == goal) return true;
			}
		}
		for (int c = 1; c <= size-goal; c++) {
			int count = 0;
			int col_copy = c;
			for(int r = size-1; r >= c; r--) {
				if(board[r][col_copy] == player) count++;
				else count = 0;
				
				col_copy++;
				if (count == goal) return true;
			}
		}
		
		return false;
	}
	
	public boolean horizontalWin(char player) {
		for (int r = 0; r < board.length; r++) {
			int count = 0;
			for(int c = 0; c < board.length; c++) {
				if (board[r][c] == player) {
					count++;
				}
				else count = 0;
				
				if (count==goal) return true;
			}
		}
		return false;
	}
		
	public boolean verticalWin(char player) {
		for (int c = 0; c < board.length; c++) {
			int count = 0;
			for(int r = 0; r < board.length; r++) {
				if (board[r][c] == player) {
					count++;
				}
				else count = 0;
				
				if (count==goal) return true;
			}
		}
		return false;
	}
	
	public String changeScore (char player) {
		if (player == 'X') score1++;
		if (player == 'O') score2++;
		return "\nPlayer 1: " + score1 + "\nPlayer 2: " + score2 + "\n";
	}
	
	public void reset() {
		for (char[] row : board) {
			for (int c = 0; c < row.length; c++) {
				row[c] = '-';
			}
		}
	}
	// toString: print the current board
	@Override
	public String toString() {
		for (int x = 0; x < size; x++) {
			System.out.print(x + " ");
		}
		System.out.println();
		String result = "";
		// print board
		for (char[] row : board) {
			for (char col : row) {
				result += col + " ";
			}
			result += "\n";
		}
		return result;
	}
}

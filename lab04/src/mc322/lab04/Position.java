package mc322.lab04;

import java.lang.Math;

// Representa uma posição coluna-linha num tabuleiro de Resta Um
public class Position {
	private char column;
	private int line;
	
	// A coluna é um caracter em [a-g] e a linha um número em [1-7]
	public Position(char column, int line) {
		this.column = column;
		this.line = line;
	}
	
	public Position(String stringPosition) {
		this.column = stringPosition.charAt(0);
		this.line = stringPosition.charAt(1) - '0';
	}
	
	public char getColumn() {
		return column;
	}
	
	public int getLine() {
		return line;
	}
	
	// Determina se é uma posição válida para uma peça no tabuleiro
	public boolean isValid() {
		// Verifica se está completamente fora do tabuleiro
		if (line < 1 || line > 7 || column < 'a' || column > 'g') {
			return false;
		}
		// Verifica se está nas posições não jogáveis do tabuleiro
		if ((line < 3 || line > 5) && (column < 'c' || column > 'e')) {
			return false;
		}
		return true;
	}
	
	// Retorna o índice da coluna começando a contagem em 0
	public int getColumnIndex() {
		return (int)(column - 'a');
	}

	// Retorna o índice da linha começando a contagem em 0
	public int getLineIndex() {
		return line - 1;
	}
	
	public int getColumnDistanceTo(Position other) {
		return Math.abs(column - other.getColumn());
	}
	
	public int getLineDistanceTo(Position other) {
		return Math.abs(line - other.getLine());
	}
	
	public String toString() {
		return ("" + column) + line;
	}
}

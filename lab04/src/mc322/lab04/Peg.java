package mc322.lab04;

// Representa uma peça, a falta dela ou uma posição inválida num jogo de Resta Um
public class Peg {
	private int type;  // -1 = posição inválida, 0 = posição válida sem peça, 1 = posição válida com peça
	private Position position;
	
	public Peg(int type, Position position) {
		if (type == 0 || type == 1) {
			this.type = type;
			this.position = position;
		}
		else {
			this.type = -1;
			this.position = null;
		}	
	}
	
	public int getType() {
		return type;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public String toString() {
		switch (type) {
		case 0:
			return "-";
		case 1:
			return "P";
		default:
			return " ";
		}
	}
	
	public boolean isEmptySpace() {
		return type == 0;
	}
}

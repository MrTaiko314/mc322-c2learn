package mc322.lab04;

// Representa uma pe�a, a falta dela ou uma posi��o inv�lida num jogo de Resta Um
public class Peg {
	private int type;  // -1 = posi��o inv�lida, 0 = posi��o v�lida sem pe�a, 1 = posi��o v�lida com pe�a
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

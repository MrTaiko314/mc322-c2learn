package mc322.lab04;

// Representa um tabuleiro num jogo de Resta Um
public class Board {
	private Peg pegs[][];  // Peças do tabuleiro, indexadas por linha-coluna
	
	public Board() {
		this.pegs = new Peg[7][7];
		
		// Esquema do estado inicial do tabuleiro. Espaços vazios são posições
		// inválidas para se pôr uma peça, "P" representa uma posição válida 
		// com peça e "-" representa uma posição válida sem peça.
		String[][] board = {
				{"", "", "P", "P", "P", "", ""},
				{"", "", "P", "P", "P", "", ""},
				{"P", "P", "P", "P", "P", "P", "P"},
				{"P", "P", "P", "-", "P", "P", "P"},
				{"P", "P", "P", "P", "P", "P", "P"},
				{"", "", "P", "P", "P", "", ""},
				{"", "", "P", "P", "P", "", ""},
		};
		
		// Inicializa a array de peças de acordo com as definições acima.
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				Position position = new Position((char)('a' + i), j + 1);
				switch (board[i][j]) {
				case "P":
					this.pegs[i][j] = new Peg(1, position);
					break;
				case "-":
					this.pegs[i][j] = new Peg(0, position);
					break;
				default:
					this.pegs[i][j] = new Peg(-1, position);
					break;
				}
			}
		}
	}
	
	public String getState() {
		String result = "";
		for (int i = 6; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				result += pegs[i][j].toString();
			}
			result += "\n";
		}
		return result;
	}
	
	private Peg getPegAt(Position aPosition) {
		if (!aPosition.isValid()) {
			return null;
		}
		return pegs[aPosition.getLineIndex()][aPosition.getColumnIndex()];
	}
	
	private void setPegAt(Position aPosition, Peg newPeg) {
		pegs[aPosition.getLineIndex()][aPosition.getColumnIndex()] = newPeg;
	}
	
	// Executa o comando passado, se possível
	public boolean executeCommand(Command command) {
		Position sourcePosition = command.getSourcePosition();
		Position targetPosition = command.getTargetPosition();
		
		if (!sourcePosition.isValid()) {
			System.err.println("Invalid source position: " + sourcePosition.toString());
			return false;
		}
		if (!targetPosition.isValid()) {
			System.err.println("Invalid target position: " + targetPosition.toString());
			return false;
		}
		
		Position middlePosition = null;
		if (sourcePosition.getLine() == targetPosition.getLine() &&
				sourcePosition.getColumnDistanceTo(targetPosition) == 2) {
			char middleColumn = (char)((sourcePosition.getColumn() + targetPosition.getColumn()) / 2);
			middlePosition = new Position(middleColumn, sourcePosition.getLine());
		}
		else if (sourcePosition.getColumn() == targetPosition.getColumn() &&
				sourcePosition.getLineDistanceTo(targetPosition) == 2) {
			int middleLine = (sourcePosition.getLine() + targetPosition.getLine()) / 2;
			middlePosition = new Position(sourcePosition.getColumn(), middleLine);
		}
		
		if (middlePosition == null) {
			System.err.println("Invalid source-target relative position: command=" + command.toString());
			return false;
		}
		
		Peg sourcePeg = getPegAt(sourcePosition);
		Peg targetPeg = getPegAt(targetPosition);
		Peg middlePeg = getPegAt(middlePosition);
		
		// Verifica se as peças existem
		if (sourcePeg == null || targetPeg == null || middlePeg == null) {
			return false;
		}
		// Verifica se a origem e o meio são peças e se o destino é um espaço livre
		if (sourcePeg.getType() != 1 || targetPeg.getType() != 0 || middlePeg.getType() != 1) {
			System.err.println("Invalid pegs formation");
			return false;
		}
		
		// Executa o comando
		setPegAt(targetPosition, sourcePeg);
		setPegAt(sourcePosition, new Peg(0, sourcePosition));
		setPegAt(middlePosition, new Peg(0, middlePosition));
		
		return true;
	}
	
	public void print() {
		for (int i = 6; i >= 0; i--) {
			System.out.print("" + (i + 1) + " ");
			for (int j = 0; j < 7; j++) {
				System.out.print(pegs[i][j].toString() + ((j == 6) ? "\n" : " "));
			}
		}
		System.out.println("  a b c d e f g");
	}
	
}

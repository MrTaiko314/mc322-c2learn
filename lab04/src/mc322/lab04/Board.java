package mc322.lab04;

// Representa um tabuleiro num jogo de Resta Um
public class Board {
	private Peg pegs[][];  // Pe�as do tabuleiro, indexadas por linha-coluna
	
	public Board() {
		this.pegs = new Peg[7][7];
		
		// Esquema do estado inicial do tabuleiro. Espa�os vazios s�o posi��es
		// inv�lidas para se p�r uma pe�a, "P" representa uma posi��o v�lida 
		// com pe�a e "-" representa uma posi��o v�lida sem pe�a.
		String[][] board = {
				{"", "", "P", "P", "P", "", ""},
				{"", "", "P", "P", "P", "", ""},
				{"P", "P", "P", "P", "P", "P", "P"},
				{"P", "P", "P", "-", "P", "P", "P"},
				{"P", "P", "P", "P", "P", "P", "P"},
				{"", "", "P", "P", "P", "", ""},
				{"", "", "P", "P", "P", "", ""},
		};
		
		// Inicializa a array de pe�as de acordo com as defini��es acima.
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
	
	// Executa o comando passado, se poss�vel
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
		
		// Verifica se as pe�as existem
		if (sourcePeg == null || targetPeg == null || middlePeg == null) {
			return false;
		}
		// Verifica se a origem e o meio s�o pe�as e se o destino � um espa�o livre
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

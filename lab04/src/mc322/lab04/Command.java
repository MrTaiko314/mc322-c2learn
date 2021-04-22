package mc322.lab04;

// Representa um comando de movimentação de uma peça num jogo de Resta Um
public class Command {
	private String command;
	
	// Uma string de comando deve ser na forma a1:b2
	public Command(String command) {
		this.command = command;
	}
	
	public Position getSourcePosition() {
		return new Position(command.substring(0, 2));
	}
	
	public Position getTargetPosition() {
		return new Position(command.substring(3, 5));
	}
	
	public String toString() {
		return command;
	}
}

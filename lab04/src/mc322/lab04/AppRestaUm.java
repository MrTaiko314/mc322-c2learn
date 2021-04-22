package mc322.lab04;

public class AppRestaUm {

	public static String[] executaJogo(String csvPath) {
		CSVReader csvReader = new CSVReader();
		csvReader.setDataSource(csvPath);
		String[] stringCommands = csvReader.requestCommands();
		
		Command[] commands = new Command[stringCommands.length];
		for (int i = 0; i < stringCommands.length; i++) {
			commands[i] = new Command(stringCommands[i]);
		}
		
//		System.out.println("Total de comandos: " + commands.length);
//		System.out.println();
//		
//		System.out.println("Comandos:");
//		for (int i = 0; i < commands.length; i++) {
//			System.out.println(commands[i]);
//		}
//		System.out.println();
		
		Board board = new Board();
		
		String[] result = new String[commands.length + 1];
		result[0] = board.getState();

		System.out.println("Tabuleiro inicial:");
		board.print();
		System.out.println();
		
		for (int i = 0; i < commands.length; i++) {
			System.out.println("Source: " + commands[i].getSourcePosition().toString());
			System.out.println("Target: " + commands[i].getTargetPosition().toString());
			
			if (board.executeCommand(commands[i])) {
				result[i + 1] = board.getState();
				board.print();
			}
			System.out.println();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		executaJogo("testes/teste07.csv");
	}

}

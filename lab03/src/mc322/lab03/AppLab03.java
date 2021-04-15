package mc322.lab03;

public class AppLab03 {

	public static void main(String[] args) {
		Animacao animacao = new Animacao("080403MCMVM");

		System.out.println(animacao.apresenta());
		for (int i = 0; i < animacao.acoes.length; i++) {
		    animacao.passo();
		    System.out.println(animacao.apresenta());
		}
	}

}

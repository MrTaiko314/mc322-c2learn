package mc322.lab03;

public class Animacao {
    String animacao;
    char[] acoes;
    int indiceAcaoAtual;
    AquarioLombriga aquarioLombriga;
    
    public Animacao(String animacao) {
        this.animacao = animacao;
        int tamanhoAquario = Integer.parseInt(animacao.substring(0, 2));
        int tamanhoLombriga = Integer.parseInt(animacao.substring(2, 4));
        int posicaoInicialLombriga = Integer.parseInt(animacao.substring(4, 6));
        this.aquarioLombriga = new AquarioLombriga(tamanhoAquario, tamanhoLombriga, posicaoInicialLombriga);
        this.acoes = animacao.substring(6).toCharArray();
        this.indiceAcaoAtual = 0;
    }
    
    public String apresenta() {
        return aquarioLombriga.apresenta();
    }
    
    public void passo() {
        if (indiceAcaoAtual < acoes.length) {
            switch (acoes[indiceAcaoAtual]) {
                case 'C':
                    aquarioLombriga.crescer();
                    break;
                case 'M':
                    aquarioLombriga.mover();
                    break;
                case 'V':
                    aquarioLombriga.virar();
                    break;
            }
            indiceAcaoAtual++;
        }
    }
    
}

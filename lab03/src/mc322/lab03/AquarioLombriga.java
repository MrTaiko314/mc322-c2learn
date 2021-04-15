package mc322.lab03;

public class AquarioLombriga {
    public int tamanhoAquario;
    public int tamanhoLombriga;
    public int posicaoLombriga;  // Posição 1 é a primeira posição (mais à esquerda do aquário), 1 <= posicaoLombriga <= tamanhoAquario
    public int direcaoLombriga;  // 0: para a esquerda; 1: para a direita
    
    public AquarioLombriga(int tamanhoAquario, int tamanhoLombriga, int posicaoInicialLombriga) {
        this.tamanhoAquario = (tamanhoLombriga > tamanhoAquario) ? tamanhoLombriga : tamanhoAquario;
        this.tamanhoLombriga = tamanhoLombriga;
        this.posicaoLombriga = (posicaoInicialLombriga + this.tamanhoLombriga - 1 > this.tamanhoAquario) ? 1 : posicaoInicialLombriga;
        this.direcaoLombriga = 1;
    }
    
    // Determina se a lombriga está virada para a direita
    public boolean viradaParaDireita() {
        return direcaoLombriga == 1;
    }
    
    public void crescer() {
        if (viradaParaDireita()) {
            if (posicaoLombriga == 1) {
                System.out.println("Não há espaço suficiente para a lombriga crescer!");
                return;
            }
            tamanhoLombriga++;
            posicaoLombriga--;
        }
        else {
            // A lombriga está virada para a esquerda
            if (posicaoLombriga == tamanhoAquario) {
                System.out.println("Não há espaço suficiente para a lombriga crescer!");
                return;
            }
            tamanhoLombriga++;
            posicaoLombriga++;
        }
    }
    
    public void mover() {
        if (viradaParaDireita()) {
            if (posicaoLombriga + tamanhoLombriga > tamanhoAquario) {
                // A lombriga está encostada no lado direito do aquário
                virar();
            }
            else {
                posicaoLombriga++;
            }
        }
        else {
            // A lombriga está virada para a esquerda
            if (posicaoLombriga - tamanhoLombriga < 1) {
                // A lombriga está encostada no lado esquerdo do aquário
                virar();
            }
            else {
                posicaoLombriga--;
            }
        }
    }
    
    public void virar() {
        if (viradaParaDireita()) {
            direcaoLombriga = 0;
            posicaoLombriga += tamanhoLombriga - 1;
        }
        else {
            // A lombriga está virada para a esquerda
            direcaoLombriga = 1;
            posicaoLombriga -= tamanhoLombriga - 1;
        }
    }
    
    public String apresenta() {
        String resultado = "";
        if (viradaParaDireita()) {
            int i = 1;
            for (; i < posicaoLombriga; i++) {
                resultado += "#";
            }
            for (; i < posicaoLombriga + tamanhoLombriga; i++) {
                resultado += (i == posicaoLombriga + tamanhoLombriga - 1) ? "O" : "@";
            }
            for (; i <= tamanhoAquario; i++) {
                resultado += "#";
            }
        }
        else {
            // A lombriga está virada para a esquerda
            int i = 1;
            for (; i <= posicaoLombriga - tamanhoLombriga; i++) {
                resultado += "#";
            }
            for (; i <= posicaoLombriga; i++) {
                resultado += (i == posicaoLombriga - (tamanhoLombriga - 1)) ? "O" : "@";
            }
            for (; i <= tamanhoAquario; i++) {
                resultado += "#";
            }
        }
        return resultado;
    }
}

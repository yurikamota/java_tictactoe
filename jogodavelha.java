import java.util.Scanner;

public class JogoDaVelha {
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char[][] board = new char[3][3];
    private static char currentPlayer = PLAYER_X;

    public static void main(String[] args) {
        inicializarTabuleiro();
        boolean jogoEmAndamento = true;

        while (jogoEmAndamento) {
            exibirTabuleiro();
            realizarJogada();
            if (verificarVitoria()) {
                exibirTabuleiro();
                System.out.println("Parabéns! O jogador " + currentPlayer + " venceu!");
                jogoEmAndamento = false;
            } else if (verificarEmpate()) {
                exibirTabuleiro();
                System.out.println("O jogo terminou em empate!");
                jogoEmAndamento = false;
            } else {
                alternarJogador();
            }
        }
    }

    private static void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void exibirTabuleiro() {
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println(" ---+---+---");
        }
    }

    private static void realizarJogada() {
        Scanner scanner = new Scanner(System.in);
        int linha, coluna;

        while (true) {
            System.out.println("Jogador " + currentPlayer + ", faça sua jogada!");
            System.out.print("Digite a linha (0-2): ");
            linha = scanner.nextInt();
            System.out.print("Digite a coluna (0-2): ");
            coluna = scanner.nextInt();

            if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && board[linha][coluna] == EMPTY) {
                board[linha][coluna] = currentPlayer;
                break;
            } else {
                System.out.println("Jogada inválida! Tente novamente.");
            }
        }
    }

    private static boolean verificarVitoria() {
        // Verificar linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // Verificar diagonais
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    private static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void alternarJogador() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }
}

package com.lucas.pedrapapeltesoura;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvScorePlayer, tvScoreComputer, tvComputerChoice, tvResult;
    private Button btnPedra, btnPapel, btnTesoura, btnNovaJogada, btnFinalizarJogo;

    private int scorePlayer = 0;
    private int scoreComputer = 0;
    private final String[] opcoes = {"Pedra", "Papel", "Tesoura"};
    private final String[] emojis = {"✊", "✋", "✌️"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa componentes
        tvScorePlayer = findViewById(R.id.tvScorePlayer);
        tvScoreComputer = findViewById(R.id.tvScoreComputer);
        tvComputerChoice = findViewById(R.id.tvComputerChoice);
        tvResult = findViewById(R.id.tvResult);
        btnPedra = findViewById(R.id.btnPedra);
        btnPapel = findViewById(R.id.btnPapel);
        btnTesoura = findViewById(R.id.btnTesoura);
        btnNovaJogada = findViewById(R.id.btnNovaJogada);
        btnFinalizarJogo = findViewById(R.id.btnFinalizarJogo);

        // Listeners dos botões de escolha
        btnPedra.setOnClickListener(v -> jogar(0));
        btnPapel.setOnClickListener(v -> jogar(1));
        btnTesoura.setOnClickListener(v -> jogar(2));

        // Nova jogada
        btnNovaJogada.setOnClickListener(v -> {
            tvComputerChoice.setText("Aguardando jogada...");
            tvResult.setText("");
            btnNovaJogada.setVisibility(View.GONE);
            btnFinalizarJogo.setVisibility(View.GONE);
            habilitarBotoes(true);
        });

        // Finalizar jogo
        btnFinalizarJogo.setOnClickListener(v -> {
            scorePlayer = 0;
            scoreComputer = 0;
            tvScorePlayer.setText("0");
            tvScoreComputer.setText("0");
            tvComputerChoice.setText("Aguardando jogada...");
            tvResult.setText("");
            btnNovaJogada.setVisibility(View.GONE);
            btnFinalizarJogo.setVisibility(View.GONE);
            habilitarBotoes(true);
        });
    }

    private void jogar(int escolhaJogador) {
        // Gera jogada aleatória do computador
        Random random = new Random();
        int escolhaComputador = random.nextInt(3);

        // Exibe jogada do computador
        tvComputerChoice.setText("Computador: " + emojis[escolhaComputador] + " " + opcoes[escolhaComputador]);

        // Determina resultado
        String resultado = determinarVencedor(escolhaJogador, escolhaComputador);
        tvResult.setText(resultado);

        // Atualiza cor do resultado
        if (resultado.equals("Você venceu! 🎉")) {
            tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            scorePlayer++;
            tvScorePlayer.setText(String.valueOf(scorePlayer));
        } else if (resultado.equals("Você perdeu! 😢")) {
            tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            scoreComputer++;
            tvScoreComputer.setText(String.valueOf(scoreComputer));
        } else {
            tvResult.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }

        // Desabilita botões e mostra opções
        habilitarBotoes(false);
        btnNovaJogada.setVisibility(View.VISIBLE);
        btnFinalizarJogo.setVisibility(View.VISIBLE);
    }

    private String determinarVencedor(int jogador, int computador) {
        if (jogador == computador) {
            return "Empate! 🤝";
        }
        // Pedra(0) vence Tesoura(2), Papel(1) vence Pedra(0), Tesoura(2) vence Papel(1)
        if ((jogador == 0 && computador == 2) ||
                (jogador == 1 && computador == 0) ||
                (jogador == 2 && computador == 1)) {
            return "Você venceu! 🎉";
        }
        return "Você perdeu! 😢";
    }

    private void habilitarBotoes(boolean habilitar) {
        btnPedra.setEnabled(habilitar);
        btnPapel.setEnabled(habilitar);
        btnTesoura.setEnabled(habilitar);
    }
}
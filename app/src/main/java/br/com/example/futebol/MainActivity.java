package br.com.example.futebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.example.futebol.players.PlayersActivity;
import br.com.example.futebol.presence.PresencaActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnPlayers, btnPresenca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlayers = findViewById(R.id.btnPlayers);
        btnPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlayersActivity.class));
            }
        });

        btnPresenca = findViewById(R.id.btnPresenca);
        btnPresenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PresencaActivity.class));
            }
        });
    }





}

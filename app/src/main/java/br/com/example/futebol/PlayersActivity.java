package br.com.example.futebol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayersActivity extends AppCompatActivity {

    TextView noResult;
    RecyclerView rcvPlayers;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        noResult = findViewById(R.id.noResult);
        rcvPlayers = findViewById(R.id.rcvPlayers);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayersActivity.this, AddPlayersActivity.class));
            }
        });
    }

}

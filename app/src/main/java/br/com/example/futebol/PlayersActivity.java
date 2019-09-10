package br.com.example.futebol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.example.database.PlayersDB;
import br.com.example.database.domain.Player;

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

    @Override
    protected void onResume() {
        super.onResume();

        listPlayers();
    }

    private void listPlayers(){
        PlayersDB db = new PlayersDB(getApplicationContext());
        List<Player> list = db.buscarPlayers();
        if (!list.isEmpty()){
            noResult.setVisibility(View.GONE);
            rcvPlayers.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager  = new LinearLayoutManager(this);
            rcvPlayers.setLayoutManager(layoutManager);

            PlayerAdapter mAdapter = new PlayerAdapter(list, getApplicationContext());
            rcvPlayers.setAdapter(mAdapter);
        }else{
            noResult.setVisibility(View.VISIBLE);
            rcvPlayers.setVisibility(View.GONE);
        }
    }
}

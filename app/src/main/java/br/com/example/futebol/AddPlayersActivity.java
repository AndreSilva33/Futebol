package br.com.example.futebol;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import br.com.example.database.PlayersDB;
import br.com.example.database.domain.Player;

public class AddPlayersActivity extends AppCompatActivity {

    EditText edtName;
    RadioGroup rdgPosicao;
    RadioButton rbGoleiro, rblinha;
    Switch swStatus;
    Button btnCancel, btnSave;
    String posSelect;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_players_add);

        edtName = findViewById(R.id.edtNome);
        rdgPosicao = findViewById(R.id.rdgPosicao);
        rbGoleiro = findViewById(R.id.rbGoleiro);
        rblinha = findViewById(R.id.rbLinha);
        swStatus = findViewById(R.id.swStatus);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()){
                    Player player = new Player();
                    player.setNome(edtName.getText().toString());
                    player.setPosicao(posSelect);
                    player.setStatus(swStatus.isChecked());

                    PlayersDB db = new PlayersDB(getApplicationContext());

                    if (db.insertPlayer(player) > 0){
                        Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }

    private boolean validateFields(){
        if (edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Digite um nome", Toast.LENGTH_SHORT).show();
            return false;
        }

        posSelect = null;
        int id = rdgPosicao.getCheckedRadioButtonId();
        if (id == rbGoleiro.getId()) {
            posSelect = "G";
        }else if (id == rblinha.getId()){
            posSelect = "L";
        }

        if(posSelect == null){
            Toast.makeText(this, "Escolha uma posição", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;



    }
}

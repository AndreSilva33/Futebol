package br.com.example.futebol.players;

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
import br.com.example.futebol.R;

public class AddPlayersActivity extends AppCompatActivity {

    EditText edtName;
    RadioGroup rdgPosicao;
    RadioButton rbGoleiro, rblinha;
    Switch swStatus;
    Button btnCancel, btnSave, btnDelete;
    String posSelect;
    Player playerSelected;
    boolean deletePlayer;




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
        btnDelete = findViewById(R.id.btnDelete);


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
                    PlayersDB db = new PlayersDB(getApplicationContext());

                    //VERIFICA SE VEIO ALGUM JOGADOR COMO PARAMETRO
                    if (playerSelected != null){
                        playerSelected.setNome(edtName.getText().toString());
                        playerSelected.setPosicao(posSelect);
                        playerSelected.setStatus(swStatus.isChecked());
                        if (db.updatePlayer(playerSelected) > 0){
                            Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                      //VERIFICA SE VEIO ALGUM JOGADOR COMO PARAMETRO
                      //ADD
                    }else{
                        Player player = new Player();
                        player.setNome(edtName.getText().toString());
                        player.setPosicao(posSelect);
                        player.setStatus(swStatus.isChecked());



                        if (db.insertPlayer(player) > 0){
                            Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                    //ADD

                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayersDB db = new PlayersDB(getApplicationContext());
                 if (db.deletePlayer(playerSelected.getId())>0){
                     Toast.makeText(getApplicationContext(), "Excluido com sucesso", Toast.LENGTH_SHORT).show();
                     finish();
                 }
            }
        });

        //RECEBENDO UM JOGADOR COMO PARAMETRO
        playerSelected = (Player) getIntent().getSerializableExtra("player");

        deletePlayer = getIntent().getBooleanExtra("delete", false);

        if(deletePlayer){
            btnCancel.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);

            btnDelete.setVisibility(View.VISIBLE);
        }else{
            btnCancel.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);

            btnDelete.setVisibility(View.GONE);
        }

        // AQUI VC RECEBE A VARIAVEL Q VAI SER RESPONSAVEL POR TE DIZER SE TA EXCLUINDO
        // PODE SER UMA VARIAVEL BOOLEAN,
        // verifica se a variavel é true, se for, esconde o botão de cadastrar
        // e mostra o botão de excluir
        // fazer no layout (activity_players_add) um novo botão para excluir
        //o botão começa gone, se a variavel vier true, vc mostra ele

        //SE VIER UM JOGADOR COMO PARAMETRO PREENCHE OS DADOS
        if (playerSelected != null){
            addFields();
        }

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
    // FUNÇÃO QUE PEGA OS DADOS
   private void addFields(){
        edtName.setText(playerSelected.getNome());
        if (playerSelected.getPosicao().equals("G")){
            rbGoleiro.setChecked(true);
            rblinha.setChecked(false);
        }else{
            rbGoleiro.setChecked(false);
            rblinha.setChecked(true);
        }
        swStatus.setChecked(playerSelected.isStatus());
    }
    // FUNÇÃO QUE PEGA OS DADOS
}

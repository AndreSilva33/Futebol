package br.com.example.futebol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.example.database.domain.Player;

public class PlayerAdapter extends RecyclerView.Adapter <PlayersViewHolder> {
    List<Player> players;
    Activity mActivity;

    public PlayerAdapter(List<Player> listPlayers, Activity activity){

        this.players = listPlayers;
        this.mActivity = activity;
    }

    @Override
    public PlayersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_players_item, parent, false);
        return new PlayersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayersViewHolder holder, final int position) {
        holder.nomeLista.setText(players.get(position).getNome());
        holder.posicaoLista.setText(players.get(position).getPosicao());
        holder.ativoLista.setClickable(false);
        if (players.get(position).isStatus()){
            holder.ativoLista.setChecked(true);
        }
        holder.cdMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // AQUI VC TEM CHAMAR A NOVA ACTIVITY PASSANDO O JOGADOR(CLASS PLAYER) COMO PARAMETRO
                // PARA ISSO VC TEM Q SERIALIZAR A CLASSE PLAYER
                Intent it = new Intent(mActivity, AddPlayersActivity.class);
                Bundle dados = new Bundle();
                dados.putSerializable("player",players.get(position));
                it.putExtras(dados);

               mActivity.startActivity(it);
            }
        });

        holder.cdMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                // FAZER A MESMA COISA QUE O CLICKLISTENER
                // SÓ Q EM DADOS TEM Q PASSAR MAIS UMA VARIALVEL PARA DIZER Q ESTÁ QUERNEDO EXCLUIR

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}

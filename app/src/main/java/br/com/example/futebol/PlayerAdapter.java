package br.com.example.futebol;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.example.database.domain.Player;

public class PlayerAdapter extends RecyclerView.Adapter <PlayersViewHolder> {
    List<Player> players;
    Context mContext;

    public PlayerAdapter(List<Player> listPlayers, Context context){

        this.players = listPlayers;
        this.mContext = context;
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

                Toast.makeText(mContext, "Clickou item posicao " + players.get(position).getNome(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}

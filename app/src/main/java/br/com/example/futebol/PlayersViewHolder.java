package br.com.example.futebol;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class PlayersViewHolder  extends RecyclerView.ViewHolder {

    TextView nomeLista, posicaoLista;
    Switch ativoLista;
    CardView cdMain;

    public PlayersViewHolder(View itemView) {
        super(itemView);
        cdMain = itemView.findViewById(R.id.cdMain);
        nomeLista = itemView.findViewById(R.id.txtNomeLista);
        posicaoLista = itemView.findViewById(R.id.txtPosicaoLista);
        ativoLista = itemView.findViewById(R.id.swAtivoLista);
    }
}

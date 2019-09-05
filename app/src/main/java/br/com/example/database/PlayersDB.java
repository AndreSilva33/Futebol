package br.com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.example.database.domain.Player;

public class PlayersDB extends SQLiteOpenHelper {

    //NOME E VERSÃO DO BANCO DE DADOS
    private static final String NOME_BANCO = "players.sqlite";
    private static final int VERSAO_BANCO = 1;
    //NOME E VERSÃO DO BANCO DE DADOS


    //NOME DAS TABELAS
    private static final String TABELA_PLAYERS = "players";
    //NOME DAS TABELAS

    //CAMPO DA TABELA PLAYERS
    private static final String PLAYERS_ID = "id";
    private static final String PLAYERS_NOME = "nome";
    private static final String PLAYERS_POSICAO = "posicao";
    private static final String PLAYERS_STATUS = "status";
    //CAMPO DA TABELA PLAYERS

    //CRIAÇÃO DA TABELA PLAYERS
    private static final String CREATE_TABLE_PLAYERS = "CREATE TABLE IF NOT EXISTS "
            + TABELA_PLAYERS + "("
            + PLAYERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PLAYERS_NOME + " VARCHAR(50), "
            + PLAYERS_POSICAO + " CHAR(1), "
            + PLAYERS_STATUS + " CHAR(1))";
    //CRIAÇÃO DA TABELA PLAYERS

    //
    public PlayersDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLAYERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //PLAYERS
    // INSERIR JOGADOR NA TABELA
    public long insertPlayer(Player player){
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(PLAYERS_NOME, player.getNome());
            values.put(PLAYERS_POSICAO, player.getPosicao());
            values.put(PLAYERS_STATUS, player.isStatus() ? "S" : "N");
            return db.insert(TABELA_PLAYERS, null, values);
        }finally {
            db.close();
        }
    }
    // INSERIR JOGADOR NA TABELA
    //PLAYERS
}

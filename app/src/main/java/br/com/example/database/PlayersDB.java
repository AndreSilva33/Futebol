package br.com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.example.database.domain.Player;
import br.com.example.database.domain.Presence;

public class PlayersDB extends SQLiteOpenHelper {

    //NOME E VERSÃO DO BANCO DE DADOS
    private static final String NOME_BANCO = "players.sqlite";
    private static final int VERSAO_BANCO = 2;
    //NOME E VERSÃO DO BANCO DE DADOS


    //NOME DAS TABELAS
    private static final String TABELA_PLAYERS = "players";
    private static final String TABELA_PRESENCA = "presenca";
    //NOME DAS TABELAS

    //CAMPO DA TABELA PLAYERS
    private static final String PLAYERS_ID = "id";
    private static final String PLAYERS_NOME = "nome";
    private static final String PLAYERS_POSICAO = "posicao";
    private static final String PLAYERS_STATUS = "status";
    //CAMPO DA TABELA PLAYERS

    //CAMPO DA TABELA PRESENCA
    private static final String PRESENCA_ID = "id";
    private static final String PRESENCA_ID_PLAYER = "id_player";
    private static final String PRESENCA_DATA = "data";
    //CAMPO DA TABELA PRESENCA

    //CRIAÇÃO DA TABELA PLAYERS
    private static final String CREATE_TABLE_PLAYERS = "CREATE TABLE IF NOT EXISTS "
            + TABELA_PLAYERS + "("
            + PLAYERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PLAYERS_NOME + " VARCHAR(50), "
            + PLAYERS_POSICAO + " CHAR(1), "
            + PLAYERS_STATUS + " CHAR(1))";
    //CRIAÇÃO DA TABELA PLAYERS

    //CRIAÇÃO DA TABELA PRESENCA
    private static final String CREATE_TABLE_PRESENCA = "CREATE TABLE IF NOT EXISTS "
            + TABELA_PRESENCA + "("
            + PRESENCA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRESENCA_ID_PLAYER + " INTEGER, "
            + PRESENCA_DATA + " DATE)";
    //CRIAÇÃO DA TABELA PRESENCA

    private static final String DROP_TABLE_PLAYERS = "DROP TABLE IF EXISTS " +TABELA_PLAYERS;
    private static final String DROP_TABLE_PRESENCA = "DROP TABLE IF EXISTS " +TABELA_PRESENCA;

    //
    public PlayersDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    //CRIAR A TABELA
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLAYERS);
        db.execSQL(CREATE_TABLE_PRESENCA);
    }
    //CRIAR A TABELA

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_PLAYERS);
        db.execSQL(DROP_TABLE_PRESENCA);

        onCreate(db);
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

    // ATUALIZANDO JOGADOR
    public long updatePlayer(Player player){
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(PLAYERS_NOME, player.getNome());
            values.put(PLAYERS_POSICAO, player.getPosicao());
            values.put(PLAYERS_STATUS, player.isStatus() ? "S" : "N");

            String where = PLAYERS_ID +"=?";
            String[] whereArgs = new String[]{String.valueOf(player.getId())};

            return db.update(TABELA_PLAYERS, values, where, whereArgs);
        }finally {
            db.close();
        }
    }


    // ATUALIZANDO JOGADOR

    //BUSCAR JOGADORES
    public List<Player> buscarPlayers(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.query(TABELA_PLAYERS,new String[]{PLAYERS_ID,PLAYERS_NOME, PLAYERS_POSICAO, PLAYERS_STATUS},
                    null,null,null, null, PLAYERS_NOME + " ASC");

            List<Player> players = new ArrayList<>();

            if (c.moveToFirst()){
                do{
                    Player p = new Player();
                    players.add(p);

                    p.setId(c.getInt(c.getColumnIndex(PLAYERS_ID)));
                    p.setNome(c.getString(c.getColumnIndex(PLAYERS_NOME)));
                    p.setPosicao(c.getString(c.getColumnIndex(PLAYERS_POSICAO)));
                    p.setStatus(c.getString(c.getColumnIndex(PLAYERS_STATUS)).equals("S"));

                }while(c.moveToNext());

            }
            c.close();
            return players;
        }finally {
            db.close();
        }
    }
    //BUSCAR JOGADORES

    // EXCLUIR UM JOGADOR
    public long deletePlayer(int id){
        SQLiteDatabase db = getWritableDatabase();
        try{

            String where = PLAYERS_ID +"=?";
            String[] whereArgs = new String[]{String.valueOf(id)};

            return db.delete(TABELA_PLAYERS, where, whereArgs);
        }finally {
            db.close();
        }
    }
    // EXCLUIR UM JOGADOR
    //PLAYERS

    //PRESENCA
    public List<Presence> buscarPresenca(String data){
        SQLiteDatabase db = getWritableDatabase();
        try{
            String where = PRESENCA_DATA + "=?";
            String[] whereArgs = new String[]{data};

            Cursor c = db.query(TABELA_PRESENCA,new String[]{PRESENCA_ID, PRESENCA_ID_PLAYER, PRESENCA_DATA},
                    where,whereArgs,null, null, PRESENCA_DATA + " ASC");

            List<Presence> presences = new ArrayList<>();

            if (c.moveToFirst()){
                do{
                    Presence p = new Presence();
                    presences.add(p);

                    p.setId(c.getInt(c.getColumnIndex(PRESENCA_ID)));
                    p.setId_player(c.getInt(c.getColumnIndex(PRESENCA_ID_PLAYER)));
                    p.setData(c.getString(c.getColumnIndex(PRESENCA_DATA)));


                }while(c.moveToNext());

            }
            c.close();
            return presences;
        }finally {
            db.close();
        }
    }


    //PRESENCA

}

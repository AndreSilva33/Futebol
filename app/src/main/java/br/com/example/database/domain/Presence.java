package br.com.example.database.domain;

import java.io.Serializable;
import java.util.Date;

public class Presence implements Serializable {
    int id, id_player;
    String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

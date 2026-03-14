package com.insper.mini_spotify_api;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Usuario {

    private UUID id;
    private String nome;
    private String email;
    private TipoPlano tipoPlano;
    private boolean ativo;
    private LocalDateTime dataCriacao;
    private Estatisticas estatisticas;
    private Collection<Playlist> playlists;


    public Usuario() {

    }

    public Usuario(String nome, String email, TipoPlano tipoPlano) {

        this.nome = nome;
        this.email = email;
        this.tipoPlano = tipoPlano;
        this.ativo = true;

    }

    public enum TipoPlano {
        FREE,
        PREMIUM
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoPlano getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(TipoPlano tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Estatisticas getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(Estatisticas estatisticas) {
        this.estatisticas = estatisticas;
    }

    public Collection<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlists) {
        this.playlists = Collections.singletonList(playlists);
    }
}

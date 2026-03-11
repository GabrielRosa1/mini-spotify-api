package com.insper.mini_spotify_api;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public class Playlist {

    private Long id;
    private String nome;
    private boolean publica;
    private LocalDateTime dataCriacao;
    private Usuario usuario;
    private List<Musica> musicas;

}

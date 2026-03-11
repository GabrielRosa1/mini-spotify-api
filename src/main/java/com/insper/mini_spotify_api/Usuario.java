package com.insper.mini_spotify_api;

import java.time.LocalDateTime;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private TipoPlano tipoPlano;
    private boolean ativo;
    private LocalDateTime dataCriacao;
    private Estatisticas estatisticas;

    public enum TipoPlano {
        FREE,
        PREMIUM
    }

}

package com.insper.mini_spotify_api;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PlaylistService {

    private final UsuarioService usuarioService;
    private HashMap<UUID, Playlist> playlists = new HashMap<>();

    public PlaylistService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    //POST /playlists
    public Playlist criarPlaylist(Playlist playlist) {

        if (playlist == null) {
            throw new RuntimeException("Body inválido");
        }

        if (playlist.getNome() == null || playlist.getNome().isBlank()) {
            throw new RuntimeException("Nome da playlist é obrigatório");
        }

        if (playlist.getUsuario() == null) {
            throw new RuntimeException("Usuário é obrigatório");
        }

        if (!usuarioService.verifyUUID(playlist.getUsuario().getId())) {
            throw new RuntimeException("Id de um usuário válido é obrigatório");
        }

        if (!playlist.getUsuario().isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        if (playlist.getPublica() == null) {
            throw new RuntimeException("Campo 'publica' é obrigatório");
        }

        for (Playlist p : playlists.values()) {
            if (p.getNome().equalsIgnoreCase(playlist.getNome())) {
                throw new RuntimeException("Já existe uma playlist com esse nome");
            }
        }

        if (playlist.getMusicas() == null) {
            playlist.setMusicas(new ArrayList<>());
        }

        for (Musica musica : playlist.getMusicas()) {
            if (musica == null) {
                throw new RuntimeException("A playlist contém música nula");
            }
        }

        playlist.setId(UUID.randomUUID());
        playlist.setDataCriacao(LocalDateTime.now());

        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

}

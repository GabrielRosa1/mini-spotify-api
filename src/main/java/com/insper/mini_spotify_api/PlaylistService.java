package com.insper.mini_spotify_api;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;


@Service
public class PlaylistService {

    private HashMap<UUID, Playlist> playlists = new HashMap<>();


    //POST /playlists
    public Playlist criarPlaylist(Playlist playlist) {

        for (Playlist p : playlists.values()) {
            if (p.getNome().equals(playlist.getNome())) {
                throw new RuntimeException("Já existe uma playlist com esse nome");
            }
        }

        if (playlist.getNome() == null || playlist.getNome().isBlank() || !playlist.getUsuario().isAtivo()
                || playlist.getUsuario().getId() == null || playlist.getPublica() == null) {
            throw new RuntimeException("Dados inválidos");
        }

        playlist.setId(UUID.randomUUID());
        playlist.setDataCriacao(LocalDateTime.now());

        playlists.put(playlist.getId(), playlist);
        return playlist;

    }

}

package com.insper.mini_spotify_api;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PlaylistService {

    private final UsuarioService usuarioService;
    private final MusicaService musicaService;
    private HashMap<UUID, Playlist> playlists = new HashMap<>();

    public PlaylistService(UsuarioService usuarioService, MusicaService musicaService) {
        this.usuarioService = usuarioService;
        this.musicaService = musicaService;
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

        if (!usuarioService.getUsuario(playlist.getUsuario().getId()).isAtivo()) {
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
        playlist.setAtivo(true);

        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

    //GET /playlists
    public Collection<Playlist> getPlaylists() {

        Collection<Playlist> totalPlaylists = new ArrayList<>();

        for (Playlist p : playlists.values()) {
            if (p.getNome() != null && p.isAtivo()) {
                totalPlaylists.add(p);
            }
        }

        return totalPlaylists;
    }

    //GET /playlists/{id}
    public Playlist getPlaylist(UUID id) {

        Playlist playlist = playlists.get(id);

        if (playlist == null || !playlist.getPublica() || !playlist.isAtivo()) {
            throw new RuntimeException("Essa playlist não existe ou está privada");
        }

        return playlist;

    }

    //PUT /playlists/{id}
    public Playlist editPlaylist(UUID id, Playlist dadosAtualizados) {

        Playlist playlist = playlists.get(id);

        if (playlist == null || !playlist.isAtivo()) {
            throw new RuntimeException("Essa playlist não existe ou não pode ser modificada");
        }

        if (dadosAtualizados.getNome() == null || dadosAtualizados.getNome().isBlank()) {
            throw new RuntimeException("Dados inválidos");
        }

        playlist.setNome(dadosAtualizados.getNome());
        playlist.setUsuario(dadosAtualizados.getUsuario());
        playlist.setMusicas(dadosAtualizados.getMusicas());

        return playlist;

    }

    //DELETE /playlists/{id}
    public void deletePlaylist(UUID id) {
        Playlist playlist = playlists.get(id);

        if (playlist == null || !playlist.isAtivo()) {
            throw new RuntimeException("Essa playlist não existe ou não pode ser modificada");
        }

        playlist.setAtivo(false);
    }

    //PUT /playlists/{id}
    public Playlist reactivatePlaylist(UUID id) {
        Playlist playlist = playlists.get(id);

        if (playlist == null) {
            throw new RuntimeException("Essa playlist não existe ou não pode ser modificada");
        }

        if (playlist.isAtivo()) {
            throw new RuntimeException("Essa playlist já está ativa");
        }

        playlist.setAtivo(true);
        return playlist;
    }

    //POST /playlists/{id}/musica/{id}
    public Playlist adicionarMusica(UUID pId, UUID mId) {
        Playlist playlist = playlists.get(pId);
        Musica musica = musicaService.getMusica(mId);
        List<Musica> musicas = playlist.getMusicas();
        musicas.add(musica);
        playlist.setMusicas(musicas);
        return playlist;
    }

}

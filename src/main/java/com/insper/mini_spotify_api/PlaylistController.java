package com.insper.mini_spotify_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;


@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/playlists")
    public ResponseEntity<Object> criarPlaylist(@RequestBody Playlist playlist) {
        try {
            Playlist p = playlistService.criarPlaylist(playlist);
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/playlists")
    public ResponseEntity<Collection<Playlist>> getPlaylists() {

        Collection<Playlist> totalPlaylists = playlistService.getPlaylists();
        return ResponseEntity.ok(totalPlaylists);

    }

    @GetMapping("/playlists/{id}")
    public ResponseEntity<Object> getPlaylist(@PathVariable UUID id) {
        try {
            Playlist playlist = playlistService.getPlaylist(id);
            return ResponseEntity.ok(playlist);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/playlists/{id}")
    public ResponseEntity<Object> editPlaylist(@PathVariable UUID id, @RequestBody Playlist dadosAtualizados) {
        try {
            Playlist playlist = playlistService.editPlaylist(id, dadosAtualizados);
            return ResponseEntity.ok(playlist);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<Object> deletePlaylist(@PathVariable UUID id) {
        try {
            playlistService.deletePlaylist(id);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/playlists/reativar/{id}")
    public ResponseEntity<Object> reactivatePlaylist(@PathVariable UUID id) {
        try {
            Playlist playlist = playlistService.reactivatePlaylist(id);
            return ResponseEntity.ok(playlist);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/playlists/{id}/musica/{id}")
    public ResponseEntity<Object> adicionarMusica(@PathVariable UUID pID, @PathVariable UUID mID) {
        try {
            Playlist playlist = playlistService.adicionarMusica(pID, mID);
            return ResponseEntity.ok(playlist);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

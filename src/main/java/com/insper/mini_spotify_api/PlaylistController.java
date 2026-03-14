package com.insper.mini_spotify_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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

}

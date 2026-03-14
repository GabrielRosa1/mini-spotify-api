package com.insper.mini_spotify_api;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;


@Service
public class AlbumService {

    private final HashMap<UUID, Album> albuns = new HashMap<>();

    public HashMap<UUID, Album> getAlbuns() {
        return albuns;
    }

    public boolean verifyUUID(UUID id) {
        for (Album a : albuns.values()) {
            if (a.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}

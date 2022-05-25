package com.kreitek.kreitekfy.album.infraestructure.rest;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.application.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class AlbumRestController {

    private final AlbumService albumService;

    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "/albums/{albumsId}")
    ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long albumId) {
        Optional<AlbumDTO> album = this.albumService.getAlbumById(albumId);
        if(album.isPresent()) {
            return new ResponseEntity<>(album.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/albums", produces = "application/json")
    ResponseEntity<AlbumDTO> insertAlbum(@RequestBody AlbumDTO albumDTO) {
        AlbumDTO albumSaved = this.albumService.saveAlbum(albumDTO);
        return new ResponseEntity<>(albumSaved, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "albums/{idAlbum}")
    ResponseEntity<?> deleteAlbumById(@PathVariable Long albumId) {
        this.albumService.deleteAlbum(albumId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.kreitek.kreitekfy.album.infraestructure.rest;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.application.dto.AlbumSimpleDTO;
import com.kreitek.kreitekfy.album.application.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/albums")
public class AlbumRestController {

    private final AlbumService albumService;

    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<AlbumSimpleDTO>> getAlbums(@RequestParam(name = "partialName", required = false) String partialName) {
        List<AlbumSimpleDTO> albums;

        if(partialName == null) {
            albums = this.albumService.getAlbums();
        } else {
            albums = this.albumService.getAlbumByName(partialName);
        }

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AlbumDTO>> getAlbumsByCriteriaPaged(@RequestParam(value = "filter", required = false) String filter, Pageable pageable) {

        Page<AlbumDTO> items = this.albumService.getAlbumsByCriteriaPaged(pageable, filter);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @GetMapping(value = "/{albumId}")
    ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long albumId) {
        Optional<AlbumDTO> album = this.albumService.getAlbumById(albumId);
        if(album.isPresent()) {
            return new ResponseEntity<>(album.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = "application/json")
    ResponseEntity<AlbumDTO> insertAlbum(@RequestBody AlbumDTO albumDTO) {
        AlbumDTO albumSaved = this.albumService.saveAlbum(albumDTO);
        return new ResponseEntity<>(albumSaved, HttpStatus.CREATED);
    }

    @PatchMapping(produces = "application/json")
    ResponseEntity<AlbumDTO> updateAlbum(@RequestBody AlbumDTO albumDTO) {
        AlbumDTO albumSaved = this.albumService.saveAlbum(albumDTO);
        return new ResponseEntity<>(albumSaved, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{albumId}")
    ResponseEntity<?> deleteAlbumById(@PathVariable Long albumId) {
        this.albumService.deleteAlbum(albumId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

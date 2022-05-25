package com.kreitek.kreitekfy.song.infrastructure.rest;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongRestController {

    private final SongService service;

    @Autowired
    public SongRestController(SongService service) {
        this.service = service;
    }

    @GetMapping(value = "/songs", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<SongDTO> songDTOS = this.service.getAllSongs();
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/songs/{idSong}", produces = "application/json")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long idSong) {
        return this.service
                .getSongById(idSong)
                .map(songDTO -> new ResponseEntity<>(songDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping(value = "/songs", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SongDTO> saveSong(@RequestBody SongDTO songDTO) {
        songDTO = this.service.saveSong(songDTO);
        return new ResponseEntity<>(songDTO,HttpStatus.CREATED);
    }
}

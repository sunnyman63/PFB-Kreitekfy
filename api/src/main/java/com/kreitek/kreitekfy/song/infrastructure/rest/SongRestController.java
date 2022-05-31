package com.kreitek.kreitekfy.song.infrastructure.rest;


import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class SongRestController  {

    private final SongService service;

    @Autowired
    public SongRestController(SongService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping(value = "/songs", produces = "application/json")
    ResponseEntity<Page<SongDTO>> getSongByCriteriaPaged(@RequestParam(value = "filter", required = false) String filter, Pageable pageable){

        Page<SongDTO> items = this.service.getSongByCriteriaPaged(pageable, filter);
        return new ResponseEntity<Page<SongDTO>>(items, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/songs/{idSong}", produces = "application/json")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long idSong) {
        return this.service
                .getSongById(idSong)
                .map(songDTO -> new ResponseEntity<>(songDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @CrossOrigin
    @GetMapping(value = "/songs/newests", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getNewestSongs(@PathVariable Date inclusionDate) {
        List<SongDTO> songDTOS = this.service.getNewestSongs(inclusionDate);
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(value = "/songs", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SongDTO> saveSong(@RequestBody SongDTO songDTO) {
        songDTO = this.service.saveSong(songDTO);
        return new ResponseEntity<>(songDTO,HttpStatus.CREATED);
    }

    @PatchMapping(value = "/songs", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SongDTO> updateSong(@RequestBody SongDTO songDTO) {
        songDTO = this.service.saveSong(songDTO);
        return new ResponseEntity<>(songDTO,HttpStatus.OK);
    }

}

package com.kreitek.kreitekfy.song.infrastructure.rest;


import com.kreitek.kreitekfy.shared.infrastructure.specs.EntitySpecification;
import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.services.SongService;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongRestController  {

    private final SongService service;

    @Autowired
    public SongRestController(SongService service) {
        this.service = service;
    }


    @GetMapping(value = "/songs", produces = "application/json")
    ResponseEntity<Page<SongDTO>> getSongByCriteriaPaged(@RequestParam(value = "filter", required = false) String filter, Pageable pageable){

        Page<SongDTO> items = this.service.getSongByCriteriaPaged(pageable, filter);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @GetMapping(value = "/songs/{idSong}", produces = "application/json")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long idSong) {
        return this.service
                .getSongById(idSong)
                .map(songDTO -> new ResponseEntity<>(songDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping(value = "/songs/newests", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getNewestSongs() {
        List<SongDTO> songDTOS = this.service.getNewestSongs();
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/songs", produces = "application/json", consumes = "application/json")
    public ResponseEntity<SongDTO> saveSong(@RequestBody SongDTO songDTO) {
        songDTO = this.service.saveSong(songDTO);
        return new ResponseEntity<>(songDTO,HttpStatus.CREATED);
    }
}

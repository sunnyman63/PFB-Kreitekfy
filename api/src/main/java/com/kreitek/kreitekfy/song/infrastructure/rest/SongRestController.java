package com.kreitek.kreitekfy.song.infrastructure.rest;


import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;
import com.kreitek.kreitekfy.song.application.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/songs")
public class SongRestController  {

    private final SongService service;

    @Autowired
    public SongRestController(SongService service) {
        this.service = service;
    }


    @GetMapping(produces = "application/json")
    ResponseEntity<Page<SongDTO>> getSongByCriteriaPaged(@RequestParam(value = "filter", required = false) String filter, Pageable pageable){

        Page<SongDTO> items = this.service.getSongByCriteriaPaged(pageable, filter);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @GetMapping(value = "/{idSong}", produces = "application/json")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long idSong) {
        return this.service
                .getSongById(idSong)
                .map(songDTO -> new ResponseEntity<>(songDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping(value = "/newests", produces = "application/json")
    public ResponseEntity<List<SongSimpleDTO>> getNewestSongs() {
        List<SongSimpleDTO> songDTOS = this.service.getAllSongsByOrderByInclusionDateDesc();
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/top-rated", produces = "application/json")
    public ResponseEntity<List<SongSimpleDTO>> getTopRatedSongs(){
        List<SongSimpleDTO> songSimpleDTOS = this.service.findByOrderByTotalRateDesc();
        return new ResponseEntity<>(songSimpleDTOS, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<SongDTO> saveSong(@RequestBody SongDTO songDTO) {
        songDTO = this.service.saveSong(songDTO);
        return new ResponseEntity<>(songDTO,HttpStatus.CREATED);
    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<SongDTO> updateSong(@RequestBody SongDTO songDTO) {
        songDTO = this.service.saveSong(songDTO);
        return new ResponseEntity<>(songDTO,HttpStatus.OK);
    }

}

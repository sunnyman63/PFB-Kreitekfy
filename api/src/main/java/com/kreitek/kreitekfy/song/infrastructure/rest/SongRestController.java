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
import org.springframework.web.client.ResponseErrorHandler;

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

    @GetMapping(value = "/search",produces = "application/json")
    ResponseEntity<List<SongSimpleDTO>> getSongs(@RequestParam(name = "partialName", required = false) String partialName){

        List<SongSimpleDTO> styles;

        if(partialName == null) {
            styles = this.service.getSongs();
        }else{
            styles = this.service.getSongsByName(partialName);
        }

        return new ResponseEntity<>(styles, HttpStatus.OK);
    }

    @GetMapping(value = "/{idSong}", produces = "application/json")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long idSong) {
        return this.service
                .getSongById(idSong)
                .map(songDTO -> new ResponseEntity<>(songDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping(value = "/newests", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getNewestSongs() {
        List<SongDTO> songDTOS = this.service.getAllSongsByOrderByInclusionDateDesc();
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/top-rated", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getTopRatedSongs(){
        List<SongDTO> songDTOS = this.service.findByOrderByTotalRateDesc();
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/top-view", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getTopViewedSongs(){
        List<SongDTO> songDTOS = this.service.findByOrderByTotalViewsDesc();
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/foru/{idUser}", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getForUSongs(@PathVariable Long idUser){
        List<SongDTO> songDTOS = this.service.findByUserPreferences(idUser);

        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
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

    @DeleteMapping(value = "/{idSong}")
    public  ResponseEntity<?> deleteSong(@PathVariable Long idSong){
        this.service.deleteSong(idSong);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

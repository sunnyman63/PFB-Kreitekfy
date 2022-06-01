package com.kreitek.kreitekfy.song.infrastructure.rest;


import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;
import com.kreitek.kreitekfy.song.application.services.SongService;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;

import java.util.ArrayList;
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
    public ResponseEntity<List<SongDTO>> getNewestSongs(@RequestParam(value = "styleId", required = false) Long styleId) {
        List<SongDTO> songDTOS = new ArrayList<SongDTO>();
        if(styleId!=null){
            songDTOS = this.service.getAllSongsByOrderByInclusionDateDesc(styleId);
        }else{
            songDTOS = this.service.getAllSongsByOrderByInclusionDateDesc(0L);
        }

        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/top-rated", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getTopRatedSongs(@RequestParam(value = "styleId", required = false) Long styleId){
        List<SongDTO> songDTOS = new ArrayList<SongDTO>();
        if(styleId!=null){
            songDTOS = this.service.findByOrderByTotalRateDesc(styleId);
        }else{
            songDTOS = this.service.findByOrderByTotalRateDesc(0L);
        }
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/top-view", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getTopViewedSongs(@RequestParam(value = "styleId", required = false) Long styleId){
        List<SongDTO> songDTOS = new ArrayList<SongDTO>();
        if(styleId!=null){
            songDTOS = this.service.findByOrderByTotalViewsDesc(styleId);
        }else{
            songDTOS = this.service.findByOrderByTotalViewsDesc(0L);
        }

        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/foru/{idUser}", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getForUSongs(@PathVariable Long idUser, @RequestParam(value = "styleId", required = false) Long styleId){
        List<SongDTO> songDTOS = new ArrayList<SongDTO>();
        if(styleId!=null) {
            songDTOS = this.service.findByUserPreferences(idUser, styleId);
        }else{
            songDTOS = this.service.findByUserPreferences(idUser, 0L);
        }


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

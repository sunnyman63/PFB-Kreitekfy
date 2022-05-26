package com.kreitek.kreitekfy.artist.infraestructure.rest;

import com.kreitek.kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.kreitekfy.artist.application.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/artists")
public class ArtistRestController {

    private final ArtistService artistService;

    public ArtistRestController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<List<ArtistDTO>> getAllArtists(){
        List<ArtistDTO> artists = this.artistService.getAllArtists();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @GetMapping(value = "/{artistsId}", produces = "application/json")
    ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long albumId){
        Optional<ArtistDTO> artist = this.artistService.getArtistById(albumId);
        if(artist.isPresent()){
            return new ResponseEntity<>(artist.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = "application/json")
    ResponseEntity<ArtistDTO> insertArtist(@RequestBody ArtistDTO artistDTO){
        artistDTO = this.artistService.saveArtist(artistDTO);
        return new ResponseEntity<>(artistDTO, HttpStatus.CREATED);
    }

    @DeleteMapping()
    ResponseEntity<ArtistDTO> deleteArtist(@RequestBody Long id){
        this.artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
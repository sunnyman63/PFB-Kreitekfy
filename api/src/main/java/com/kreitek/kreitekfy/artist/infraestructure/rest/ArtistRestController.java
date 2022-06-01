package com.kreitek.kreitekfy.artist.infraestructure.rest;

import com.kreitek.kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.kreitekfy.artist.application.service.ArtistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    ResponseEntity<Page<ArtistDTO>> getArtistByCriteriaPaged(@RequestParam(value = "filter", required = false) String filter, Pageable pageable){

        Page<ArtistDTO> items = this.artistService.getArtistByCriteriaPaged(pageable, filter);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<ArtistDTO>> getArtist(@RequestParam(name = "partialName", required = false) String partialName) {
        List<ArtistDTO> artist;

        if(partialName == null) {
            artist = this.artistService.getArtists();
        } else {
            artist = this.artistService.getArtistByName(partialName);
        }

        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @GetMapping(value = "/{artistId}", produces = "application/json")
    ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long artistId){
        Optional<ArtistDTO> artist = this.artistService.getArtistById(artistId);
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

    @PatchMapping(produces = "application/json")
    ResponseEntity<ArtistDTO> updateArtist(@RequestBody ArtistDTO artistDTO){
        artistDTO = this.artistService.saveArtist(artistDTO);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity<ArtistDTO> deleteArtist(@RequestBody Long id){
        this.artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
package com.kreitek.kreitekfy.style.infraestructure.rest;

import com.kreitek.kreitekfy.style.application.dto.StyleDTO;
import com.kreitek.kreitekfy.style.application.service.StyleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/styles")
public class StyleRestController {

    private final StyleService styleService;

    public StyleRestController(StyleService styleService) {
        this.styleService = styleService;
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<List<StyleDTO>> getAllStyles(){
        List<StyleDTO> styles = this.styleService.getAllStyles();
        return new ResponseEntity<>(styles, HttpStatus.OK);
    }

    @GetMapping(value = "/{styleId}", produces = "application/json")
    ResponseEntity<StyleDTO> getStyleById(@PathVariable Long styleId){
        Optional<StyleDTO> style = this.styleService.getStyleById(styleId);
        if(style.isPresent()){
            return new ResponseEntity<>(style.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = "application/json")
    ResponseEntity<StyleDTO> insertStyle(@RequestBody StyleDTO styleDTO){
        StyleDTO styleSaved = this.styleService.saveStyle(styleDTO);
        return new ResponseEntity<>(styleSaved,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{styleId}")
    ResponseEntity<?> deleteStyleById(@PathVariable Long styleId){
        this.styleService.deleteStyle(styleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
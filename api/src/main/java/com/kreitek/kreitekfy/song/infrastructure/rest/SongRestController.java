package com.kreitek.kreitekfy.song.infrastructure.rest;

import com.kreitek.kreitekfy.song.application.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongRestController {

    private final SongService service;

    @Autowired
    public SongRestController(SongService service) {
        this.service = service;
    }

    
}

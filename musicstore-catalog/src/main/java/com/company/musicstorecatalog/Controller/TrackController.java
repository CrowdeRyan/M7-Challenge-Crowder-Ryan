package com.company.musicstorecatalog.Controller;

import com.company.musicstorecatalog.Exception.BadIdException;
import com.company.musicstorecatalog.Model.Track;
import com.company.musicstorecatalog.Model.Track;
import com.company.musicstorecatalog.Repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/track")
public class TrackController {



        @Autowired
        private TrackRepository repo;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Track createTrack(@RequestBody Track track) {
            return repo.save(track);
        }

        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public List<Track> getAllTrack() {
            return repo.findAll();
        }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track getTrackById(@PathVariable int id) {
        Optional<Track> optionalTrack = repo.findById(id);
        if (optionalTrack.isPresent() == false) {
            throw new BadIdException("there is no track with id " + id);
        }
        return optionalTrack.get();
    }

        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public void updateTrack(@PathVariable int id, @RequestBody Track track) {
            if (track.getId() == null) {
                track.setId(id);
            } else if (track.getId() != id) {
                throw new BadIdException("The id in your path (" + id + ") is " +
                        "different from the id in your body (" + track.getId() + ").");
            }

            repo.save(track);
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public void removeTrackFromInventory(@PathVariable int id) {
            repo.deleteById(id);


        }
}

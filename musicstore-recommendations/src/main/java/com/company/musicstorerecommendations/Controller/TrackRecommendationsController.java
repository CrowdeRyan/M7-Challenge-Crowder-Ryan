package com.company.musicstorerecommendations.Controller;

import com.company.musicstorerecommendations.Exception.BadIdException;
import com.company.musicstorerecommendations.Model.TrackRecommendations;
import com.company.musicstorerecommendations.Repository.TrackRecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/track")
public class TrackRecommendationsController {
    @Autowired
    private TrackRecommendationsRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendations createTrackRecommendations(@RequestBody TrackRecommendations track) {
        return repo.save(track);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendations> getEveryLastTrackRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendations getTrackRecommendationsById(@PathVariable Integer id) {
        Optional<TrackRecommendations> optionalTrackRecommendations = repo.findById(id);
        if (optionalTrackRecommendations.isPresent() == false) {
            throw new BadIdException("there is no track with id " + id);
        }
        return optionalTrackRecommendations.get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTrackRecommendations(@PathVariable Integer id, @RequestBody TrackRecommendations track) {
        if (track.getId() == null) {
            track.setId(id);
        } else if (track.getId() != id) {
            throw new BadIdException("The id in your path (" + id + ") is " +
                    "different from the id in your body (" + track.getId() + ").");
        }

        repo.save(track);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeTrackRecommendationsFromInventory(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}

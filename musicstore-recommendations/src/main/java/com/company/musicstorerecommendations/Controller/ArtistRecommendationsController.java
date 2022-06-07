package com.company.musicstorerecommendations.Controller;

import com.company.musicstorerecommendations.Exception.BadIdException;
import com.company.musicstorerecommendations.Model.ArtistRecommendations;
import com.company.musicstorerecommendations.Repository.ArtistRecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistRecommendationsController {
    @Autowired
    private ArtistRecommendationsRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendations createArtistRecommendations(@RequestBody ArtistRecommendations artist) {
        return repo.save(artist);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendations> getEveryLastArtistRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendations getArtistRecommendationsById(@PathVariable int id) {
        Optional<ArtistRecommendations> optionalArtistRecommendations = repo.findById(id);
        if (optionalArtistRecommendations.isPresent() == false) {
            throw new BadIdException("there is no artist with id " + id);
        }
        return optionalArtistRecommendations.get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateArtistRecommendations(@PathVariable int id, @RequestBody ArtistRecommendations artist) {
        if (artist.getId() == null) {
            artist.setId(id);
        } else if (artist.getId() != id) {
            throw new BadIdException("The id in your path (" + id + ") is " +
                    "different from the id in your body (" + artist.getId() + ").");
        }

        repo.save(artist);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeArtistRecommendationsFromInventory(@PathVariable int id) {
        repo.deleteById(id);
    }
}

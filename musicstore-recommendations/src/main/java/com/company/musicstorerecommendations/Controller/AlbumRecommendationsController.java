package com.company.musicstorerecommendations.Controller;

import com.company.musicstorerecommendations.Exception.BadIdException;
import com.company.musicstorerecommendations.Model.AlbumRecommendations;
import com.company.musicstorerecommendations.Repository.AlbumRecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumRecommendationsController {
    @Autowired
    private AlbumRecommendationsRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendations createAlbumRecommendations(@RequestBody AlbumRecommendations album) {
        return repo.save(album);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendations> getEveryLastAlbumRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendations getAlbumRecommendationsById(@PathVariable int id) {
        Optional<AlbumRecommendations> optionalAlbumRecommendations = repo.findById(id);
        if (optionalAlbumRecommendations.isPresent() == false) {
            throw new BadIdException("there is no album with id " + id);
        }
        return optionalAlbumRecommendations.get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateAlbumRecommendations(@PathVariable int id, @RequestBody AlbumRecommendations album) {
        if (album.getId() == null) {
            album.setId(id);
        } else if (album.getId() != id) {
            throw new BadIdException("The id in your path (" + id + ") is " +
                    "different from the id in your body (" + album.getId() + ").");
        }

        repo.save(album);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeAlbumRecommendationsFromInventory(@PathVariable int id) {
        repo.deleteById(id);
    }
}


package com.company.musicstorecatalog.Controller;

import com.company.musicstorecatalog.Exception.BadIdException;
import com.company.musicstorecatalog.Model.Artist;
import com.company.musicstorecatalog.Repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist) {
        return repo.save(artist);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtist() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtistById(@PathVariable Integer id) {
        Optional<Artist> optionalArtist = repo.findById(id);
        if (optionalArtist.isPresent() == false) {
            throw new BadIdException("there is no artist with id " + id);
        }
        return optionalArtist.get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateArtist(@PathVariable Integer id, @RequestBody Artist artist) {
        if (artist.getId() == null) {
            artist.setId(id);
        } else if (artist.getId() != id) {
            throw new BadIdException("The id in your path (" + id + ") is " +
                    "different from the id in your body (" + artist.getId() + ").");
        }

        repo.save(artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeArtistFromInventory(@PathVariable Integer id) {
        repo.deleteById(id);


    }

}

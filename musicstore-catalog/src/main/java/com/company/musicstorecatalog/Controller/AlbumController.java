package com.company.musicstorecatalog.Controller;

import com.company.musicstorecatalog.Exception.BadIdException;
import com.company.musicstorecatalog.Model.Album;
import com.company.musicstorecatalog.Repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album) {
        return repo.save(album);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getEveryLastAlbum() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbumById(@PathVariable int id) {
        Optional<Album> optionalAlbum = repo.findById(id);
        if (optionalAlbum.isPresent() == false) {
            throw new BadIdException("there is no album with id " + id);
        }
        return optionalAlbum.get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAlbum(@PathVariable int id, @RequestBody Album album) {
        if (album.getId() == null) {
            album.setId(id);
        } else if (album.getId() != id) {
            throw new BadIdException("The id in your path (" + id + ") is " +
                    "different from the id in your body (" + album.getId() + ").");
        }

        repo.save(album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeAlbumFromInventory(@PathVariable int id) {
        repo.deleteById(id);
    }
}

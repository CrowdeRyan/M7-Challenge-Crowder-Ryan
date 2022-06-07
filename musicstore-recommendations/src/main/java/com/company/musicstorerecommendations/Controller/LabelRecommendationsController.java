package com.company.musicstorerecommendations.Controller;

import com.company.musicstorerecommendations.Exception.BadIdException;
import com.company.musicstorerecommendations.Model.LabelRecommendations;
import com.company.musicstorerecommendations.Repository.LabelRecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/label")
public class LabelRecommendationsController {
    @Autowired
    private LabelRecommendationsRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendations createLabelRecommendations(@RequestBody LabelRecommendations label) {
        return repo.save(label);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendations> getEveryLastLabelRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendations getLabelRecommendationsById(@PathVariable int id) {
        Optional<LabelRecommendations> optionalLabelRecommendations = repo.findById(id);
        if (optionalLabelRecommendations.isPresent() == false) {
            throw new BadIdException("there is no label with id " + id);
        }
        return optionalLabelRecommendations.get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLabelRecommendations(@PathVariable int id, @RequestBody LabelRecommendations label) {
        if (label.getId() == null) {
            label.setId(id);
        } else if (label.getId() != id) {
            throw new BadIdException("The id in your path (" + id + ") is " +
                    "different from the id in your body (" + label.getId() + ").");
        }

        repo.save(label);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeLabelRecommendationsFromInventory(@PathVariable int id) {
        repo.deleteById(id);
    }
}

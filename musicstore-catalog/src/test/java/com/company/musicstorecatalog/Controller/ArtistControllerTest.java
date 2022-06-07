package com.company.musicstorecatalog.Controller;

import com.company.musicstorecatalog.Model.Artist;
import com.company.musicstorecatalog.Repository.ArtistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRepository repo;

    private ObjectMapper mapper = new ObjectMapper();


    private Artist sampleArtist;
    private String artistJson;
    private final List<Artist> allArtist = new ArrayList<>();
    private String allArtistJson;

    @Before
    public void setup() throws Exception {
        sampleArtist = new Artist();
        sampleArtist.setName("Celldweller");
        sampleArtist.setId(5);
        sampleArtist.setInstagram("NeoTokyo");
        sampleArtist.setTwitter("BetaSessions");

        artistJson = mapper.writeValueAsString(sampleArtist);


        Artist artist1 = new Artist();
        artist1.setId(3);
        artist1.setName("Our Lady Peace");
        artist1.setInstagram("NotEnough");
        artist1.setTwitter("SpiralDown");

        allArtist.add(artist1);
        allArtist.add(sampleArtist);

        allArtistJson = mapper.writeValueAsString(allArtist);

    }

    @Test
    public void shouldCreateNewArtistOnPostRequest() throws Exception {
        Artist inputArtist = new Artist();
        inputArtist.setId(5);
        inputArtist.setName("Celldweller");
        inputArtist.setInstagram("NeoTokyo");
        inputArtist.setTwitter("BetaSessions");

        String inputJson = mapper.writeValueAsString(inputArtist);

        doReturn(sampleArtist).when(repo).save(inputArtist);

        mockMvc.perform(
                        post("/artist")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(artistJson));

    }

    @Test
    public void shouldReturnArtistById() throws Exception {
        doReturn(Optional.of(sampleArtist)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/artist/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(artistJson))
                );
    }


    @Test
    public void shouldReturnAllArtist() throws Exception {
        doReturn(allArtist).when(repo).findAll();

        mockMvc.perform(
                        get("/artist"))
                .andExpect(status().isOk())
                .andExpect(content().json(allArtistJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(
                        put("/artist/5")
                                .content(artistJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/artist/2")).andExpect(status().isOk());
    }


}
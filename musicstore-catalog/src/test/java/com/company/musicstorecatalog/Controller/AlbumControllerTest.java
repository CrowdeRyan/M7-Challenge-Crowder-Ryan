package com.company.musicstorecatalog.Controller;

import com.company.musicstorecatalog.Model.Album;
import com.company.musicstorecatalog.Repository.AlbumRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumRepository repo;

    private ObjectMapper mapper = new ObjectMapper();


    private Album sampleAlbum;
    private String albumJson;
    private final List<Album> allAlbum = new ArrayList<>();
    private String allAlbumJson;

    @Before
    public void setup() throws Exception {
        sampleAlbum = new Album();
        sampleAlbum.setId(1);
        sampleAlbum.setTitle("Issues");
        sampleAlbum.setArtist_id("3");
        sampleAlbum.setRelease_date("11-3-2002");
        sampleAlbum.setLabel_id(7);
        sampleAlbum.setList_price((int) 14.99);

        albumJson = mapper.writeValueAsString(sampleAlbum);


        Album album1 = new Album();
        album1.setId(3);
        album1.setTitle("The Passage");
        album1.setArtist_id("7");
        album1.setRelease_date("7-23-2012");
        album1.setLabel_id(8);
        album1.setList_price((int) 16.99);

        allAlbum.add(album1);
        allAlbum.add(sampleAlbum);

        allAlbumJson = mapper.writeValueAsString(allAlbum);

    }

    @Test
    public void shouldCreateNewAlbumOnPostRequest() throws Exception {
        Album inputAlbum = new Album();
        inputAlbum.setId(1);
        inputAlbum.setTitle("Issues");
        inputAlbum.setArtist_id("3");
        inputAlbum.setRelease_date("11-3-2002");
        inputAlbum.setLabel_id(7);
        inputAlbum.setList_price((int) 14.99);

        String inputJson = mapper.writeValueAsString(inputAlbum);

        doReturn(sampleAlbum).when(repo).save(inputAlbum);

        mockMvc.perform(
                        post("/album")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(albumJson));

    }

    @Test
    public void shouldReturnAlbumById() throws Exception {
        doReturn(Optional.of(sampleAlbum)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/album/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(albumJson))
                );
    }


    @Test
    public void shouldReturnAllAlbum() throws Exception {
        doReturn(allAlbum).when(repo).findAll();

        mockMvc.perform(
                        get("/album"))
                .andExpect(status().isOk())
                .andExpect(content().json(allAlbumJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(
                        put("/album/1")
                                .content(albumJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/album/2")).andExpect(status().isOk());
    }

    @Test
    public void shouldResponseWithStatus404IfIdIsNotFound() throws Exception {
        mockMvc.perform(get("/album/12"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldTryGetByIdAndReturn404StatusCode() throws Exception {
        mockMvc.perform(get("/album/time"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
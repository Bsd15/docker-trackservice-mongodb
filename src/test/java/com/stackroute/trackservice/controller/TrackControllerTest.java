//package com.stackroute.trackservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.trackservice.domain.Track;
//import com.stackroute.trackservice.exceptions.TestServiceGlobalExceptionHandler;
//import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
//import com.stackroute.trackservice.exceptions.TrackNotFoundException;
//import com.stackroute.trackservice.service.TrackService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class TrackControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    private Track track;
//
//    @MockBean
//    private TrackService trackService;
//    @InjectMocks
//    private TrackController trackController;
//
//    private List<Track> trackList;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(new TestServiceGlobalExceptionHandler()).build();
//        track = new Track(1, "Track 1", "Comments");
//        trackList = new ArrayList<>();
//        trackList.add(track);
//    }
//
//    /**
//     * Test - /api/v1/track - saveTrack()
//     * Check if saveTrack return track after saving it.
//     *
//     * @throws TrackAlreadyExistsException
//     */
//    @Test
//    public void givenTrackToSaveShouldReturnTrack() throws Exception {
//        when(trackService.saveTrack(any())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test - saveTrack()
//     * Method should throw TrackAlreadyExistsException
//     * @throws TrackAlreadyExistsException
//     * @throws Exception
//     */
//    @Test
//    public void givenUrlAndTrackShouldReturnTrackAlreadyExistsException() throws TrackAlreadyExistsException, Exception {
//        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test - getAllTracks()
//     * Method should return list of tracks.
//     * @throws Exception
//     */
//    @Test
//    public void givenUrlShouldReturnTracksList() throws Exception {
//        when(trackService.getAllTracks()).thenReturn(trackList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void givenUrlShouldReturnException() throws Exception {
//        when(trackService.getAllTracks()).thenThrow(Exception.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    /**
//     * Test - getTrack()
//     * Given Id of the track, method should return a Track object.
//     * @throws TrackNotFoundException
//     * @throws Exception
//     */
//    @Test
//    public void givenUrlWithIdShouldReturnTrack() throws TrackNotFoundException, Exception {
//        when(trackService.getTrack(anyInt())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test - getTrack
//     * Method should throw TrackNotFoundException
//     * @throws TrackNotFoundException
//     * @throws Exception
//     */
//    @Test
//    public void givenUrlWithIdToGetTrackShouldReturnTrackNotFoundException() throws TrackNotFoundException, Exception {
//        when(trackService.getTrack(anyInt())).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/116")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//
//    /**
//     * Test - deleteTrackById()
//     * Given Track Id to delete, should return deleted Track
//     * @throws TrackNotFoundException
//     * @throws Exception
//     */
//    @Test
//    public void givenUrlWithIdShouldReturnDeletedTrack() throws TrackNotFoundException, Exception {
//        when(trackService.deleteTrackById(anyInt())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/1")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test - deleteTrackById()
//     * Given non-existent track Id, should throw TrackNotFoundException
//     * @throws TrackNotFoundException
//     * @throws Exception
//     */
//    @Test
//    public void givenUrlWithIdShouldReturnTrackNotFoundException() throws TrackNotFoundException, Exception {
//        when(trackService.deleteTrackById(anyInt())).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/1")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//
//    /**
//     * Test - searchTrackByName()
//     * Given Name of the track, should throw TrackNotFoundException
//     * @throws TrackNotFoundException
//     * @throws Exception
//     */
//    @Test
//    public void givenUrlWithNameShouldReturnTrack() throws TrackNotFoundException, Exception {
//        when(trackService.searchTrackByName(any())).thenReturn(trackList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    /**
//     * Test - searchTrackByName()
//     * Given Name of the track, should return the corresponding track.
//     * @throws TrackNotFoundException
//     * @throws Exception
//     */
//    @Test
//    public void givenUrlWithNameShouldReturnTrackNotFoundException() throws TrackNotFoundException, Exception {
//        when(trackService.searchTrackByName(any())).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
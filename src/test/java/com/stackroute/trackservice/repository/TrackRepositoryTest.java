//package com.stackroute.trackservice.repository;
//
//import com.stackroute.trackservice.domain.Track;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@DataMongoTest
//public class TrackRepositoryTest {
//
//    @Autowired
//    private TrackRepository trackRepository;
//    Track track;
//
//    @Before
//    public void setUp() throws Exception {
//        track = new Track(1, "Track 1", "Comment 1");
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        trackRepository.deleteAll();
//    }
//
//    /**
//     * Test - trackRepository.save()
//     * Check if the save method successfully saves track to database or not.
//     */
//    @Test
//    public void givenTrackShouldReturnTrackObject() {
//        Track resultTrack = trackRepository.save(track);
//        assertEquals("givenUserShouldReturnUserObject(): check trackRepository.save()",
//                track, resultTrack);
//
//    }
//
//    /**
//     * Test - TrackRepository.save()
//     * Check if save() return correct track object after saving.
//     */
//    @Test
//    public void givenTrackShouldReturnCorrectTrackObject() {
//        Track resultTrack = trackRepository.save(track);
//        Track expectedTrack = new Track();
//        assertNotEquals("givenTrackShouldReturnCorrectTrackObject(): save() not returning correct track object.",
//                expectedTrack, resultTrack);
//    }
//
//    /**
//     * Test - TrackRepository.findAll()
//     * Test to check all tracks being added are correctly returned.
//     */
//    @Test
//    public void givenTracksShouldReturnAllTracksInDatabase() {
//        Track track1 = new Track(1, "Track 1", "Comment");
//        Track track2 = new Track(2, "Track 2", "Comment");
//        Track track3 = new Track(3, "Track 3", "Comment");
////       Track List that will be used as expected result
//        List<Track> trackList = new ArrayList<>();
//        trackList.add(track1);
//        trackList.add(track2);
//        trackList.add(track3);
////        Save tracks
//        trackRepository.save(track1);
//        trackRepository.save(track2);
//        trackRepository.save(track3);
//
////        List of result tracks
//        List<Track> resultTrackList = trackRepository.findAll();
//
////        Assert
//        assertEquals("givenTracksShouldReturnAllTracksInDatabase(): TrackRepository not returning correct tracks.",
//                trackList, resultTrackList);
//
//    }
//
//    /**
//     * Test - findById()
//     * Given track Id should return correct track
//     */
//    @Test
//    public void givenTrackIdShouldReturnTrack() {
//        trackRepository.save(track);
//        Track resultTrack = null;
//        Optional<Track> optionalTrack = trackRepository.findById(track.getTrackId());
//        if (optionalTrack.isPresent()) {
//            resultTrack = optionalTrack.get();
//        }
//        assertNotNull("givenTrackIdShouldReturnTrack(): findById() returned null",
//                resultTrack);
//        assertEquals("givenTrackIdShouldReturnTrack(): findById() did not return correct track.",
//                track, resultTrack);
//    }
//}
package com.stackroute.trackservice.seeddata;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Class implements CommandLineRunner to get called after application context is
 * created and seeds data to the database.
 */
@Component
//@PropertySource("track2.properties")
public class TrackServiceSeedDataCommandLineRunner implements CommandLineRunner {
    TrackService trackService;

    @Value("${track2.trackId}")
    int trackId; /*To be used to create track1 using Property source*/

    @Value("${track2.trackName}")
    String trackName; /*To be used to create track1 using Property source*/

    @Value("${track2.comments}")
    String comments; /*To be used to create track1 using Property source*/

    @Autowired
    public TrackServiceSeedDataCommandLineRunner(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void run(String... args) throws Exception {
        //        Create seed data objects
        Track track1 = new Track(trackId, trackName, comments);
//        Track track2 = new Track(5, "Track 5", "Comment from CommandLineRunner");
//        Track track3 = new Track(6, "Track 6", "Comment from CommandLineRunner");
        try {
            trackService.saveTrack(track1);
//            trackService.saveTrack(track2);
//            trackService.saveTrack(track3);
        } catch (TrackAlreadyExistsException trackAlreadyExistsException) {
            trackAlreadyExistsException.printStackTrace();
        }
    }
}

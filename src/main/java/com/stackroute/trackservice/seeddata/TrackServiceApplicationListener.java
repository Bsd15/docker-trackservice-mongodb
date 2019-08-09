package com.stackroute.trackservice.seeddata;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Class listens to Application Context events i.e. when application
 * starts or reloads and seeds predefined data into the database.
 */
@Component
//@PropertySource("track3.properties")
//@ConfigurationProperties("track3")

public class TrackServiceApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
//   TrackService to perform database operations.
    private TrackService trackService;

    /**
     * Environment sources
     */
    @Autowired
    private Environment environment;



    @Autowired
    public TrackServiceApplicationListener(TrackService trackService) {
        this.trackService = trackService;
    }

    @Value("${track.id}")
    private int id; /*Track id from application.properties*/

//    Values from track3 properties get by @ConfigurationProperties
    private int trackId;
    private String trackName;
    private String comments;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Create seed data objects
        Track track1 = new Track( id, environment.getProperty("track.trackName"), environment.getProperty("track.comments"));
        Track track2 = new Track(trackId, trackName, comments);
//        Track track3 = new Track(3, "Track 3", "Comment 3");
        try {
            trackService.saveTrack(track1);
            trackService.saveTrack(track2);
//            trackService.saveTrack(track3);
        } catch (TrackAlreadyExistsException trackAlreadyExistsException) {
            trackAlreadyExistsException.printStackTrace();
        }
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

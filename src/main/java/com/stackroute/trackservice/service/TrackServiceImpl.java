package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.exceptions.TrackNotFoundException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implements TrackService to provide basic insert, delete, get operations on Tracks
 */
@Service
@Primary
@Profile("dev")
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    /**
     * Insert track into the database.
     * @param track Track to be inserted in the database
     * @return Track created after inserting into the database.
     * @throws TrackAlreadyExistsException When a Track with same Id already exists.
     */
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (track != null) {
            if (trackRepository.existsById(track.getTrackId())) {
                throw new TrackAlreadyExistsException("print error  ");
            }
            return trackRepository.save(track);
        }
        return track;
    }

    /**
     * Finds the track in the database using the trackId.
     * @param id ID of the track to get from database
     * @return Track object
     * @throws TrackNotFoundException
     */
    @Override
    public Track getTrack(int id) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException();
        }
        return trackRepository.findById(id).get();
    }

    /**
     * Returns all the tracks in the database.
     * @return List of Tracks as List<Track>
     * @throws Exception
     */
    @Override
    public List<Track> getAllTracks() throws Exception
    {
        List<Track> allTracks = trackRepository.findAll();
        if (allTracks.isEmpty()) {
            throw new Exception("No Tracks in the table.");
        }
        return allTracks;
    }

    /**
     * Search tracks by Name
     * @param trackName Name of the track to be searched
     * @return List of tracks found with the given name
     * @throws TrackNotFoundException
     */
    @Override
    public List<Track> searchTrackByName(String trackName) throws TrackNotFoundException {
        List<Track> foundTracksList = trackRepository.findByTrackName(trackName);
        if (foundTracksList.isEmpty()) {
            throw new TrackNotFoundException();
        }
        return foundTracksList;
    }

    /**
     * First checks the track is present in the database and stores in a Optional.
     * If the track is present then deletes the track in the database and return the track
     * else return null.
     * @param id Id of the track to be deleted.
     * @return Track if deleted or null if the track is not found in the database.
     * @throws TrackNotFoundException
     */
    @Override
    public Track deleteTrackById(int id) throws TrackNotFoundException {
        Optional<Track> trackToBeDeleted = trackRepository.findById(id);
        if (trackToBeDeleted.isPresent()) {
            trackRepository.deleteById(id);
            return trackToBeDeleted.get();
        }
//        If the track to be deleted is not present in the database.
        throw new TrackNotFoundException();
    }

    /**
     * Deletes all the tracks in the database.
     */
    @Override
    public void deleteAllTracks() {
        trackRepository.deleteAll();
    }


    /**
     * Finds the Track by Id as a reference and updated it's fields and save's it.
     * @param trackId Id of the track to be updated
     * @param updatedTrack Track object containing the updated details
     * @return Updated track.
     * @throws TrackNotFoundException
     */
    @Override
    public Track updateTrackById(int trackId, Track updatedTrack) throws TrackNotFoundException {
//        Gets the reference to the Track object (lazy)
        if (trackRepository.existsById(updatedTrack.getTrackId())) {
            Optional<Track> optionalTrack = trackRepository.findById(trackId);
            if (optionalTrack.isPresent()) {
                Track trackToUpdate = optionalTrack.get();
                trackToUpdate.setTrackName(updatedTrack.getTrackName());
                trackToUpdate.setComments(updatedTrack.getComments());
                return trackRepository.save(trackToUpdate);
            }
        }
        throw new TrackNotFoundException();
    }

}

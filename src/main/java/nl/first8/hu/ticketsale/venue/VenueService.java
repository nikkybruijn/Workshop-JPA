package nl.first8.hu.ticketsale.venue;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Nikky on 12-6-2017.
 */

@Service
public class VenueService {
  private final VenueRepository venueRepository;

  @Autowired
  public VenueService(VenueRepository venueRepository) {
    this.venueRepository = venueRepository;
  }

  public Optional<List<Concert>> getByArtist(@NonNull final String artistName) {
    return venueRepository.findByArtistName(artistName);
  }

  public Optional<List<Concert>> getByGenre(@NonNull final String genreName) {
    return venueRepository.findByGenre(genreName);
  }

  public Optional<List<Concert>> getByLocation(@NonNull final String locationName) {
    return venueRepository.findByLocation(locationName);
  }

  public Optional<List<Concert>> getByDate(@NonNull final String date) {
    return venueRepository.findByDate(date);
  }

}

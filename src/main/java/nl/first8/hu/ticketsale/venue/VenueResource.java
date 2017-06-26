package nl.first8.hu.ticketsale.venue;

import nl.first8.hu.ticketsale.sales.Ticket;
import nl.first8.hu.ticketsale.sales.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Nikky on 12-6-2017.
 */

@RestController
@RequestMapping("/concerts")
@Transactional
public class VenueResource {
  private final VenueService venueService;

  @Autowired
  public VenueResource(VenueService venueService) {
    this.venueService = venueService;
  }

  @GetMapping(path = "/search/artist")
  public ResponseEntity<List<Concert>> getConcertsByArtistName(@RequestParam("artist_name") final String artist) {
    try {
      Optional<List<Concert>> concerts = venueService.getByArtist(artist);
      return ResponseEntity.ok(concerts.get());
    } catch (RuntimeException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping(path = "/search/genre")
  public ResponseEntity<List<Concert>> getConcertsByGenre(@RequestParam("artist_genre") final String genre) {
    try {
      Optional<List<Concert>> concerts = venueService.getByGenre(genre);
      return ResponseEntity.ok(concerts.get());
    } catch (RuntimeException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping(path = "/search/date")
  public ResponseEntity<List<Concert>> getConcertsByDate(@RequestParam("date") final String date) {
    try {
      Optional<List<Concert>> concerts = venueService.getByDate(date);
      return ResponseEntity.ok(concerts.get());
    } catch (RuntimeException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }


  @GetMapping(path = "/search/location")
  public ResponseEntity<List<Concert>> getConcertsByLocation(@RequestParam("location") final String location) {
    try {
      Optional<List<Concert>> concerts = venueService.getByLocation(location);
      return ResponseEntity.ok(concerts.get());
    } catch (RuntimeException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}

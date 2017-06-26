package nl.first8.hu.ticketsale.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class VenueRepository {

  private final EntityManager entityManager;

  @Autowired
  public VenueRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Optional<Concert> findConcertById(Long concertId) {
    return Optional.ofNullable(entityManager.find(Concert.class, concertId));
  }

  public void insert(final Concert concert) {
    entityManager.persist(concert);
  }

  public Optional<List<Concert>> findByArtistName(String artist) {
    try {
      String query = "SELECT c FROM Concert c WHERE c.artist.name = :name";
      return Optional.of(entityManager.createQuery(query, Concert.class).setParameter("name", artist).getResultList());
    } catch (NoResultException nre) {
      return Optional.empty();
    }
  }

  public Optional<List<Concert>> findByGenre(String genre) {
    Genre artistGenre = Genre.valueOf(genre);
    try {
      String query = "SELECT c FROM Concert c WHERE c.artist.genre = :genre";

      return Optional.of(entityManager.createQuery(query, Concert.class).setParameter("genre", artistGenre).getResultList());
    } catch (NoResultException nre) {
      return Optional.empty();
    }
  }

  public Optional<List<Concert>> findByDate(String date) {
    try {
      SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
      Date parsedDate = null;

      parsedDate = dateFormatter.parse(date);
      String query = "SELECT c FROM Concert c WHERE c.date > :date";

      return Optional.of(entityManager.createQuery(query, Concert.class).setParameter("date", parsedDate).getResultList());

    } catch (NoResultException | ParseException e) {
      return Optional.empty();
    }
  }

  public Optional<List<Concert>> findByLocation(String location) {
    try {
      String query = "SELECT c FROM Concert c WHERE c.location.name = :location";

      return Optional.of(entityManager.createQuery(query, Concert.class).setParameter("location", location).getResultList());
    } catch (NoResultException nre) {
      return Optional.empty();
    }
  }
}

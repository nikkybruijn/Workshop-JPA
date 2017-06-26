package nl.first8.hu.ticketsale.venue;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.first8.hu.ticketsale.util.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Nikky on 13-6-2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(false)
public class VenueRepositoryIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private TestRepository testRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testSearchByArtistName() throws Exception {
      testRepository.createConcert("Kendrick Lamar", Genre.HIPHOP ,"Los Angeles");
//      testRepository.createConcert("Katy Perry", Genre.POP, "Amsterdam");
//      testRepository.createConcert("The Analogues", Genre.ROCK, "Hilversum");

    String expectedResult = "The Analogues";

    mvc.perform(
      get("/concerts/search/artist").param("artist_name", expectedResult)
        .accept(MediaType.APPLICATION_JSON)
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andReturn();
  }

  @Test
  public void testSearchByArtistGenre() throws Exception {
    testRepository.createConcert("Kendrick Lamar", Genre.HIPHOP ,"Los Angeles");
    testRepository.createConcert("Katy Perry", Genre.POP, "Amsterdam");
    testRepository.createConcert("The Analogues", Genre.ROCK, "Hilversum");

    String expectedResult = "HIPHOP";

    mvc.perform(
      get("/concerts/search/genre").param("artist_genre", expectedResult)
        .accept(MediaType.APPLICATION_JSON)
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andReturn();

  }

  @Test
  public void testSearchByDate() throws Exception {
    testRepository.createConcertWithDate("Kendrick Lamar", Genre.HIPHOP, "Los Angeles", "02-03-2017");
    testRepository.createConcertWithDate("Katy Perry", Genre.POP, "Amsterdam", "10-09-2016");
    testRepository.createConcertWithDate("The Analogues", Genre.ROCK, "Hilversum", "27-11-2017");

    String expectedResult = "10-09-2016";

    mvc.perform(
      get("/concerts/search/date").param("date", expectedResult)
        .accept(MediaType.APPLICATION_JSON)
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andReturn();

  }

  @Test
  public void testSearchByLocation() throws Exception {
    testRepository.createConcert("Kendrick Lamar", Genre.HIPHOP ,"Los Angeles");
    testRepository.createConcert("Katy Perry", Genre.POP, "Amsterdam");
    testRepository.createConcert("The Analogues", Genre.ROCK, "Hilversum");

    String expectedResult = "Amsterdam";

    mvc.perform(
      get("/concerts/search/location").param("location", expectedResult)
        .accept(MediaType.APPLICATION_JSON)
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andReturn();

  }

}

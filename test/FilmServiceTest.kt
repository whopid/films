import com.example.apiHandler.CreateFilmRequest
import com.example.dao.FilmDAOInMemory
import com.example.service.FilmService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FilmServiceTest {
    private val filmDao = FilmDAOInMemory()
    private val filmService = FilmService(filmDao)

    @Test
    fun testAuthorFiltering() = runBlocking {
        //prepare
        listOf(
            CreateFilmRequest("Name1", "author1", 1.1f, 3600),
            CreateFilmRequest("Name2", "author1", 1.1f, 3600),
            CreateFilmRequest("Name3", "author2", 1.1f, 3600),
            CreateFilmRequest("Name4", "author3", 1.1f, 3600),
            CreateFilmRequest("Name5", "author4", 1.1f, 3600)
        ).forEach {
            filmDao.add(it)
        }

        val filteredByAuthor = filmService.findByAuthor("author1")
        assertEquals(2, filteredByAuthor.size)
    }
}


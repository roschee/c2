package ch.rmuerner.c2.db;

import java.sql.Date;

import junit.framework.TestCase;

import org.junit.Test;

import ch.rmuerner.c2.db.dao.DAOFactory;
import ch.rmuerner.c2.db.dao.h2.H2CategoryDAO;
import ch.rmuerner.c2.db.dao.h2.H2CompetitionDAO;
import ch.rmuerner.c2.db.dao.h2.H2DAOFactory;
import ch.rmuerner.c2.db.dto.CategoryDTO;
import ch.rmuerner.c2.db.dto.CompetitionDTO;

public class H2DatabaseTest extends TestCase {

	@Test
	public void testDBSetUp() {
		H2DAOFactory h2DAOFactory = (H2DAOFactory) DAOFactory
				.getDAOFactory(DAOFactory.Database.H2);
		h2DAOFactory.initDatabase();
	}

	@Test
	public void testCompetition() {
		// Get DAO
		H2DAOFactory h2DAOFactory = (H2DAOFactory) DAOFactory
				.getDAOFactory(DAOFactory.Database.H2);
		h2DAOFactory.initDatabase();
		H2CompetitionDAO dao = (H2CompetitionDAO) h2DAOFactory
				.getCompetitionDAO();
		// Create First
		CompetitionDTO dto1 = new CompetitionDTO(-1, //
				"Schüler und Jugendturnier Spiez 2013", //
				"AC Halle Spiez", //
				new Date(java.util.Date.UTC(2013, 5, 2, 0, 0, 0)));

		int result1 = dao.saveOrUpdate(dto1);
		assertEquals(1, result1);
		assertEquals(1, dao.selectAll().size());
		
		CompetitionDTO dtoRead1 = dao.selectById(1);
		assertEquals("Schüler und Jugendturnier Spiez 2013", dtoRead1.getName());
		
		// Create Second
		CompetitionDTO dto2 = new CompetitionDTO(-1, //
				"OJEM", //
				"Saanen", //
				new Date(java.util.Date.UTC(2013, 9, 10, 0, 0, 0)));

		int result2 = dao.saveOrUpdate(dto2);
		assertEquals(1, result2);
		assertEquals(2, dao.selectAll().size());
		
		CompetitionDTO dtoRead2 = dao.selectById(2);
		assertEquals("OJEM", dtoRead2.getName());
		
		// Delete First
		CompetitionDTO dto3 = dao.selectById(1);
		int result3 = dao.delete(dto3);
		assertEquals(1, result3);
		assertEquals(1, dao.selectAll().size());
		
	}
	
	@Test
	public void testCategory(){
		// Get DAO
		H2DAOFactory h2DAOFactory = (H2DAOFactory) DAOFactory
				.getDAOFactory(DAOFactory.Database.H2);
		h2DAOFactory.initDatabase();
		H2CategoryDAO dao = (H2CategoryDAO) h2DAOFactory
				.getCategoryDAO();
		
		// Create First
		CategoryDTO dto1 = new CategoryDTO(-1, "Schüler A -30kg", -1, -1);

		int result1 = dao.saveOrUpdate(dto1);
		assertEquals(1, result1);
		assertEquals(1, dao.selectAll().size());
		
		CategoryDTO dtoRead1 = dao.selectById(1);
		assertEquals("Schüler A -30kg", dtoRead1.getName());
		
		
		// Create Second
		CategoryDTO dto2 = new CategoryDTO(-1, "Schüler B -26kg", -1, -1);

		int result2 = dao.saveOrUpdate(dto2);
		assertEquals(1, result2);
		assertEquals(2, dao.selectAll().size());
		
		CategoryDTO dtoRead2 = dao.selectById(1);
		assertEquals("Schüler A -30kg", dtoRead2.getName());
		
		// Delete First
	}
}

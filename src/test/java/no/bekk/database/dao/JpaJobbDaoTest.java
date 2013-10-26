package no.bekk.database.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.bekk.database.batchsize.Jobb;
import no.bekk.database.batchsize.JobbDao;
import no.bekk.database.batchsize.JobbEvent;
import no.bekk.database.logging.LogAppender;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Level;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
public class JpaJobbDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private JobbDao jobbDao;

	private LogAppender logAppender;

	@Before
	public void setUp() {

		makeJobs(10);

		em.flush();
		em.clear();
	}

	@Test
	public void testname() throws Exception {

		final Jobb findById = jobbDao.findById(1L);
	}

	@Test
	public void updateWithTempTable() {
		Set<Long> id = new HashSet<Long>();
		id.add(1L);
		id.add(2L);
		id.add(3L);
		jobbDao.oppdaterJobb(id);
		em.flush();
		System.out.println();
	}

	@Test
	public void skalKunGjoere2SpoerringerMedBatchSize10() {
		assertEquals(10, jobbDao.count());

		logAppender = new LogAppender(Level.INFO, "jdbc.sqlonly");

		List<Jobb> all = jobbDao.getAll();

		for (Jobb jobb : all) {
			System.out.println(jobb.getEvents());
		}
		assertEquals(2, logAppender.getLogEvents().size());
	}

	private void makeJobs(final int count) {

		for (int i = 0; i < count; i++) {
			Jobb jobb = new Jobb();
			em.persist(jobb);
			for (int j = 0; j < 3; j++) {
				JobbEvent jobbEvent = new JobbEvent("hei" + j, jobb);
				em.persist(jobbEvent);
			}

		}
	}

}

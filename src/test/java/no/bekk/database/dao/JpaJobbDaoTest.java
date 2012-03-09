package no.bekk.database.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.bekk.database.batchsize.Jobb;
import no.bekk.database.batchsize.JobbDao;
import no.bekk.database.batchsize.JobbEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
public class JpaJobbDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private JobbDao jobbDao;

	@Before
	public void setUp() {

		makeJobs(10);

		em.flush();
		em.clear();
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

	@Test
	public void avsenderSkalFinneJobben() {
		assertEquals(10, jobbDao.count());

		List<Jobb> all = jobbDao.getAll();

		for (Jobb jobb : all) {
			System.out.println(jobb.getEvents());
		}
		assertTrue(true);
	}

}

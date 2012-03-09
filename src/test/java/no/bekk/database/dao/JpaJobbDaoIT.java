package no.bekk.database.dao;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
public class JpaJobbDaoIT extends AbstractTransactionalJUnit4SpringContextTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	private JobbDao jobbDao;

	@Before
	public void setUp() {

	}

	@Test
	public void avsenderSkalFinneJobben() {
		assertTrue(true);
	}

}

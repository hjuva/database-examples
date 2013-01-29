package no.bekk.database.batchsize;

import java.util.Set;

import no.bekk.database.dao.JpaDao;

import org.springframework.stereotype.Repository;

@Repository
public class JpaJobbDao extends JpaDao<Long, Jobb> implements JobbDao {

	@Override
	public void oppdaterJobb(final Set<Long> id) {
		getEntityManager()
				.createQuery("update " + getEntityClass().getName() + " set flag = false where id in (:id)")
				.setParameter("id", id)
				.executeUpdate();
	}

}

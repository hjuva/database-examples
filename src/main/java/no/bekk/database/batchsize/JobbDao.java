package no.bekk.database.batchsize;

import java.util.Set;

import no.bekk.database.dao.Dao;

public interface JobbDao extends Dao<Long, Jobb> {

	void oppdaterJobb(Set<Long> id);

}

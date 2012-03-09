package no.bekk.database.batchsize;

import no.bekk.database.dao.JpaDao;

import org.springframework.stereotype.Repository;

@Repository
public class JpaJobbDao extends JpaDao<Long, Jobb> implements JobbDao {

}

package no.bekk.database.dao;

import no.bekk.database.model.Jobb;

import org.springframework.stereotype.Repository;

@Repository
public class JpaJobbDao extends JpaDao<Long, Jobb> implements JobbDao {

}

package no.bekk.database.util;

import javax.persistence.EntityManager;

public class BrevTestData {

	private final EntityManager em;

	public BrevTestData(final EntityManager em) {
		this.em = em;
	}

}

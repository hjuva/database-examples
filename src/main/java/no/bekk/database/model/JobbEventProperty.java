package no.bekk.database.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JobbEventProperty {

	@SuppressWarnings("unused")
	private JobbEventProperty() {
	}

	@Column(name = "PROP_KEY")
	private String key;

	@Column(name = "PROP_VALUE")
	private String value;

	public JobbEventProperty(final String key, final String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}

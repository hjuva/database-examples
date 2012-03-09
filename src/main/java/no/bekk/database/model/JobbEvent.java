package no.bekk.database.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "JOBB_EVENT")
public class JobbEvent {

	private static final Logger LOG = LoggerFactory.getLogger(JobbEvent.class);
	private static final String JOBB_EVENT_SEQ = "JOBB_EVENT_SEQ";

	@SuppressWarnings("unused")
	@Id
	@GenericGenerator(name = JOBB_EVENT_SEQ, strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = JOBB_EVENT_SEQ), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "100"),
			@Parameter(name = "optimizer", value = "org.hibernate.id.enhanced.OptimizerFactory.HiLoOptimizer") })
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = JOBB_EVENT_SEQ)
	@Column(name = "ID")
	private long id;

	@Column(name = "MELDING")
	private String melding;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@CollectionTable(name = "JOBB_EVENT_PROPERTIES", joinColumns = @JoinColumn(name = "JOBB_EVENT_ID"))
	@BatchSize(size = 200)
	private List<JobbEventProperty> eventProps;

	@SuppressWarnings("unused")
	@ManyToOne
	@JoinColumn(name = "JOBB_ID")
	@Index(name = "JE_JOBB_ID_IDX")
	private Jobb jobb;

	@SuppressWarnings("unused")
	private JobbEvent() {
	}

}

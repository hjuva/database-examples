package no.bekk.database.logging;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class LogAppender {

	private final ListAppender<ILoggingEvent> appender = new ListAppender<ILoggingEvent>();

	public LogAppender() {
		init(Level.TRACE, "no.posten");
	}

	public LogAppender(final Level level, final String... logname) {
		init(level, logname);
	}

	public void clear() {
		appender.list.clear();
	}

	public void init(final Level level, final String... logname) {
		Logger rootlogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
		rootlogger.detachAndStopAllAppenders();
		LoggerContext loggerContext = rootlogger.getLoggerContext();

		for (String string : logname) {
			Logger logger = (Logger) LoggerFactory.getLogger(string);
			logger.setLevel(level);
			logger.addAppender(appender);
		}
		appender.setContext(loggerContext);
		appender.start();
	}

	public List<ILoggingEvent> hentLogEvents() {
		return appender.list;
	}

	public void assertSingleLoggedEvent(final String msg, final Level level) {
		assertEquals(1, appender.list.size());
		assertLoggedEventEquals(0, msg, level);
	}

	public void assertLoggedEventEquals(final int statement, final String msg, final Level level) {
		ILoggingEvent loggingEvent = appender.list.get(statement);
		assertEquals(level, loggingEvent.getLevel());
		assertEquals(msg, loggingEvent.getMessage());
	}

	public boolean hasBeenLoggedWithLevel(final Level... levels) {
		final List<Level> asList = Arrays.asList(levels);
		for (ILoggingEvent logEvent : appender.list) {
			if (asList.contains(logEvent.getLevel())) {
				return true;
			}
		}
		return false;
	}

	public boolean findLoggedEvent(final String msg, final Level level) {
		return getLoggedEvent(msg, level) != null;
	}

	public String getLoggedEvent(final String msg, final Level level) {
		for (ILoggingEvent logEvent : appender.list) {
			if (logEvent.getLevel().equals(level) && logEvent.getFormattedMessage().startsWith(msg)) {
				return logEvent.getFormattedMessage();
			}
		}
		return null;
	}

	public void assertMdcVerdiSatt(final int statement, final String key, final String expectedValue) {
		ILoggingEvent loggingEvent = appender.list.get(statement);
		@SuppressWarnings("deprecation")
		Map<String, String> mdc = loggingEvent.getMdc();
		String value = mdc.get(key);
		assertEquals(expectedValue, value);
	}

	public void assertMdcIkkeSatt(final int statement, final String key) {
		ILoggingEvent loggingEvent = appender.list.get(statement);
		@SuppressWarnings("deprecation")
		Map<String, String> mdc = loggingEvent.getMdc();
		assertFalse("MDC " + key + "skal ikke vara satt", mdc.containsKey(key));
	}

}

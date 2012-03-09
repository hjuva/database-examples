/**
 * Copyright 2010 Tim Azzopardi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package no.bekk.database.log;

import java.util.Arrays;
import java.util.List;

import net.sf.log4jdbc.Slf4jSpyLogDelegator;
import net.sf.log4jdbc.Spy;
import net.sf.log4jdbc.tools.LoggingType;

public class Log4JdbcCustomFormatter extends Slf4jSpyLogDelegator {

	private LoggingType loggingType = LoggingType.DISABLED;

	private String margin = "";

	private String sqlPrefix = "SQL:";

	public int getMargin() {
		return margin.length();
	}

	public void setMargin(final int n) {
		margin = String.format("%1$#" + n + "s", "");
	}

	public Log4JdbcCustomFormatter() {
	}

	@Override
	public String sqlOccured(final Spy spy, final String methodCall, final String rawSql) {
		if (loggingType == LoggingType.DISABLED) {
			return "";
		}

		String sql = rawSql;
		// Remove all existing cr lf, unless MULTI_LINE
		if (loggingType != LoggingType.MULTI_LINE) {
			sql = sql.replaceAll("\r", "");
			sql = sql.replaceAll("\n", "");
			return sql;
		}

		final String fromClause = " from ";
		if (loggingType == LoggingType.MULTI_LINE) {
			List<String> insertNewlineBefore = Arrays.asList("from ", "inner join", "left outer join", "where ", "or ", "and ",
					"\\(select", "then ", "when ", "end ", "order by ");
			for (String pattern : insertNewlineBefore) {
				sql = sql.replaceAll(pattern, "\n" + pattern);
			}
			final String comma = ", ";
			sql = sql.replaceAll(comma, comma + "\n");
		}
		if (loggingType == LoggingType.SINGLE_LINE_TWO_COLUMNS) {
			if (sql.startsWith("select")) {
				String from = sql.substring(sql.indexOf(fromClause) + fromClause.length());
				sql = from + "\t" + sql;
			}
		}
		getSqlOnlyLogger().info(sqlPrefix + sql);
		return sql;
	}

	@Override
	public String sqlOccured(final Spy spy, final String methodCall, final String[] sqls) {
		String s = "";
		for (String sql : sqls) {
			s += sqlOccured(spy, methodCall, sql) + String.format("%n");
		}
		return s;
	}

	public LoggingType getLoggingType() {
		return loggingType;
	}

	public void setLoggingType(final LoggingType loggingType) {
		this.loggingType = loggingType;
	}

	public String getSqlPrefix() {
		return sqlPrefix;
	}

	public void setSqlPrefix(final String sqlPrefix) {
		this.sqlPrefix = sqlPrefix;
	}

}
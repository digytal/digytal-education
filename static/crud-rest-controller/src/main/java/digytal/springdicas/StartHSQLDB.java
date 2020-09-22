package digytal.springdicas;

import org.hsqldb.util.DatabaseManagerSwing;

public class StartHSQLDB {
	static String FILE_URL = "file:/digytal/springdicas/database/database";

	public static void main(String[] args) {
		// local();
		server();
	}

	public static void local() {
		String[] dbArgs = { "--user", "sa", "--password", "", "--url", "jdbc:hsqldb:" + FILE_URL };
		DatabaseManagerSwing.main(dbArgs);
	}

	public static void server() {
		final String[] dbArgs = { "--database.0", FILE_URL, "--dbname.0", "database", "--port", "5454" };
		org.hsqldb.server.Server.main(dbArgs);
		// final String[] serveArgs = { "--user", "sa", "--password", "", "--url",
		// "jdbc:hsqldb:hsql://localhost:5454/kayrosdb"};
		// DatabaseManagerSwing.main(serveArgs);
	}
}

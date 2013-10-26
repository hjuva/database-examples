package no.bekk.database;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class SetTest {

	@Test
	public void test() throws IOException {
		new File("target/testing").createNewFile();
	}

}

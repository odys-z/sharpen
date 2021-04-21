package odysz.github.io.sharpen.example;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.odysz.anson.x.AnsonException;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
    public void testApp() throws AnsonException, IOException {
		AnMsg m = new AnMsg();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		m.toBlock(bos);
        assertEquals( "{\"type\": \"odysz.github.io.sharpen.example.AnMsg\", \"version\": null}",
        		bos.toString() );
    }
}

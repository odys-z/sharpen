package odysz.github.io.sharpen.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import io.odysz.anson.x.AnsonException;

/**
 *
 */
public class App {
    public static void main( String[] args ) {
		try {
			AnMsg msg = new AnMsg();
			msg.version = "1.0";

			ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
			msg.toBlock(bos);
			System.out.println(bos);

		} catch (AnsonException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}


package sharpen.ui.tests;

import sharpen.core.Configuration;
import org.junit.Test;

public class API8ChainCallTestCase extends AbstractConversionTestCase {

	@Test
	public void testChainCall() throws Throwable {
		runResourceTestCase(newInterface2Configuration(), "api8/chaincall/Callee", "api8/chaincall/Callee");
		runResourceTestCase(newInterface2Configuration(), "api8/chaincall/Caller1", "api8/chaincall/Caller1");
	}

	public Configuration newInterface2Configuration() {
		Configuration configuration = newPascalCasePlusConfiguration();
		configuration.enableNativeInterfaces();
		configuration.enableNativeTypeSystem();
		return configuration;
	}
}


package sharpen.ui.tests;

import sharpen.core.Configuration;
import org.junit.Test;

public class NativeInterface2TestCase extends AbstractConversionTestCase {

	@Test
	public void testInterface2() throws Throwable {
		runResourceTestCase(newInterface2Configuration(), "interface2/Foo", "interface2/IFoo");
		runResourceTestCase(newInterface2Configuration(), "interface2/Provider", "interface2/Provider");
	}
	
	@Test
	public void testAnoymousCall() throws Throwable {
		// order is important
		runResourceTestCase(newInterface2Configuration(), "interface2/Foo", "interface2/IFoo");
		runResourceTestCase(newInterface2Configuration(), "interface2/Provider", "interface2/Provider");
		runResourceTestCase(newInterface2Configuration(), "interface2/Caller", "interface2/Caller");
	}
	
	public Configuration newInterface2Configuration() {
		Configuration configuration = newPascalCasePlusConfiguration();
		configuration.setIgnoreErrors(true);
		configuration.enableNativeInterfaces();
		configuration.enableNativeTypeSystem();
		
		configuration.setIgnoreErrors(Boolean.valueOf(System.getProperty("ie")));
		return configuration;
	}
}

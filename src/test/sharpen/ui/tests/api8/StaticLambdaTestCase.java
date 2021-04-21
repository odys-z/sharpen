package sharpen.ui.tests.api8;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import sharpen.core.Configuration;
import sharpen.ui.tests.AbstractConversionTestCase;

public class StaticLambdaTestCase extends AbstractConversionTestCase {
	@Test
	public void testCompilerAnnotations() throws IOException, CoreException {
		runResourceTestCase("StaticLambda");
	}
	
	@Override
	protected void runResourceTestCase(String resourceName) throws IOException, CoreException {
		super.runResourceTestCase("api8/" + resourceName);
	}
	
	@Override
	protected Configuration getConfiguration() {
	    final Configuration config = super.getConfiguration();
	    config.enableNativeInterfaces();
		return config;
	}
}

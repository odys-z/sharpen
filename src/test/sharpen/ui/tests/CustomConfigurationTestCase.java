/* Copyright (C) 2010 Versant Inc. http://www.db4o.com */

package sharpen.ui.tests;

import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sharpen.core.Configuration;
import sharpen.core.ConfigurationFactory;
import sharpen.core.JavaProjectCmd;
import sharpen.core.Sharpen;
import sharpen.ui.tests.configuration.CustomConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CustomConfigurationTestCase extends AbstractConversionTestCase {

	private IProgressMonitor _progressMonitor;
	private ArrayList<String> _configJars;

	@Before
	@Override
	public void setUp() throws Exception {
		_project = new JavaProjectCmd();
		_configJars = new ArrayList<String>();
		Path configPath = createConfigurationJar(CustomConfiguration.class);
		_configJars.add(configPath.toString());
		configPath = createConfigurationJar(WrongConfiguration.class);
		_configJars.add(configPath.toString());
		configPath = createConfigurationJar(NoPublicConstructorConfiguration.class);
		_configJars.add(configPath.toString());
		_project.setclassPath(_configJars);

		_progressMonitor = new IProgressMonitor() {
			// @Override for interface can no longer be used in newer JDK version? 
			public void beginTask(String s, int i) { }

			public void done() { }

			public void internalWorked(double v) { }

			public boolean isCanceled() { return false; }

			public void setCanceled(boolean b) { }

			public void setTaskName(String s) { }

			public void subTask(String s) { }

			public void worked(int i) { }
		};
	}

	private static Path createConfigurationJar(Class<?> configurationClass) throws Exception {
		String jar = JarUtilities.createJar(configurationClass);
		URI currentDirectoryURI = ConfigurationFactory.getCurrentDirectoryURI();
		File currentDirectory = new File(currentDirectoryURI);
		String configJar = configurationClass.getSimpleName()+ ".sharpenconfig.jar";
		Path configPath = Paths.get(currentDirectory.getPath(), configJar);
		tryDelete(configPath);
		Files.move(Paths.get(jar), configPath);
		return configPath;
	}

	@After
	@Override
	public  void tearDown() throws Exception {
		super.tearDown();
		for(String configPath : _configJars) {
			tryDelete(Paths.get(configPath));
		}
	}

	private static void tryDelete(Path configPath) {
		try {
			Files.delete(configPath);
		}
		catch (IOException ex){
		}
	}

	@Override
	protected Configuration configuration() {
		return new CustomConfiguration(ConfigurationFactory.DEFAULT_RUNTIME_TYPE_NAME);
	}
	
	@Test
	public void returnsCustomConfiguration() {
		Sharpen.getDefault().configuration(configuration());
		Assert.assertSame(CustomConfiguration.class, Sharpen.getDefault().configuration().getClass());
	}

	@Test
	public void returnsNullIfExternalJarNotFound() throws Exception {
		Configuration config = ConfigurationFactory.newExternalConfiguration("AbsentConfigurationClass", null, _progressMonitor);
		Assert.assertNull(config);
	}

	@Test
	public void throwsExceptionIfClassNotExtendsConfiguration() throws Exception {
		try {
			// Configuration config =    : this suppress Eclipse warning
			ConfigurationFactory.newExternalConfiguration(WrongConfiguration.class.getName(), null, _progressMonitor);
			Assert.fail("Factory must throw exception");
		}
		catch (Exception ex){
			Assert.assertTrue(ex.getMessage().toLowerCase().contains("configuration class must extend sharpen.core.configuration"));
		}
	}

	@Test
	public void throwsExceptionIfClassHasNotPublicConstructor() {
		try {
			// Configuration config =    : this suppress Eclipse warning
			ConfigurationFactory.newExternalConfiguration(NoPublicConstructorConfiguration.class.getName(), null, _progressMonitor);
			Assert.fail("Factory must throw exception");
		}
		catch (Exception ex){
			Assert.assertTrue(ex.getMessage().toLowerCase().contains("configuration class constructor must have public modifier"));
		}
	}

	@Test
	public void createsRequiredConfigurationInstance() throws Exception {
		Configuration config = ConfigurationFactory.newExternalConfiguration(CustomConfiguration.class.getName(), null, _progressMonitor);
		Assert.assertTrue(config.getClass().getName().equals(CustomConfiguration.class.getName()));
	}
}

class WrongConfiguration {
}

class NoPublicConstructorConfiguration extends Configuration {

	protected NoPublicConstructorConfiguration(String runtimeTypeName) {
		super(runtimeTypeName);
	}

	@Override
	public boolean isIgnoredExceptionType(String exceptionType) {
		return false;
	}

	@Override
	public boolean mapByteToSbyte() {
		return false;
	}
}
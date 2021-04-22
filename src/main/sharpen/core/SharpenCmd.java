package sharpen.core;

/**
 * Main entry point 
 */
public class SharpenCmd {
	
	public static void main(String[] args) 
	{
		try {
			
			SharpenApplication AppCmd = new SharpenApplication();

			// ody:
			// In my opinion, the "-cp" is handled by java, and sharpen doesn't handle it correctly
			// So we use "-Dclsp=.../*" for multiple jars
			// actually we should use apache.common.CommandLineParser 
			// Let my know if I am wrong
			
			AppCmd.start(args);
		} catch (Exception ex) {
			System.out.println("Faied to run. Exception:" + ex.getMessage());
		}
	}	
}

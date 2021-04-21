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

			// for (String arg: args) System.out.println(arg);
			
//			String[] argscls = new String[args.length + 2];
//			System.arraycopy(args, 0, argscls, 0, args.length);
//			argscls[argscls.length - 2] = "-" + SharpenCommandLine.opt_cp;
//			argscls[argscls.length - 1] = System.getProperty("clsp");
//			AppCmd.start(argscls);
			AppCmd.start(args);
		} catch (Exception ex) {
			System.out.println("Faied to run. Exception:" + ex.getMessage());
		}
	}	
}

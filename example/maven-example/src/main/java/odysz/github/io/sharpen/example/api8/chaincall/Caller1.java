package odysz.github.io.sharpen.example.api8.chaincall;

public class Caller1 {
	public void Call1() {
		new Callee().f1();
	}

	/* later
	public Callee Call2() {
		return new Callee().f1();
	}
	*/
}

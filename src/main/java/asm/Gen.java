package asm;

import java.io.IOException;
import org.objectweb.asm.ClassReader;

public class Gen {

	public static void main(String[] args) throws IOException {
		ClassPrint classPrint = new ClassPrint();
		ClassReader cr = new ClassReader("java.lang.Runnable");
		cr.accept(classPrint, 0);
	}
}

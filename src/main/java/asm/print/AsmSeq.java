package asm.print;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class AsmSeq {
	public static void main(String[] args) throws IOException {
		ClassReader classReader = new ClassReader("asm.C");
		ClassWriter classWriter = new ClassWriter(0);
		PrintClassVisitor printClassVisitor = new PrintClassVisitor(classWriter);
		
		classReader.accept(printClassVisitor, 0);
		
	}
}

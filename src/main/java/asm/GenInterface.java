package asm;

import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import static org.objectweb.asm.Opcodes.*;

public class GenInterface {

	public byte[] gen() throws IOException {
		ClassWriter classWriter = new ClassWriter(0);
		classWriter.visit(V1_7, ACC_PUBLIC + ACC_INTERFACE + ACC_ABSTRACT, "asm/Comparable_Stub", null, "java/lang/Object",
				new String[] { "asm/A" });
		classWriter.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "LESS", "I", null, new Integer(-1)).visitEnd();
		classWriter.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "EQUAL", "I", null, new Integer(0)).visitEnd();
		classWriter.visitField(ACC_PUBLIC + ACC_STATIC + ACC_FINAL, "GREATER", "I", null, new Integer(1)).visitEnd();

		classWriter.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "CompareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
		
		classWriter.visitEnd();
		return classWriter.toByteArray();
	}
}

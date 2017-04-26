package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassPrint extends ClassVisitor {

	public ClassPrint() {
		super(Opcodes.ASM4);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		System.out.println(name + " extends " + superName + "{");
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		System.out.println("   " + name + desc);
		return null;
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		System.out.println("      " + desc + " " + name);
		return null;
	}

	@Override
	public void visitEnd() {
		System.out.println("}");
	}

}

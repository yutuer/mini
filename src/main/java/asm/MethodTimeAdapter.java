package asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodTimeAdapter extends MethodVisitor{

	private String owner;
	
	public MethodTimeAdapter(MethodVisitor paramMethodVisitor, String owner) {
		super(Opcodes.ASM4, paramMethodVisitor);
		this.owner = owner;
	}

	@Override
	public void visitCode() {
		mv.visitCode();
		mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
		mv.visitInsn(Opcodes.LSUB);
		mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
	}

	@Override
	public void visitInsn(int opcodes) {
		if((opcodes >=  Opcodes.IRETURN && opcodes <= Opcodes.RETURN) || opcodes == Opcodes.ATHROW){
			mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
			mv.visitInsn(Opcodes.LADD);
			mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
		}
		mv.visitInsn(opcodes);
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		mv.visitMaxs(maxStack + 4, maxLocals);
	}

}

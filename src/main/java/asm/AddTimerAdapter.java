package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AddTimerAdapter extends ClassVisitor {

	private String owner;
	private boolean isInterface;

	public AddTimerAdapter(ClassVisitor paramClassVisitor) {
		super(Opcodes.ASM4, paramClassVisitor);
	}

	@Override
	public void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3,
			String[] paramArrayOfString) {
		cv.visit(paramInt1, paramInt2, paramString1, paramString2, paramString3, paramArrayOfString);
		owner = paramString1;
		isInterface = (paramInt1 & Opcodes.ACC_INTERFACE) != 0;
	}

	@Override
	public MethodVisitor visitMethod(int paramInt, String paramString1, String paramString2, String paramString3,
			String[] paramArrayOfString) {
		MethodVisitor visitMethod = cv.visitMethod(paramInt, paramString1, paramString2, paramString3,
				paramArrayOfString);
		if(visitMethod != null && !isInterface && !paramString1.equals("<init>")){
			visitMethod = new MethodTimeAdapter(visitMethod, owner);
		}
		return visitMethod;
	}

	@Override
	public void visitEnd() {
		FieldVisitor visitField = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "timer", "J", null, new Long(0));
		if(visitField != null){
			visitField.visitEnd();
		}
		cv.visitEnd();
	}

	
	
}

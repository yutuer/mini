package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AddFieldAdapter extends ClassVisitor {

	private boolean isFind;
	private String fName;
	private String fDesc;
	private int fAcc;

	public AddFieldAdapter(ClassVisitor paramClassVisitor, int fAcc, String fName, String fDesc) {
		super(Opcodes.ASM4, paramClassVisitor);
		this.fAcc = fAcc;
		this.fName = fName;
		this.fDesc = fDesc;
	}

	@Override
	public FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3,
			Object paramObject) {
		if (paramString1.equals(fName)) {
			isFind = true;
		}
		return super.visitField(paramInt, paramString1, paramString2, paramString3, paramObject);
	}

	@Override
	public void visitEnd() {
		if (!isFind) {
			MethodVisitor visitMethod = cv.visitMethod(fAcc, fName, fDesc, null, null);
			if (visitMethod != null) {
				visitMethod.visitEnd();
			}
		}
		super.visitEnd();
	}

}

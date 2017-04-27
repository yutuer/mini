package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class RemoveDebugAdapter extends ClassVisitor {

	private String mName;
	private String mDesc;

	public RemoveDebugAdapter(ClassVisitor paramClassVisitor, String mName, String mDesc) {
		super(Opcodes.ASM4, paramClassVisitor);
		this.mName = mName;
		this.mDesc = mDesc;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		if (name.equals(mName) && desc.equals(mDesc)) {
			return null;
		}
		return cv.visitMethod(access, name, desc, signature, exceptions);
	}

}

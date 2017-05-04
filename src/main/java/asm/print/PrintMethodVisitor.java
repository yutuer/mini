package asm.print;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintMethodVisitor extends MethodVisitor{

	private static Logger log = LoggerFactory.getLogger(PrintMethodVisitor.class);
	
	public PrintMethodVisitor(MethodVisitor mv) {
		super(Opcodes.ASM4, mv);
	}

	@Override
	public void visitParameter(String paramString, int paramInt) {
		// TODO Auto-generated method stub
		super.visitParameter(paramString, paramInt);
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		// TODO Auto-generated method stub
		return super.visitAnnotationDefault();
	}

	@Override
	public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
		// TODO Auto-generated method stub
		return super.visitAnnotation(paramString, paramBoolean);
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString,
			boolean paramBoolean) {
		// TODO Auto-generated method stub
		return super.visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int paramInt, String paramString, boolean paramBoolean) {
		// TODO Auto-generated method stub
		return super.visitParameterAnnotation(paramInt, paramString, paramBoolean);
	}

	@Override
	public void visitAttribute(Attribute paramAttribute) {
		// TODO Auto-generated method stub
		super.visitAttribute(paramAttribute);
	}

	@Override
	public void visitCode() {
		// TODO Auto-generated method stub
		super.visitCode();
	}

	@Override
	public void visitFrame(int paramInt1, int paramInt2, Object[] paramArrayOfObject1, int paramInt3,
			Object[] paramArrayOfObject2) {
		// TODO Auto-generated method stub
		super.visitFrame(paramInt1, paramInt2, paramArrayOfObject1, paramInt3, paramArrayOfObject2);
	}

	@Override
	public void visitInsn(int paramInt) {
		log.info("visitInsn ----- paramInt:{}", paramInt);
		super.visitInsn(paramInt);
	}

	@Override
	public void visitIntInsn(int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		super.visitIntInsn(paramInt1, paramInt2);
	}

	@Override
	public void visitVarInsn(int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		super.visitVarInsn(paramInt1, paramInt2);
	}

	@Override
	public void visitTypeInsn(int paramInt, String paramString) {
		// TODO Auto-generated method stub
		super.visitTypeInsn(paramInt, paramString);
	}

	@Override
	public void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		super.visitFieldInsn(paramInt, paramString1, paramString2, paramString3);
	}

	@Override
	public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		super.visitMethodInsn(paramInt, paramString1, paramString2, paramString3);
	}

	@Override
	public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3,
			boolean paramBoolean) {
		// TODO Auto-generated method stub
		super.visitMethodInsn(paramInt, paramString1, paramString2, paramString3, paramBoolean);
	}

	@Override
	public void visitInvokeDynamicInsn(String paramString1, String paramString2, Handle paramHandle,
			Object... paramArrayOfObject) {
		// TODO Auto-generated method stub
		super.visitInvokeDynamicInsn(paramString1, paramString2, paramHandle, paramArrayOfObject);
	}

	@Override
	public void visitJumpInsn(int paramInt, Label paramLabel) {
		// TODO Auto-generated method stub
		super.visitJumpInsn(paramInt, paramLabel);
	}

	@Override
	public void visitLabel(Label paramLabel) {
		// TODO Auto-generated method stub
		super.visitLabel(paramLabel);
	}

	@Override
	public void visitLdcInsn(Object paramObject) {
		// TODO Auto-generated method stub
		super.visitLdcInsn(paramObject);
	}

	@Override
	public void visitIincInsn(int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		super.visitIincInsn(paramInt1, paramInt2);
	}

	@Override
	public void visitTableSwitchInsn(int paramInt1, int paramInt2, Label paramLabel, Label... paramArrayOfLabel) {
		// TODO Auto-generated method stub
		super.visitTableSwitchInsn(paramInt1, paramInt2, paramLabel, paramArrayOfLabel);
	}

	@Override
	public void visitLookupSwitchInsn(Label paramLabel, int[] paramArrayOfInt, Label[] paramArrayOfLabel) {
		// TODO Auto-generated method stub
		super.visitLookupSwitchInsn(paramLabel, paramArrayOfInt, paramArrayOfLabel);
	}

	@Override
	public void visitMultiANewArrayInsn(String paramString, int paramInt) {
		// TODO Auto-generated method stub
		super.visitMultiANewArrayInsn(paramString, paramInt);
	}

	@Override
	public AnnotationVisitor visitInsnAnnotation(int paramInt, TypePath paramTypePath, String paramString,
			boolean paramBoolean) {
		// TODO Auto-generated method stub
		return super.visitInsnAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
	}

	@Override
	public void visitTryCatchBlock(Label paramLabel1, Label paramLabel2, Label paramLabel3, String paramString) {
		// TODO Auto-generated method stub
		super.visitTryCatchBlock(paramLabel1, paramLabel2, paramLabel3, paramString);
	}

	@Override
	public AnnotationVisitor visitTryCatchAnnotation(int paramInt, TypePath paramTypePath, String paramString,
			boolean paramBoolean) {
		// TODO Auto-generated method stub
		return super.visitTryCatchAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
	}

	@Override
	public void visitLocalVariable(String paramString1, String paramString2, String paramString3, Label paramLabel1,
			Label paramLabel2, int paramInt) {
		// TODO Auto-generated method stub
		super.visitLocalVariable(paramString1, paramString2, paramString3, paramLabel1, paramLabel2, paramInt);
	}

	@Override
	public AnnotationVisitor visitLocalVariableAnnotation(int paramInt, TypePath paramTypePath,
			Label[] paramArrayOfLabel1, Label[] paramArrayOfLabel2, int[] paramArrayOfInt, String paramString,
			boolean paramBoolean) {
		// TODO Auto-generated method stub
		return super.visitLocalVariableAnnotation(paramInt, paramTypePath, paramArrayOfLabel1, paramArrayOfLabel2,
				paramArrayOfInt, paramString, paramBoolean);
	}

	@Override
	public void visitLineNumber(int paramInt, Label paramLabel) {
		// TODO Auto-generated method stub
		super.visitLineNumber(paramInt, paramLabel);
	}

	@Override
	public void visitMaxs(int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		super.visitMaxs(paramInt1, paramInt2);
	}

	@Override
	public void visitEnd() {
		super.visitEnd();
	}

}

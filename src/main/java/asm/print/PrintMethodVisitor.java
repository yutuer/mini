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

public class PrintMethodVisitor extends MethodVisitor {

	private static Logger log = LoggerFactory.getLogger(PrintMethodVisitor.class);

	public static void info(String format, Object... arguments) {
		log.info("METHOD:    " + format, arguments);
	}

	public PrintMethodVisitor(MethodVisitor mv) {
		super(Opcodes.ASM4, mv);
	}

	@Override
	public void visitParameter(String paramString, int paramInt) {
		info("visitParameter ----- paramString:{},paramInt:{}", paramString, paramInt);
		super.visitParameter(paramString, paramInt);
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		info("visitAnnotationDefault ----- ");
		return super.visitAnnotationDefault();
	}

	@Override
	public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
		info("visitAnnotationDefault ----- paramString:{},paramBoolean:{}", paramString, paramBoolean);
		return super.visitAnnotation(paramString, paramBoolean);
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString,
			boolean paramBoolean) {
		info("visitTypeAnnotation ----- paramInt:{},paramTypePath:{},paramString:{},paramBoolean:{}", paramInt,
				paramTypePath, paramString, paramBoolean);
		return super.visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int paramInt, String paramString, boolean paramBoolean) {
		info("visitParameterAnnotation ----- paramInt:{},paramString:{},paramBoolean:{}", paramInt, paramString,
				paramBoolean);
		return super.visitParameterAnnotation(paramInt, paramString, paramBoolean);
	}

	@Override
	public void visitAttribute(Attribute paramAttribute) {
		info("visitAttribute ----- paramAttribute:{}", paramAttribute);
		super.visitAttribute(paramAttribute);
	}

	@Override
	public void visitCode() {
		info("visitCode ----- ");
		super.visitCode();
	}

	@Override
	public void visitFrame(int paramInt1, int paramInt2, Object[] paramArrayOfObject1, int paramInt3,
			Object[] paramArrayOfObject2) {
		info("visitFrame ----- paramInt1:{},paramInt2:{},paramArrayOfObject1:{},paramInt3:{},paramArrayOfObject2:{}",
				paramInt1, paramInt2, paramArrayOfObject1, paramInt3, paramArrayOfObject2);
		super.visitFrame(paramInt1, paramInt2, paramArrayOfObject1, paramInt3, paramArrayOfObject2);
	}

	@Override
	public void visitInsn(int paramInt) {
		info("visitInsn ----- paramInt:{}", paramInt);
		super.visitInsn(paramInt);
	}

	@Override
	public void visitIntInsn(int paramInt1, int paramInt2) {
		info("visitIntInsn ----- paramInt1:{},paramInt2:{}", paramInt1, paramInt2);
		super.visitIntInsn(paramInt1, paramInt2);
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		info("visitVarInsn ----- opcode:{},var:{}", opcode, var);
		super.visitVarInsn(opcode, var);
	}

	@Override
	public void visitTypeInsn(int paramInt, String paramString) {
		info("visitTypeInsn ----- paramInt:{},paramString:{}", paramInt, paramString);
		super.visitTypeInsn(paramInt, paramString);
	}

	@Override
	public void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3) {
		info("visitFieldInsn ----- paramInt:{},paramString1:{},paramString2:{},paramString3:{}", paramInt,
				paramString1, paramString2, paramString3);
		super.visitFieldInsn(paramInt, paramString1, paramString2, paramString3);
	}

	@Override
	public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3) {
		info("visitMethodInsn ----- paramInt:{},paramString1:{},paramString2:{},paramString3:{}", paramInt,
				paramString1, paramString2, paramString3);
		super.visitMethodInsn(paramInt, paramString1, paramString2, paramString3);
	}

	@Override
	public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3,
			boolean paramBoolean) {
		info("visitMethodInsn ----- paramInt:{},paramString1:{},paramString2:{},paramString3:{}, paramBoolean:{}",
				paramInt, paramString1, paramString2, paramString3, paramBoolean);
		super.visitMethodInsn(paramInt, paramString1, paramString2, paramString3, paramBoolean);
	}

	@Override
	public void visitInvokeDynamicInsn(String paramString1, String paramString2, Handle paramHandle,
			Object... paramArrayOfObject) {
		info("visitInvokeDynamicInsn ----- paramString1:{},paramString2:{},paramHandle:{},paramArrayOfObject:{}",
				paramString1, paramString2, paramHandle, paramArrayOfObject);
		super.visitInvokeDynamicInsn(paramString1, paramString2, paramHandle, paramArrayOfObject);
	}

	@Override
	public void visitJumpInsn(int paramInt, Label paramLabel) {
		info("visitJumpInsn ----- paramInt:{},paramLabel:{}", paramInt, paramLabel);
		super.visitJumpInsn(paramInt, paramLabel);
	}

	@Override
	public void visitLabel(Label label) {
		info("visitLabel ----- label:{}", label);
		super.visitLabel(label);
	}

	@Override
	public void visitLdcInsn(Object paramObject) {
		info("visitLdcInsn ----- paramObject:{}", paramObject);
		super.visitLdcInsn(paramObject);
	}

	@Override
	public void visitIincInsn(int paramInt1, int paramInt2) {
		info("visitIincInsn ----- paramInt1:{},paramInt2:{}", paramInt1, paramInt2);
		super.visitIincInsn(paramInt1, paramInt2);
	}

	@Override
	public void visitTableSwitchInsn(int paramInt1, int paramInt2, Label paramLabel, Label... paramArrayOfLabel) {
		info("visitTableSwitchInsn ----- paramInt1:{},paramInt2:{},paramLabel:{}, paramArrayOfLabel:{}", paramInt1,
				paramInt2, paramLabel, paramArrayOfLabel);
		super.visitTableSwitchInsn(paramInt1, paramInt2, paramLabel, paramArrayOfLabel);
	}

	@Override
	public void visitLookupSwitchInsn(Label paramLabel, int[] paramArrayOfInt, Label[] paramArrayOfLabel) {
		info("visitLookupSwitchInsn ----- paramLabel:{}, paramArrayOfInt:{}, paramArrayOfLabel:{}", paramLabel,
				paramArrayOfInt, paramArrayOfLabel);
		super.visitLookupSwitchInsn(paramLabel, paramArrayOfInt, paramArrayOfLabel);
	}

	@Override
	public void visitMultiANewArrayInsn(String paramString, int paramInt) {
		info("visitMultiANewArrayInsn ----- paramString:{}, paramInt:{}", paramString, paramInt);
		super.visitMultiANewArrayInsn(paramString, paramInt);
	}

	@Override
	public AnnotationVisitor visitInsnAnnotation(int paramInt, TypePath paramTypePath, String paramString,
			boolean paramBoolean) {
		info("visitInsnAnnotation ----- paramInt:{},paramTypePath:{},paramString:{},paramBoolean:{}", paramInt,
				paramTypePath, paramString, paramBoolean);
		return super.visitInsnAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
	}

	@Override
	public void visitTryCatchBlock(Label paramLabel1, Label paramLabel2, Label paramLabel3, String paramString) {
		info("visitTryCatchBlock ----- paramLabel1:{},paramLabel2:{},paramLabel3:{},paramString:{}", paramLabel1,
				paramLabel2, paramLabel3, paramString);
		super.visitTryCatchBlock(paramLabel1, paramLabel2, paramLabel3, paramString);
	}

	@Override
	public AnnotationVisitor visitTryCatchAnnotation(int paramInt, TypePath paramTypePath, String paramString,
			boolean paramBoolean) {
		info("visitTryCatchAnnotation ----- paramInt:{},paramTypePath:{},paramString:{},paramBoolean:{}", paramInt,
				paramTypePath, paramString, paramBoolean);
		return super.visitTryCatchAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
	}

	@Override
	public void visitLocalVariable(String paramString1, String paramString2, String paramString3, Label paramLabel1,
			Label paramLabel2, int paramInt) {
		info("visitLocalVariable ----- paramString1:{},paramString2:{},paramString3:{},paramLabel1:{},paramLabel2:{},paramInt:{}",
				paramString1, paramString2, paramString3, paramLabel1, paramLabel2, paramInt);
		super.visitLocalVariable(paramString1, paramString2, paramString3, paramLabel1, paramLabel2, paramInt);
	}

	@Override
	public AnnotationVisitor visitLocalVariableAnnotation(int paramInt, TypePath paramTypePath,
			Label[] paramArrayOfLabel1, Label[] paramArrayOfLabel2, int[] paramArrayOfInt, String paramString,
			boolean paramBoolean) {
		info("visitLocalVariableAnnotation ----- paramInt:{}, paramTypePath:{}, paramArrayOfLabel1:{}, paramArrayOfLabel2:{},paramArrayOfInt:{}, paramString:{}, paramBoolean:{}",
				paramInt, paramTypePath, paramArrayOfLabel1, paramArrayOfLabel2, paramArrayOfInt, paramString,
				paramBoolean);
		return super.visitLocalVariableAnnotation(paramInt, paramTypePath, paramArrayOfLabel1, paramArrayOfLabel2,
				paramArrayOfInt, paramString, paramBoolean);
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		info("visitLineNumber ----- line:{}, start:{}", line, start);
		super.visitLineNumber(line, start);
	}

	@Override
	public void visitMaxs(int paramInt1, int paramInt2) {
		info("visitMaxs ----- paramInt1:{}, paramInt2:{}", paramInt1, paramInt2);
		super.visitMaxs(paramInt1, paramInt2);
	}

	@Override
	public void visitEnd() {
		info("visitEnd ----- ");
		super.visitEnd();
	}

}

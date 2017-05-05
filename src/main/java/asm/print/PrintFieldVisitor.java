package asm.print;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintFieldVisitor extends FieldVisitor {

	private static Logger log = LoggerFactory.getLogger(PrintMethodVisitor.class);

	public static void info(String format, Object... arguments) {
		log.info("FIELD:    " + format, arguments);
	}

	public PrintFieldVisitor(FieldVisitor paramFieldVisitor) {
		super(Opcodes.ASM4, paramFieldVisitor);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
		info("visitAnnotation ----- paramString:{}, paramBoolean:{}", paramString, paramBoolean);
		return super.visitAnnotation(paramString, paramBoolean);
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString,
			boolean paramBoolean) {
		info("visitTypeAnnotation ----- paramInt:{}, paramTypePath:{}, paramString:{}, paramBoolean:{}", paramInt,
				paramTypePath, paramString, paramBoolean);
		return super.visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
	}

	@Override
	public void visitAttribute(Attribute paramAttribute) {
		info("visitAttribute ----- paramAttribute:{}", paramAttribute);
		super.visitAttribute(paramAttribute);
	}

	@Override
	public void visitEnd() {
		info("visitEnd ----- ");
		super.visitEnd();
	}

}

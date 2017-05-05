package asm.print;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintClassVisitor extends ClassVisitor{

	private static Logger log = LoggerFactory.getLogger(PrintClassVisitor.class);
	
	public static void info(String format, Object... arguments){
		log.info("CLASS:    " + format, arguments);
	}
	
	public PrintClassVisitor(ClassVisitor cv) {
		super(Opcodes.ASM4, cv);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		info("visit ---- version:{},access:{},name:{},signature:{},superName:{},interfaces:{}", version, access, name, signature, superName, interfaces);
		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		info("visitAnnotation ----- desc:{},visible:{}", desc, visible);
		return super.visitAnnotation(desc, visible);
	}

	@Override
	public void visitAttribute(Attribute attr) {
		info("visitAttribute ----- attr:{}", attr);
		super.visitAttribute(attr);
	}

	@Override
	public void visitEnd() {
		info("visitEnd ----- ");
		super.visitEnd();
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		info("visitField ------ access:{},name:{},desc:{},signature:{},value:{}",access, name, desc, signature, value);
		FieldVisitor visitField = super.visitField(access, name, desc, signature, value);
		return new PrintFieldVisitor(visitField);
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {
		info("visitInnerClass ----- name:{},outerName:{},innerName:{},access:{}", name, outerName, innerName, access);
		super.visitInnerClass(name, outerName, innerName, access);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		info("visitMethod ----- access:{},name:{},desc:{},signature:{},exceptions:{}", access, name, desc, signature, exceptions);
		return new PrintMethodVisitor(cv.visitMethod(access, name, desc, signature, exceptions));
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {
		info("visitOuterClass ----- owner:{},name:{},desc:{}");
		super.visitOuterClass(owner, name, desc);
	}

	@Override
	public void visitSource(String source, String debug) {
		info("visitSource ----- source:{},debug:{}");
		super.visitSource(source, debug);
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
		info("visitTypeAnnotation ----- typeRef:{},typePath:{},desc:{},visible:{}");
		return super.visitTypeAnnotation(typeRef, typePath, desc, visible);
	}
}

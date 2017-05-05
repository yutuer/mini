package asm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URL;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;


public class Gen {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		{
			ClassPrint classPrint = new ClassPrint();
			ClassReader cr = new ClassReader("java.lang.Runnable");
			cr.accept(classPrint, 0);
		}
		{
			Class<?> c = new ByteClassLoader().loadClass("asm.Comparable_Stub");
			for(Field f:c.getDeclaredFields()){
				System.out.println(f.getName());
			}
		}
		{	
			byte[] b = new GenInterface().gen();
			URL resource = ClassLoader.getSystemClassLoader().getResource(Gen.class.getName().replaceAll("\\.", "/").concat(".class"));
			String path = resource.getPath();
			File f = new File(path.replace(Gen.class.getSimpleName(), "Comparable_Stub"));
			if(!f.exists()){
				f.createNewFile();
			}
			try(FileOutputStream fops = new FileOutputStream(f)){
				fops.write(b);
			}
			
			ClassWriter classWriter = new ClassWriter(0);
			TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter,new PrintWriter(System.out));
			CheckClassAdapter checkClassAdapter = new CheckClassAdapter(traceClassVisitor);
			ClassReader classReader = new ClassReader(b);
			ChangeVersionAdapter changeVersionAdapter = new ChangeVersionAdapter(Opcodes.ASM4, checkClassAdapter);
			classReader.accept(changeVersionAdapter, 0);
			byte[] b2 = classWriter.toByteArray();
		}
		{
			byte[] b = new GenInterface().gen();
			ClassWriter classWriter = new ClassWriter(0);
			TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter,new PrintWriter(System.out));
			CheckClassAdapter checkClassAdapter = new CheckClassAdapter(traceClassVisitor);
			ClassReader classReader = new ClassReader(b);
			RemoveDebugAdapter removeDebugAdapter = new RemoveDebugAdapter(checkClassAdapter, "CompareTo", "(Ljava/lang/Object;)I");
			classReader.accept(removeDebugAdapter, 0);
			byte[] b2 = classWriter.toByteArray();
			
		}
		{
			Type[] argumentTypes = Type.getArgumentTypes("([I)V");
			for (int i = 0; i < argumentTypes.length; i++) {
				Type type = argumentTypes[i];
				System.out.println(type);
			}
		}
	}
}

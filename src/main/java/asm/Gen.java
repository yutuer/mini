package asm;

import java.io.IOException;
import java.lang.reflect.Field;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


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
			ClassWriter classWriter = new ClassWriter(0);
			ClassReader classReader = new ClassReader(b);
			ChangeVersionAdapter changeVersionAdapter = new ChangeVersionAdapter(Opcodes.ASM4, classWriter);
			classReader.accept(changeVersionAdapter, 0);
			byte[] b2 = classWriter.toByteArray();
		}
		{
			byte[] b = new GenInterface().gen();
			ClassWriter classWriter = new ClassWriter(0);
			ClassReader classReader = new ClassReader(b);
			RemoveDebugAdapter removeDebugAdapter = new RemoveDebugAdapter(classWriter, "CompareTo", "(Ljava/lang/Object;)I");
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

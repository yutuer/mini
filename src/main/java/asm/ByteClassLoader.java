package asm;

import java.io.IOException;

public class ByteClassLoader extends ClassLoader{

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		if(name.endsWith("_Stub")){
			try {
				byte[] b = new GenInterface().gen();
				return defineClass(name, b, 0, b.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.findClass(name);
	}

	
	
}

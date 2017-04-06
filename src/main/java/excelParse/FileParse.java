package excelParse;

import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class FileParse extends AbstractParse {

	protected final String fileName;
	
	private InputStream is;
	
	private final ClassLoader cl;

	public FileParse(String fileName) throws FileNotFoundException {
		this(fileName, null);
	}

	public FileParse(String fileName, ClassLoader cl) {
		super();
		this.fileName = fileName;
		this.cl = cl;
	}

	public InputStream getInputStream() {
		if(is == null){
			if(cl == null){
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			}else{
				is = cl.getResourceAsStream(fileName);
			}
		}
		return is;
	}
}

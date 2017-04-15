package excelParse;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.badperson.util.LoadResource;

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
				is = LoadResource.getResourceAsStream(fileName);
			}else{
				is = cl.getResourceAsStream(fileName);
			}
		}
		return is;
	}
}

package excelParse;

import org.apache.log4j.Logger;

import com.badperson.interfaces.IParse;

public abstract class AbstractParse implements IParse {

	public static final Logger logger = Logger.getLogger(AbstractParse.class);

	public AbstractParse(){
		super();
	}

}

package excelParse;

import org.apache.log4j.Logger;

public abstract class AbstractParse<V> implements IParse<V> {

	public static final Logger logger = Logger.getLogger(AbstractParse.class);

	public AbstractParse(){
		super();
	}

}

package excelParse;

public class LongquanGroupPair<FIRST extends Comparable<FIRST>> extends Pair<FIRST, FIRST> implements Comparable<LongquanGroupPair<FIRST>>{

	public LongquanGroupPair(FIRST first, FIRST second) {
		super(first, second);
	}

	public void fill(FIRST rowNum) {
		if(rowNum.compareTo(getFirst()) < 0){
			setFirst(rowNum);
		}
		
		if(rowNum.compareTo(getSecond()) > 0){
			setSecond(rowNum);
		}
	}

	@Override
	public int compareTo(LongquanGroupPair<FIRST> o) {
		return getFirst().compareTo(o.getFirst());
	}

}

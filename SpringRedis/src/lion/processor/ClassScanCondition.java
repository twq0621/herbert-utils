package lion.processor;
import java.util.List;


public class ClassScanCondition {

	/**
	 * 是否排除内部类 true->是 false->否
	 */
	private boolean excludeInner = true;
	/**
	 * 过滤规则适用情况 true—>搜索符合规则的 false->排除符合规则的
	 */
	private boolean checkInOrEx = true;

	/**
	 * 过滤规则列表 如果是null或者空，即全部符合不过滤
	 */
	private List<String> classFilters = null;
	
	/**
	 * 判断是否是该类的子类
	 */
	@SuppressWarnings("rawtypes")
	private Class extendClass[];

	/**
	 * @return the excludeInner
	 */
	public boolean isExcludeInner() {
		return excludeInner;
	}

	/**
	 * @return the checkInOrEx
	 */
	public boolean isCheckInOrEx() {
		return checkInOrEx;
	}

	/**
	 * @return the classFilters
	 */
	public List<String> getClassFilters() {
		return classFilters;
	}

	/**
	 * @param pExcludeInner
	 *            the excludeInner to set
	 */
	public void setExcludeInner(boolean pExcludeInner) {
		excludeInner = pExcludeInner;
	}

	/**
	 * @param pCheckInOrEx
	 *            the checkInOrEx to set
	 */
	public void setCheckInOrEx(boolean pCheckInOrEx) {
		checkInOrEx = pCheckInOrEx;
	}

	/**
	 * @param pClassFilters
	 *            the classFilters to set
	 */
	public void setClassFilters(List<String> pClassFilters) {
		classFilters = pClassFilters;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getExtendClass() {
		return extendClass;
	}

	@SuppressWarnings("rawtypes")
	public void setExtendClass(Class[] extendClass) {
		this.extendClass = extendClass;
	}

}

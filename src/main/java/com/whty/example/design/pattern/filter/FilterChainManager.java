/**
 * 
 */
package com.whty.example.design.pattern.filter;

/**
 * <p>
 * Title:FilterChainManager
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月15日 上午10:34:16
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class FilterChainManager {

	private FilterChain filterChain;

	/**
	 * @param target
	 */
	public FilterChainManager(Target target) {
		super();
		filterChain = new FilterChain();
		filterChain.setTarget(target);
	}

	public void addFilter(Filter filter) {
		filterChain.addFilter(filter);
	}

	public void filterRequest(Object request) {
		filterChain.execute(request);
	}
}

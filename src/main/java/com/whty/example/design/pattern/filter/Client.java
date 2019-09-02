/**
 * 
 */
package com.whty.example.design.pattern.filter;

/**
 * <p>
 * Title:Client
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月15日 上午10:41:48
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class Client {

	private FilterChainManager filterChainManager;

	public FilterChainManager getFilterChainManager() {
		return filterChainManager;
	}

	public void setFilterChainManager(FilterChainManager filterChainManager) {
		this.filterChainManager = filterChainManager;
	}

	public void sendRequest(Object request) {
		if (filterChainManager == null) {
			return;
		}
		filterChainManager.filterRequest(request);
	}
}

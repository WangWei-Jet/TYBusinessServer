/**
 * 
 */
package com.whty.example.design.pattern.filter;

/**
 * <p>
 * Title:FilterChainDemo
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月15日 上午10:43:39
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class FilterChainDemo {
	public static void main(String[] args) {
		Target target = new Target();
		FilterChainManager filterChainManager = new FilterChainManager(target);
		filterChainManager.addFilter(new EncodeFilter());
		filterChainManager.addFilter(new MessageFilter());

		Client client = new Client();
		client.setFilterChainManager(filterChainManager);
		client.sendRequest("8583报文请求");
	}
}

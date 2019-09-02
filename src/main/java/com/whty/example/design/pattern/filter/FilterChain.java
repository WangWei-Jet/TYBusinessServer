/**
 * 
 */
package com.whty.example.design.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:FilterChain
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月15日 上午10:23:53
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class FilterChain {
	private List<Filter> filterList = new ArrayList<>();

	private Target target;

	public FilterChain() {
		// do nothing
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public void addFilter(Filter filter) {
		if (filter == null) {
			return;
		}
		filterList.add(filter);
	}

	public void execute(Object request) {
		// 过滤器先过滤请求
		if (!filterList.isEmpty()) {
			for (Filter filter : filterList) {
				filter.doFilterWork(request);
			}
		}
		if (target == null) {
			return;
		}
		target.doWork(request);
	}
}

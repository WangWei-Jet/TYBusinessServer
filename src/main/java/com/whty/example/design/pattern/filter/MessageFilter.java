/**
 * 
 */
package com.whty.example.design.pattern.filter;

/**
 * <p>
 * Title:MessageFilter
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年3月15日 上午10:23:35
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class MessageFilter implements Filter {

	/* (non-Javadoc)
	 * @see com.whty.example.design.pattern.filter.Filter#doFilterWork(java.lang.Object)
	 */
	@Override
	public void doFilterWork(Object request) {
		System.out.println(this.getClass().getSimpleName() + "=>request:" + request.toString());
		System.out.println(this.getClass().getSimpleName() + "=>do filter work");
		System.out.println(this.getClass().getSimpleName() + "=>finish filter work");
	}

}

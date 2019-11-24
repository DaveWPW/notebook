package common.util;

import com.dave.notebook.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * Shiro工具类
 * 
 * @author Dave20191011
 *
 */
public class ShiroUtil {
	
	public static User getCurrentUser() {
		return (User)SecurityUtils.getSubject().getPrincipal();
	}
	
}

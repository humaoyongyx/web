package issac.demo.filter;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import issac.demo.model.PermissionBean;
import issac.demo.model.UserBean;
import issac.demo.utils.CommonUtils;

public class SystemUserFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

		String requestURI = getPathWithinApplication(request);
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		
		if (user.getRoleId().contains("1")) {
			return true;
		}
		Object object = session.getAttribute("permission");
		if (object != null) {
			List<PermissionBean> permissionBeans = (List<PermissionBean>) object;
			for (PermissionBean permissionBean : permissionBeans) {
				String moduleUrl = permissionBean.getModuleUrl();
				String actionRegUrl = permissionBean.getActionUrl();
				if (requestURI.equals(moduleUrl)) {
					return true;
				} else {
					if (moduleUrl.contains(CommonUtils.getUrlPath(requestURI))) {
						String urlAction = CommonUtils.getUrlAction(requestURI);
						if (!urlAction.trim().equals("")) {
							return urlAction.matches(actionRegUrl);
						}
					}
				}
		
			}
		}
		
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		WebUtils.issueRedirect(request, response, "/unauthorized");
		return false;
	}


}

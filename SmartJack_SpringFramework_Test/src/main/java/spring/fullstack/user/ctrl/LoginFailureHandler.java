package spring.fullstack.user.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import spring.fullstack.user.model.UserIService;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	private String loginidname;
	private String loginpwdname;
	private String errormsgname;
	private String defaultFailureUrl;
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("▒▒▒▒▒▒▒▒▒▒로그인실패핸들러▒▒▒▒▒▒▒▒▒▒");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("▒▒▒▒▒▒▒▒▒▒로그인실패핸들러▒▒▒▒▒▒▒▒▒▒ : 입력아이디>"+id+"/ 비밀번호>"+pw);
		String errormsg = null;
		
		if (exception instanceof BadCredentialsException) { // 비밀번호 불일치
			errormsg = "  아이디 혹은 비밀번호가 일치하지 않습니다.";
		} else if (exception instanceof InternalAuthenticationServiceException) { // 존재하지 않는 아이디
			errormsg = "  아이디 혹은 비밀번호가 일치하지 않습니다.";
		}

		request.setAttribute(loginidname, id);
		request.setAttribute(loginpwdname, pw);
		request.setAttribute(errormsgname, errormsg);
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);

	}
	public String getLoginidname() {
		return loginidname;
	}

	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}

	public String getLoginpwdname() {
		return loginpwdname;
	}

	public void setLoginpwdname(String loginpwdname) {
		this.loginpwdname = loginpwdname;
	}

	public String getErrormsgname() {
		return errormsgname;
	}

	public void setErrormsgname(String errormsgname) {
		this.errormsgname = errormsgname;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}

}

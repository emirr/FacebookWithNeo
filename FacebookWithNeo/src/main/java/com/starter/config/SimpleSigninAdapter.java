//package com.starter.config;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.security.web.WebAttributes;
//import org.springframework.security.web.savedrequest.RequestCache;
//import org.springframework.security.web.savedrequest.SavedRequest;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.web.SignInAdapter;
//import org.springframework.web.context.request.NativeWebRequest;
//
//public class SimpleSigninAdapter implements SignInAdapter {
//
//	private  RequestCache requestCache;
//
//	@Inject
//	public SimpleSigninAdapter(RequestCache requestCache) {
//		this.requestCache = requestCache;
//	}
//	
//	@Override
//	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
//		SigninUtils.signin(localUserId);
//		return extractOriginalUrl(request);
//	}
//
//	private String extractOriginalUrl(NativeWebRequest request) {
//		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
//		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
//		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
//		if (saved == null) {
//			return null;
//		}
//		requestCache.removeRequest(nativeReq, nativeRes);
//		removeAutheticationAttributes(nativeReq.getSession(false));
//		return saved.getRedirectUrl();
//	}
//		 
//	private void removeAutheticationAttributes(HttpSession session) {
//		if (session == null) {
//			return;
//		}
//		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//	}
//
//}

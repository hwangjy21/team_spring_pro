package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootConfig.class , SecurityConfig.class}; //시큐리티컨피그.class 추가해야관련처리 가능
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// encoding filter 설정
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8"); // 내부로 들어오는 데이터도 인코딩 설정(요청 시 request)
		encodingFilter.setForceEncoding(true); // 외부로 나가는 데이터도 인코딩 설정(응답 시 response)
		return new Filter[] { encodingFilter };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registeration) {
		/* 그 외 기타 사용자 설정 */

		// 사용자 지정 익셉션 설정을 할 것인지 처리 404기본페이지를 좀 이쁘게 해주려고 true처리
		registeration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
		
		/*파일 업로드 설정*/
		//경로, maxFileSize, maxPeqSize, fileSizeThreshold

		String uploadLocation = "C:\\_myweb\\_java\\fileUpload";//로컬용 231118 (webapps)
//		String uploadLocation = "/aj2002/tomcat/webapps/_javaweb/_java/fileUpload/"; //카페24용 231118

		int maxFileSize = 1024*1024*20; // 20MB
		int maxReqSize = maxFileSize*2; // 40MB
		int fileSizeThreshold = maxFileSize; //20MB
		
		MultipartConfigElement multipartConfig = 
				new MultipartConfigElement(uploadLocation, maxFileSize, maxReqSize, fileSizeThreshold);
		
		registeration.setMultipartConfig(multipartConfig);
		
		
	}
}

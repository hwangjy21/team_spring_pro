package com.myweb.www.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.myweb.www.service.ChatService;
import com.myweb.www.domain.FileVO;
import com.myweb.www.service.MemberService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	//231030
	
	@Getter
	@Setter
	private String authEmail; //만들고자 하는 곳의 멤버변수만
	
	@Getter
	@Setter
	private String authUrl;
	
	//redirect로 데이터를 가져가는 역할(리다이렉트 스트레터지)
	private RedirectStrategy rdstg = new  DefaultRedirectStrategy();
	//실제로그인정보 경로 등을 저장
	private RequestCache reqCache = new HttpSessionRequestCache();
	
	@Inject
	private MemberService msv;
	
	@Inject
	private ChatService chatsv; //231223전경환추가
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		setAuthEmail(authentication.getName()); //여기오면 이미 로그인 성공했다는뜻
		setAuthUrl("/common/main");
		
		//쭈욱 메퍼.xml까지 갔따옴

		//내부에서 로그인 세션 저장됨.
		HttpSession ses = request.getSession();
		log.info("LoginSuccess >>> ses >>> " + ses.toString() );

		//시큐리티에서 로그인 값이 없다면 null로 저장될 수(도) 있음.
		/*if(!isOk || ses == null) {
			return;
		}else {
			// 값이 잘 저장됬다는 뜻
			// 시큐리티에서 로그인을 시도하면 (여러번하면 여러번의) 시도한 기록 남게됨
			// 이전 시도한 시큐리티의 로그인 인증 실패 기록 삭제 
			//ex (5번이상 실패다가 1번 성공하면 삭제해야 나중에 5회이상 실패등에서 롹걸때 계산하기 편할듯)
			ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);			
		}*/
		SavedRequest saveReq = reqCache.getRequest(request, response);
		//로그인 안하고 이것저것 클릭해서 볼때 관련 클릭했던 경로페이지가 있냐없냐 등을보는듯
		// 처음이면 리스트를 넣고  이것저거것 있다면 로그인후 그페이지로 보내려고 getRedirectUrl가 있는듯 (로그인 값 이나 리스트 값으로 데이터를 보내줌)
		rdstg.sendRedirect(request, response, (saveReq != null)? saveReq.getRedirectUrl() : getAuthUrl()  );
		
		//231216전경환추가------S
//	    HttpSession session = request.getSession();
	    if (authentication != null) {
	        String username = authentication.getName();

	        // 사용자의 프로필 이미지 정보 가져오기
	        FileVO profileImage = msv.getFile(username);
	        if (profileImage != null) {
	            // 이미지 URL 생성
	            String profileImageUrl = 
	            						"http://localhost:8088/upload/" + //로컬용
//	            						"http://aj2002.cafe24.com/_javaweb/_java/fileUpload/" + //카페24배포용webapps
	            						
	                                      profileImage.getSaveDir() + "/" +
	                                      profileImage.getUuid() + "_" +
	                                      profileImage.getFileName();

	            log.info("로그인석세스핸들러의 profileImageUrl은"+profileImageUrl);
	            // 세션에 이미지 URL 저장
	            ses.setAttribute("profileImagePath", profileImageUrl);
	        }        
	        
	        // 안 읽은 메시지 수 조회
	        int AllUnreadChat = chatsv.getAllUnreadChatID(username);
	        log.info("username는"+username);        
	        log.info("로그인석세스핸들러의 AllUnreadChat은 "+AllUnreadChat);
	        
	        // 세션에 안 읽은 메시지 수 저장
	        ses.setAttribute("AllUnreadChat", AllUnreadChat);
	        log.info("ses.setAttribute(\"AllUnreadChat\"부분 지나침");
	    }
		//231216전경환추가------E
	}


}
package com.kw.cine.auth.handler;

/**
  * @FileName : CustomAuthenticationSuccessHandler.java
  * @Project : cine
  * @Date : 2021. 1. 25. 
  * @작성자 : KNG
  * @변경이력 :
  * @프로그램 설명 : 로그인 성공시 핸들러
  */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kw.cine.auth.dto.ResponseDataCode;
import com.kw.cine.auth.dto.ResponseDataDto;
import com.kw.cine.auth.dto.ResponseDataStatus;

/**
 * 로그인 성공시 핸들러
 *
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {

	private static final Logger logger = LogManager.getLogger(CustomAuthenticationSuccessHandler.class);

	/**
	 * 로그인이 성공하고나서 로직
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
    	
    	logger.info("관리자 아이디 로그인을 성공하였습니다.");
    	
    	ObjectMapper mapper = new ObjectMapper();	//JSON 변경용
    	
    	ResponseDataDto responseDataDto = new ResponseDataDto();
    	responseDataDto.setCode(ResponseDataCode.SUCCESS);
    	responseDataDto.setStatus(ResponseDataStatus.SUCCESS);
    	
    	String prevPage = request.getSession().getAttribute("prevPage").toString();	//이전 페이지 가져오기
    	 
    	Map<String, String> items = new HashMap<String,String>();	
    	items.put("url", prevPage);	// 이전 페이지 저장
    	responseDataDto.setItem(items);
    	
    	response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info(mapper.writeValueAsString(responseDataDto).toString());
        response.getWriter().print(mapper.writeValueAsString(responseDataDto));
        response.getWriter().flush();
        //response.sendRedirect(prevPage);
    }
}
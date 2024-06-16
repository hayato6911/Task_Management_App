package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.ErrorMessageConst;
import com.example.demo.form.LoginFrom;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;
/**
 * ログイン画面 Controller
 * 
 * 
 * @author hayato
 */

@RequiredArgsConstructor
@Controller
public class LoginController {
	
	/** ログイン画面 Service */
	private final LoginService service;
	
	/** passwordEncoder */
    private final PasswordEncoder passwordEncoder;	
    
    /** メッセージソース  */
    private final MessageSource messageSource;
    
	/**
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
	@GetMapping("/login")
	public String view(Model model,  LoginFrom form) {
		
		return "login";
	}
	/**
	 * ログイン
	 * 
	 * @param model モデル
	 * @param form 入力情報
　	 * @return 表示画面
	 */
	@PostMapping("/login")
    public String login(Model model,  LoginFrom form) {
	var userInfo = service.searchUserById(form.getLoginId());
	boolean isCorrectUserAuth = userInfo.isPresent() 
	  && passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
	if(isCorrectUserAuth) {
	  return "redirect:/menu";
	}else {
		var errorMsg = AppUtil.getMessage(messageSource, ErrorMessageConst.LOGIN_WRONG_INPUT);
		model.addAttribute("errorMsg", errorMsg);
		return "login";
	}
  }
}

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報テーブル Entity
 * 
 * @author hayato
 */
@Entity
@Table(name = "user_info")
@Data
public class UserInfo {
	
	/** ログインID */
	@Id
	@Column(name = "login_id")
	private String loginid;
	
	/** パスワード */
	private String password; 

}

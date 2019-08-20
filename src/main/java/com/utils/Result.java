/**   
* @Title: Result.java 
* @Package com.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-08-20 
*/
package com.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** 
* @ClassName: Result 
* @Description: 统一返回结果
* @author: zhuyj
*/
public class Result<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private static int SUCCESS = 0;
	@JsonIgnore
	private static String SUCCESS_MSG = "success";
	
	@JsonIgnore
	private static int FAIL = -1;
	@JsonIgnore
	private static String FAIL_MSG = "fail";
	
	private int code;
	private String msg;
	private T data;
	
	public Result<T> setCode(int code){
		this.code = code;
		return this;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public Result<T> setMsg(String msg){
		this.msg = msg;
		return this;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public Result<T> setData(T data){
		this.data = data;
		return this;
	}
	
	public T getData() {
		return this.data;
	}
	
	public static <T> Result<T> SUCESS() {
		return new Result<T>().setCode(SUCCESS).setMsg(SUCCESS_MSG);
	}
	
	public static <T> Result<T> SUCESS(int code) {
		return new Result<T>().setCode(code).setMsg(SUCCESS_MSG);
	}
	
	public static <T> Result<T> SUCESS(String msg) {
		return new Result<T>().setCode(SUCCESS).setMsg(msg);
	}
	
	public static <T> Result<T> SUCESS(T data) {
		return new Result<T>().setCode(SUCCESS).setMsg(SUCCESS_MSG).setData(data);
	}
	
	public static <T> Result<T> SUCESS(int code, String msg) {
		return new Result<T>().setCode(code).setMsg(msg);
	}
	
	public static <T> Result<T> FAIL() {
		return new Result<T>().setCode(FAIL).setMsg(FAIL_MSG);
	}
	
	public static <T> Result<T> FAIL(int code) {
		return new Result<T>().setCode(code).setMsg(FAIL_MSG);
	}
	
	public static <T> Result<T> FAIL(String msg) {
		return new Result<T>().setCode(FAIL).setMsg(msg);
	}
	
	public static <T> Result<T> FAIL(T data) {
		return new Result<T>().setCode(FAIL).setMsg(FAIL_MSG).setData(data);
	}
	
	public static <T> Result<T> FAIL(int code, String msg) {
		return new Result<T>().setCode(code).setMsg(msg);
	}
	
}

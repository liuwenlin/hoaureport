package com.hoau.hoaureport.module.frameworkimpl.server.util;

import org.apache.commons.codec.binary.Base64;

import com.hoau.hoaureport.module.frameworkimpl.shared.domain.Token;


/**
 * 令牌序列化与反序列化
 * @author 高佳
 * @date 2015年6月23日
 */
final public class TokenMarshal {
	private TokenMarshal(){
	}

	/**
	 * 序列化(Base64编码)
	 * @param token
	 * @return
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	public static String marshal(Token token) {
		return new String(Base64.encodeBase64String(token.toBytes()));
	}

	/**
	 * 反序列化（Base64解码）
	 * @param tokenStr
	 * @return
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	public static Token unMarshal(String tokenStr) {
		return new Token(Base64.decodeBase64(tokenStr));
	}
}

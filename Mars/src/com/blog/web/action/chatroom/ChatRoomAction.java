package com.blog.web.action.chatroom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/chat/chat")
@Scope("prototype")
public class ChatRoomAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	
	public String toChatRoom(){
		return "list";
	}
}

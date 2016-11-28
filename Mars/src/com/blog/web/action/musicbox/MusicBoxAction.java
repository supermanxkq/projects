package com.blog.web.action.musicbox;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.web.action.BaseAction;


@Controller("/musicbox/musicbox")
@Scope("prototype")
public class MusicBoxAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	public String list() {
		return "list";
	}
}

package com.blog.web.action.goods;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.dto.goods.GoodsDTO;
import com.blog.service.goods.GoodsService;
import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/goods/goods")
@Scope("prototype")
public class GoodsAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;

	@Resource
	private GoodsService goodsService;
	private GoodsDTO goodsDTO=new GoodsDTO();
	public String list() {
		this.setMethod("add");
		return "list";
	}
	public String add(){
		goodsService.addSave(goodsDTO);
		return "savesuccess";
	}
	public GoodsDTO getGoodsDTO() {
		return goodsDTO;
	}
	public void setGoodsDTO(GoodsDTO goodsDTO) {
		this.goodsDTO = goodsDTO;
	}
}

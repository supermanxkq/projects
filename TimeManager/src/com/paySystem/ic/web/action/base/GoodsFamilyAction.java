package com.paySystem.ic.web.action.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.service.base.GoodsFamilyService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:GoodsFamilyAction
 * @Description:商品分类管理Action
 * @date: 2014-6-26下午03:38:04
 * @author: 张亚运
 * @version: V1.0
 */
@Controller("/base/goodsfamily")
@Scope("prototype")
public class GoodsFamilyAction extends BaseAction {

    /**
     * 版本序列号
     */
    private static final long serialVersionUID = 1L;

    @Resource
    GoodsFamilyService goodsfamilyService;

    @Resource
    FunctionsService functionsService;

    private GoodsFamilyDTO goodsfamilyDto = new GoodsFamilyDTO();

    public GoodsFamilyDTO getGoodsfamilyDto() {
        return goodsfamilyDto;
    }

    public void setGoodsfamilyDto(GoodsFamilyDTO goodsfamilyDto) {
        this.goodsfamilyDto = goodsfamilyDto;
    }

    /**
     *@Description:进入List页面
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String list() {
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);
        return "list";
    }

    /**
     *@Description:加载页面数据
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    @SuppressWarnings("unchecked")
    public String jsonPageList() {

        UserSession us = Utils.getUserSession();
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        /**判断排序参数是否有值*/
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderby.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {
            /**如果页面没有要求排序方式，则设置按照分类编号排序*/
            orderby.put("parentId", "asc");
        }

        List<GoodsFamilyDTO> listDto = new ArrayList<GoodsFamilyDTO>();
        QueryResult<GoodsFamilyDTO> result = null;
        try {
            result = goodsfamilyService.queryAll((goodsfamilyDto.getPage() - 1) * pageNum, pageNum, goodsfamilyDto,
                    orderby);
            listDto = result.getResultlist();
        }
        catch (Exception e) {
            e.getMessage();
        }
        List<List<String>> lists = new ArrayList<List<String>>();
        for (int i = 0; i < listDto.size(); i++) {
            /**向页面赋值*/
            GoodsFamilyDTO dto = listDto.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(dto.getFamilyName()));
            strings.add(Utils.getString(dto.getNodeLevel() + "级分类"));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.VISIBLE_STATUS_CONV, dto.getDefaultShow()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.GoodsFamily_STATUS, dto.getFamilyWay()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, dto.getStatus()));
            strings.add(DateTimeTool.dateFormat("", dto.getCreateTime()));
            String operation = "";

            if (us.getUserLevel() == 0) {
                if (Utils.checkPermission("sy-1701-03")) {
                    operation += "<a href =base/goodsfamily!editUI?goodsfamilyDto.familyId=" + dto.getFamilyId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }
                /**判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示*/
                if (Utils.checkPermission("sy-1701-04") && dto.getStatus() != 9) {
                    operation += "<a href=javascript:deleteData('base/goodsfamily!delete','" + dto.getFamilyId()
                            + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }
                if (Utils.checkPermission("sy-1701-01")) {
                    operation += "<a href =base/goodsfamily!checkUI?goodsfamilyDto.familyId=" + dto.getFamilyId()
                            + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
                }
            }

            strings.add(operation);
            lists.add(strings);
        }
        PageView pageView = new PageView(goodsfamilyDto.getPage(), result.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
        return Utils.printInfo(listInfoDTO);

    }

    /**
     *@Description:添加按钮方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String addUI() {

        this.setMethod("addSave");
        List<OptionsInteger> preFamilyValues = new ArrayList<OptionsInteger>();
        preFamilyValues.add(new OptionsInteger(0, "顶级分类"));
        preFamilyValues.addAll(goodsfamilyService.getFamilyOption());
        this.getRequest().setAttribute("preFamilyValues", preFamilyValues);
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);
        /**商品分类形式*/
        this.getRequest().setAttribute("familySign", OptionsValue.GoodsFamily_STATUS);
        this.getRequest().setAttribute("visibleValues", OptionsValue.VISIBLE_STATUS_CONV);

        return INPUT;
    }

    /**
     *@Description:添加保存方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String addSave() {

        /**所属分类为顶级分类则节点等级为1级，否则则为选择分类的节点等级+1*/
        if (goodsfamilyDto.getParentId() != 0) {
            goodsfamilyDto.setNodeLevel(goodsfamilyService.getNodeLevel(goodsfamilyDto.getParentId()) + 1);
        }
        else {
            goodsfamilyDto.setNodeLevel(1);
        }
        GoodsFamily goodsFamily = goodsfamilyService.saveGoodsFamily(goodsfamilyDto);
        functionsService.saveFunction("商品分类管理", 1, "添加商品分类：" + goodsFamily.getFamilyId());
        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        this.getRequest().setAttribute("url", "base/goodsfamily!list");

        return SUCCESS;
    }

    /**
     *@Description:修改按钮方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String editUI() {
        this.setMethod("editSave");
        goodsfamilyDto = goodsfamilyService.find(goodsfamilyDto.getFamilyId());
        List<OptionsInteger> preFamilyValues = new ArrayList<OptionsInteger>();
        preFamilyValues.add(new OptionsInteger(0, "顶级分类"));
        preFamilyValues.addAll(goodsfamilyService.getFamilyOption());
        this.getRequest().setAttribute("preFamilyValues", preFamilyValues);
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);
        /**商品分类形式*/
        this.getRequest().setAttribute("familySign", OptionsValue.GoodsFamily_STATUS);
        this.getRequest().setAttribute("visibleValues", OptionsValue.VISIBLE_STATUS_CONV);

        return INPUT;
    }

    /**
     *@Description:修改保存方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String editSave() {

        /**所属分类为顶级分类则节点等级为1级，否则则为选择分类的节点等级+1*/
        if (goodsfamilyDto.getParentId() != 0) {
            goodsfamilyDto.setNodeLevel(goodsfamilyService.getNodeLevel(goodsfamilyDto.getParentId()) + 1);
        }
        else {
            goodsfamilyDto.setNodeLevel(1);
        }
        try {
            ReturnDTO dto = goodsfamilyService.updateGoodsFamily(goodsfamilyDto);
            if (dto.getFlag()) {
                functionsService.saveFunction("商品分类管理", 2, "商品信息修改：" + goodsfamilyDto.getFamilyId());
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "base/goodsfamily!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "base/goodsfamily!list");
                return ERROR;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     *@Description:删除更新方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String delete() {
        try {
            goodsfamilyService.deleteGoodsFamily(Integer.valueOf(this.getId()));
            functionsService.saveFunction("商品分类管理", 3, "删除商品分类：" + goodsfamilyDto.getFamilyId());
            this.ajaxResult = "ajaxsuccess";
        }
        catch (Exception e) {
            e.printStackTrace();
            this.ajaxResult = "ajaxfailure";
            this.msgResult = e.getMessage();
        }
        return this.ajaxResult;
    }

    public String checkUI() {
        this.setMethod("checkUI");
        goodsfamilyDto = goodsfamilyService.find(goodsfamilyDto.getFamilyId());
        List<OptionsInteger> preFamilyValues = new ArrayList<OptionsInteger>();
        preFamilyValues.add(new OptionsInteger(0, "顶级分类"));
        preFamilyValues.addAll(goodsfamilyService.getFamilyOption());
        this.getRequest().setAttribute("preFamilyValues", preFamilyValues);
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);
        /**商品分类形式*/
        this.getRequest().setAttribute("familySign", OptionsValue.GoodsFamily_STATUS);
        this.getRequest().setAttribute("visibleValues", OptionsValue.VISIBLE_STATUS_CONV);

        return INPUT;
    }

    /**
     *@Description:检验分类名称是否存在
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String checkName() {
        ReturnDTO dto = new ReturnDTO();
        if (goodsfamilyService.validateName(goodsfamilyDto.getFamilyName(), goodsfamilyDto.getFamilyId())) {
            dto.setFlag(true);
        }
        return Utils.printInfo(dto);
    }

}

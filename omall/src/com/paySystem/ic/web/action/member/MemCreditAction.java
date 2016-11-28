package com.paySystem.ic.web.action.member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemCreditRule;
import com.paySystem.ic.service.member.MemCreditRuleService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemCreditRuleDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;

/**
 * @ClassName:MemCreditAction
 * @Description:会员积分规则action
 * @date: 2014-7-22上午09:44:17
 * @author: 张亚运
 * @version: V1.0
 */
@Controller("/member/memCreditRule")
@Scope("prototype")
public class MemCreditAction extends BaseAction {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = -384723733585687348L;

    @Resource
    MemCreditRuleService memCreditService;

    @Resource
    FunctionsService functionsService;

    public MemCreditRuleDTO memCreditDto = new MemCreditRuleDTO();

    public MemCreditRuleDTO getMemCreditDto() {
        return memCreditDto;
    }

    public void setMemCreditDto(MemCreditRuleDTO memCreditDto) {
        this.memCreditDto = memCreditDto;
    }

    /**
     *@Description:list方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String list() {
        UserSession us = Utils.getUserSession();
        if (us.getUserLevel() == 0) {
            this.getRequest().setAttribute("statusValues", OptionsValue.COND_STATE);
            memCreditDto.setStatus(1);
            return "list";
        }
        else {
            return "intercepthtml";
        }
    }

    /**
     *@Description:异步加载数据
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
            /**如果页面没有要求排序方式，则设置按照编号排序*/
            orderby.put("gradeId", "asc");
        }

        List<MemCreditRuleDTO> listDto = new ArrayList<MemCreditRuleDTO>();
        QueryResult<MemCreditRuleDTO> result = null;
        try {
            result = memCreditService.queryAll((memCreditDto.getPage() - 1) * pageNum, pageNum, memCreditDto, orderby);
            listDto = result.getResultlist();
        }
        catch (Exception e) {
            e.getMessage();
        }
        List<List<String>> lists = new ArrayList<List<String>>();
        for (int i = 0; i < listDto.size(); i++) {
            /**向页面赋值*/
            MemCreditRuleDTO dto = listDto.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(dto.getGradeName()));
            /**
            strings.add(NumberUtil.numberFormat("#,##0.00", dto.getConsPoints()));
            */
            strings.add(NumberUtil.numberFormat("#,##0.00", dto.getLoginPoints()));
            strings.add(NumberUtil.numberFormat("#,##0.00", dto.getGradePoints()));
            strings.add(NumberUtil.numberFormat("#,##0.00", dto.getUpperLimit()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, dto.getStatus()));
            strings.add(Utils.getString(DateTimeTool.dateFormat("", dto.getCreateTime())));
            String operation = "";

            if (us.getUserLevel() == 0) {
                if (Utils.checkPermission("sy-1306-01")) {
                    operation += "<a href =member/memCreditRule!checkUI?memCreditDto.creditId=" + dto.getCreditId()
                            + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
                }
                if (Utils.checkPermission("sy-1306-03") && dto.getStatus() != 9) {
                    operation += "<a href =member/memCreditRule!editUI?memCreditDto.creditId=" + dto.getCreditId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }
                /**判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示*/
                if (Utils.checkPermission("sy-1306-04") && dto.getStatus() != 9) {
                    operation += "<a href=javascript:deleteData('member/memCreditRule!delete','" + dto.getCreditId()
                            + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }

            }

            strings.add(operation);
            lists.add(strings);
        }
        PageView pageView = new PageView(memCreditDto.getPage(), result.getTotalrecord());
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
        List<OptionsInteger> memGradeValues = new ArrayList<OptionsInteger>();
        memGradeValues.addAll(memCreditService.getMemGradeOption());
        this.getRequest().setAttribute("memGradeValues", memGradeValues);
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);
        return INPUT;
    }

    /**
     *@Description:添加保存方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String addSave() {

        MemCreditRule memCredit = memCreditService.saveMemCredit(memCreditDto);
        functionsService.saveFunction("会员积分规则", 1, "添加会员积分规则：" + memCredit.getCreditId());
        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        this.getRequest().setAttribute("url", "member/memCreditRule!list");

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
        memCreditDto = memCreditService.findMemCredit(memCreditDto.getCreditId());
        List<OptionsInteger> memGradeValues = new ArrayList<OptionsInteger>();
        memGradeValues.addAll(memCreditService.getMemGradeOption());
        this.getRequest().setAttribute("memGradeValues", memGradeValues);
        this.getRequest().setAttribute("statusValues", OptionsValue.STATUS_ENABLE_DISABLE);

        return INPUT;
    }

    /**
     *@Description:修改保存方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String editSave() {
        ReturnDTO dto = memCreditService.updateMemCredit(memCreditDto);
        if (dto.getFlag()) {
            functionsService.saveFunction("会员积分规则", 2, "会员积分规则修改：" + memCreditDto.getCreditId());
            this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
            this.getRequest().setAttribute("url", "member/memCreditRule!list");
            return SUCCESS;
        }
        else {
            this.getRequest().setAttribute("result", dto.getMsg());
            this.getRequest().setAttribute("url", "member/memCreditRule!list");
            return ERROR;
        }
    }

    /**
     *@Description:删除方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String delete() {
        ReturnDTO dto = memCreditService.deleteMemCredit(Integer.valueOf(this.getId()));
        if (dto.getFlag()) {
            functionsService.saveFunction("会员积分规则", 3, "会员积分规则删除：" + this.getId());
            this.ajaxResult = "ajaxsuccess";
        }
        else {
            this.ajaxResult = "ajaxfailure";
        }
        return this.ajaxResult;
    }

    /**
     *@Description:查看方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String checkUI() {

        this.setMethod("checkUI");
        memCreditDto = memCreditService.findMemCredit(memCreditDto.getCreditId());
        List<OptionsInteger> memGradeValues = new ArrayList<OptionsInteger>();
        memGradeValues.addAll(memCreditService.getMemGradeOption());
        this.getRequest().setAttribute("memGradeValues", memGradeValues);
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);

        return INPUT;
    }

    /**
     *@Description:校验该会员等级的积分规则是否存在
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String checkName() {
        ReturnDTO returnDto = new ReturnDTO();
        if (memCreditService.validateName(memCreditDto.getGradeId(), memCreditDto.getCreditId())) {
            returnDto.setFlag(true);
        }
        return Utils.printInfo(returnDto);
    }
}

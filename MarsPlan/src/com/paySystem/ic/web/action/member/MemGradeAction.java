package com.paySystem.ic.web.action.member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemGrade;
import com.paySystem.ic.service.member.MemGradeService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGradeDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MemGradeAction
 * @Description:会员等级Action
 * @date: 2014-7-15下午12:13:49
 * @author: 张亚运
 * @version: V1.0
 */
@Controller("/memGrade/memGrade")
@Scope("prototype")
public class MemGradeAction extends BaseAction {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = -1312160334071783335L;

    @Resource
    MemGradeService memGradeService;

    @Resource
    FunctionsService functionsService;

    public MemGradeDTO memGradeDto = new MemGradeDTO();

    public MemGradeDTO getMemGradeDto() {
        return memGradeDto;
    }

    public void setMemGradeDto(MemGradeDTO memGradeDto) {
        this.memGradeDto = memGradeDto;
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
            memGradeDto.setStatus(1);
            return "list";
        }
        else {
            return "intercepthtml";
        }
    }

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

        List<MemGradeDTO> listDto = new ArrayList<MemGradeDTO>();
        QueryResult<MemGradeDTO> result = null;
        try {
            result = memGradeService.queryAll((memGradeDto.getPage() - 1) * pageNum, pageNum, memGradeDto, orderby);
            listDto = result.getResultlist();
        }
        catch (Exception e) {
            e.getMessage();
        }
        List<List<String>> lists = new ArrayList<List<String>>();
        for (int i = 0; i < listDto.size(); i++) {
            /**向页面赋值*/
            MemGradeDTO dto = listDto.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(dto.getGradeName()));
            strings.add(NumberUtil.numberFormat("#,##0", dto.getLowerLimit()));
            strings.add(NumberUtil.numberFormat("#,##0", dto.getUpperLimit()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, dto.getStatus()));
            strings.add(DateTimeTool.dateFormat("", dto.getCreateTime()));
            String operation = "";

            if (us.getUserLevel() == 0) {
                if (Utils.checkPermission("sy-1303-03") && dto.getStatus() != 9) {
                    operation += "<a href =memGrade/memGrade!editUI?memGradeDto.gradeId=" + dto.getGradeId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }
                /**判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示*/
                if (Utils.checkPermission("sy-1303-04") && dto.getStatus() != 9) {
                    operation += "<a href=javascript:deleteData('memGrade/memGrade!delete','" + dto.getGradeId()
                            + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }
                if (Utils.checkPermission("sy-1303-01")) {
                    operation += "<a href =memGrade/memGrade!checkUI?memGradeDto.gradeId=" + dto.getGradeId()
                            + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
                }
            }

            strings.add(operation);
            lists.add(strings);
        }
        PageView pageView = new PageView(memGradeDto.getPage(), result.getTotalrecord());
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

        MemGrade memGrade = memGradeService.saveMemGrade(memGradeDto);
        functionsService.saveFunction("会员等级管理", 1, "添加会员等级：" + memGrade.getGradeId());
        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        this.getRequest().setAttribute("url", "memGrade/memGrade!list");

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
        memGradeDto = memGradeService.findGrade(memGradeDto.getGradeId());
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);
        return INPUT;
    }

    /**
     *@Description:修改保存方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String editSave() {
        try {
            ReturnDTO dto = memGradeService.updateMemGrade(memGradeDto);
            if (dto.getFlag()) {
                functionsService.saveFunction("会员等级管理", 2, "会员等级修改：" + memGradeDto.getGradeId());
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "memGrade/memGrade!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "memGrade/memGrade!list");
                return ERROR;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     *@Description:删除会员等级
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String delete() {
        try {
            ReturnDTO dto = memGradeService.deleteMemGrade(Integer.valueOf(this.getId()));
            if (dto.getFlag()) {
                functionsService.saveFunction("会员等级管理", 3, "会员等级删除：" + memGradeDto.getGradeId());
                this.ajaxResult = "ajaxsuccess";
            }
            else {
                this.ajaxResult = "ajaxfailure";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            this.msgResult = e.getMessage();
        }
        return this.ajaxResult;
    }

    /**
     *@Description:查看会员等级信息
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String checkUI() {
        this.setMethod("checkUI");
        memGradeDto = memGradeService.findGrade(memGradeDto.getGradeId());
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);
        return INPUT;
    }

    /**
     *@Description:检验等级名称是否存在
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String checkName() {

        ReturnDTO dto = new ReturnDTO();
        if (memGradeService.validateName(memGradeDto.getGradeName(), memGradeDto.getGradeId())) {
            dto.setFlag(true);
        }
        return Utils.printInfo(dto);
    }
}

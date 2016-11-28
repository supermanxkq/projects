package com.paySystem.ic.web.action.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.message.MessParamRelation;
import com.paySystem.ic.service.message.MessParamRelationService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Controller("/message/messagerelation")
@Scope("prototype")
public class MessRelationAction extends BaseAction {

    private static final long serialVersionUID = 8598408971252860774L;

    @Resource
    MessParamRelationService messParamRelationService;

    @Resource
    FunctionsService functionsService;

    private MessageDTO messDto = new MessageDTO();

    public MessageDTO getMessDto() {
        return messDto;
    }

    public void setMessDto(MessageDTO messDto) {
        this.messDto = messDto;
    }

    /**
     *@Title:list
     *@Description:list方法
     *@param:@return
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    public String list() {
        UserSession us = Utils.getUserSession();
        if (us.getUserLevel() != 2) {
            // 若登录权限为0和1（平台和机构），则进入list页面
            this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);// 使用状态
            this.getRequest().setAttribute("type", OptionsValue.PACKAGE_TYPE);// 套餐类型
            messDto.setState(1);
            messDto.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd", DateTimeTool.nDaysAgo(7, new Date())));
            messDto.setEndTime(DateTimeTool.dateFormat("yyyy-MM-dd", new Date()));
            return "list";
        }
        else {
            // 若登录权限为2（商户），则跳转无权限页面
            return "intercepthtml";
        }
    }

    /**
     *@Title:jsonPageList
     *@Description:参数使用关系查询jsonPage
     *@param:@return
     *@param:@throws Exception
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    @SuppressWarnings("unchecked")
    public String jsonPageList() throws Exception {

        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();

        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            // 判断排序参数是否有值
            orderBy.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {
            // 如果页面没有要求排序方式，则设置按照会员编号排序
            orderBy.put("mp.updatetime", "desc");
        }

        QueryResult<MessageDTO> result = messParamRelationService.queryAll((messDto.getPage() - 1) * pageNum, pageNum,
                messDto, orderBy);
        List<MessageDTO> messList = result.getResultlist();

        List<List<String>> lists = new ArrayList<List<String>>();

        for (int i = 0; i < messList.size(); i++) {
            // 向页面赋值
            MessageDTO messageDTO = messList.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(messageDTO.getMessName()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.PACKAGE_TYPE, messageDTO.getMessType()));
            strings.add(Utils.getString(messageDTO.getMessPeriod()));
            strings.add(NumberUtil.numberFormat("", messageDTO.getMessFee()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, messageDTO.getState()));
            strings.add(Utils.getString(messageDTO.getOrgMerName()));
            strings.add(Utils.getString(messageDTO.getBeginTime()));
            strings.add(Utils.getString(messageDTO.getEndTime()));
            strings.add(Utils.getString(messageDTO.getProposer()));
            strings.add(Utils.getString(messageDTO.getUpdateTime()));

            String operation = "";

            if (Utils.checkPermission("sy-1602-01")) {
                // 判断权限在相关操作栏显示相关操作
                operation += "<a href=message/messagerelation!checkUI?messDto.mprId=" + messageDTO.getMprId()
                        + " title='修改'>" + Globals.IMG_VIEW + "</a>&nbsp;";
            }

            if (Utils.checkPermission("sy-1602-03") && messageDTO.getState() != 9) {
                // 判断权限在相关操作栏显示相关操作
                operation += "<a href=message/messagerelation!editUI?messDto.mprId=" + messageDTO.getMprId()
                        + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
            }
            if (Utils.checkPermission("sy-1602-04") && messageDTO.getState() != 9) {
                // 判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示
                operation += "<a href=javascript:deleteData('message/messagerelation!delete','" + messageDTO.getMprId()
                        + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
            }
            strings.add(operation);
            lists.add(strings);
        }

        PageView pageView = new PageView(messDto.getPage(), result.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
        return Utils.printInfo(listInfoDTO);
    }

    /**
     *@Title:addUI
     *@Description:添加按钮执行方法
     *@param:@return
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    public String addUI() {
        messDto.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", new Date()));
        this.setMethod("addSave");
        this.getRequest().setAttribute("status", OptionsValue.STATE_STATUSD);// 使用状态
        this.getRequest().setAttribute("type", OptionsValue.PACKAGE_TYPE);// 套餐类型
        return INPUT;
    }

    /**
     *@Title:editUI
     *@Description:修改按钮执行方法
     *@param:@return
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    @SuppressWarnings("deprecation")
    public String editUI() {
        this.setMethod("editSave");
        MessageDTO mprdto = messParamRelationService.queryById(messDto.getMprId());
        HttpServletRequest request = this.getRequest();
        request.setAttribute("status", OptionsValue.STATE_STATUSD);// 使用状态
        if (mprdto != null) {
            int m = (DateTimeTool.dateFormat("", mprdto.getEndTime()).getYear() - DateTimeTool.dateFormat("",
                    mprdto.getBeginTime()).getYear())
                    * 12
                    + DateTimeTool.dateFormat("", mprdto.getEndTime()).getMonth()
                    - DateTimeTool.dateFormat("", mprdto.getBeginTime()).getMonth();
            // 若DTO不为空向修改页面赋值
            messDto.setMfpId(mprdto.getMfpId());
            messDto.setOrgName(mprdto.getOrgName());
            messDto.setMerName(mprdto.getMerName());
            messDto.setOrgId(mprdto.getOrgId());
            messDto.setMerId(mprdto.getMerId());
            messDto.setMessName(mprdto.getMessName());
            messDto.setUseLives(m);
            messDto.setState(mprdto.getState());
            messDto.setBeginTime(mprdto.getBeginTime());
            if (mprdto.getMfpDesc() != null) {
                messDto.setMfpDesc(mprdto.getMfpDesc());
            }
        }
        return INPUT;
    }

    /**
     *@Description:查看方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    @SuppressWarnings("deprecation")
    public String checkUI() {
        this.setMethod("checkUI");
        MessageDTO mprdto = messParamRelationService.queryById(messDto.getMprId());
        HttpServletRequest request = this.getRequest();
        request.setAttribute("status", OptionsValue.STATE_STATUS);// 使用状态
        if (mprdto != null) {
            // 若DTO不为空向修改页面赋值
            messDto.setMfpId(mprdto.getMfpId());
            messDto.setOrgName(mprdto.getOrgName());
            messDto.setMerName(mprdto.getMerName());
            messDto.setOrgId(mprdto.getOrgId());
            messDto.setMerId(mprdto.getMerId());
            messDto.setMessName(mprdto.getMessName());
            messDto.setState(mprdto.getState());
            messDto.setBeginTime(mprdto.getBeginTime());
            int m = (DateTimeTool.dateFormat("", mprdto.getEndTime()).getYear() - DateTimeTool.dateFormat("",
                    mprdto.getBeginTime()).getYear())
                    * 12
                    + DateTimeTool.dateFormat("", mprdto.getEndTime()).getMonth()
                    - DateTimeTool.dateFormat("", mprdto.getBeginTime()).getMonth();
            messDto.setUseLives(m);
            if (mprdto.getMfpDesc() != null) {
                messDto.setMfpDesc(mprdto.getMfpDesc());
            }
        }
        return INPUT;
    }

    /**
     *@Title:addSave
     *@Description:保存数据方法
     *@param:@return
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    public String addSave() {
        UserSession us = Utils.getUserSession();
        if (us.getUserLevel() == 0) {
            // 权限为0（平台）则获取机构ID
            messDto.setOrgMerId(messDto.getOrgId());
        }
        else {
            // 权限为1（机构）则获取商户ID
            messDto.setOrgMerId(messDto.getMerId());
        }

        @SuppressWarnings("unused")
        MessParamRelation mpr = messParamRelationService.saveMessParamRelation(messDto);
        functionsService.saveFunction("短信费管理", 1, "增加参数使用关系：" + messDto.getMprId());
        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);// 使用状态
        this.getRequest().setAttribute("url", "message/messagerelation!list");
        return SUCCESS;
    }

    /**
     *@Title:editSave
     *@Description:修改保存方法
     *@param:@return
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    public String editSave() {
        try {
            ReturnDTO dto = messParamRelationService.updateMessParamRelation(messDto);
            if (dto.getFlag()) {
                functionsService.saveFunction("短信费管理", 2, "参数使用关系管理：" + messDto.getMprId());
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "message/messagerelation!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "message/messagerelation!list");
                return ERROR;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     *@Title:delete
     *@Description:逻辑删除方法
     *@param:@return
     *@return:String
     *@author:
     *@thorws:
     */
    public String delete() {
        try {
            messParamRelationService.deleteMessParamRelation(this.getId());
            // 在操作日志中生成记录
            functionsService.saveFunction("短信费管理", 3, "删除参数是用关系：" + this.getId());
            this.ajaxResult = "ajaxsuccess";
        }
        catch (Exception e) {
            e.printStackTrace();
            this.ajaxResult = "ajaxfailure";
            this.msgResult = e.getMessage();
        }
        return this.ajaxResult;
    }

    /**
     *@Description:检验该参数关系是否存在
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String validateName() {

        ReturnDTO returnDto = new ReturnDTO();
        UserSession us = Utils.getUserSession();
        if (us.getUserLevel() != 2) {
            // 权限为0（平台）则获取机构ID
            messDto.setOrgMerId(messDto.getOrgId());
        }
        else {
            // 权限为1（机构）则获取商户ID
            messDto.setOrgMerId(messDto.getMerId());
        }
        if (!messParamRelationService.queryRelation(messDto.getOrgMerId(), messDto.getMprId())) {
            returnDto.setFlag(true);
        }

        return Utils.printInfo(returnDto);

    }
}

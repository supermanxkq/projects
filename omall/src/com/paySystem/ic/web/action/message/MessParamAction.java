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
import com.paySystem.ic.bean.message.MessFeeParam;
import com.paySystem.ic.service.message.MessFeeParamService;
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

@Controller("/message/messageparam")
@Scope("prototype")
public class MessParamAction extends BaseAction {

    private static final long serialVersionUID = 1157838270801154543L;

    @Resource
    MessFeeParamService messFeeParamService;

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
     *@Description:显示页面list方法
     *@param:@return
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    public String list() {

        UserSession us = Utils.getUserSession();
        if (us.getUserLevel() != 2) {
            messDto.setUseSign(1);
            this.getRequest().setAttribute("status", OptionsValue.STATE_STATUS);//使用状态
            this.getRequest().setAttribute("type", OptionsValue.PACKAGE_TYPE);//套餐类型
            messDto.setBeginTime(DateTimeTool.dateFormat("yyyy-MM-dd", DateTimeTool.nDaysAgo(7, new Date())));
            messDto.setEndTime(DateTimeTool.dateFormat("yyyy-MM-dd", new Date()));
            return "list";
        }
        else {
            return "intercepthtml";
        }
    }

    /**
     *@Title:jsonPageList
     *@Description:短信参数查询jsonPage
     *@param:@return
     *@param:@throws Exception
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    @SuppressWarnings("unchecked")
    public String jsonPageList() throws Exception {
        UserSession us = Utils.getUserSession();
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        // 判断排序参数是否有值
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderBy.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {// 如果页面没有要求排序方式，则设置按照会员编号排序
            orderBy.put("mfpId", "desc");
        }
        messDto.setHelpSign(0);
        QueryResult<MessageDTO> result = messFeeParamService.queryAll((messDto.getPage() - 1) * pageNum, pageNum,
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
            strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, messageDTO.getUseSign()));
            strings.add(Utils.getString(messageDTO.getProposer()));
            strings.add(Utils.getString(DateTimeTool.dateFormat("", messageDTO.getUpdateTime())));

            String operation = "";
            if (Utils.checkPermission("sy-1601-01")) {
                operation += "<a href=message/messageparam!checkUI?messDto.mfpId=" + messageDTO.getMfpId()
                        + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
            }
            if (us.getUserLevel() != 2 && messageDTO.getUseSign() != 9) {
                if (Utils.checkPermission("sy-1601-03")) {
                    operation += "<a href=message/messageparam!editUI?messDto.mfpId=" + messageDTO.getMfpId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }
                if (messageDTO.getUseSign() != 9 && Utils.checkPermission("sy-1601-04")) {
                    operation += "<a href=javascript:deleteData('message/messageparam!delete','"
                            + messageDTO.getMfpId() + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }
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
        this.setMethod("addSave");
        this.getRequest().setAttribute("status", OptionsValue.STATE_STATUSD);//使用状态
        this.getRequest().setAttribute("type", OptionsValue.PACKAGE_TYPE);//套餐类型
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
    public String editUI() {
        this.setMethod("editSave");
        MessFeeParam mfp = messFeeParamService.find(messDto.getMfpId());
        HttpServletRequest request = this.getRequest();
        request.setAttribute("status", OptionsValue.STATE_STATUSD);//使用状态
        request.setAttribute("type", OptionsValue.PACKAGE_TYPE);//套餐类型
        if (mfp != null) {
            messDto.setMfpId(mfp.getMfpId());
            messDto.setMessName(mfp.getMessName());
            messDto.setMessType(mfp.getMessType());
            messDto.setUseSign(mfp.getUseSign());
            messDto.setMessPeriod(mfp.getMessPeriod());
            messDto.setMessFee(mfp.getMessFee());
            messDto.setMiniPeriod(mfp.getMessPeriod());
            messDto.setSingleFee(mfp.getMessFee());
            messDto.setMfpDesc(mfp.getMfpDesc());
        }
        return INPUT;
    }

    /**
     *@Description:查看方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String checkUI() {
        this.setMethod("checkUI");
        MessFeeParam mfp = messFeeParamService.find(messDto.getMfpId());
        HttpServletRequest request = this.getRequest();
        request.setAttribute("status", OptionsValue.STATE_STATUS);//使用状态
        request.setAttribute("type", OptionsValue.PACKAGE_TYPE);//套餐类型
        if (mfp != null) {
            messDto.setMfpId(mfp.getMfpId());
            messDto.setMessName(mfp.getMessName());
            messDto.setMessType(mfp.getMessType());
            messDto.setUseSign(mfp.getUseSign());
            messDto.setMessPeriod(mfp.getMessPeriod());
            messDto.setMessFee(mfp.getMessFee());
            messDto.setMiniPeriod(mfp.getMessPeriod());
            messDto.setSingleFee(mfp.getMessFee());
            messDto.setMfpDesc(mfp.getMfpDesc());
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
        if (messDto.getMessType() == 0) {
            messDto.setMessPeriod(messDto.getMiniPeriod());
            messDto.setMessFee(messDto.getSingleFee());
        }

        @SuppressWarnings("unused")
        MessFeeParam mfp = messFeeParamService.saveMessFeeParam(messDto);
        functionsService.saveFunction("短信费管理", 1, "增加参数：" + messDto.getMfpId());
        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        this.getRequest().setAttribute("url", "message/messageparam!list");
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
            ReturnDTO dto = messFeeParamService.updateMessFeeParam(messDto);
            if (dto.getFlag()) {
                functionsService.saveFunction("短信费管理", 2, "短信参数管理：" + messDto.getMfpId());
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "message/messageparam!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "message/messageparam!list");
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
            messFeeParamService.deleteMessFeeParam(this.getId());
            //在操作日志中生成记录
            functionsService.saveFunction("短信参数管理", 3, "删除短信参数：" + this.getId());
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
     *@Description:检验名称是否存在
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String validateName() {

        ReturnDTO returnDto = new ReturnDTO();

        if (!messFeeParamService.queryMessName(messDto.getMessName(), messDto.getMfpId())) {
            returnDto.setFlag(true);
        }

        return Utils.printInfo(returnDto);

    }

    /**
     *@Title:mfpjsonPageList
     *@Description:参数帮助页面显示
     *@param:@return
     *@param:@throws Exception
     *@return:String
     *@author:张亚运
     *@thorws:
     */
    @SuppressWarnings("unchecked")
    public String mfpjsonPageList() throws Exception {
        this.getSession().setAttribute(Globals.QUERY_PARM, messDto);
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        // 判断排序参数是否有值
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderBy.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {// 如果页面没有要求排序方式，则设置按照会员编号排序
            orderBy.put("mfpId", "desc");
        }
        messDto.setHelpSign(1);
        QueryResult<MessageDTO> result = messFeeParamService.queryAll((messDto.getPage() - 1) * pageNum, pageNum,
                messDto, orderBy);
        List<MessageDTO> messList = result.getResultlist();

        List<List<String>> lists = new ArrayList<List<String>>();
        for (int i = 0; i < messList.size(); i++) {
            // 向页面赋值
            MessageDTO messageDTO = messList.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(messageDTO.getMfpId()));
            strings.add(Utils.getString(messageDTO.getMessName()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.PACKAGE_TYPE, messageDTO.getMessType()));
            strings.add(Utils.getString(messageDTO.getMessPeriod()));
            strings.add(NumberUtil.numberFormat("", messageDTO.getMessFee()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, messageDTO.getUseSign()));
            String operation = "<a href=javascript:secMfp('" + messageDTO.getMfpId() + "','" + messageDTO.getMessName()
                    + "'); title='选择'>[选择]</a>&nbsp;";
            strings.add(operation);
            lists.add(strings);
        }

        PageView pageView = new PageView(messDto.getPage(), result.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView, "mfpQuery"));
        return Utils.printInfo(listInfoDTO);
    }

}

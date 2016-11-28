package com.paySystem.ic.web.action.buss;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.MailServParam;
import com.paySystem.ic.service.buss.MailServParamService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.MailUtils;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.MailServParamDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:MailServerAction
 * @Description:邮件服务器设置
 * @date: 2014-7-4下午12:05:29
 * @author: 张亚运
 * @version: V1.0
 */
@Controller("/mail/mailserver")
@Scope("prototype")
public class MailServerAction extends BaseAction {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 4831309582716558920L;

    @Resource
    MailServParamService mailServParamService;

    @Resource
    FunctionsService functionsService;

    private MailServParamDTO mailServParamDto = new MailServParamDTO();

    public MailServParamDTO getMailServParamDto() {
        return mailServParamDto;
    }

    public void setMailServParamDto(MailServParamDTO mailServParamDto) {
        this.mailServParamDto = mailServParamDto;
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
            mailServParamDto.setStatus(1);
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
            /**如果页面没有要求排序方式，则设置按照分类编号排序*/
            orderby.put("espId", "asc");
        }

        List<MailServParamDTO> listDto = new ArrayList<MailServParamDTO>();
        QueryResult<MailServParamDTO> result = null;
        try {
            result = mailServParamService.queryAll((mailServParamDto.getPage() - 1) * pageNum, pageNum,
                    mailServParamDto, orderby);
            listDto = result.getResultlist();
        }
        catch (Exception e) {
            e.getMessage();
        }
        List<List<String>> lists = new ArrayList<List<String>>();
        for (int i = 0; i < listDto.size(); i++) {
            /**向页面赋值*/
            MailServParamDTO dto = listDto.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(dto.getEspUrl()));
            /**
             * strings.add(Utils.getString(dto.getEspPort()));
             * */
            strings.add(Utils.getString(dto.getEmaccNo()));
            strings.add(Utils.getString(dto.getReplyUrl()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.COND_STATE, dto.getStatus()));
            strings.add(DateTimeTool.dateFormat("", dto.getCreateTime()));
            strings.add(Utils.getString(dto.getOperator()));
            String operation = "";

            if (us.getUserLevel() == 0) {
                if (Utils.checkPermission("sy-6400-03") && dto.getStatus() != 9) {
                    operation += "<a href =mail/mailserver!editUI?mailServParamDto.espId=" + dto.getEspId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }
                /**判断权限在相关操作栏显示相关操作，若状态为删除状态则不显示*/
                if (Utils.checkPermission("sy-6400-04") && dto.getStatus() != 9) {
                    operation += "<a href=javascript:deleteData('mail/mailserver!delete','" + dto.getEspId()
                            + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }
                if (Utils.checkPermission("sy-6400-02")) {
                    operation += "<a href =mail/mailserver!checkUI?mailServParamDto.espId=" + dto.getEspId()
                            + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
                }
            }

            strings.add(operation);
            lists.add(strings);
        }
        PageView pageView = new PageView(mailServParamDto.getPage(), result.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
        return Utils.printInfo(listInfoDTO);
    }

    /**
     *@Description:添加按钮调用方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String addUI() {
        this.setMethod("addSave");
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUSD);
        return INPUT;
    }

    /**
     *@Description:添加保存方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String addSave() {

        MailServParam msp = mailServParamService.saveMailServParam(mailServParamDto);
        functionsService.saveFunction("邮件管理", 1, "添加邮件服务器：" + msp.getEspId());
        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        this.getRequest().setAttribute("url", "mail/mailserver!list");

        return SUCCESS;
    }

    /**
     *@Description:修改按钮调用方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String editUI() {
        this.setMethod("editSave");
        mailServParamDto = mailServParamService.find(mailServParamDto.getEspId());
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUSD);
        return INPUT;
    }

    /**
     *@Description:修改保存按钮
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String editSave() {
        try {
            ReturnDTO dto = mailServParamService.updateMailServParam(mailServParamDto);
            if (dto.getFlag()) {
                functionsService.saveFunction("邮件设置", 2, "邮件服务器修改：" + mailServParamDto.getEspId());
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "mail/mailserver!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "mail/mailserver!list");
                return ERROR;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     *@Description:删除方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String delete() {
        try {
            mailServParamService.deleteMailServParam(Integer.valueOf(this.getId()));
            functionsService.saveFunction("商品分类管理", 3, "删除商品分类：" + mailServParamDto.getEspId());
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
     *@Description:查看方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String checkUI() {
        this.setMethod("checkUI");
        mailServParamDto = mailServParamService.find(mailServParamDto.getEspId());
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUSD);
        return INPUT;
    }

    /**
     *@Description:测试邮件发送
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String sendEmail() {

        ReturnDTO returnDto = new ReturnDTO();
        if (mailServParamDto.getTestEmail() != null) {
            String emailSubject = "MCIU";
            String emailContent = "尊敬的用户您好：" + "这封为系统测试邮件，请勿回复，谢谢！";
            String email = mailServParamDto.getTestEmail();
            try {
                MailUtils.sendMail(mailServParamDto, emailSubject, emailContent, email);
                returnDto.setFlag(true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Utils.printInfo(returnDto);
    }

}

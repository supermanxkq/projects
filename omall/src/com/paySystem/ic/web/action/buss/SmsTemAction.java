package com.paySystem.ic.web.action.buss;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.buss.SmsTem;
import com.paySystem.ic.service.buss.SmsTemService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.buss.SmsTemDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * @ClassName:SmsTemAction
 * @Description:短信常用模版设置Action
 * @date: 2014-7-9下午04:58:22
 * @author: 张亚运
 * @version: V1.0
 */
@Controller("/message/smstemaction")
@Scope("prototype")
public class SmsTemAction extends BaseAction {

    /**
     *@Description:序列号
     */
    private static final long serialVersionUID = 8515439166679881540L;

    @Resource
    SmsTemService smsTemService;

    @Resource
    FunctionsService functionsService;

    private SmsTemDTO smsTemDto = new SmsTemDTO();

    public SmsTemDTO getSmsTemDto() {
        return smsTemDto;
    }

    public void setSmsTemDto(SmsTemDTO smsTemDto) {
        this.smsTemDto = smsTemDto;
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
            this.setMethod("addSave");
            this.getRequest().setAttribute("smsTemName", OptionsValue.SMS_STEMNAME);
            smsTemDto = smsTemService.find(10000000);
            return "list";
        }
        else {
            return "intercepthtml";
        }
    }

    /**
     *@Description:保存短信模版
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String addSave() {

        SmsTemDTO stDto = smsTemService.find(smsTemDto.getStemId());

        /**判断是否有该条数据
         * 1.如果没有该条数据则添加保存
         * 2.如果有该条数据则修改保存
         * */

        if (stDto == null) {
            /**添加保存短信模版*/
            SmsTem st = smsTemService.saveSmsTem(smsTemDto);
            functionsService.saveFunction("短信常用模版", 1, "添加短信常用模版：" + st.getStemId());
            this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
            this.getRequest().setAttribute("url", "message/smstemaction!list");
            return SUCCESS;
        }
        else {
            /**修改保存短信模版*/
            ReturnDTO dto = smsTemService.updateSmsTem(smsTemDto);
            if (dto.getFlag()) {
                functionsService.saveFunction("短信常用模版", 2, "短信常用模版修改：" + smsTemDto.getStemId());
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "message/smstemaction!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "message/smstemaction!list");
                return ERROR;
            }
        }

    }

    /**
     *@Description:根据传过来的Id获取该条信息内容
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String getStem() {

        ReturnDTO returnDto = new ReturnDTO();
        if (smsTemDto.getStemId() != null) {
            try {
                smsTemDto = smsTemService.find(smsTemDto.getStemId());
                returnDto.setFlag(true);
                returnDto.setObj(smsTemDto);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Utils.printInfo(returnDto);

    }

}

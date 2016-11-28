package com.paySystem.ic.web.action.message;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.member.MemberGroupsDetail;
import com.paySystem.ic.bean.member.Members;
import com.paySystem.ic.bean.message.ShortMesSend;
import com.paySystem.ic.service.member.MemberGroupsDetailService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.service.message.MessParamRelationService;
import com.paySystem.ic.service.message.ShortMesSendService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.stock.MessServParamConfigService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.ShortMessUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;
import com.paySystem.ic.web.dto.message.MessageDTO;
import com.paySystem.ic.web.dto.message.ShortMesSendDTO;
import com.paySystem.ic.web.dto.message.SmsMemGroupDTO;
import com.paySystem.ic.web.dto.stock.MessServParamConfigDTO;
import com.paySystem.ic.web.dto.system.UserSession;

@Controller("/message/shortMesSend")
@Scope("prototype")
public class ShortMesSendAction extends BaseAction {

    private static final long serialVersionUID = 1157838270801154543L;

    private ShortMesSendDTO smsDto = new ShortMesSendDTO();

    @Resource
    ShortMesSendService smsService;

    @Resource
    MemberService memService;

    @Resource
    FunctionsService functionsService;
    //短信关系
    @Resource
    MessParamRelationService messParamRelationService;

    @Resource
    MessServParamConfigService messServParamConfigService;

    @Resource
    MemberGroupsDetailService memberGroupsDetailService;

    MembersDTO mem;

    private MessServParamConfigDTO messServParamConfigDTO = new MessServParamConfigDTO();

    public MessServParamConfigDTO getMessServParamConfigDTO() {
        return messServParamConfigDTO;
    }

    public void setMessServParamConfigDTO(MessServParamConfigDTO messServParamConfigDTO) {
        this.messServParamConfigDTO = messServParamConfigDTO;
    }

    private List<MemGroupsDTO> groupList = new ArrayList<MemGroupsDTO>();

    public List<MemGroupsDTO> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<MemGroupsDTO> groupList) {
        this.groupList = groupList;
    }

    public ShortMesSendDTO getSmsDto() {
        return smsDto;
    }

    public void setSmsDto(ShortMesSendDTO smsDto) {
        this.smsDto = smsDto;
    }

    /**
     *@Description:显示页面list方法
     *@author:张国法
     */
    public String list() {
        smsDto.setSmsStatus(0);
        this.getRequest().setAttribute("status", OptionsValue.AUDIT_STATUS);// 使用状态
        this.getRequest().setAttribute("type", OptionsValue.PACKAGE_TYPE);// 套餐类型
        return "list";
    }

    /**
     *@Title:jsonPageList
     *@Description:短信参数查询jsonPage
     *@author:张国法
     */
    @SuppressWarnings("unchecked")
    public String jsonPageList() throws Exception {
        UserSession us = Utils.getUserSession();
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        // 判断排序参数是否有值
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderBy.put(this.getOrderProperty(), this.getOrderProperty());
        }
        else {
            orderBy.put("smsId", "desc");
        }

        QueryResult<ShortMesSend> terResult = smsService.querySmsByCond((smsDto.getPage() - 1) * pageNum, pageNum,
                smsDto, orderBy);
        List<ShortMesSend> smslist = terResult.getResultlist();
        List<List<String>> lists = new ArrayList<List<String>>();

        for (int i = 0; i < smslist.size(); i++) {
            // 向页面赋值
            ShortMesSend smsDto = smslist.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(smsDto.getSmsTitle()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.PEOPLE_TYPE, smsDto.getSmsType()));// 使用人群
            if(null!=smsDto.getUserType()){
            	if (smsDto.getUserType() == 0) {
                    strings.add("平台");
                }
                if (smsDto.getUserType() == 1) {

                    strings.add(Utils.getString(smsService.findOrgan(smsDto.getMerOrgId())));
                }
                if (smsDto.getUserType() == 2) {
                    strings.add(Utils.getString(smsService.findMerName(smsDto.getMerOrgId())));
                }
            }else{
            	strings.add("");
            }
            
            strings.add(Utils.getString(smsDto.getNum()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.AUDIT_STATUS, smsDto.getSmsStatus()));// 审核状态
            if(StringUtils.isNotEmpty(smsDto.getOperator()))
            	strings.add(Utils.getString(smsService.findUser(smsDto.getOperator())));
            else
            	strings.add("");
            strings.add(Utils.getString(DateTimeTool.dateFormat("", smsDto.getCreateTime())));
            strings.add(Utils.getString(DateTimeTool.dateFormat("", smsDto.getAuditTime())));
            String operation = "";

            if (Utils.checkPermission("sy-1603-01")) {
                operation += "<a href=message/shortMesSend!checkUI?smsDto.smsId=" + smsDto.getSmsId() + "  title='查看'>"
                        + Globals.IMG_VIEW + "</a>&nbsp;";
            }
            if (Utils.checkPermission("sy-1603-05") && smsDto.getSmsStatus() == 0) {
                operation += "<a href=javascript:void(0) onclick=loadData('" + smsDto.getSmsId()
                        + "',this) title='审核'>" + Globals.IMG_AUDIT + "</a> &nbsp;";
            }

            if (us.getUserLevel() != 2) {
                if (Utils.checkPermission("sy-1603-03") && smsDto.getSmsStatus() == 2
                        && smsDto.getMerOrgId().equals(us.getOrganId())) {
                    operation += "<a href=message/shortMesSend!editUI?smsDto.smsId=" + smsDto.getSmsId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }

                if (Utils.checkPermission("sy-1603-04") && smsDto.getSmsStatus() == 2
                        && smsDto.getMerOrgId().equals(us.getOrganId())) {
                    operation += "<a href=javascript:deleteData('message/shortMesSend!delete','" + smsDto.getSmsId()
                            + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }
            }
            else {
                if (Utils.checkPermission("sy-1603-03") && smsDto.getSmsStatus() == 2
                        && smsDto.getMerOrgId().equals(us.getMerId())) {
                    operation += "<a href=message/shortMesSend!editUI?smsDto.smsId=" + smsDto.getSmsId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }

                if (Utils.checkPermission("sy-1603-04") && smsDto.getSmsStatus() == 2
                        && smsDto.getMerOrgId().equals(us.getMerId())) {
                    operation += "<a href=javascript:deleteData('message/shortMesSend!delete','" + smsDto.getSmsId()
                            + "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }
            }

            strings.add(operation);
            lists.add(strings);
        }

        PageView pageView = new PageView(smsDto.getPage(), terResult.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
        return Utils.printInfo(listInfoDTO);

    }

    /**
     *@Description:查看方法
     *@return:String
     *@author: 张亚运
     *@throws:
     */
    public String checkUI() {

        this.setMethod("checkDetail");
        Integer smsId = smsDto.getSmsId();
        List<Object[]> objList = null;
        /** 将所有群组遍历出来并传于页面 */
        try {
            objList = smsService.getMemGroups();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        List<MemGroupsDTO> memGroups = new ArrayList<MemGroupsDTO>();
        List<SmsMemGroupDTO> smsMemGroups = smsService.findMemGroup(smsId);
        String s = ",";
        for(SmsMemGroupDTO smsMemGroupDTO : smsMemGroups){
        	s+=smsMemGroupDTO.getGroupId()+",";
        }
        for (int i = 0; i < objList.size(); i++) {
        	MemGroupsDTO mg = new MemGroupsDTO();
            Object[] obj = objList.get(i);
            if (null != obj[1] && !"".equals(obj[1])) {
                mg.setGroupId(Integer.parseInt(obj[0].toString()));
                mg.setGroupName(obj[1].toString());
                mg.setIsChecked(1);
            }
            if(s.indexOf(mg.getGroupId()+"")!=-1)
            memGroups.add(mg);
        }
        this.getRequest().setAttribute("memGroups", memGroups);

        if (smsDto != null) {

            ShortMesSend shortMesSend = smsService.find(smsId);

            if (shortMesSend != null) {

                if (shortMesSend.getUserType() == 0) {
                    smsDto.setMerOrgName("平台");
                }
                if (shortMesSend.getUserType() == 1) {

                    smsDto.setMerOrgName(smsService.findOrgan(shortMesSend.getMerOrgId()));
                }
                if (shortMesSend.getUserType() == 2) {
                    smsDto.setMerOrgName(smsService.findMerName(shortMesSend.getMerOrgId()));
                }

                smsDto.setSmsTitle(Utils.getString(shortMesSend.getSmsTitle()));
                smsDto.setSmsContent(shortMesSend.getSmsContent());
                smsDto.setSmsType(shortMesSend.getSmsType());
                smsDto.setTotalPrice(shortMesSend.getTotalPrice());
                smsDto.setNum(shortMesSend.getNum());
                smsDto.setMerOrgId(shortMesSend.getMerOrgId());
                smsDto.setCreateDate(DateTimeTool.dateFormat("", shortMesSend.getCreateTime()));
                smsDto.setAuditDate(DateTimeTool.dateFormat("", shortMesSend.getAuditTime()));
            }

            /** 加载所有短信模版 */
            //List<OptionsInteger> smsTems = new ArrayList<OptionsInteger>();
            //smsTems.add(new OptionsInteger(0, "请选择..."));
            //smsTems.addAll(smsTemService.getSmsOption());
            //this.getRequest().setAttribute("smsTems", smsTems);
            this.getRequest().setAttribute("type", OptionsValue.PEOPLE_TYPE);// 发送人群
            this.getRequest().setAttribute("status", OptionsValue.AUDIT_STATUS);// 审核状态
            return INPUT;
        }
        return ERROR;
    }

    /**
     *@Title:addUI
     *@Description:添加按钮执行方法
     *@param:@return
     *@return:String
     *@author:张国法
     *@thorws:
     */
    public String addUI() {

        this.setMethod("addSave");
        this.getRequest().setAttribute("status", OptionsValue.AUDIT_STATUS);// 使用状态
        this.getRequest().setAttribute("type", OptionsValue.PEOPLE_TYPE);// 发送人群
        /** 加载所有会员群组 */
        List<Object[]> objList = smsService.getMemGroups();
        List<MemGroupsDTO> memGroups = new ArrayList<MemGroupsDTO>();
        for (int i = 0; i < objList.size(); i++) {
        	MemGroupsDTO mg = new MemGroupsDTO();
            Object[] obj = objList.get(i);
            if (null != obj[1] && !"".equals(obj[1])) {
                mg.setGroupId(Integer.parseInt(obj[0].toString()));
                mg.setGroupName(obj[1].toString());
            }
            memGroups.add(mg);
        }
        this.getRequest().setAttribute("memGroups", memGroups);

        return INPUT;
    }

    /**
     *@Title:addSave
     *@Description:保存群发短信信息
     */
    public String addSave() throws Exception {
        UserSession us = Utils.getUserSession();
        int num = 0;
        if (smsDto.getSmsType() == 0) {
        	for(MemGroupsDTO memGroupsDTO :groupList){
	            List<MemberGroupsDetail> memberGroupsDetail = memberGroupsDetailService.findByGroupId(memGroupsDTO.getGroupId());
	            num = num + memberGroupsDetail.size();
        	}
        	smsDto.setSmsType(0);
        }
        smsDto.setNum(num);
        // 计算本次发送费用
        BigDecimal bigDecimal = null;
        MessageDTO messageDTO = messParamRelationService.queryByOrgId();
    	if(null!=messageDTO.getMessFee()){
    		if(null!=messageDTO.getMessType()&&messageDTO.getMessType()==0){
    			bigDecimal = messageDTO.getMessFee().multiply(new BigDecimal(smsDto.getNum()+""));
    		}else if(null!=messageDTO.getMessType()&&messageDTO.getMessType()==1){
    			bigDecimal = new BigDecimal("0");
    		}
    	}
    	else{
    		this.getRequest().setAttribute("result", "短信套餐不存在!");
			this.getRequest().setAttribute("url", "message/shortMesSend!list");
			return ERROR;
    	}

        smsDto.setTotalPrice(bigDecimal);
        smsDto.setSmsStatus(0);
        smsDto.setUserType(us.getUserLevel());
        smsService.addSMSInfo(smsDto, groupList);
        functionsService.saveFunction("短信群发管理", 1, "增加群发短信：" + smsDto.getSmsId());
        this.getRequest().setAttribute("status", OptionsValue.AUDIT_STATUS);// 使用状态
        this.getRequest().setAttribute("type", OptionsValue.PEOPLE_TYPE);// 发送人群
        this.getRequest().setAttribute("url", "message/shortMesSend!list");
        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        return SUCCESS;
    }

    /**
     * 进入修改短信信息
     * 
     * @return
     * @throws Exception
     */
    public String editUI() throws Exception {
        // 设置form中表单的方法
        this.setMethod("editSave");
        Integer smsId = smsDto.getSmsId();
        /** 加载所有会员群组 */
        List<Object[]> objList = smsService.getMemGroups();
        List<MemGroupsDTO> memGroups = new ArrayList<MemGroupsDTO>();
        List<SmsMemGroupDTO> smsMemGroups = smsService.findMemGroup(smsId);
        String s = ",";
        for(SmsMemGroupDTO smsMemGroupDTO : smsMemGroups){
        	s+=smsMemGroupDTO.getGroupId()+",";
        }
        for (int i = 0; i < objList.size(); i++) {
        	MemGroupsDTO mg = new MemGroupsDTO();
            Object[] obj = objList.get(i);
            if (null != obj[1] && !"".equals(obj[1])) {
                mg.setGroupId(Integer.parseInt(obj[0].toString()));
                mg.setGroupName(obj[1].toString());
            }
            if(s.indexOf(mg.getGroupId()+"")!=-1)
            	mg.setIsChecked(1);
            memGroups.add(mg);
        }
        this.getRequest().setAttribute("memGroups", memGroups);

        if (smsDto != null) {

            ShortMesSend shortMesSend = smsService.find(smsDto.getSmsId());

            if (smsDto != null) {
                smsDto.setSmsTitle(Utils.getString(shortMesSend.getSmsTitle()));
                smsDto.setSmsContent(shortMesSend.getSmsContent());
                smsDto.setSmsType(shortMesSend.getSmsType());
                smsDto.setMerOrgId(shortMesSend.getMerOrgId());
            }

            this.getRequest().setAttribute("type", OptionsValue.PEOPLE_TYPE);// 发送人群
            this.getRequest().setAttribute("status", OptionsValue.AUDIT_STATUS);// 审核状态
            this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
            return INPUT;
        }
        return ERROR;
    }

    /** 编辑（修改）保存 */
    public String editSave() {
        int num = 0;
        try {
            ShortMesSend shortMesSend = smsService.find(smsDto.getSmsId());
            UserSession us = Utils.getUserSession();
            // 判断发送目标群体
            
            if (smsDto.getSmsType() == 0) {
            	// 获取本次发送短信数量
            	for(MemGroupsDTO memGroupsDTO :groupList){
    	            List<MemberGroupsDetail> memberGroupsDetail = memberGroupsDetailService.findByGroupId(memGroupsDTO.getGroupId());
    	            num = num + memberGroupsDetail.size();
            	}
            	smsDto.setSmsType(0);
            }
            smsDto.setNum(num);

            BigDecimal bigDecimal = null;
            // 计算本次发送费用
            MessageDTO messageDTO = messParamRelationService.queryByOrgId();
        	if(null!=messageDTO.getMessFee()){
        		if(null!=messageDTO.getMessType()&&messageDTO.getMessType()==0){
        			bigDecimal = messageDTO.getMessFee().multiply(new BigDecimal(smsDto.getNum()+""));
        		}else if(null!=messageDTO.getMessType()&&messageDTO.getMessType()==1){
        			bigDecimal = new BigDecimal("0");
        		}
        	}
        	else{
        		this.getRequest().setAttribute("result", "短信套餐不存在!");
				this.getRequest().setAttribute("url", "message/shortMesSend!list");
				return ERROR;
        	}
            smsDto.setTotalPrice(bigDecimal);
            smsDto.setMerOrgId(shortMesSend.getMerOrgId());
            smsDto.setSmsStatus(0);
            smsDto.setUserType(us.getUserLevel());
            ReturnDTO dto = smsService.update(smsDto, groupList);
            if (dto.getFlag()) {
                functionsService.saveFunction("修改群发短信管理", 2, "修改群发短信内容：" + Utils.getString(smsDto.getSmsId()));
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "message/shortMesSend!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "message/shortMesSend!list");
                return ERROR;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    // 删除短信发送信息
    public String delete() {
        try {
            UserSession us = Utils.getUserSession();
            ShortMesSend sms = smsService.find(Integer.parseInt(this.getId()));
            smsDto.setSmsTitle(Utils.getString(sms.getSmsTitle()));
            smsDto.setSmsId(sms.getSmsId());
            smsDto.setSmsContent(sms.getSmsContent());
            smsDto.setSmsType(sms.getSmsType());
            smsDto.setTotalPrice(sms.getTotalPrice());
            smsDto.setNum(sms.getNum());
            smsDto.setMerOrgId(sms.getMerOrgId());
            smsDto.setAuditTime(sms.getAuditTime());
            smsDto.setSmsStatus(9);
            smsDto.setUserType(us.getUserLevel());
            smsService.update(smsDto, groupList);

            functionsService.saveFunction("删除短信群发管理", 3, "删除短信：" + sms.getSmsId());
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
     *@Title:loadOrder
     *@Description:加载需审核的短信信息
     *@param:@return
     *@return:String
     *@author:张国法
     *@thorws:
     */
    public String loadOrder() {
        ReturnDTO dto = new ReturnDTO();
        smsDto = smsService.findShortMesSend(smsDto.getSmsId());
        if (smsDto != null) {
            dto.setFlag(true);
            dto.setObj(smsDto);
        }
        return Utils.printInfo(dto);
    }

    /**
     *@Description:审核通过方法
     *@author:张国法
     * @throws Exception 
     */
    public String auditSuccess() throws Exception {

        ShortMesSend sms = smsService.find(smsDto.getSmsId());

        smsDto.setSmsTitle(Utils.getString(sms.getSmsTitle()));
        smsDto.setSmsId(sms.getSmsId());
        smsDto.setSmsContent(sms.getSmsContent());
        smsDto.setSmsType(sms.getSmsType());
        smsDto.setTotalPrice(sms.getTotalPrice());
        smsDto.setNum(sms.getNum());
        smsDto.setMerOrgId(sms.getMerOrgId());
        smsDto.setCreateTime(sms.getCreateTime());
        smsDto.setAuditTime(smsService.getSysTime());
        smsDto.setSmsStatus(1);
        smsDto.setUserType(sms.getUserType());
        smsService.update(smsDto, groupList);

        this.getRequest().setAttribute("type", OptionsValue.PEOPLE_TYPE);// 发送人群
        this.getRequest().setAttribute("status", OptionsValue.AUDIT_STATUS);// 审核状态

        StringBuffer sb = new StringBuffer();

        List<SmsMemGroupDTO> smsMemGroups = smsService.findMemGroup(smsDto.getSmsId());
        // 根据所选目标群体进行会员手机号获取
        if (smsDto.getSmsType() == 0) {
            // 获取本次发送短信数量
        	for(SmsMemGroupDTO smsMemGroupDTO :smsMemGroups){
	            List<MemberGroupsDetail> memberGroupsDetail = memberGroupsDetailService.findByGroupId(smsMemGroupDTO.getGroupId());
	            for(MemberGroupsDetail detail :memberGroupsDetail){
	                mem = (MembersDTO) EntityDtoConverter.bean2Dto(detail.getMembers(), new MembersDTO());
	                if (null != mem.getTeleNo() && !"".equals(mem.getTeleNo())) {
	                    sb.append(mem.getTeleNo() + ",");
	                }
	            }
        	}
        }
        String phone = sb.substring(0, sb.length() - 1);

        //messServParamConfigDTO.setMobileValue(phone);
        //messServParamConfigDTO.setContent(sms.getSmsContent());
        //messServParamConfigService.sendMessageByParamType(messServParamConfigDTO);
        ShortMessUtil.sendMessage(phone, sms.getSmsContent());

        return "list";

    }

    /**
     *@Description:审核失败调用方法
     *@author:张国法
     */
    public String auditFailure() {

        ShortMesSend sms = smsService.find(smsDto.getSmsId());
        smsDto.setSmsTitle(Utils.getString(sms.getSmsTitle()));
        smsDto.setSmsId(sms.getSmsId());
        smsDto.setSmsContent(sms.getSmsContent());
        smsDto.setSmsType(sms.getSmsType());
        smsDto.setTotalPrice(sms.getTotalPrice());
        smsDto.setNum(sms.getNum());
        smsDto.setMerOrgId(sms.getMerOrgId());
        smsDto.setCreateTime(sms.getCreateTime());
        smsDto.setAuditTime(smsService.getSysTime());
        smsDto.setSmsStatus(2);
        smsDto.setUserType(sms.getUserType());
        smsService.update(smsDto, groupList);
        this.getRequest().setAttribute("type", OptionsValue.PEOPLE_TYPE);// 发送人群
        this.getRequest().setAttribute("status", OptionsValue.AUDIT_STATUS);// 审核状态
        return "list";

    }


    /**
     *@Description:得到所有群组下的会员及电话
     *@return:StringBuffer
     *@author: 张亚运
     *@throws:
     */
    public StringBuffer getMembers(String groups) {

        StringBuffer sb = new StringBuffer();
        /** 群组ID数组 **/
        String[] groupArr = groups.split(";");
        /** 会员list集合 **/
        List<Members> list = null;
        /** 存放会员list **/
        List<List<Members>> listt = new ArrayList<List<Members>>();
        /** 会员电话数组 **/
        String[] memTelNo = null;
        List<MemberGroupsDetail> groupList = new ArrayList<MemberGroupsDetail>();
        if (groupArr != null) {
            if (groupArr.length == 1) {
                groupList = memberGroupsDetailService.findByGroupId(Integer.parseInt(groupArr[0]));
                if (groupList != null && groupList.size() > 0) {
                    list = new ArrayList<Members>();
                    for (int i = 0; i < groupList.size(); i++) {
                        list.add(groupList.get(i).getMembers());
                    }
                }
                /** 通过会员list集合得到邮件地址数组 **/
                memTelNo = getTeleNoArr(list);

            }
            else {
                for (int i = 0; i < groupArr.length; i++) {
                    groupList = memberGroupsDetailService.findByGroupId(Integer.parseInt(groupArr[i]));
                    if (groupList != null && groupList.size() > 0) {
                        list = new ArrayList<Members>();
                        for (int j = 0; j < groupList.size(); j++) {
                            if (groupList.get(j).getMembers() != null) {
                                list.add(groupList.get(j).getMembers());
                            }
                        }

                        listt.add(list);

                    }
                }
                if (listt != null && listt.size() > 0) {
                    memTelNo = getTele(listt);
                }

            }

            for (int i = 0; i < memTelNo.length; i++) {
                sb.append(memTelNo[i] + ",");
            }

            smsDto.setNum(memTelNo.length);
        }
        return sb;
    }

    /***
     * 
     *@Title:getTele
     *@TODO:得到电话号码集合
     *@data:2014-3-20
     *@param:@param listt
     *@param:@return
     *@return:String[]
     *@author:孟凡岭
     *@thorws:
     */
    public String[] getTele(List<List<Members>> listt) {
        String[] memTele = null;
        List<String> list = new ArrayList<String>();
        if (listt != null && listt.size() > 0) {
            /** 第一次遍历list集合 **/
            for (int i = 0; i < listt.size(); i++) {
                if (listt.get(i) != null) {
                    memTele = getTeleNoArr(listt.get(i));
                }
                /** 第二次遍历数组 **/
                if (memTele != null) {
                    for (int j = 0; j < memTele.length; j++) {
                        if (memTele[j] != null) {
                            list.add(memTele[j]);
                        }
                    }
                }
            }
        }

        return removeEqual(list.toArray());
    }

    /**
     * 
     *@Title:removeEqual
     *@TODO:去掉数组中重复的字符串
     *@data:2014-3-20
     *@param:@param array
     *@param:@return
     *@return:String[]
     *@author:孟凡岭
     *@thorws:
     */
    public String[] removeEqual(Object[] array) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            if (!list.contains(array[i])) {
                list.add(array[i].toString());
            }
        }
        String[] array2 = list.toArray(new String[list.size()]);
        return array2;
    }

    /***
     * 
     *@Title:getTeleNoArr
     *@TODO:从list中取手机号，转成数组
     *@data:2014-3-20
     *@param:@param list
     *@param:@return
     *@return:String[]
     *@author:孟凡岭
     *@thorws:
     */
    public String[] getTeleNoArr(List<Members> list) {
        String[] memTeleNo = null;
        if (list != null) {
            if (list.size() == 1) {
                memTeleNo = new String[] { list.get(0).getTeleNo() };
            }
            else if (list.size() > 1) {
                memTeleNo = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getTeleNo() != null) {
                        memTeleNo[i] = list.get(i).getTeleNo();
                    }
                }
            }
        }
        else {
            return null;
        }

        return memTeleNo;
    }
}

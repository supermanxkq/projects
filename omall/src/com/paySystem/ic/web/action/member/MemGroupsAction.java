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
import com.paySystem.ic.bean.member.MemGroups;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.bean.member.MemberGroupsDetail;
import com.paySystem.ic.bean.member.Members;
import com.paySystem.ic.service.base.OrgansService;
import com.paySystem.ic.service.member.MemGroupsService;
import com.paySystem.ic.service.member.MemberGroupsDetailService;
import com.paySystem.ic.service.member.MemberService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.member.MemGroupsDTO;
import com.paySystem.ic.web.dto.member.MemberGroupsDetailDTO;
import com.paySystem.ic.web.dto.member.MembersDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsString;

@Controller("/member/memGroups")
@Scope("prototype")
public class MemGroupsAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Resource
    private MemGroupsService memGroupsServiceImpl;

    @Resource
    private FunctionsService functionsService;

    @Resource
    private OrgansService organsService;

    @Resource
    private MemberService memberService;

    @Resource
    private MemberGroupsDetailService memberGroupsDetailService;

    private MemGroupsDTO memGroupsDTO = new MemGroupsDTO();

    private MemberGroupsDetailDTO memberGroupsDetailDTO = new MemberGroupsDetailDTO();

    private MembersDTO memDto = new MembersDTO();

    private List<Member> members;

    private List<String> right;

    private List<String> left;

    private String rights;

    /***
     * 加载页面
     * 
     * @Title:list
     *@param:@return
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    public String list() {
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);
        return "list";
    }

    /***
     * 查询群组所有信息
     * 
     * @Title:jsonPageList
     *@param:@return
     *@param:@throws Exception
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    @SuppressWarnings("unchecked")
    public String jsonPageList() throws Exception {
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
            orderby.put(this.getOrderProperty(), this.getOrderDirection());
        }
        else {// 如果页面没有要求排序方式，则设置按照会员编号排序
            orderby.put("groupId", "desc");
        }
        QueryResult<MemGroups> queryResult = memGroupsServiceImpl.queryResult((memGroupsDTO.getPage() - 1) * pageNum,
                pageNum, memGroupsDTO, orderby);
        List<MemGroups> list = queryResult.getResultlist();
        List<List<String>> lists = new ArrayList<List<String>>();
        String orgName = null;
        for (int i = 0; i < list.size(); i++) {
            MemGroups memGroups = list.get(i);
            List<String> strings = new ArrayList<String>();
            strings.add(String.valueOf(i + 1));
            strings.add(Utils.getString(memGroups.getGroupId()));
            strings.add(memGroups.getGroupName());
            strings.add(Utils.getString(memGroups.getOrgans().getName()));
            strings.add(Utils.getString(memGroups.getUserName()));
            strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", memGroups.getCreateTime()));
            strings.add(Utils.getOptionsIntegerName(OptionsValue.STATE_STATUS, memGroups.getStatus()));
            strings.add(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", memGroups.getUpdateTime()));
            String operation = "";
            if (Utils.checkPermission("sy-1305-01")) {
                operation += "<a href=member/memGroups!checkUI?memGroupsDTO.groupId=" + memGroups.getGroupId()
                        + " title='查看'>" + Globals.IMG_VIEW + "</a>&nbsp;";
            }
            if (Utils.checkPermission("sy-1305-03")) {
                if (memGroups.getStatus() != 9) {
                    operation += "<a href=member/memGroups!editUI?memGroupsDTO.groupId=" + memGroups.getGroupId()
                            + " title='修改'>" + Globals.IMG_EDIT + "</a>&nbsp;";
                }
            }
            if (Utils.checkPermission("sy-1305-04")) {
                if (memGroups.getStatus() != 9) {
                    operation += "<a href=javascript:deleteData('member/memGroups!delete','" + memGroups.getGroupId()
                            + "') title='刪除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
                }
            }
            strings.add(operation);
            lists.add(strings);
        }
        PageView pageView = new PageView(memGroupsDTO.getPage(), queryResult.getTotalrecord());
        ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
        return Utils.printInfo(listInfoDTO);
    }

    /***
     * 进入修改会员群组信息
     * 
     * @Title:editUI
     *@param:@return
     *@param:@throws Exception
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    public String editUI() throws Exception {
        this.setMethod("editSave");
        Integer groupID = memGroupsDTO.getGroupId();
        MemGroups memGroups = memGroupsServiceImpl.find(groupID);
        UserSession us = Utils.getUserSession();
        memGroupsDTO.setOrganId(us.getOrganId());
        memGroupsDTO.setName(us.getOrganName());
        memGroupsDTO.setGroupId(memGroups.getGroupId());
        memGroupsDTO.setGroupName(memGroups.getGroupName());
        memGroupsDTO.setStatus(memGroups.getStatus().toString());
        memGroupsDTO.setGroupDesc(memGroups.getGroupDesc());
        memGroupsDTO.setCreateTime(memGroups.getCreateTime());
        memGroupsDTO.setUserName(memGroups.getUserName());
        // List<MemGroupsDTO> list = memGroupsServiceImpl
        // .queryAll(us.getOrganId());
        // List<OptionsString> listt = new ArrayList<OptionsString>();
        // for (int i = 0; i < list.size(); i++) {
        // listt.add(new OptionsString(list.get(i).getGroupId().toString(),
        // list.get(i).getGroupId().toString()));
        // }
        // 定义已添加的群组会员
        List<OptionsString> groupList = new ArrayList<OptionsString>();
        // 待添加的群组会员（不能包含已添加的群组会员）
        List<OptionsString> memberList = new ArrayList<OptionsString>();
        groupList = memGroupsServiceImpl.getMemberByGroupId(memGroupsDTO.getGroupId());
        this.getRequest().setAttribute("groupslist", groupList);
        this.getRequest().setAttribute("memerslist", memberList);
        // this.getRequest().setAttribute("groupId", listt);
        this.getRequest().setAttribute("personTypeValues", OptionsValue.PERSONID_TYPE);
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);// 状态
        this.getRequest().setAttribute("sexValues", OptionsValue.SEX_STATUS);
        this.getRequest().setAttribute("areaIdValues", OptionsValue.AREA_VALUE);// 地区类型
        return INPUT;
    }

    /***
     * 编辑保存
     * 
     * @Title:editSave
     *@param:@return
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    public String editSave() {
        right = getStringList(rights);
        try {
            ReturnDTO dto = memGroupsServiceImpl.update(memGroupsDTO, right);
            if (dto.getFlag()) {
                functionsService.saveFunction("会员群组管理", 2, "修改会员群组：" + memGroupsDTO.getGroupId());
                this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
                this.getRequest().setAttribute("url", "member/memGroups!list");
                return SUCCESS;
            }
            else {
                this.getRequest().setAttribute("result", dto.getMsg());
                this.getRequest().setAttribute("url", "member/memGroups!list");
                return ERROR;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    private List<String> getStringList(String rights) {
        String[] str = rights.split(";");
        List<String> list = new ArrayList<String>();
        if (str != null) {
            for (int i = 0; i < str.length; i++) {
                list.add(str[i]);
            }
        }
        return list;
    }

    /***
     * 删除群组会员
     * 
     * @Title:delete
     *@param:@return
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    public String delete() {
        try {
            // ID 值得是
            MemGroups memGroups = memGroupsServiceImpl.find(Integer.valueOf(this.getId()));
            memGroups.setStatus(9);
            memGroupsServiceImpl.update(memGroups);
            functionsService.saveFunction("会员群组管理", 3, "删除会员群组：" + memGroups.getGroupId().toString());
            this.ajaxResult = "ajaxsuccess";
        }
        catch (Exception e) {
            e.printStackTrace();
            this.ajaxResult = "ajaxfailure";
            this.msgResult = e.getMessage();
        }
        return this.ajaxResult;
    }

    /***
     * 添加页面信息
     * 
     * @Title:addUI
     *@param:@return
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    public String addUI() {
        this.setMethod("addSave");
        UserSession us = Utils.getUserSession();
        MemGroupsDTO m = new MemGroupsDTO();
        m.setOrganId(us.getOrganId());
        m.setName(us.getOrganName());
        List<MemGroupsDTO> list = memGroupsServiceImpl.queryAll(us.getOrganId());
        List<OptionsString> listt = new ArrayList<OptionsString>();
        for (int i = 0; i < list.size(); i++) {
            listt.add(new OptionsString(list.get(i).getGroupId().toString(), list.get(i).getGroupId().toString()));
        }
        // 定义已添加的群组会员
        List<OptionsString> groupList = new ArrayList<OptionsString>();
        // 待添加的群组会员（不能包含已添加的群组会员）
        List<OptionsString> memberList = new ArrayList<OptionsString>();

        this.getRequest().setAttribute("groupslist", groupList);
        this.getRequest().setAttribute("memerslist", memberList);
        this.getRequest().setAttribute("groupId", listt);
        this.getRequest().setAttribute("personTypeValues", OptionsValue.PERSONID_TYPE);
        this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);// 状态
        this.getRequest().setAttribute("sexValues", OptionsValue.SEX_STATUS);
        this.getRequest().setAttribute("areaIdValues", OptionsValue.AREA_VALUE);// 地区类型
        return INPUT;
    }

    /***
     * 添加群组会员信息
     * 
     * @Title:addSave
     *@param:@return
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    public String addSave() {
        right = getStringList(rights);
        memGroupsDTO.setUpdateTime(memGroupsServiceImpl.getSysTime());
        memGroupsDTO.setCreateTime(memGroupsServiceImpl.getSysTime());
        memGroupsDTO.setMemId(right);
        memGroupsServiceImpl.save(memGroupsDTO, right);
        this.getRequest().setAttribute("result", this.getText("operation.success.notice"));
        this.getRequest().setAttribute("url", "member/memGroups!list");
        return SUCCESS;
    }

    /***
     * 群组会员信息查看
     * 
     * @Title:checkUI
     *@param:@return
     *@param:@throws Exception
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    public String checkUI() throws Exception {
        MemberGroupsDetailDTO m = new MemberGroupsDetailDTO();
        List<Members> memberList = new ArrayList<Members>();
        List<MemberGroupsDetail> list = memberGroupsDetailService.findByGroupId(memGroupsDTO.getGroupId());
        for (int i = 0; i < list.size(); i++) {
            memberList.add(list.get(i).getMembers());
        }
        if (memGroupsDTO != null) {
            Integer groupID = memGroupsDTO.getGroupId();
            MemGroups memGroups = memGroupsServiceImpl.find(groupID);
            if (memGroupsDTO != null) {
                memGroupsDTO.setGroupId(memGroups.getGroupId());
                memGroupsDTO.setGroupName((memGroups.getGroupName()));
                memGroupsDTO.setStatus(memGroups.getStatus().toString());
                memGroupsDTO.setCreateTime(DateTimeTool.doDateFormatStringBeginningOfyyyymmdd000000(memGroups
                        .getCreateTime()));
                if (memGroups.getStatus() == 0) {
                    memGroupsDTO.setStatus(String.valueOf("禁用"));
                }
                else if (memGroups.getStatus() == 1) {
                    memGroupsDTO.setStatus(String.valueOf("启用"));
                }
                else {
                    memGroupsDTO.setStatus(String.valueOf("删除"));
                }
                memGroupsDTO.setGroupDesc(Utils.getString(memGroups.getGroupDesc()));
                memGroupsDTO.setUserName(Utils.getString(memGroups.getUserName()));
                memGroupsDTO.setOrganId(Utils.getString(memGroups.getOrgans()));
                memGroupsDTO.setName(memGroups.getOrgans().getName());
                memGroupsDTO.setUpdateTime(DateTimeTool.doDateFormatStringBeginningOfyyyymmdd000000(memGroups
                        .getUpdateTime()));
                memGroupsDTO.setMember(memberList);
            }
            this.getRequest().setAttribute("personTypeValues", OptionsValue.PERSONID_TYPE);
            this.getRequest().setAttribute("statusValues", OptionsValue.STATE_STATUS);// 状态
            this.getRequest().setAttribute("sexValues", OptionsValue.SEX_STATUS);
            this.getRequest().setAttribute("areaIdValues", OptionsValue.AREA_VALUE);// 地区类型
            return "look";
        }
        this.setMethod("checkUI");
        return "look";
    }

    /**
     *@Title:testGroupName
     *@Description:检测群组名称是否重复
     *@Return:String
     *@author:徐凯强
     *@Date:2014-8-26下午07:56:35
     */
    public String testGroupName() {
        ReturnDTO returnDTO = new ReturnDTO();
        if (memGroupsServiceImpl.checkGroupsNameExsis(memGroupsDTO, this.getMethod())) {
            returnDTO.setFlag(true);
        }
        else {
            returnDTO.setFlag(false);
        }
        return Utils.printInfo(returnDTO);

    }

    /***
     * 查询会员信息
     * 
     * @Title:queryAllList
     *@param:@return
     *@return:String
     *@author:解文侠
     *@thorws:
     */
    public String queryAllList() {

        List<OptionsString> listt = memberService.findMemOptionString(memDto, memGroupsDTO.getGroupId(), memGroupsDTO
                .getRightSelect());
        return Utils.printInfo(listt);
    }

    public MembersDTO getMemDto() {
        return memDto;
    }

    public void setMemDto(MembersDTO memDto) {
        this.memDto = memDto;
    }

    public MemGroupsService getMemGroupsServiceImpl() {
        return memGroupsServiceImpl;
    }

    public void setMemGroupsServiceImpl(MemGroupsService memGroupsServiceImpl) {
        this.memGroupsServiceImpl = memGroupsServiceImpl;
    }

    public FunctionsService getFunctionsService() {
        return functionsService;
    }

    public void setFunctionsService(FunctionsService functionsService) {
        this.functionsService = functionsService;
    }

    public OrgansService getOrgansService() {
        return organsService;
    }

    public void setOrgansService(OrgansService organsService) {
        this.organsService = organsService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public List<String> getRight() {
        return right;
    }

    public void setRight(List<String> right) {
        this.right = right;
    }

    public List<String> getLeft() {
        return left;
    }

    public void setLeft(List<String> left) {
        this.left = left;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public MemGroupsDTO getMemGroupsDTO() {
        return memGroupsDTO;
    }

    public void setMemGroupsDTO(MemGroupsDTO memGroupsDTO) {
        this.memGroupsDTO = memGroupsDTO;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return members;
    }

    public MemberGroupsDetailDTO getMemberGroupsDetailDTO() {
        return memberGroupsDetailDTO;
    }

    public void setMemberGroupsDetailDTO(MemberGroupsDetailDTO memberGroupsDetailDTO) {
        this.memberGroupsDetailDTO = memberGroupsDetailDTO;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

}

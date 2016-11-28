package com.paySystem.ic.service.base.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.MerResetPwd;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.bean.system.Role;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.dao.base.MerBussDao;
import com.paySystem.ic.dao.base.MerResetPwdDao;
import com.paySystem.ic.dao.base.MerStoreApplyDao;
import com.paySystem.ic.dao.base.MerchantsDao;
import com.paySystem.ic.dao.base.OrgansDao;
import com.paySystem.ic.dao.base.StoreInfoDAO;
import com.paySystem.ic.dao.base.UndealServiceNumDAO;
import com.paySystem.ic.dao.base.impl.MerResetPwdDaoImpl;
import com.paySystem.ic.dao.system.UserDao;
import com.paySystem.ic.service.base.MerStoreApplyService;
import com.paySystem.ic.service.evaluation.MerCreditTotalService;
import com.paySystem.ic.service.system.RoleService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.MD5;
import com.paySystem.ic.utils.MailUtils;
import com.paySystem.ic.utils.ReadInit;
import com.paySystem.ic.utils.ShortMessUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.MerStoreApplyDTO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;
import com.paySystem.ic.web.dto.base.StoreInfoDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 
 * @ProjectName: backomall
 * @ClassName: MerStoreApplyService
 * @Description: 商户入驻申请的Service层实现
 * @date: 2014-11-21 上午09:46:48
 * @author: 郭营
 * @version: V1.0
 */
@Service(MerStoreApplyService.AUDITMERCHANTSSERVICE)
public class MerStoreApplyServiceImpl implements MerStoreApplyService {
    @Resource
    MerStoreApplyDao merStoreApplyDao;

    @Resource
    MerchantsDao merchantsDao;

    @Resource
    MerBussDao merBussDao;

    @Resource
    StoreInfoDAO storeInfoDAO;

    @Resource
    UserDao userDao;

    @Resource
    OrgansDao organsDao;

    @Resource
    RoleService roleService;

    @Resource
    MerCreditTotalService merCreditTotalService;

    @Resource
    UndealServiceNumDAO undealServiceNumDAO;

    @Resource
    private MerResetPwdDao merResetPwdDao = new MerResetPwdDaoImpl();

    /**
     * 
     *@Title: queryAll
     *@Description: 查询商户入驻申请的数据
     *@Params: @param page 起始页
     *@Params: @param pageNum 终止页
     *@Params: @param merStoreApplyDTO
     *@Params: @param orderBy 排序方式
     *@Params: @return
     *@Params: @throws Exception
     *@Return: QueryResult<MerStoreApplyDTO>
     *@author: 郭营
     *@Date: 2014-11-21上午09:23:12
     */
    @Override
    public QueryResult<MerStoreApplyDTO> queryAll(int page, int pageNum, MerStoreApplyDTO merStoreApplyDTO,
            LinkedHashMap<String, String> orderBy) throws Exception {
        return merStoreApplyDao.queryAll(page, pageNum, merStoreApplyDTO, orderBy);
    }

    /**
     * 
     *@Title: MerApplyAction
     *@Description: 商户入驻申请审核
     *@Params: @param modfiyValue 要审核的申请记录
     *@Params: @param status 状态
     *@Params: @return
     *@Params: @throws Exception
     *@Return: String
     *@author: 郭营
     *@Date: 2014-11-21上午09:43:51
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String MerApplyAction(String modfiyValue, String status) throws Exception {
        String[] array = modfiyValue.split("\\|");
        Date date = merStoreApplyDao.getSysTime();
        MerResetPwd reset = null;
        for (int i = 0; i < array.length; i++) {
            MerStoreApplyDTO merStoreApplyDTO = merStoreApplyDao.findById(Integer.valueOf(array[i]));
            UserSession us = Utils.getUserSession();
            merStoreApplyDTO.setOperator(us.getUserName());
            merStoreApplyDTO.setStatus(Integer.valueOf(status));
            merStoreApplyDTO.setOperTime(DateTimeTool.dateFormat("yyyy-MM-dd HH:mm:ss", DateTimeTool.dateFormat(
                    "yyyy-MM-dd HH:mm:ss", new Date())));
            merStoreApplyDao.saveMerStoreApply(merStoreApplyDTO);

            /**增加商户信息*/
            if (status.equals("1")) {
                MerchantsDTO merchantsDTO = new MerchantsDTO();
                String merId = merchantsDao.getMerId();
                merchantsDTO.setMerId(merId);
                merchantsDTO.setStatus(1);
                merchantsDTO.setConPerName(merStoreApplyDTO.getName());
                merchantsDTO.setConPerTeleNo(merStoreApplyDTO.getPhone());
                merchantsDTO.setTeleNo(merStoreApplyDTO.getTelephone());
                merchantsDTO.setMerName(merStoreApplyDTO.getStoreName());

                /***增加商户管理员*/
                User user = new User(merStoreApplyDTO.getPhone());
                user.setMerId(merId);
                user.setRealName(merStoreApplyDTO.getName());
                Role role = roleService.findBycode(Globals.MER_ROLE_CODE);
                user.getRoles().add(role);

                /**机构*/
                Organs organs = organsDao.find(ReadInit.read("ORGANID"));
                userDao.saveUser(user);
                merchantsDTO.setOrgans(organs);
                merchantsDTO.setMemId(merStoreApplyDTO.getMemId());
                Merchants merchants = new Merchants();
                merchants = merchantsDao.saveMerchant(merchantsDTO);

                /**放入默认参数信息，并保存商户业务参数*/
                merchantsDTO.setGnoPrefix("");
                merchantsDTO.setCosmSign(0);
                merchantsDTO.setCpsmSign(0);
                merchantsDTO.setCpscSign(0);
                merchantsDTO.setMsscSign(0);
                merchantsDTO.setExitsCount(0);
                merchantsDTO.setSalingCount(0);
                merchantsDTO.setIcpNo("");
                merchantsDTO.setIcpFilePath("");
                merBussDao.saveMerBuss(merchantsDTO);

                /**添加店铺铺信息*/
                StoreInfoDTO storeInfoDTO = new StoreInfoDTO();
                storeInfoDTO.setStoreId(merId);
                storeInfoDTO.setMerId(merId);
                storeInfoDTO.setStoreName(merStoreApplyDTO.getStoreName());
                storeInfoDTO.setTeleNo(merStoreApplyDTO.getPhone());
                storeInfoDAO.saveStore(storeInfoDTO);

                /**商户评价累计表*/
                merCreditTotalService.initMerCreditTotal(merId);

                /**商户入驻密码重置保存*/
                reset = new MerResetPwd();
                reset.setAuditingTime(date);
                reset.setIsReset(0);
                reset.setMemId(merStoreApplyDTO.getMemId());
                reset.setMerId(merchants.getMerId());
                reset.setTeleNo(merStoreApplyDTO.getPhone());
                reset.setOperateId(us.getUserName());
                reset.setMd5(MD5.MD5Encode(reset.getMerId() + reset.getMemId() + reset.getTeleNo()));
                merResetPwdDao.save(reset);

                /**往商城后台首界面待处理业务统计数实体 添加数据，维护平台的*/
                UndealServiceNum undealServiceNum = new UndealServiceNum();
                undealServiceNum.setMerCheckNum(1);
                undealServiceNumDAO.maintainData(ReadInit.read("ORGANID"), undealServiceNum, 1);
                /**创建一个商户业务统计数，对应一个商户**/
                undealServiceNum=new UndealServiceNum();
                undealServiceNum.setOrgMerId(merchants.getMerId());
                undealServiceNumDAO.save(undealServiceNum);
            }
            String content = "";
            String subject = "";
            String Mcontent = "";

            /**发送短信和邮箱信息*/
            if (status.equals("1")) {

                /**短信内容*/
                content = "您好，你在XXX平台申请的开户申请已经通过，请登录系统完善资料";
                /**邮件主题*/
                subject = "开户申请邮件";
                /**邮件内容*/
                Mcontent = "您好，你在XXX平台申请的开户申请已经通过，请访问地址(" + Utils.getBasePath() + "base/merReset?md5=" + reset.getMd5()
                        + ")重置密码，并完善资料。";
            }

            if (status.equals("2")) {
                /**短信内容*/
                content = "您好，你在XXX平台申请的开户申请由于不符合流程，请重新申请";
                /**邮件主题*/
                subject = "开户申请邮件";
                /**邮件内容*/
                Mcontent = "您好，你在XXX平台申请的开户申请由于不符合流程，请重新申请";
            }

            ShortMessUtil.sendMessage(merStoreApplyDTO.getPhone(), content);
            MailUtils.sendMail(subject, Mcontent, merStoreApplyDTO.getEmail());
        }

        return "success";
    }

    public MerStoreApplyDao getMerStoreApplyDao() {
        return merStoreApplyDao;
    }

    public void setMerStoreApplyDao(MerStoreApplyDao merStoreApplyDao) {
        this.merStoreApplyDao = merStoreApplyDao;
    }

    public MerchantsDao getMerchantsDao() {
        return merchantsDao;
    }

    public void setMerchantsDao(MerchantsDao merchantsDao) {
        this.merchantsDao = merchantsDao;
    }

    public StoreInfoDAO getStoreInfoDAO() {
        return storeInfoDAO;
    }

    public void setStoreInfoDAO(StoreInfoDAO storeInfoDAO) {
        this.storeInfoDAO = storeInfoDAO;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public OrgansDao getOrgansDao() {
        return organsDao;
    }

    public void setOrgansDao(OrgansDao organsDao) {
        this.organsDao = organsDao;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public MerCreditTotalService getMerCreditTotalService() {
        return merCreditTotalService;
    }

    public void setMerCreditTotalService(MerCreditTotalService merCreditTotalService) {
        this.merCreditTotalService = merCreditTotalService;
    }

    public MerResetPwdDao getMerResetPwdDao() {
        return merResetPwdDao;
    }

    public void setMerResetPwdDao(MerResetPwdDao merResetPwdDao) {
        this.merResetPwdDao = merResetPwdDao;
    }

}

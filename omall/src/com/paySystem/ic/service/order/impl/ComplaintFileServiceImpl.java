package com.paySystem.ic.service.order.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.UndealServiceNum;
import com.paySystem.ic.bean.goods.GoodInfo;
import com.paySystem.ic.bean.goods.PlatHandleRecord;
import com.paySystem.ic.bean.internalMessage.InternalMessage;
import com.paySystem.ic.bean.internalMessage.Receivers;
import com.paySystem.ic.bean.order.ComplaintFiled;
import com.paySystem.ic.bean.order.ComplaintInfor;
import com.paySystem.ic.bean.order.Orders;
import com.paySystem.ic.dao.base.GoodsInfoDao;
import com.paySystem.ic.dao.base.StoreInfoDAO;
import com.paySystem.ic.dao.base.UndealServiceNumDAO;
import com.paySystem.ic.dao.base.impl.GoodsInfoDaoImpl;
import com.paySystem.ic.dao.base.impl.StoreInfoDAOImpl;
import com.paySystem.ic.dao.base.impl.UndealServiceNumDAOImpl;
import com.paySystem.ic.dao.goods.PlatHandleRecordDAO;
import com.paySystem.ic.dao.goods.impl.PlatHandleRecordDAOImpl;
import com.paySystem.ic.dao.internalMessage.InternalMessageDao;
import com.paySystem.ic.dao.internalMessage.impl.InternalMessageDaoImpl;
import com.paySystem.ic.dao.order.ComplaintFiledDao;
import com.paySystem.ic.dao.order.ComplaintInforDao;
import com.paySystem.ic.dao.order.OrdersDAO;
import com.paySystem.ic.dao.order.impl.ComplaintFiledDaoImpl;
import com.paySystem.ic.dao.order.impl.ComplaintInforDaoImpl;
import com.paySystem.ic.dao.order.impl.OrderDaoImpl;
import com.paySystem.ic.service.order.ComplaintFiledService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.order.ComplaintFiledDTO;
import com.paySystem.ic.web.dto.order.ComplaintHandleDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:ComplaintFileServiceImpl
 * @Description:投诉后台处理Service接口实现类
 * @date: 2014-11-19
 * @author: 孟凡岭
 * @version: V1.0
 */
@Service(ComplaintFiledService.COMPLAINTFILEDSERVICE)
public class ComplaintFileServiceImpl implements ComplaintFiledService {
	@Resource
	private ComplaintFiledDao complaintFiledDao = new ComplaintFiledDaoImpl();
	@Resource
	private ComplaintInforDao complaintInforDao = new ComplaintInforDaoImpl();
	@Resource
	private PlatHandleRecordDAO platHandleRecorDao = new PlatHandleRecordDAOImpl();
	@Resource
	private GoodsInfoDao goodsInfoDao = new GoodsInfoDaoImpl();
	@Resource
	private StoreInfoDAO storeInfoDAO = new StoreInfoDAOImpl();
	@Resource
	private UndealServiceNumDAO undealServiceNumDAO = new UndealServiceNumDAOImpl();
	@Resource
	private OrdersDAO ordersDAO = new OrderDaoImpl();
	@Resource
	private InternalMessageDao internalMessageDao = new InternalMessageDaoImpl();

	public InternalMessageDao getInternalMessageDao() {
		return internalMessageDao;
	}

	public void setInternalMessageDao(InternalMessageDao internalMessageDao) {
		this.internalMessageDao = internalMessageDao;
	}

	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}

	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}

	public UndealServiceNumDAO getUndealServiceNumDAO() {
		return undealServiceNumDAO;
	}

	public void setUndealServiceNumDAO(UndealServiceNumDAO undealServiceNumDAO) {
		this.undealServiceNumDAO = undealServiceNumDAO;
	}

	public StoreInfoDAO getStoreInfoDAO() {
		return storeInfoDAO;
	}

	public void setStoreInfoDAO(StoreInfoDAO storeInfoDAO) {
		this.storeInfoDAO = storeInfoDAO;
	}

	public GoodsInfoDao getGoodsInfoDao() {
		return goodsInfoDao;
	}

	public void setGoodsInfoDao(GoodsInfoDao goodsInfoDao) {
		this.goodsInfoDao = goodsInfoDao;
	}

	public ComplaintFiledDao getComplaintFiledDao() {
		return complaintFiledDao;
	}

	public void setComplaintFiledDao(ComplaintFiledDao complaintFiledDao) {
		this.complaintFiledDao = complaintFiledDao;
	}

	public ComplaintInforDao getComplaintInforDao() {
		return complaintInforDao;
	}

	public void setComplaintInforDao(ComplaintInforDao complaintInforDao) {
		this.complaintInforDao = complaintInforDao;
	}

	public PlatHandleRecordDAO getPlatHandleRecorDao() {
		return platHandleRecorDao;
	}

	public void setPlatHandleRecorDao(PlatHandleRecordDAO platHandleRecorDao) {
		this.platHandleRecorDao = platHandleRecorDao;
	}

	/**
	 *@MethodName:queryResult
	 *@Description:分页查询
	 *@param i
	 *@param pageNum
	 *@param complaintFiledDTO
	 *@param orderby
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-19上午11:58:45
	 */
	public QueryResult<ComplaintFiledDTO> queryResult(int first, int pageNum,
			ComplaintFiledDTO complaintFiledDTO,
			LinkedHashMap<String, String> orderby) throws Exception {
		// TODO Auto-generated method stub
		return complaintFiledDao.queryResult(first, pageNum, complaintFiledDTO,
				orderby);
	}

	/**
	 *@MethodName:loadData
	 *@Description:查询详情
	 *@param complaintFiledDTO
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-20上午11:57:29
	 */
	public ComplaintHandleDTO loadComplaint(ComplaintFiledDTO complaintFiledDTO)
			throws Exception {
		// TODO Auto-generated method stub
		return complaintFiledDao.loadComplaint(complaintFiledDTO);
	}

	/**
	 *@MethodName:updateCom
	 *@Description:处理投诉
	 *@param complaintHandleDTO
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-21下午02:42:28
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateCom(ComplaintHandleDTO complaintHandleDTO)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			PlatHandleRecord p = new PlatHandleRecord();
			// 更新投诉申请表
			ComplaintFiled c = complaintFiledDao.find(complaintHandleDTO
					.getFiledId());
			c.setCompSta(complaintHandleDTO.getAdminHandlSug());
			c.setUpdateTime(complaintFiledDao.getSysTime());
			complaintFiledDao.update(c);
			// 更新投诉信息记录表
			// ComplaintInfor info = complaintInforDao.findByFiledId(c
			// .getFiledId());
			ComplaintInfor info = new ComplaintInfor();
			info.setCompSta(complaintHandleDTO.getAdminHandlSug());
			info.setHandlSug(complaintHandleDTO.getHandlSug());
			info.setOperator(Utils.getUserSession().getUserName());
			info.setUpdateTime(complaintInforDao.getSysTime());
			info.setManComSta(2);
			info.setOrderId(c.getOrderId());
			info.setFiledId(c.getFiledId());
			info.setStoreComDesc(complaintHandleDTO.getHandlSug());
			if (complaintHandleDTO.getHandlWay() != null) {
				info.setHandlWay(complaintHandleDTO.getHandlWay());
				p.setPhType(complaintHandleDTO.getHandlWay());
			} else {
				info.setHandlWay(-1);
				p.setPhType(-1);
			}
			if (complaintHandleDTO.getPoints() != null) {
				info.setDeductScore(complaintHandleDTO.getPoints());
			}
			if (complaintHandleDTO.getVioId() != null) {
				info.setVioRugleId(complaintHandleDTO.getVioId());
				p.setVioRugleId(String.valueOf(complaintHandleDTO.getVioId()));
			} else {
				info.setVioRugleId(-1);
				p.setVioRugleId("");
			}
			complaintInforDao.save(info);
			// 向平台处理商品记录表添加数据

			p.setGoodsId(Long.valueOf(complaintHandleDTO.getGoodsId()));
			p.setPhReason("会员投诉");
			p.setPhDescr(complaintHandleDTO.getHandlSug().getBytes());
			p.setOperatorId(Utils.getUserSession().getUserName());
			p.setCreateTime(platHandleRecorDao.getSysTime());
			if (complaintHandleDTO.getPoints() != null) {
				p
						.setDeductScore(Double.valueOf(complaintHandleDTO
								.getPoints()));
			} else {
				p.setDeductScore(0);
			}
			platHandleRecorDao.save(p);
			// 如果处理方式为强制下架时改变商品状态
			if (complaintHandleDTO.getHandlWay() != null
					&& complaintHandleDTO.getHandlWay() == 2) {
				GoodInfo g = goodsInfoDao.find(Long.valueOf(complaintHandleDTO
						.getGoodsId()));
				g.setGoodSaleSta(1);
				goodsInfoDao.update(g);
			}
			// 更新待处理业务统计表和订单投诉状态
			UndealServiceNum u = new UndealServiceNum();
			if (complaintHandleDTO.getAdminHandlSug() == 3
					|| complaintHandleDTO.getAdminHandlSug() == 5
					|| complaintHandleDTO.getAdminHandlSug() == 2) {
				u.setComplaintsNum(1);
				undealServiceNumDAO.maintainData(c.getStoreId(), u, 1);
				// 更新订单投诉状态
				Orders orders = ordersDAO.find(c.getOrderId());
				// 将订单状态改为投诉完成
				orders.setOrdCompType(2);
				ordersDAO.update(orders);
				// 发送站内信进行提示
				InternalMessage msg = new InternalMessage();
				StringBuilder content = new StringBuilder();
				content.append("您对店名为:" + complaintHandleDTO.getStoreName()
						+ "的投诉我们已经进行了处理！");
				content.append("投诉编号为：" + c.getFiledId() + "。处理结果为："
						+ complaintHandleDTO.getHandlSug());
				content.append("如有疑问请与我们联系！");
				msg.setContent(content.toString());
				msg.setTitle("投诉处理");
				msg.setReceiverFlag(0);
				msg.setSender(Utils.getUserSession().getUserName());
				msg.setSendTime(internalMessageDao.getSysTime());
				msg = internalMessageDao.saveMsg(msg);
				Receivers receivers = new Receivers();
				receivers.setStatus(0);
				receivers.setMemId(Integer.valueOf(orders.getMemId()));
				receivers.setReceiverName(orders.getMemName());
				receivers.setInternalMessageId(msg.getInternalMessageId());
				internalMessageDao.save(receivers);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	/**
	 *@MethodName:appoint
	 *@Description:指派给商家处理,更新状态
	 *@param filedId
	 *@Author:孟凡岭
	 *@Date:2014-11-24上午11:28:16
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void appoint(Integer filedId) throws Exception {
		ComplaintInfor info = complaintInforDao.findByFiledId(filedId);
		info.setOperator(Utils.getUserSession().getUserName());
		info.setUpdateTime(complaintInforDao.getSysTime());
		info.setCompSta(2);
		info.setManComSta(1);
		complaintInforDao.update(info);
		ComplaintFiled c = complaintFiledDao.find(filedId);
		c.setCompSta(2);
		complaintFiledDao.update(c);
	}

	/**
	 *@MethodName:businessAppeal
	 *@Description:商户申诉
	 *@param complaintHandleDTO
	 *@return
	 *@throws Exception
	 *@Author:孟凡岭
	 *@Date:2014-11-24下午12:07:08
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean businessAppeal(ComplaintHandleDTO complaintHandleDTO) {
		try {
			ComplaintFiled c = complaintFiledDao.find(complaintHandleDTO
					.getFiledId());
			c.setCompSta(7);
			c.setUpdateTime(complaintFiledDao.getSysTime());
			complaintFiledDao.update(c);
			ComplaintInfor info = complaintInforDao
					.findByFiledId(complaintHandleDTO.getFiledId());
			info.setStoreComDesc(complaintHandleDTO.getStoreComDesc());
			info.setOperator(Utils.getUserSession().getUserName());
			info.setUpdateTime(complaintInforDao.getSysTime());
			info.setCompSta(7);
			complaintInforDao.update(info);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
}

package com.paySystem.ic.dao.goods.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.goods.FormatInfo;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.FormatInfoDAO;
import com.paySystem.ic.web.dto.goods.FormatInfoDTO;

/**
 * 
 * @ProjectName:omall
 * @ClassName:FormatInfoDAOImpl
 * @Description:规格dao实现类
 * @date: 2014-8-26
 * @author:Jacky
 * @version: V1.0
 */
@Repository(FormatInfoDAO.FORMATINFODAO)
public class FormatInfoDAOImpl extends DaoSupport<FormatInfo> implements FormatInfoDAO {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.FormatInfoDAO#saveBeans(java.util.List)
	 *@MethodName:saveBeans
	 *@Description:批量保存
	 *@param formatList
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:10:59
	 */
	public void saveBeans(List<FormatInfoDTO> formatList) throws Exception {
		List<FormatInfo> formatLists = batchCopy(formatList);
		if(CollectionUtils.isNotEmpty(formatLists))
			this.saves(formatLists);
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.FormatInfoDAO#deleteBeans(java.lang.Long)
	 *@MethodName:deleteBeans
	 *@Description:批量删除
	 *@param goodsId
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:11:12
	 */
	public void deleteBeans(Long goodsId) throws Exception {
		em.createQuery("delete from FormatInfo o where o.goodsId=?").setParameter(1, goodsId).executeUpdate();
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.goods.FormatInfoDAO#findFormatInfoDTOByGoodsId(java.lang.Long)
	 *@MethodName:findFormatInfoDTOByGoodsId
	 *@Description:根据商品ID查找规格信息
	 *@param goodsId
	 *@return
	 *@throws Exception
	 *@Author:Jacky
	 *@Date:2014-8-26下午09:11:28
	 */
	@SuppressWarnings("unchecked")
	public List<FormatInfoDTO> findFormatInfoDTOByGoodsId(Long goodsId) throws Exception {
		List<Object[]> formatList = em.createNativeQuery("select o.formatId,o4.formatName,o.formatValue,o3.fgroupName from B_FormatInfo as o left outer join  b_formatgrouprela as o2 on o.formatId=o2.formatId  left outer join  b_formatgroup as o3 on o3.fgroupId=o2.fgroupId left outer join  b_format as o4 on o.formatId=o4.formatId where o.goodsId=? ").setParameter(1, goodsId).getResultList();
		if(CollectionUtils.isNotEmpty(formatList)) {
			List<FormatInfoDTO> formatReturnList = new ArrayList<FormatInfoDTO>(formatList.size());
			for(Object[] obj : formatList) {
				FormatInfoDTO fid = new FormatInfoDTO();
				fid.setFormatId(((BigInteger)obj[0]).longValue());
				fid.setFormatName(obj[1]!=null?obj[1].toString():"");
				fid.setFormatValue(obj[2]!=null?obj[2].toString():"");
				fid.setGoodsId(goodsId);
				fid.setGroupName(obj[3]!=null?obj[3].toString():"");
				formatReturnList.add(fid);
			}
			return formatReturnList;
		}
		return null;
	}

	/**
	 * 
	 *@Title:batchCopy
	 *@Description:批量复制
	 *@Params:@param formatInfoList
	 *@Params:@return
	 *@Return:List<FormatInfo>
	 *@author:Jacky
	 *@Date:2014-8-26下午09:11:45
	 */
	private List<FormatInfo> batchCopy(List<FormatInfoDTO> formatInfoList) {
		List<FormatInfo> formatList = new ArrayList<FormatInfo>();
		if(CollectionUtils.isNotEmpty(formatInfoList)) {
			for(FormatInfoDTO formatInfoDTO : formatInfoList) {
				FormatInfo fi = new FormatInfo();
				BeanUtils.copyProperties(formatInfoDTO, fi);
				formatList.add(fi);
			}
		}
		return formatList;
	}
}

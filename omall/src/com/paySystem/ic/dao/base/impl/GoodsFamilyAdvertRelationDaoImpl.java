package com.paySystem.ic.dao.base.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.base.GoodsFamilyAdvertRelation;
import com.paySystem.ic.dao.base.GoodsFamilyAdvertRelationDao;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.utils.UploadUtil;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.base.GoodsFamilyAdvertRelationDTO;
import com.paySystem.ic.web.dto.base.GoodsFamilyDTO;

@Repository(GoodsFamilyAdvertRelationDao.GOODADVREL)
public class GoodsFamilyAdvertRelationDaoImpl extends
		DaoSupport<GoodsFamilyAdvertRelation> implements
		GoodsFamilyAdvertRelationDao {

	@Override
	public GoodsFamilyAdvertRelation saveRelation(GoodsFamilyDTO goodFamilyDTO) throws Exception {

		GoodsFamilyAdvertRelation advertRelation = getRel(goodFamilyDTO);

		// 如果生成楼层并且展示广告
		if (goodFamilyDTO.getCreateFloorSign() == 1
				&& goodFamilyDTO.getShowAdvertSign() == 1) {
			String picPath =null;
			if (goodFamilyDTO.getAdvertPic() != null
					&& StringUtils.isNotBlank(goodFamilyDTO
							.getAdvertPicFileName())) {
				if(goodFamilyDTO.getAdvertPicFileName()!=null){
					picPath = UploadUtil.uploadImg(goodFamilyDTO.getAdvertPic(),
							goodFamilyDTO.getAdvertPicFileName());
					advertRelation.setAdvertPicPath(picPath);
				}
			}
			if (checkExsitRel(goodFamilyDTO) == null) {
				this.save(advertRelation);
			} else {
				if (picPath != null) {
					this.update(advertRelation);
				}
			}
		} else {
			if (checkExsitRel(goodFamilyDTO) != null) {
				this.delete(checkExsitRel(goodFamilyDTO).getRelId());
			}
		}
		return advertRelation;
	}

	private GoodsFamilyAdvertRelation checkExsitRel(
			GoodsFamilyDTO goodsFamilyDTO) {

		StringBuffer sb = new StringBuffer(
				" from GoodsFamilyAdvertRelation o where 1=1 ");
		sb.append(" and o.familyId = " + goodsFamilyDTO.getFamilyId());

		Query query = em.createQuery(sb.toString());
		List<GoodsFamilyAdvertRelation> gfs = query.getResultList();

		return gfs.size() > 0 ? gfs.get(0) : null;
	}

	private GoodsFamilyAdvertRelation getRel(GoodsFamilyDTO goodFamilyDTO) {

		GoodsFamilyAdvertRelation advertRelation = this
				.checkExsitRel(goodFamilyDTO);

		if (advertRelation == null) {
			advertRelation = new GoodsFamilyAdvertRelation();
		}
		advertRelation.setFamilyId(goodFamilyDTO.getFamilyId());
		advertRelation.setObjectId(goodFamilyDTO.getObjectId());
		advertRelation.setAdvertContentSign(goodFamilyDTO
				.getAdvertContentSign());
		//advertRelation.setAdvertPicPath(goodFamilyDTO.getAdvertPicPath());
		if(advertRelation.getCreateTime()==null){
			advertRelation.setCreateTime(this.getSysTime());
		}
		advertRelation.setUpdateTime(this.getSysTime());
		advertRelation.setOperatorId(Utils.getUserSession().getUserName());
		
		return advertRelation;
	}

	/**
	 * 通过分类ID删除楼层信息
	 */
	public void deleteByFamilyId(Integer familyId) {
		// TODO Auto-generated method stub
		String sql = "delete from b_goodsfamilyadvRel  where familyId=?1";
		Query query = this.em.createNativeQuery(sql);
		query.setParameter(1, familyId);
		query.executeUpdate();
	}

	/**
	 * 根据familyId查询楼层数据
	 */
	public GoodsFamilyAdvertRelationDTO findByFamilyId(Integer familyId)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "from GoodsFamilyAdvertRelation g where g.familyId=?1";
		Query query = this.em.createQuery(sql);
		query.setParameter(1, familyId);
		List<GoodsFamilyAdvertRelation> list = query.getResultList();
		if (list != null) {
			return (GoodsFamilyAdvertRelationDTO) EntityDtoConverter.bean2Dto(
					list.get(0), new GoodsFamilyAdvertRelationDTO());
		}
		return null;
	}

}

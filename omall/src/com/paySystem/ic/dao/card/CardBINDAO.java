package com.paySystem.ic.dao.card;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardBINDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @author ：赵巧鹤
 * @ClassName ：CardBINDAO 卡BIN DAO的接口
 * @project_name：mciu
 * @createTime：2013-12-7 下午02:59:13
 * 
 * 
 */
/**
 * @author Administrator
 * @projectName MCIU20131123
 * @className CardBINDAO
 * @description TODO
 * @date 2014-2-10下午07:21:51
 * @param 
 * 
 */

public interface CardBINDAO extends DAO<CardBIN> {

	public static final String CARDBINDAO = "cardBINDAO";

	
	/**
	 * @Title:getBinId
	 * @Descrition:TODO 按流水号获得binId
	 * @param: @return
	 * @return: String
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public String getBinId();

	
	/**
	 * @Title:saveCardBIN
	 * @Descrition:TODO 保存
	 * @param: @param cardBINDTO
	 * @param: @return
	 * @return: CardBIN
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public CardBIN saveCardBIN(CardBINDTO cardBINDTO);


	/**
	 * @Title:updateCardBIN
	 * @Descrition:TODO 修改
	 * @param: @param cardBINDTO
	 * @param: @return
	 * @return: ReturnDTO
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public ReturnDTO updateCardBIN(CardBINDTO cardBINDTO);


	/**
	 * @Title:deleteCardBIN
	 * @Descrition:TODO 删除卡BIN
	 * @param: @param binId
	 * @return: void
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public void deleteCardBIN(String binId);

	
	/**
	 * @Title:queryByBIN
	 * @Descrition:TODO 根据卡BIN进行查询
	 * @param: @param binId
	 * @param: @return
	 * @return: CardBINDTO
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public CardBINDTO queryByBIN(String binId);


	/**
	 * @Title:queryCardBIN
	 * @Descrition:TODO 查询卡BIN信息
	 * @param: @param fristindex
	 * @param: @param pageNum
	 * @param: @param cardBINDTO
	 * @param: @param orderBy
	 * @param: @return
	 * @param: @throws Exception
	 * @return: QueryResult<CardBIN>
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public QueryResult<CardBIN> queryCardBIN(int fristindex, int pageNum,
			CardBINDTO cardBINDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 *@Title:queryCardBin
	 *@Description:查询所有卡BIN信息 --谢洪飞
	 *@param:@return
	 *@return:Query
	 *@thorws:
	 */
	
	public Query queryCardBin(String organId);
	
	  /**
	  *@Title:getOptionByOrganId
	  *@Description:根据机构号查询下属卡BIN
	  *@param:@param organId 机构编号
	  *@param:@return
	  *@return:List<OptionsString>
	  *@author: 谢
	  *@thorws:
	 */
	
	public List<OptionsString> getOptionByOrganId(String organId);
	



	/**
	 * @Title:validateBinName
	 * @Descrition:TODO  检验数据库中是否已经存在该卡BIN名称
	 * @param: @param binName
	 * @param: @param binId
	 * @param: @return
	 * @return: boolean
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public boolean validateBinName(String binName,String binId);

	/**
	 * @Title:validate
	 * @Descrition:TODO  检验数据库中是否已经存在该卡BIN号
	 * @param: @param binId
	 * @param: @return
	 * @return: boolean
	 * @author: 赵巧鹤
	 * @throws:
	 */
	
	public boolean validate(String binId);
	
	
	

	/**
	
	* @ClassName: CardBINDAO.java
	
	* @Description: 手动获得卡BIN的id
	
	* @author  赵巧鹤
	
	* @date 2014-5-26 下午09:11:17
	
	*/
	public String getBINID();
}

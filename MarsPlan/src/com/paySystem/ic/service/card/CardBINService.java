package com.paySystem.ic.service.card;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.service.common.DAO;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.card.CardBINDTO;
import com.paySystem.ic.web.ui.OptionsString;

/**
 * @作者 赵巧鹤
 * @类名称 CardBINService 卡BINService 的接口
 * @项目名称 mciu
 * @创建时间 2013-12-10 下午02:46:11
 */
public interface CardBINService extends DAO<CardBIN> {

	public static final String CARDBINSERVICE = "cardBINService";

	/**
	 * 根据卡BIN查询
	 * 
	 * */
	public CardBIN queryById(String binId);

	/**
	 * 查询出所有的卡BIN
	 * 
	 * */
	public List<OptionsString> queryCardBIN();

	/**
	 *@Title:getOptionByOrganId
	 *@Description:根据机构号查询下属卡BIN
	 *@param:@param organId 机构编号
	 *@param:@return
	 *@return:List<OptionsString>
	 *@author: 谢
	 *@thorws:
	 */
	List<OptionsString> getOptionByOrganId(String organId);

	/**
	 * 根据页面上的条件进行查询
	 * */

	public QueryResult<CardBIN> queryTermByCond(int fristindex, int pageNum,
			CardBINDTO terminalsDTO, LinkedHashMap<String, String> orderBy)
			throws Exception;

	/**
	 * 增加卡BIN
	 * 
	 * */
	public CardBIN saveCardBIN(CardBINDTO cardBINDTO);

	/**
	 * 修改卡BIN
	 * 
	 * **/
	public ReturnDTO updateCardBIN(CardBINDTO cardBINDTO);

	/**
	 * 删除卡BIN
	 * 
	 * **/
	public void deleteCardBIN(String binId);

	/**
	 * 按流水获取卡BIN号
	 * 
	 * @return
	 */
	String getCardBINId();

	/**
	 * @param binName
	 * @param binId
	 *            检验数据库中是否已经存在该卡BIN名称
	 * @return
	 */
	public boolean validateBinName(String binName, String binId);

	/**
	 * 检验数据库中是否已经存在该卡BIN编号
	 * 
	 * @param binId
	 * @return
	 */
	public boolean validate(String binId);
	/**
	
	* @ClassName: CardBINService.java
	
	* @Description: 获得binId，移植数据库专门写的方法
	
	* @author  赵巧鹤
	
	* @date 2014-5-27 上午09:37:26
	
	*/
	public String getBinID();
}

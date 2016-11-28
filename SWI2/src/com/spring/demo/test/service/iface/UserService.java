package com.spring.demo.test.service.iface;

import java.util.List;

/**
 * @ProjectName:SWI
 * @ClassName:UserService
 * @Description:用户服务接口
 * @date: 2015-7-10下午03:40:37
 * @author: 半仙儿
 * @version: V1.0
 * @date:2015-7-10下午03:40:37
 */
public interface UserService {
	/** 保存对象 */
	public boolean Save(Object bean);

	public boolean insert(Object bean);

	/** 更新对象 */
	public boolean Update(Object bean);

	/** 根据编号进行查询 */
	public Object FindById(Class cl, Object id);

	/** 查找数据库中的数据 */
	public List Find(Object uname);

	/** 查询所有的数据 */
	public List FindAll(Object bean);

	/** 通过对象的属性进行查询 */
	public List FindByArr(Object arr);

	/** 通过对象的编号进行删除 */
	public boolean Delete(Object id);
}

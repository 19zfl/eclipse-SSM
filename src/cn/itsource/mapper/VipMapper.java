package cn.itsource.mapper;

import java.util.List;

import cn.itsource.domain.Vip;
import cn.itsource.query.VipQuery;

public interface VipMapper {
	
	// 查询所有
	List<Vip> queryAll(VipQuery query);	
	
	// 查询一个
	Vip queryOne(Long id);
	
	// 新增
	void saveVip(Vip v);
	
	// 删除
	void delVip(Long id);
	
	// 修改
	void updateVip(Vip v);

}

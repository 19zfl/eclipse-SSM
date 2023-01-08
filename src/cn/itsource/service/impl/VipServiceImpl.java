package cn.itsource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.domain.Vip;
import cn.itsource.mapper.VipMapper;
import cn.itsource.query.VipQuery;
import cn.itsource.service.VipService;

@Service
public class VipServiceImpl implements VipService {
	
	@Autowired
	private VipMapper vipMapper;

	@Override
	public List<Vip> queryAll(VipQuery query) {
		return vipMapper.queryAll(query);
	}

	@Override
	public void saveVip(Vip v) {
		vipMapper.saveVip(v);
	}

	@Override
	public void delVip(Long id) {
		vipMapper.delVip(id);
	}

	@Override
	public void updateVip(Vip v) {
		vipMapper.updateVip(v);
	}

	@Override
	public Vip queryOne(Long id) {
		return vipMapper.queryOne(id);
	}
	
}

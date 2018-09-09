package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.GoodsDao;
import web.domain.Goods;

public class GoodsAction extends BaseAction<Goods>{
	@Autowired
	protected GoodsDao goodsDao;

	public GoodsAction(){
		super();
		model=new Goods();
	}

	public String entry(){
		return entry(goodsDao);
	}

	public String info() {
		return info(goodsDao);
	}

	public String publish(){
		return publish(goodsDao);
	}

	public String delete(){
		return delete(goodsDao);
	}
}

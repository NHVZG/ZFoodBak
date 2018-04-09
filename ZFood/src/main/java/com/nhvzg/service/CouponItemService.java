package com.nhvzg.service;

import com.nhvzg.dao.CouponItemMapper;
import com.nhvzg.dao.CouponMapper;
import com.nhvzg.entity.Coupon;
import com.nhvzg.entity.CouponItem;
import com.nhvzg.result.UserCouponMsg;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by NHVZG on 2018/2/26.
 */
@Service
public class CouponItemService {
    @Autowired
    private CouponItemMapper itemMapperr;
    @Autowired
    private CouponMapper mapper;


    public void addCouponItemList(List list){
        for(int i=0;i<list.size();i++){
            CouponItem item=((CouponItem)list.get(i));
            item.setCouponItemId(UUIDTools.getPrimaryKey());
            item.setState(1);
        }
        itemMapperr.addList(list);
    }

    @Deprecated
    //����û��Ż�ȯ
    public void addCouponItem(CouponItem item){
        item.setCouponItemId(UUIDTools.getPrimaryKey());
       /* if(item.getEndtime().equals("1")) {//����ͳһʱ���Ż�ȯ
            Coupon coupon=mapper.selectByPrimaryKey(item.getCouponid());
            item.setStarttime(coupon.getStarttime());
            item.setEndtime(coupon.getEndtime());
        }*/
        item.setState(1);
        itemMapperr.insert(item);
    }

    //ɾ���û��Ż�ȯ
    public void removeCouponItem(String couponItemId){
        itemMapperr.deleteByPrimaryKey(couponItemId);
    }

    //��ȡ�û������Ż�ȯ
    public List<UserCouponMsg> getUserCouponItem(CouponItem item){
        return itemMapperr.getCouponsByUser(item.getUserId());
    }


    //�û��Ż�ȯ����   �Ѿ���Ϊmysql�¼��Զ�ִ��
    @Deprecated
    public void setCouponItemState(String couponItemId){
        CouponItem item=itemMapperr.selectByPrimaryKey(couponItemId);
        item.setState(-1);
        itemMapperr.updateByPrimaryKey(item);
    }
    //�����û��Ż�ȯ�Ƿ����  �Ѿ���Ϊmysql�¼��Զ�ִ��
    @Deprecated
    public void checkAvailable(CouponItem item){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    }
}

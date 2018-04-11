package com.nhvzg.service;

import com.nhvzg.dao.CouponItemMapper;
import com.nhvzg.dao.CouponMapper;
import com.nhvzg.entity.Coupon;
import com.nhvzg.entity.CouponItem;
import com.nhvzg.entity.OrderItem;
import com.nhvzg.result.OrderMessage;
import com.nhvzg.result.UserCouponMsg;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/26.
 */
@Service
public class CouponItemService {
    @Autowired
    private CouponItemMapper itemMapperr;
    @Autowired
    private CouponMapper mapper;

    //添加用户优惠券
    public void addCouponItemList(List list){
        for(int i=0;i<list.size();i++){
            CouponItem item=((CouponItem)list.get(i));
            item.setCouponItemId(UUIDTools.getPrimaryKey());
            item.setState(1);
        }
        itemMapperr.addList(list);
    }

    @Deprecated
    //添加用户优惠券
    public void addCouponItem(CouponItem item){
        item.setCouponItemId(UUIDTools.getPrimaryKey());
       /* if(item.getEndtime().equals("1")) {//代表统一时间优惠券
            Coupon coupon=mapper.selectByPrimaryKey(item.getCouponid());
            item.setStarttime(coupon.getStarttime());
            item.setEndtime(coupon.getEndtime());
        }*/
        item.setState(1);
        itemMapperr.insert(item);
    }

    //删除用户优惠券
    public void removeCouponItem(String couponItemId){
        itemMapperr.deleteByPrimaryKey(couponItemId);
    }

    //获取用户所有优惠券
    public List<UserCouponMsg> getUserCouponItem(CouponItem item){
        return itemMapperr.getCouponsByUser(item.getUserId());
    }

    //验证金额优惠值
    public Map getAmount(List<UserCouponMsg>  couponList,List<OrderMessage> orderItemList){
        Map result= new HashMap();
        double preferential=0;
        double amount=0;
        double pack=0;
        double commonDiscount=0;
        List<OrderItem> list=orderItemList.get(0).getOrderItems();
        for(OrderItem item:list){
            double unitprice=0;
            if(item.getUnitprice()!=null)
                unitprice=item.getUnitprice();
            amount=amount+unitprice*item.getNum();
            double p=item.getPackprice()==null?0:item.getPackprice();
            pack=pack+p;
        }
        for (UserCouponMsg coupon : couponList) {//优惠先减再*百分比
            String extra=coupon.getExtra();
            if (extra != null && !extra.equals("")) {//专属食物优惠
                for(OrderItem item:list){
                    if(item.getFoodId().equals(extra)){
                        if(coupon.getPercent()==1){
                            preferential=preferential+(100-coupon.getDiscount())*item.getNum()*item.getUnitprice()/100;
                        }else{
                            preferential=preferential+coupon.getDiscount();
                        }
                    }
                }
            }else{
                if(coupon.getPercent()==1){//相同类型的通用百分比优惠只取最大优惠的
                    commonDiscount=commonDiscount<coupon.getDiscount()&&commonDiscount!=0?commonDiscount:coupon.getDiscount();
                }else{
                    preferential=preferential+coupon.getDiscount();
                }
            }
        }
        if(commonDiscount!=0){//通用百分比优惠 以优惠完的总额再打折
            if(amount>preferential) {
                preferential = preferential + (amount - preferential) * (100-commonDiscount) / 100;
            }else{
                preferential=amount;
            }
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.UP);//四舍五入
        result.put("preferential",Double.parseDouble(nf.format(preferential)));
        result.put("amount",Double.parseDouble(nf.format(amount)));
        result.put("pack",pack);
        return result;
    }

    //设置用户优惠券状态
    public void setCouponItemListState(List<CouponItem> list){
        itemMapperr.setCouponItemListState(list);
    }

    //用户优惠券过期   已经改为mysql事件自动执行
    @Deprecated
    public void setCouponItemState(String couponItemId){
        CouponItem item=itemMapperr.selectByPrimaryKey(couponItemId);
        item.setState(-1);
        itemMapperr.updateByPrimaryKey(item);
    }
    //检验用户优惠券是否过期  已经改为mysql事件自动执行
    @Deprecated
    public void checkAvailable(CouponItem item){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    }
}

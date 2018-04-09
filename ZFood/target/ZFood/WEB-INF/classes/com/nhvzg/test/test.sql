update `order` set price=(
  select SUM(oio.price) as sumPrice from(
                                          select oi.unitPrice*oi.num as price from `order` o RIGHT JOIN `orderitem` oi on o.order_id=oi.order_id WHERE o.shop_id='22bsajsdh3u' and o.state=5
                                        )oio
) WHERE state=5 and user_id='e6879869Me55eM4d10Mada5M65eb7855dc46';


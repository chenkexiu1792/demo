package aop;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    @RecordOperate(desc = "保存订单",convert=SaveOrderConver.class)
    public Boolean saveOrder(SaveOrder saveOrder){
        System.out.println("新增订单，订单ID："+saveOrder.getId());
        return true;
    }

    @RecordOperate(desc = "更新订单",convert = UpdateOrderConver.class)
    public Boolean updateOrder(UpdateOrder updateOrder){
        System.out.println("更新订单，订单ID："+updateOrder.getOrderId());
        return true;
    }

}

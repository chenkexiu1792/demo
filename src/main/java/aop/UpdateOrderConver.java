package aop;

public class UpdateOrderConver implements Convert<UpdateOrder> {
    @Override
    public OperateLogD0 convert(UpdateOrder updateOrder) {
        OperateLogD0 operateLogD0 = new OperateLogD0();
        operateLogD0.setOrderId(updateOrder.getOrderId());
        return operateLogD0;
    }
}

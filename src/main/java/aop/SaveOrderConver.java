package aop;

public class SaveOrderConver implements Convert<SaveOrder> {

    @Override
    public OperateLogD0 convert(SaveOrder saveOrder) {
        OperateLogD0 operateLogD0 = new OperateLogD0();
        operateLogD0.setOrderId(saveOrder.getId());
        return operateLogD0;
    }
}

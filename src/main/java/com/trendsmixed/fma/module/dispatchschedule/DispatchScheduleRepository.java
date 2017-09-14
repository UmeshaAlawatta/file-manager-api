package com.trendsmixed.fma.module.dispatchschedule;

import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.module.customer.Customer;
import com.trendsmixed.fma.module.salesorder.SalesOrder;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DispatchScheduleRepository extends PagingAndSortingRepository<DispatchSchedule, Integer> {

    @Query(value = "SELECT"
            + " new com.trendsmixed.fma.dao.Combo(o.id,o.job.jobNo,o.job.item.code)"
            + " FROM DispatchSchedule o")
    public List<Combo> getCombo();

    public Iterable<DispatchSchedule> findBySalesOrderItemSalesOrder(SalesOrder salesOrder);

    @Query(value = "SELECT"
            + " new com.trendsmixed.fma.dao.Combo(o.id,o.job.jobNo,o.job.item.code)"
            + " FROM DispatchSchedule o"
            + " WHERE o.salesOrderItem.salesOrder.customer = :customer")
    public List<Combo> getComboByCustomer(@Param("customer") Customer customer);
}

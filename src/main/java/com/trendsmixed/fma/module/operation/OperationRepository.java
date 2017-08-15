package com.trendsmixed.fma.module.operation;

import com.trendsmixed.fma.dao.OperationSummary;
import com.trendsmixed.fma.module.job.Job;
import com.trendsmixed.fma.module.production.Production;
import com.trendsmixed.fma.module.section.Section;
import com.trendsmixed.fma.module.shift.Shift;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends PagingAndSortingRepository<Operation, Integer> {

    //@Query(value = "select teamMenu.menu from TeamMenu teamMenu where teamMenu.team= :team And teamMenu.menu.menu IS NULL ")
    /*@Query(value = "SELECT operation.production.controlPoint.workCenter.costCenter.section.code as section,"
            + " SUM(operation.actualQuantity)  as actualTotal," 
            + " SUM(operation.plannedQuantity) as plannedTotal,"
            + " (SUM(operation.actualQuantity)/SUM(operation.plannedQuantity))*100 as value"
            + " FROM Operation operation"
            + " WHERE operation.production.productionDate BETWEEN '2017-02-01' AND '2017-02-28'"
            + " GROUP BY operation.production.controlPoint.workCenter.costCenter.section")
     */
    @Query(value = "SELECT "
            + " new com.trendsmixed.fma.dao.ScheduleAdherence(operation.production.controlPoint.workCenter.costCenter.section.code, SUM(operation.actualQuantity), SUM(operation.plannedQuantity), (SUM(operation.actualQuantity)/SUM(operation.plannedQuantity))*100) "
            + " FROM Operation operation"
            + " WHERE operation.production.productionDate BETWEEN :startDate AND :endDate"
            + " GROUP BY operation.production.controlPoint.workCenter.costCenter.section")
    public List test(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /* @Query(value = "SELECT operation "
            + " FROM Operation operation"
            + " WHERE operation.production.productionDate = :productionDate "
            + " AND operation.production.shift = :shift "
            + " AND operation.production.controlPoint.workCenter.costCenter.section = :section")
    public Page<Operation> findBySectionAndProductionDateAndShift(@Param("section")Section section, @Param("productionDate")Date productionDate, @Param("shift")Shift shift, Pageable pageable);
     */
    public Page<Operation> findByProductionControlPointWorkCenterCostCenterSectionAndProductionProductionDateAndProductionShift(Section section, Date date, Shift shift, Pageable pageable);

    public Page<Operation> findByProductionControlPointWorkCenterCostCenterSectionAndProductionProductionDateBetweenAndProductionShift(Section section, Date startDate, Date endDate, Shift shift, Pageable pageable);

    public Page<Operation> findByProductionControlPointWorkCenterCostCenterSectionAndProductionProductionDateBetween(Section section, Date startDate, Date endDate, Pageable pageable);

    public Page<Operation> findByProductionProductionDateAndProductionShift(Date date, Shift shift, Pageable pageable);

    public Page<Operation> findByProductionProductionDateBetweenAndProductionShift(Date startDate, Date endDate, Shift shift, Pageable pageable);

    public Page<Operation> findByProductionProductionDateBetween(Date startDate, Date endDate, Pageable pageable);

    public Page<Operation> findByJob(Job job, Pageable pageable);

    @Query(value = "SELECT "
            + " new com.trendsmixed.fma.dao.OperationSummary(operation.productType,operation.operationType,SUM(operation.actualQuantity)) "
            + " FROM Operation operation"
            + " WHERE operation.job.id = :jobId AND operation.actualQuantity IS NOT NULL"
            + " GROUP BY operation.productType, operation.operationType ")
    public List<OperationSummary> getSummaryByJob(@Param("jobId") int jobId);

    public List<Operation> findByProduction(Production production);

}
//com.trendsmixed.fma.dao.ScheduleAdherence

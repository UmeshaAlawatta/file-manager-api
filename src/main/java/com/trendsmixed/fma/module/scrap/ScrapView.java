package com.trendsmixed.fma.module.scrap;

import com.trendsmixed.fma.module.job.JobView;
import com.trendsmixed.fma.module.lossreason.LossReasonView;
import com.trendsmixed.fma.module.operationtype.OperationTypeView;
import com.trendsmixed.fma.module.producttype.ProductTypeView;
import com.trendsmixed.fma.module.section.SectionView;
import com.trendsmixed.fma.utility.PageView;

public class ScrapView {

    public static interface Id {
    }

    public static interface Code {
    }

    public static interface Name {
    }

    public static interface ScrapDate {
    }

    public static interface Quantity {
    }

    public static interface Rate {
    }

    public static interface Job {
    }

    public static interface Section {
    }

    public static interface OperationType {
    }

    public static interface ItemType {
    }

    public static interface LossReason {
    }
    
    public static interface ProductType {
    }
    
    public static interface All extends Id, Code, Name, ScrapDate, Quantity, Rate, PageView.All {
    }

    public static interface AllAndSectionAll extends All, Section, SectionView.All {
    }

    public static interface AllAndOperationTypeAll extends All, OperationType, OperationTypeView.All {
    }

    public static interface AllAndJobAll extends All, Job, JobView.All {
    }
    
    
    public static interface AllAndLossReasonAll extends All, LossReason, LossReasonView.All {
    }

    
    public static interface AllAndProductTypeAll extends All, ProductType, ProductTypeView.All {
    }
    
}

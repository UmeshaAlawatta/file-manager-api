package com.trendsmixed.fma.module.invoicetype;

import com.trendsmixed.fma.utility.PageView;

public class InvoiceTypeView {

    public interface Id {
    }

    public interface Name {
    }

    public interface Code {
    }

    public interface TaxRate {
    }

    public interface All extends Id, Name, Code, TaxRate, PageView.All {
    }

}

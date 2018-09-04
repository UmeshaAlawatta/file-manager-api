package com.trendsmixed.fma.module.creditnote;

import com.trendsmixed.fma.module.invoice.InvoiceView;
import com.trendsmixed.fma.utility.PageView;

public class CreditNoteView {

    public interface Id {
    }

    public interface Invoice {
    }

    public interface DateOfCreditNote {
    }

    public interface All extends Id, DateOfCreditNote, PageView.All {
    }

    public interface AllAndInvoice extends All, Invoice, InvoiceView.All {
    }
}
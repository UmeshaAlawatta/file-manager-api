package com.trendsmixed.fma.module.contacttype;

import com.trendsmixed.fma.module.contact.ContactView;
import com.trendsmixed.fma.utility.PageView;

public class ContactTypeView {

    public static interface Id {
    }

    public static interface Code {
    }

    public static interface Name {
    }

    public static interface Contact {

    }

    public static interface All extends Id, Code, Name, PageView.All {
    }

    public static interface AllAndContactAll extends All, Contact, ContactView.All {
    }

}
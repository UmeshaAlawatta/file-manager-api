package com.trendsmixed.fma.module.port;

import com.trendsmixed.fma.utility.PageView;

public class PortView {

    public interface Id {
    }

    public interface Code {
    }

    public interface Name {
    }

    public interface All extends Id, Code, Name, PageView.All {
    }

}
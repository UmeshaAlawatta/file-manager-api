package com.trendsmixed.fma.module.controlpointtype;

import com.trendsmixed.fma.utility.PageView;

public class ControlPointTypeView {

    public interface Id {
    }

    public interface Code {
    }

    public interface Name {
    }

    public interface All extends Id, Code, Name, PageView.All {
    }

}

package com.erp.action;

import com.erp.entity.Dep;
import com.erp.service.DepService;

public class DepAction extends BaseAction<Dep> {

    private DepService depService;

    public void setDepService(DepService depService) {
        this.depService = depService;
        setBaseService(depService);
    }
}

package com.erp.action;

import com.alibaba.fastjson.JSON;
import com.erp.entity.Dep;
import com.erp.service.DepService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
public class DepAction {

    private Dep dep1;
    private Dep dep2;
    private Object param;

    private int page;
    private int rows;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Dep getDep1() {
        return dep1;
    }

    public void setDep1(Dep dep1) {
        this.dep1 = dep1;
    }

    public Dep getDep2() {
        return dep2;
    }

    public void setDep2(Dep dep2) {
        this.dep2 = dep2;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    @Autowired
    private DepService depService;

    /**
     * 分页查询
     */
    public void listByPage(){

        int start = (page-1)*rows;
        HashMap<String, Object> dataMap = new HashMap<>();
        Long total = depService.getCount(dep1, dep2, param);
        List<Dep> list = depService.listByPage(dep1,dep2,param,start,rows);
        dataMap.put("total",total);
        dataMap.put("rows",list);
        write(dataMap);
    }

    /**
     * 响应数据给页面
     * @param object
     */
    private void write(Object object){

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
}

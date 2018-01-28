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
import java.util.Map;

@Controller
public class DepAction {

    private Dep dep1;
    private Dep dep2;
    private Object param;

    private int page;
    private int rows;
    private Long uuid;

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

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
     * 根据id查询
     */
    public void get(){
        Dep editDep = depService.get(uuid);
        //将editDep转成加了前缀的json字符串
        write(mapJson(editDep));
    }


    /**
     * 增加数据
     */
    public void add(){
        try {
            depService.add(dep1);
            ajaxReturn(true,"增加成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxReturn(false,"增加失败");
        }
    }
    /**
     * 根据uuid删除数据
     */
    public void del(){
        try {
            depService.deleteById(uuid);
            ajaxReturn(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxReturn(false,"删除失败");
        }
    }

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

    /**
     * 给页面相面ajax数据
     * @param success
     * @param message
     */
    private void ajaxReturn(boolean success,String message){
        HashMap<String, Object> rtn = new HashMap<>();
        rtn.put("success",success);
        rtn.put("message",message);
        write(rtn);
    }

    private String mapJson(Object object) {
        String jsonString = JSON.toJSONString(object);
        //System.out.println("jsonString====" + jsonString);
        Map<String, Object> map = JSON.parseObject(jsonString);
        //System.out.println("map====" + map);

        HashMap<String, Object> newMap = new HashMap<>();
        for (String key : map.keySet()) {
            newMap.put("dep1"+"."+key,map.get(key));
        }
        //System.out.println("newMap"+newMap);
        return JSON.toJSONString(newMap);
    }

    /*public static void main(String[] args) {
        Dep dep = new Dep();
        dep.setUuid(1223l);
        dep.setName("zsss");
        dep.setTele("8877");
        mapJson(dep);
    }*/
}

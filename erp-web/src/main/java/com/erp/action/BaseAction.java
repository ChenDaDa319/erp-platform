package com.erp.action;

import com.alibaba.fastjson.JSON;
import com.erp.service.BaseService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseAction<T> {

    private BaseService<T> baseService;

    private T t1;
    private T t2;
    private Object param;

    private int page;
    private int rows;
    private Long uuid;


    public void setBaseService(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    public T getT1() {
        return t1;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }

    public T getT2() {
        return t2;
    }

    public void setT2(T t2) {
        this.t2 = t2;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public void update(){
        try {

            baseService.update(t1);
            ajaxReturn(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxReturn(false,"修改失败");
        }
    }

    /**
     * 根据id查询
     */
    public void get(){
        T editDep = (T) baseService.get(uuid);
        //将editDep转成加了前缀的json字符串
        write(mapJson(editDep));
    }


    /**
     * 增加数据
     */
    public void add(){
        try {
            baseService.add(t1);
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
            baseService.deleteById(uuid);
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
        Long total = baseService.getCount(t1, t2, param);
        List<T> list = baseService.listByPage(t1,t2,param,start,rows);
        dataMap.put("total",total);
        dataMap.put("rows",list);
        write(dataMap);
    }

    /**
     * 响应数据给页面
     * @param object
     */
    public void write(Object object){

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(object));
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    /**
     * 把mapJson响应给页面
     * @param mapJson
     */
    public void write(String mapJson){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(mapJson);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    /**
     * 给页面相面ajax数据
     * @param success
     * @param message
     */
    public void ajaxReturn(boolean success,String message){
        HashMap<String, Object> rtn = new HashMap<>();
        rtn.put("success",success);
        rtn.put("message",message);
        write(rtn);
    }

    public String mapJson(Object object) {
        String jsonString = JSON.toJSONString(object);
        Map<String, Object> map = JSON.parseObject(jsonString);

        HashMap<String, Object> newMap = new HashMap<>();
        for (String key : map.keySet()) {
            newMap.put("t1"+"."+key,map.get(key));
        }
        return JSON.toJSONString(newMap);
    }

}

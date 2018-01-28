var method = "";
/*************入口函数**************/
$(function () {
    $('#grid').datagrid({
        url: name+'_listByPage.action',
        columns:columns,
        /* [[
            {field: 'uuid', title: '部门编号', width: 100},
            {field: 'name', title: '部门名称', width: 100},
            {field: 'tele', title: '部门电话', width: 100, align: 'right'},
            {
                field: 'a', title: '操作', width: 100, formatter: function (value, row, index) {
                var operation = '<a href="javascript:void(0);" onclick="edit(' + row.uuid + ')">修改</a>';
                operation += ' <a href="javascript:void(0);" onclick="del(' + row.uuid + ')">删除</a>'
                return operation;
            }
            }
        ]],*/
        singleSelect: true,
        pagination: true,
        pageSize: 10,
        pageList: [5, 10, 15, 20],
        toolbar: [{
            iconCls: 'icon-add',
            text: '增加',
            handler: function () {
                method= "add";
                $('#editDlg').window('open');
            }
        }]
    });
    /**弹窗初始化*/
    $('#editDlg').dialog({
        title: '部门编辑',
        width: 300,
        height: 200,
        closed: true,
        modal: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                var editData = $('#editForm').serializeJSON();
                $.ajax({
                    url: name+method+".action",
                    data: editData,
                    dataType: 'json',//把响应回来的内容(一般是字符串)转成json对象
                    type: 'post',//post/get
                    success: function (rtn) {
                        $.messager.alert('提示', rtn.message, 'info', function () {
                            if (rtn.success) {
                                //重新加载表格数据
                                $('#editDlg').dialog('close');
                                $('#editForm').form('clear');
                                $('#grid').datagrid('load');
                            }
                        });
                    }
                });

            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('editForm').form('clear');
                $('#editDlg').dialog('close');
            }
        }]
    });
})
/*************入口函数**************/
/**查询函数*/
$('#btnSearch').bind('click', function () {
    var searchData = $('#searchForm').serializeJSON();
    $('#grid').datagrid('load', searchData);
})

/**删除函数*/
function del(uuid) {
    $.messager.confirm("确认", "确认要删除吗？", function (yes) {
        if (yes) {
            $.ajax({
                url: name+'_del.action',
                data: {uuid: uuid},
                dataType: 'json',//把响应回来的内容(一般是字符串)转成json对象
                type: 'post',//post/get
                success: function (rtn) {
                    $.messager.alert('提示', rtn.message, 'info', function () {
                        if (rtn.success) {
                            //成功时
                            //重新加载表格数据
                            $('#grid').datagrid('reload');
                        }
                    });
                }
            });
        }
    });
}
/** 编辑部门信息函数*/
function edit(uuid) {
    /**编辑前要先根据id查询数据*/
    $('#editDlg').window('open');
    /** 清空上一次的数据*/
    $('#editForm').form('clear');
    /**根据id先加载数据*/
    $('#editForm').form('load',name+'_get.action?uuid='+uuid);
    /** 将按钮方法修改为update*/
    method = "update";
}
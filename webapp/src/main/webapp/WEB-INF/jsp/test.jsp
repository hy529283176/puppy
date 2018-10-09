<%--
  Created by IntelliJ IDEA.
  User: Southgis_Puppy
  Date: 2018/8/15
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Site.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zy.all.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
    <%
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    %>
</head>

<body style="padding: 0;margin: 0;overflow: auto;">
    <div style="width: 100%;height: auto;padding: 0px 20px;">
        <div style="width: 100%;height: 200px;background-color: #b2e2fa;margin-top: 15px;overflow: hidden;">
            <label>书签名称：</label>
            <span>
                <input type="text" id="shuqianname" name="shuqianname" value=""/>
                <input type="hidden" name="" value=""/>
                <input type="button" id="saveShuqian" onclick="SaveShuQian();" value="新增"/>
            </span>
            <div id="biaoqian" style="background-color: #c7c7c7;width: 300px;height: 100%;overflow: hidden;">
                <table>
                    <c:forEach items="${bookmarklist}" var="blist">
                        <tr><td>${blist.name}</td><td><input type="button" name="deleteBookMark" value="删除" onclick="DeleteBookMark('${blist.rid}')"></td></tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <hr style="border: solid 1px blue;">
        <div style="width: 100%;height: 200px;background-color: #999999;overflow: hidden;">
            <input type="button" id="saveBiaozhu" value="添加标注">
            <div id="biaoqian1" style="background-color: #b2e2fa;width: 300px;height: 100%;overflow: hidden;">

            </div>
        </div>
        <hr style="border: solid 1px blue;">
        <div style="width: 100%;height: 200px;background-color: #b2e2fa;overflow: hidden;">
            <div id="biaoqian2" style="background-color: #c7c7c7;width: 300px;height: 100%;overflow: hidden;">
                <textarea wrap="hard" rows="5" cols="5" id="zuobiaoshuchu" style="width: 300px;height: 150px;padding: 0;">
                </textarea>
                <input type="button" id="daochu" onclick="exportText();" value="导出坐标">&nbsp;&nbsp;&nbsp;<input type="button" id="shanchu" value="清除结果">
            </div>
        </div>
        <hr style="border: solid 1px blue;">
        <div style="width: 100%;height: 300px;background-color: #b2e2fa;overflow: hidden;">
            <div id="biaoqian3" style="background-color: #c7c7c7;width: 300px;height: 100%;overflow: hidden;">
                <form action="${pageContext.request.contextPath}/uploadSystem/conmonFilesUpload" method="post" enctype="multipart/form-data" >
                    <p> 选择文件:</p>
                    <input type="file" name="files">
                    <input type="submit" value="提交">
                </form>
                <span>${mes}</span>
            </div>
        </div>
        <hr style="border: solid 1px blue;">
        <div style="width: 100%;height: 200px;background-color: #b2e2fa;overflow: hidden;">
            <div id="biaoqian4" style="background-color: #c7c7c7;width: 300px;height: 100%;overflow: hidden;">
                <input type="button" id="btnexcel" onclick="exportExcel();" value="导出Excel">
                <input type="button" id="btnword" onclick="exportWord();" value="导出Word">
                <span>${message}</span>
            </div>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/plugs/Jqueryplugs.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/_layout.js"></script>
<script src="${pageContext.request.contextPath}/js/plugs/jquery.SuperSlide.source.js"></script>
    <script type="text/javascript">
        function SaveShuQian(){
            var shuqianname = document.getElementById("shuqianname");
            var name = "";
            if(shuqianname.value==""){
                name = "无"
            }else{
                name = shuqianname.value;
            }
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();
            var hours = date.getHours();
            var minutes = date.getMinutes();
            var second = date.getSeconds();
            var createtime = year+"/"+month+"/"+day+" "+hours+":"+minutes+":"+second;
            var obj = {rid:"",createTime:"2018-08-30",createUser:"李明",descript:"标签",extent:"{\"x\":\"20\",\"y\":\"20\"}",
                    name:"jia",markType:"0"};
            var str = JSON.stringify(obj);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/webgisWebSerivce/mapbookmark/saveUpdateMapBookMark1",
                data: str,
                dataType : 'json',
                success: function(data){
                    if(data.status=="ok"){
                        alert("保存成功！");
                    }
                },
                error: function(){
                    alert("未知错误，请联系管理员或开发人员!");
                }
            });
        }
       function DeleteBookMark(rid){
            if(rid == null || rid == ""){
                return;
            }
            $.ajax({
                type:"get",
                url:"http://localhost:8080/webgisWebSerivce/mapbookmark/deleteMapBookMark",
                data:{rid:rid},
                dataType:'json',
                success:function (data) {
                    alert(1);
                },
                error: function(){
                    alert("未知错误，请联系管理员或开发人员!");
                }
            });
       }
       
       function exportText() {
           var text = document.getElementById("zuobiaoshuchu");
           $("#zuobiaoshuchu").val();
           if(text.value == ""){
               return;
           }
           window.location.href = 'http://localhost:8080/webgisWebService/maptool/exportText?text='+ text.value;
       }
       
       function exportExcel() {
           var data = {
                   "sheetName": "图层信息",
                   "className": "CADCoverageName",
                   "rowData":[  {
                       "cadCoverageName": "0村庄填充",
                       "factorCode": "64",
                       "villageName": "新群村",
                       "description": "",
                       "siteArea": "0.08784848"
                   },
                       {
                           "cadCoverageName": "0村庄填充",
                           "factorCode": "65",
                           "villageName": "新群村",
                           "description": "",
                           "siteArea": "0.02245009"
                       }
                   ]
               }
           ;
           var json = JSON.stringify(data);
           json = encodeURIComponent(json);
           var url = "http://localhost:8080/webgisWebSerivce/maptool/exportExcelService";
           var form = $("<form accept-charset=\"UTF-8\">");
           form.attr('style', 'display:none');
           form.attr('target', '');
           form.attr('method', 'POST'); //请求方式
           form.attr('action', url);//请求地址
           var input1 = $('<input>');//将你请求的数据模仿成一个input表单
           input1.attr('type', 'hidden');
           input1.attr('name', 'jsonData');//该输入框的name
           input1.attr('value',json);//该输入框的值
           $('body').append(form);
           form.append(input1);
           form.submit();
           form.remove();
       }
       
       function exportWord() {
           var data = {
                   "templatePath": "E:\\Company\\Jobforms\\002.docx",
                   "tempFilePath": "E:\\Company\\Jobforms",
                   "expotDataMap": {
                       "name": "李明",
                       "sex": "男",
                       "age": "24",
                       "hobby": "看剧",
                       "birthday": "保密"
                   }
               };
           var json = JSON.stringify(data);
           json = encodeURIComponent(json);
           var url = "http://localhost:8080/webgisWebSerivce/maptool/exportWordService";
           var form = $("<form accept-charset=\"UTF-8\">");
           form.attr('style', 'display:none');
           form.attr('target', '');
           form.attr('method', 'POST'); //请求方式
           form.attr('action', url);//请求地址
           var input1 = $('<input>');//将你请求的数据模仿成一个input表单
           input1.attr('type', 'hidden');
           input1.attr('name', 'jsonData');//该输入框的name
           input1.attr('value',json);//该输入框的值
           $('body').append(form);
           form.append(input1);
           form.submit();
           form.remove();
       }
    </script>
</body>

</html>
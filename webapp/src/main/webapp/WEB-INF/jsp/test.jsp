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
       
       function exportText() {
           var data = $("#zuobiaoshuchu").val();
           if(data == ""){
               return;
           }
           var txtData = JSON.stringify(data);
           txtData = encodeURIComponent(txtData);
           var url = "http://localhost:8080/webgisWebService/public/maptool/exportTxt";
           var params = {data:txtData};
           usePostMethodExportFile(params,url);
       }
       
       function exportExcel() {
           var data ={
               "sheetName": "图斑协调预演",
               "className": "LineOfControlConflict",
               "columns": [
                   {"title": "控制线名称",
                       "key": "landType"
                   },{"title": "个数",
                       "key": "patternSpotNumber"
                   },{"title": "占用面积",
                       "key": "floorSpace"
                   }],
               "rowData": [{"landType": "生态控制线",
                   "patternSpotNumber": "26",
                   "floorSpace": "4568310.73m²"
               },{
                   "landType": "城镇开发边界控制线",
                   "patternSpotNumber": "10",
                   "floorSpace": "18757458.85m²"
               }]};
           var json = JSON.stringify(data);
           json = encodeURIComponent(json);
           var url = "http://localhost:8080/webgisWebService/public/maptool/exportExcelService";
           var params = {jsonData:json};
           usePostMethodExportFile(params,url);
       }
       
       function exportWord() {
           var data =      {
               "templatePath": "FZXZPGReport.docx",
               "exportDataMap": {
                   "DKBH": "2157",
                   "DKMJ": "48,715.4",
                   "XMBH": "AQ201800108",
                   "XMMC": "蓝宝石晶体工业项目",
                   "YDLX": "一类工业用地",
                   "YDMJ": "48,715.4",
                   "PIC_MAP": {
                       "data": [],
                       "height": 400,
                       "path": "E:\\wordmodel\\dikuai.jpg",
                       "width": 500
                   },
                   "SPZT": "",
                   "TB_SPXX": {
                       "datas": [
                           {
                               "rowData": [
                                   {
                                       "text": ""
                                   },
                                   {
                                       "text": "审批信息"
                                   },
                                   {
                                       "text": "审批数量"
                                   },
                                   {
                                       "text": "审批面积（平方米）"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "1"
                                   },
                                   {
                                       "text": "建设用地批准书"
                                   },
                                   {
                                       "text": "0"
                                   },
                                   {
                                       "text": "0m2"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "2"
                                   },
                                   {
                                       "text": "建设用地批准书"
                                   },
                                   {
                                       "text": "0"
                                   },
                                   {
                                       "text": "0m2"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "3"
                                   },
                                   {
                                       "text": "建设用地批准书"
                                   },
                                   {
                                       "text": "0"
                                   },
                                   {
                                       "text": "0m2"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "4"
                                   },
                                   {
                                       "text": "建设用地选址意见书"
                                   },
                                   {
                                       "text": "0"
                                   },
                                   {
                                       "text": "0m2"
                                   }
                               ]

                           }
                       ],
                       "headers": {
                           "rowData": [
                               {
                                   "text": "审批信息统计"
                               },
                               {
                                   "text": ""
                               },
                               {
                                   "text": ""
                               },
                               {
                                   "text": ""
                               }
                           ],
                           "mergeCellBool":true,
                           "startCell":0,
                           "endCell":3
                       }
                   },
                   "TB_ZBDKXX":{
                       "datas": [
                           {
                               "rowData": [
                                   {
                                       "text": ""
                                   },
                                   {
                                       "text": "地块编码"
                                   },
                                   {
                                       "text": "规划用地代码"
                                   },
                                   {
                                       "text": "规划用地名称"
                                   },
                                   {
                                       "text": "地块面积（平方米）"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "1"
                                   },
                                   {
                                       "text": "2,138"
                                   },
                                   {
                                       "text": "G1"
                                   },
                                   {
                                       "text": "公园绿地"
                                   },
                                   {
                                       "text": "9459.37 m2"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "1"
                                   },
                                   {
                                       "text": "2,138"
                                   },
                                   {
                                       "text": "G1"
                                   },
                                   {
                                       "text": "公园绿地"
                                   },
                                   {
                                       "text": "9459.37 m2"
                                   }
                               ]
                           }
                       ],
                       "headers": {
                           "rowData": [
                               {
                                   "text": "周边地块信息"
                               },
                               {
                                   "text": ""
                               },
                               {
                                   "text": ""
                               },
                               {
                                   "text": ""
                               },
                               {
                                   "text": ""
                               }
                           ],
                           "mergeCellBool":true,
                           "startCell":0,
                           "endCell":4
                       }
                   },
                   "TB_KZXJCJG":{
                       "datas": [
                           {
                               "rowData": [
                                   {
                                       "text": ""
                                   },
                                   {
                                       "text": "用地类型"
                                   },
                                   {
                                       "text": "图斑个数"
                                   },
                                   {
                                       "text": "占地面积（平方米）"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "1"
                                   },
                                   {
                                       "text": "城市开发边界控制线"
                                   },
                                   {
                                       "text": "1"
                                   },
                                   {
                                       "text": "48715.38 m2"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "1"
                                   },
                                   {
                                       "text": "城市开发边界控制线"
                                   },
                                   {
                                       "text": "1"
                                   },
                                   {
                                       "text": "48715.38 m2"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "2"
                                   },
                                   {
                                       "text": "建设用地规模边界"
                                   },
                                   {
                                       "text": "2"
                                   },
                                   {
                                       "text": "97430.76 m2"
                                   }
                               ]
                           },
                           {
                               "rowData": [
                                   {
                                       "text": "3"
                                   },
                                   {
                                       "text": "生态保护红线"
                                   },
                                   {
                                       "text": "0"
                                   },
                                   {
                                       "text": "0m2"
                                   }
                               ]
                           }
                       ],
                       "headers": {
                           "rowData": [
                               {
                                   "text": "控制线检测结果"
                               },
                               {
                                   "text": ""
                               },
                               {
                                   "text": ""
                               },
                               {
                                   "text": ""
                               }
                           ],
                           "mergeCellBool":true,
                           "startCell":0,
                           "endCell":3
                       }
                   }
               }
           };
           var json = JSON.stringify(data);
           json = encodeURIComponent(json);
           var url = "http://localhost:8080/webgisWebService/public/maptool/exportWordService";
           var params = {jsonData:json};
           usePostMethodExportFile(params,url);
       }

       //params是post请求需要的参数，url是请求url地址
       function usePostMethodExportFile(params, url) {
            var form = document.createElement("form");
            form.style.display = 'none';
            form.action = url;
            form.method = "post";
            form.acceptCharset = "UTF-8";
            document.body.appendChild(form);

            for(var key in params){
                var input = document.createElement("input");
                input.type = "hidden";
                input.name = key;
                input.value = params[key];
                form.appendChild(input);
            }

            form.submit();
            form.remove();
        }
    </script>
</body>

</html>
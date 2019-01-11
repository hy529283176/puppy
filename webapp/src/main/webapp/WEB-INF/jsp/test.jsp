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
               "sheetName": "",
               "className": "CADCoverageName",
               "columns": [
                   {
                       "title": "CAD图层名称",
                       "key": "cadCoverageName"
                   },
                   {
                       "title": "要素代码",
                       "key": "factorCode"
                   },
                   {
                       "title": "名称",
                       "key": "villageName"
                   },
                   {
                       "title": "备注",
                       "key": "description"
                   },
                   {
                       "title": "用地面积",
                       "key": "siteArea"
                   }],
               "rowData": [
                   {
                       "cadCoverageName": "生态控制线",
                       "factorCode": "26",
                       "villageName": "生态控制线",
                       "description": "26",
                       "siteArea": "4568310.73m²"
                   },
                   {
                       "cadCoverageName": "生态控制线",
                       "factorCode": "26",
                       "villageName": "生态控制线",
                       "description": "26",
                       "siteArea": "4568310.73m²"
                   }
               ]};
           var json = JSON.stringify(data);
           json = encodeURIComponent(json);
           var url = "http://localhost:8080/webgisWebService/public/maptool/exportExcelService";
           var params = {jsonData:json};
           usePostMethodExportFile(params,url);
       }
       
       function exportWord() {
           var data =    {
               "templatePath": "LGCTFXReport.docx",
               "exportDataMap": {
                   "JCDL": "建设用地",
                   "JCSJ": "2015-11-18 11:13",
                   "JCTC": "城市空间布局规划土地利用总体规划-用途分区",
                   "JCMJ": "588419.74m2",
                   "PICLIST": [
                       {
                           "data": [],
                           "height": "200",
                           "path": "http://192.168.10.115:6080/arcgis/rest/directories/arcgisoutput/Utilities/PrintingTools_GPServer/_ags_08a845566084496e8d94f53da6d2afdd.png",
                           "width": "300"
                       }
                   ],
                   "TBLIST": [
                       {
                           "datas": [
                               {
                                   "rowData": [
                                       {
                                           "text": "两规均为建设用地"
                                       },
                                       {
                                           "text": "60"
                                       },
                                       {
                                           "text": "523,555.17"
                                       },
                                       {
                                           "text": "88.98%"
                                       }
                                   ]
                               },
                               {
                                   "rowData": [
                                       {
                                           "text": "土规非建设用地，城规建设用地"
                                       },
                                       {
                                           "text": "4"
                                       },
                                       {
                                           "text": "64,523.2"
                                       },
                                       {
                                           "text": "10.97%"
                                       }
                                   ]
                               },
                               {
                                   "rowData": [
                                       {
                                           "text": "土规建设用地，城规非建设用地"
                                       },
                                       {
                                           "text": "1"
                                       },
                                       {
                                           "text": "341.37"
                                       },
                                       {
                                           "text": "0.06%"
                                       }
                                   ]
                               },
                               {
                                   "rowData": [
                                       {
                                           "text": "两规均为非建设用地"
                                       },
                                       {
                                           "text": "0"
                                       },
                                       {
                                           "text": "0"
                                       },
                                       {
                                           "text": "0%"
                                       }
                                   ]
                               }
                           ],
                           "headers": {
                               "rowData": [
                                   {
                                       "text": "分类结果"
                                   },
                                   {
                                       "text": "地块个数"
                                   },
                                   {
                                       "text": "占地面积（平方米）"
                                   },
                                   {
                                       "text": "面积占比"
                                   }
                               ]
                           }
                       },
                       {
                           "datas": [
                               {
                                   "rowData": [
                                       {
                                           "text": ""
                                       },
                                       {
                                           "text": "城规用地性质"
                                       },
                                       {
                                           "text": "土规用途分区"
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
                                           "text": "C6_教育科研设计用地"
                                       },
                                       {
                                           "text": "城镇用地区"
                                       },
                                       {
                                           "text": "247996.30"
                                       }
                                   ]
                               }
                           ],
                           "headers": {
                               "rowData": [
                                   {
                                       "text": "两规均为建设用地"
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
                               "mergeCellBool": true,
                               "startCell": 0,
                               "endCell": 3
                           }
                       },
                       {
                           "datas": [
                               {
                                   "rowData": [
                                       {
                                           "text": ""
                                       },
                                       {
                                           "text": "城规用地性质"
                                       },
                                       {
                                           "text": "土规用途分区"
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
                                           "text": "C6_教育科研设计用地"
                                       },
                                       {
                                           "text": "水域用地区"
                                       },
                                       {
                                           "text": "51097.77"
                                       }
                                   ]
                               }
                           ],
                           "headers": {
                               "rowData": [
                                   {
                                       "text": "土规非建设用地，城规建设用地"
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
                               "mergeCellBool": true,
                               "startCell": 0,
                               "endCell": 3
                           }
                       },
                       {
                           "datas": [
                               {
                                   "rowData": [
                                       {
                                           "text": ""
                                       },
                                       {
                                           "text": "城规用地性质"
                                       },
                                       {
                                           "text": "土规用途分区"
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
                                           "text": "E1_水域"
                                       },
                                       {
                                           "text": "城镇用地区"
                                       },
                                       {
                                           "text": "341.37"
                                       }
                                   ]
                               }
                           ],
                           "headers": {
                               "rowData": [
                                   {
                                       "text": "土规建设用地，城规非建设用地"
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
                               "mergeCellBool": true,
                               "startCell": 0,
                               "endCell": 3
                           }
                       },
                       {
                           "datas": [
                               {
                                   "rowData": [
                                       {
                                           "text": ""
                                       },
                                       {
                                           "text": "城规用地性质"
                                       },
                                       {
                                           "text": "土规用途分区"
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
                                           "text": "E1_水域"
                                       },
                                       {
                                           "text": "城镇用地区"
                                       },
                                       {
                                           "text": "341.37"
                                       }
                                   ]
                               }
                           ],
                           "headers": {
                               "rowData": [
                                   {
                                       "text": "两规均为非建设用地"
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
                               "mergeCellBool": true,
                               "startCell": 0,
                               "endCell": 3
                           }
                       }
                   ]
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
            form.method = "POST";
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
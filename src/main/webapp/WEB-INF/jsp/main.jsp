<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import = "com.panpom.springmvc01.bean.*" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%  String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link href="<%=path%>/css/main_css.css" rel="stylesheet">
<link href="<%=path%>/css/zTreeStyle.css" rel="stylesheet">


<%--不能用3.2.1  不然显示不出树--%>
<%--<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.js"></script>--%>
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.ztree.core-3.2.js"></script>
<script type="text/javascript" src="<%=path%>/js/commonAll.js"></script>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Main</title>


    <script type="text/javascript">
        /**退出系统**/
        function logout(){
            if(confirm("您确定要退出本系统吗？")){

                $.ajax({
                    type:'POST',
                    data: {
                        val1: name,
                    },
                    url:'message-cleanSession',
                    dataType:'json',
                    success: function(data){
                        json =data;//alert(data);
                    },
                    error : function() {
                        window.location.replace("index.jsp");
                    }

                });
            }
        }
        /**获得当前日期**/
        function  getDate01(){
            var time = new Date();
            var myYear = time.getFullYear();
            var myMonth = time.getMonth()+1;
            var myDay = time.getDate();
            var myHour = time.getHours();
            var mymiuth = time.getMinutes();
            if(myMonth < 10){
                myMonth = "0" + myMonth;
            }

            document.getElementById("day_day").innerHTML =  myYear + "." + myMonth+"."+myDay;
            document.getElementById("day_hour").innerHTML =  myHour + ":" + mymiuth+":00";

        }
    </script>
    <script type="text/javascript">
        /* zTree插件加载目录的处理  */
        var zTree;
        var setting = {
            view: {
                dblClickExpand: false,
                showLine: false,
                expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
            },
            data: {
                key: {
                    name: "resourceName"
                },
                simpleData: {
                    enable:true,
                    idKey: "resourceID",
                    pIdKey: "parentID",
                    rootPId: ""
                }
            },
            callback: {
// 				beforeExpand: beforeExpand,
// 				onExpand: onExpand,
                onClick: zTreeOnClick
            }
        };
        var curExpandNode = null;
        function beforeExpand(treeId, treeNode) {
            var pNode = curExpandNode ? curExpandNode.getParentNode():null;
            var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
            for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
                if (treeNode !== treeNodeP.children[i]) {
                    zTree.expandNode(treeNodeP.children[i], false);
                }
            }
            while (pNode) {
                if (pNode === treeNode) {
                    break;
                }
                pNode = pNode.getParentNode();
            }
            if (!pNode) {
                singlePath(treeNode);
            }

        }
        function singlePath(newNode) {
            if (newNode === curExpandNode) return;
            if (curExpandNode && curExpandNode.open==true) {
                if (newNode.parentTId === curExpandNode.parentTId) {
                    zTree.expandNode(curExpandNode, false);
                } else {
                    var newParents = [];
                    while (newNode) {
                        newNode = newNode.getParentNode();
                        if (newNode === curExpandNode) {
                            newParents = null;
                            break;
                        } else if (newNode) {
                            newParents.push(newNode);
                        }
                    }
                    if (newParents!=null) {
                        var oldNode = curExpandNode;
                        var oldParents = [];
                        while (oldNode) {
                            oldNode = oldNode.getParentNode();
                            if (oldNode) {
                                oldParents.push(oldNode);
                            }
                        }
                        if (newParents.length>0) {
                            for (var i = Math.min(newParents.length, oldParents.length)-1; i>=0; i--) {
                                if (newParents[i] !== oldParents[i]) {
                                    zTree.expandNode(oldParents[i], false);
                                    break;
                                }
                            }
                        }else {
                            zTree.expandNode(oldParents[oldParents.length-1], false);
                        }
                    }
                }
            }
            curExpandNode = newNode;
        }

        function onExpand(event, treeId, treeNode) {
            curExpandNode = treeNode;
        }

        /** 用于捕获节点被点击的事件回调函数  **/
        function zTreeOnClick(event, treeId, treeNode) {

            var zTree = $.fn.zTree.getZTreeObj("dleft_tab1");
            zTree.expandNode(treeNode, null, null, null, true);
// 		zTree.expandNode(treeNode);
            // 规定：如果是父类节点，不允许单击操作
            if(treeNode.isParent){
// 			alert("父类节点无法点击哦...");
                return false;
            }
            // 如果节点路径为空或者为"#"，不允许单击操作
            if(treeNode.accessPath=="" || treeNode.accessPath=="#"){
                //alert("节点路径为空或者为'#'哦...");
                // alert(treeNode.resourceID)
                switch(treeNode.resourceID){
                    case 10:
                        $('#rightMain').attr("src","index");
                        document.getElementById("here_area").innerHTML = "当前位置：账号管理&nbsp;>&nbsp;用户管理"
                        break;
                    case 11:
                        $('#rightMain').attr("src","main");
                        document.getElementById("here_area").innerHTML = "当前位置：账号管理&nbsp;>&nbsp;村管理"
                        break;
                    case 12:
                        // $('#main').load("main-shwoTown.action?type=0");
                        $('#rightMain').attr("src","main-shwoTown.action?type=0");
                        document.getElementById("here_area").innerHTML = "当前位置：账号管理&nbsp;>&nbsp;镇管理"
                        break;
                    case 13:
                        // $('#main').load("main-showservice.action?type=0");
                        $('#rightMain').attr("src","main-showservice.action?type=0");
                        document.getElementById("here_area").innerHTML = "当前位置：账号管理&nbsp;>&nbsp;服务管理"
                        break;
                    case 14:
                        // $('#main').load("main-showDeployNotice.action");
                        $('#rightMain').attr("src","main-showDeployNotice.action");
                        document.getElementById("here_area").innerHTML = "当前位置：内容管理&nbsp;>&nbsp;部署管理"
                        break;
                    case 15:
                        // $('#main').load("file-getFiles.action");
                        $('#rightMain').attr("src","file-getFiles.action");
                        document.getElementById("here_area").innerHTML = "当前位置：内容管理&nbsp;>&nbsp;目录管理"
                        break;
                    case 16:
                        // $('#main').load("main-showLog.action");
                        $('#rightMain').attr("src","main-showLog.action");
                        document.getElementById("here_area").innerHTML = "当前位置：记录查询&nbsp;>&nbsp;查询所有记录"
                        break;
                    case 17:
                        // $('#main').load("main-help.action");
                        $('#rightMain').attr("src","main-help.action");
                        document.getElementById("here_area").innerHTML = "当前位置：帮助说明&nbsp;>&nbsp;帮助说明"
                        break;
                    case 18:
                        //$('#main').load("file-helpdownloadfile.action");
                        $('#rightMain').attr("src","file-helpdownloadfile.action");
                        document.getElementById("here_area").innerHTML = "当前位置：帮助说明&nbsp;>&nbsp;模版文件下载"
                        break;
                }

                return false;
            }
            // 跳到该节点下对应的路径, 把当前资源ID(resourceID)传到后台，写进Session

            rightMain(treeNode.accessPath);

            if( treeNode.isParent ){
                $('#here_area').html('当前位置：'+treeNode.getParentNode().resourceName+'&nbsp;>&nbsp;<span style="color:#1A5CC6">'+treeNode.resourceName+'</span>');
            }else{
                $('#here_area').html('当前位置：系统&nbsp;>&nbsp;<span style="color:#1A5CC6">'+treeNode.resourceName+'</span>');
            }
        };
        /* 图标菜单 */
        function switchTab(tabpage,tabid){

            var oItem = document.getElementById(tabpage).getElementsByTagName("li");
            for(var i=0; i<oItem.length; i++){
                var x = oItem[i];
                x.className = "";
            }
            if('left_tab1' == tabid){
                $(document).ajaxStart(onStart).ajaxSuccess(onStop);
                // 异步加载"业务模块"下的菜单
                loadMenu('YEWUMOKUAI', 'dleft_tab1');
            }else  if('left_tab2' == tabid){
                $(document).ajaxStart(onStart).ajaxSuccess(onStop);
                // 异步加载"系统管理"下的菜单
                loadMenu('XITONGMOKUAI', 'dleft_tab1');
            }else  if('left_tab3' == tabid){
                $(document).ajaxStart(onStart).ajaxSuccess(onStop);
                // 异步加载"其他"下的菜单
                loadMenu('QITAMOKUAI', 'dleft_tab1');
            }
        }


        $(document).ready(function(){
            $(document).ajaxStart(onStart).ajaxSuccess(onStop);
            /** 默认异步加载"业务模块"目录  **/
            loadMenu('YEWUMOKUAI', "dleft_tab1");
            // 默认展开所有节点
            if( zTree ){
                // 默认展开所有节点
                zTree.expandAll(true);
            }
        });

        function loadMenu(resourceType, treeObj){

            data = [
                {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":3,"resourceName":"账号管理","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":3,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":10,"resourceName":"用户管理","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":3,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":11,"resourceName":"村管理","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":3,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":12,"resourceName":"镇管理","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":3,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":13,"resourceName":"服务管理","resourceOrder":0,"resourceType":""},

                {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":2,"resourceName":"内容管理","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":14,"resourceName":"部署管理","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":15,"resourceName":"目录操作","resourceOrder":0,"resourceType":""},

                {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":4,"resourceName":"记录查询","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":4,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":16,"resourceName":"查询所有记录","resourceOrder":0,"resourceType":""},

                {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":5,"resourceName":"帮助说明","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":5,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":17,"resourceName":"帮助说明","resourceOrder":0,"resourceType":""},
                {"accessPath":"","checked":false,"delFlag":0,"parentID":5,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":18,"resourceName":"帮助文档下载","resourceOrder":0,"resourceType":""}
            ];
            // 如果返回数据不为空，加载"业务模块"目录
            if(data != null){
                // 将返回的数据赋给zTree
                $.fn.zTree.init($("#"+treeObj), setting, data);
//      alert(treeObj);
                zTree = $.fn.zTree.getZTreeObj(treeObj);
                if( zTree ){
                    // 默认展开所有节点
                    zTree.expandAll(true);
                }
            }
        }

        //ajax start function
        function onStart(){
            $("#ajaxDialog").show();
        }

        //ajax stop function
        function onStop(){
// 		$("#ajaxDialog").dialog("close");
            $("#ajaxDialog").hide();
        }

        function getTime()
        {
            var time = new Date();
            $("#day_hour").html(time.toTimeString().substring(0,8));
        }
        $(function(){
            setInterval("getTime()",1000);
        });
    </script>
</head>


<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    response.flushBuffer();
    User user=(User)request.getSession().getAttribute("user");
    if(user == null){
        user=new User();
    }
//    Logger log = LoggerFactory.getLogger(this.getClass());
//    log.error("==========________");

%>
<body onload="getDate01()">
<div id="top">
    <div id="top_logo">
        <img alt="logo" src="images/logo.jpg" width="274" height="49" style="vertical-align:middle;">
    </div>
    <div id="top_links">
        <div id="top_op">
            <ul>
                <li>
                    <img alt="当前用户" src="images/user.jpg">：
                    <span><%=user.getuName() %></span>
                </li>

                <li>
                    <img alt="今天是" src="images/date.jpg">：
                    <span id="day_day"></span>
                </li>
                <li>
                    <img alt="现在时间" src="images/month.jpg">：
                    <span id="day_hour"></span>
                </li>
            </ul>
        </div>
        <div id="top_close">
            <a href="javascript:void(0);" onclick="logout();" target="_parent">
                <img alt="退出系统" title="退出系统" src="images/close.jpg" style="position: relative; top: 10px; left: 25px;">
            </a>
        </div>
    </div>
</div>
<!-- side menu start -->
<div id="side">
    <div id="left_menu">
        <ul id="TabPage2" style="height:200px; margin-top:50px;">
            <li id="left_tab1" class="selected" onClick="javascript:switchTab('TabPage2','left_tab1');" title="三务公开">
                <img alt="三务公开" title="三务公开" src="images/1_hover.jpg" width="33" height="31">
            </li>

        </ul>


        <div id="nav_show" style="position:absolute; bottom:0px; padding:10px;">
            <a href="javascript:;" id="show_hide_btn">
                <img alt="显示/隐藏" title="显示/隐藏" src="images/nav_hide.png" width="35" height="35">
            </a>
        </div>
    </div>
    <div id="left_menu_cnt">
        <div id="nav_module">
            <img src="images/module_1.png" width="210" height="58"/>
        </div>
        <div id="nav_resource">
            <ul id="dleft_tab1" class="ztree"></ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $('#TabPage2 li').click(function(){
            var index = $(this).index();
            $(this).find('img').attr('src', 'images/'+ (index+1) +'_hover.jpg');
            $(this).css({background:'#fff'});
            $('#nav_module').find('img').attr('src', 'images/module_'+ (index+1) +'.png');
            $('#TabPage2 li').each(function(i, ele){
                if( i!=index ){
                    $(ele).find('img').attr('src', 'images/'+ (i+1) +'.jpg');
                    $(ele).css({background:'#044599'});
                }
            });
            // 显示侧边栏
            switchSysBar(true);
        });

        // 显示隐藏侧边栏
        $("#show_hide_btn").click(function() {
            switchSysBar();
        });
    });

    /**隐藏或者显示侧边栏**/
    function switchSysBar(flag){
        var side = $('#side');
        var left_menu_cnt = $('#left_menu_cnt');
        if( flag==true ){	// flag==true
            left_menu_cnt.show(500, 'linear');
            side.css({width:'280px'});
            $('#top_nav').css({width:'77%', left:'304px'});
            $('#main').css({left:'280px'});
        }else{
            if ( left_menu_cnt.is(":visible") ) {
                left_menu_cnt.hide(10, 'linear');
                side.css({width:'60px'});
                $('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
                $('#main').css({left:'60px'});
                $("#show_hide_btn").find('img').attr('src', 'images/nav_show.png');
            } else {
                left_menu_cnt.show(500, 'linear');
                side.css({width:'280px'});
                $('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
                $('#main').css({left:'280px'});
                $("#show_hide_btn").find('img').attr('src', 'images/nav_hide.png');
            }
        }
    }
</script>
<!-- side menu start -->
<div id="top_nav">
    <span id="here_area">当前位置：系统&nbsp;>&nbsp;系统介绍</span>
</div>
<div id="main">
    <iframe name="right" id="rightMain"  frameborder="no" scrolling="auto" width="100%" height="100%" allowtransparency="true"/>
</div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
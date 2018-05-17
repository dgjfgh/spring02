<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body>

<div ng-app="" ng-init="firstName='John1'">

    <p>姓名为 <span ng-bind="firstName"></span></p>

    <p>我的第一个表达式： {{ 5 + 5 }}</p></div>

<div ng-app="myApp" ng-controller="myCtrl">

    名: <input type="text" ng-model="firstName"><br>
    姓: <input type="text" ng-model="lastName"><br>
    <br>
    姓名: {{firstName + " " + lastName}}

</div>

<div ng-app="" ng-init="names=['Jani','Hege','Kai']">
    <p>使用 ng-repeat 来循环数组</p>
    <ul>
        <li ng-repeat="x in names">
            {{ x }}
        </li>
    </ul>
</div>

<script>
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function ($scope) {
        $scope.firstName = "John";
        $scope.lastName = "Doe";
    });
</script>

</body>
</html>



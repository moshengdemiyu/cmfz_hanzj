<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script src="${path}/js/echarts.js"></script>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.get("${path}/echarts/selectBySexAndMonth",function (data) {

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '用户统计图',   // 标题内容
                    show:true,
                    subtext: '纯属虚构',
                    sublink: '${path}/main/main.jsp',
                    subtarget: 'self'
                },
                tooltip: {},    // 鼠标提示
                legend: {
                    data:['小男生','小女生']         // 选项卡
                },
                xAxis: {
                    data: data.month    // 横轴展示
                },
                yAxis: {},  // 纵轴展示     自适应
                series: [{
                    name: '小男生',
                    type: 'bar',
                    data: data.boys  //数据
                },{
                    name: '小女生',
                    type: 'line',
                    data: data.girls  //数据
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },"json")

</script>


<%--用户统计--%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
    </head>
    <body>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="main" style="width: 600px;height:400px;"></div>

    </body>
</html>
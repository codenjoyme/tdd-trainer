<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <meta charset="utf-8">
    <title>Game boards</title>
    <link href="${ctx}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<link href="${ctx}/resources/css/dojo.css" rel="stylesheet">
<script src="${ctx}/resources/js/jquery-1.7.2.js"></script>
<script src="${ctx}/resources/js/jcanvas.min.js"></script>

<script src="${ctx}/resources/js/board.js"></script>
<script>
    $(document).ready(function () {
        initBoard('${player.name}', '${ctx}/');
    });
</script>

</body>
    <span class="score-info width-calculator" id="width_calculator_container"></span>
    <div id="showdata"></div>
    <div>
        <div id="glasses">
            <div id="div_${player.name}" style="float: left">
                <table>
                    <tr>
                        <td>
                            <span id="player_name" class="label label-info big">${player.name}</span> :
                            <span class="label label-info big" id="score_${player.name}"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <canvas id="${player.name}" width="${boardSize*30}" height="${boardSize*30}" style="border:1px solid">
                                <!-- each pixel is 24x24-->
                                Your browser does not support the canvas element.
                            </canvas>

                            <span class="score-info" id="score_info_${player.name}">+200</span>
                        </td>
                    </tr>
                </table>
            </div>

            <div id="systemCanvas" style="display: none">
                <canvas id="_system" width="168" height="24"> <!-- 7 figures x 24px-->
                    Your browser does not support the canvas element.
                </canvas>

                <%--<img src="${ctx}/resources/sprite/red.png" id="red">
                <img src="${ctx}/resources/sprite/green.png" id="green">--%>
            </div>
        </div>
    </div>
</div>
</html>
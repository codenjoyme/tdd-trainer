function initBoard(playerName, contextPath){
    var canvas = new Object();
    var infoPool = new Object();

    canvas = createCanvas(playerName);
    infoPool = [];

    function constructUrl() {
        var url = contextPath + "screen?";

        return url + "allPlayersScreen=false&" + playerName;
    }

    function drawBoardForPlayer(data) {
        canvas.resizeHeight(data.testList.length + 1);
        canvas.clear();

        for (var index in data.testList) {
            var value = data.testList[index];
            if (value.indexOf('fail') != -1) color = '#f00';
            if (value.indexOf('success') != -1) color = '#0f0';
            if (value.indexOf('next') != -1) color = '#0ff';
            canvas.drawText(value, {x:7, y:1 + parseInt(index)}, color);
        }

        canvas.drawText(parseInt(data.leave) + ' sec', {x:13, y:1}, '#FF0');
    }

    function calculateTextSize(text) {
        var div = $("#width_calculator_container");
        div.html(text);
        div.css('display', 'block');
        return div[0];
    }

    function showScoreInformation(information) {
        if (!!information && information.length > 0) {
            for (var i in information) {
                if (information[i] == '0') {
                    continue;
                }
                infoPool.push(information[i]);
            }
        }
        if (infoPool.length == 0) return;

        var score = $("#score_info_" + playerName);
        if (score.is(':visible')) {
            return;
        }

        var text = '<center>' + infoPool.join('<br><br>') + '</center>';
        infoPool.splice(0, infoPool.length);

        var canvas = $("#" + playerName);
        var size = calculateTextSize(text);
        score.css({
                position: "absolute",
                marginLeft: 0,
                marginTop: 0,
                left: canvas.position().left + canvas.width()/2 - size.clientWidth/2,
                top: canvas.position().top + canvas.height()/2 - size.clientHeight/2
            });

        score.html(text);

        score.show().delay(300).fadeOut(1600, function() {
            score.hide();

            showScoreInformation('');
        });
    }

    function createCanvas(canvasName) {
        var plotSize = 20;
        var canvas = $("#" + canvasName);
        canvas[0].width = plotSize * 15 ;
        canvas[0].height = plotSize * 15;

        var drawPlot = function(color, x, y) {
            var plot = $("#" + color)[0];
            canvas.drawImage({
                source:plot,
                x:x * plotSize + plotSize / 2,
                y:(boardSize - y) * plotSize - plotSize / 2
            });
        };

        var drawText = function(name, pt, color) {
            canvas.drawText({
                fillStyle: color,
                strokeStyle: '#000',
                strokeWidth: 3,
                x: (pt.x) * plotSize, y: (pt.y) * plotSize,
                fontSize: 10,
                fontFamily: 'Verdana, sans-serif',
                text: name
            });
        }

        var clear = function() {
            canvas.clearCanvas();
        }

        var resizeHeight = function(current) {
            if (canvas[0].height < current * plotSize) {
                canvas[0].height = canvas[0].height * 1.5;
            }
        }

        return {
            drawPlot : drawPlot,
            drawText: drawText,
            clear : clear,
            resizeHeight : resizeHeight
        };
    }

    function drawUsersCanvas(data) {
        if (data == null) {
            $("#showdata").text("There is NO data for player available!");
            return;
        }
        $("#showdata").text('');

        drawUserCanvas(data);
    }

    function drawUserCanvas(data) {
        drawBoardForPlayer(data);
        $("#score_" + playerName).text(data.score);
        showScoreInformation(data.info);
    }

    $('body').bind("board-updated", function(events, data) {
        drawUsersCanvas(data);
    });

    function updatePlayersInfo() {
        currentCommand = null; // for joystick.js
        $.ajax({ url:constructUrl(),
                success:function (data) {
                    $('body').trigger("board-updated", data[playerName]);
                },
                dataType:"json",
                cache:false,
                complete:updatePlayersInfo,
                timeout:30000
            });
    }

    updatePlayersInfo();
}
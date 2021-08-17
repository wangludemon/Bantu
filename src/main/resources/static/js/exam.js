    //剩余答题时间（秒）
    var lefttime = 3600

    window.onload = function () {

        countDown();
        function addZero(i) {
            return i < 10 ? "0" + i : i + "";
        }
        function countDown() {

            lefttime = lefttime - 1;


            var h = parseInt(lefttime / (60 * 60) % 24);
            var m = parseInt(lefttime / 60 % 60);
            var s = parseInt(lefttime % 60);

            h = addZero(h);
            m = addZero(m);
            s = addZero(s);
            document.getElementsByClassName("count")[0].innerHTML = `${h} : ${m} : ${s} `;
            document.getElementsByClassName("count")[1].innerHTML = `${h} : ${m} : ${s} `;
            if (lefttime <= 0) {
                // document.getElementsByClassName("count")[0].innerHTML = "活动已结束";
                return;
            }
            setTimeout(countDown, 1000);
        }

    }

 $(function(){
             $(":radio ").click(function(){

               alert($(this).parent().parent().parent().attr("id"));

              var examId = $(this).parent().parent().parent().attr("id");

              var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
    				// 设置已答题
    				if(!cardLi.hasClass('hasBeenAnswer')){
                        cardLi.addClass('hasBeenAnswer');
                    }

             })

             $(":checkbox ").click(function(){

              //alert($(this).parent().parent().parent().attr("id"));

             var examId = $(this).parent().parent().parent().attr("id");

             var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
                   // 设置已答题
                   if(!cardLi.hasClass('hasBeenAnswer')){
                       cardLi.addClass('hasBeenAnswer');
                   }

            })


            });

!function () {
	window.onload=function  () {
		//define global variable
		var $banner = $(".banner-imgs-img")
		var $loadCover = $(".load-cover")

		// define function
		function imagesmove ($node) {
			// body... 
			var imagesCount=$node.children('a').length
			var imageWidth=$node.width()
			var singleImageWidth=$node.find('img').width()
			var lastImageLeft=-singleImageWidth*(imagesCount-1)
			var nowIndex=0
			var oldIndex=1
			$('#bt1').css("background",'rgba(255,255,255,0.8)')
			window.setInterval(function () {
				var imagesCount=$node.children('a').length
				var imageWidth=$node.width()
				var singleImageWidth=$node.find('img').width()
				var lastImageLeft=-singleImageWidth*(imagesCount-1)
				var nowLeft=parseInt($node.css('left'))
				var nextLeft=nowLeft-singleImageWidth
				nowIndex=-nowLeft/singleImageWidth

				//image move
				if (nextLeft<=lastImageLeft) {
					$node.css('left',-singleImageWidth+'px')
				}else{
					$node.css('left',nextLeft+'px')
				}

				//buttons move
				$("#bt"+oldIndex).css('background','rgba(10, 10, 10, 0.58)')
				switch (nowIndex) {
					case 1:
						$('#bt2').css("background",'rgba(255,255,255,0.8)')
						oldIndex=2
						break;
					case 2:
						$('#bt3').css("background",'rgba(255,255,255,0.8)')
						oldIndex=3
						break;
					case 3:
						$('#bt4').css("background",'rgba(255,255,255,0.8)')
						oldIndex=4
						break;
					case 4:
						$('#bt5').css("background",'rgba(255,255,255,0.8)')
						oldIndex=5
						break;
					case 5:
						$('#bt1').css("background",'rgba(255,255,255,0.8)')
						oldIndex=1
						break;
					default:
						// statements_def
						break;
				}
			}, 3000)
		}//end function imagesmove

		//excatu function
		imagesmove($banner)


	//event listener section
	$(".header-right-loadbtn").bind("click" ,function  () {
		$loadCover.css("display" , "block")
	})
	$(".load-close-btn").bind("click", function () {
		$loadCover.css("display" , "none")
		$("#load-choose-load").click()
		
	})	
	$(".header-right-registerbtn").bind("click" ,function  () {
		$loadCover.css("display" , "block")
		$(".load-choose-scan").click()
	})
	$(".load-cover").bind("click", function (e) {
		console.log(e.target.className)
		var targetClass = e.target.className
		if (targetClass == "load-cover") {
			$loadCover.css("display" , "none")
		}
	})


	}//the all end

	$(".floor-classSin-detail button").on("click", function () {
		var vid = ($(this).data("vid"));
		var that = $(this);
		if(that.text() == "加入学习"){
            $.post("/study/"+vid, function (data) {
                if(data.success){
                    that.removeClass("btn-success").addClass("btn-warning");
                    that.text("取消学习");
                    that.blur();
                }else{
                    alert(data.msg);
                }
            })
		}else{
            $.post("/study/delete?vid="+vid, function (data) {
            	that.removeClass("btn-warning").addClass("btn-success");
            	that.text("加入学习");
            	that.blur();
            })
		}

    })

    getMoreContents = function () {
        var keyword = document.getElementById("keyword").value;
        if (keyword == "") {
            clearContent();
            return;
        } else {
            var ajax = new XMLHttpRequest();
            var url = "/search/" + keyword;
            ajax.open("GET", url, true);
            ajax.send(null);
            ajax.onreadystatechange = function() {
                if (ajax.readyState == 4) {
                    if (ajax.status == 200) {
                        var result = ajax.responseText;
                        insertContent(result);
                    }
                }
            }
        }
    }
    insertContent = function (content) {
        clearContent();
        setLocation();
        var json = JSON.parse(content);
        var len = json.length;
        for (var i = 0; i < len; i++) {
            var value = json[i];
            var tr = document.createElement("tr");
            var td = document.createElement("td");
            td.setAttribute("bgcolor", "#FFFAFA");
            td.setAttribute("border", "0");
            td.onmouseover = function() {
                this.className = 'mouseOver';
            };
            td.onmouseout = function() {
                this.className = 'mouseOut';
            };
            td.onclick = function() {
                // document.getElementById("keyword").value=this.;
            };
            //var text = document.createTextNode(value);
            var text = document.createElement("a");
            text.href="/dd/2";
            text.text=value;
            text.target = "_blank";
            td.appendChild(text);
            tr.appendChild(td);
            document.getElementById("content_table_body").appendChild(tr);
        }

    }

    clearContent = function () {
        var popNode = document.getElementById("popDiv");
        popNode.style.border = "none";
        var contentNode = document.getElementById("content_table_body");
        var len = contentNode.childNodes.length;
        for (var i = len - 1; i >= 0; i--) {
            contentNode.removeChild(contentNode.childNodes[i]);
        }
    }
    setLocation = function (){
        var inputNode = document.getElementById("keyword");
        var width = inputNode.offsetWidth;
        var left = inputNode["offsetLeft"];
        var top = inputNode.offsetHeight+inputNode["offsetTop"];
        var popNode = document.getElementById("popDiv");
        popNode.style.border = "gray 0.5px solid";
        popNode.style.width = width+"px";
        popNode.style.top = top+"px";
        popNode.style.left = left+"px";
        document.getElementById("content_table").style.width=width+"px";
    }


}()
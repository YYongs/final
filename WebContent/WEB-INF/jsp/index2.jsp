<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!--   -->
<div id="article">
	<div id="header2">
		<h1>uri : WebSocket을 활용한 채팅 : 김 수환 강사님 ^^</h1>
		<p id="time">2016. 12. 1.</p>
	</div>
	<div id="content">

		<script type="text/javascript">
			var wsUri = "ws://192.168.0.105/spring_websocket_1201/websocket/echo";
			websocket = new WebSocket(wsUri);
		
			function init() {
				output = document.getElementById("output");
			}
		
			function send_message() {
				doSend(textID.value);
			}
			
			websocket.onopen = function(evt) {
				console.log("onopen : " + evt);
				onOpen(evt)
			};
			websocket.onmessage = function(evt) {
				console.log("onmessage : " + evt);
				onMessage(evt)
			};
			websocket.onerror = function(evt) {
				onError(evt)
			};
		
			function onOpen(evt) {
				writeToScreen("Connected to Endpoint!");
				doSend(textID.value);
			}
			function onMessage(evt) {
				writeToScreen("Message Received: " + evt.data);
			}
			function onError(evt) {
				writeToScreen('ERROR: ' + evt.data);
			}
			function doSend(message) {
				writeToScreen("Message Sent: " + message);
				websocket.send(message);
			//websocket.close();
			}
			function writeToScreen(message) {
				var pre = document.createElement("p");
				pre.style.wordWrap = "break-word";
				pre.innerHTML = message;
				output.appendChild(pre);
				output.scrollTop = output.scrollHight;
			}
			window.addEventListener("load", init, false);
		</script>
		<h1 style="text-align: center;">MyWebSocket!</h1>
		<br>
		<div style="text-align: center;">
			<form action="">
				<input id="textID" name="message" value="" type="text"><br>
				<input onclick="send_message()" value="Send" type="button">
			</form>
		</div>
		<div id="output"></div>


	</div>
</div>
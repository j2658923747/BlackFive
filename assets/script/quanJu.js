var serverIP="ws://127.0.0.1:80/w";
var socket=new WebSocket(serverIP);

//打开socket
socket.onopen = function(event){
    console.log("连接成功！");
}
//监听消息
socket.onmessage = function(event){
    console.log("有新消息:"+event.data);
}
//服务端断开
socket.onclose = function(event){
    console.log("服务端断开:"+event);
}
//监听异常
socket.onerror = function(event){
    console.log("发生错误:"+event);
}
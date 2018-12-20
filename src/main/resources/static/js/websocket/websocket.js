function WebSocketDemo(url) {
    this.socket = new WebSocket(url);
    this.socket.onopen = function () {
        console.log("Socket 连接成功");
    }
    this.socket.onmessage = function (msg) {
        alert(msg);
    }
}

WebSocketDemo.prototype.close = function () {
    this.socket.close();
}

WebSocketDemo.prototype.send = function (msg) {
    this.socket.send(msg);

}

let demo = new WebSocketDemo("ws://127.0.0.1/socket");
demo.send("你好");
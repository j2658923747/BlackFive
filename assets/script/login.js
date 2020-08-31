
cc.Class({
    extends: cc.Component,

    properties: {
        username:cc.EditBox,
        password:cc.EditBox,
    },

    // LIFE-CYCLE CALLBACKS:

    // onLoad () {},

    start () {

    },

    onClickLogin:function(){
        //登录
        var jsonObj = '{"code":100,"username":"'+this.username.string+'","password":"'+this.password.string+'"}';
        cc.log(jsonObj);
        socket.send(jsonObj);
    },
    onClickRegister:function(){
        //注册
        var jsonObj = '{"code":101,"username":"'+this.username.string+'","password":"'+this.password.string+'"}';
        cc.log(jsonObj);
        socket.send(jsonObj);
    }
    // update (dt) {},
});

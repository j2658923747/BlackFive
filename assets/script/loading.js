
cc.Class({
    extends: cc.Component,

    properties: {
        proWidth:cc.Integer,
        proNode:cc.Node,
        speed:cc.Integer,
    },


    //规定进度条百分比
    setProgress:function(pro){
        if(pro > 1 || pro < 0){
            return;
        }
        this.nowWidth = this.proWidth * pro;
        // this.proNode.width = nowWidth;
    },

    // LIFE-CYCLE CALLBACKS:

    onLoad () {
        this.proNode.width = 0;
    },

    start () {

    },

    update (dt) {
        if( this.proNode.width < this.nowWidth){
            this.proNode.width += dt*this.speed;
        }
    },
});

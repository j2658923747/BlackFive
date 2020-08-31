// Learn cc.Class:
//  - [Chinese] https://docs.cocos.com/creator/manual/zh/scripting/class.html
//  - [English] http://docs.cocos2d-x.org/creator/manual/en/scripting/class.html
// Learn Attribute:
//  - [Chinese] https://docs.cocos.com/creator/manual/zh/scripting/reference/attributes.html
//  - [English] http://docs.cocos2d-x.org/creator/manual/en/scripting/reference/attributes.html
// Learn life-cycle callbacks:
//  - [Chinese] https://docs.cocos.com/creator/manual/zh/scripting/life-cycle-callbacks.html
//  - [English] https://www.cocos2d-x.org/docs/creator/manual/en/scripting/life-cycle-callbacks.html

cc.Class({
    extends: cc.Component,

    properties: {
        proGress:cc.Prefab,
        allParent:cc.Node,
    },

    // LIFE-CYCLE CALLBACKS:

    onLoad () {
        var gress = cc.instantiate(this.proGress);
        gress.y = -270;
        this.allParent.addChild(gress);
        var gress_script = gress.getComponent('loading');
        gress_script.setProgress(1);
        
    },

    start () {

    },

    // update (dt) {},
});

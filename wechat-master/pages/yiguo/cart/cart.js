var app = getApp();
Page({
  openid:"",
  data:{
 text:"这是一个页面",
 items:[],
 total_prices:0,
 onPullDownRefresh: function () {
    console.log('onPullDownRefresh')
  }
  },
  onShow:function(options){
    // 页面初始化 options为页面跳转所带来的参数
       var that = this;
       wx.getStorage({
       key: 'openId',
       success: function(res) {
           console.log("getopenid session="+res.data)
             that.setData({
                 openid:res.data
              })

      var openId=res.data;
     console.log("openid="+openId);

              //ajax请求数据
     wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptCart/getAllOrder.do',
             data:{
               openId:openId
             },
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {
                console.log("orders="+res.data);
               var jsonText = JSON.parse(res.data);
               var listgoods2=new Array();
               var totalPrices=0;
               for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
                   //计算商品结算总价格
                   totalPrices=totalPrices+jsonText[0][v]["amount"]*jsonText[0][v]["price"];
               }
               that.setData({
                   items:listgoods2,
                   total_prices:totalPrices
               });
               console.log("totalprices="+totalPrices);
             }
         })
         },
        fail: function(res){
          console.log("storage error:"+res.data);
          var openid=app.getOpenId();
          console.log("openid="+openid);
        }
     })
   
    
  },
  lookdetail:function(e){
    var lookid=e.currentTarget.dataset
    wx.switchTab({
      url:"/pages/yiguo/index/index?lookid"
    })
  },
  onReady:function(){
    // 页面渲染完成
    console.log("openid="+this.data.openid);
  },
  onLoad:function(){
    // 页面显示
    var that = this;   
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  },
  getCarts() {
        this.setData({ 
            hidden: !1
        })

        App.HttpService.getCartByUser()
        .then(data => {
            console.log(data)
            if (data.meta.code == 0) {
                data.data.forEach(n => n.goods.thumb_url = App.renderImage(n.goods.images[0] && n.goods.images[0].path))
                this.setData({
                    'carts.items': data.data,
                    'prompt.hidden': data.data.length,
                })
            }
            
            this.setData({ 
                hidden: !0
            })
        })
    },
    onPullDownRefresh() {
        this.getCarts()
    },
    navigateTo(e) {
        console.log(e)
        App.WxService.navigateTo('/pages/goods-detail/index', {
            id: e.currentTarget.dataset.id
        })
    },
    confirmOrder(e) {
        console.log(e)
         wx.navigateTo({
             url:"/pages/yiguo/order-confirm/index"
            })
    },
    del(e) {
        const id = e.currentTarget.dataset.id
console.log("id="+id)
    var that=this;   
	 wx.getStorage({
       key: 'openId',
		success: function(res) {
           console.log("getopenid session="+res.data)

        var openId=res.data;
         console.log("openid="+openId);
        wx.showModal({
            title: '友情提示', 
            content: '确定要删除这个宝贝吗？', 
               success: function(res) {
                   if (res.confirm) {
                     wx.request({
                           url: 'https://j-pongyou.rhcloud.com/xProgram/insweptCart/delToOrders.do',
			 data:{
                openId:openId,
                id:id
			 },
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {

             wx.showToast({
                          title: '成功',
                          icon: 'success',
                        duration: 2000
                })
            
          wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptCart/getAllOrder.do',
             data:{
               openId:openId
             },
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {
                console.log("orders="+res.data);
               var jsonText = JSON.parse(res.data);
               var listgoods2=new Array();
               var totalPrices=0;
               for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
                   //计算商品结算总价格
                   totalPrices=totalPrices+jsonText[0][v]["amount"]*jsonText[0][v]["price"];
               }
               that.setData({
                   items:listgoods2,
                   total_prices:totalPrices
               });
               console.log("totalprices="+totalPrices);
             }
         })
                     }
            
                     
         })
                      }
                  }
             })
             },
        fail: function(res){
          console.log("storage error:"+res.data);
          var openid=app.getOpenId();
          console.log("openid="+openid);
        }
     })
        
    },
    clear() {
     var that=this;
     wx.getStorage({
       key: 'openId',
		success: function(res) {
           console.log("getopenid session="+res.data)

        var openId=res.data;
         console.log("openid="+openId);
        wx.showModal({
            title: '友情提示', 
            content: '确定要清空购物车吗？', 
               success: function(res) {
                   if (res.confirm) {
                     wx.request({
                           url: 'https://j-pongyou.rhcloud.com/xProgram/insweptCart/delAllOrders.do',
			 data:{
                openId:openId
			 },
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {

             wx.showToast({
                          title: '成功',
                          icon: 'success',
                        duration: 2000
                })
            
          wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptCart/getAllOrder.do',
             data:{
               openId:openId
             },
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {
                console.log("orders="+res.data);
               var jsonText = JSON.parse(res.data);
               var listgoods2=new Array();
               var totalPrices=0;
               for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
                   //计算商品结算总价格
                   totalPrices=totalPrices+jsonText[0][v]["amount"]*jsonText[0][v]["price"];
               }
               that.setData({
                   items:listgoods2,
                   total_prices:totalPrices
               });
               console.log("totalprices="+totalPrices);
             }
         })
                     }
            
                     
         })
                      }
                  }
             })
               },
        fail: function(res){
          console.log("storage error:"+res.data);
          var openid=app.getOpenId();
          console.log("openid="+openid);
        }
     })
    },
    onTapEdit(e) {
        this.setData({
            canEdit: !!e.currentTarget.dataset.value
        })
    },
    bindKeyInput(e) {
        const id = e.currentTarget.dataset.id
        const total = Math.abs(e.detail.value)
        if (total < 0 || total > 100) return
        this.putCartByUser(id, {
            total: total
        })
    },
    putCartByUser(id, params) {
        App.HttpService.putCartByUser(id, params)
        .then(data => {
            console.log(data)
            if (data.meta.code == 0) {
                this.getCarts()
            }
        })
    },
    decrease(e) {
        const id = e.currentTarget.dataset.id
        const total = Math.abs(e.currentTarget.dataset.total)
        if (total == 1) return
        this.putCartByUser(id, {
            total: total - 1
        })
    },
    increase(e) {
        const id = e.currentTarget.dataset.id
        const total = Math.abs(e.currentTarget.dataset.total)
        if (total == 100) return
        this.putCartByUser(id, {
            total: total + 1
        })
    }
})
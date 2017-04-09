var app = getApp()
Page({
    data: {
        userInfo: {},
        items:[],
        hidden: !0,
        total_prices:0,
        address:[]
    },
    onLoad:function(options){
        // 页面初始化 options为页面跳转所带来的参数
     var that = this;
     app.getUserInfo(function(userInfo){
       that.setData({
        userInfo:userInfo
       })
      })
      

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
  wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptAddress/getAddress.do',
			 data:{
                openId:openId
			 },
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {
                console.log("addewss="+res.data)
               var jsonText = JSON.parse(res.data);
               var listgoods2=new Array();
               for(var v in jsonText[0]){
                   if(jsonText[0][v].defaultAddress==1)
                   listgoods2[v]=jsonText[0][v];
               }
               
			   for(var i in listgoods2){
				   if(listgoods2[i].defaultAddress==1){
                       that.setData({
                           address:listgoods2
                        });
				   }
			   }
           }
         })
               /*
     wx.request({
             url: 'https://j-pongyou.rhcloud.com/a/test/address.do',
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {
               console.log(res.data);
               var jsonText = JSON.parse(res.data);
               var listgoods2=new Array();
               for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
               }
               that.setData({
                   address:listgoods2
               });
             }
         })*/
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
    onShow() {
        const address_id = this.data.address_id
        if (address_id) {
            this.getAddressDetail(address_id)
        } else {

        }
    },
    redirectTo(e) {
        console.log(e)
        App.WxService.redirectTo('/pages/address-confirm/index', {
            ret: this.data.address_id
        })
    },
    getAddressDetail(id) {
        App.HttpService.getAddressDetail(id)
        .then(data => {
            console.log(data)
            if (data.meta.code == 0) {
                this.setData({
                    'address.item': data.data
                })
            }
        })
    },
    addOrder() {
        var address_id = 0;
        try{
        address_id = this.data.address[0].defaultAddress;
        }catch(e){

        }
        console.log("address="+address_id);
    if(address_id!=1){
         wx.showModal({
               title: '友情提示', 
               content: '没有收货地址，请先设置', 
               success: function(res) {
                   if (res.confirm) {
                       wx.navigateTo({
                          url:"/pages/yiguo/address/index"
                        })
                      }
                     }
                  })
    }else{
      wx.requestPayment({                //微信支付
           'timeStamp': '',
            'nonceStr': '',
            'package': '',
           'signType': 'MD5',
           'paySign': '',
           'success':function(res){
             console.log('success');
                    wx.showToast({
                        title: '支付成功',
                        icon: 'success',
                        duration: 3000
                    });
             },
            'fail':function(res){
               wx.showModal({
                title: '提示',
               content: '支付失败',
               success: function(res) {
                   if (res.confirm) {
                     console.log('用户点击确定')
                      }
                     }
                  })
            },
            'complete':function(res){
                    console.log('complete');
           }
         })
      }

    },
    clear() {
        App.HttpService.clearCartByUser()
        .then(data => {
            console.log(data)
        })
    },
})
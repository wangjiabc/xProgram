var app = getApp()
Page({
  data:{
      openid:"",
      foodId:"",
      detailgood:[],
      onPullDownRefresh: function () {
          console.log('onPullDownRefresh')
      }
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数   
    var foodId=options.id;
     console.log("detailid="+foodId);
    var that=this;
    that.setData({
                   foodId:options.id
               });
       wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptCart/getFoodDetail.do',
             data:{
                  foodId:foodId
             },
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
                   detailgood:listgoods2
               });
               console.log("detailgood="+listgoods2);
             }
         })

           var that = this;

    app.getUserInfo(function(userInfo){
      var nickName = userInfo.nickName
           var avatarUrl = userInfo.avatarUrl
           var gender = userInfo.gender //性别 0：未知、1：男、2：女 
           var province = userInfo.province
           var city = userInfo.city
           var country = userInfo.country
      that.setData({
        userInfo:userInfo
      })
      console.log("useinfo="+userInfo.nickName);
    })

   wx.getStorage({
       key: 'openId',
       success: function(res) {
           console.log("getopenid session="+res.data)
             that.setData({
                 openid:res.data
              })
         },
        fail: function(res){
          console.log("storage error:"+res.data);
          var openid=app.getOpenId();
          console.log("openid="+openid);
        }
     })
  },

  onReady:function(){
 
    // 页面渲染完成
  },
  onShow:function(){
    // 页面显示
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  },
  addCart:function(e){
    var page=e.touches[0].pageX;
    var foodId=this.data.foodId;
    var openId=this.data.openid;
    console.log("foodid="+foodId+"   openid="+openId);
    if(page>250){
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
     }else{
        wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptCart/insertOrder.do',
             data:{
                  foodId:foodId,
                  openId:openId,
                  amount:1
             },
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {
              wx.showToast({
                  title: '添加成功',
                   icon: 'success',
                  duration: 2000
                 })
            
             wx.navigateBack({
                     delta: 1
                    })

            }
       })
     }
  }
})

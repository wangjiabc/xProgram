//app.js
App({
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    // var logs = wx.getStorageSync('logs') || []
    // logs.unshift(Date.now())
    // wx.setStorageSync('logs', logs)
  },
  getUserInfo:function(cb){
    var that = this
    if(this.globalData.userInfo){
      typeof cb == "function" && cb(this.globalData.userInfo)
    }else{
      //调用登录接口
      wx.login({
        success: function () {
          wx.getUserInfo({
            success: function (res) {
              that.globalData.userInfo = res.userInfo
              typeof cb == "function" && cb(that.globalData.userInfo)
            }
          })
        }
      })
    }
  },
  getOpenId:function(){

   wx.login({
     success: function (r) {
        var code = r.code;//登录凭证
        var that=this;
        if (code) {
            //2、调用获取用户信息接口
            wx.getUserInfo({
                success: function (res) {
                    console.log("encryptedData="+res.encryptedData);
                    console.log("iv="+res.iv);
                    console.log("code="+code);
                    //3.解密用户信息 获取unionId
         
                 wx.request({
                    url: 'https://j-pongyou.rhcloud.com/xProgram/insweptUser/getUserInfo.do',
                      data:{
                          "jsCode":code,
                            "encryptedData":res.encryptedData,
                           "iv":res.iv
                           },
                        header: {
                             'Content-Type': 'application/json'
                               },
                         success: function(res) {
                          var jsonText = JSON.parse(res.data);
                          console.log("data="+res.data);
                          var userInfo=new Array();
                            for(var v in jsonText[0]){
                                   userInfo[v]=jsonText[0][v];
                                 }
                            console.log("openid="+userInfo[0].openId);  
                                wx.setStorage({
                                      key: 'openId',
                                      data:userInfo[0].openId
                                     })  
                            }
                        })
              
              },
                fail: function () {
                    console.log('获取用户信息失败')
                }
            })

        } else {
            console.log('获取用户登录态失败！' + r.errMsg)
        }
     }

   });

  },
  globalData:{
    userInfo:null
  }
})
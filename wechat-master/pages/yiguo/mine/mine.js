var app = getApp()
Page({
  data:{
     openid:"",
     userInfo: {},
     mine_list:[ 
          {
            "pic_url": "/images/icons/iocn_home_01.png",
            "title":"我的订单",
          },
          {
            "pic_url": "/images/icons/iocn_home_02.png",
            "title":"优惠券",
          },
          {
            "pic_url": "/images/icons/iocn_home_03.png",
            "title":"收货地址",
          },
          {
            "pic_url": "/images/icons/iocn_home_04.png",
            "title":"客服电话",
          },
          {
            "pic_url": "/images/icons/iocn_home_04.png",
            "title":"查看物流",
          },
          {
            "pic_url": "/images/icons/iocn_home_04.png",
            "title":"修改密码",
          }
        ],
    item: {
      signinHidden:false,
      userlocal:{
       nickName:'',
       nickPwd:''
     },
    }
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
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
      console.log("useinfo="+userInfo);
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
  modalconfirm:function(){
    wx.setStorageSync('username', this.data.item.userlocal.nickName);
    wx.setStorageSync('password', this.data.item.userlocal.nickPwd);
    this.setData({
      'item.signinHidden':true
    })
  },
  modalcancel:function(){

  },
  saveusername:function(event){
    this.setData({
      'item.userlocal.nickName': event.detail.value
    });
  },
  saveuserpwd:function(event){
    this.setData({
      'item.userlocal.nickPwd': event.detail.value
    });
  },
  onReady:function(){
    // 页面渲染完成
        console.log("openid="+this.data.openid);
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
  menueDetail:function(e){   //导航栏点击事件
     console.log("id="+e.currentTarget.dataset.id);
     var event=e.currentTarget.dataset.id;
     switch (event) {
       case 0:
           wx.navigateTo({
             url:"/pages/yiguo/order/index"
            })
         break;
       case 1:
           wx.navigateTo({
             url:"/pages/yiguo/list1/list1"
            })
         break;
        case 2:
           wx.navigateTo({
             url:"/pages/yiguo/address/index"
            })
         break; 
       case 3:
           wx.makePhoneCall({
              phoneNumber: '1340000' //仅为示例，并非真实的电话号码
           })
         break;
       case 4:
           wx.navigateTo({
             url:"/pages/yiguo/list3/list3"
            })
         break;
       case 5:
           wx.navigateTo({
             url:"/pages/yiguo/list3/list3"
            })
         break;
       default:
         break;
     }
   }
})
//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    toView:"",
    motto: 'MiHome_Store',
    userInfo: {},
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 100,
    newgoods:[
   /*   { "id":'0101001',
        "hg_pic":"http://img14.yiguoimg.com/e/ad/2016/160914/585749449477366062_260x320.jpg"
      },{"id":'0101002',
        "hg_pic":"http://img09.yiguoimg.com/e/ad/2016/161011/585749449909281099_260x320.jpg"
      },{"id":'0101003',
        "hg_pic":"http://img12.yiguoimg.com/e/ad/2016/160914/585749449480249646_260x320.jpg"
      }*/
    ],

    banner:[

        /*  {
            "pic_url": "http://img09.yiguoimg.com/e/ad/2016/160825/585749448986042649_800x400.jpg",
          },
          {
            "pic_url": "http://img11.yiguoimg.com/e/ad/2016/160927/585749449690947899_800x400.jpg",
          },
          {
            "pic_url": "http://img14.yiguoimg.com/e/ad/2016/160923/585749449636290871_800x400.jpg",
          },
          {
            "pic_url": "http://img13.yiguoimg.com/e/ad/2016/160914/585749449480315182_800x400.jpg",
          },
          {
            "pic_url": "http://img14.yiguoimg.com/e/ad/2016/161010/585749449889390922_800x400.jpg",
          }*/
        ]
      ,
      
   banner_list: [
          {
            "id":0,
            "pic_url": "/images/icons/583e83a337387.jpg",
            "title":"水果",
          },
          {
            "id":1,
            "pic_url": "/images/icons/583e83bbb5d2b.jpg",
            "title":"美酒",
          },
          {
            "id":2,
            "pic_url": "/images/icons/583e83ee7612f.jpg",
            "title":"特产",
          },
          {
            "id":3,
            "pic_url": "/images/icons/583e83f97e2d3.jpg",
            "title":"其它",
          }
        ]
      
  },
  onPullDownRefresh: function () {
    console.log('onPullDownRefresh')
  },
  scroll: function(e){
    if(this.data.toView=="top"){
      this.setData({
        toView:""
      })
    }
  },
 //详情页跳转
  lookdetail:function(e){
    var lookid=e.currentTarget.dataset;
    console.log("lookid="+lookid.id);
    console.log("id="+e.currentTarget.dataset.id);
    wx.navigateTo({
      url:"/pages/yiguo/detail/detail?id="+lookid.id
    })
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  tap:function(){
    this.setData({
      toView:"top"
    })
  },
  onLoad: function () {
    var that = this;
    app.getOpenId();
     wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptIndex/hotgoods.do',
             header: {
                 'Content-Type': 'application/json'
             },
             success: function(res) {
              //var aaa={"more_pic":"http://img10.yiguoimg.com/e/ad/2016/161008/585749449862226248_778x303.jpg"};
                 var jsonText = JSON.parse(res.data);
                 var listgoods2=new Array();
                  for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
                  }
              that.setData({
                //  hotgoods:[{"more_pic":jsonText[0]}]
                    hotgoods:listgoods2
                });
                console.log("data="+listgoods2[0].foodId);  
             }
         })
        
    //调用应用实例的方法获取全局数据
   
    wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptIndex/banner.do',
             header: {
                 'Content-Type': 'application/json'
             },
             success: function(res) {
                 var jsonText = JSON.parse(res.data);
                 var listgoods2=new Array();
                  for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
                  }
              that.setData({
                    banner:listgoods2
                });
                console.log("data="+jsonText);  
             }
         })


      wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptIndex/newgoods.do',
             header: {
                 'Content-Type': 'application/json'
             },
             success: function(res) {
                 var jsonText = JSON.parse(res.data);
                 var listgoods2=new Array();
                  for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
                  }
              that.setData({
                    newgoods:listgoods2
                });
                console.log("data="+jsonText);  
             }
         })

  },
  menueDetail:function(e){   //导航栏点击事件
     console.log("id="+e.currentTarget.dataset.id);
     var e=e.currentTarget.dataset.id;
     switch (e) {
       case 0:
           wx.navigateTo({
             url:"/pages/yiguo/list0/list0"
            })
         break;
       case 1:
           wx.navigateTo({
             url:"/pages/yiguo/list1/list1"
            })
         break;
        case 2:
           wx.navigateTo({
             url:"/pages/yiguo/list2/list2"
            })
         break; 
       case 3:
           wx.navigateTo({
             url:"/pages/yiguo/list3/list3"
            })
         break;
       default:
         break;
     }
  }
})

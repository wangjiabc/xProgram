var app = getApp()
Page({
  data:{
    current: 0,
    listgoods:[
        
    ], 
compos:[

], 
  swiper:{
      indicatorDots: false,
      autoplay: false,
      interval: 5000,
      duration: 1000
      }
  },
  onPullDownRefresh: function () {
    console.log('onPullDownRefresh')
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    var that = this;
    //ajax请求数据
     wx.request({
             url: 'https://j-pongyou.rhcloud.com/a/test/bbb.do',
            header: {
                 'Content-Type': 'application/json'
            },
            success: function(res) {
               switch1(res.data);
               console.log(res.data);
               var jsonText = JSON.parse(res.data);
               var listgoods2=new Array();
               for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
               }
               that.setData({
                   listgoods:listgoods2
               });
               console.log("listgoods2="+listgoods2);
             }
         })
    //对商品进行价格排序
    console.log(this.data.listgoods);
    switch1(this.data.listgoods);
    function switch1(odata){
        for(var i=0;i<odata.length-1;i++){
                // console.log("i:"+i+"="+odata[i].price);
                for(var j=0;j<odata.length-i-1;j++){
                       // console.log("j:"+j+"="+parseInt(odata[j].price)+"-----"+parseInt(odata[j+1].price));
                  if(parseInt(odata[j].price)<parseInt(odata[j+1].price)){
                    var temp=odata[j];
                        odata[j]=odata[j+1];
                        odata[j+1]=temp;
                  }
                }
        }
        console.log(odata);
    } 
  },
  //详情页跳转
  lookdetail:function(e){
    var lookid=e.currentTarget.dataset;
    console.log("id="+e.currentTarget.dataset.id);
   /* wx.navigateTo({
      url:"/pages/yiguo/detail/detail?id="+lookid.id
    })*/
  },
  switchSlider:function(e){
      function switch1(odata){
        for(var i=0;i<odata.length-1;i++){
                // console.log("i:"+i+"="+odata[i].price);
                for(var j=0;j<odata.length-i-1;j++){
                       // console.log("j:"+j+"="+parseInt(odata[j].price)+"-----"+parseInt(odata[j+1].price));
                  if(parseInt(odata[j].price)<parseInt(odata[j+1].price)){
                    var temp=odata[j];
                        odata[j]=odata[j+1];
                        odata[j+1]=temp;
                  }
                }
        }
        return odata;
      }
    var that = this;
    this.setData({
      current:e.target.dataset.index
    })
    var SliderId=e.target.dataset.index;
    if(SliderId==1){
       var com=switch1(this.data.listgoods);
       that.setData({
                  compos:com
                });
    }
    console.log(this.data.compos);
  },
  changeSlider:function(e){
    this.setData({
      current: e.detail.current
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
  }

})

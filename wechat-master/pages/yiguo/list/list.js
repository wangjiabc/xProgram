var app = getApp()
var GetList = function (that) {  
      that.setData({  
        hidden: false  
       });  
       wx.showToast({
        title: '加载中',
        icon: 'loading'
   })

      wx.request({  
        url: 'https://j-pongyou.rhcloud.com/xProgram/insweptList/newgoods.do',
             data:{
                 start:that.data.page
             },
        success: function (res) {  
            var jsonText = JSON.parse(res.data);
               var listgoods2=new Array();
               var i=0;
               for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
                   i++;
               }
               that.setData({
                   listgoods:listgoods2
               }); 
                wx.hideToast()
      console.log("i="+i+"   page="+that.data.page+"   res="+res.data);
        }  
    });  
   }
Page({
  data:{
    current: 0,
    page:0,
    scrollTop:0,
    listgoods:[/*{
    "id":'0101001',
    "name": "kkkkkZespri佳沛新西兰阳光金奇异果6个92-114g/个(北京)",
    "price": "111.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img14.yiguoimg.com/e/ad/2016/160914/585749449477366062_260x320.jpg"
}, {
    "id":'0101002',
    "name": "智利蓝莓2盒（约125g/盒）",
    "pic_url": "http://img09.yiguoimg.com/e/ad/2016/161011/585749449909281099_260x320.jpg",
    "price": "177.0",
    "type": "3.3kg/箱"
}, {
    "id":'0101003',
    "name": "澳大利亚脐橙12个约160g/个(北京)",
    "price": "178.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img12.yiguoimg.com/e/ad/2016/160914/585749449480249646_260x320.jpg"
}, {
    "id":'0102001',
    "name": "Zespri佳沛新西兰阳光金奇异果6个92-114g/个(北京)",
    "price": "172.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img14.yiguoimg.com/e/ad/2016/160914/585749449477366062_260x320.jpg"
}, {
    "id":'0102002',
    "name": "智利蓝莓2盒（约125g/盒）",
    "pic_url": "http://img09.yiguoimg.com/e/ad/2016/161011/585749449909281099_260x320.jpg",
    "price": "171.0",
    "type": "3.3kg/箱"
}, {
    "id":'0102003',
    "name": "澳大利亚脐橙12个约160g/个(北京)",
    "price": "174.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img12.yiguoimg.com/e/ad/2016/160914/585749449480249646_260x320.jpg"
}, {
    "id":'0103001',
    "name": "Zespri佳沛新西兰阳光金奇异果6个92-114g/个(北京)",
    "price": "177.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img14.yiguoimg.com/e/ad/2016/160914/585749449477366062_260x320.jpg"
}, {
    "id":'0103002',
    "name": "智利蓝莓2盒（约125g/盒）",
    "pic_url": "http://img09.yiguoimg.com/e/ad/2016/161011/585749449909281099_260x320.jpg",
    "price": "173.0",
    "type": "3.3kg/箱"
}, {
    "id":'0103003',
    "name": "澳大利亚脐橙12个约160g/个(北京)",
    "price": "169.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img12.yiguoimg.com/e/ad/2016/160914/585749449480249646_260x320.jpg"
}, {
    "id":'0201001',
    "name": "Zespri佳沛新西兰阳光金奇异果6个92-114g/个(北京)",
    "price": "159.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img14.yiguoimg.com/e/ad/2016/160914/585749449477366062_260x320.jpg"
}, {
    "id":'0201002',
    "name": "智利蓝莓2盒（约125g/盒）",
    "pic_url": "http://img09.yiguoimg.com/e/ad/2016/161011/585749449909281099_260x320.jpg",
    "price": "149.0",
    "type": "3.3kg/箱"
}, {
    "id":'0202001',
    "name": "澳大利亚脐橙12个约160g/个(北京)",
    "price": "139.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img12.yiguoimg.com/e/ad/2016/160914/585749449480249646_260x320.jpg"
}, {
    "id":'1203001',
    "name": "Zespri佳沛新西兰阳光金奇异果6个92-114g/个(北京)",
    "price": "159.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img14.yiguoimg.com/e/ad/2016/160914/585749449477366062_260x320.jpg"
}, {
    "id":'1401001',
    "name": "智利蓝莓2盒（约125g/盒）",
    "pic_url": "http://img09.yiguoimg.com/e/ad/2016/161011/585749449909281099_260x320.jpg",
    "price": "181.0",
    "type": "3.3kg/箱"
}, {
    "id":'1101001',
    "name": "澳大利亚脐橙12个约160g/个(北京)",
    "price": "180.0",
    "type": "3.3kg/箱",
    "pic_url": "http://img12.yiguoimg.com/e/ad/2016/160914/585749449480249646_260x320.jpg"
}*/], 
compos:[

], 
  swiper:{
      indicatorDots: false,
      autoplay: false,
      interval: 5000,
      duration: 1000
      }
  },
  onPullDownRefresh: function(){
    this.data.page=0;
      

   this.setData({  
            listgoods:[]
        });  
    GetList(this);

    wx.stopPullDownRefresh()

  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    var that = this;
    //ajax请求数据
     wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptList/newgoods.do',
             data:{
                 start:0
             },
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
    console.log("datalistgoods="+this.data.listgoods);   //原生方法要加this
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
    wx.navigateTo({
      url:"/pages/yiguo/detail/detail?id="+lookid.id
    })
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
  },
 bindDownLoad:function(){         //滚动到底部触发的事件
   this.data.page+=10;

 
   GetList(this)
   this.setData({
       scrollTop:0
   });

 }

})

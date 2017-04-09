const App = getApp()

Page({
    data: {
        openid:"",
        current: 0,  //默认选择第一列
        activeIndex: 0,
        navList: [],
        hidden: !0,
        items: [],
        listgoods:[],
        prompt: {
            hidden: !0,
            icon: '../../assets/images/iconfont-order-default.png',
            title: '您还没有相关的订单',
            text: '可以去看看有哪些想买的',
        },
    },
    onLoad() {
        this.setData({
            navList: [
                {
                    name: '全部',
                    _id: 'all',
                },
                {
                    name: '已提交',
                    _id: 'submitted',
                },
                {
                    name: '已确认',
                    _id: 'confirmed',
                },
                {
                    name: '已完成',
                    _id: 'finished',
                },
                {
                    name: '已取消',
                    _id: 'canceled',
                },
            ]
        })
    },
    onShow() {
        this.onPullDownRefresh();
        console.log("onshow");
    },
    initData() {
        this.setData({
            order: {
                items: [],
                params: {
                    page : 1,
                    limit: 10,
                    type : 'all',
                },
                paginate: {}
            }
        })
    },
    navigateTo(e) {
        console.log(e)
        App.WxService.navigateTo('/pages/order-detail/index', {
            id: e.currentTarget.dataset.id
        })
    },
    getOrderList() {
        const order = this.data.order
        const params = order.params
         var that = this;
          
        this.setData({ 
            hidden: !1
        })

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
    onPullDownRefresh() {
        this.initData()
        this.getOrderList()
    },
    onReachBottom() {
        this.lower()
    },
    lower() {
        if (!this.data.order.paginate.hasNext) return
        this.getOrderList()
    },
    onTapTag(e) {
        const type = e.currentTarget.dataset.type
        const index = e.currentTarget.dataset.index
        this.initData()
        this.setData({
            activeIndex: index,
            'order.params.type': type,
        })
        this.getOrderList()
    },
    switchSlider:function(e){
    this.setData({
      current:e.target.dataset.index
    })
    var SliderId=e.target.dataset.index;

  },
  changeSlider:function(e){
    this.setData({
      current: e.detail.current
    })
  }
})
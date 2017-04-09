const App = getApp()

Page({
    data: {
        hidden: !0,
        items:[],
        prompt: {
            hidden: !0,
            title: '还没有收货地址呢',
            text: '暂时没有相关数据',
        },
    },
    onLoad() {
    },
    onShow() {
        this.onPullDownRefresh()
    },
    initData() {
       
    },
    toAddressEdit(e) {
        console.log(e.currentTarget.dataset.id)
        wx.navigateTo({
            url:'/pages/yiguo/address-edit/index?id='+e.currentTarget.dataset.id
        })
    },
    toAddressAdd(e) {
        console.log(e)
        wx.navigateTo({
             url:'/pages/yiguo/address-add/index'
        })
    },
    setDefalutAddress(e) {
        const id = e.currentTarget.dataset.id
       console.log("id="+id);
    },
    getAddressList() {
       var that = this;
       
        this.setData({ 
            hidden: !1
        })

 wx.getStorage({
       key: 'openId',
		success: function(res) {
           console.log("getopenid session="+res.data)

        var openId=res.data;
         console.log("openid="+openId);
		 var default_address;
         wx.request({
             url: 'https://j-pongyou.rhcloud.com/xProgram/insweptAddress/getAddress.do',
             data:{
                 openId:openId
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
                   items:listgoods2
               });
               console.log("listgoods2="+listgoods2[0]);
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
        this.getAddressList()
    }
 
})
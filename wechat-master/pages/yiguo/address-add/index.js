const App = getApp()

Page({
    data: {
    	show: !0,
        form: {
			name   : null, 
			tel    : null, 
			address: '', 
			is_def : !1, 
        },
    },
    onLoad() {
    },
	switchChange (e){
		console.log('switch 发生 change 事件，携带值为', e.detail.value)
		this.setData({
			'form.is_def': e.detail.value, 
		})
	},
	bindKeyInput(e) {
		const model  = e.currentTarget.dataset.model
		const value  = e.detail.value
		const params = {}
		params[model] = value
		this.setData(params)
	},
	submitForm() {
		setTimeout(() => {
			const params = this.data.form
			console.log(params)
	 wx.getStorage({
       key: 'openId',
		success: function(res) {
           console.log("getopenid session="+res.data)

        var openId=res.data;
         console.log("openid="+openId);
		 var default_address;
		 if(params.is_def==true){         //mysql不能放入boolean
			 default_address=1;
		 }else{
			 default_address=0;
		 }
		     wx.request({
				 url:'https://j-pongyou.rhcloud.com/xProgram/insweptAddress/insertInfoforUser.do',
				 data:{
				   openId:openId,
				   phone:params.tel,
				   name:params.name,
				   address:params.address,
				   default_address:default_address	 
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
							  setTimeout(function(){
                                     wx.navigateTo({
										 url:'/pages/yiguo/address/index'
									 })
                               },2000)

				    }
			 })
			  },
        fail: function(res){
          console.log("storage error:"+res.data);
          var openid=app.getOpenId();
          console.log("openid="+openid);
        }
     })
		}, 300)
		
	},
	showToast(message) {
		App.WxService.showToast({
			title   : message, 
			icon    : 'success', 
			duration: 1500, 
		})
		.then(() => App.WxService.navigateBack())
	},
	chooseLocation() {
		App.WxService.chooseLocation()
	    .then(data => {
	        console.log(data)
	        this.setData({
	        	'form.address': data.address
	        })
	    })
	},
})
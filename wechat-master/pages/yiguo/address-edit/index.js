const App = getApp()

Page({
    data: {
    	show: !0,
        form: [],
		items:[]
    },
    onLoad(option) {
    	this.setData({
    		id: option.id
    	})
		console.log("id="+option.id);
    },
    onShow() {
    	this.renderForm(this.data.id)
    },
    renderForm(id) {
		var that=this;
		var data=this.data;

	 wx.getStorage({
       key: 'openId',
		success: function(res) {
           console.log("getopenid session="+res.data)

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
               var jsonText = JSON.parse(res.data);
               var listgoods2=new Array();
               for(var v in jsonText[0]){
                   listgoods2[v]=jsonText[0][v];
               }
               
			   for(var i in listgoods2){
				   if(listgoods2[i].id==id){
                       that.setData({
                            items:listgoods2[i],
							'form.name':listgoods2[i].name,
							'form.tel':listgoods2[i].phone,
							'form.address':listgoods2[i].address,
							'form.is_def':listgoods2[i].defaultAddress,
                        });
				   }
			   }
               console.log("items="+data.items.phone);   //自定义方法不加this

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
    radioChange(e) {
		console.log('radio发生change事件，携带value值为：', e.detail.value)
		const value = e.detail.value
		const radio = this.data.radio
		radio.forEach(n => n.checked = n.value === value)
		this.setData({
			radio: radio, 
			'form.gender': value, 
		})
	},
	switchChange(e) {
		console.log('switch 发生 change 事件，携带值为', e.detail.value)
		this.setData({
			'form.is_def': e.detail.value, 
		})
	},
	bindKeyInput(e) {
		const model  = e.currentTarget.dataset.model
		const value  = e.detail.value
		const params = {}
		console.log("model="+model);
		params[model] = value
		console.log("value="+value);
		if(model=='name'){
			this.setData({
			'form.name': e.detail.value, 
		  })
		}
		if(model=='tel'){
			this.setData({
			'form.tel': e.detail.value, 
		  })
		}
		if(model=='address'){
			this.setData({
			'form.address': e.detail.value, 
		  })
		}
	},
	submitForm() {
		setTimeout(() => {
			const id = this.data.id
			const params = this.data.form
			console.log("params="+params.name+" "+params.tel+" "+params.address+" "+params.is_def)
	
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
				 url:'https://j-pongyou.rhcloud.com/xProgram/insweptAddress/upUserInfo.do',
				 data:{
				   id:id,
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
                                     wx.navigateBack({
                                           delta: 1
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
	delete() {
		const id = this.data.id
		wx.request({
				 url:'https://j-pongyou.rhcloud.com/xProgram/insweptAddress/delAddress.do',
				 data:{
				   id:id,
				   openId:1
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
                                     wx.navigateBack({
                                           delta: 1
                                      })
                               },2000)

				    }
			 })
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
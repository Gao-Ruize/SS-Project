// pages/sentMsgDetailPage/sentMsgDetailPage.js
Page({
    /**
     * 页面的初始数据
     */
    data: {
        //信息id
        msgId: "",
        active: 1,
        readStudentInfo: [{name: "zhangsan", ID: "1"}, {name: "lisi", ID: "2"}],
        unreadStudentInfo: [{name: "wangwu", ID: "3"}, {name: "zhaoliu", ID: "4"}],
        searchValue: "",
        showPopUp: false,
        result: [],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let tmpMsgId = options.msgId;
        this.setData({
            msgId: tmpMsgId,
        });
        //通过msgId获得对应的学生
        //将已读学生和未读学生分开处理
    },
    onChange(event) {
        // event.detail 的值为当前选中项的索引
        this.setData({active: event.detail});
    },
    searchChange(event) {
        this.setData({
            searchValue: event.detail,
        });
        let checkdata = this.data.searchValue;
        console.log(checkdata);
    },
    showSendPop() {
        this.setData({showPopUp: true});
    },
    onClose() {
        this.setData({showPopUp: false})
    },
    onSearch() {
        console.log("search!");
    },
    mulChosChange(event) {
        this.setData({result: event.detail});
    },
    changeChosBar(event) {
        this.setData({result: event.detail})
    },
    sendMsg() {
        let check = this.data.result;
        console.log(check);
        wx.showToast({
            title: '发送成功！',
            icon: "success",
            duration: 1500
        });
        this.onClose();
        this.setData({result: []});
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})
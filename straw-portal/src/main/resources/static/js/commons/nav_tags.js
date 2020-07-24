let navTagsApp = new Vue({
    el: '#navTagsApp',
    data: {
        tags: [
            {id:1 , name: '第一阶段'},
            {id:2 , name: '第二阶段'},
            {id:3 , name: '第三阶段'},
            {id:4 , name: '第四阶段'}
        ]
    },
    methods: {
        loadTags: function () {
            //alert("准备加载问题标签列表....");
            $.ajax({
                url: '/api/v1/tags',
                type: 'get',
                dataType: 'json',
                success: function (json) {
                    // navTagsApp.tags = json.data;
                    navTagsApp.tags = json.data;
                }
            });
        }
    },
    created: function () {
        this.loadTags();
    }
});
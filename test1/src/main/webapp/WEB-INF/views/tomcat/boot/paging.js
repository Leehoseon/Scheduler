function makePage(param) {

    // 자바는 파라미터의 순서로 받아야 하지만 js는 파라미터를 받고 있으면 사용하고 없으면 사용하지 않는다.
    //파람의 유알엘을 받거나 없으면 윈도위 유알엘 받음
    //var url = param.url||window.location;

    var tag = param.tag||"<a>$$page</a>";

    var pageSize = param.pageSize || 10;

    var liCount = param.liCount || 10;

    //var callback = param.callback;

    var total = param.total || 0;

    var current = param.current || 1;

    var tempEnd = makeTempEnd();
    var startNum = makeStart();
    var realEnd = makeRealEnd();

    var prev = startNum -1;
    
    var next = tempEnd * pageSize < total ? tempEnd +1: 0;

//    console.log("tempEnd:" + tempEnd);
//    console.log("startNum:" + startNum);
//    console.log("realEnd:" + realEnd);
//    console.log("prev:" + prev);
//    console.log("next:" + next);
//    console.log((makeTags()));
    
    //코드 제너레이션
    function makeTags() {
        var str = "";

        if(prev != 0){
            str += "<a id='"+ prev +"'> << </a>";
        }

        for(var i = startNum; i <= realEnd; i++){

            str += "<a id='"+ i +"'>"+ i +"</a>";
        }
        if(next != 0){
            str += "<a id='"+ next +"'> >> </a>";
        }

        return str;
    }

    //tempEnd
    function makeTempEnd() {
        return  Math.ceil(current/liCount) * liCount;
    }

    //start
    function makeStart() {
        return tempEnd - (liCount -1);
    }
    //realEnd
    function makeRealEnd() {
        var lastPage = Math.ceil(total/pageSize);

        return tempEnd > lastPage? lastPage:tempEnd;

    }
    return makeTags();
}
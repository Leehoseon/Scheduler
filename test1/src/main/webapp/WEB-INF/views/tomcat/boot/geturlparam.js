/*var bie = new RegExp(/MSIE/).test(navigator.userAgent);
var bcr = new RegExp(/Chrome/).test(navigator.userAgent);*/


function getQuerystring(param) {
	var _tempUrl = window.location.search.substring(1);
	var target;
	/*console.log(_tempUrl);*/
	var _tempArray = new Array ();
	_tempArray.push(_tempUrl.split('&'));
	
	var temp = "" + _tempUrl.split('&') +"";
	var results = temp.match('/,/'); 
	
	for (var i = 0; i < _tempArray[0].length; i++) {
		var test = temp.split(',');
	}	
	
	for (var i = 0; i < test.length; i++) {
		var _keyValuePair =""+ test[i].split('=') +"";
		var start = _keyValuePair.lastIndexOf(',') +1;
		var value = _keyValuePair.substring(start,_keyValuePair.length);
		var key = _keyValuePair.substring(0,start-1);
		/*console.log(_keyValuePair);
		console.log(value);
		console.log(key);*/
		
		if (key == param) {
			return value;
		}
	}
};

var page = getQuerystring("page");
var tno = getQuerystring("tno"); 


/*var page = getQuerystring("page");
var tno = getQuerystring("tno");*/
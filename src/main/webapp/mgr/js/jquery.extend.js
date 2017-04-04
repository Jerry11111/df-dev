

$.extend({
	
	/**
	 * 
 	 * @param {Object} options{
 	 * 		tag: required
 	 *      ....
 	 * }
	 */
	create: function(options){
		var tag = options.tag;
		var tagHtml = '<' + tag + '/>';
		var $tag = $(tagHtml);
		if( options.css ){
			$tag.css(options.css);
		}
		if( options.text ){
			$tag.text(options.text);
		}
		delete options.tag;
		delete options.css;
		delete options.text;
		$tag.attr(options);
		return $tag;
	}
});




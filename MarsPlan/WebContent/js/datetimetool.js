

/**
 * 时间比较大小 例如：2015-01-07 11：20与2015-01-08 12：20 返回1开始时间大于结束时间 返回0 相等
 * 返回-1开始时间小于结束时间
 * 
 * @return {Number}
 */
function comptime(beginTime, endTime) {
	var beginTimes = beginTime.substring(0, 10).split('-');
	var endTimes = endTime.substring(0, 10).split('-');

	/** 如果比较秒，截取到19 */
	beginTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0] + ' '
			+ beginTime.substring(10, 16);
	endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' '
			+ endTime.substring(10, 16);

	var a = (Date.parse(endTime) - Date.parse(beginTime)) / 3600 / 1000;
	if (a < 0) {
		/** 开始时间大于结束时间 */
		return 1;
	} else if (a > 0) {
		/** 开始时间小于结束时间 */
		return -1;
	} else if (a == 0) {
		/** 相等 */
		return 0;
	} else {
		return 'exception'
	}
}


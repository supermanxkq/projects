<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>音乐盒</title>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="音乐盒">
	<meta http-equiv="description" content="音乐盒">
	
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<link rel="Shortcut Icon"href="images/logo.png" type="image/x-icon" />
  </head>
  
  <body>
    
    <div class="default-main">
    	<div class="main-wrapper">
    		<!-- 左侧 -->
    		<div class="mb-layout-bd column1">
    			<div class="leftbar-bottom-bg">
    				<div class="leftbar-outer">
    					<div class="leftbar">
    						<div class="list list-temp playing">
    							<a>歌曲列表</a>
    						</div>
<%--    						<div class="list list-temp">--%>
<%--    							<a>歌手</a>--%>
<%--    						</div>--%>
    					</div>
    				</div>
    			</div>
    		</div>
    		<!-- 右侧歌词区域 -->
    		<div class="mb-layout-bd column3">
    			<div class="lrc-wrapper ui-lrc ui-lrc-vertical lrc">
    				<ul style="position: relative; top: 0px;"></ul>
    			</div>
    		</div>
    		<!-- 歌曲列表区域 -->
    		<div class="mb-layout-bd column2">
    			<input type="hidden" id="singerOrList" value="list"/>
    			<!-- 按歌曲列表显示 -->
    			<div class="tab-main ui-tabs ui-widget ui-widget-content ui-corner-all">
    				<div class="tab-content cfix">
    					<div class="tab-page">
    						<!-- 列表头部 -->
    						<div class="ui-reelList-header ui-state-default">
    							<div class="ui-reelList-header-column c0">
    								<div class="ui-reelList-checkbox"></div>
    								<span class="sort-item">歌曲</span>
    							</div>
    							<div class="ui-reelList-header-column c1">
    								<span class="sort-item">歌手</span>
    							</div>
    							<div class="ui-reelList-header-column c2">
    								<span class="sort-item">专辑</span>
    							</div>
    							<div class="ui-reelList-header-column c3">
    								<span class="sort-item"></span>
    							</div>
    						</div>
    						<!-- 歌曲列表 -->
    						<div class="ui-reelList-viewport">
    							<div class="ui-reelList-canvas">
    							</div>
    							<div class="similar-wrapper" style="top: 31px;"></div>
    						</div>
    					</div>
    						
    				</div>
    			</div>
    			<!-- 按歌手显示 -->
    			<div class="page-local-artist" style="display: none;">
    				<div class="artist-view-tab">
                        <span class="title">歌手</span>
                        <span class="total">共18名</span>
                    </div>
                    <div class="items-container">
                    	<div class="view-grid-canvas">
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="陈奕迅" class="cover">
                    					<img src="images/singer/chenyixun.jpg" width="130px" height="130px" alt="陈奕迅"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="陈奕迅">
		                                    <span>陈奕迅</span>
		                                    <span class="artist-num">(5首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="陶喆" class="cover">
                    					<img src="images/singer/taozhe.jpg" width="130px" height="130px" alt="陶喆"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="陶喆">
		                                    <span>陶喆</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="王杰" class="cover">
                    					<img src="images/singer/wangjie.jpg" width="130px" height="130px" alt="王杰"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="王杰">
		                                    <span>王杰</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="张宇" class="cover">
                    					<img src="images/singer/zhangyu.jpg" width="130px" height="130px" alt="张宇"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="张宇">
		                                    <span>张宇</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="杨坤" class="cover">
                    					<img src="images/singer/yangkun.jpg" width="130px" height="130px" alt="杨坤"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="杨坤">
		                                    <span>杨坤</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="林宥嘉" class="cover">
                    					<img src="images/singer/linyoujia.jpg" width="130px" height="130px" alt="林宥嘉"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="林宥嘉">
		                                    <span>林宥嘉</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="陈势安" class="cover">
                    					<img src="images/singer/chenshian.jpg" width="130px" height="130px" alt="陈势安"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="陈势安">
		                                    <span>陈势安</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="樊凡" class="cover">
                    					<img src="images/singer/fanfan.jpg" width="130px" height="130px" alt="樊凡"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="樊凡">
		                                    <span>樊凡</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="李克勤" class="cover">
                    					<img src="images/singer/likeqin.jpg" width="130px" height="130px" alt="李克勤"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="李克勤">
		                                    <span>李克勤</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="GALA乐队" class="cover">
                    					<img src="images/singer/gala.jpeg" width="130px" height="130px" alt="GALA乐队"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="GALA乐队">
		                                    <span>GALA乐队</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="陈学冬" class="cover">
                    					<img src="images/singer/chenxuedong.jpeg" width="130px" height="130px" alt="陈学冬"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="陈学冬">
		                                    <span>陈学冬</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="张阳阳" class="cover">
                    					<img src="images/singer/zhangyangyang.png" width="130px" height="130px" alt="张阳阳"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="张阳阳">
		                                    <span>张阳阳</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="郁可唯" class="cover">
                    					<img src="images/singer/yukewei.jpg" width="130px" height="130px" alt="郁可唯"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="郁可唯">
		                                    <span>郁可唯</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="小虫" class="cover">
                    					<img src="images/singer/xiaochong.jpg" width="130px" height="130px" alt="小虫"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="小虫">
		                                    <span>小虫</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="许嵩" class="cover">
                    					<img src="images/singer/xusong.jpg" width="130px" height="130px" alt="许嵩"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="许嵩">
		                                    <span>许嵩</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="萧敬腾" class="cover">
                    					<img src="images/singer/xiaojingteng.jpg" width="130px" height="130px" alt="萧敬腾"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="萧敬腾">
		                                    <span>萧敬腾</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="周杰伦" class="cover">
                    					<img src="images/singer/zhoujielun.jpg" width="130px" height="130px" alt="周杰伦"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="周杰伦">
		                                    <span>周杰伦</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-item">
                    			<div class="artist-cover cover-img">
                    				<a title="筷子兄弟" class="cover">
                    					<img src="images/singer/kuaizixiongdi.jpg" width="130px" height="130px" alt="筷子兄弟"/>
                    					<span title="播放" class="artist-play play-icon"></span>
                    				</a>
                    				<div class="artist-status"></div>
	                    			<div class="artist-name">
	                   					<a title="筷子兄弟">
		                                    <span>筷子兄弟</span>
		                                    <span class="artist-num">(1首)</span>
		                                </a>
	                   				</div>
                    			</div>
                    			
                    		</div>
                    		
                    		<div class="view-expand view-item  auto-height">
                    			<input type="hidden" id="singerIndex" value="0"/>
                    			<div class="expand-top">
                    				<div class="view-expand-corner" style="left: 60px;"></div>
                    			</div>
                    			<div class="expand-content">
                    				<a class="view-expand-close" href="javascript:;"></a>
                    				<div class="songinfo">
                    					<div class="song-title">
                    						<a href="javascript:;" title="陈奕迅" class="artist-name-b">陈奕迅</a>
                    					</div>
                    				</div>
                    				<div class="songlist-wrap">
                    					<ul class="songlist">
                    					</ul>
                    				</div>
                    			</div>
                    		</div>
                    	</div>
                    </div>
    			</div>
    		</div>
    	</div>
    </div>
    
    <!-- 顶部区域 -->
    <div class="mb-layout-hd cmb-comm">
    	<div class="top-title">
    		<!--<img src="images/music-top.png" width="300px" height="43px" alt="kingx music box"/>    -->
    		<marquee>半仙儿的音乐盒(´･ω･`)1.0版本
    			音乐盒模仿百度音乐盒制作，采用Ajax技术读取后台数据，暂时还没有歌词同步显示，待开发
    			第二个版本的时候，加入歌词信息，另外歌曲没有快进的功能，音量只能进行点击进行调节。
    			还有点击了音乐列表中的歌曲名称时候，应该有个音乐正在播放的特效，给用户良好的体验。歌词的上面要
    			加上歌手的照片信息，这样会更加的贴切。。。。还要添加用户喜欢的歌曲收藏，用户自定义上传歌曲。
    		</marquee>
    	</div>
    </div>
    
    <audio id="myAudio" src="music/music-00001.mp3" controls="true" style="display: none;"></audio>  
     
    <!-- 显示音乐播放器的div -->
	<div class="mb-layout-ft minwidth">
		<div class="panel">
			<div class="panel-inner">
				<!-- 控制播放暂停上一首下一首 -->
				<div class="left-panel">
					<input type="hidden" id="curIndex" value="0">
					<ul class="play-btn">
						<li class="prev">
							<a class="wg-button" title="上一首"></a>
						</li>
						<li class="play" title="播放[空格键]">
							<a class="stop-a"></a>
						</li>
						<li class="next">
							<a class="wg-button" title="下一首"></a>
						</li>
					</ul>
				</div>
				<!-- 控制播放模式，调节声音 -->
				<div class="right-panel">
					<ul class="playmod">
						<li class="random-mod">
							<a href="javascript:;" title="随机播放" class="wg-button"></a>
						</li>
						<li class="single-mod">
							<a href="javascript:;" title="单曲循环" class="wg-button"></a>
						</li>
						<li class="list-mod">
							<a href="javascript:;" title="列表循环" class="wg-button currentMod"></a>
						</li>
					</ul>
					<div class="volume">
						<a class="mute wg-button" title="静音"></a>
						<div class="vol-slider-wrapper">
							<div class="vol-slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
								<div class="ui-slider-range ui-widget-header ui-slider-range-min ui-slider-range-volume" style="width: 100%"></div>
								<a class="ui-slider-handle ui-state-default ui-corner-all ui-volume" style="left: 100%"></a>
							</div>
						</div>
					</div>
				</div>
				<!-- 进度条和时间展示 -->
				<div class="main-panel">
					<div class="title-wrapper">
						<div class="title-inner">
							<div class="title">
								<a class="songname">不要说话</a>
								<span class="split">-</span>
								<a class="artist">陈奕迅</a>
							</div>
						</div>
					</div>
					<div class="pane">
						<div class="time">
							<span class="curTime">00:00</span>
							<span>/</span>
							<span class="totalTime">04:45</span>
						</div>
						<div class="progress-wrapper">
							<div id="progressSlider" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
								<div class="ui-slider-left"></div>
								<div class="ui-slider-right"></div>
								<div class="ui-slider-range ui-widget-header ui-slider-range-min ui-slider-range-schedule">
									<span class="ui-slider-range-inner"></span>
								</div>
								<div class="ui-slider-progressbar ui-widget-header" style="width: 100%;"></div>
								<a class="ui-slider-handle ui-state-default ui-corner-all ui-schedule">
									<span></span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>

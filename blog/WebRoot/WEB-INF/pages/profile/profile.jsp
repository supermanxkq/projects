<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
	<!--<![endif]-->
	<!-- BEGIN HEAD -->
	<head>
		<meta charset="utf-8" />
		<title>徐半仙儿-个人信息</title>
		<base href="<%=basePath%>">
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<link rel="Shortcut Icon"href="images/logo.png" type="image/x-icon" />
		<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-responsive.min.css"
			rel="stylesheet" />
		<link href="assets/bootstrap/css/bootstrap-fileupload.css"
			rel="stylesheet" />
		<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href="css/style.css" rel="stylesheet" />
		<link href="css/style_responsive.css" rel="stylesheet" />
		<link href="css/style_gray.css" rel="stylesheet" id="style_color" />

		<link href="assets/fancybox/source/jquery.fancybox.css"
			rel="stylesheet" />
		<link rel="stylesheet" type="text/css"
			href="assets/uniform/css/uniform.default.css" />
			<script type="text/javascript">
			//退出系统登录
			function quit() {
				if(confirm("确定要退出系统吗？")){
					parent.window.location.href="<%=basePath%>system/login!logout";
			}
		}
			</script>
	</head>
	<!-- END HEAD -->
	<!-- BEGIN BODY -->
	<body class="fixed-top">
	 <!-- Begin header -->
  <jsp:include page="/WEB-INF/pages/common/top2.jsp"></jsp:include>
  <!-- end header -->
		<!-- BEGIN CONTAINER -->
		<div id="container" class="row-fluid">
			<!-- BEGIN SIDEBAR -->
			<div id="sidebar" class="nav-collapse collapse">
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				<div class="sidebar-toggler hidden-phone"></div>
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->

				<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
				<div class="navbar-inverse">
					<form class="navbar-search visible-phone">
						<input type="text" class="search-query" placeholder="Search" />
					</form>
				</div>
				<!-- END RESPONSIVE QUICK SEARCH FORM -->
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="sidebar-menu">
					<!-- 链接开始 -->
					<li class="has-sub">
					<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-user"></i> </span> 好456<span class="arrow"></span> </a>
						<ul class="sub">
							<li>
								<a class="" href="link/link!list?columnCode=2">好456</a>
							</li>
							<s:if test="#session.user_session!=null">
							<li>
								<a class="" href="link/link!toEditPage">好456管理</a>
							</li>
							</s:if>
						</ul>
					</li>
					<!-- 查看个人信息开始-->
					<li class="has-sub active">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-user"></i> </span> 个人信息<span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="profile/profile!list">查看个人信息</a>
							</li>
						</ul>
					</li>
					<!-- 查看个人信息结束-->
					<!-- 资源分享开始 -->
					<li class="has-sub">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-folder-open"></i> </span> 资源 <span class="arrow"></span> </a>
						<ul class="sub">
							<li>
								<a class="" href="article/article!list?columnCode=1">资源分享</a>
							</li>
							<s:if test="#session.user_session!=null">
								<li>
									<a class="" href="blogtype/blogtype!list">资源分类</a>
								</li>
							</s:if>
						</ul>
					</li>
					<!-- 资源分享结束 -->
					<!-- 留言板开始 -->
					<li class="has-sub">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-comment"></i> </span> 留言板 <span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="leavemessage/leavemessage!list?columnCode=3">留言板</a>
							</li>
						</ul>
					</li>
					<!-- 留言板结束 -->
					<!-- 音乐盒开始 -->
					<li class="has-sub">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-music"></i> </span> 音乐盒 <span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="musicbox/musicbox!list" target="_blank">音乐盒</a>
							</li>
						</ul>
					</li>
					<!-- 音乐盒结束 -->
					<!-- 社区论坛开始 -->
					<li class="has-sub">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-comments-alt"></i> </span>社区论坛<span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="#" target="_blank">社区论坛</a>
							</li>
						</ul>
					</li>
					<!-- 社区论坛结束-->
					<!-- 友情捐助开始-->
					<li class="has-sub">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-heart-empty"></i> </span> 友情捐助<span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="#" target="_blank">友情捐助</a>
							</li>
						</ul>
					</li>
					<!-- 友情捐助结束 -->
					<!-- 时间轴开始 -->
					<li class="has-sub">
						<a href="javascript:;" class=""> <span class="icon-box"><i
								class="icon-filter"></i> </span> 时间轴<span class="arrow"></span> </a>
						<ul class="sub">
							<li class="active">
								<a class="" href="#" target="_blank">时间轴</a>
							</li>
						</ul>
					</li>
					<!-- 时间轴结束 -->
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
			<!-- END SIDEBAR -->
			<!-- BEGIN PAGE -->
			<div id="main-content">
				<!-- BEGIN PAGE CONTAINER-->
				<div class="container-fluid">
					<!-- BEGIN PAGE HEADER-->
					<div class="row-fluid">
						<div class="span12">
							<!-- BEGIN THEME CUSTOMIZER-->
							<div id="theme-change" class="hidden-phone">
								<i class="icon-cogs"></i>
								<span class="settings"> <span class="text">Theme:</span>
									<span class="colors"> <span class="color-default"
										data-style="default"></span> <span class="color-gray"
										data-style="gray"></span> <span class="color-purple"
										data-style="purple"></span> <span class="color-navy-blue"
										data-style="navy-blue"></span> </span> </span>
							</div>
							<!-- END THEME CUSTOMIZER-->
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								个人简介
								<small>半仙儿的个人简介</small>
							</h3>
							<ul class="breadcrumb">
								<li>
									<a href="#"><i class="icon-home"></i>
									</a><span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="#">个人信息</a>
									<span class="divider">&nbsp;</span>
								</li>
								<li>
									<a href="javascript:void(0);">查看个人信息</a><span class="divider-last">&nbsp;</span>
								</li>
							</ul>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">
							<div class="widget">
								<div class="widget-title">
									<h4>
										<i class="icon-user"></i>个人简介
									</h4>
									<span class="tools"> <a href="javascript:;"
										class="icon-chevron-down"></a> <a href="javascript:;"
										class="icon-remove"></a> </span>
								</div>
								<div class="widget-body">
									<div class="span3">
										<div class="text-center profile-pic">
											<img src="img/header.jpg" alt="">
										</div>
										<%--                                <ul class="nav nav-tabs nav-stacked">--%>
										<%--                                    <li><a href="javascript:void(0)"><i class="icon-coffee"></i> Portfolio</a></li>--%>
										<%--                                    <li><a href="javascript:void(0)"><i class="icon-paper-clip"></i> Projects</a></li>--%>
										<%--                                    <li><a href="javascript:void(0)"><i class="icon-picture"></i> Gallery</a></li>--%>
										<%--                                </ul>--%>
										<%--                                <ul class="nav nav-tabs nav- ">--%>
										<%--                                    <li><a href="javascript:void(0)"><i class="icon-facebook"></i> Facebook</a></li>--%>
										<%--                                    <li><a href="javascript:void(0)"><i class="icon-twitter"></i> Twitter</a></li>--%>
										<%--                                    <li><a href="javascript:void(0)"><i class="icon-linkedin"></i> LinkedIn</a></li>--%>
										<%--                                    <li><a href="javascript:void(0)"><i class="icon-pinterest"></i> Pinterest</a></li>--%>
										<%--                                    <li><a href="javascript:void(0)"><i class="icon-github"></i> Github</a></li>--%>
										<%--                                </ul>--%>
									</div>
									<div class="span6">
										<h4>
											<s:property value="profileDTO.userName" />
											<br />
											<small><s:property value="profileDTO.occupation" />
											</small>
										</h4>
										<table class="table table-borderless">
											<tbody>
												<tr>
													<td class="span2">
														姓名:
													</td>
													<td>
														<s:property value="profileDTO.userName" />
													</td>
												</tr>
												<tr>
													<td class="span2">
														国家 :
													</td>
													<td>
														<s:property value="profileDTO.country" />
													</td>
												</tr>
												<tr>
													<td class="span2">
														生日 :
													</td>
													<td>
														<s:date name="profileDTO.birthday" format="yyyy-MM-dd" />
													</td>
												</tr>
												<tr>
													<td class="span2">
														工作 :
													</td>
													<td>
														<s:property value="profileDTO.job" />
													</td>
												</tr>
												<tr>
													<td class="span2">
														邮箱 :
													</td>
													<td>
														<s:property value="profileDTO.email" />
													</td>
												</tr>
												<tr>
													<td class="span2">
														QQ :
													</td>
													<td>
														<s:property value="profileDTO.QQ" />
													</td>
												</tr>
												<tr>
													<td class="span2">
														个人博客 :
													</td>
													<td>
														<s:property value="profileDTO.blogAddress" />
													</td>
												</tr>
												<tr>
													<td class="span2">
														新浪微博 :
													</td>
													<td>
														<s:property value="profileDTO.microBlog" />
													</td>
												</tr>
											</tbody>
										</table>
										<h4>
											个人介绍
										</h4>

										<p class="push">
											讲个催人尿下的故事吧，文笔不好，能了解我即可。<br/> 顺序【 休学->培训->复学->第一份工作->第二份工作】<br/> <br/> 
											【休学】2012年大二的我感觉自己不适合在应用电子专业学习，感觉什么都学不到，没有什么实际的用处。就决定退学。但是，命运到了转折点，有次哥哥跟我说，你学编程吧，让我先休学，然后去某培训机构。
											然后我就编了个老妈生病需要照顾的谎言，好不容易的办理了xxx手续，休学去培训。从此开始了我半年多的培训，其实我是喜欢编程的，身为屌丝的我每每会因为编程那种feel而有成就感，感觉很装逼的样子。休学很困难，这里就不骂学校领导了，
											还是靠我自己的小聪明，一路给领导演戏，眼泪都快出来了，演的我自己都感动了，好不容易才ok...<br/> <br/> 
											【培训】性价比不高，一周上3天课，休息2天，刚开始老师讲的还好，还细，还给录个视频。但是好像没怎么关注过我，还记得问过他一个问题，还说等会儿来，结果没来。卧槽！肮脏的住宿环境，要600/月，6人，空间小，没窗户，冬天都要窒息，蟑螂满地跑，一群烟民，晚上不睡觉，玩联盟，当时特别讨厌这个游戏，宿舍哥们儿天天晚上玩儿，吼叫不断。
											学费高，住宿贵，家里那年受灾，父母外出打工，让我很内疚，老妈因此高血压。所以有时候省钱，吃不饱很正常。后来那个老师，卧槽，不说了，项目经理，很装逼，只讲思路，不录视频，让你写代码，完全按照公司上班领导安排任务一样。很多人受不了，当时还有个人和他大吵一顿，说他讲的不好。我记得有一句话这样说：我叫你老师，是尊敬你！撕逼的很厉害。
											我吓得不敢说话，后来吧，我估计受这个老师影响了，问他具体的问题，我擦，必须被骂。问题都是自己解决，自己那时候还学了php，用zendframework框架做过几个例子，天天看着韩顺平的视频，做着博客，有空首都图书馆。一个人，很孤单。。。那时候，喜欢抽烟。。<br/> 
											<br/> 【复学】我得转到12届电子班，但是我恨透了这个专业，坚持要转专业，我很忐忑当时，又打算着退学。索性命好，12电子班都北京生，不要外地，我就可以转专业，我乐坏了。提前一周就去找领导，跟领导办事儿就特么爱“踢足球”，签个字问问这个同意不？那个同意不？弄的我是天天跑。还记得那时刚来老师都特么快不认识我了。
											终于可以转了，领导嫌我从电子系转到计算机系学分什么的不好弄，意思就是别给他们添麻烦。不让我进软件班，让我从物联网，网络班选一个，我当时就很生气，和俩个系主任理论了一下午，嗯，是一下午。
											凭我破嘴，最后领导同意了。应该是没有办法勉强同意了。说软件班人多，没有地方。一大堆理由，在我看来都是为了不给他们添麻烦找借口。
											还记得，奖学金，国家励志，都与我擦身而过，我没有选修什么的业余课程分数，虽然各科都是班里第一，但是没有机会。我只能感觉自己对不起父母。而领导给出的话是，“有收获，就有失去”。我只想说，卧槽尼玛！
											<br/> <br/> 【第一份工作】和软件班里老师关系不错，老师推荐我工作，我去应聘，凭借自己当时做的一个项目，老板看上了，从此就过上了，一边上班，一边上课的生活。7个月，我付出了很多，加班有时候晚上11点回来。那段时光，很励志！工资只有1000/月。因为喜欢，所以坚持下来了。
											还记得第一次领工资，俩个月的工资，领了500块钱。每天都是下午3点跑去公司上班，按小时算，一小时5.2元，我笑了。用宋小宝的话说：我特么怎么也是个技术工种，你是在侮辱我！公司学习让我不仅学到了技术，更重要的是如何解决问题，如何在调了30分钟的bug后，依然淡定细心检查不急躁，这一点很重要。我当时有一次问题解决不出来，热泪盈眶，几乎绝望。还记得那个下雨天电闪雷鸣中的小猫，站在二楼的窗前，前进不得，后退不能，它会直接从2楼跳下来吗？虽然摔不死，也很疼吧，我当时也和它一样绝望，后来被2楼的人抱进去了。我当时也是同事帮我解决的Bug。没死！！许多女同事解决不出来Bug，都常偷偷躲到卫生间里哭。关系还不错，跟我讲过，我也亲眼见她们哭过，没办法，绩效考核的任务必须完成。
											小公司，概要，详细设计，GUI做效果，开发模块儿，写测试用例，修改测试人员返回的bug,每周例会汇报工作，很匆忙充实的工作，好像什么事情都得做。晚上太晚就公司睡，和哥们儿玩联盟。吃点儿饭，喝点儿酒。
											当时打算做音乐方面的开发工作，就辞职了，我说我兴趣不到位。说要给我涨工资，我没有答应，辞职了。有点后悔？应该吧，自己累了吧，总是有点儿任性。曾经有3-4次辞职的想法。
											<br/> <br/> 【第二份工作】也是凭借自己开发的上线小网站吧，人家感觉我还能做出点东西，就要了，也就是我现在的工作。还可以吧，工资是之前的好几倍。继续努力吧，向大神们靠近。我自己感觉自己挺笨的，数学不好，英语还得百度，逻辑思维也凑合，想个简单的思路还得画半天流程图。
											感觉自己就是坚持吧。。。<br/> <br/>  Java小学生，多多指点。我一直在奋斗……希望和大家交流能学到更多的知识，共同进步！共筑北漂梦！又特么扯犊子了！哈哈。
											网站做的不好，就是想达到一个互相分享资源的目的，有兴趣的可以加个群，分享一下学习资源！
										</p>
										<h4>
											技能
										</h4>

										<table class="table table-borderless">
											<tbody>
												<tr>
													<td class="span1">
														<span class="label label-inverse">HTML</span>
													</td>
													<td>
														<div class="progress progress-success progress-striped">
															<div style="width: 90%" class="bar"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td class="span1">
														<span class="label label-inverse">CSS</span>
													</td>
													<td>
														<div class="progress progress-warning progress-striped">
															<div style="width: 85%" class="bar"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td class="span1">
														<span class="label label-inverse">Javascript</span>
													</td>
													<td>
														<div class="progress progress-success progress-striped">
															<div style="width: 60%" class="bar"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td class="span1">
														<span class="label label-inverse">PHP</span>
													</td>
													<td>
														<div class="progress progress-success progress-striped">
															<div style="width: 40%" class="bar"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td class="span1">
														<span class="label label-inverse">Photoshop</span>
													</td>
													<td>
														<div class="progress progress-warning progress-striped">
															<div style="width: 80%" class="bar"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td class="span1">
														<span class="label label-inverse">Java</span>
													</td>
													<td>
														<div class="progress progress-danger progress-striped">
															<div style="width: 70%" class="bar"></div>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
										<h4>
											地址
										</h4>
										<div class="well">
											<address>
												<strong>北京市</strong>
												<br>
												朝阳区
												<br>
											</address>
											<address>
												<strong>邮箱地址</strong>
												<br>
												<a href="mailto:#">994028591@qq.com</a>
											</address>
										</div>
									</div>
									<div class="span3">
										<h4>
											工作经历
										</h4>
										<ul class="icons push">
											<li>
												<i class="icon-hand-right"></i>
												<strong>北京万盛行投资担保有限公司</strong>
												<br />
												<em>时间:一周</em>
												<br />
												<em>投资理财销售服务。</em>
												<br>
												<a href="javascript:void(0)"></a>
											</li>
											<li>
												<i class="icon-hand-right"></i>
												<strong>融芯思联（北京）有限公司</strong>
												<br />
												<em>时间: 7个月</em>
												<br />
												<em>电子商务软件开发。</em>
												<br>
												<a href="http://www.raiserswin.com" target="_blank">http://www.raiserswin.com</a>
											</li>
										</ul>
										<h4>
											当前状态
										</h4>
										<div class="alert alert-success">
											<i class="icon-ok-sign"></i>
											<s:property value="profileDTO.currentStatus" />
										</div>
										<h4>
											项目经验
										</h4>
										<ul class="unstyled">
											<li>
												<strong>时间管理</strong>
											</li>
											<li>
												<strong>鱼香是只猫</strong>
											</li>
											<li>
												<strong>融芯思联亚中收单项目</strong>
											</li>
											<li>
												<strong>融芯思联亚中电商项目</strong>
											</li>
										</ul>
									</div>
									<div class="space5"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- END PAGE CONTENT-->
				</div>
				<!-- END PAGE CONTAINER-->
			</div>
			<!-- END PAGE -->
		</div>
		<!-- END CONTAINER -->
   <!-- begin footer -->
   <jsp:include page="/WEB-INF/pages/common/footer.jsp"></jsp:include>
   <!-- end footer -->
		<!-- BEGIN JAVASCRIPTS -->
		<!-- Load javascripts at bottom, this will reduce page load time -->
		<script src="js/jquery-1.8.3.min.js"></script>
		<script src="assets/bootstrap/js/bootstrap.min.js"></script>
		<script src="js/jquery.blockui.js"></script>
		<!-- ie8 fixes -->
		<!--[if lt IE 9]>
   <script src="js/excanvas.js"></script>
   <script src="js/respond.js"></script>
   <![endif]-->
		<script type="text/javascript"
			src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
		<script type="text/javascript"
			src="assets/uniform/jquery.uniform.min.js"></script>
		<script src="js/scripts.js"></script>
		<script>
	jQuery(document).ready(function() {
		// initiate layout and plugins
			App.init();
		});
</script>
		<!-- END JAVASCRIPTS -->
		
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"32"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
	</body>
	<!-- END BODY -->
</html>
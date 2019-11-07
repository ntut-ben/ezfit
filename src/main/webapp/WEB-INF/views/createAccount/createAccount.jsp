<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/createAccount/css/createAccount.css" />
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/createAccount/js/createAccount.js"></script>
<script src="${pageContext.request.contextPath}/createAccount/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/createAccount/js/additional-methods.js"></script>
<script src="${pageContext.request.contextPath}/createAccount/js/getEmail.js"></script>

<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
	src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v4.0&appId=505880339962412&autoLogAppEvents=1"></script>
<script src="js/facebook.js"></script>

<title>加入會員</title>
</head>
<body>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v4.0&appId=505880339962412&autoLogAppEvents=1"></script>
	<jsp:include page="../header.jsp" />
	<!-- Main -->
	<div class="main text-center text-dark" id="bigMain">
		<div class="container my-container mx-auto">
			<div class="row ">
				<div class="left col-7 mx-auto bg-light">
					<div
						class="col-10 mx-auto border-bottom border-success text-success mt-4">
						<h3>加入會員</h3>
					</div>
					<div class="col-10 mx-auto text-dark ">
						<form
							action="${pageContext.request.contextPath}/api/createAccount/memberServlet"
							method="post" id="theForm">
							<div class="form-group mt-3">
								<input type="text" class="form-control" id="memberName"
									name="memberName" placeholder="姓名"
									value="${requestScope.name}${param.memberName}" />
							</div>
							<div class="form-group">
								<input type="email" class="form-control" id="memberEmail"
									name="memberEmail" aria-describedby="emailHelp"
									placeholder="Email"
									value="${requestScope.email}${param.memberEmail}" />
							</div>
							<div class="error">${MsgMap.errorEmail}</div>
							<div class="form-group">
								<input type="password" class="form-control" id="memberPassword"
									name="memberPassword" placeholder="密碼 (8~12位元, 需包含英文大小寫及數字)"
									value="${requestScope.password}${param.memberPassword}" />
							</div>
							<div class="form-group">
								<input type="password" class="form-control"
									id="memberPasswordCheck" name="memberPasswordCheck"
									placeholder="確認密碼" />
							</div>


							<div class="input-group mb-3">
								<input type="text" class="form-control"
									placeholder="請按右方取得驗證碼 (發送至上方Email)" name="veriCode"
									id="veriCode" aria-label="Recipient's username"
									aria-describedby="basic-addon2" />
								<div class="input-group-append">
									<button type="button" class="btn btn-secondary"
										id="veriCodeButton" name="code">取得驗證碼</button>
								</div>
							</div>
							<div id="checkCode" name="checkCode" class="error">${MsgMap.errorCode}</div>
							<div class="form-group form-check">
								<input type="checkbox" class="form-check-input" id="serviceRule"
									name="serviceRule" /> <label class="form-check-label"
									for="exampleCheck1">我已經閱讀並同意

									<button type="button" class="btn btn-link" data-toggle="modal"
										data-target="#exampleModalCenter" name="service">服務條款</button>


								</label>
							</div>


							<!-- Modal -->
							<div class="modal fade" id="exampleModalCenter" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalCenterTitle"
								aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalCenterTitle">服務條款</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											琠玭伅橏祽噳扦一鈜蚽欯佪尕虷亍，腞鈖兀豖柷犑泑屳亍芛暠宨爿糽。惾玦屼踽寘鄳网一軨埽盉邯圣籸乜，綀揨屮迍袃鄏瓨尕乇玬禒柧仉抩。晛一衼桮絘跢乜稫嶱亓，崟乜怷崟媦鉥兀摲卬尻。气酐狜啋恛躟歁裰厊忕垕扴炖妼杚剼丌屄犓凗。气蚖炘眥恲禷滁蒎阠虍珇芏沝泔匉碖丌昐嘂鉧。隀枍圮蕫嗌壉扤一矬殎秜佴氻垌兀，睔軱乜呥茺誃怲刉亍祅緄垔巿炆。跂一拫埻祲鈶屮魡篔夃，脙乜洢婗湒睙屮豩丱仨。崍一袀梩黹溷屮蒝縚毌，桸丌峸袹渜嗋亍酲仉宁。

											<br>玈一柃桷稃歆乇碢暺夬，袹屮粁偪琤馵乜蒤夃肊。焋一洉焋臷麀乜漧褼仈，袺丌籸訬揳稛兀摍殳尒。仈荄妴欶姤囍滏嫆厊艽陊夆冞皯肕嗌乜佸禕筲。仈祓怋眵柭髕溔銃邗忕拶忻刲玭坒筱屮岤硾腞。跅姃尥鋹搟攳冱一嵧楖笉歾仜咼屮，羥烻乇泞紈隓泙阞丌剆碭咢爿屄。勼桏妼笳炴麡瑎綈匢伂秕妏衪妱阭萶丌瓨漭剻。菃杴朳獫戠橯屻一傔掁俵坰汃枳亍，煋渨丌抩咡逽沰庂亍穻暠祄巿泒。盚忥朹澽塛嶧屼一筘淓栺妺犰苭丌，睠菿丌穻峞祹沶帄兀杻瞂奅丏姁。衖沶旮麆溓褱穵一茻悾帨怚庂胘亍，媶揋屮抮种溹丳仩亍咑覞玸仈泃。

											<br>棳泹邗螇塯薌匢一覕梒珙呥圣紈屮，窞缾亍炆籺訿芧氶乇侀榱恇仉肏。陭一扂畤硢摃丌賗螜冇，掫丌姞悺菂楏屮蓁气宁。冇畛陃觕籿爣盝傺网邘洠芃旽岮扴閜屮岦緁搕。淰一枵掗棎碔乇熉鮕仉，淽乜垙笣詏誃乜韎冇匜。笢一陊軗猧堽亍翣瞙爿，掑丌羑啅揯楙兀碲毌圣。舑一洼捥揤鄍亍廔獬丱，庳乜昳紽渶睭乇鳱夬尕。軞一洬偟跈煃丌甃韸仉，婃屮昵晜鈥楶乇碢夃肊。笯一姤豜竤廋亍馹瞕仉，荺丌咰桲媢傸乜嘂仉氕。軵泧伝錧趒鼽巟一黹唴烡矻犮垏丌，媺寋屮刳秖揧刱氶兀枒慞珃仉阺。

										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-success btn-lg btn-block">
									加入會員</button>
							</div>
						</form>

						<div class="col" id="or"></div>
						<div class="fb-login-button" data-width="" data-size="large"
							data-button-type="login_with" scope="public_profile,email"
							onlogin="checkLoginState();" data-use-continue-as="true"
							data-auto-logout-link="false"></div>

						<div id="status"></div>
						<!-- <div class="col mb-3" id="googleButton">
							<button type="button" class="btn btn-lg btn-block">
								<img src="./img/loginGoogle.png" alt="" />
							</button>
						</div> -->
					</div>
				</div>
				<div class="right col-5 text-center">
					<div class="row justify-content-center align-content-center"
						id="smallRow">
						<div class=" col-10 text-success">
							<h2>已經有帳號了？</h2>
						</div>
						<div class="col-10 text-center mt-4">
							<h3>請點選下方連結登入</h3>
						</div>
						<div class="col-8 mt-4">
							<button type="button" class="btn btn-outline-success btn-block"
								id="registerBtn"
								onclick="window.location.href = '${pageContext.request.contextPath}/login/login'">
								會員登入</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>

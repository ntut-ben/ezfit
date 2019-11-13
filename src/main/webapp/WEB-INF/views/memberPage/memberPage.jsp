<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>-->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="${pageContext.request.contextPath}/memberPage/js/memberPage.js"></script>
<!-- <script
	src="${pageContext.request.contextPath}/memberPage/js/jquery.twzipcode.min.js"></script> -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.15-rc1/jquery.twzipcode.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/memberPage/css/memberPage.css" />

<title>管理帳號</title>
</head>

<body>
	<jsp:include page="../header.jsp" />
	<div class="main">
		<!-- 中間白色容器 -->
		<div class="container">
			<div class="col-8 topline text-success">
				<i class="fas fa-cog fa-pulse"></i> 管理帳號
			</div>
			<div class="row shadow p-3 mb-5 bg-white rounded mt-3" id="bigRow">
				<!-- 左邊 -->
				<div class="col-6 mt-3 left border-right border-success">
					<h3>基本資料</h3>
					<div>
						<form
							action="${pageContext.request.contextPath}/api/memberPage/updateInfo"
							method="post" name="reg_testdate" style="margin-top: 25px;">
							<div class="form-group row mb-2">
								<label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
								<div class="col">
									<input type="text" readonly class="form-control-plaintext"
										id="staticEmail" value="${LoginOK.email}" />
								</div>
							</div>
							<div class="form-group row mb-2">
								<label for="inputName" class="col-sm-2 col-form-label">姓名</label>
								<div class="col-8">
									<input type="text" class="form-control" name="memberName"
										id="inputName" value="${LoginOK.name}" />
								</div>
							</div>

							<div class="form-group row mb-2">
								<label for="inputSex" class="col-sm-2 col-form-label">性別</label>
								<div class="col-8">
									<select class="custom-select mr-sm-2"
										id="inlineFormCustomSelect" name="memberSex">
										<option
											<c:if test="${! empty LoginOK.gender }">selected="selected"</c:if>>性別</option>
										<option value="男"
											<c:if test="${LoginOK.gender=='男'}">selected="selected"</c:if>>男</option>
										<option value="女"
											<c:if test="${LoginOK.gender=='女'}">selected="selected"</c:if>>女</option>
										<option value="不便透露"
											<c:if test="${LoginOK.gender=='不便透露'}">selected="selected"</c:if>>不便透露</option>
									</select>
								</div>
							</div>

							<div class="form-group row mb-2">
								<label for="inputHeight" class="col-sm-2 col-form-label text-sm">身高</label>
								<div class="input-group col-8">
									<input type="text" class="form-control"
										aria-describedby="basic-addon2" name="memberHeight"
										value="${LoginOK.height}" />
									<div class="input-group-append">
										<span class="input-group-text" id="basic-addon2">公分</span>
									</div>
								</div>
							</div>

							<div class="form-group row mb-2">
								<label for="input" class="col-sm-2 col-form-label">體重</label>
								<div class="input-group col-8">
									<input type="text" class="form-control"
										aria-describedby="basic-addon2" name="memberWeight"
										value="${LoginOK.weight}" />
									<div class="input-group-append">
										<span class="input-group-text" id="basic-addon2">公斤</span>
									</div>
								</div>
							</div>

							<div class="form-group row mb-2">
								<label for="inputSex" class="col-sm-2 col-form-label">生日</label>
								<div class="col-8">
									<input type="text" class="form-control" id="datepicker"
										name="memberBir" value="${birthday}" />
								</div>
							</div>
							<div class="form-group row mb-2">
								<label for="inputName" class="col-sm-2 col-form-label">電話</label>
								<div class="col-8">
									<input type="text" class="form-control" name="memberTel"
										value="${LoginOK.tel}" />
								</div>
							</div>
							<div class="form-group row mb-2">
								<label for="inputName" class="col-sm-2 col-form-label">地址</label>
								<div class="col-8">
									<div id="twzipcode" class="form-group">
								
									</div>
									<input type="text" class="form-control" name="memberAddr"
										value="${LoginOK.address}" />
								</div>
							</div>
							<div class="col" style="text-align: right;">
								<button type="submit" class="btn btn-success mt-4 mr-4">
									儲存更新</button>
							</div>
						</form>
					</div>
				</div>

				<div class="col-6 right">
					<!-- 右上 -->
					<div class="col-12 mt-3 top">
						<h3>密碼修改</h3>
						<div>
							<form
								action="${pageContext.request.contextPath}/api/memberPage/updatePwd"
								method="post" class="form-inline">
								<div class="col">
									<input type="hidden" readonly class="form-control-plaintext"
										name="staticEmail" value="${LoginOK.email}" />
								</div>
								<div class="form-group col-12 mt-3 mb-2">
									<label for="inputName" class="col-3 col-form-label">目前密碼</label>
									<div class="col">
										<input type="password" class="form-control" name="oldPwd"
											aria-label="Username" aria-describedby="basic-addon1"
											value="${requestScope.password}${param.oldPwd}" />
									</div>
									<div class="error">${ErrorMsgKey.ErrorOldPwd}</div>
								</div>
								<div class="form-group col-12 mb-2">
									<label for="inputName" class="col-3 col-form-label">新密碼</label>
									<div class="col">
										<input type="password" id="password"
											class="col-9 form-control" name="newPwd"
											aria-label="Username" aria-describedby="passwordHelpBlock"
											value="${requestScope.newPassword}${param.newPwd}" /> <small
											id="passwordHelpBlock" class="form-text text-muted">密碼
											(8~12位元, 需包含英文大小寫及數字)</small>
									</div>
									<div class="error">${ErrorMsgKey.ErrorFormat}</div>
								</div>
								<div class="form-group col-12 mb-3">
									<label for="inputName" class="col-3 col-form-label">確認新密碼</label>
									<div class="col">
										<input type="password" class="form-control" name="newPwdCheck"
											aria-label="Username" aria-describedby="basic-addon1"
											value="${requestScope.newPasswordCheck}${param.newPwdCheck}" />
									</div>
									<div class="error">${ErrorMsgKey.ErrorNewPwd}</div>
								</div>

								<div class="col" style="text-align: right;">
									<button type="submit" class="btn btn-success mr-5">
										確認更改</button>
								</div>

							</form>
						</div>
					</div>
					<!-- 右下 -->
					<div class="col-12 bottom" style="margin-top: 45px;">
						<h3>頭像更換</h3>
						<form method="POST" enctype="multipart/form-data"
							action="${pageContext.request.contextPath}/api/memberPage/updatePic">
							<div class="row mt-4">
								<div class="col-3 ml-3">
									<img id="blah"
										src="${pageContext.request.contextPath}/img/logo-g.svg"
										alt="your image" />
								</div>
								<div class="col-6 ml-5 align-self-center">
									<Input Type="file" size="40" class="fieldWidth"
										style="width: 480px;" name="file1" id="inputGroupFile01" />
								</div>
								<!--  <div class="col-6 ml-5 align-self-center">

									<input type="file" class="custom-file-input"
										id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" />
									<label class="custom-file-label" for="inputGroupFile01">選擇檔案</label>
								</div>
								-->
								<div class="col" style="text-align: right;">
									<button type="submit" class="btn btn-success mr-5">
										上傳頭像</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../footer.jsp" />

	<script>
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$("#blah").attr("src", e.target.result);
				};

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("#inputGroupFile01").change(function() {
			readURL(this);
		});
	</script>

	<!-- <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script> -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
</body>
</body>
</html>
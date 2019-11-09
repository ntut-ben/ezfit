package Recipe.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Recipe.model.BoardBean;
import Recipe.model.FollowedRecipeBean;
import Recipe.model.KeyWordBean;
import Recipe.model.MateralBean;
import Recipe.model.MethodBean;
import Recipe.model.RecipeBean;
import Recipe.service.BoardService;
import Recipe.service.FollowedRecipeService;
import Recipe.service.KeyWordService;
import Recipe.service.MateralService;
import Recipe.service.MethodService;
import Recipe.service.RecipeService;
import _00.utils.ToJson;
import createAccount.model.MemberBean;
import createAccount.service.MemberService;

@Controller
public class recipeController {

//	新修改的部分為：加入會員(11/07) Ctrl + F 加入會員

	@Autowired
	BoardService bs;
	@Autowired
	FollowedRecipeService frs;
	@Autowired
	RecipeService rs;
	@Autowired
	MemberService ms;
	@Autowired
	MateralService materalService;
	@Autowired
	MethodService methodService;
	@Autowired
	KeyWordService kwService;

	MemberBean member = null;
	RecipeBean rb = null;

//	fileUpLoad所使用的參數
	String def_upload_dir = null;
	int SizeMax = 1024 * 1024 * 4;

//	跳轉 頁面
	@RequestMapping(value = "/my_page")
	public String gotoMyPage() {
		return "my_page";
	}

	@RequestMapping(value = "/publish_recipe")
	public String gotoPublishRecipe() {
		return "publish_recipe";
	}

	@RequestMapping(value = "/recipe_main")
	public String gotoRecipeMain() {
		return "recipe_main";
	}

	@RequestMapping(value = "/recipe_page")
	public String gotoRecipePage() {
		return "recipe_page";
	}

	@RequestMapping(value = "/image/{category}/{imageName}/{lastName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "category") String category,
			@PathVariable(value = "imageName") String imageName, @PathVariable(value = "lastName") String lastName)
			throws IOException {

		File serverFile = null;
		if (category.equals("member")) {
			serverFile = new File("C:/ezfitData/memberPic/" + imageName);
		} else if (category.equals("recipe")) {
			serverFile = new File("C:/ezfitData/recipePic/" + imageName);
		} else if (category.equals("method")) {
			serverFile = new File("C:/ezfitData/methodPic/" + imageName);
		} else if(category.equals("memberHead")) {
			serverFile = new File("C:/ezfitData/memberHeadPic/" + imageName);
		}

		return Files.readAllBytes(serverFile.toPath());
	}
	
	
//	上傳留言板留言
	@RequestMapping(value = "/board/addNewChat", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String addChatServlet(@RequestParam("recipeId") String recipeId) {
		List<BoardBean> boardList = new ArrayList<>();
		boardList = bs.selectBoard(recipeId);
		ToJson<BoardBean> json = new ToJson<>();
		String jsonString = "";
		jsonString = json.getArrayJson(boardList);
		System.out.println(jsonString);
		return jsonString;
	}

//	寫留言，但尚未加入判斷是否為會員              加入會員
	@RequestMapping(value = "/board/addNewChat", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String addChatServlet(@RequestParam("recipeId") String recipeId,
			@RequestParam("detail") String detail, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");
//		member = new MemberBean();
//		member.setPkey(1);

		List<BoardBean> boardList = new ArrayList<>();
		if (detail == null)
			detail = "";
		bs.insertBoard(recipeId, String.valueOf(member.getPkey()), detail);
		boardList = bs.selectBoard(recipeId);
		ToJson<BoardBean> json = new ToJson<>();
		String jsonString = "";
		jsonString = json.getArrayJson(boardList);
		return jsonString;
	}

//	確認是否有追蹤，ownerId要從session拿emberBean     加入會員
	@RequestMapping(value = "/checkedFollowed", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String checkFollowed(@RequestParam("recipeId") String recipeId, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");
		String checked;
//		String followerId = "1";
		String followerId = String.valueOf(member.getPkey());
		checked = frs.checkFollowed(followerId, recipeId);

		ToJson<String> json = new ToJson<>();
		String jsonString = json.getJson(checked);

		return jsonString;
	}

//	編輯食譜頁面，要刪除食譜，不確定produces這樣設對不對

	@RequestMapping(value = "/recipe/delete", produces = "text/html;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody void deleteRecipe(HttpServletResponse response, @RequestParam("published") String del,
			@RequestParam("pk") String recipeId) throws IOException {
		if (del.equals("delete")) {
			int n = 0;
			n = rs.deleteRecipe(recipeId);
			if (n == 1) {
				response.getWriter().write("done");
			}
		}
	}

//	編輯個人簡介，Member要從session取，       加入會員
	@RequestMapping(value = "/editMemberIntro", produces = "text/html;charset=utf-8", method = RequestMethod.POST)
	public void editMemberIntroServlet(@RequestParam("introduction") String introduction, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");
//		String myId = "2";
//		member = new MemberBean();
//		member = rs.getMemberByMemberId(myId);
		member.setIntroduction(introduction);
		ms.updateMemInfo(member);
		response.getWriter().write("done");
	}

//	**************************************
//	*                                    *
//	*				極度有可能會死	         *
//	*                                    *
//	**************************************
//	編輯個人簡介的背景圖，Member要從session取，      加入會員
	@RequestMapping(value = "/changeMemberPic", method = RequestMethod.POST)
	public void editMemberPic(HttpServletRequest request) {
//		String memberId = "2";
//		member = new MemberBean();
//		member = rs.getMemberByMemberId(memberId);
		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(SizeMax);
		// 每一個Fileitem代表一個form上傳的物件內容ex input type="text"
		@SuppressWarnings("rawtypes")
		List items = null; // 會產生 FileUploadException
		// 把資料從request取出
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} // Parse the request
		@SuppressWarnings("rawtypes")
		Iterator iter = items.iterator();
		try {
			while (iter.hasNext()) {// 先把所有參數取得而不先write to file
				FileItem item = (FileItem) iter.next();

				// 一般文字欄位
				if (item.isFormField()) {
					System.out.println("上傳檔案的其它參數:" + item.getFieldName() + "=" + item.getString("UTF-8"));

				} else {// 上傳檔案欄位
					// or it's a file upload request
					if (item.getSize() > 0) {
//								uploadlist.put(item.getFieldName(), item);
						System.out.println("上傳檔案:" + item.getFieldName() + "/" + item.getName());
						setUploadDir("C:/ezfitData/memberPic");
						String newfile = doUpload(item, "coverImg" + member.getPkey());
						member.setCoverImg(newfile);
						ms.updateMemInfo(member);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	編輯食譜用，在撈取食譜資訊    加入會員
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/recipe/editRecipe", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String editRecipeServlet(@RequestParam("recipeId") String recipeId,
			HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");

		List<MateralBean> materalList = new ArrayList<>();
		List<MethodBean> methodList = new ArrayList<>();

		rb = rs.getRecipeByRecipeId(recipeId);
		String json = "";

		methodList = methodService.showMethod(recipeId);
		materalList = materalService.selectMateralByRecipe(recipeId);
		// ============資料封裝================
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		list.add(rb);
		list.add(methodList.size());
		list.add(materalList.size());
		list.add(methodList);
		list.add(materalList);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		json = gson.toJson(list);
		return json;
	}

//	依使用者輸入的搜尋字串去查詢，不須經過會員認證
	@RequestMapping(value = "/searchUserKeyIn", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String findTargetRecipeServlet(@RequestParam("search") String target) {

		Set<RecipeBean> recipeSet = new HashSet<>();
		recipeSet = rs.selectRecipeByRecipeNameOrMemberName(target);

		ToJson<RecipeBean> toJson = new ToJson<>();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<RecipeBean> list = new ArrayList(recipeSet);
		String json = toJson.getArrayJson(list);
		return json;
	}

//	布置使用者所查詢的會員資料(食譜數量，個人自介、背景等等，無須會員認證
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/whoAreYou", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String getMemberServlet(@RequestParam("ownerId") String ownerId) {

		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		member = rs.getMemberByMemberId(ownerId);

//		找該年分食譜數量
		String year2019 = String.valueOf(rs.selectRecipePerYear(ownerId, true, "2019").size());
		String year2018 = String.valueOf(rs.selectRecipePerYear(ownerId, true, "2018").size());
		String year2017 = String.valueOf(rs.selectRecipePerYear(ownerId, true, "2017").size());
		String year2016 = String.valueOf(rs.selectRecipePerYear(ownerId, true, "2016").size());
		list.add(member);
		list.add(year2019);
		list.add(year2018);
		list.add(year2017);
		list.add(year2016);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(list);
		return json;
	}

//	*************************************************************
//	*紀錄熱門關鍵字，之後要加入將關鍵字搜尋，並找出相關資源，此部分尚未完成，只有紀錄關鍵字功能*
//	*************************************************************
	@RequestMapping(value = "/keyword/submit.do", method = RequestMethod.GET)
	public String keywordSearchServlet(@RequestParam("searchRecipe") String keyword) {
		if (!keyword.equals(""))
			kwService.insertKeyWord(keyword);

		return "redirect:/recipe_main";
	}

//	找出前十名的熱門關鍵字
	@RequestMapping(value = "/keyword/submit.do", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String keywordSearchServlet() {
		List<KeyWordBean> list = new ArrayList<>();
		;
		list = kwService.hotSearch();
		ToJson<KeyWordBean> json = new ToJson<>();
		String js = json.getArrayJson(list);
		return js;
	}

	// 會員的個人食譜頁面，需要依照Session取得的memberId與傳來的memberId比對是自己的食譜還是別人的食譜
	// 會依傳入年分來回傳食譜
	// 尚未完成取Session來比對的步驟 加入會員
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/myRecpie.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String myRecipeServlet(@RequestParam("ownerId") String ownerId,
			@RequestParam("year") String year, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");
//		系統尚未整合，先自己寫死
//		String myId = "2";
		String myId = String.valueOf(member.getPkey());

		@SuppressWarnings("rawtypes")
		List list = new ArrayList<>();
		String json;
		if (myId.equals(ownerId)) {
//			我的食譜
			list = rs.selectRecipePerYear(ownerId, true, year);
			list.add(true);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			json = gson.toJson(list);
			System.out.println("已寫出我的食譜");

		} else {
//			別人的食譜
			list = rs.selectRecipePerYear(ownerId, true, year);
			list.add(false);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			json = gson.toJson(list);
			System.out.println("已寫出別人的食譜");

		}
		return json;
	}

//	取出本週新食譜(周一起，周日結束)，上限四筆
	@RequestMapping(value = "/recipe/weekRecipe", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String newInOneWeekServlet() {

		List<RecipeBean> list = new ArrayList<>();
		Date monday = getMondayOfThisWeek();
		Date sunday = getSundayOfThisWeek();
		list = rs.weekNew(monday, sunday);

		ToJson<RecipeBean> json = new ToJson<>();
		String jsonString = "";
		jsonString = json.getArrayJson(list);

		return jsonString;
	}

//	追蹤食譜事宜
//	尚未寫身分驗證，要從Session取出使用者PK，來判斷是誰要追隨食譜
	@RequestMapping(value = "/aboutSave", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody void saveOrUnsavedServlet(@RequestParam("followId") String fbPk,
			@RequestParam("recipeId") String recipeId, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");
//		目前先把member寫死
//		String followerId = "1";
		String followerId = String.valueOf(member.getPkey());
		FollowedRecipeBean fb = new FollowedRecipeBean();

		if (fbPk.equals("notFound")) {
			System.out.println("該使用者要追蹤");
			fb = frs.insertFollowedRecipe(recipeId, followerId);
			String json = "";
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			json = gson.toJson(fb);

			try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("處理完畢");
		} else {
			System.out.println("取消追蹤");
			frs.deleteFollowedRecipe(fbPk);
		}

	}

//	關鍵字搜尋
	@RequestMapping(value = "/recipe/search.do", method = RequestMethod.GET)
	public @ResponseBody String searchRecipeServlet(@RequestParam("name") String search) {

		String JsonString = null;
		if (!search.equals("")) {
			ToJson<RecipeBean> json = new ToJson<>();
			Set<RecipeBean> recipeSet = new HashSet<>();
			Set<RecipeBean> recipeSet2 = new HashSet<>();

			recipeSet = rs.selectRecipeByRecipeNameOrMemberName(search);
			recipeSet2 = materalService.selectRecipeByMateral(search);

//			要合併set1 AND set2
			if (null != recipeSet2 && recipeSet2.size() > 0) {
				if (recipeSet.size() > 0) {
					recipeSet.removeAll(recipeSet2); // set1中去除set2已有的對象
					recipeSet.addAll(recipeSet2); // set1中添加set2
				} else {
					recipeSet = recipeSet2;
				}
			}

			List<RecipeBean> list = new ArrayList<>(recipeSet);
			JsonString = json.getArrayJson(list);
		}
		return JsonString;
	}

//	找出熱門食譜(依照收藏數)，最多四筆
	@RequestMapping(value = "/recipe/hotRecipe.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String selectHotRecipeServlet() {

		List<RecipeBean> list = new ArrayList<>();
		list = rs.searchHotRecipe();

		ToJson<RecipeBean> toJson = new ToJson<RecipeBean>();
		String JsonString = "";
		JsonString = toJson.getArrayJson(list);
		return JsonString;
	}

//	取出使用者的所有發佈的食譜，目的在知道使用者食譜數
//	該方法需要從Session取出MemberBean，目前先寫死     加入會員(該方法好像不用拿session)
	@RequestMapping(value = "/getTotal", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String totalRecipeServlet(@RequestParam("memberId") String owmerId) {

		List<RecipeBean> list = new ArrayList<>();
		list = rs.selectRecipe(owmerId, true);
		ToJson<RecipeBean> json = new ToJson<>();
		String JsonString = "";
		JsonString = json.getArrayJson(list);
		return JsonString;
	}

//	**************************************
//	*                                    *
//	*				極度有可能會死	         *
//	*                                    *
//	**************************************
//	上傳食譜，Member要從session取，暫時先寫死      加入會員
	@RequestMapping(value = "/recipe/writeRecipe", method = RequestMethod.POST)
	public String writeRecipeServlet(HttpServletRequest request) {
		// 尚未合併，故先自己把member寫死
//		MemberBean member = new MemberBean();
//		member.setPkey(1);
		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");

//====================================================
		RecipeBean recipe = new RecipeBean();
//		MethodBean method;
		MethodBean method = new MethodBean();
//		MateralBean mb;
		MateralBean mb = new MateralBean();
		List<MethodBean> methodList = new ArrayList<>();

		recipe.setMember(member);
		int methodLength = 0;
		int killed = 0;
		@SuppressWarnings("unused")
		boolean insert = true;
		int point = 0;
		String oldFileName = "";
		@SuppressWarnings("unused")
		String oldMethod = "";

//		====基本設定====
		int bufferSize = 4096;
		Map aboutMethod = null;
		Map uploadlist = null;
		File tempfile = null;

//===============================處理檔案上傳============================================
		// 處始化時把給request把所有的值取出,存入map
		aboutMethod = new HashMap();
		uploadlist = new HashMap();
		// 建立一個以disk-base的檔案物件
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 初始化內容
		// 傳送所用的buffer空間
		factory.setSizeThreshold(bufferSize);
		// 設定暫放目錄
		factory.setRepository(tempfile);
		// 建立一個檔案上傳的物件
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 最大檔案大小
		upload.setSizeMax(SizeMax);
		// 每一個Fileitem代表一個form上傳的物件內容ex input type="text"
		List items = null; // 會產生 FileUploadException
		// 把資料從request取出
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} // Parse the request
		Iterator iter = items.iterator();
		try {
			while (iter.hasNext()) {// 先把所有參數取得而不先write to file
				FileItem item = (FileItem) iter.next();
//				method = new MethodBean();
//				mb = new MateralBean();
				// 一般文字欄位
				if (item.isFormField()) {

					String key = item.getFieldName();
					String value = item.getString("UTF-8");
					System.out.println("上傳檔案的其它參數:" + item.getFieldName() + "=" + item.getString("UTF-8"));
					if (key.equals("recipePk")) {
						if (Integer.valueOf(value) == 0) {
							recipe.setRecipeId(null);
							rs.insertRecipe(recipe);
						} else {
							oldFileName = rs.getRecipeByRecipeId(value).getFileName();
							oldMethod = methodService.selectMethodById(value).getFileName();
							recipe.setRecipeId(Integer.valueOf(value));
							methodList = methodService.showMethod(value);
							methodLength = methodList.size();
							killed = methodLength;
							insert = false;
						}
					}
					if (key.equals("recipeName")) {
						recipe.setRecipeName(value);
					}
					if (key.equals("spendTime")) {
						if (value == null || value.equals("null")) {
							recipe.setSpendTime(String.valueOf(0));
						} else {
							recipe.setSpendTime(value);
						}
					}
					if (key.equals("servings")) {
						if (value == null || value.equals("null")) {
							recipe.setServings(0);
						} else {
							recipe.setServings(Integer.valueOf(value));
						}
					}
					if (key.equals("introduction")) {
						recipe.setIntroduction(value);
					}
					if (key.equals("published")) {
						if (value.equals("published")) {
							recipe.setPublished(true);
							rs.updateRecipe(recipe);
							System.out.println("新增Recipe 完成");
							methodService.deleteMethodByRecipe(String.valueOf(recipe.getRecipeId()));
							System.out.println("預先清空method");
							materalService.deleteMateralByRecipeId(String.valueOf(recipe.getRecipeId()));
							System.out.println("預先清空materal");
						}
						if (value.equals("saved")) {
							recipe.setPublished(false);
							rs.updateRecipe(recipe);
							System.out.println("Recipe 儲存完成，(不)發佈");
							methodService.deleteMethodByRecipe(String.valueOf(recipe.getRecipeId()));
							System.out.println("預先清空method");
							materalService.deleteMateralByRecipeId(String.valueOf(recipe.getRecipeId()));
							System.out.println("預先清空materal");
						}
					}

					if (key.substring(0, 4).equals("mdPk")) {
						if (value.equals("undefined") || Integer.valueOf(value) == 0) {
							method.setId(null);
							methodService.insertOrUpdateMethod(method);
						} else {
							point = methodLength - killed;
							--killed;
							method.setId(null);
							methodService.insertOrUpdateMethod(method);
						}
					}
					if (key.substring(0, 4).equals("meth")) {
						method.setDetail(value);
						method.setRecipe(recipe);
						methodService.insertOrUpdateMethod(method);
						method = new MethodBean();
					}
					if (key.substring(0, 4).equals("mlPk")) {
						if (Integer.valueOf(value) == 0) {
							mb.setMateral_Id(null);
						} else {
							mb.setMateral_Id(null);
						}
					}
					if (key.substring(0, 4).equals("mate")) {
						mb.setMateralName(value);
						mb.setRecipe(recipe);
					}
					if (key.substring(0, 4).equals("unit")) {
						mb.setUnit(value);
						materalService.insertMateral(mb);
						mb = new MateralBean();
					}
				} else {// 上傳檔案欄位
					// or it's a file upload request
					if (item.getSize() > 0) {
						uploadlist.put(item.getFieldName(), item);
						System.out.println("上傳檔案:" + item.getFieldName() + "/" + item.getName());
//                    ========以下為自已加的==========
						if (item.getFieldName().substring(0, 6).equals("recipe")) {
							setUploadDir("C:/ezfitData/recipePic");
							String newfile = doUpload(item, "recipe" + recipe.getRecipeId());
							recipe.setFileName(newfile);
						} else if (item.getFieldName().substring(0, 6).equals("method")) {
							methodService.insertOrUpdateMethod(method);
							setUploadDir("C:/ezfitData/methodPic");
							String fileName = doUpload(item, ("method" + method.getId()));
							System.out.println(fileName);
							method.setFileName(fileName);
						} else {
							System.out.println("找不到分類，不存~");
						}

					} else {
						System.out.println("傳過來的檔案Size不大於0");
						System.out.println("上傳失敗的檔案:" + item.getFieldName() + "/" + item.getName());
						if (item.getFieldName().substring(0, 6).equals("recipe")) {
							recipe.setFileName(oldFileName);
							System.out.println("recipe沒改圖片，輸入舊圖片檔名=" + oldFileName);
						} else if (item.getFieldName().substring(0, 6).equals("method")) {
							try {
								method.setFileName(methodList.get(point).getFileName());
								System.out.println("method沒改圖片，輸入舊圖片檔名=" + methodList.get(point).getFileName());
							} catch (Exception e) {
								e.printStackTrace();
								method.setFileName(null);
							}
						} else {
							System.out.println("找沒");
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/recipe_main";

	}

//	從session裡面拿Member               加入會員
	@ResponseBody
	@RequestMapping(value = "/getMember", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getMyMemberServlet(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		member = (MemberBean) session.getAttribute("LoginOK");
//		String myId = "2";
//		String myId = String.valueOf(member.getPkey());
//		member = new MemberBean();
//		member = rs.getMemberByMemberId(myId);
		String json = "";
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		json = gson.toJson(member);
		return json;
	}

	

	// ==============主程式所使用之方法===========================
	// 設定檔案上傳後存放的地方
	public void setUploadDir(String upload_dir) {
		this.def_upload_dir = upload_dir;
	}

	// 開始上傳
	public String doUpload(FileItem item, String fileName) {

		File file1 = new File("C:/ezfitData/memberPic");
		File file2 = new File("C:/ezfitData/recipePic");
		File file3 = new File("C:/ezfitData/methodPic");

		if (!file1.exists()) {
			file1.mkdir();
		}

		if (!file2.exists()) {
			file2.mkdir();
		}

		if (!file3.exists()) {
			file3.mkdir();
		}

		String str = "";
		long sizeInBytes = item.getSize();
		// 碓認上傳資料是否有誤
		if (sizeInBytes > SizeMax)
			return "檔案太大!";
		if (sizeInBytes > 0) {
			int index = -1;
			String itename = null;
			if ((index = item.getName().lastIndexOf("\\")) != -1)
				itename = item.getName().substring(index, item.getName().length());
			else
				itename = item.getName();
			// 副檔名
			String formatName = itename.substring(itename.length() - 4, itename.length());
			fileName = (fileName + formatName).toLowerCase();
			System.out.println("上傳檔案檔案名稱:" + fileName);
//	        =====test=====
			System.out.println("target:" + def_upload_dir + "\\" + fileName);
//	        ==============
			File uploadedFile = new File(def_upload_dir + "\\" + fileName);
			// 會產生 Exception
			try {
				item.write(uploadedFile);
				str = fileName;
			} catch (Exception e) {
//	            System.out.println("上傳失敗!" + e.toString());
				e.printStackTrace();
				str = "上傳失敗!";
			}
			// 會產生 Exception
		}
		return str;
	}

	public void reName(String targetFile, String oldFileName, String newFileName) {
		// File (or directory) with old name
//		File file = new File("C:/_JSP/workspaceJDBC/ezfit/src/main/webapp/data/" + targetFile + "/" + oldFileName);
		File file = new File("C:/ezfitData/" + targetFile + "/" + oldFileName);

		// File (or directory) with new name
//		File file2 = new File("C:/_JSP/workspaceJDBC/ezfit/src/main/webapp/data/" + targetFile + "/" + newFileName);
		File file2 = new File("C:/ezfitData/" + targetFile + "/" + newFileName);
		@SuppressWarnings("unused")
		boolean success = true;
		try {
			success = file.renameTo(file2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("X");
		}
//		if (!success) {
//			// File was not successfully renamed
//		}
	}

	// 取附檔名
	public String getLastName(String name) {
		String arr[] = name.split("\\.");
		String ans = "." + arr[1];
		return ans;
	}

//	得到週一
	static SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");

	/** * 得到本週週一 * * @return yyyy-MM-dd */
//	public static String getMondayOfThisWeek() { 
	public static Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		System.out.println(c.getTime());
		return c.getTime();
//	return format.format(c.getTime()); 
	}

//	得到週日
//	public static String getSundayOfThisWeek() {
	public static Date getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
//		return format.format(c.getTime());
		return c.getTime();
	}

}

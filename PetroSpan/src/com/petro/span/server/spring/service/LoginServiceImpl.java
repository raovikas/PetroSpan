package com.petro.span.server.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
//import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.google.api.services.fusiontables.Fusiontables;
import com.google.api.services.fusiontables.Fusiontables.Query.Sql;
import com.google.api.services.fusiontables.model.Sqlresponse;
import com.petro.span.shared.LoginModel;
import com.petro.span.shared.PrivilegedFiltersModel;
import com.petro.span.shared.PrivilegedTabModel;
import com.petro.span.shared.UserDto;




@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	AuthorizationService authService;

	String PRIVILEGED_TABSELECTION_TABLEID = "1cNpkNeu2za7z-PzqfSBESdVlOjfV6YhKzpbxrpw3";
	String PRIVILEGED_FILTERSELECTION_TABLEID ="1m7aiENt0rXVzquEwidCjsiHYw521BbiBuBefZK4P";

	@Override
	public Map<String, String> getUserInfo(String token) throws JSONException  {
		//		System.out.println("server side token "+token);
		token = token.replace("\"", "");
		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="	+ token;
		Map<String,String>  userDetailMap = new HashMap<String, String>();
		final StringBuffer r = new StringBuffer();
		try {
			final URL u = new URL(url);
			final URLConnection uc = u.openConnection();
			final int end = 1000;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(uc.getInputStream());
				br = new BufferedReader(isr);
				final int chk = 0;
				while ((url = br.readLine()) != null) {
					if ((chk >= 0) && ((chk < end))) {
						r.append(url).append('\n');
					}
				}
				System.out.println("r append "+r);




			} catch (final java.net.ConnectException cex) {
				r.append(cex.getMessage());
			} catch (final Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (final Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		JSONObject  jsonObject = new JSONObject(r.toString());

		if(!jsonObject.isNull("email"))
			userDetailMap.put("email",jsonObject.get("email").toString());

		if(!jsonObject.isNull("name"))
			userDetailMap.put("name",jsonObject.get("name").toString());

		if(!jsonObject.isNull("hd"))
			userDetailMap.put("hd",jsonObject.get("hd").toString());

		if(!jsonObject.isNull("picture"))
			userDetailMap.put("picture",jsonObject.get("picture").toString());

		return userDetailMap;

	}




	@Override
	public Boolean isTokenExpire(String token) throws JSONException {
		token = token.replace("\"", "");
		String url 	= "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token="+token;
		final StringBuffer r = new StringBuffer();
		try {
			final URL u = new URL(url);
			final URLConnection uc = u.openConnection();
			final int end = 1000;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(uc.getInputStream());
				br = new BufferedReader(isr);
				final int chk = 0;
				while ((url = br.readLine()) != null) {
					if ((chk >= 0) && ((chk < end))) {
						r.append(url).append('\n');
					}
					//					System.out.println("r append "+r);
				}




			} catch (final java.net.ConnectException cex) {
				r.append(cex.getMessage());
			} catch (final Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (final Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}	
		JSONObject  jsonObject = new JSONObject(r.toString());
		System.out.println("jsonObject   "+jsonObject.toString());

		if(jsonObject.has("error")){
			System.out.println("error so return true");
			return true;

		}
		return false;
	}









	String REGISTERED_USER_TABLEID = "10wxEU56AnodDoRGt2jVJPJdHPnHvzD4uH8sFeZ5f" ;
	String USER_ROLE_TABLEID = "16jmzP-A_kBLTSWzURieiNZ7EudFs1DNpf9N2OQr1";

	@Override
	public LoginModel login(UserDto userDto) throws IOException, GeneralSecurityException, JSONException {
		Fusiontables fusionTable = null;
		StringBuffer returnValue = new StringBuffer();
		String token = userDto.getAccessToken();
		String newtoken = null ;

		//		Map<String,List<String>> returnMap = new HashMap<String, List<String>>();
		List<String> role_list = new ArrayList<String>();
		List<String> returnMsgList = new ArrayList<String>();
		PrivilegedTabModel model = new PrivilegedTabModel();
		PrivilegedFiltersModel filtersModel = new PrivilegedFiltersModel();
		LoginModel loginModel = new LoginModel();


		if(userDto.getAccessToken()==null || isTokenExpire(token)){
			newtoken = authService.getAccessToken();
			fusionTable =  authService.createAuthorizedClient(newtoken);

			//	       model.getUserInfo().setToken(newtoken);
		}
		else{
			fusionTable =  authService.createAuthorizedClient(token);

		}

		Sql sql =  fusionTable.query().sql("SELECT LoginName , HashedPassword From "+REGISTERED_USER_TABLEID+" Where LoginName = '"+userDto.getLoginName()+"'");

		System.out.println("SELECT LoginName , HashedPassword From "+REGISTERED_USER_TABLEID+" Where LoginName = '"+userDto.getLoginName()+"'");

		Sqlresponse response =   sql.execute();

		System.out.println("response.toString()  "+response.toString());
		if(response.getRows()==null){
			//			returnValue.append("Wrong UserName or Password");	
			returnMsgList.add("Wrong UserName or Password");
		}
		else if(response.getRows().size() == 1){
			String hashPassFrmDB =  (String) response.getRows().get(0).get(1);
			if(BCrypt.checkpw(userDto.getPassword(), hashPassFrmDB)){

				/**
				 * below commented code create a fusion table view to get user role
				 */

				//				String USERRoleTable =  "16jmzP-A_kBLTSWzURieiNZ7EudFs1DNpf9N2OQr1";
				//				String RoleTable =  "1U2K3DDhS_XVLyq5mbUP5zW1dcjvfCQjs-1Zu1lqM";
				//				Sql sq =fusionTable.query().sql("CREATE VIEW GETUSERRole AS (Select RoleID From "+USERRoleTable+ " as UR "
				//						+ "LEFT OUTER JOIN "+RoleTable+" As RT on RT.RoleID = UR.RoleID Where UT.LoginName = '"+userDto.getLoginName()+"' )");
				//				
				//				System.out.println("CREATE VIEW GETUSERRole AS (Select RoleID From 1U2K3DDhS_XVLyq5mbUP5zW1dcjvfCQjs-1Zu1lqM as RT "
				//						+ "LEFT OUTER JOIN 16jmzP-A_kBLTSWzURieiNZ7EudFs1DNpf9N2OQr1 As UR on RT.RoleID = UR.RoleID Where UR.LoginName = '"+userDto.getLoginName()+"' )");
				//				Sqlresponse resp = sq.execute();
				//				
				//				resp.getRows().get(0);
				//				System.out.println("resp  "+resp);
				//				System.out.println("tableid "+resp.getRows().get(0).get(0));
				//				Sqlresponse res  = fusionTable.query().sql("Select RoleID from "+resp.getRows().get(0).get(0)).execute();
				//				
				//				for(int i = 0 ; i<res.getRows().size();i++){
				//					role_list.add((String) res.getRows().get(i).get(0));
				//					System.out.println("role_list size  "+role_list.size());
				//				}
				//				System.out.println("res  "+res);
				//				System.out.println("Select RoleID from "+resp.getRows().get(0).get(0));

				Sql userRoleSql =	fusionTable.query().sql("Select RoleID From "+USER_ROLE_TABLEID+" Where LoginName = '"+userDto.getLoginName()+"'");
				System.out.println("Select RoleID From "+USER_ROLE_TABLEID+" Where LoginName = '"+userDto.getLoginName()+"'");
				Sqlresponse userRoleSqlResponse  =   userRoleSql.execute();
				System.out.println( "userRoleSqlResponse "+userRoleSqlResponse.toString());
				for (int i = 0; i < userRoleSqlResponse.getRows().size(); i++) {
					role_list.add((String) userRoleSqlResponse.getRows().get(i).get(0));
					System.out.println("role_list size  "+role_list.size());
				}


				/*
				 * if user is Privileged user then access the privileged he/she has to access the Tabs of application from PrivilegedTabSelection fusion table
				 */



				if(role_list.contains("Role_PRIVILAGE")){
					Sql userPrivilegedSql =	fusionTable.query().sql("Select * From "+PRIVILEGED_TABSELECTION_TABLEID+" Where LoginName = '"+userDto.getLoginName()+"'");
					System.out.println("Select * From "+PRIVILEGED_TABSELECTION_TABLEID+" Where LoginName = '"+userDto.getLoginName()+"'");
					Sqlresponse userPrivilegedSqlResponse  =   userPrivilegedSql.execute();
					System.out.println( "userPrivilegedSqlResponse "+userPrivilegedSqlResponse.toString());

					if(userPrivilegedSqlResponse.getRows()!=null && userPrivilegedSqlResponse.getRows().size() ==1){


						List<List<Object>> rows = userPrivilegedSqlResponse.getRows();
						for (int row = 0; row < rows.size(); row++) {
							List<Object> r = rows.get(row);

							for (int col = 0; col < r.size(); col++) {

								if(col == 1)
									model.setTechnical((String) r.get(col));
								else if(col == 2)
									model.setCommercial((String) r.get(col));
								else if(col == 3)
									model.setForecast((String) r.get(col));
								else if(col == 4)
									model.setProduction((String) r.get(col));

							}

						}
					}

					
					
					/*
					 * if user is Privileged user then access the privileged he/she has to access the Filters of application from PrivilegedFiltersSelection fusion table
					 */
					
					Sql userFiltersPrivilegedSql =	fusionTable.query().sql("Select * From "+PRIVILEGED_FILTERSELECTION_TABLEID+" Where LoginName = '"+userDto.getLoginName()+"'");
					System.out.println("Select * From "+PRIVILEGED_FILTERSELECTION_TABLEID+" Where LoginName = '"+userDto.getLoginName()+"'");
					Sqlresponse userFiltersPrivilegedSqlResponse  =   userFiltersPrivilegedSql.execute();
					System.out.println( "userFiltersPrivilegedSqlResponse "+userFiltersPrivilegedSqlResponse.toString());

					
					
					if(userFiltersPrivilegedSqlResponse.getRows()!=null && userFiltersPrivilegedSqlResponse.getRows().size() ==1){


						List<List<Object>> rows = userFiltersPrivilegedSqlResponse.getRows();
						for (int row = 0; row < rows.size(); row++) {
							List<Object> r = rows.get(row);

							for (int col = 0; col < r.size(); col++) {

								if(col == 1)
									filtersModel.setQuailtyZone((String) r.get(col));
								
								else if(col == 2)
									filtersModel.setTownshipRange((String) r.get(col));
								
								else if(col == 3)
									filtersModel.setOperator((String) r.get(col));
								
								else if(col == 4)
									filtersModel.setLeaseName((String) r.get(col));
								
								else if(col == 5)
									filtersModel.setWellName((String) r.get(col));
								
								else if(col == 6)
									filtersModel.setWellType((String) r.get(col));
								
								else if(col == 7)
									filtersModel.setWellOrientation((String) r.get(col));
								
								else if(col == 8)
									filtersModel.setFluidType((String) r.get(col));
								
								else if(col == 9)
									filtersModel.setWellStatus((String) r.get(col));
								
								else if(col == 10)
									filtersModel.setProducingZone((String) r.get(col));
								
								else if(col == 11)
									filtersModel.setPermitDate((String) r.get(col));
								
								else if(col == 12)
									filtersModel.setSpudDate((String) r.get(col));
								
								else if(col == 13)
									filtersModel.setCompletionDate((String) r.get(col));
								
								else if(col == 14)
									filtersModel.setFirstProductionDt((String) r.get(col));
								
								else if(col == 15)
									filtersModel.setSearchBy((String) r.get(col));
								
								
							}

						}
					}
					
				}







				returnMsgList.add("200");
			}
			else
				returnMsgList.add("Wrong UserName or Password");
		}

		//		returnMap.put("ReturnMsg",returnMsgList);
		//		returnMap.put("RoleList", role_list);
		loginModel.setModel(model);
		loginModel.setReturnMsgList(returnMsgList);
		loginModel.setRole_list(role_list);
		loginModel.setFiltersModel(filtersModel);

		System.out.println("returnValue.toString() "+returnValue.toString());
		return loginModel;
	}

}

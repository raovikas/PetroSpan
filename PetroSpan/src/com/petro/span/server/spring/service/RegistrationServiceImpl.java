package com.petro.span.server.spring.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.fusiontables.Fusiontables;
import com.google.api.services.fusiontables.Fusiontables.Query.Sql;
import com.google.api.services.fusiontables.model.Sqlresponse;
import com.petro.span.shared.AdminMasterModel;
import com.petro.span.shared.FilterSelectionModel;
import com.petro.span.shared.PrivilegedFiltersModel;
import com.petro.span.shared.PrivilegedTabModel;
import com.petro.span.shared.RegisteredUser;
import com.petro.span.shared.RegistrationModel;
import com.petro.span.shared.TabSelectionModel;

@Service
public class RegistrationServiceImpl implements RegistrationService{


	@Autowired
	AuthorizationService authService;

	@Autowired
	LoginService gloginService;

	String REGISTERED_USER_TABLEID = "10wxEU56AnodDoRGt2jVJPJdHPnHvzD4uH8sFeZ5f" ;
	String USER_ROLE_TABLEID = "16jmzP-A_kBLTSWzURieiNZ7EudFs1DNpf9N2OQr1";
	String UPDATE_USER_ROLE_TABLEID = "1u6WJipbp6u9xjYSh5U1xio3YQR0XSIEiOBrBv7U_";
	String MERGE_RegisteredUser_TABLEID ="1hFTbuUtLlaouui-Go2NrH-J63guOqotl-kCyU-qs";
	String PRIVILEGED_TABSELECTION_TABLEID = "1cNpkNeu2za7z-PzqfSBESdVlOjfV6YhKzpbxrpw3";
	String PRIVILEGED_FILTERSELECTION_TABLEID ="1m7aiENt0rXVzquEwidCjsiHYw521BbiBuBefZK4P";


	@Override
	public String saveData(RegistrationModel model) throws IOException, GeneralSecurityException, JSONException {
		Fusiontables fusionTable = null;
		String returnToken = "";
		String token = model.getAccessToken();
		if(model.getAccessToken()==null || gloginService.isTokenExpire(token)){
			String newtoken = authService.getAccessToken();
			fusionTable =  authService.createAuthorizedClient(newtoken);
			returnToken = returnToken+newtoken;
		}
		else{
			fusionTable =  authService.createAuthorizedClient(token);
			returnToken = returnToken+token;
		}

		Sql sql =  fusionTable.query().sql("INSERT into "+REGISTERED_USER_TABLEID +"  ( 'UserName' , 'EmailAddress' , 'Gender' , 'LoginName' ,'HashedPassword' )  "
				+ "VALUES ( '"+ model.getUserName() +"' , '"+ model.getEmailAddress()+"' ,' "+model.getGender()+"' , '"+model.getLoginName()+"' , '"+ BCrypt.hashpw(model.getHashedPassword(),BCrypt.gensalt()) +"' ) ");

		System.out.println("INSERT into "+REGISTERED_USER_TABLEID +"  ( 'UserName' , 'EmailAddress' , 'Gender' , 'LoginName' ,'HashedPassword')  "
				+ "VALUES ( '"+ model.getUserName() +"' , '"+ model.getEmailAddress()+"' ,' "+model.getGender()+"' , '"+model.getLoginName()+"' , '"+ BCrypt.hashpw(model.getHashedPassword(),BCrypt.gensalt()) +"' ) ");
		Sqlresponse response =   sql.execute();

		System.out.println("response.toString()  "+response.toString());
		System.out.println("returnToken "+returnToken);


		Sql userRoleSql = fusionTable.query().sql("INSERT into "+USER_ROLE_TABLEID+" ( 'LoginName' , 'RoleID') " +" VALUES ( '" + model.getLoginName()+"' , 'Role_USER')");
		System.out.println("userRoleSql  "+ "INSERT into "+USER_ROLE_TABLEID+" ( 'LoginName' , 'RoleID') " +" VALUES ( '" + model.getLoginName()+"' , 'Role_USER' )");

		Sqlresponse  userRoleresponse =   userRoleSql.execute();

		System.out.println("userRoleresponse  "+userRoleresponse.toString());


		//		if( gloginService.isTokenExpire(token)){
		//			String newtoken = authService.getAccessToken();
		//			fusionTable =  authService.createAuthorizedClient(newtoken);
		//		}
		//		else{
		//				fusionTable =  authService.createAuthorizedClient(token);
		//		}
		//      
		Sql updateUserRoleSql = fusionTable.query().sql("INSERT into "+UPDATE_USER_ROLE_TABLEID+" ( 'LoginName' , 'RoleID') " +" VALUES ( '" + model.getLoginName()+"' , 'Role_USER')");
		System.out.println("updateUserRoleSql  "+ "INSERT into "+UPDATE_USER_ROLE_TABLEID+" ( 'LoginName' , 'RoleID') " +" VALUES ( '" + model.getLoginName()+"' , 'Role_USER' )");

		Sqlresponse  updateUserRoleresponse =   updateUserRoleSql.execute();

		System.out.println("updateUserRoleresponse  "+updateUserRoleresponse.toString());

		return returnToken;
	}






	@Override
	public Boolean isEmailAlreadyExist(String emailAddress, String token) {
		//		Fusiontables fusionTable = null;
		try {
			Fusiontables fusionTable = getFusionTableObject(token);
			//			if(token==null || gloginService.isTokenExpire(token)){
			//				String newtoken = authService.getAccessToken();
			//				fusionTable =  authService.createAuthorizedClient(newtoken);
			//			}
			//			else{
			//					fusionTable =  authService.createAuthorizedClient(token);
			//			}

			Sql sql = 	fusionTable.query().sql("Select 'EmailAddress' From "+REGISTERED_USER_TABLEID+" Where 'EmailAddress' = '"+emailAddress+"'");
			System.out.println("Select 'EmailAddress' From "+REGISTERED_USER_TABLEID+" Where 'EmailAddress' = '"+emailAddress+"'");

			Sqlresponse response = sql.execute();

			System.out.println("response "+response);

			if(response.getRows()!=null  &&   response.getRows().size()==1){
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}






	private Fusiontables getFusionTableObject(String token) {
		Fusiontables fusionTable = null;
		try {


			if(token==null || gloginService.isTokenExpire(token)){
				String newtoken = authService.getAccessToken();
				fusionTable =  authService.createAuthorizedClient(newtoken);
			}
			else{
				fusionTable =  authService.createAuthorizedClient(token);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return fusionTable;
	}






	@Override
	public Boolean isLoginNameExist(String loginName, String token) {
		try {
			Fusiontables fusionTable = getFusionTableObject(token);
			//			if(token==null || gloginService.isTokenExpire(token)){
			//				String newtoken = authService.getAccessToken();
			//				fusionTable =  authService.createAuthorizedClient(newtoken);
			//			}
			//			else{
			//					fusionTable =  authService.createAuthorizedClient(token);
			//			}

			Sql sql = 	fusionTable.query().sql("Select 'LoginName' From "+REGISTERED_USER_TABLEID+" Where 'LoginName' = '"+loginName+"'");
			System.out.println("Select 'LoginName' From "+REGISTERED_USER_TABLEID+" Where 'LoginName' = '"+loginName+"'");

			Sqlresponse response = sql.execute();

			System.out.println("response "+response);

			if(response.getRows()!=null  &&   response.getRows().size()==1){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}




	/*
	 * (non-Javadoc)
	 * @see com.petro.span.server.spring.service.RegistrationService#getRegisteredUserDetails(java.lang.String)
	 * 
	 * Get the details of registered users
	 */

	@Override
	public List<RegisteredUser> getRegisteredUserDetails(String token) {

		List<RegisteredUser> registeredUserList = new ArrayList<RegisteredUser>();

		try {
			Fusiontables fusionTable = getFusionTableObject(token);

			Sql sql = fusionTable.query().sql("Select * from "+MERGE_RegisteredUser_TABLEID);
			Sqlresponse response = sql.execute();

			System.out.println("response "+response);

			List<List<Object>> rows = response.getRows();
			for (int row = 0; row < rows.size(); row++) {
				List<Object> r = rows.get(row);
				RegisteredUser model = new RegisteredUser();

				for (int col = 0; col < r.size(); col++) {
					if(col ==0)
						model.setLoginName( (String) r.get(col));
					else if(col == 1)
						model.setUserName((String) r.get(col));
					else if(col == 2)
						model.setEmailAddress((String) r.get(col));
					else if(col == 3)
						model.setGender((String) r.get(col));
					else if(col == 4)
						model.setRoleId((String) r.get(col));

				}

				registeredUserList.add(model);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return registeredUserList;
	}






	@Override
	public String freezeUsersAccessRights(  AdminMasterModel masterModel,
			String token) {
		List<RegisteredUser> checkList =   masterModel.getCheckList();
		List<TabSelectionModel> tabCheckList  =   masterModel.getTabsCheckList();
		List<FilterSelectionModel> filtersCheckList =  masterModel.getFiltersCheckList();
		
		
		StringBuffer returnMsg = new StringBuffer();
		String successMsg = "changes succesfully";
		try {
			Fusiontables fusionTable = getFusionTableObject(token);
			for (int i = 0; i < checkList.size(); i++) {
				RegisteredUser registeredUser =  checkList.get(i);


				/** Update UPDATE_USER_ROLE_TABLEID 
				 * based on LoginName
				 */
				Sql rowIDSql = fusionTable.query().sql("Select ROWID From "+UPDATE_USER_ROLE_TABLEID+" where LoginName ='"+registeredUser.getLoginName()+"'");
				Sqlresponse rowIDResponse = rowIDSql.execute();
				System.out.println("rowIDResponse "+rowIDResponse);



				Sql sql = fusionTable.query().sql("Update "+UPDATE_USER_ROLE_TABLEID+" set RoleID = '"+registeredUser.getRoleId()+"' where ROWID ='"+ rowIDResponse.getRows().get(0).get(0)+"'");
				Sqlresponse updateResponse = sql.execute();
				System.out.println("updateResponse "+updateResponse);


				/**
				 * delete row from USER_ROLE_TABLEID so that insert new rows on the bases of Role  selection
				 */
				Sql deleteRowIDSql = fusionTable.query().sql("Select ROWID From "+USER_ROLE_TABLEID+" where LoginName ='"+registeredUser.getLoginName()+"'");
				Sqlresponse  deleteRowIDResponse = deleteRowIDSql.execute();
				System.out.println("deleteRowIDResponse "+deleteRowIDResponse);

				if(deleteRowIDResponse.getRows()!=null){
				for (int j = 0; j < deleteRowIDResponse.getRows().size(); j++) {

					Sql deleteSql = fusionTable.query().sql("Delete From "+USER_ROLE_TABLEID+" where ROWID = '"+deleteRowIDResponse.getRows().get(j).get(0)+"'");
					Sqlresponse deleteResponse = deleteSql.execute();
					System.out.println("deleteResponse "+deleteResponse);
				}
			}

				/**
				 * delete row from PrivilegedTabSelection table  based on loginUser so that only privileged user detail present in table and insert new privileged user
				 */

				Sql privilegeTabRowIDSql = fusionTable.query().sql("Select ROWID From "+PRIVILEGED_TABSELECTION_TABLEID+" where LoginName ='"+registeredUser.getLoginName()+"'");
				Sqlresponse  privilegeTabResponse = privilegeTabRowIDSql.execute();
				System.out.println("privilegeTabResponse "+privilegeTabResponse);


				if(privilegeTabResponse.getRows()!=null){
					for (int j = 0; j < privilegeTabResponse.getRows().size(); j++) {

						Sql deleteprivilegeTabSql = fusionTable.query().sql("Delete From "+PRIVILEGED_TABSELECTION_TABLEID+" where ROWID = '"+privilegeTabResponse.getRows().get(j).get(0)+"'");
						Sqlresponse deleteprivilegeTabResponse = deleteprivilegeTabSql.execute();
						System.out.println("deleteprivilegeTabResponse "+deleteprivilegeTabResponse);
					}
				}
				
				
				
				/**
				 * delete row from PrivilegedFiltersSelection table  based on loginUser so that only privileged user detail present in table and insert new privileged user
				 */

				Sql privilegeFiltersRowIDSql = fusionTable.query().sql("Select ROWID From "+PRIVILEGED_FILTERSELECTION_TABLEID+" where LoginName ='"+registeredUser.getLoginName()+"'");
				Sqlresponse  privilegeFiltersResponse = privilegeFiltersRowIDSql.execute();
				System.out.println("privilegeFiltersResponse "+privilegeFiltersResponse);


				if(privilegeFiltersResponse.getRows()!=null){
					for (int j = 0; j < privilegeFiltersResponse.getRows().size(); j++) {

						Sql deleteprivilegeFiltersSql = fusionTable.query().sql("Delete From "+PRIVILEGED_FILTERSELECTION_TABLEID+" where ROWID = '"+privilegeFiltersResponse.getRows().get(j).get(0)+"'");
						Sqlresponse deleteprivilegeFiltersResponse = deleteprivilegeFiltersSql.execute();
						System.out.println("deleteprivilegeFiltersResponse "+deleteprivilegeFiltersResponse);
					}
				}

				String roleID = registeredUser.getRoleId();
				switch (roleID) {
				case "Role_PRIVILAGE":

					Sql insertsql = fusionTable.query().sql("INSERT into  "+USER_ROLE_TABLEID+" ('LoginName','RoleID') Values ('"+registeredUser.getLoginName()+"','"+registeredUser.getRoleId()+"');INSERT into "+USER_ROLE_TABLEID+" ('LoginName','RoleID') Values ('"+registeredUser.getLoginName()+"','Role_USER')" );
					Sqlresponse insertResponse = insertsql.execute();
					System.out.println("insertResponse "+insertResponse);
					


					/**
					 * if user has 	Role_PRIVILAGE role then insert entry into PRIVILEGED_TABSELECTION_TABLEID 
					 * for Tabs Selection to that particular User
					 */
					PrivilegedTabModel model = new PrivilegedTabModel();
					for (int j = 0; j < tabCheckList.size(); j++) {


						if(tabCheckList.get(j).getTabName().equals("Technical")){
							model.setTechnical("true");
						}else if(tabCheckList.get(j).getTabName().equals("Commercial")){
							model.setCommercial("true");
						}else if(tabCheckList.get(j).getTabName().equals("Forecast")){
							model.setForecast("true");
						}else if(tabCheckList.get(j).getTabName().equals("Production")){
							model.setProduction("true");
						}
					}
					
					Sql insertTabSql = fusionTable.query().sql("INSERT into  "+PRIVILEGED_TABSELECTION_TABLEID+" ('LoginName','Technical','Commercial','Forecast','Production')  Values ('"+registeredUser.getLoginName()+"','"+model.getTechnical()+"','"+model.getCommercial()+"','"+model.getForecast()+"','"+model.getProduction()+"')" );
					Sqlresponse insertTabResponse = insertTabSql.execute();
					System.out.println("insertTabResponse "+insertTabResponse);
					
					
					
					/**
					 * if user has 	Role_PRIVILAGE role then insert entry into PRIVILEGED_FILTERSELECTION_TABLEID 
					 * for Tabs Selection to that particular User
					 */
					PrivilegedFiltersModel filtersModel = new PrivilegedFiltersModel();
					for (int j = 0; j < filtersCheckList.size(); j++) {

						if(filtersCheckList.get(j).getFilterName().equals("Quality Zone"))
							filtersModel.setQuailtyZone("true");
						else if(filtersCheckList.get(j).getFilterName().equals("TownShip/Range"))
							filtersModel.setTownshipRange("true");
						else if(filtersCheckList.get(j).getFilterName().equals("Operator"))
							filtersModel.setOperator("true");
						else if(filtersCheckList.get(j).getFilterName().equals("Lease Name "))
							filtersModel.setLeaseName("true");
						
						else if(filtersCheckList.get(j).getFilterName().equals("Well Name "))
							filtersModel.setWellName("true");
						
						else if(filtersCheckList.get(j).getFilterName().equals("Well Type "))
							filtersModel.setWellType("true");
						
						else if(filtersCheckList.get(j).getFilterName().equals("Well Orientation "))
							filtersModel.setWellOrientation("true");
						
						
						else if(filtersCheckList.get(j).getFilterName().equals("Fluid Type"))
							filtersModel.setFluidType("true");
						
						
						else if(filtersCheckList.get(j).getFilterName().equals("Well Status "))
							filtersModel.setWellStatus("true");
						
						
						else if(filtersCheckList.get(j).getFilterName().equals("Producing Zone"))
							filtersModel.setProducingZone("true");
						
						
						else if(filtersCheckList.get(j).getFilterName().equals("Permit Date"))
							filtersModel.setPermitDate("true");
						
						else if(filtersCheckList.get(j).getFilterName().equals("Spud Date"))
							filtersModel.setSpudDate("true");
						
						else if(filtersCheckList.get(j).getFilterName().equals("Completion Date"))
							filtersModel.setCompletionDate("true");
						
						
						else if(filtersCheckList.get(j).getFilterName().equals("First Production Date "))
							filtersModel.setFirstProductionDt("true");
						
						else if(filtersCheckList.get(j).getFilterName().equals("Search By"))
							filtersModel.setSearchBy("true");
					}
					
					Sql insertFiltersSql = fusionTable.query().sql("INSERT into  "+PRIVILEGED_FILTERSELECTION_TABLEID+" ('LoginName','QualityZone','Township/Range','Operator','LeaseName','WellName','WellType','WellOrientation','FluidType','WellStatus','ProducingZone','PermitDate','SpudDate','CompletionDate','FirstProductionDate','SearchBy')  Values "
							+ "('"+registeredUser.getLoginName()+"','"+filtersModel.getQuailtyZone()+"','"+filtersModel.getTownshipRange()+"','"+filtersModel.getOperator()+"','"+filtersModel.getLeaseName()+"','"+filtersModel.getWellName()+"','"+filtersModel.getWellType()+"','"+filtersModel.getWellOrientation()+"','"+filtersModel.getFluidType()+"','"+filtersModel.getWellStatus()
							+"','"+filtersModel.getProducingZone()+"','"+filtersModel.getPermitDate()+"','"+filtersModel.getSpudDate()+"','"+filtersModel.getCompletionDate()+"','"+filtersModel.getFirstProductionDt()+"','"+filtersModel.getSearchBy()+"')" );
					Sqlresponse insertFiltersResponse = insertFiltersSql.execute();
					System.out.println("insertFiltersResponse "+insertFiltersResponse);
					
					if(returnMsg.length()==0)
						returnMsg.append(successMsg);
					
					break;

				case "Role_ADMIN":

					Sql insertADMINSql = fusionTable.query().sql("INSERT into  "+USER_ROLE_TABLEID+" ('LoginName','RoleID') Values ('"+registeredUser.getLoginName()+"','"+registeredUser.getRoleId()+"');INSERT into "+USER_ROLE_TABLEID+" ('LoginName','RoleID') Values ('"+registeredUser.getLoginName()+"','Role_USER');INSERT into "+USER_ROLE_TABLEID+" ('LoginName','RoleID') Values ('"+registeredUser.getLoginName()+"','Role_PRIVILAGE')" );
					Sqlresponse insertADMINResponse = insertADMINSql.execute();
					System.out.println("insertADMINResponse "+insertADMINResponse);
					if(returnMsg.length()==0)
						returnMsg.append(successMsg);
					break;

				default:

					Sql insertDefaultSql = fusionTable.query().sql("INSERT into  "+USER_ROLE_TABLEID+" ('LoginName','RoleID') Values ('"+registeredUser.getLoginName()+"','"+registeredUser.getRoleId()+"')" );
					Sqlresponse insertDefaultResponse = insertDefaultSql.execute();
					System.out.println("insertDefaultResponse "+insertDefaultResponse);
					if(returnMsg.length()==0)
						returnMsg.append(successMsg);
					break;
				}

			}



		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMsg.toString();
	}

}

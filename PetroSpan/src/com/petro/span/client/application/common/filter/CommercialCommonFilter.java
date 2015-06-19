package com.petro.span.client.application.common.filter;

import com.petro.span.client.AuthUtil;
import com.petro.span.client.Loader;
import com.petro.span.client.PlayAreaList;
import com.petro.span.shared.CurrentUser;
import com.petro.span.shared.UserInfo;

public class CommercialCommonFilter extends CommonFilter{

	public CommercialCommonFilter(CommonFilterUiBinder uiBinder,
			PlayAreaList playAreaObject, AuthUtil authUtil, UserInfo user,
			Loader loader, CurrentUser currentUser) {
		super(uiBinder, playAreaObject, authUtil, user, loader, currentUser);
	}

}
